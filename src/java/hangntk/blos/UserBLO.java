/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.blos;

import hangntk.entity.TblUser;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author ASUS
 */
public class UserBLO implements Serializable {

    EntityManagerFactory emf = Persistence.createEntityManagerFactory("MYSOCIALMEDIAPU");

    public void persist(Object object) {
        EntityManager em = emf.createEntityManager();
        try {
            em.getTransaction().begin();
            em.persist(object);
            em.getTransaction().commit();
        } catch (Exception e) {
            Logger.getLogger(getClass().getName()).log(Level.SEVERE, "exception caught", e);
            em.getTransaction().rollback();
        } finally {
            em.close();
        }
    }

    //ham nay tra ve entity tblUser
    public TblUser checkLogin(String email, String password) throws Exception {
        TblUser user;
        EntityManager em = emf.createEntityManager();

        String jpql = "Select u From TblUser u "
                + "Where u.userEmail= :email And u.userPass =:password";
        Query query = em.createQuery(jpql);
        query.setParameter("email", email);
        query.setParameter("password", password);

        user = (TblUser) query.getSingleResult();
        return user;
    }

    public boolean regist(TblUser u) {
        boolean result = false;
        EntityManager em = emf.createEntityManager();
        TblUser user = em.find(TblUser.class, u.getUserEmail());
        if (user == null) {
            em.getTransaction().begin();
            em.persist(u);
            em.getTransaction().commit();
            result = true;
        }
        return result;
    }

    public TblUser findByID(String email) {
        TblUser user;
        EntityManager em = emf.createEntityManager();
        String jpql = "TblUser.findByUserEmail";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("userEmail", email);
        try {
            user = (TblUser) query.getSingleResult();
        } catch (Exception e) {
            return null;
        }
        return user;
    }
}
