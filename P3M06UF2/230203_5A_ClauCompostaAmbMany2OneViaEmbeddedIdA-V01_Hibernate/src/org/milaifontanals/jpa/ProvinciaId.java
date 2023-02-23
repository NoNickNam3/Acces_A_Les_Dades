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

    @ManyToOne(optional = false, cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "codi_pais",
            foreignKey = @ForeignKey(name = "provincia_fk_codi_pais"))
    private Pais pais;
    @Column(name = "codi_prov", length = 3)
    private String provCodi;
    // Mireu a llegiu-me, per què hem anomenat aquest camp provCodi... 

    protected ProvinciaId() {

    }

    public ProvinciaId(Pais pais, String codiProv) {
        setPais(pais);
        setProvCodi(codiProv);
    }

    public Pais getPais() {
        return pais;
    }

    private void setPais(Pais pais) {
        if (pais == null) {
            throw new RuntimeException("País obligatori en província");
        }
        this.pais = pais;
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
        int hash = 7;
        hash = 23 * hash + Objects.hashCode(this.pais);
        hash = 23 * hash + Objects.hashCode(this.provCodi);
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
        if (!Objects.equals(this.provCodi, other.provCodi)) {
            return false;
        }
        if (!Objects.equals(this.pais, other.pais)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ProvinciaId{" + "pais=" + pais + ", codi=" + provCodi + '}';
    }


}
