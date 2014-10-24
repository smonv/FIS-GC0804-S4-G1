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
 * @author Cu Beo
 */
@Entity
@Table(name = "payment_types")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PaymentTypes.findAll", query = "SELECT p FROM PaymentTypes p"),
    @NamedQuery(name = "PaymentTypes.findByPtid", query = "SELECT p FROM PaymentTypes p WHERE p.ptid = :ptid"),
    @NamedQuery(name = "PaymentTypes.findByName", query = "SELECT p FROM PaymentTypes p WHERE p.name = :name")})
public class PaymentTypes implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    //@NotNull
    @Column(name = "ptid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer ptid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "paymentType")
    private List<Orders> ordersList;

    public PaymentTypes() {
    }

    public PaymentTypes(Integer ptid) {
        this.ptid = ptid;
    }

    public PaymentTypes(Integer ptid, String name) {
        this.ptid = ptid;
        this.name = name;
    }

    public Integer getPtid() {
        return ptid;
    }

    public void setPtid(Integer ptid) {
        this.ptid = ptid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        hash += (ptid != null ? ptid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PaymentTypes)) {
            return false;
        }
        PaymentTypes other = (PaymentTypes) object;
        if ((this.ptid == null && other.ptid != null) || (this.ptid != null && !this.ptid.equals(other.ptid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.PaymentTypes[ ptid=" + ptid + " ]";
    }
    
}
