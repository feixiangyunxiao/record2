package com.game.common;

import java.util.List;

public class JsonModel <T> {
	
	private int code;
	private String msg;
	private List<T> data;
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
	public List<T> getData() {
		return data;
	}
	public void setData(List<T> data) {
		this.data = data;
	}
	
	public static <E> JsonModel<E> creatJson (int code, String msg, List<E> data) {
		JsonModel<E> result = new JsonModel<E>();
		result.setCode(code);
		result.setMsg(msg);
		result.setData(data);
		return result;
	}
}
