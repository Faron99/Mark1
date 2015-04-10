/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jpa.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 *
 * @author mmercadoco
 */
@Embeddable
public class TacPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idTAC")
    private int idTAC;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "TACName")
    private String tACName;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdIssue")
    private int idIssue;
    @Basic(optional = false)
    @NotNull
    @Column(name = "IdCore_team")
    private int idCoreteam;
    @Basic(optional = false)
    @NotNull
    @Column(name = "Id_PM")
    private int idPM;

    public TacPK() {
    }

    public TacPK(int idTAC, String tACName, int idIssue, int idCoreteam, int idPM) {
        this.idTAC = idTAC;
        this.tACName = tACName;
        this.idIssue = idIssue;
        this.idCoreteam = idCoreteam;
        this.idPM = idPM;
    }

    public int getIdTAC() {
        return idTAC;
    }

    public void setIdTAC(int idTAC) {
        this.idTAC = idTAC;
    }

    public String getTACName() {
        return tACName;
    }

    public void setTACName(String tACName) {
        this.tACName = tACName;
    }

    public int getIdIssue() {
        return idIssue;
    }

    public void setIdIssue(int idIssue) {
        this.idIssue = idIssue;
    }

    public int getIdCoreteam() {
        return idCoreteam;
    }

    public void setIdCoreteam(int idCoreteam) {
        this.idCoreteam = idCoreteam;
    }

    public int getIdPM() {
        return idPM;
    }

    public void setIdPM(int idPM) {
        this.idPM = idPM;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idTAC;
        hash += (tACName != null ? tACName.hashCode() : 0);
        hash += (int) idIssue;
        hash += (int) idCoreteam;
        hash += (int) idPM;
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof TacPK)) {
            return false;
        }
        TacPK other = (TacPK) object;
        if (this.idTAC != other.idTAC) {
            return false;
        }
        if ((this.tACName == null && other.tACName != null) || (this.tACName != null && !this.tACName.equals(other.tACName))) {
            return false;
        }
        if (this.idIssue != other.idIssue) {
            return false;
        }
        if (this.idCoreteam != other.idCoreteam) {
            return false;
        }
        if (this.idPM != other.idPM) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.TacPK[ idTAC=" + idTAC + ", tACName=" + tACName + ", idIssue=" + idIssue + ", idCoreteam=" + idCoreteam + ", idPM=" + idPM + " ]";
    }
    
}
