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
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cu Beo
 */
@Entity
@Table(name = "order_product_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "OrderProductDetails.findAll", query = "SELECT o FROM OrderProductDetails o"),
    @NamedQuery(name = "OrderProductDetails.findByOpdid", query = "SELECT o FROM OrderProductDetails o WHERE o.opdid = :opdid"),
    @NamedQuery(name = "OrderProductDetails.findByQuantity", query = "SELECT o FROM OrderProductDetails o WHERE o.quantity = :quantity"),
    @NamedQuery(name = "OrderProductDetails.findByCreateAt", query = "SELECT o FROM OrderProductDetails o WHERE o.createAt = :createAt")})
public class OrderProductDetails implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    //@NotNull
    @Column(name = "opdid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer opdid;
    @Column(name = "quantity")
    private Integer quantity;
    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    @JoinColumn(name = "order_id", referencedColumnName = "oid")
    @ManyToOne
    private Orders orderId;
    @JoinColumn(name = "product_id", referencedColumnName = "pid")
    @ManyToOne
    private Products productId;

    public OrderProductDetails() {
    }

    public OrderProductDetails(Integer opdid) {
        this.opdid = opdid;
    }

    public Integer getOpdid() {
        return opdid;
    }

    public void setOpdid(Integer opdid) {
        this.opdid = opdid;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Date getCreateAt() {
        return createAt;
    }

    public void setCreateAt(Date createAt) {
        this.createAt = createAt;
    }

    public Orders getOrderId() {
        return orderId;
    }

    public void setOrderId(Orders orderId) {
        this.orderId = orderId;
    }

    public Products getProductId() {
        return productId;
    }

    public void setProductId(Products productId) {
        this.productId = productId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (opdid != null ? opdid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof OrderProductDetails)) {
            return false;
        }
        OrderProductDetails other = (OrderProductDetails) object;
        if ((this.opdid == null && other.opdid != null) || (this.opdid != null && !this.opdid.equals(other.opdid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.OrderProductDetails[ opdid=" + opdid + " ]";
    }
    
}
