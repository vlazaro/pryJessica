package com.orm.service;

import java.sql.ResultSet;
import java.util.List;
import org.hibernate.Session;
import com.orm.entity.CuentasBancarias;

public class CuentaBancariaService {
	private Session session;
	public CuentaBancariaService(Session session) {

		this.session = session;
	}

	public void insertarCuenta(CuentasBancarias cuenta) {

		session.beginTransaction();
		session.save(cuenta);
		session.getTransaction().commit();
		
	}

	public void modificarCuenta(CuentasBancarias cuenta) {
		session.beginTransaction();
		session.update(cuenta);
		session.getTransaction().commit();
	}

	public void eliminarCuenta(CuentasBancarias cuenta) {
		session.beginTransaction();
		session.delete(cuenta);
		session.getTransaction().commit();
		

	}

	@SuppressWarnings("unchecked")
	public List<CuentasBancarias> consultarCuentas(CuentasBancarias numcuenta) {

		List<CuentasBancarias> result = (List<CuentasBancarias>)session.createQuery(" from cuentasbancarias ").list();
		
		return result;

	}

	public CuentasBancarias consultarCuenta(String numcuenta) {
		String query = "Select * from cuentasbancarias where numcuenta = '" + numcuenta + "'";

		CuentasBancarias cuenta = null;
		return cuenta;

	}

	@SuppressWarnings("unchecked")
	public ResultSet consultarCuentaResultSet(String numcuenta) {
		String query = "Select * from cuentasbancarias where numcuenta = '" + numcuenta + "'";
		
		session.beginTransaction();
		List<CuentasBancarias> result = (List<CuentasBancarias>)session.createQuery(query).list();
		ResultSet rs = (ResultSet) result;
		session.getTransaction().commit();
		return rs;

	}

	@SuppressWarnings("unchecked")
	public CuentasBancarias consultarCuenta(int idCuenta) {
		String query = "Select * from cuentasbancarias where id= " + idCuenta;
		session.beginTransaction();
		List<CuentasBancarias> result = (List<CuentasBancarias>)session.createQuery(query).list();
		session.getTransaction().commit();
		CuentasBancarias cuenta = result.get(0);
		return cuenta;

	}

}
