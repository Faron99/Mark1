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
public class PwikiPK implements Serializable {
    @Basic(optional = false)
    @NotNull
    @Column(name = "idPWiki")
    private int idPWiki;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 7)
    @Column(name = "IdTAC")
    private String idTAC;

    public PwikiPK() {
    }

    public PwikiPK(int idPWiki, String idTAC) {
        this.idPWiki = idPWiki;
        this.idTAC = idTAC;
    }

    public int getIdPWiki() {
        return idPWiki;
    }

    public void setIdPWiki(int idPWiki) {
        this.idPWiki = idPWiki;
    }

    public String getIdTAC() {
        return idTAC;
    }

    public void setIdTAC(String idTAC) {
        this.idTAC = idTAC;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (int) idPWiki;
        hash += (idTAC != null ? idTAC.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PwikiPK)) {
            return false;
        }
        PwikiPK other = (PwikiPK) object;
        if (this.idPWiki != other.idPWiki) {
            return false;
        }
        if ((this.idTAC == null && other.idTAC != null) || (this.idTAC != null && !this.idTAC.equals(other.idTAC))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.PwikiPK[ idPWiki=" + idPWiki + ", idTAC=" + idTAC + " ]";
    }
    
}
