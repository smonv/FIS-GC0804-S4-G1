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
import javax.persistence.Lob;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Cu Beo
 */
@Entity
@Table(name = "projects")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Projects.findAll", query = "SELECT p FROM Projects p"),
    @NamedQuery(name = "Projects.findByPid", query = "SELECT p FROM Projects p WHERE p.pid = :pid"),
    @NamedQuery(name = "Projects.findByTitle", query = "SELECT p FROM Projects p WHERE p.title = :title"),
    @NamedQuery(name = "Projects.findByLocation", query = "SELECT p FROM Projects p WHERE p.location = :location"),
    @NamedQuery(name = "Projects.findByImagePath", query = "SELECT p FROM Projects p WHERE p.imagePath = :imagePath"),
    @NamedQuery(name = "Projects.findByStartAt", query = "SELECT p FROM Projects p WHERE p.startAt = :startAt"),
    @NamedQuery(name = "Projects.findByEndAt", query = "SELECT p FROM Projects p WHERE p.endAt = :endAt")})
public class Projects implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    //@NotNull
    @Column(name = "pid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer pid;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 254)
    @Column(name = "title")
    private String title;
    @Basic(optional = false)
    @NotNull
    @Lob
    @Size(min = 1, max = 2147483647)
    @Column(name = "content")
    private String content;
    @Size(max = 254)
    @Column(name = "location")
    private String location;
    @Size(max = 2147483647)
    @Column(name = "image_path")
    private String imagePath;
    @Column(name = "start_at")
    @Temporal(TemporalType.DATE)
    private Date startAt;
    @Column(name = "end_at")
    @Temporal(TemporalType.DATE)
    private Date endAt;

    public Projects() {
    }

    public Projects(Integer pid) {
        this.pid = pid;
    }

    public Projects(Integer pid, String title, String content) {
        this.pid = pid;
        this.title = title;
        this.content = content;
    }

    public Integer getPid() {
        return pid;
    }

    public void setPid(Integer pid) {
        this.pid = pid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public Date getStartAt() {
        return startAt;
    }

    public void setStartAt(Date startAt) {
        this.startAt = startAt;
    }

    public Date getEndAt() {
        return endAt;
    }

    public void setEndAt(Date endAt) {
        this.endAt = endAt;
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
        if (!(object instanceof Projects)) {
            return false;
        }
        Projects other = (Projects) object;
        if ((this.pid == null && other.pid != null) || (this.pid != null && !this.pid.equals(other.pid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Projects[ pid=" + pid + " ]";
    }
    
}
