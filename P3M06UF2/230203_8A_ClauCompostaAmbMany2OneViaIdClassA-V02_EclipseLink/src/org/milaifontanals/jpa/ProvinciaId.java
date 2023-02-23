/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Usuari
 */
public class ProvinciaId implements Serializable {

    private String aux;
    private String codi;

    protected ProvinciaId() {

    }

    public ProvinciaId(String codiPais , String codiProv) {
        this.aux = codiPais;
        this.codi = codiProv;
    }
    
    public ProvinciaId(Pais pais, String codiProv) {
        this(pais.getCodi(),codiProv);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 37 * hash + Objects.hashCode(this.aux);
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
        final ProvinciaId other = (ProvinciaId) obj;
        if (!Objects.equals(this.aux, other.aux)) {
            return false;
        }
        if (!Objects.equals(this.codi, other.codi)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProvinciaId{" + "aux=" + aux + ", codi=" + codi + '}';
    }


}
