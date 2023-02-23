/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 *
 * @author Usuari
 */
@Embeddable
public class ProvinciaId implements Serializable {

    // Camp que fa l'efecte a nivell de PK del què hauria de fer el camp Pais
    @Column(name = "codi_pais", length=2)
    private String paisCodi;
    
    @Column(name = "codi_prov", length = 3)
    private String provCodi;
    // Mireu a llegiu-me, per què hem anomenat aquest camp provCodi... 

    protected ProvinciaId() {

    }

    public ProvinciaId(String paisCodi, String provCodi) {
        setPaisCodi(paisCodi);
        setProvCodi(provCodi);
    }

    public ProvinciaId(Pais pais, String provCodi) {
        setPaisCodi(pais.getCodi());
        setProvCodi(provCodi);
    }
    
    public String getPaisCodi() {
        return paisCodi;
    }

    public void setPaisCodi(String paisCodi) {
        if (paisCodi==null || paisCodi.length()!=2) {
            throw new RuntimeException("Codi de país obligatori i de 2 caràcters");
        }
        this.paisCodi = paisCodi;
    }

    public String getProvCodi() {
        return provCodi;
    }

    public void setProvCodi(String provCodi) {
        if (provCodi == null || provCodi.length() == 0 || provCodi.length() > 3) {
            throw new RuntimeException("Codi de província obligatòri, amb contingut i de 3 caràcters màxim");
        }
        this.provCodi = provCodi;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.paisCodi);
        hash = 97 * hash + Objects.hashCode(this.provCodi);
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
        if (!Objects.equals(this.paisCodi, other.paisCodi)) {
            return false;
        }
        if (!Objects.equals(this.provCodi, other.provCodi)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProvinciaId{" + "paisCodi=" + paisCodi + ", provCodi=" + provCodi + '}';
    }

}
