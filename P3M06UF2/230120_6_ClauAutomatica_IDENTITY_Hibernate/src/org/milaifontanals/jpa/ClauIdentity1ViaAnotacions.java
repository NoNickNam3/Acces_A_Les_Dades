/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 *
 * @author Usuari
 */
@Entity
@Table(name="clau_identity1")
public class ClauIdentity1ViaAnotacions {
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    Integer codi;
    @Basic(optional=false)
    @Column(length=30, nullable=false)
    String descripcio;

    protected ClauIdentity1ViaAnotacions() {
    }

    public ClauIdentity1ViaAnotacions(String descripcio) {
        this.descripcio = descripcio;
    }

    public Integer getCodi() {
        return codi;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.codi);
        hash = 89 * hash + Objects.hashCode(this.descripcio);
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
        final ClauIdentity1ViaAnotacions other = (ClauIdentity1ViaAnotacions) obj;
        if (!Objects.equals(this.descripcio, other.descripcio)) {
            return false;
        }
        if (!Objects.equals(this.codi, other.codi)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Registre{" + "codi=" + codi + ", descripcio=" + descripcio + '}';
    }

    

}
