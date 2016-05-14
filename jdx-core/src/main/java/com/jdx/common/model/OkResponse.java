package com.jdx.common.model;

import java.util.List;

public class OkResponse {

	//Parámetro que contiene el mensaje de error a mostrar
	private String msg;

	
	public OkResponse(String msg) {
		super();
		this.msg = msg;
	}

	/**
	 * @return the msg
	 */
	public String getMsg() {
		return msg;
	}

	/**
	 * @param msg the msg to set
	 */
	public void setMsg(String msg) {
		this.msg = msg;
	}
	
	
}
