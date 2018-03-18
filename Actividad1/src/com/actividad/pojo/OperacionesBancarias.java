package com.actividad.pojo;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class OperacionesBancarias {
	
	
	List<Operacion> operacion = new ArrayList<Operacion>();

	public List<Operacion> getOperacion() {
		return operacion;
	}

	@XmlElement
	public void setOperacion(List<Operacion> operacion) {
		this.operacion = operacion;
	}

	@Override
	public String toString() {
		return "OperacionesBancarias [operacion=" + operacion + "]";
	}

	


	
	

}
