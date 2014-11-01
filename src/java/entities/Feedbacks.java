/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author SolomonT
 */
@Entity
@Table(name = "feedbacks")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Feedbacks.findAll", query = "SELECT f FROM Feedbacks f"),
    @NamedQuery(name = "Feedbacks.findByFid", query = "SELECT f FROM Feedbacks f WHERE f.fid = :fid"),
    @NamedQuery(name = "Feedbacks.findByName", query = "SELECT f FROM Feedbacks f WHERE f.name = :name"),
    @NamedQuery(name = "Feedbacks.findByEmail", query = "SELECT f FROM Feedbacks f WHERE f.email = :email"),
    @NamedQuery(name = "Feedbacks.findByCreateAt", query = "SELECT f FROM Feedbacks f WHERE f.createAt = :createAt"),
    @NamedQuery(name = "Feedbacks.findByUpdateAt", query = "SELECT f FROM Feedbacks f WHERE f.updateAt = :updateAt"),
    @NamedQuery(name = "Feedbacks.findByClientId", query = "SELECT f FROM Feedbacks f WHERE f.clientId = :clientId")
})
public class Feedbacks implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    //@NotNull
    @Column(name = "fid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer fid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "name")
    private String name;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "feedback_description")
    private String feedbackDescription;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "problem")
    private String problem;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "improvement")
    private String improvement;
    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    @Column(name = "update_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;
    @JoinColumn(name = "client_id", referencedColumnName = "cid")
    @ManyToOne
    private Clients clientId;
    @JoinColumn(name = "feedback_level", referencedColumnName = "flid")
    @ManyToOne
    private FeedbackLevel feedbackLevel;
    @JoinColumn(name = "feedback_status", referencedColumnName = "lsid")
    @ManyToOne
    private ListStatus feedbackStatus;

    public Feedbacks() {
    }

    public Feedbacks(Integer fid) {
        this.fid = fid;
    }

    public Feedbacks(Integer fid, String name, String email, String feedbackDescription) {
        this.fid = fid;
        this.name = name;
        this.email = email;
        this.feedbackDescription = feedbackDescription;
    }

    public Integer getFid() {
        return fid;
    }

    public void setFid(Integer fid) {
        this.fid = fid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFeedbackDescription() {
        return feedbackDescription;
    }

    public void setFeedbackDescription(String feedbackDescription) {
        this.feedbackDescription = feedbackDescription;
    }

    public String getProblem() {
        return problem;
    }

    public void setProblem(String problem) {
        this.problem = problem;
    }

    public String getImprovement() {
        return improvement;
    }

    public void setImprovement(String improvement) {
        this.improvement = improvement;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Date getUpdateAt() {
        return updateAt;
    }

    public void setUpdateAt(Date updateAt) {
        this.updateAt = updateAt;
    }

    public Clients getClientId() {
        return clientId;
    }

    public void setClientId(Clients clientId) {
        this.clientId = clientId;
    }

    public FeedbackLevel getFeedbackLevel() {
        return feedbackLevel;
    }

    public void setFeedbackLevel(FeedbackLevel feedbackLevel) {
        this.feedbackLevel = feedbackLevel;
    }

    public ListStatus getFeedbackStatus() {
        return feedbackStatus;
    }

    public void setFeedbackStatus(ListStatus feedbackStatus) {
        this.feedbackStatus = feedbackStatus;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (fid != null ? fid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Feedbacks)) {
            return false;
        }
        Feedbacks other = (Feedbacks) object;
        if ((this.fid == null && other.fid != null) || (this.fid != null && !this.fid.equals(other.fid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Feedbacks[ fid=" + fid + " ]";
    }
    
}
