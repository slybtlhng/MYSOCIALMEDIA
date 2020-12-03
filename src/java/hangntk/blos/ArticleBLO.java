/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.blos;

import hangntk.entity.TblArticle;
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
import org.eclipse.persistence.config.HintValues;
import org.eclipse.persistence.config.QueryHints;

/**
 *
 * @author ASUS
 */
public class ArticleBLO implements Serializable {

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

    //ham nay tra ve list Search theo content theo trang dang nam o dau
    public List<TblArticle> searchLikeContent(String search, int positionPage, int maxRecord) {
        EntityManager em = emf.createEntityManager();
        String jpql = "TblArticle.findByLikeContent";
        Query query = em.createNamedQuery(jpql);
        query.setParameter("articleDescription", "%" + search + "%");
        query.setParameter("articleStatus", "Active");
        query.setFirstResult((positionPage - 1) * maxRecord);
        query.setMaxResults(maxRecord);
        List<TblArticle> result = query.getResultList();
        return result;
    }

    public boolean insertArticle(String articleTitle, String articleDescription, String articleImage, Date articleDate, String postBy) {
        EntityManager em = emf.createEntityManager();
        String sql = "INSERT INTO TblArticle (articleTitle,articleDescription,articleImage,articleDate,articlePostBy) "
                + "VALUES(?,?,?,?,?)";
        Query query = em.createNativeQuery(sql);
        em.getTransaction().begin();
        query.setParameter("1", articleTitle);
        query.setParameter("2", articleDescription);
        query.setParameter("3", articleImage);
        query.setParameter("4", articleDate);
        query.setParameter("5", postBy);
        boolean check = query.executeUpdate() > 0;
        em.getTransaction().commit();
        return check;
    }

    public List<TblArticle> searchAsPostBy(TblUser user) {
        EntityManager em = emf.createEntityManager();
        String jpql = "Select a from TblArticle a "
                + "Where a.articlePostBy= :articlePostBy and a.articleStatus =:articleStatus ORDER BY a.articleDate DESC";
        Query query = em.createQuery(jpql);
        query.setParameter("articlePostBy", user);
        query.setParameter("articleStatus", "Active");
        List<TblArticle> result = query.getResultList();
        return result;
    }

    public long totalArticleSearch(String search) {
        EntityManager em = emf.createEntityManager();
        Query query = em.createQuery("Select count(t.articleID) From TblArticle t Where t.articleDescription LIKE :articleDescription and t.articleStatus =:articleStatus");
        query.setParameter("articleDescription", "%" + search + "%");
        query.setParameter("articleStatus", "Active");
        long countResult = (long) query.getSingleResult();
        return countResult;
    }

    public TblArticle findByArticleID(String id) {
        EntityManager em = emf.createEntityManager();
        String jpql = "TblArticle.findByArticleID";
        Query query = em.createNamedQuery(jpql).setHint(QueryHints.REFRESH, HintValues.TRUE);
        query.setParameter("articleID", id);
        query.setParameter("articleStatus", "Active");
        TblArticle result = (TblArticle) query.getSingleResult();
        return result;
    }

    //update status ="Delete"
    public boolean updateStatusDelete(String articleID) {
        EntityManager em = emf.createEntityManager();

        TblArticle article = em.find(TblArticle.class, articleID);
        if (article != null) {
            article.setArticleStatus("Delete");
            em.getTransaction().begin();
            em.merge(article);
            em.getTransaction().commit();
            return true;
        }
        return false;
    }
}
