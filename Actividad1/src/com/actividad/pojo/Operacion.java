package com.actividad.pojo;

import java.util.Date;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class Operacion {
	private String fechahora;
	private String numcuenta;
	private String propietario;
	private String tipo;
	private String cantidad;
	private String saldo;
	
	
	public String getNumcuenta() {
		return numcuenta;
	}
	@XmlElement
	public void setNumcuenta(String numcuenta) {
		this.numcuenta = numcuenta;
	}
	public String getPropietario() {
		return propietario;
	}
	@XmlElement
	public void setPropietario(String propietario) {
		this.propietario = propietario;
	}
	public String getTipo() {
		return tipo;
	}
	@XmlElement
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public String getCantidad() {
		return cantidad;
	}
	@XmlElement
	public void setCantidad(String cantidad) {
		this.cantidad = cantidad;
	}
	public String getSaldo() {
		return saldo;
	}
	@XmlElement
	public void setSaldo(String saldo) {
		this.saldo = saldo;
	}
	public String getFechahora() {
		return fechahora;
	}
	@XmlAttribute
	public void setFechahora(String fechahora) {
		this.fechahora = fechahora;
	}
	@Override
	public String toString() {
		return "Operacion [fechahora=" + fechahora + ", numcuenta=" + numcuenta + ", propietario=" + propietario
				+ ", tipo=" + tipo + ", cantidad=" + cantidad + ", saldo=" + saldo + "]";
	}
	
}
