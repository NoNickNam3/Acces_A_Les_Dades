/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 *
 * @author Usuari
 */
@Entity
public class Provincia implements Serializable {

    @EmbeddedId
    private ProvinciaId provinciaId;
    @Basic(optional = false)
    @Column(length = 60, nullable = false)
    private String nom;         // Obligatori amb contingut de 60 caràcters màxim

    protected Provincia() {

    }

    public Provincia(Pais pais, String codiProv, String nom) {
        this(new ProvinciaId(pais,codiProv),nom);
    }
    
    public Provincia(ProvinciaId provinciaId, String nom) {
        setProvinciaId(provinciaId);
        setNom(nom);
    }

    public ProvinciaId getProvinciaId() {
        return provinciaId;
    }

    private void setProvinciaId(ProvinciaId provinciaId) {
        if (provinciaId==null) {
            throw new RuntimeException("ProvinciaId no pot ser null");
        }
        this.provinciaId = provinciaId;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if (nom == null || nom.length() == 0 || nom.length() > 60) {
            throw new RuntimeException("Nom obligatori i amb contingut i de 60 caràcters màxim");
        }
        this.nom = nom;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.provinciaId);
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
        final Provincia other = (Provincia) obj;
        if (!Objects.equals(this.provinciaId, other.provinciaId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Provincia{" + "provinciaId=" + provinciaId + ", nom=" + nom + '}';
    }

}
