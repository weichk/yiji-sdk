/**
 * create by zhike@yiji.com
 * date:2015年4月15日
 */
package com.yiji.openapi.sdk.marshall;


import com.yiji.openapi.sdk.common.annotation.OpenApiField;
import com.yiji.openapi.sdk.common.enums.ApiDataType;
import com.yiji.openapi.sdk.common.message.ApiMessage;
import com.yiji.openapi.sdk.common.utils.Reflections;
import com.yiji.openapi.sdk.common.utils.Strings;
import com.yiji.openapi.sdk.exception.ApiClientException;
import com.yiji.openapi.sdk.message.MessageFactory;
import com.yiji.openapi.sdk.security.CryptoHandler;
import com.yiji.openapi.sdk.sign.SignTypeEnum;
import com.yiji.openapi.sdk.sign.Signer;
import com.yiji.openapi.sdk.sign.SignerFactory;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Set;

/**
 * @author zhike@yiji.com
 */
public abstract class ApiSdkMarshalSupport {

    private static Log logger = LogFactory.getLog(ApiSdkMarshalSupport.class);

    private SignerFactory signerFactory;

    protected MessageFactory messageFactory;

    protected CryptoHandler cryptoHandler;

    public void setSignerFactory(SignerFactory signerFactory) {
        this.signerFactory = signerFactory;
    }

    public SignerFactory getSignerFactory() {
        return signerFactory;
    }

    protected Signer getSigner(SignTypeEnum signType) {
        return signerFactory.getSigner(signType.name());
    }

    /**
     * 加密
     * @param bean
     */
    protected void encrypt(ApiMessage bean) {
        Set<Field> fields = Reflections.getFields(bean.getClass());
        for (Field f : fields) {
            try {
                OpenApiField openApiField = f.getAnnotation(OpenApiField.class);
                if (openApiField == null || !openApiField.security()) {
                    continue;
                }
                Object value = Reflections.invokeGetter(bean, f.getName());
                if (value instanceof String && Strings.isNotEmpty((String) value)) {
                    value = doEncrypt((String) value);
                    Reflections.setFieldValue(bean, f.getName(), value);
                } else {
                    ObjectAccessor<ApiMessage> objectAccessor = ObjectAccessor.of(bean);
                    if (objectAccessor.getField(f.getName()) != null && value != null) {
                        ApiDataType filedType = objectAccessor.getFieldType(objectAccessor.getField(f.getName()));
                        if (filedType.JSON.equals(filedType)) {
                            setResEncryptValue(value);
                        } else if (filedType.JSON_ARRAY.equals(filedType)) {
                            List<Object> listObject = (List<Object>) Reflections.invokeGetter(bean,f.getName());
                            if (listObject != null) {
                                for (Object object : listObject) {
                                    setResEncryptValue(object);
                                }
                            }
                        }
                    }
                }
            } catch (NoSuchMethodException ne) {
                logger.error("参数"+f.getName()+"没有get、set方法不进行加密运算");
            } catch (Exception e) {
                throw new ApiClientException("安全数据项加密失败:" + e.getMessage());
            }
        }
    }

    private void setResEncryptValue(Object object) throws NoSuchMethodException {
        Field[] subFields = object.getClass().getDeclaredFields();
        for (Field subField : subFields) {
            OpenApiField openApiField = subField.getAnnotation(OpenApiField.class);
            if (openApiField == null || !openApiField.security()) {
                continue;
            }
            subField.setAccessible(true);
            Object subValue = Reflections.invokeGetter(object, subField.getName());
            if (subValue instanceof String && Strings.isNotEmpty((String) subValue)) {
                subValue = doEncrypt((String) subValue);
                Reflections.setFieldValue(object, subField.getName(), subValue);
            }
        }
    }

    /**
     * 解密
     * @param bean
     */
    protected void decrypt(ApiMessage bean) {
        Set<Field> fields = Reflections.getFields(bean.getClass());
        for (Field f : fields) {
            try {
                OpenApiField openApiField = f.getAnnotation(OpenApiField.class);
                if (openApiField == null || !openApiField.security()) {
                    continue;
                }
                Object value = Reflections.invokeGetter(bean, f.getName());
                if (value instanceof String && Strings.isNotEmpty((String) value)) {
                    value = doDecrypt((String) value);
                    Reflections.setFieldValue(bean, f.getName(), value);
                } else {
                    ObjectAccessor<ApiMessage> objectAccessor = ObjectAccessor.of(bean);
                    if (objectAccessor.getField(f.getName()) != null && value != null) {
                        ApiDataType filedType = objectAccessor.getFieldType(objectAccessor.getField(f.getName()));
                        if (filedType.JSON.equals(filedType)) {
                            setResDecryptValue(value);
                        } else if (filedType.JSON_ARRAY.equals(filedType)) {
                            List<Object> listObject = (List<Object>) Reflections.invokeGetter(bean,f.getName());
                            if (listObject != null) {
                                for (Object object : listObject) {
                                    setResDecryptValue(object);
                                }
                            }
                        }
                    }
                }
            } catch (NoSuchMethodException ne) {
                logger.error("参数"+f.getName()+"没有get、set方法不进行解密运算");
            } catch (Exception e) {
                throw new ApiClientException("安全数据项解密失败:" + e.getMessage());
            }
        }
    }

    private void setResDecryptValue(Object object) throws NoSuchMethodException {
        Field[] subFields = object.getClass().getDeclaredFields();
        for (Field subField : subFields) {
            OpenApiField openApiField = subField.getAnnotation(OpenApiField.class);
            if (openApiField == null || !openApiField.security()) {
                continue;
            }
            subField.setAccessible(true);
            Object subValue = Reflections.invokeGetter(object, subField.getName());
            if (subValue instanceof String && Strings.isNotEmpty((String) subValue)) {
                subValue = doDecrypt((String) subValue);
                Reflections.setFieldValue(object, subField.getName(), subValue);
            }
        }
    }
    protected String doEncrypt(String value) {
        return cryptoHandler.encrypt(value);
    }

    protected String doDecrypt(String value) {
        return cryptoHandler.decrypt(value);
    }


    public void setMessageFactory(MessageFactory messageFactory) {
        this.messageFactory = messageFactory;
    }

    public void setCryptoHandler(CryptoHandler cryptoHandler) {
        this.cryptoHandler = cryptoHandler;
    }

    public MessageFactory getMessageFactory() {
        return messageFactory;
    }

    public CryptoHandler getCryptoHandler() {
        return cryptoHandler;
    }
}
