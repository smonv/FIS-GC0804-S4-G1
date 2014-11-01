/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SolomonT
 */
@Entity
@Table(name = "feedback_level")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FeedbackLevel.findAll", query = "SELECT f FROM FeedbackLevel f"),
    @NamedQuery(name = "FeedbackLevel.findByFlid", query = "SELECT f FROM FeedbackLevel f WHERE f.flid = :flid"),
    @NamedQuery(name = "FeedbackLevel.findByName", query = "SELECT f FROM FeedbackLevel f WHERE f.name = :name")})
public class FeedbackLevel implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    //@NotNull
    @Column(name = "flid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer flid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "feedbackLevel")
    private List<Feedbacks> feedbacksList;

    public FeedbackLevel() {
    }

    public FeedbackLevel(Integer flid) {
        this.flid = flid;
    }

    public FeedbackLevel(Integer flid, String name) {
        this.flid = flid;
        this.name = name;
    }

    public Integer getFlid() {
        return flid;
    }

    public void setFlid(Integer flid) {
        this.flid = flid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<Feedbacks> getFeedbacksList() {
        return feedbacksList;
    }

    public void setFeedbacksList(List<Feedbacks> feedbacksList) {
        this.feedbacksList = feedbacksList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (flid != null ? flid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof FeedbackLevel)) {
            return false;
        }
        FeedbackLevel other = (FeedbackLevel) object;
        if ((this.flid == null && other.flid != null) || (this.flid != null && !this.flid.equals(other.flid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.FeedbackLevel[ flid=" + flid + " ]";
    }
    
}
