package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p"),
    @NamedQuery(name = "Products.findByPid", query = "SELECT p FROM Products p WHERE p.pid = :pid"),
    @NamedQuery(name = "Products.findByCode", query = "SELECT p FROM Products p WHERE p.code = :code"),
    @NamedQuery(name = "Products.findByName", query = "SELECT p FROM Products p WHERE p.name = :name"),
    @NamedQuery(name = "Products.findByPrice", query = "SELECT p FROM Products p WHERE p.price = :price"),
    @NamedQuery(name = "Products.findByConstructionPrice", query = "SELECT p FROM Products p WHERE p.constructionPrice = :constructionPrice"),
    @NamedQuery(name = "Products.findByConstructionTime", query = "SELECT p FROM Products p WHERE p.constructionTime = :constructionTime"),
    @NamedQuery(name = "Products.findByCreateAt", query = "SELECT p FROM Products p WHERE p.createAt = :createAt"),
    @NamedQuery(name = "Products.findByUpdateAt", query = "SELECT p FROM Products p WHERE p.updateAt = :updateAt"),
    @NamedQuery(name = "Products.exists", query = "SELECT COUNT(p.pid) FROM Products p WHERE p.pid = :pid"),
    @NamedQuery(name = "Products.getForSelectBox", query = "SELECT p.pid,p.name FROM Products p"),
    @NamedQuery(name = "Products.getProductPrice", query = "SELECT p.price FROM Products p WHERE p.pid = :pid"),
    @NamedQuery(name = "Products.findAllByCategoryId", query = "SELECT p FROM Products p WHERE p.categoryId = :categoryId"),
    @NamedQuery(name = "Products.countAll", query = "SELECT COUNT(p.pid) FROM Products p"),
    @NamedQuery(name = "Products.countAllByCategory", query = "SELECT COUNT(p.pid) FROM Products p WHERE p.categoryId = :categoryId"),

})

public class Products implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    //@NotNull
    @Column(name = "pid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 10)
    @Column(name = "code")
    private String code;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "name")
    private String name;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "price")
    private BigDecimal price;
    @Column(name = "construction_price")
    private BigDecimal constructionPrice;
    @Column(name = "construction_time")
    private Integer constructionTime;
    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    @Column(name = "update_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;
    @JoinColumn(name = "category_id", referencedColumnName = "cid")
    @ManyToOne
    private Categories categoryId;
    @OneToMany(mappedBy = "productId")
    private List<ProductImages> productImagesList;
    @OneToOne(mappedBy = "productId")
    private ProductInformations productInformations;
    @OneToMany(mappedBy = "productId")
    private List<OrderProductDetails> orderProductDetailsList;

    public Products() {
    }

    public Products(Integer pid) {
        this.pid = pid;
    }

    public Products(Integer pid, String code, String name) {
        this.pid = pid;
        this.code = code;
        this.name = name;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public BigDecimal getConstructionPrice() {
        return constructionPrice;
    }

    public void setConstructionPrice(BigDecimal constructionPrice) {
        this.constructionPrice = constructionPrice;
    }

    public Integer getConstructionTime() {
        return constructionTime;
    }

    public void setConstructionTime(Integer constructionTime) {
        this.constructionTime = constructionTime;
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

    public Categories getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Categories categoryId) {
        this.categoryId = categoryId;
    }

    @XmlTransient
    public List<ProductImages> getProductImagesList() {
        return productImagesList;
    }

    public void setProductImagesList(List<ProductImages> productImagesList) {
        this.productImagesList = productImagesList;
    }

    public ProductInformations getProductInformations() {
        return productInformations;
    }

    public void setProductInformations(ProductInformations productInformations) {
        this.productInformations = productInformations;
    }

    @XmlTransient
    public List<OrderProductDetails> getOrderProductDetailsList() {
        return orderProductDetailsList;
    }

    public void setOrderProductDetailsList(List<OrderProductDetails> orderProductDetailsList) {
        this.orderProductDetailsList = orderProductDetailsList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (pid != null ? pid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Products)) {
            return false;
        }
        Products other = (Products) object;
        if ((this.pid == null && other.pid != null) || (this.pid != null && !this.pid.equals(other.pid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Products[ pid=" + pid + " ]";
    }

}
