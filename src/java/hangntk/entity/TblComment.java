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
@Table(name = "tblComment", catalog = "MYSOCIALMEDIA", schema = "dbo")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "TblComment.findAll", query = "SELECT t FROM TblComment t")
    , @NamedQuery(name = "TblComment.findByCommentNo", query = "SELECT t FROM TblComment t WHERE t.commentNo = :commentNo")
    , @NamedQuery(name = "TblComment.findByCommentID", query = "SELECT t FROM TblComment t WHERE t.commentID = :commentID")
    , @NamedQuery(name = "TblComment.findByCommentContent", query = "SELECT t FROM TblComment t WHERE t.commentContent = :commentContent")
    ,@NamedQuery(name = "TblComment.findByArticle", query = "SELECT t FROM TblComment t WHERE t.commentOnPost = :commentOnPost AND t.commentStatus=:commentStatus")
    , @NamedQuery(name = "TblComment.findByCommentDate", query = "SELECT t FROM TblComment t WHERE t.commentDate = :commentDate")})
public class TblComment implements Serializable {

    @Basic(optional = false)
    @Column(name = "commentStatus", nullable = false, length = 50)
    private String commentStatus;
    private static final long serialVersionUID = 1L;
    @Basic(optional = false)
    @Column(name = "commentNo", nullable = false)
    private int commentNo;
    @Id
    @Basic(optional = false)
    @Column(name = "commentID", nullable = false, length = 10)
    private String commentID;
    @Basic(optional = false)
    @Column(name = "commentContent", nullable = false, length = 2147483647)
    private String commentContent;
    @Basic(optional = false)
    @Column(name = "commentDate", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date commentDate;
    @JoinColumn(name = "commentOnPost", referencedColumnName = "articleID")
    @ManyToOne
    private TblArticle commentOnPost;
    @JoinColumn(name = "commentPostBy", referencedColumnName = "userEmail")
    @ManyToOne
    private TblUser commentPostBy;

    public TblComment() {
    }

    public TblComment(String commentID) {
        this.commentID = commentID;
    }

    public TblComment(String commentID, int commentNo, String commentContent, Date commentDate) {
        this.commentID = commentID;
        this.commentNo = commentNo;
        this.commentContent = commentContent;
        this.commentDate = commentDate;
    }

    public int getCommentNo() {
        return commentNo;
    }

    public void setCommentNo(int commentNo) {
        this.commentNo = commentNo;
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Date getCommentDate() {
        return commentDate;
    }

    public void setCommentDate(Date commentDate) {
        this.commentDate = commentDate;
    }

    public TblArticle getCommentOnPost() {
        return commentOnPost;
    }

    public void setCommentOnPost(TblArticle commentOnPost) {
        this.commentOnPost = commentOnPost;
    }

    public TblUser getCommentPostBy() {
        return commentPostBy;
    }

    public void setCommentPostBy(TblUser commentPostBy) {
        this.commentPostBy = commentPostBy;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (commentID != null ? commentID.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TblComment)) {
            return false;
        }
        TblComment other = (TblComment) object;
        if ((this.commentID == null && other.commentID != null) || (this.commentID != null && !this.commentID.equals(other.commentID))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hangntk.entity.TblComment[ commentID=" + commentID + " ]";
    }

    public String getCommentStatus() {
        return commentStatus;
    }

    public void setCommentStatus(String commentStatus) {
        this.commentStatus = commentStatus;
    }

}
