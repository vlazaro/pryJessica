package com.orm.configuracion;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;

public class AppConfig {
	private  Session sessionObj;
	private  SessionFactory sessionFactoryObj;

	public SessionFactory buildSessionFactory() {
		Configuration configObj = new Configuration();
		configObj.configure("hibernate.cfg.xml");
		ServiceRegistry serviceRegistryObj = new StandardServiceRegistryBuilder()
				.applySettings(configObj.getProperties()).build();
			sessionFactoryObj = configObj.buildSessionFactory(serviceRegistryObj);
		return sessionFactoryObj;

	}
	
	
//	public static void main(String[] args) {
//		System.out.println(".......Hibernate Maven Example.......\n");
//		try {
//			sessionObj = buildSessionFactory().openSession();
//			sessionObj.beginTransaction();
//
//			for(int i = 101; i <= 105; i++) {
//				Propietario = new P;
//				userObj.setUserid(i);
//				userObj.setUsername("Editor " + i);
//				userObj.setCreatedBy("Administrator");
//				userObj.setCreatedDate(new Date());
//
//				sessionObj.save(userObj);
//			}
//			System.out.println("\n.......Records Saved Successfully To The Database.......\n");
//
//			// Committing The Transactions To The Database
//			sessionObj.getTransaction().commit();
//		} catch(Exception sqlException) {
//			if(null != sessionObj.getTransaction()) {
//				System.out.println("\n.......Transaction Is Being Rolled Back.......");
//				sessionObj.getTransaction().rollback();
//			}
//			sqlException.printStackTrace();
//		} finally {
//			if(sessionObj != null) {
//				sessionObj.close();
//			}
//		}
//	}
	
	
	

}
