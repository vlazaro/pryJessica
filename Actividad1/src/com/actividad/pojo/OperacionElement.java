package com.actividad.pojo;

public enum OperacionElement {
	
	OPERACION("operacion"),FECHA_HORA("fechahora"),CANTIDAD("cantidad"),NUMCUENTA("numcuenta"),PROPIETARIO("propietario"),SALDO("saldo"),TIPO("tipo");
	
	private String name;
	
	private OperacionElement(String name) {
		this.setName(name);
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	
	
}
