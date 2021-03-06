package com.orm.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the propietarios database table.
 * 
 */
@Entity
@Table(name="propietarios")
@NamedQuery(name="Propietario.findAll", query="SELECT p FROM Propietario p")
public class Propietario implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private String dni;

	private String nombre;

	private String numerosecreto;

	private String primerapellido;

	private String segundoapellido;

	private String usuario;

	//bi-directional many-to-one association to Cuentasbancaria
	@OneToMany(mappedBy="propietario")
	private List<CuentasBancarias> cuentasbancarias;

	public Propietario() {
	}

	public String getDni() {
		return this.dni;
	}

	public void setDni(String dni) {
		this.dni = dni;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getNumerosecreto() {
		return this.numerosecreto;
	}

	public void setNumerosecreto(String numerosecreto) {
		this.numerosecreto = numerosecreto;
	}

	public String getPrimerapellido() {
		return this.primerapellido;
	}

	public void setPrimerapellido(String primerapellido) {
		this.primerapellido = primerapellido;
	}

	public String getSegundoapellido() {
		return this.segundoapellido;
	}

	public void setSegundoapellido(String segundoapellido) {
		this.segundoapellido = segundoapellido;
	}

	public String getUsuario() {
		return this.usuario;
	}

	public void setUsuario(String usuario) {
		this.usuario = usuario;
	}

	public List<CuentasBancarias> getCuentasbancarias() {
		return this.cuentasbancarias;
	}

	public void setCuentasbancarias(List<CuentasBancarias> cuentasbancarias) {
		this.cuentasbancarias = cuentasbancarias;
	}

	public CuentasBancarias addCuentasbancaria(CuentasBancarias cuentasbancaria) {
		getCuentasbancarias().add(cuentasbancaria);
		cuentasbancaria.setPropietario(this);

		return cuentasbancaria;
	}

	public CuentasBancarias removeCuentasbancaria(CuentasBancarias cuentasbancaria) {
		getCuentasbancarias().remove(cuentasbancaria);
		cuentasbancaria.setPropietario(null);

		return cuentasbancaria;
	}

}