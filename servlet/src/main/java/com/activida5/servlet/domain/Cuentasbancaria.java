package com.activida5.servlet.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the cuentasbancarias database table.
 * 
 */
@Entity
@Table(name="cuentasbancarias")
@NamedQueries({
@NamedQuery(name="Cuentasbancaria.findAll", query="SELECT c FROM Cuentasbancaria c"),
@NamedQuery(name="Cuentabancaria.findByNumCuenta", query="SELECT c FROM Cuentasbancaria c where c.numcuenta = :cuenta")

})
public class Cuentasbancaria implements Serializable {
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
	private List<Operacione> operaciones;

	public Cuentasbancaria() {
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

	public List<Operacione> getOperaciones() {
		return this.operaciones;
	}

	public void setOperaciones(List<Operacione> operaciones) {
		this.operaciones = operaciones;
	}

	public Operacione addOperacione(Operacione operacione) {
		getOperaciones().add(operacione);
		operacione.setCuentasbancaria(this);

		return operacione;
	}

	public Operacione removeOperacione(Operacione operacione) {
		getOperaciones().remove(operacione);
		operacione.setCuentasbancaria(null);

		return operacione;
	}

}