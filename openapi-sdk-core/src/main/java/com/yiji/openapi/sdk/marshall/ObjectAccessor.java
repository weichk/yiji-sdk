package com.yiji.openapi.sdk.marshall;

import com.yiji.openapi.sdk.common.annotation.OpenApiField;
import com.yiji.openapi.sdk.common.convert.ApiServiceConversionService;
import com.yiji.openapi.sdk.common.enums.ApiDataType;
import com.yiji.openapi.sdk.common.utils.ApiDataTypeUtils;
import com.yiji.openapi.sdk.exception.ApiClientException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.convert.ConversionService;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * 实现对对象属性的访问 如果是复杂对象,用json字符串存取.
 *
 * @author qzhanbo@yiji.com
 */
public class ObjectAccessor<T> {
    private static final Logger logger = LoggerFactory.getLogger(ObjectAccessor.class);

    private final T target;
    private static ConversionService conversionService = ApiServiceConversionService.INSTANCE;

    private Map<String, Field> fieldMap;
    private final static ConcurrentMap<Class<?>, Map<String, Field>> classMap = new ConcurrentHashMap<>();

    public static <T> ObjectAccessor<T> of(T target) {
        return new ObjectAccessor<T>(target);
    }

    private ObjectAccessor(T target) {
        Assert.notNull(target, "Target object must not be null");
        this.target = target;
        Map<String, Field> fieldMap = classMap.get(target.getClass());
        if (fieldMap == null) {
            final Map<String, Field> tmpMap = new HashMap<>();
            ReflectionUtils.doWithFields(this.target.getClass(), new ReflectionUtils.FieldCallback() {
                public void doWith(Field field) {
                    OpenApiField openApiField = field.getAnnotation(OpenApiField.class);
                    if (openApiField == null) {
                        logger.warn("发现没有标注OpenApiField的字段{}", field);
                        return;
                    }
                    if (tmpMap.containsKey(field.getName())) {
                        throw new ApiClientException(field + "和" + tmpMap.get(field.getName()) + "重名");
                    } else {
                        tmpMap.put(field.getName(), field);
                    }
                }
            });
            classMap.put(target.getClass(), tmpMap);
            fieldMap = tmpMap;
        }
        this.fieldMap = fieldMap;
    }


    /**
     * 获取对象属性名集合
     *
     * @return
     */
    public Set<String> propertySet() {
        return this.fieldMap.keySet();
    }

    /**
     * 获取非transient属性名集合
     *
     * @return
     */
    public Set<String> propertySetExcludeTransient() {
        Set<String> fieldSet = new HashSet<>();
        for (Map.Entry<String, Field> entry : fieldMap.entrySet()) {
            if (!Modifier.isTransient(entry.getValue().getModifiers())) {
                fieldSet.add(entry.getKey());
            }
        }
        return fieldSet;
    }

    public boolean isCollection(Field field) {
        return List.class.isAssignableFrom(field.getType()) || Map.class.isAssignableFrom(field.getType())
                || Set.class.isAssignableFrom(field.getType()) || field.getType().isArray();
    }

    public boolean isJSONObject(Field field) {
        Class type = field.getType();
        return Collection.class.isAssignableFrom(type) || Map.class.isAssignableFrom(type) || type.isArray()
                || Number.class.isAssignableFrom(type) || type.isPrimitive() || Boolean.class.isAssignableFrom(type);
    }

    public Field getField(String propertyName) {
        Field field = this.fieldMap.get(propertyName);
        return  field;
    }

    public ApiDataType getFieldType(Field field) {
        return ApiDataTypeUtils.converetToApiData(field);
    }

}
