package com.orm.service;

import org.hibernate.Session;
import org.hibernate.transform.AliasToEntityMapResultTransformer;

import com.orm.configuracion.AppConfig;
import com.orm.entity.Propietario;
import org.hibernate.Transaction;
import org.hibernate.Query;

public class PropietariosService {

	private  static Session session;
	public PropietariosService(Session session) {
		this.session = session;
		
	}
	public void insertarPropietario(Propietario prop) {
		session.beginTransaction();
		session.save(prop);
		session.getTransaction().commit();

	}
	
	public void modificarPropietario(Propietario propietario) {
		session.beginTransaction();
		session.update(propietario);
		session.getTransaction().commit();
		
	}
	public void eliminarPropietario(Propietario prop) {
		session.beginTransaction();
		session.delete(prop);
		session.getTransaction().commit();
		
	}
	
	public Propietario consultarPropietarioByDni(Propietario prop) {
		Transaction tx=null;
		tx=session.beginTransaction(); 
		Propietario propietario = (Propietario) session.get(Propietario.class,prop.getDni());
		tx.commit(); 
        
		return propietario;
		
	}
	public Propietario consultarPropietarioByUsuarioPassword(Propietario prop) {
		String query= "Select p from Propietario p where usuario ='"+ prop.getUsuario()+ "' and numerosecreto='" + prop.getNumerosecreto() + "'";
		Propietario propietario = (Propietario) session.createQuery(query);
		return propietario;
		
	}
	
	public boolean validarUsuarioPassword(String usuario,String password) {
		String query= "select p from Propietario p where usuario = '" + usuario + "' and numerosecreto = '" +  password + "'";
		Propietario propietario = (Propietario) session.createQuery(query).uniqueResult();
		if (propietario.getDni().isEmpty()) {
			return false;
		}
		return true;
		
		
	}
	
}
