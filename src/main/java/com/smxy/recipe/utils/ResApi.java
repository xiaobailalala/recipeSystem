/**   
 * Copyright © 2018 eSunny Info. Developer Stu. All rights reserved.
 * 
 * @Package: com.smxy.recipe.utils 
 * @author: 雏实。   
 * Build File @date: 2018年7月4日 下午1:37:34 
 */
package com.smxy.recipe.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author zpx
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResApi<T> implements Serializable {
	private int code;
	private String msg;
	private T data;

	public ResApi(T data) {
		this.data = data;
	}

	public ResApi(int code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public static ResApi<String> getSuccess(){
		return new ResApi<>(200, "success", "success");
	}

	public static ResApi<Object> getSuccess(Object o){
		return new ResApi<>(200, "success", o);
	}

	public static ResApi<Object> getData(Object o){
		return new ResApi<>(o);
	}

	public static ResApi<String> getError(int code, String msg){
		return new ResApi<>(code, msg);
	}

	public static ResApi<String> getError(){
		return new ResApi<>(500, "系统出错", "failed");
	}
}

