package com.orm.service;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;


import com.orm.entity.CuentasBancarias;
import com.orm.entity.Operaciones;

public class OperacionesService {

	
	private Session session;

	public OperacionesService(Session session) {
		this.session = session;
	}

	public void insertarOperacion(Operaciones operacion) {
		
			
			String tipo;
			if (operacion.getCantidad() >= 0) {
				tipo = "I";
			} else {
				tipo = "E";
			}
			Operaciones opPrevia = recupearOperacionPrevia(operacion.getCuentasbancaria().getId());
			Double saldoActual = obtenerSaldoActualizado(opPrevia, operacion.getCuentasbancaria().getId(), tipo,
					operacion.getCantidad());
			session.beginTransaction();
			operacion.setSaldoactualizado(saldoActual);
			operacion.setTipo(tipo);
			Timestamp timestamp = new Timestamp(new Date().getTime());
			operacion.setFechahora(timestamp);
			HistorialService historial = new HistorialService(session);
			historial.insertarHistorialOperacion(operacion);
			session.getTransaction().commit();

		
	}

	public void modificarOperaciones(Operaciones operaciones) {
		session.beginTransaction();
		session.update(operaciones);
		session.getTransaction().commit();
	}

	@SuppressWarnings("unchecked")
	public ResultSet listarOperacionesRojo(String nroCuenta)  {
		String query = "SELECT o.* FROM operaciones o, cuentasbancarias c where o.saldoactualizado <0 and o.id_cuentabancaria = c.id and c.numcuenta =" + nroCuenta;
		
		List<Operaciones> result = (List<Operaciones>)session.createQuery(query).list();
		ResultSet rs = (ResultSet) result;
		return rs;
	}
	
	@SuppressWarnings("unchecked")
	public ResultSet listarCuentasEnRojo()  {
		String query = "SELECT c.* ,o.saldoactualizado FROM operaciones o , cuentasbancarias c  where o.id_cuentabancaria = c.id " + 
				"and o.saldoactualizado <0";
		
		List<Operaciones> result = (List<Operaciones>)session.createQuery(query).list();
		ResultSet rs = (ResultSet) result;
		return rs;
	}

	@SuppressWarnings("unchecked")
	public ResultSet listarOperaciones(String nroCuenta)  {
		String query = "SELECT o.* FROM operaciones o, cuentasbancarias c where o.id_cuentabancaria = c.id and c.numcuenta =" + nroCuenta;
		List<Operaciones> result = (List<Operaciones>)session.createQuery(query).list();
		ResultSet rs = (ResultSet) result;
		return rs;
	}

	public void eliminarOperaciones(Operaciones operaciones)  {
		session.beginTransaction();
		session.save(operaciones);
		session.getTransaction().commit();
		
	}

	@SuppressWarnings("unused")
	// Consulta de operaciones por id
	private Operaciones consultarOperacionByIdOperacion(Operaciones operaciones)  {
		String query = "select *from operaciones where id=?" + operaciones.getId();
		List<Operaciones> result = (List<Operaciones>)session.createQuery(query).list();
		return result.get(0);
	}

	@SuppressWarnings("unchecked")
	private Operaciones consultarOperacionByIdCuenta(Operaciones operaciones)  {

		String query = "select *from operaciones where id_cuentabancaria=?" + operaciones.getCuentasbancaria().getId();
		List<Operaciones> result = (List<Operaciones>)session.createQuery(query).list();
		return result.get(0);
	}

	// Consulta de todas las operaciones:

	private List<Operaciones> consultarListaoperaciones()  {

		String query = "select * from operaciones ";
		session.beginTransaction();
		List<Operaciones> result = (List<Operaciones>)session.createQuery(query).list();
		session.getTransaction().commit();
		return result;

	}

	@SuppressWarnings("unchecked")
	private Operaciones recupearOperacionPrevia(int idCuenta)  {
		String query = "SELECT * FROM operaciones where id_cuentabancaria =" + idCuenta + " and "
				+ "fechahora = (SELECT MAX(fechahora) FROM operaciones where id_cuentabancaria = " + idCuenta + ")";
		session.beginTransaction();
		List<Operaciones> result = (List<Operaciones>)session.createQuery(query).list();
		session.getTransaction().commit();
		return result.get(0);
	}

	private CuentasBancarias recuperarCuenta(int idCuenta)  {
		CuentaBancariaService ctaservice = new CuentaBancariaService(session);
		CuentasBancarias ctabancaria = ctaservice.consultarCuenta(idCuenta);
		return ctabancaria;
	}

	private Double obtenerSaldoActualizado(Operaciones opPrevia, int cuenta, String tipo, Double cantidad)
			 {
		Double saldoActual = 0.0;
		if (opPrevia.getId() != 0) {
			Double saldoPrevio = opPrevia.getSaldoactualizado();
			saldoActual = saldoPrevio + cantidad;

		} else {
			CuentasBancarias cta = recuperarCuenta(cuenta);
			Double saldoInicial = cta.getSaldo();
			saldoActual = saldoInicial + cantidad;

		}
		return saldoActual;

	}
}
