/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.blos;

import hangntk.entity.TblNotification;
import hangntk.entity.TblUser;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
public class NotificationBLO implements Serializable {

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

    public boolean insert(String articleID, String userID, Date notificationDate, String notificationContent, String notificationUserID) {
        EntityManager em = emf.createEntityManager();
        String sql = "INSERT INTO TblNotification (articleID,userID,notificationDate,notificationContent,notificationUserID) "
                + "VALUES(?,?,?,?,?)";
        Query query = em.createNativeQuery(sql);
        em.getTransaction().begin();
        query.setParameter("1", articleID);
        query.setParameter("2", userID);
        query.setParameter("3", notificationDate);
        query.setParameter("4", notificationContent);
        query.setParameter("5", notificationUserID);
        boolean check = query.executeUpdate() > 0;
        em.getTransaction().commit();
        return check;
    }

    public List<TblNotification> getNotiByUserEmail(String userEmail) {
        EntityManager em = emf.createEntityManager();
        TblUser user = em.find(TblUser.class, userEmail);
        List<TblNotification> result = null;
        if (user != null) {
            String jpql = "Select n from TblNotification n "
                    + "Where n.notificationUserID= :notificationUserID ORDER BY n.notificationDate DESC";
            Query query = em.createQuery(jpql);
            query.setParameter("notificationUserID", user);
            result = query.getResultList();
        }
        return result;
    }
}
