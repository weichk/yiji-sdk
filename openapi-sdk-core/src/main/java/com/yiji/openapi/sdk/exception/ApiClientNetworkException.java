/**
 * create by zhike@yiji.com
 * date:2015年3月28日
 */
package com.yiji.openapi.sdk.exception;

/**
 * @author zhike@yiji.com
 *
 */
public class ApiClientNetworkException extends ApiClientException {

	/** serialVersionUID */
	private static final long serialVersionUID = -4222566964668918958L;

	public ApiClientNetworkException() {
		super();
	}

	public ApiClientNetworkException(String message, Throwable cause) {
		super(message, cause);
	}

	public ApiClientNetworkException(String message) {
		super(message);
	}

	public ApiClientNetworkException(Throwable cause) {
		super(cause);
	}

}
