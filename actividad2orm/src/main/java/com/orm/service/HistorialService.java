package com.orm.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;

import org.hibernate.Session;

import com.orm.entity.Operacion;
import com.orm.entity.Propietario;

public class HistorialService {
	Session session = null;

	public HistorialService(Session session) {
		this.session = session;
	}

	public void insertarHistorialOperacion(Operacion op) throws SQLException {

		String query = "INSERT INTO historial(tipoEvento,fechahoraevento,id_cuentabancaria) VALUES(?,?,?)";

	}

	public void insertarHistorialLogin(Propietario prop) throws SQLException {

		String query = "INSERT INTO historial(tipoEvento,fechahoraevento,dni_propietario) VALUES(?,?,?)";

		Timestamp timestamp = new Timestamp(new Date().getTime());

	}

	public ResultSet consultaFechaHoraUltimoInicioSesio(String dniPropietario) throws SQLException {
		String query = "SELECT * FROM HISTORIAL WHERE dni_propietario ='" + dniPropietario
				+ "'  and fechahoraevento = (select max(fechahoraevento)  from historial " + "where dni_propietario ='"
				+ dniPropietario + "')";

		ResultSet rs = null;
		return rs;

	}

}
