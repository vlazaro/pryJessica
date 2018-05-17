package com.actividad5.servlet.service;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import org.hibernate.Transaction;

import com.activida5.servlet.domain.Cuentasbancaria;
import com.activida5.servlet.domain.Propietario;

@Stateless
public class PropietarioService extends BaseHibernate{
	public void save(Propietario prop) {
		super.save(prop);
	}
	public void delete(Propietario prop) {
		super.delete(prop);
	}
	public Propietario findbydni(String dni) {
		 Propietario  prop = null;
		 Transaction tx = this.session.getTransaction();
		 try {
			 tx.begin();
			 TypedQuery<Propietario> query = session.createNamedQuery("Propietario.findBydni");
			 query.setParameter("dni", dni);
			 prop = query.getSingleResult();
			 tx.commit();	
		} catch (Exception e) {
			if (tx != null) {
              tx.rollback();
          }
          e.printStackTrace();
		}
		return prop;
		
	}
	
	public Propietario findbyusuariopassword(String usuario,String password) {
		 Propietario  prop = null;
		 Transaction tx = this.session.getTransaction();
		 try {
			 tx.begin();
			 TypedQuery<Propietario> query = session.createNamedQuery("Propietario.findByUsuarioPassword");
			 query.setParameter("usuario", usuario);
			 query.setParameter("password", password);
			 prop = query.getSingleResult();
			 tx.commit();	
		} catch (Exception e) {
			if (tx != null) {
             tx.rollback();
         }
         e.printStackTrace();
		}
		return prop;
		
	}

}
