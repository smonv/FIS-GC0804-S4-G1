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
@Table(name = "list_status")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ListStatus.findAll", query = "SELECT l FROM ListStatus l"),
    @NamedQuery(name = "ListStatus.findByLsid", query = "SELECT l FROM ListStatus l WHERE l.lsid = :lsid"),
    @NamedQuery(name = "ListStatus.findByName", query = "SELECT l FROM ListStatus l WHERE l.name = :name")})
public class ListStatus implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    //@NotNull
    @Column(name = "lsid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer lsid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "complaintStatus")
    private List<Complaints> complaintsList;
    @OneToMany(mappedBy = "feedbackStatus")
    private List<Feedbacks> feedbacksList;
    @OneToMany(mappedBy = "orderStatus")
    private List<Orders> ordersList;

    public ListStatus() {
    }

    public ListStatus(Integer lsid) {
        this.lsid = lsid;
    }

    public ListStatus(Integer lsid, String name) {
        this.lsid = lsid;
        this.name = name;
    }

    public Integer getLsid() {
        return lsid;
    }

    public void setLsid(Integer lsid) {
        this.lsid = lsid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<Complaints> getComplaintsList() {
        return complaintsList;
    }

    public void setComplaintsList(List<Complaints> complaintsList) {
        this.complaintsList = complaintsList;
    }

    @XmlTransient
    public List<Feedbacks> getFeedbacksList() {
        return feedbacksList;
    }

    public void setFeedbacksList(List<Feedbacks> feedbacksList) {
        this.feedbacksList = feedbacksList;
    }

    @XmlTransient
    public List<Orders> getOrdersList() {
        return ordersList;
    }

    public void setOrdersList(List<Orders> ordersList) {
        this.ordersList = ordersList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (lsid != null ? lsid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ListStatus)) {
            return false;
        }
        ListStatus other = (ListStatus) object;
        if ((this.lsid == null && other.lsid != null) || (this.lsid != null && !this.lsid.equals(other.lsid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ListStatus[ lsid=" + lsid + " ]";
    }
    
}
