/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "tblEmotion", catalog = "MYSOCIALMEDIA", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblEmotion.findAll", query = "SELECT t FROM TblEmotion t")
    , @NamedQuery(name = "TblEmotion.findByEmotionNo", query = "SELECT t FROM TblEmotion t WHERE t.emotionNo = :emotionNo")
    , @NamedQuery(name = "TblEmotion.findByEmotionID", query = "SELECT t FROM TblEmotion t WHERE t.emotionID = :emotionID")
    , @NamedQuery(name = "TblEmotion.findByEmotionContent", query = "SELECT t FROM TblEmotion t WHERE t.emotionContent = :emotionContent")})
public class TblEmotion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "emotionNo", nullable = false)
    private int emotionNo;
    @Id
    @Basic(optional = false)
    @Column(name = "emotionID", nullable = false, length = 10)
    private String emotionID;
    @Basic(optional = false)
    @Column(name = "emotionContent", nullable = false, length = 10)
    private String emotionContent;
    @JoinColumn(name = "emotionOnPost", referencedColumnName = "articleID")
    @ManyToOne
    private TblArticle emotionOnPost;
    @JoinColumn(name = "emotionBy", referencedColumnName = "userEmail")
    @ManyToOne
    private TblUser emotionBy;

    public TblEmotion() {
    }

    public TblEmotion(String emotionID) {
        this.emotionID = emotionID;
    }

    public TblEmotion(String emotionID, int emotionNo, String emotionContent) {
        this.emotionID = emotionID;
        this.emotionNo = emotionNo;
        this.emotionContent = emotionContent;
    }

    public TblEmotion(String emotionContent, TblArticle emotionOnPost, TblUser emotionBy) {
        this.emotionContent = emotionContent;
        this.emotionOnPost = emotionOnPost;
        this.emotionBy = emotionBy;
    }

    public int getEmotionNo() {
        return emotionNo;
    }

    public void setEmotionNo(int emotionNo) {
        this.emotionNo = emotionNo;
    }

    public String getEmotionID() {
        return emotionID;
    }

    public void setEmotionID(String emotionID) {
        this.emotionID = emotionID;
    }

    public String getEmotionContent() {
        return emotionContent;
    }

    public void setEmotionContent(String emotionContent) {
        this.emotionContent = emotionContent;
    }

    public TblArticle getEmotionOnPost() {
        return emotionOnPost;
    }

    public void setEmotionOnPost(TblArticle emotionOnPost) {
        this.emotionOnPost = emotionOnPost;
    }

    public TblUser getEmotionBy() {
        return emotionBy;
    }

    public void setEmotionBy(TblUser emotionBy) {
        this.emotionBy = emotionBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (emotionID != null ? emotionID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblEmotion)) {
            return false;
        }
        TblEmotion other = (TblEmotion) object;
        if ((this.emotionID == null && other.emotionID != null) || (this.emotionID != null && !this.emotionID.equals(other.emotionID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hangntk.entity.TblEmotion[ emotionID=" + emotionID + " ]";
    }
    
}
