package com.actividad5.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;

import com.activida5.servlet.domain.Cuentasbancaria;
import com.activida5.servlet.domain.Historial;
import com.activida5.servlet.domain.Operacione;
import com.activida5.servlet.domain.Propietario;

public class Config {

	private static SessionFactory sessionFactory;

    static {
        try {
         //   sessionFactory = new AnnotationConfiguration().configure("hibernate.cfg.xml").buildSessionFactory();
           // sessionFactory = new Configuration().configure().buildSessionFactory();
        	sessionFactory = createSessionFactory("");
            
        } catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    private static SessionFactory createSessionFactory(String options) {
        Configuration configuration = new Configuration();
        configuration.addAnnotatedClass(Cuentasbancaria.class);
        configuration.addAnnotatedClass(Historial.class);
        configuration.addAnnotatedClass(Operacione.class);
        configuration.addAnnotatedClass(Propietario.class);
        
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/operacionesbancarias?zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&serverTimezone=UTC" + options);
        configuration.setProperty("hibernate.connection.username", "root");
        configuration.setProperty("hibernate.connection.password", "root");
        configuration.setProperty("hibernate.hbm2ddl.auto", "update");
        configuration.setProperty("hibernate.show_sql", "true");
        configuration.setProperty("hibernate.connection.pool_size", "10");

        StandardServiceRegistryBuilder builder = new StandardServiceRegistryBuilder()
            .applySettings(configuration.getProperties());
        SessionFactory sessionFactory = configuration.buildSessionFactory(builder.build());
        return sessionFactory;
      }
    

    public static Session openSession() {
        return sessionFactory.openSession();
    }
	
}
