/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.biblioteca;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Usuari
 */
public class Soci implements Serializable {
    private int codi;           // Estrictament positiu    
    private String cognom1;     // Obligatori i no buit
    private String cognom2;     // No obligatori o buit
    private String nom;         // Obligatori i no buit
    private Date dataNaix;     // Obligatori
    private char sexe;          // (M)ale / (F)emale

    protected Soci() {
        
    }
    
    public Soci(int codi, String cognom1, String nom, Date dataNaix, char sexe) {
        setCodi(codi);
        setCognom1(cognom1);
        setNom(nom);
        setDataNaix(dataNaix);
        setSexe(sexe);
    }

    public int getCodi() {
        return codi;
    }

    private final void setCodi(int codi) {
        if (codi<=0) {
            throw new SociException("Codi de soci ha de ser estrictament positiu");
        }
        this.codi = codi;
    }

    public String getCognom1() {
        return cognom1;
    }

    public final void setCognom1(String cognom1) {
        if (cognom1==null || cognom1.length()==0) {
            throw new SociException("Cognom1 obligatori i no buit");
        }
        this.cognom1 = cognom1;
    }

    public String getCognom2() {
        return cognom2;
    }

    public void setCognom2(String cognom2) {
        if (cognom2!=null && cognom2.length()==0) {
            throw new SociException("Cognom2 null o amb contingut");
        }
        this.cognom2 = cognom2;
    }

    public String getNom() {
        return nom;
    }

    public final void setNom(String nom) {
        if (nom==null || nom.length()==0) {
            throw new SociException("Nom obligatori i no buit");
        }
        this.nom = nom;
    }

    public Date getDataNaix() {
        return dataNaix;
    }

    public final void setDataNaix(Date dataNaix) {
        if (dataNaix==null) {
            throw new SociException("Data de naixement obligatòria");
        }
        this.dataNaix = (Date) dataNaix.clone();
    }

    public char getSexe() {
        return sexe;
    }

    public final void setSexe(char sexe) {
        sexe = Character.toUpperCase(sexe);
        if (sexe!='M' && sexe!='F') {
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
