/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.biblioteca;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.Table;
import javax.persistence.TableGenerator;

@Entity
@Table(name = "SOCI", // No cal, per què coincideix amb el que crearia JPA automàticament
        indexes = {
            @Index(name = "SOCI_IDX_COGNOMS_NOM",
                    columnList = "cognom1,cognom2,nom"
            )
        }
)
public class Soci implements Serializable {

    @Id
    @TableGenerator(name = "gen_clau_table", table = "comptadors",
            pkColumnName = "taula",
            valueColumnName = "comptador",
            pkColumnValue = "soci",
            initialValue=100,
            allocationSize = 1
    )
    @GeneratedValue(strategy=GenerationType.TABLE, generator="gen_clau_table")
    private int codi;           // Estrictament positiu - GESTIONAT per JPA
    @Basic(optional = false)
    @Column(nullable = false, length = 30)
    private String cognom1;     // Obligatori i no buit
    @Column(length = 30)
    private String cognom2;     // No obligatori o buit
    @Basic(optional = false)
    @Column(nullable = false, length = 30)
    private String nom;         // Obligatori i no buit
    @Basic(optional = false)
    @Column(name = "data_naix", nullable = false)
    private Date dataNaix;     // Obligatori
//    @Column(nullable = false, columnDefinition = "char check (sexe in ('M','F'))")
    // La marca anterior no funciona en MySQL, per la sintaxi "create table" que genera Hibernate
    @Basic(optional = false)
    @Column(columnDefinition = "char check (sexe in ('M','F'))")
    private char sexe;          // (M)ale / (F)emale

    protected Soci() {
    }

    public Soci(String cognom1, String nom, Date dataNaix, char sexe) {
        setCognom1(cognom1);
        setNom(nom);
        setDataNaix(dataNaix);
        setSexe(sexe);
    }

    public int getCodi() {
        return codi;
    }

    public String getCognom1() {
        return cognom1;
    }

    public  void setCognom1(String cognom1) {
        if (cognom1 == null || cognom1.length() == 0) {
            throw new SociException("Cognom1 obligatori i no buit");
        }
        this.cognom1 = cognom1;
    }

    public String getCognom2() {
        return cognom2;
    }

    public void setCognom2(String cognom2) {
        if (cognom2 != null && cognom2.length() == 0) {
            throw new SociException("Cognom2 null o amb contingut");
        }
        this.cognom2 = cognom2;
    }

    public String getNom() {
        return nom;
    }

    public  void setNom(String nom) {
        if (nom == null || nom.length() == 0) {
            throw new SociException("Nom obligatori i no buit");
        }
        this.nom = nom;
    }

    public Date getDataNaix() {
        return dataNaix;
    }

    public  void setDataNaix(Date dataNaix) {
        if (dataNaix == null) {
            throw new SociException("Data de naixement obligatòria");
        }
        this.dataNaix = (Date) dataNaix.clone();
    }

    public char getSexe() {
        return sexe;
    }

    public  void setSexe(char sexe) {
        sexe = Character.toUpperCase(sexe);
        if (sexe != 'M' && sexe != 'F') {
            throw new SociException("Valors vàlids per sexe: (M)ale / (F)emale");
        }
        this.sexe = sexe;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.codi;
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
        final Soci other = (Soci) obj;
        return this.codi == other.codi;
    }

    @Override
    public String toString() {
        return "Soci{" + "codi=" + codi + ", cognom1=" + cognom1 + ", cognom2=" + cognom2 + ", nom=" + nom + ", dataNaix=" + dataNaix + ", sexe=" + sexe + '}';
    }

}
