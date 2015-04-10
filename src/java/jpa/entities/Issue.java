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
@Table(name = "issue")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Issue.findAll", query = "SELECT i FROM Issue i"),
    @NamedQuery(name = "Issue.findByIdIssue", query = "SELECT i FROM Issue i WHERE i.idIssue = :idIssue"),
    @NamedQuery(name = "Issue.findByIssue", query = "SELECT i FROM Issue i WHERE i.issue = :issue"),
    @NamedQuery(name = "Issue.findByDescription", query = "SELECT i FROM Issue i WHERE i.description = :description"),
    @NamedQuery(name = "Issue.findByRootCause", query = "SELECT i FROM Issue i WHERE i.rootCause = :rootCause"),
    @NamedQuery(name = "Issue.findByRecommend", query = "SELECT i FROM Issue i WHERE i.recommend = :recommend")})
public class Issue implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @NotNull
    @Column(name = "idIssue")
    private Integer idIssue;
    @Column(name = "Issue")
    private Integer issue;
    @Size(max = 100)
    @Column(name = "Description")
    private String description;
    @Size(max = 200)
    @Column(name = "Root_Cause")
    private String rootCause;
    @Size(max = 200)
    @Column(name = "Recommend")
    private String recommend;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "issue")
    private Collection<Tac> tacCollection;

    public Issue() {
    }

    public Issue(Integer idIssue) {
        this.idIssue = idIssue;
    }

    public Integer getIdIssue() {
        return idIssue;
    }

    public void setIdIssue(Integer idIssue) {
        this.idIssue = idIssue;
    }

    public Integer getIssue() {
        return issue;
    }

    public void setIssue(Integer issue) {
        this.issue = issue;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getRootCause() {
        return rootCause;
    }

    public void setRootCause(String rootCause) {
        this.rootCause = rootCause;
    }

    public String getRecommend() {
        return recommend;
    }

    public void setRecommend(String recommend) {
        this.recommend = recommend;
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
        hash += (idIssue != null ? idIssue.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Issue)) {
            return false;
        }
        Issue other = (Issue) object;
        if ((this.idIssue == null && other.idIssue != null) || (this.idIssue != null && !this.idIssue.equals(other.idIssue))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "jpa.entities.Issue[ idIssue=" + idIssue + " ]";
    }
    
}
