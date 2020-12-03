/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hangntk.entity;

import java.io.Serializable;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.DatatypeConverter;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author ASUS
 */
@Entity
@Table(name = "tblUser", catalog = "MYSOCIALMEDIA", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblUser.findAll", query = "SELECT t FROM TblUser t")
    , @NamedQuery(name = "TblUser.findByUserEmail", query = "SELECT t FROM TblUser t WHERE t.userEmail = :userEmail")
    , @NamedQuery(name = "TblUser.findByUserRole", query = "SELECT t FROM TblUser t WHERE t.userRole = :userRole")
    , @NamedQuery(name = "TblUser.findByUserName", query = "SELECT t FROM TblUser t WHERE t.userName = :userName")
    , @NamedQuery(name = "TblUser.findByUserPass", query = "SELECT t FROM TblUser t WHERE t.userPass = :userPass")
    , @NamedQuery(name = "TblUser.findByUserStatus", query = "SELECT t FROM TblUser t WHERE t.userStatus = :userStatus")})
public class TblUser implements Serializable {

    @OneToMany(mappedBy = "emotionBy")
    private Collection<TblEmotion> tblEmotionCollection;

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "userEmail", nullable = false, length = 50)
    private String userEmail;
    @Basic(optional = false)
    @Column(name = "userRole", nullable = false, length = 100)
    private String userRole;
    @Basic(optional = false)
    @Column(name = "userName", nullable = false, length = 100)
    private String userName;
    @Basic(optional = false)
    @Column(name = "userPass", nullable = false, length = 100)
    private String userPass;
    @Basic(optional = false)
    @Column(name = "userStatus", nullable = false, length = 10)
    private String userStatus;
    @OneToMany(mappedBy = "articlePostBy")
    private Collection<TblArticle> tblArticleCollection;
    @OneToMany(mappedBy = "commentPostBy")
    private Collection<TblComment> tblCommentCollection;
    @OneToMany(mappedBy = "userID")
    private Collection<TblNotification> tblNotificationCollection;

    public TblUser() {
    }

    public TblUser(String userEmail) {
        this.userEmail = userEmail;
    }

    public TblUser(String userEmail, String userRole, String userName, String userPass, String userStatus) {
        this.userEmail = userEmail;
        this.userRole = userRole;
        this.userName = userName;
        this.userPass = userPass;
        this.userStatus = userStatus;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserRole() {
        return userRole;
    }

    public void setUserRole(String userRole) {
        this.userRole = userRole;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPass() {
        return userPass;
    }

    public void setUserPass(String userPass) {
        this.userPass = userPass;
    }

    public String getUserStatus() {
        return userStatus;
    }

    public void setUserStatus(String userStatus) {
        this.userStatus = userStatus;
    }

    @XmlTransient
    public Collection<TblArticle> getTblArticleCollection() {
        return tblArticleCollection;
    }

    public void setTblArticleCollection(Collection<TblArticle> tblArticleCollection) {
        this.tblArticleCollection = tblArticleCollection;
    }

    @XmlTransient
    public Collection<TblComment> getTblCommentCollection() {
        return tblCommentCollection;
    }

    public void setTblCommentCollection(Collection<TblComment> tblCommentCollection) {
        this.tblCommentCollection = tblCommentCollection;
    }

    @XmlTransient
    public Collection<TblNotification> getTblNotificationCollection() {
        return tblNotificationCollection;
    }

    public void setTblNotificationCollection(Collection<TblNotification> tblNotificationCollection) {
        this.tblNotificationCollection = tblNotificationCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (userEmail != null ? userEmail.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblUser)) {
            return false;
        }
        TblUser other = (TblUser) object;
        if ((this.userEmail == null && other.userEmail != null) || (this.userEmail != null && !this.userEmail.equals(other.userEmail))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hangntk.entity.TblUser[ userEmail=" + userEmail + " ]";
    }
    
      public String encryptPass(String password)throws Exception{
        MessageDigest digest=MessageDigest.getInstance("SHA-256");
        byte[]hash=digest.digest(password.getBytes(StandardCharsets.UTF_8));
        return DatatypeConverter.printHexBinary(hash);
    }

    @XmlTransient
    public Collection<TblEmotion> getTblEmotionCollection() {
        return tblEmotionCollection;
    }

    public void setTblEmotionCollection(Collection<TblEmotion> tblEmotionCollection) {
        this.tblEmotionCollection = tblEmotionCollection;
    }
}
