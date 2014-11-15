package entities;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

@Entity
@Table(name = "manufacturers")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Manufacturers.findAll", query = "SELECT m FROM Manufacturers m"),
    @NamedQuery(name = "Manufacturers.findByMid", query = "SELECT m FROM Manufacturers m WHERE m.mid = :mid"),
    @NamedQuery(name = "Manufacturers.findByName", query = "SELECT m FROM Manufacturers m WHERE m.name = :name"),
    @NamedQuery(name = "Manufacturers.findByNation", query = "SELECT m FROM Manufacturers m WHERE m.nationId = :nation")
})

public class Manufacturers implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    //@NotNull
    @Column(name = "mid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer mid;
    @Size(max = 254)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "manufacturerId")
    private List<ProductInformations> productInformationsList;
    @JoinColumn(name = "nation_id", referencedColumnName = "nid")
    @ManyToOne
    private Nations nationId;

    public Manufacturers() {
    }

    public Manufacturers(Integer mid) {
        this.mid = mid;
    }

    public Integer getMid() {
        return mid;
    }

    public void setMid(Integer mid) {
        this.mid = mid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @XmlTransient
    public List<ProductInformations> getProductInformationsList() {
        return productInformationsList;
    }

    public void setProductInformationsList(List<ProductInformations> productInformationsList) {
        this.productInformationsList = productInformationsList;
    }

    public Nations getNationId() {
        return nationId;
    }

    public void setNationId(Nations nationId) {
        this.nationId = nationId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (mid != null ? mid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Manufacturers)) {
            return false;
        }
        Manufacturers other = (Manufacturers) object;
        if ((this.mid == null && other.mid != null) || (this.mid != null && !this.mid.equals(other.mid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Manufacturers[ mid=" + mid + " ]";
    }
    
}
