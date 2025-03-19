package com.sparrow.utils;

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * @Author: Sparrow
 * @Description: TODO
 * @DateTime: 2025/3/17 21:28
 **/
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ResponseResult<T> {

	private Integer code;

	private String msg;

	private T data;

	public ResponseResult(String msg, Integer code) {
		this.msg = msg;
		this.code = code;
	}

	public ResponseResult(Integer code, T data) {
		this.code = code;
		this.data = data;
	}

	public ResponseResult(Integer code, String msg, T data) {
		this.code = code;
		this.msg = msg;
		this.data = data;
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}

	public T getData() {
		return data;
	}
}
