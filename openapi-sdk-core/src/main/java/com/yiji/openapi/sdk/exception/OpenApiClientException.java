package com.yiji.openapi.sdk.exception;

/**
 * openApi 客户端异常
 * 
 * @author zhike@yiji.com
 * @date 2014年6月11日
 */
public class OpenApiClientException extends RuntimeException {

	/** UID */
	private static final long serialVersionUID = 788548256305224364L;

	public OpenApiClientException() {
		super();
	}

	/**
	 * @param message
	 * @param cause
	 */
	public OpenApiClientException(String message, Throwable cause) {
		super(message, cause);
	}

	/**
	 * @param message
	 */
	public OpenApiClientException(String message) {
		super(message);
	}

	/**
	 * @param cause
	 */
	public OpenApiClientException(Throwable cause) {
		super(cause);
	}

}
