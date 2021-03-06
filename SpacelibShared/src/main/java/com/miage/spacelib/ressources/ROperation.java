package com.miage.spacelib.ressources;

import java.io.Serializable;
import java.util.Calendar;

public abstract class ROperation implements Serializable {

    private Long id;
    
    private RNavette navette;
    
    private Calendar dateCreation;

    private String statut;
    
    public ROperation(){
        
    }
    
    public ROperation(RNavette n, String s){
        this.navette = n;
        this.dateCreation = Calendar.getInstance();
        this.statut = s;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public RNavette getNavette() {
        return navette;
    }

    public void setNavette(RNavette navette) {
        this.navette = navette;
    }

    public Calendar getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Calendar dateCreation) {
        this.dateCreation = dateCreation;
    }

    public String getStatut() {
        return statut;
    }

    public void setStatut(String statut) {
        this.statut = statut;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ROperation)) {
            return false;
        }
        ROperation other = (ROperation) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.miage.spacelib.entities.Operation[ id=" + id + " ]";
    }
    
}
