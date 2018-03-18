package com.orm.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the operaciones database table.
 * 
 */
@Entity
@Table(name="operaciones")
@NamedQuery(name="Operacione.findAll", query="SELECT o FROM Operacione o")
public class Operacion implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private double cantidad;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechahora;

	private double saldoactualizado;

	private String tipo;

	//bi-directional many-to-one association to Cuentasbancaria
	@ManyToOne
	@JoinColumn(name="id_cuentabancaria")
	private CuentaBancaria cuentasbancaria;

	public Operacion() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public double getCantidad() {
		return this.cantidad;
	}

	public void setCantidad(double cantidad) {
		this.cantidad = cantidad;
	}

	public Date getFechahora() {
		return this.fechahora;
	}

	public void setFechahora(Date fechahora) {
		this.fechahora = fechahora;
	}

	public double getSaldoactualizado() {
		return this.saldoactualizado;
	}

	public void setSaldoactualizado(double saldoactualizado) {
		this.saldoactualizado = saldoactualizado;
	}

	public String getTipo() {
		return this.tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public CuentaBancaria getCuentasbancaria() {
		return this.cuentasbancaria;
	}

	public void setCuentasbancaria(CuentaBancaria cuentasbancaria) {
		this.cuentasbancaria = cuentasbancaria;
	}

}