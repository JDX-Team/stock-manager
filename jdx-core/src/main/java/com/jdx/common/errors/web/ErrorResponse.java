package com.jdx.common.errors.web;

import java.util.List;

public class ErrorResponse {

	//Parámetro que contiene el mensaje de error a mostrar
	private String errorMsg;
	//Este parámetro contendrá las columnas que generan el error
	private List<String> columns;
	//Parametro de recarga de la página
	private boolean reload = false;
	
	public String getErrorMsg() { return errorMsg; }
	public void setErrorMsg(String errorMsg) { this.errorMsg = errorMsg; }
	public List<String> getColumns() { return columns; }
	public void setColumns(List<String> columns) { this.columns = columns; }
	public boolean isReload() {	return reload; }
	public void setReload(boolean reload) {	this.reload = reload; }
	
}
