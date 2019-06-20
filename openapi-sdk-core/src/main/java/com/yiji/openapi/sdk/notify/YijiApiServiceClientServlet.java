/**
 * coding by zhike@yiji.com
 */
package com.yiji.openapi.sdk.notify;

import com.yiji.openapi.sdk.common.utils.AbstractSpringServlet;
import com.yiji.openapi.sdk.common.utils.Servlets;
import com.yiji.openapi.sdk.common.utils.Strings;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author zhike@yiji.com
 * @date 2014年6月16日
 */
public class YijiApiServiceClientServlet extends AbstractSpringServlet {
	/** UID */
	private static final long serialVersionUID = 6169228472722574634L;

	private NotifyHandlerDispatcher notifyHandlerDispatcher;

	private static final int SUCCESS_RESPONSE_CODE = 200;
	private static final String SUCCESS_RESPONSE_BODY = "SUCCESS";
	private static final String NOTIFY_HANDLER_DISPATCHER = "apiSdkDefaultNotifyHandlerDispatcher";

	private static final String SUCCESS_RESPONSE_CODE_KEY = "success_response_code";
	private static final String SUCCESS_RESPONSE_BODY_KEY = "success_response_body";
	private static final String NOTIFY_HANDLER_DISPATCHER_KEY = "notfy_handler_dispatcher_service";


	@Override
	protected void doInit() {
		super.doInit();
		String serviceKey = getInitParameter(NOTIFY_HANDLER_DISPATCHER_KEY);
		if(Strings.isBlank(serviceKey)){
			serviceKey = NOTIFY_HANDLER_DISPATCHER;
		}
		notifyHandlerDispatcher = getBean(serviceKey, NotifyHandlerDispatcher.class);
	}
	
	@Override
	protected void doService(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
			Map<String, Object> received = Servlets.getParametersStartingWith(request, null);
			Map<String, String> notifyData = new HashMap<String, String>(received.size());
			for (Map.Entry<String, Object> entry : received.entrySet()) {
				notifyData.put(entry.getKey(), (String) entry.getValue());
			}
			String notifyUrl = Servlets.getRequestPath(request);
			notifyHandlerDispatcher.dispatch(notifyUrl, notifyData);
			response.setStatus(getSuccessResponseCode());
			Servlets.writeResponse(response, getSuccessResponseBody(), null);
		} catch (Exception e) {
			Servlets.writeResponse(response, "failure", null);
		}
	}
	
	protected String getSuccessResponseBody(){
		String body = getInitParameter(SUCCESS_RESPONSE_BODY_KEY);
		if(Strings.isBlank(body)){
			body = SUCCESS_RESPONSE_BODY;
		}
		return body;
	}
	
	protected int getSuccessResponseCode(){
		try {
			return Integer.parseInt(getInitParameter(SUCCESS_RESPONSE_CODE_KEY));
		} catch (Exception e) {
			return SUCCESS_RESPONSE_CODE;
		}
	}

}
