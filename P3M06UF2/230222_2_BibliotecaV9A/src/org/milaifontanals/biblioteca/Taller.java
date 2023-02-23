/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.biblioteca;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;


/**
 *
 * @author Usuari
 */
@Entity
@Access(AccessType.FIELD)
public class Taller implements Serializable {
    @Id
    @Column(length=4)
    private String codi;            // Obligatori i de llargada 4
    @Basic(optional=false)
    @Column(length=30, nullable=false)
    private String titol;           // Obligatori i de llargada màxima 30
    @Lob
    private String descripcio;      // No obligatori o amb contingut
    
    protected Taller() {
    }

    public Taller(String codi, String titol) {
        setCodi(codi);
        setTitol(titol);
    }

    public String getCodi() {
        return codi;
    }

    private void setCodi(String codi) {
        if (codi==null || codi.length()!=4) {
            throw new RuntimeException("Codi de taller obligatori i de llargada 4 caràcters");
        }
        this.codi = codi;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        if (titol==null || titol.length()==0 || titol.length()>30) {
            throw new RuntimeException("Títol de taller obligatori i de llargada màxima 30 caràcters");
        }
        this.titol = titol;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 37 * hash + Objects.hashCode(this.codi);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Taller other = (Taller) obj;
        if (!Objects.equals(this.codi, other.codi)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Taller{" + "codi=" + codi + ", titol=" + titol + ", descripcio=" + descripcio + '}';
    }

    
}
