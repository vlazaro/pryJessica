package com.orm.service;

import java.sql.Connection;
import java.sql.ResultSet;
import java.util.List;
import org.hibernate.Session;
import com.orm.entity.CuentaBancaria;

public class CuentaBancariaService {
	private Session session;
	public CuentaBancariaService(Session session) {

		this.session = session;
	}

	public void insertarCuenta(CuentaBancaria cuenta) {

	}

	public void modificarCuenta(CuentaBancaria cuenta) {

	}

	public void eliminarCuenta(CuentaBancaria cuenta) {

	}

	public List<CuentaBancaria> consultarCuentas(CuentaBancaria numcuenta) {

		List<CuentaBancaria> cuentas = null;
		return cuentas;

	}

	public CuentaBancaria consultarCuenta(String numcuenta) {
		String query = "Select * from cuentasbancarias where numcuenta = '" + numcuenta + "'";

		CuentaBancaria cuenta = null;
		return cuenta;

	}

	public ResultSet consultarCuentaResultSet(String numcuenta) {
		String query = "Select * from cuentasbancarias where numcuenta = '" + numcuenta + "'";
		
		ResultSet rs = null;
		return rs;

	}

	public CuentaBancaria consultarCuenta(int idCuenta) {
		String query = "Select * from cuentasbancarias where id= " + idCuenta;

		CuentaBancaria cuenta = null;
		return cuenta;

	}

}
