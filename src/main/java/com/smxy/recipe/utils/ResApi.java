/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.utils 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:37:34 
 */
package com.smxy.recipe.utils;

/**
 * @author zpx
 *
 */
public class ResApi<T> {
	private int code;//状态码
	private String msg;//信息
	private T data;//数据
	public ResApi() {
		super();
		// TODO Auto-generated constructor stub
	}
	public ResApi(int code, String msg, T data) {
		super();
		this.code = code;
		this.msg = msg;
		this.data = data;
	}
	public int getCode() {
		return code;
	}
	public void setCode(int code) {
		this.code = code;
	}
	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}
	public T getData() {
		return data;
	}
	public void setData(T data) {
		this.data = data;
	}
	@Override
	public String toString() {
		return "ResApi [code=" + code + ", msg=" + msg + ", data=" + data + "]";
	}
}

