/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author mmercadoco
 */
@Entity
@Table(name = "core_team")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CoreTeam.findAll", query = "SELECT c FROM CoreTeam c"),
    @NamedQuery(name = "CoreTeam.findByIdCoreteam", query = "SELECT c FROM CoreTeam c WHERE c.idCoreteam = :idCoreteam"),
    @NamedQuery(name = "CoreTeam.findByName", query = "SELECT c FROM CoreTeam c WHERE c.name = :name"),
    @NamedQuery(name = "CoreTeam.findByCharge", query = "SELECT c FROM CoreTeam c WHERE c.charge = :charge")})
public class CoreTeam implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idCore_team")
    private Integer idCoreteam;
    @Size(max = 45)
    @Column(name = "Name")
    private String name;
    @Size(max = 45)
    @Column(name = "Charge")
    private String charge;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "coreTeam")
    private Collection<Tac> tacCollection;

    public CoreTeam() {
    }

    public CoreTeam(Integer idCoreteam) {
        this.idCoreteam = idCoreteam;
    }

    public Integer getIdCoreteam() {
        return idCoreteam;
    }

    public void setIdCoreteam(Integer idCoreteam) {
        this.idCoreteam = idCoreteam;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCharge() {
        return charge;
    }

    public void setCharge(String charge) {
        this.charge = charge;
    }

    @XmlTransient
    public Collection<Tac> getTacCollection() {
        return tacCollection;
    }

    public void setTacCollection(Collection<Tac> tacCollection) {
        this.tacCollection = tacCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idCoreteam != null ? idCoreteam.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof CoreTeam)) {
            return false;
        }
        CoreTeam other = (CoreTeam) object;
        if ((this.idCoreteam == null && other.idCoreteam != null) || (this.idCoreteam != null && !this.idCoreteam.equals(other.idCoreteam))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.CoreTeam[ idCoreteam=" + idCoreteam + " ]";
    }
    
}
