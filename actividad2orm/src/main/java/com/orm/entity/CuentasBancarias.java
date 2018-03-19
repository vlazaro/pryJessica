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
@NamedQuery(name="CuentasBancarias.findAll", query="SELECT c FROM CuentasBancarias c")
public class CuentasBancarias implements Serializable {
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
	private List<Operaciones> operaciones;

	public CuentasBancarias() {
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

	public List<Operaciones> getOperaciones() {
		return this.operaciones;
	}

	public void setOperaciones(List<Operaciones> operaciones) {
		this.operaciones = operaciones;
	}

	public Operaciones addOperacione(Operaciones operacione) {
		getOperaciones().add(operacione);
		operacione.setCuentasbancaria(this);

		return operacione;
	}

	public Operaciones removeOperacione(Operaciones operacione) {
		getOperaciones().remove(operacione);
		operacione.setCuentasbancaria(null);

		return operacione;
	}

}