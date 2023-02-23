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
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Usuari
 */
@Entity
public class Pais implements Serializable {
    @Id
    @Column(length=2)
    private String codi;    // Obligatori - ISO 3166-1 alfa-2, codis de dues lletres.
    // Més informació: https://en.wikipedia.org/wiki/ISO_3166-1
    @Basic(optional=false)
    @Column(length=60, nullable=false)
    private String nom;     // Obligatori - Llargada màxima 60
    

    protected Pais() {
    }

    public Pais(String codi, String nom) {
        setCodi(codi);
        setNom(nom);
    }

    public String getCodi() {
        return codi;
    }

    private void setCodi(String codi) {
        if (codi == null || codi.length() != 2) {
            throw new RuntimeException("Codi de pais obligatori amb 2 caràcters");
        }
        this.codi = codi;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if (codi == null || codi.length() == 0 || codi.length() > 60) {
            throw new RuntimeException("Nom de país obligatori amb contingut i amb 60 caràcters màxim");
        }
        this.nom = nom;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.codi);
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
        final Pais other = (Pais) obj;
        if (!Objects.equals(this.codi, other.codi)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Pais{" + "codi=" + codi + ", nom=" + nom + '}';
    }

}
