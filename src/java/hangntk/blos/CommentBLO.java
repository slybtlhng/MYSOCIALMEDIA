/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.blos;

import hangntk.entity.TblArticle;
import hangntk.entity.TblComment;
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
public class CommentBLO implements Serializable {

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

    public List<TblComment> searchCommentByPost(TblArticle article) {
        EntityManager em = emf.createEntityManager();
        String jpql = "TblComment.findByArticle";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("commentOnPost", article);
        query.setParameter("commentStatus", "Active");
        List<TblComment> result = query.getResultList();
        return result;
    }

    public boolean insertComment(String postBy, String postOn, String content, Date date) {
        EntityManager em = emf.createEntityManager();
        String sql = "INSERT INTO TblComment (commentPostBy,commentOnPost,commentContent,commentDate) "
                + "VALUES(?,?,?,?)";
        Query query = em.createNativeQuery(sql);
        em.getTransaction().begin();
        query.setParameter("1", postBy);
        query.setParameter("2", postOn);
        query.setParameter("3", content);
        query.setParameter("4", date);
        boolean check = query.executeUpdate() > 0;
        em.getTransaction().commit();
        return check;
    }

    //update status ="Delete"
    public boolean updateStatusDelete(String commentID) {
        EntityManager em = emf.createEntityManager();

        TblComment comment = em.find(TblComment.class, commentID);
        if (comment != null) {
            comment.setCommentStatus("Delete");
            em.getTransaction().begin();
            em.merge(comment);
            em.getTransaction().commit();
            return true;
        }
        return false;
    }
}
