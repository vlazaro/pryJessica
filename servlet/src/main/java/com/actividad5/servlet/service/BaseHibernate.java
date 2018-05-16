package com.actividad5.servlet.service;

import org.hibernate.Session;
import org.hibernate.Transaction;

import com.actividad5.util.Config;

public class BaseHibernate {
	public Session session;

	public BaseHibernate() {
		session = Config.openSession();

	}

	public void save(Object o) {
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			session.saveOrUpdate(o);
			;
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();

		}

	}

	public void delete(Object o) {
		Transaction tx = session.getTransaction();
		try {
			tx.begin();
			session.delete(o);
			tx.commit();
		} catch (Exception e) {
			if (tx != null) {
				tx.rollback();
			}
			e.printStackTrace();

		}

	}
	
	
}
