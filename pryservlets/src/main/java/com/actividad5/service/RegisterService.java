
package com.actividad5.service;

import com.actividad5.model.User;
import com.actividad5.util.HibernateUtil;

import javax.ejb.Stateless;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.Transaction;

@Stateless
public class RegisterService {
        
    public boolean register(User user){
        Session session = HibernateUtil.openSession();
        if(isUserExists(user)) return false;        
        
        Transaction tx = null;        
        try {
            tx = session.getTransaction();
            tx.begin();
            session.saveOrUpdate(user);            
            tx.commit();
        } catch (Exception e) {
            if (tx != null) {
                tx.rollback();
            }
            e.printStackTrace();
        } finally {
            session.close();
        }        
        return true;
    }
    @SuppressWarnings("unchecked")
    public boolean isUserExists(User user){
        Session session = HibernateUtil.openSession();
        boolean result = false;
        Transaction tx = null;
        try{
            tx = session.getTransaction();
            tx.begin();
			TypedQuery<User> query = session.createQuery("from User where userId='"+user.getUserId()+"'");
            User u = query.getSingleResult();
            tx.commit();
            if(u!=null) result = true;
        }catch(Exception ex){
            if(tx!=null){
                tx.rollback();
            }
        }finally{
            session.close();
        }
        return result;
    }
}
