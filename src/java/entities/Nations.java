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

@Entity
@Table(name = "nations")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Nations.findAll", query = "SELECT n FROM Nations n"),
    @NamedQuery(name = "Nations.findByNid", query = "SELECT n FROM Nations n WHERE n.nid = :nid"),
    @NamedQuery(name = "Nations.findByName", query = "SELECT n FROM Nations n WHERE n.name = :name")})

public class Nations implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    //@NotNull
    @Column(name = "nid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer nid;
    @Size(max = 254)
    @Column(name = "name")
    private String name;
    @OneToMany(mappedBy = "producedNation")
    private List<ProductInformations> productInformationsList;

    public Nations() {
    }

    public Nations(Integer nid) {
        this.nid = nid;
    }

    public Integer getNid() {
        return nid;
    }

    public void setNid(Integer nid) {
        this.nid = nid;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nid != null ? nid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Nations)) {
            return false;
        }
        Nations other = (Nations) object;
        if ((this.nid == null && other.nid != null) || (this.nid != null && !this.nid.equals(other.nid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Nations[ nid=" + nid + " ]";
    }
    
}
