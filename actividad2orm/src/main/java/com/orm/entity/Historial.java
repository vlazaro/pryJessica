package com.orm.entity;

import java.io.Serializable;
import javax.persistence.*;
import java.util.Date;


/**
 * The persistent class for the historial database table.
 * 
 */
@Entity
@NamedQuery(name="Historial.findAll", query="SELECT h FROM Historial h")
public class Historial implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	private int id;

	@Column(name="dni_propietario")
	private String dniPropietario;

	@Temporal(TemporalType.TIMESTAMP)
	private Date fechahoraevento;

	@Column(name="id_cuentabancaria")
	private String idCuentabancaria;

	private String tipoevento;

	public Historial() {
	}

	public int getId() {
		return this.id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDniPropietario() {
		return this.dniPropietario;
	}

	public void setDniPropietario(String dniPropietario) {
		this.dniPropietario = dniPropietario;
	}

	public Date getFechahoraevento() {
		return this.fechahoraevento;
	}

	public void setFechahoraevento(Date fechahoraevento) {
		this.fechahoraevento = fechahoraevento;
	}

	public String getIdCuentabancaria() {
		return this.idCuentabancaria;
	}

	public void setIdCuentabancaria(String idCuentabancaria) {
		this.idCuentabancaria = idCuentabancaria;
	}

	public String getTipoevento() {
		return this.tipoevento;
	}

	public void setTipoevento(String tipoevento) {
		this.tipoevento = tipoevento;
	}

}