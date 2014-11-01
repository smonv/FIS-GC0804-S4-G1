/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author SolomonT
 */
@Entity
@Table(name = "products")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Products.findAll", query = "SELECT p FROM Products p"),
    @NamedQuery(name = "Products.findByPid", query = "SELECT p FROM Products p WHERE p.pid = :pid"),
    @NamedQuery(name = "Products.findByCode", query = "SELECT p FROM Products p WHERE p.code = :code"),
    @NamedQuery(name = "Products.findByName", query = "SELECT p FROM Products p WHERE p.name = :name"),
    @NamedQuery(name = "Products.findByManufacturer", query = "SELECT p FROM Products p WHERE p.manufacturer = :manufacturer"),
    @NamedQuery(name = "Products.findByProducedIn", query = "SELECT p FROM Products p WHERE p.producedIn = :producedIn"),
    @NamedQuery(name = "Products.findBySize", query = "SELECT p FROM Products p WHERE p.size = :size"),
    @NamedQuery(name = "Products.findByPrice", query = "SELECT p FROM Products p WHERE p.price = :price"),
    @NamedQuery(name = "Products.findByConstructionPrice", query = "SELECT p FROM Products p WHERE p.constructionPrice = :constructionPrice"),
    @NamedQuery(name = "Products.findByConstructionTime", query = "SELECT p FROM Products p WHERE p.constructionTime = :constructionTime"),
    @NamedQuery(name = "Products.findByImagePath", query = "SELECT p FROM Products p WHERE p.imagePath = :imagePath"),
    @NamedQuery(name = "Products.findByCreateAt", query = "SELECT p FROM Products p WHERE p.createAt = :createAt"),
    @NamedQuery(name = "Products.findByUpdateAt", query = "SELECT p FROM Products p WHERE p.updateAt = :updateAt"),
    @NamedQuery(name = "Products.exists", query = "SELECT COUNT(p.pid) FROM Products p WHERE p.pid = :pid"),
    @NamedQuery(name = "Products.getForSelectBox", query = "SELECT p.pid,p.name FROM Products p"),
    @NamedQuery(name = "Products.getProductPrice", query = "SELECT p.price FROM Products p WHERE p.pid = :pid")
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
    @Size(max = 254)
    @Column(name = "manufacturer")
    private String manufacturer;
    @Size(max = 254)
    @Column(name = "produced_in")
    private String producedIn;
    @Size(max = 254)
    @Column(name = "size")
    private String size;
    @Lob
    @Size(max = 2147483647)
    @Column(name = "infomations")
    private String infomations;
    @Column(name = "price")
    private Long price;
    @Column(name = "construction_price")
    private Long constructionPrice;
    @Column(name = "construction_time")
    private Integer constructionTime;
    @Size(max = 2147483647)
    @Column(name = "image_path")
    private String imagePath;
    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    @Column(name = "update_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;
    @OneToMany(mappedBy = "productId")
    private List<OrderProductDetails> orderProductDetailsList;
    @JoinColumn(name = "category_id", referencedColumnName = "cid")
    @ManyToOne
    private Categories categoryId;

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

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getProducedIn() {
        return producedIn;
    }

    public void setProducedIn(String producedIn) {
        this.producedIn = producedIn;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getInfomations() {
        return infomations;
    }

    public void setInfomations(String infomations) {
        this.infomations = infomations;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Long getConstructionPrice() {
        return constructionPrice;
    }

    public void setConstructionPrice(Long constructionPrice) {
        this.constructionPrice = constructionPrice;
    }

    public Integer getConstructionTime() {
        return constructionTime;
    }

    public void setConstructionTime(Integer constructionTime) {
        this.constructionTime = constructionTime;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
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

    @XmlTransient
    public List<OrderProductDetails> getOrderProductDetailsList() {
        return orderProductDetailsList;
    }

    public void setOrderProductDetailsList(List<OrderProductDetails> orderProductDetailsList) {
        this.orderProductDetailsList = orderProductDetailsList;
    }

    public Categories getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Categories categoryId) {
        this.categoryId = categoryId;
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
