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

    private Pais pais;
    private String codi;

    protected ProvinciaId() {

    }

    public ProvinciaId(Pais pais, String codi) {
        this.pais = pais;
        this.codi = codi;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.pais);
        hash = 23 * hash + Objects.hashCode(this.codi);
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
        if (!Objects.equals(this.codi, other.codi)) {
            return false;
        }
        if (!Objects.equals(this.pais, other.pais)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProvinciaId{" + "pais=" + pais + ", codi=" + codi + '}';
    }


}
