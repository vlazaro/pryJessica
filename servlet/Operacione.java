package com.activida5.servlet.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the operaciones database table.
 * 
 */
@Entity
@Table(name="operaciones")
@NamedQueries({
@NamedQuery(name="Operacione.findAll", query="SELECT o FROM Operacione o"),
@NamedQuery(name="Operacione.findByNumCuenta", query="SELECT o FROM Operacione o where o.cuentasbancaria.numcuenta = :numcuenta"),
@NamedQuery(name="Operacione.findBySaldoRojo", query="SELECT o FROM Operacione o where o.saldoactualizado < 0")

})

public class Operacione implements Serializable {
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
	private Cuentasbancaria cuentasbancaria;

	public Operacione() {
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

	public Cuentasbancaria getCuentasbancaria() {
		return this.cuentasbancaria;
	}

	public void setCuentasbancaria(Cuentasbancaria cuentasbancaria) {
		this.cuentasbancaria = cuentasbancaria;
	}

}