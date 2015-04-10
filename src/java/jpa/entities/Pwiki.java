/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mmercadoco
 */
@Entity
@Table(name = "pwiki")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Pwiki.findAll", query = "SELECT p FROM Pwiki p"),
    @NamedQuery(name = "Pwiki.findByIdPWiki", query = "SELECT p FROM Pwiki p WHERE p.pwikiPK.idPWiki = :idPWiki"),
    @NamedQuery(name = "Pwiki.findByName", query = "SELECT p FROM Pwiki p WHERE p.name = :name"),
    @NamedQuery(name = "Pwiki.findByModel", query = "SELECT p FROM Pwiki p WHERE p.model = :model"),
    @NamedQuery(name = "Pwiki.findByDescription", query = "SELECT p FROM Pwiki p WHERE p.description = :description"),
    @NamedQuery(name = "Pwiki.findByIdTAC", query = "SELECT p FROM Pwiki p WHERE p.pwikiPK.idTAC = :idTAC"),
    @NamedQuery(name = "Pwiki.findByImage", query = "SELECT p FROM Pwiki p WHERE p.image = :image"),
    @NamedQuery(name = "Pwiki.findByDips", query = "SELECT p FROM Pwiki p WHERE p.dips = :dips"),
    @NamedQuery(name = "Pwiki.findByTec", query = "SELECT p FROM Pwiki p WHERE p.tec = :tec"),
    @NamedQuery(name = "Pwiki.findByEntorno", query = "SELECT p FROM Pwiki p WHERE p.entorno = :entorno")})
public class Pwiki implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected PwikiPK pwikiPK;
    @Size(max = 45)
    @Column(name = "Name")
    private String name;
    @Size(max = 45)
    @Column(name = "Model")
    private String model;
    @Size(max = 1000)
    @Column(name = "Description")
    private String description;
    @Size(max = 70)
    @Column(name = "Image")
    private String image;
    @Size(max = 45)
    @Column(name = "Dips")
    private String dips;
    @Size(max = 45)
    @Column(name = "Tec")
    private String tec;
    @Size(max = 45)
    @Column(name = "Entorno")
    private String entorno;
    @JoinTable(name = "pwiki_has_tac", joinColumns = {
        @JoinColumn(name = "PWiki_idPWiki", referencedColumnName = "idPWiki"),
        @JoinColumn(name = "PWiki_IdTAC", referencedColumnName = "IdTAC")}, inverseJoinColumns = {
        @JoinColumn(name = "TAC_idTAC", referencedColumnName = "idTAC"),
        @JoinColumn(name = "TAC_TACName", referencedColumnName = "TACName"),
        @JoinColumn(name = "TAC_IdCore_team", referencedColumnName = "IdCore_team"),
        @JoinColumn(name = "TAC_Id_PM", referencedColumnName = "Id_PM"),
        @JoinColumn(name = "TAC_IdIssue", referencedColumnName = "IdIssue")})
    @ManyToMany
    private Collection<Tac> tacCollection;

    public Pwiki() {
    }

    public Pwiki(PwikiPK pwikiPK) {
        this.pwikiPK = pwikiPK;
    }

    public Pwiki(int idPWiki, String idTAC) {
        this.pwikiPK = new PwikiPK(idPWiki, idTAC);
    }

    public PwikiPK getPwikiPK() {
        return pwikiPK;
    }

    public void setPwikiPK(PwikiPK pwikiPK) {
        this.pwikiPK = pwikiPK;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDips() {
        return dips;
    }

    public void setDips(String dips) {
        this.dips = dips;
    }

    public String getTec() {
        return tec;
    }

    public void setTec(String tec) {
        this.tec = tec;
    }

    public String getEntorno() {
        return entorno;
    }

    public void setEntorno(String entorno) {
        this.entorno = entorno;
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
        hash += (pwikiPK != null ? pwikiPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Pwiki)) {
            return false;
        }
        Pwiki other = (Pwiki) object;
        if ((this.pwikiPK == null && other.pwikiPK != null) || (this.pwikiPK != null && !this.pwikiPK.equals(other.pwikiPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Pwiki[ pwikiPK=" + pwikiPK + " ]";
    }
    
}
