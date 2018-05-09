package com.actividad5.util;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
//import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.cfg.Configuration;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;

import com.actividad5.model.User;


public class HibernateUtil {

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
        configuration.addAnnotatedClass(User.class);
        configuration.setProperty("hibernate.dialect", "org.hibernate.dialect.MySQLDialect");
        configuration.setProperty("hibernate.connection.driver_class", "com.mysql.jdbc.Driver");
        configuration.setProperty("hibernate.connection.url", "jdbc:mysql://localhost:3306/test_db?zeroDateTimeBehavior=convertToNull&useLegacyDatetimeCode=false&serverTimezone=UTC" + options);
        configuration.setProperty("hibernate.connection.username", "be3aapi");
        configuration.setProperty("hibernate.connection.password", "be3aapi");
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