package com.orm.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cuentasbancarias database table.
 * 
 */
@Entity
@Table(name="cuentasbancarias")
@NamedQuery(name="Cuentasbancaria.findAll", query="SELECT c FROM Cuentasbancaria c")
public class CuentaBancaria implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	private String numcuenta;

	private double saldo;

	//bi-directional many-to-one association to Propietario
	@ManyToOne
	@JoinColumn(name="dni_propietario")
	private Propietario propietario;

	//bi-directional many-to-one association to Operacione
	@OneToMany(mappedBy="cuentasbancaria")
	private List<Operacion> operaciones;

	public CuentaBancaria() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNumcuenta() {
		return this.numcuenta;
	}

	public void setNumcuenta(String numcuenta) {
		this.numcuenta = numcuenta;
	}

	public double getSaldo() {
		return this.saldo;
	}

	public void setSaldo(double saldo) {
		this.saldo = saldo;
	}

	public Propietario getPropietario() {
		return this.propietario;
	}

	public void setPropietario(Propietario propietario) {
		this.propietario = propietario;
	}

	public List<Operacion> getOperaciones() {
		return this.operaciones;
	}

	public void setOperaciones(List<Operacion> operaciones) {
		this.operaciones = operaciones;
	}

	public Operacion addOperacione(Operacion operacione) {
		getOperaciones().add(operacione);
		operacione.setCuentasbancaria(this);

		return operacione;
	}

	public Operacion removeOperacione(Operacion operacione) {
		getOperaciones().remove(operacione);
		operacione.setCuentasbancaria(null);

		return operacione;
	}

}