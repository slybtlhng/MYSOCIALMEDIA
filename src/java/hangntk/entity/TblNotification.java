/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "tblNotification", catalog = "MYSOCIALMEDIA", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblNotification.findAll", query = "SELECT t FROM TblNotification t")
    , @NamedQuery(name = "TblNotification.findByNotificationNo", query = "SELECT t FROM TblNotification t WHERE t.notificationNo = :notificationNo")
    , @NamedQuery(name = "TblNotification.findByNotificationID", query = "SELECT t FROM TblNotification t WHERE t.notificationID = :notificationID")
    , @NamedQuery(name = "TblNotification.findByNotificationContent", query = "SELECT t FROM TblNotification t WHERE t.notificationContent = :notificationContent")
    , @NamedQuery(name = "TblNotification.findByNotificationDate", query = "SELECT t FROM TblNotification t WHERE t.notificationDate = :notificationDate")})
public class TblNotification implements Serializable {

    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "notificationNo", nullable = false)
    private int notificationNo;
    @Id
    @Basic(optional = false)
    @Column(name = "notificationID", nullable = false, length = 10)
    private String notificationID;
    @Basic(optional = false)
    @Column(name = "notificationContent", nullable = false, length = 2147483647)
    private String notificationContent;
    @Basic(optional = false)
    @Column(name = "notificationDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date notificationDate;
    @JoinColumn(name = "articleID", referencedColumnName = "articleID")
    @ManyToOne
    private TblArticle articleID;
    @JoinColumn(name = "userID", referencedColumnName = "userEmail")
    @ManyToOne
    private TblUser userID;
    @JoinColumn(name = "notificationUserID", referencedColumnName = "userEmail")
    @ManyToOne
    private TblUser notificationUserID;

    public TblNotification() {
    }

    public TblNotification(String notificationID) {
        this.notificationID = notificationID;
    }

    public TblNotification(String notificationID, int notificationNo, String notificationContent, Date notificationDate) {
        this.notificationID = notificationID;
        this.notificationNo = notificationNo;
        this.notificationContent = notificationContent;
        this.notificationDate = notificationDate;
    }

    public int getNotificationNo() {
        return notificationNo;
    }

    public void setNotificationNo(int notificationNo) {
        this.notificationNo = notificationNo;
    }

    public String getNotificationID() {
        return notificationID;
    }

    public void setNotificationID(String notificationID) {
        this.notificationID = notificationID;
    }

    public String getNotificationContent() {
        return notificationContent;
    }

    public void setNotificationContent(String notificationContent) {
        this.notificationContent = notificationContent;
    }

    public Date getNotificationDate() {
        return notificationDate;
    }

    public void setNotificationDate(Date notificationDate) {
        this.notificationDate = notificationDate;
    }

    public TblArticle getArticleID() {
        return articleID;
    }

    public void setArticleID(TblArticle articleID) {
        this.articleID = articleID;
    }

    public TblUser getUserID() {
        return userID;
    }

    public void setUserID(TblUser userID) {
        this.userID = userID;
    }

    public TblUser getNotificationUserID() {
        return notificationUserID;
    }

    public void setNotificationUserID(TblUser notificationUserID) {
        this.notificationUserID = notificationUserID;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (notificationID != null ? notificationID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblNotification)) {
            return false;
        }
        TblNotification other = (TblNotification) object;
        if ((this.notificationID == null && other.notificationID != null) || (this.notificationID != null && !this.notificationID.equals(other.notificationID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hangntk.entity.TblNotification[ notificationID=" + notificationID + " ]";
    }
    
}
