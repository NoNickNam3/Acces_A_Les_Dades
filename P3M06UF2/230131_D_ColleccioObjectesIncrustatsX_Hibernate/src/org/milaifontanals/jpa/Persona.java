/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Usuari
 */
public class Persona {
    private String dni;
    private String nom;
    private List<CorreuElectronic> correusElectronics = new ArrayList();
    
    protected Persona() {
    }

    public Persona(String dni, String nom) {
        this.dni = dni;
        this.nom = nom;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    /**
     * 
     * @param correu
     * @return Cert si l'ha pogut afegir i fals en cas contrari
     */
    public boolean addCorreuElectronic(CorreuElectronic correu) {
        if (!correusElectronics.contains(correu)) {
            correusElectronics.add(correu);
            return true;
        } else {
            return false;
        }
    }

    public Iterator<CorreuElectronic> iteCorreusElectronics() {
        return Collections.unmodifiableCollection(correusElectronics).iterator();
    }
    
    public boolean removeCorreuElectronic(CorreuElectronic correu) {
        return correusElectronics.remove(correu);
    }


    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.dni);
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
        final Persona other = (Persona) obj;
        if (!Objects.equals(this.dni, other.dni)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persona{" + "dni=" + dni + ", nom=" + nom + ", correusElectronics=" + correusElectronics + '}';
    }
    
}
