package com.actividad5.servlet.service;

import java.util.ArrayList;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.TypedQuery;
import org.hibernate.Transaction;
import com.activida5.servlet.domain.Cuentasbancaria;

@Stateless
public class CuentasBancariasService extends BaseHibernate {
	
	public void save(Cuentasbancaria cta) {
		super.save(cta);
	}
	public void delete(Cuentasbancaria cta) {
		super.delete(cta);
		
	}
	
	
	
	
	public List<Cuentasbancaria> getAll(){
		 List<Cuentasbancaria> list = new ArrayList<Cuentasbancaria>();
		 Transaction tx = this.session.getTransaction();
		 try {
			 tx.begin();
			 TypedQuery<Cuentasbancaria> query = session.createNamedQuery("Cuentasbancaria.findAll");
			 list = query.getResultList();
			 tx.commit();	
		} catch (Exception e) {
			if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
		}
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public Cuentasbancaria getbyNumCuenta(String numcuenta){
		 Cuentasbancaria  cta = null;
		 Transaction tx = this.session.getTransaction();
		 try {
			 tx.begin();
			 TypedQuery<Cuentasbancaria> query = session.createNamedQuery("Cuentasbancaria.findByNumCuenta");
			 query.setParameter("cuenta", numcuenta);
			 cta = query.getSingleResult();
			 tx.commit();	
		} catch (Exception e) {
			if (tx != null) {
               tx.rollback();
           }
           e.printStackTrace();
		}
		return cta;
	}
	

}
