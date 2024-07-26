package com.nagarro.accountmanagmentservice.dto;

public class HeaderResponse {

	private boolean status;
	private String msg;
	
	private Object data;

	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	public String getMsg() {
		return msg;
	}
	public void setMsg(String msg) {
		this.msg = msg;
	}

	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
	
}
