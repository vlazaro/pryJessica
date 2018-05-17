package com.actividad5.servlet.service;

import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import org.hibernate.Transaction;
import com.activida5.servlet.domain.Operacione;

@Stateless
public class OperacionesService extends BaseHibernate {
	public void save(Operacione prop) {
		super.save(prop);
	}
	public void delete(Operacione prop) {
		super.delete(prop);
		
	}
	
	public List<Operacione> findbyCuenta(String numcuenta) {
		Operacione  prop = null;
		List<Operacione> operaciones = null;
		 Transaction tx = this.session.getTransaction();
		 try {
			 tx.begin();
			 TypedQuery<Operacione> query = session.createNamedQuery("Operacione.findByNumCuenta");
			 query.setParameter("numcuenta", numcuenta);
			 operaciones = query.getResultList();
			 tx.commit();	
		} catch (Exception e) {
			if (tx != null) {
             tx.rollback();
         }
         e.printStackTrace();
		}
		return operaciones;
		
	}
	public List<Operacione> findbySaldoRojo() {
		Operacione  prop = null;
		List<Operacione> operaciones = null;
		 Transaction tx = this.session.getTransaction();
		 try {
			 tx.begin();
			 TypedQuery<Operacione> query = session.createNamedQuery("Operacione.findBySaldoRojo");
			 operaciones = query.getResultList();
			 tx.commit();	
		} catch (Exception e) {
			if (tx != null) {
             tx.rollback();
         }
         e.printStackTrace();
		}
		return operaciones;
		
	}
	
	
	
	

}
