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

@Entity
@Table(name = "admins")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Admins.findAll", query = "SELECT a FROM Admins a"),
    @NamedQuery(name = "Admins.findByAid", query = "SELECT a FROM Admins a WHERE a.aid = :aid"),
    @NamedQuery(name = "Admins.findByName", query = "SELECT a FROM Admins a WHERE a.name = :name"),
    @NamedQuery(name = "Admins.findByUsername", query = "SELECT a FROM Admins a WHERE a.username = :username"),
    @NamedQuery(name = "Admins.findByEmail", query = "SELECT a FROM Admins a WHERE a.email = :email"),
    @NamedQuery(name = "Admins.findByPasswordDigest", query = "SELECT a FROM Admins a WHERE a.passwordDigest = :passwordDigest"),
    @NamedQuery(name = "Admins.findByIsSuper", query = "SELECT a FROM Admins a WHERE a.isSuper = :isSuper"),
    @NamedQuery(name = "Admins.findByIsManager", query = "SELECT a FROM Admins a WHERE a.isManager = :isManager"),
    @NamedQuery(name = "Admins.findByCreateAt", query = "SELECT a FROM Admins a WHERE a.createAt = :createAt"),
    @NamedQuery(name = "Admins.findByUpdateAt", query = "SELECT a FROM Admins a WHERE a.updateAt = :updateAt")})

public class Admins implements Serializable {
    @OneToMany(mappedBy = "adminId")
    private List<Projects> projectsList;
    @OneToMany(mappedBy = "adminId")
    private List<Contracts> contractsList;
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    //@NotNull
    @Column(name = "aid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer aid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 60)
    @Column(name = "username")
    private String username;
    // @Pattern(regexp="[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*@(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?", message="Invalid email")//if the field contains email address consider using this annotation to enforce field validation
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "email")
    private String email;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "password_digest")
    private String passwordDigest;
    @Column(name = "is_super")
    private Boolean isSuper;
    @Column(name = "is_manager")
    private Boolean isManager;
    @Column(name = "create_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createAt;
    @Column(name = "update_at")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updateAt;

    public Admins() {
    }

    public Admins(Integer aid) {
        this.aid = aid;
    }

    public Admins(Integer aid, String name, String username, String email, String passwordDigest) {
        this.aid = aid;
        this.name = name;
        this.username = username;
        this.email = email;
        this.passwordDigest = passwordDigest;
    }

    public Integer getAid() {
        return aid;
    }

    public void setAid(Integer aid) {
        this.aid = aid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordDigest() {
        return passwordDigest;
    }

    public void setPasswordDigest(String passwordDigest) {
        this.passwordDigest = passwordDigest;
    }

    public Boolean getIsSuper() {
        return isSuper;
    }

    public void setIsSuper(Boolean isSuper) {
        this.isSuper = isSuper;
    }

    public Boolean getIsManager() {
        return isManager;
    }

    public void setIsManager(Boolean isManager) {
        this.isManager = isManager;
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

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (aid != null ? aid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Admins)) {
            return false;
        }
        Admins other = (Admins) object;
        if ((this.aid == null && other.aid != null) || (this.aid != null && !this.aid.equals(other.aid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Admins[ aid=" + aid + " ]";
    }

    @XmlTransient
    public List<Contracts> getContractsList() {
        return contractsList;
    }

    public void setContractsList(List<Contracts> contractsList) {
        this.contractsList = contractsList;
    }

    @XmlTransient
    public List<Projects> getProjectsList() {
        return projectsList;
    }

    public void setProjectsList(List<Projects> projectsList) {
        this.projectsList = projectsList;
    }
    
}
