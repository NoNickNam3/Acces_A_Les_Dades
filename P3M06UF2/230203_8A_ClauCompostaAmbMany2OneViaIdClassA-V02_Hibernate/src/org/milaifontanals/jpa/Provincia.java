/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Usuari
 */
@Entity
@IdClass(ProvinciaId.class)
//@Table(uniqueConstraints = @UniqueConstraint(columnNames = {"codi_pais", "codi_prov"},
//        name = "provincia_pk")
//)

public class Provincia implements Serializable {

    // Camp que fa l'efecte a nivell de PK del què hauria de fer el camp Pais
    @Id
    @Column(length = 2, name = "codi_pais")
    private String aux;
    // El fet d'haver-lo anomenat "aux" propicia que la PK... (veure llegiume)

    // Deixa de ser @Id, però el mantenim dins la classe, doncs al constructor
    // de Províncies se li passa un Pais (objecte). Com que la columna a la BD
    // és idèntica que la columna del camp "aux", cal indicar que aquest camp
    // és insertable=false i updatable=false.
    // Ens permet definir el mètode getPais()
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "codi_pais", insertable = false, updatable = false,
            foreignKey = @ForeignKey(name = "provincia_fk_codi_pais"))
    private Pais pais;          // Obligatori

    @Id
    @Column(name = "codi_prov", length = 3)
    private String codi;        // Obligatori - ISO 3166-2 codis de fins a 3 lletres.
    // Cada país té la seva divisió oficial:
    // Espanya: https://en.wikipedia.org/wiki/ISO_3166-2:ES
    @Basic(optional = false)
    @Column(length = 60, nullable = false)
    private String nom;         // Obligatori amb contingut de 60 caràcters màxim

    protected Provincia() {

    }

    public Provincia(Pais pais, String codi, String nom) {
        setPais(pais);
        setCodi(codi);
        setNom(nom);
    }

    public Pais getPais() {
        return pais;
    }

    private void setPais(Pais pais) {
        if (pais == null) {
            throw new RuntimeException("País obligatori en província");
        }
        this.pais = pais;
        // Ens hem de preocupar que "aux" quedi correctament emplenat
        this.aux = pais.getCodi();
    }

    public String getCodi() {
        return codi;
    }

    public void setCodi(String codi) {
        if (codi == null || codi.length() == 0 || codi.length() > 3) {
            throw new RuntimeException("Codi de província obligatòri, amb contingut i de 3 caràcters màxim");
        }
        this.codi = codi;
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
        hash = 97 * hash + Objects.hashCode(this.pais);
        hash = 97 * hash + Objects.hashCode(this.codi);
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
        return "Provincia{" + "pais=" + pais + ", codi=" + codi + ", nom=" + nom + '}';
    }

}
