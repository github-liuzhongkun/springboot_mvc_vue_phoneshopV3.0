package com.city.shophibernate.result;

//返回单个数据或对象的结果
public class ResultOne<T> {
	private T data =null; //返回结果
	private int code=0; //状态 OK 正常， ERROR：异常
	private String message=null; //操作信息，如果是异常就是异常原因

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
