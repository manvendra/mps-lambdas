package com.mps.lambdas;

import java.util.Map;

/**
 * @author manvendrasingh
 * @since 2022-May-22
 * <p>
 *     A common response class for all my lambdas.
 *     It was generated using <b>software.amazon.awssdk:archetype-lambda</b>
 * </p>
 */
public class Response {

	private final String message;
	private final Map<String, Object> input;

	public Response(String message, Map<String, Object> input) {
		this.message = message;
		this.input = input;
	}


	public String getMessage() {
		return this.message;
	}

	public Map<String, Object> getInput() {
		return this.input;
	}
}
