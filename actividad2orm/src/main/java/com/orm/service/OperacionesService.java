package com.orm.service;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.hibernate.Session;


import com.orm.entity.CuentaBancaria;
import com.orm.entity.Operacion;

public class OperacionesService {

	
	private Session session;

	public OperacionesService(Session session) {
		this.session = session;
	}

	public void insertarOperacion(Operacion operacion) {
		
		session.beginTransaction();
		session.save(operacion);
		session.getTransaction().commit();

		
	}

	public void modificarOperaciones(Operacion operaciones) {
		session.beginTransaction();
		session.update(operaciones);
		session.getTransaction().commit();
	}

	public ResultSet listarOperacionesRojo(String nroCuenta)  {
		String query = "SELECT o.* FROM operaciones o, cuentasbancarias c where o.saldoactualizado <0 and o.id_cuentabancaria = c.id and c.numcuenta =" + nroCuenta;
		
		ResultSet rs = null;
		return rs;
	}
	
	public ResultSet listarCuentasEnRojo()  {
		String query = "SELECT c.* ,o.saldoactualizado FROM operaciones o , cuentasbancarias c  where o.id_cuentabancaria = c.id " + 
				"and o.saldoactualizado <0";
		
		ResultSet rs = null;
		return rs;
	}

	public ResultSet listarOperaciones(String nroCuenta)  {
		String query = "SELECT o.* FROM operaciones o, cuentasbancarias c where o.id_cuentabancaria = c.id and c.numcuenta =" + nroCuenta;
		
		ResultSet rs = null;
		return rs;
	}

	public void eliminarOperaciones(Operacion operaciones)  {
		String query = "delete from operaciones where num_cuenta=? " + operaciones.getCuentasbancaria().getNumcuenta();
		
	}

	@SuppressWarnings("unused")
	// Consulta de operaciones por id
	private Operacion consultarOperacionByIdOperacion(Operacion operaciones)  {

		String query = "select *from operaciones where id=?" + operaciones.getId();
		
		Operacion op = null;
		return op;
	}

	private Operacion consultarOperacionByIdCuenta(Operacion operaciones)  {

		String query = "select *from operaciones where id_cuentabancaria=?" + operaciones.getCuentasbancaria().getId();
		
		Operacion op = null;
		return op;
	}

	// Consulta de todas las operaciones:

	private List<Operacion> consultarListaoperaciones()  {

		String query = "select * from operaciones ";
		

		List<Operacion> operaciones = null;
		return operaciones;

	}

	private Operacion recupearOperacionPrevia(int idCuenta)  {
		String query = "SELECT * FROM operaciones where id_cuentabancaria =" + idCuenta + " and "
				+ "fechahora = (SELECT MAX(fechahora) FROM operaciones where id_cuentabancaria = " + idCuenta + ")";
		
		Operacion op = null;
		return op;
	}

	private CuentaBancaria recuperarCuenta(int idCuenta)  {
		CuentaBancariaService ctaservice = new CuentaBancariaService(session);
		CuentaBancaria ctabancaria = ctaservice.consultarCuenta(idCuenta);
		return ctabancaria;
	}

	private Double obtenerSaldoActualizado(Operacion opPrevia, int cuenta, String tipo, Double cantidad)
			 {
		Double saldoActual = 0.0;
		if (opPrevia.getId() != 0) {
			Double saldoPrevio = opPrevia.getSaldoactualizado();
			saldoActual = saldoPrevio + cantidad;

		} else {
			CuentaBancaria cta = recuperarCuenta(cuenta);
			Double saldoInicial = cta.getSaldo();
			saldoActual = saldoInicial + cantidad;

		}
		return saldoActual;

	}
}
