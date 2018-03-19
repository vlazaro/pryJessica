package com.orm.service;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;

import com.orm.entity.CuentasBancarias;
import com.orm.entity.Historial;
import com.orm.entity.Operaciones;
import com.orm.entity.Propietario;

public class HistorialService {
	Session session = null;

	public HistorialService(Session session) {
		this.session = session;
	}

	public void insertarHistorialOperacion(Operaciones op) {

		Historial historial = new Historial();
		historial.setTipoevento(op.getTipo());
		Timestamp timestamp = new Timestamp(new Date().getTime());
		historial.setFechahoraevento(timestamp);
		historial.setIdCuentabancaria(op.getCuentasbancaria().getNumcuenta());
		
		session.beginTransaction();
		session.save(historial);
		session.getTransaction().commit();
		
	}

	public void insertarHistorialLogin(Propietario prop) throws SQLException {

		Historial historial = new Historial();
		historial.setTipoevento("L");
		Timestamp timestamp = new Timestamp(new Date().getTime());
		historial.setFechahoraevento(timestamp);
		historial.setDniPropietario(prop.getDni());
		
		session.beginTransaction();
		session.save(historial);
		session.getTransaction().commit();
				
		
		
		

	}

	@SuppressWarnings("unchecked")
	public ResultSet consultaFechaHoraUltimoInicioSesio(String dniPropietario) throws SQLException {
		String query = "SELECT * FROM HISTORIAL WHERE dni_propietario ='" + dniPropietario
				+ "'  and fechahoraevento = (select max(fechahoraevento)  from historial " + "where dni_propietario ='"
				+ dniPropietario + "')";

		session.beginTransaction();
		List<CuentasBancarias> result = (List<CuentasBancarias>)session.createQuery(query).list();
		session.beginTransaction();
		ResultSet rs = (ResultSet) result;
		return rs;
	}

}
