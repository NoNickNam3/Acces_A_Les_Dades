/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.biblioteca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Collections;

public class Soci implements Serializable {
    private int codi;           // Estrictament positiu - GESTIONAT per JPA
    private String cognom1;     // Obligatori i no buit
    private String cognom2;     // No obligatori o buit
    private String nom;         // Obligatori i no buit
    private Date dataNaix;     // Obligatori
    private char sexe;          // (M)ale / (F)emale
    private Tutoria tutoria;    // No obligatori
    private List<Telefon> telefons = new ArrayList();
    private List<Prestec> prestecs = new ArrayList();
    
    private List<AgendaTallers> tallers = new ArrayList();
    
    protected Soci() {
//        System.out.println("Entro CONSTRUCTOR Soci");
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

    public void setCognom1(String cognom1) {
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

    public void setNom(String nom) {
        if (nom == null || nom.length() == 0) {
            throw new SociException("Nom obligatori i no buit");
        }
        this.nom = nom;
    }

    public Date getDataNaix() {
        return dataNaix;
    }

    public void setDataNaix(Date dataNaix) {
        if (dataNaix == null) {
            throw new SociException("Data de naixement obligat??ria");
        }
        this.dataNaix = (Date) dataNaix.clone();
    }

    public char getSexe() {
        return sexe;
    }

    public void setSexe(char sexe) {
        sexe = Character.toUpperCase(sexe);
        if (sexe != 'M' && sexe != 'F') {
            throw new SociException("Valors v??lids per sexe: (M)ale / (F)emale");
        }
        this.sexe = sexe;
    }

    public Tutoria getTutoria() {
        return tutoria;
    }

    public void setTutoria(Tutoria tutoria) {
        this.tutoria = tutoria;
    }

    public boolean addTelefon(Telefon tel) {
        if (!telefons.contains(tel)) {
            telefons.add(tel);
            return true;
        } else {
            return false;
        }
    }

    public Iterator<Telefon> iteTelefons() {
        return Collections.unmodifiableCollection(telefons).iterator();
    }

    public boolean removeTelefon(Telefon tel) {
        return telefons.remove(tel);
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

    /**
     * Iterator que permet accedir als prestecs d'un soci.
     */
    public Iterator<Prestec> itePrestecs() {
        return prestecs.iterator();
    }
      
    /**
     * M??tode que assigna el pr??stec a la llista de pr??stecs del soci. Necessari
     * per poder afegir el pr??stec al soci en crear el prestec per?? sense
     * utilitat per al desenvolupament d'aplicacions, ja que un pr??stec t?? un
     * soci des de la seva creaci?? i ??s immutable. Per aix?? no ??s public.
     * L'utilitza el m??tode Prestec.setSoci(). Les classes del mateix paquet hi
     * tenen acc??s
     *
     * @param prestec
     */
    final void addPrestec(Prestec prestec) {
        prestecs.add(prestec);
    }
    
    public Iterator<AgendaTallers> iteTallers() {
        return Collections.unmodifiableCollection(tallers).iterator();
    }

    public void addTaller(AgendaTallers at) {
        if (at == null) {
            throw new RuntimeException("No es pot afegir una agendaTallers nul");
        }
        if (!tallers.contains(at)) {
            tallers.add(at);
            at.addSoci(this);
        }
    }

    public void removeTaller(AgendaTallers at) {
        if (tallers.contains(at)) {
            tallers.remove(at);
            at.removeSoci(this);
        }
    }
    
    
    
    
}
