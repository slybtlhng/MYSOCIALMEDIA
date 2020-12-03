/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.entity;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "tblArticle", catalog = "MYSOCIALMEDIA", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblArticle.findAll", query = "SELECT t FROM TblArticle t")
    , @NamedQuery(name = "TblArticle.findByArticleNo", query = "SELECT t FROM TblArticle t WHERE t.articleNo = :articleNo")
    , @NamedQuery(name = "TblArticle.findByArticleID", query = "SELECT t FROM TblArticle t WHERE t.articleID = :articleID AND t.articleStatus =:articleStatus")
    , @NamedQuery(name = "TblArticle.findByArticleTitle", query = "SELECT t FROM TblArticle t WHERE t.articleTitle = :articleTitle")
    , @NamedQuery(name = "TblArticle.findByArticleDescription", query = "SELECT t FROM TblArticle t WHERE t.articleDescription = :articleDescription")
    , @NamedQuery(name = "TblArticle.findByArticleImage", query = "SELECT t FROM TblArticle t WHERE t.articleImage = :articleImage")
    , @NamedQuery(name = "TblArticle.findByArticleDate", query = "SELECT t FROM TblArticle t WHERE t.articleDate = :articleDate")
    , @NamedQuery(name = "TblArticle.findByArticleNumOfLike", query = "SELECT t FROM TblArticle t WHERE t.articleNumOfLike = :articleNumOfLike")
    , @NamedQuery(name = "TblArticle.findByArticleNumOfDislike", query = "SELECT t FROM TblArticle t WHERE t.articleNumOfDislike = :articleNumOfDislike")
    , @NamedQuery(name = "TblArticle.findByArticleStatus", query = "SELECT t FROM TblArticle t WHERE t.articleStatus = :articleStatus")
    , @NamedQuery(name="TblArticle.findByLikeContent",query="Select t From TblArticle t Where t.articleDescription LIKE :articleDescription AND t.articleStatus =:articleStatus ORDER BY t.articleDate DESC ")})

public class TblArticle implements Serializable ,Comparable<TblArticle>{

    @OneToMany(mappedBy = "articleID")
    private Collection<TblNotification> tblNotificationCollection;

    @OneToMany(mappedBy = "emotionOnPost")
    private Collection<TblEmotion> tblEmotionCollection;

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "articleNo", nullable = false)
    private int articleNo;
    @Id
    @Basic(optional = false)
    @Column(name = "articleID", nullable = false, length = 10)
    private String articleID;
    @Basic(optional = false)
    @Column(name = "articleTitle", nullable = false, length = 50)
    private String articleTitle;
    @Basic(optional = false)
    @Column(name = "articleDescription", nullable = false, length = 2147483647)
    private String articleDescription;
    @Basic(optional = false)
    @Column(name = "articleImage", nullable = false, length = 2147483647)
    private String articleImage;
    @Basic(optional = false)
    @Column(name = "articleDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date articleDate;
    @Basic(optional = false)
    @Column(name = "articleNumOfLike", nullable = false)
    private int articleNumOfLike;
    @Basic(optional = false)
    @Column(name = "articleNumOfDislike", nullable = false)
    private int articleNumOfDislike;
    @Basic(optional = false)
    @Column(name = "articleStatus", nullable = false, length = 10)
    private String articleStatus;
    @JoinColumn(name = "articlePostBy", referencedColumnName = "userEmail")
    @ManyToOne
    private TblUser articlePostBy;
    @OneToMany(mappedBy = "commentOnPost")
    private Collection<TblComment> tblCommentCollection;

    public TblArticle() {
    }

    public TblArticle(String articleID) {
        this.articleID = articleID;
    }

    public TblArticle(String articleID, int articleNo, String articleTitle, String articleDescription, String articleImage, Date articleDate, int articleNumOfLike, int articleNumOfDislike, String articleStatus) {
        this.articleID = articleID;
        this.articleNo = articleNo;
        this.articleTitle = articleTitle;
        this.articleDescription = articleDescription;
        this.articleImage = articleImage;
        this.articleDate = articleDate;
        this.articleNumOfLike = articleNumOfLike;
        this.articleNumOfDislike = articleNumOfDislike;
        this.articleStatus = articleStatus;
    }

    public TblArticle(String articleTitle, String articleDescription, String articleImage, Date articleDate, int articleNumOfLike, int articleNumOfDislike, String articleStatus, TblUser articlePostBy) {
        this.articleTitle = articleTitle;
        this.articleDescription = articleDescription;
        this.articleImage = articleImage;
        this.articleDate = articleDate;
        this.articleNumOfLike = articleNumOfLike;
        this.articleNumOfDislike = articleNumOfDislike;
        this.articleStatus = articleStatus;
        this.articlePostBy = articlePostBy;
    }


    public int getArticleNo() {
        return articleNo;
    }

    public void setArticleNo(int articleNo) {
        this.articleNo = articleNo;
    }

    public String getArticleID() {
        return articleID;
    }

    public void setArticleID(String articleID) {
        this.articleID = articleID;
    }

    public String getArticleTitle() {
        return articleTitle;
    }

    public void setArticleTitle(String articleTitle) {
        this.articleTitle = articleTitle;
    }

    public String getArticleDescription() {
        return articleDescription;
    }

    public void setArticleDescription(String articleDescription) {
        this.articleDescription = articleDescription;
    }

    public String getArticleImage() {
        return articleImage;
    }

    public void setArticleImage(String articleImage) {
        this.articleImage = articleImage;
    }

    public Date getArticleDate() {
        return articleDate;
    }

    public void setArticleDate(Date articleDate) {
        this.articleDate = articleDate;
    }

    public int getArticleNumOfLike() {
        return articleNumOfLike;
    }

    public void setArticleNumOfLike(int articleNumOfLike) {
        this.articleNumOfLike = articleNumOfLike;
    }

    public int getArticleNumOfDislike() {
        return articleNumOfDislike;
    }

    public void setArticleNumOfDislike(int articleNumOfDislike) {
        this.articleNumOfDislike = articleNumOfDislike;
    }

    public String getArticleStatus() {
        return articleStatus;
    }

    public void setArticleStatus(String articleStatus) {
        this.articleStatus = articleStatus;
    }

    public TblUser getArticlePostBy() {
        return articlePostBy;
    }

    public void setArticlePostBy(TblUser articlePostBy) {
        this.articlePostBy = articlePostBy;
    }

    @XmlTransient
    public Collection<TblComment> getTblCommentCollection() {
        return tblCommentCollection;
    }

    public void setTblCommentCollection(Collection<TblComment> tblCommentCollection) {
        this.tblCommentCollection = tblCommentCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (articleID != null ? articleID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblArticle)) {
            return false;
        }
        TblArticle other = (TblArticle) object;
        if ((this.articleID == null && other.articleID != null) || (this.articleID != null && !this.articleID.equals(other.articleID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hangntk.entity.TblArticle[ articleID=" + articleID + " ]";
    }

    @Override
    public int compareTo(TblArticle t) {
         TblArticle tmp = t;
        if (this.articleDate.compareTo(tmp.articleDate) < 0) {
            return 1; //hoan vi
        } else if (this.articleDate.compareTo(tmp.articleDate) > 0) {
            return -1;//khong thay doi
        }
        return 0;
    }

    @XmlTransient
    public Collection<TblEmotion> getTblEmotionCollection() {
        return tblEmotionCollection;
    }

    public void setTblEmotionCollection(Collection<TblEmotion> tblEmotionCollection) {
        this.tblEmotionCollection = tblEmotionCollection;
    }

    @XmlTransient
    public Collection<TblNotification> getTblNotificationCollection() {
        return tblNotificationCollection;
    }

    public void setTblNotificationCollection(Collection<TblNotification> tblNotificationCollection) {
        this.tblNotificationCollection = tblNotificationCollection;
    }
    
}
