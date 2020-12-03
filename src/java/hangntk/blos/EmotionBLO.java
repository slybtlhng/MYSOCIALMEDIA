/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.blos;

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
public class EmotionBLO implements Serializable {

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

    public boolean changeEmotion(String emotionBy,String emotionOnPost,String emotionContent) {
        EntityManager em = emf.createEntityManager();
        String sql = "IF EXISTS (SELECT emotionID FROM tblEmotion WHERE emotionBy like ? And emotionOnPost like ? ) \n"
                + "   UPDATE tblEmotion SET emotionContent=? WHERE emotionBy like ? And emotionOnPost like ? \n"
                + "ELSE\n"
                + "   INSERT INTO tblEmotion(emotionBy,emotionOnPost,emotionContent) VALUES(?,?,?)\n";
        Query query=em.createNativeQuery(sql);
        em.getTransaction().begin();
        query.setParameter("1", emotionBy);
        query.setParameter("2", emotionOnPost);
        query.setParameter("3", emotionContent);
        query.setParameter("4", emotionBy);
        query.setParameter("5", emotionOnPost);
        query.setParameter("6", emotionBy);
        query.setParameter("7", emotionOnPost);
        query.setParameter("8", emotionContent);
        boolean check = query.executeUpdate() > 0;
        em.getTransaction().commit();
        return check;
    }
}
