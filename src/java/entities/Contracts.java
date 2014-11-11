package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "contracts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contracts.findAll", query = "SELECT c FROM Contracts c"),
    @NamedQuery(name = "Contracts.findByCid", query = "SELECT c FROM Contracts c WHERE c.cid = :cid"),
    @NamedQuery(name = "Contracts.findByClientName", query = "SELECT c FROM Contracts c WHERE c.clientName = :clientName"),
    @NamedQuery(name = "Contracts.findByClientEmail", query = "SELECT c FROM Contracts c WHERE c.clientEmail = :clientEmail"),
    @NamedQuery(name = "Contracts.findByClientPhone", query = "SELECT c FROM Contracts c WHERE c.clientPhone = :clientPhone"),
    @NamedQuery(name = "Contracts.findByTotalProductPrice", query = "SELECT c FROM Contracts c WHERE c.totalProductPrice = :totalProductPrice"),
    @NamedQuery(name = "Contracts.findByTotalConstructionPrice", query = "SELECT c FROM Contracts c WHERE c.totalConstructionPrice = :totalConstructionPrice"),
    @NamedQuery(name = "Contracts.findByTotalConstructionTime", query = "SELECT c FROM Contracts c WHERE c.totalConstructionTime = :totalConstructionTime"),
    @NamedQuery(name = "Contracts.findByCreateAt", query = "SELECT c FROM Contracts c WHERE c.createAt = :createAt"),
    @NamedQuery(name = "Contracts.findByUpdateAt", query = "SELECT c FROM Contracts c WHERE c.updateAt = :updateAt")})

public class Contracts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    //@NotNull
    @Column(name = "cid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer cid;
    @Size(max = 254)
    @Column(name = "client_name")
    private String clientName;
    @Size(max = 254)
    @Column(name = "client_email")
    private String clientEmail;
    @Size(max = 20)
    @Column(name = "client_phone")
    private String clientPhone;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "client_requirements")
    private String clientRequirements;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "payment_details")
    private String paymentDetails;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "total_product_price")
    private BigDecimal totalProductPrice;
    @Column(name = "total_construction_price")
    private BigDecimal totalConstructionPrice;
    @Column(name = "total_construction_time")
    private Integer totalConstructionTime;
    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    @Column(name = "update_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;
    @OneToOne(mappedBy = "contractId")
    private Projects projects;
    @JoinColumn(name = "order_id", referencedColumnName = "oid")
    @OneToOne
    private Orders orderId;

    public Contracts() {
    }

    public Contracts(Integer cid) {
        this.cid = cid;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getClientEmail() {
        return clientEmail;
    }

    public void setClientEmail(String clientEmail) {
        this.clientEmail = clientEmail;
    }

    public String getClientPhone() {
        return clientPhone;
    }

    public void setClientPhone(String clientPhone) {
        this.clientPhone = clientPhone;
    }

    public String getClientRequirements() {
        return clientRequirements;
    }

    public void setClientRequirements(String clientRequirements) {
        this.clientRequirements = clientRequirements;
    }

    public String getPaymentDetails() {
        return paymentDetails;
    }

    public void setPaymentDetails(String paymentDetails) {
        this.paymentDetails = paymentDetails;
    }

    public BigDecimal getTotalProductPrice() {
        return totalProductPrice;
    }

    public void setTotalProductPrice(BigDecimal totalProductPrice) {
        this.totalProductPrice = totalProductPrice;
    }

    public BigDecimal getTotalConstructionPrice() {
        return totalConstructionPrice;
    }

    public void setTotalConstructionPrice(BigDecimal totalConstructionPrice) {
        this.totalConstructionPrice = totalConstructionPrice;
    }

    public Integer getTotalConstructionTime() {
        return totalConstructionTime;
    }

    public void setTotalConstructionTime(Integer totalConstructionTime) {
        this.totalConstructionTime = totalConstructionTime;
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

    public Projects getProjects() {
        return projects;
    }

    public void setProjects(Projects projects) {
        this.projects = projects;
    }

    public Orders getOrderId() {
        return orderId;
    }

    public void setOrderId(Orders orderId) {
        this.orderId = orderId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (cid != null ? cid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Contracts)) {
            return false;
        }
        Contracts other = (Contracts) object;
        if ((this.cid == null && other.cid != null) || (this.cid != null && !this.cid.equals(other.cid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Contracts[ cid=" + cid + " ]";
    }
    
}
