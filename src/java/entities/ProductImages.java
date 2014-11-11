package entities;

import java.io.Serializable;
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
import javax.validation.constraints.NotNull;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "product_images")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ProductImages.findAll", query = "SELECT p FROM ProductImages p"),
    @NamedQuery(name = "ProductImages.findByProductImgId", query = "SELECT p FROM ProductImages p WHERE p.productImgId = :productImgId")})

public class ProductImages implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    //@NotNull
    @Column(name = "product_img_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer productImgId;
    @JoinColumn(name = "img_id", referencedColumnName = "img_id")
    @ManyToOne
    private Images imgId;
    @JoinColumn(name = "product_id", referencedColumnName = "pid")
    @ManyToOne
    private Products productId;

    public ProductImages() {
    }

    public ProductImages(Integer productImgId) {
        this.productImgId = productImgId;
    }

    public Integer getProductImgId() {
        return productImgId;
    }

    public void setProductImgId(Integer productImgId) {
        this.productImgId = productImgId;
    }

    public Images getImgId() {
        return imgId;
    }

    public void setImgId(Images imgId) {
        this.imgId = imgId;
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
        hash += (productImgId != null ? productImgId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ProductImages)) {
            return false;
        }
        ProductImages other = (ProductImages) object;
        if ((this.productImgId == null && other.productImgId != null) || (this.productImgId != null && !this.productImgId.equals(other.productImgId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.ProductImages[ productImgId=" + productImgId + " ]";
    }
    
}
