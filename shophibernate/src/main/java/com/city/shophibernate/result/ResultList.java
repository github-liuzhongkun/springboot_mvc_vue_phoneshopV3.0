package com.city.shophibernate.result;

import java.util.List;

//用于封装列表方式 包括分页列表结果
public class ResultList<T> {
	private int page=0; //页的序号，第几页
	private int pageCount=0; //页数
	private int rows=0; //每个页显示的个数
	private int count=0; //对象个数 
	private List<T> data =null;
	private int code=0; //状态， OK正常，ERROR：异常
	private String message=null; //操作信息，

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getPageCount() {
		return pageCount;
	}

	public void setPageCount(int pageCount) {
		this.pageCount = pageCount;
	}

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public List<T> getData() {
		return data;
	}

	public void setData(List<T> data) {
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
