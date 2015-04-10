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
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
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
@Table(name = "tac")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Tac.findAll", query = "SELECT t FROM Tac t"),
    @NamedQuery(name = "Tac.findByIdTAC", query = "SELECT t FROM Tac t WHERE t.tacPK.idTAC = :idTAC"),
    @NamedQuery(name = "Tac.findByTACName", query = "SELECT t FROM Tac t WHERE t.tacPK.tACName = :tACName"),
    @NamedQuery(name = "Tac.findByDescription", query = "SELECT t FROM Tac t WHERE t.description = :description"),
    @NamedQuery(name = "Tac.findByIdIssue", query = "SELECT t FROM Tac t WHERE t.tacPK.idIssue = :idIssue"),
    @NamedQuery(name = "Tac.findByIdCoreteam", query = "SELECT t FROM Tac t WHERE t.tacPK.idCoreteam = :idCoreteam"),
    @NamedQuery(name = "Tac.findByIdPM", query = "SELECT t FROM Tac t WHERE t.tacPK.idPM = :idPM")})
public class Tac implements Serializable {
    private static final long serialVersionUID = 1L;
    @EmbeddedId
    protected TacPK tacPK;
    @Size(max = 100)
    @Column(name = "Description")
    private String description;
    @ManyToMany(mappedBy = "tacCollection")
    private Collection<Pwiki> pwikiCollection;
    @ManyToMany(mappedBy = "tacCollection")
    private Collection<Pm> pmCollection;
    @JoinColumn(name = "IdCore_team", referencedColumnName = "idCore_team", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private CoreTeam coreTeam;
    @JoinColumn(name = "IdIssue", referencedColumnName = "idIssue", insertable = false, updatable = false)
    @ManyToOne(optional = false)
    private Issue issue;

    public Tac() {
    }

    public Tac(TacPK tacPK) {
        this.tacPK = tacPK;
    }

    public Tac(int idTAC, String tACName, int idIssue, int idCoreteam, int idPM) {
        this.tacPK = new TacPK(idTAC, tACName, idIssue, idCoreteam, idPM);
    }

    public TacPK getTacPK() {
        return tacPK;
    }

    public void setTacPK(TacPK tacPK) {
        this.tacPK = tacPK;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @XmlTransient
    public Collection<Pwiki> getPwikiCollection() {
        return pwikiCollection;
    }

    public void setPwikiCollection(Collection<Pwiki> pwikiCollection) {
        this.pwikiCollection = pwikiCollection;
    }

    @XmlTransient
    public Collection<Pm> getPmCollection() {
        return pmCollection;
    }

    public void setPmCollection(Collection<Pm> pmCollection) {
        this.pmCollection = pmCollection;
    }

    public CoreTeam getCoreTeam() {
        return coreTeam;
    }

    public void setCoreTeam(CoreTeam coreTeam) {
        this.coreTeam = coreTeam;
    }

    public Issue getIssue() {
        return issue;
    }

    public void setIssue(Issue issue) {
        this.issue = issue;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tacPK != null ? tacPK.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Tac)) {
            return false;
        }
        Tac other = (Tac) object;
        if ((this.tacPK == null && other.tacPK != null) || (this.tacPK != null && !this.tacPK.equals(other.tacPK))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Tac[ tacPK=" + tacPK + " ]";
    }
    
}
