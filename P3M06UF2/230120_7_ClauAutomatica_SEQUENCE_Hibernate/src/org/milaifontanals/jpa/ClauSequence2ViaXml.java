/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import java.util.Objects;

/**
 *
 * @author Usuari
 */
public class ClauSequence2ViaXml {
    Integer codi;
    String descripcio;

    protected ClauSequence2ViaXml() {
    }

    public ClauSequence2ViaXml(String descripcio) {
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
        final ClauSequence2ViaXml other = (ClauSequence2ViaXml) obj;
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
