package com.activida5.servlet.domain;

import java.io.Serializable;
import javax.persistence.*;
import java.util.List;


/**
 * The persistent class for the propietarios database table.
 * 
 */
@Entity
@Table(name="propietarios")
@NamedQueries({
@NamedQuery(name="Propietario.findAll", query="SELECT p FROM Propietario p"),
@NamedQuery(name="Propietario.findBydni", query="SELECT p FROM Propietario p where p.dni= :dni"),
@NamedQuery(name="Propietario.findByUsuarioPassword", query="SELECT p FROM Propietario p where p.usuario= :usuario and p.numerosecreto= :password")
})


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
	private List<Cuentasbancaria> cuentasbancarias;

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

	public List<Cuentasbancaria> getCuentasbancarias() {
		return this.cuentasbancarias;
	}

	public void setCuentasbancarias(List<Cuentasbancaria> cuentasbancarias) {
		this.cuentasbancarias = cuentasbancarias;
	}

	public Cuentasbancaria addCuentasbancaria(Cuentasbancaria cuentasbancaria) {
		getCuentasbancarias().add(cuentasbancaria);
		cuentasbancaria.setPropietario(this);

		return cuentasbancaria;
	}

	public Cuentasbancaria removeCuentasbancaria(Cuentasbancaria cuentasbancaria) {
		getCuentasbancarias().remove(cuentasbancaria);
		cuentasbancaria.setPropietario(null);

		return cuentasbancaria;
	}

}