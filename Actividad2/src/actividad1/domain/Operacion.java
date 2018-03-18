package actividad1.domain;

import java.sql.Date;

public class Operacion {
	
	private int id;
	private Date fecha_hora;
	private String tipo;
	private Double cantidad;
	private Double saldoActualizado;
	private int id_cuenta;
	
	
	public Date getFecha_hora() {
		return fecha_hora;
	}
	public void setFecha_hora(Date fecha_hora) {
		this.fecha_hora = fecha_hora;
	}
	public String getTipo() {
		return tipo;
	}
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}
	public Double getCantidad() {
		return cantidad;
	}
	public void setCantidad(Double cantidad) {
		this.cantidad = cantidad;
	}
	
	public int getId_cuenta() {
		return id_cuenta;
	}
	public void setId_cuenta(int id_cuenta) {
		this.id_cuenta = id_cuenta;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Double getSaldoActualizado() {
		return saldoActualizado;
	}
	public void setSaldoActualizado(Double saldoActualizado) {
		this.saldoActualizado = saldoActualizado;
	}

}
