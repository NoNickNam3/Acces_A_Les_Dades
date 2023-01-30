/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import java.util.Objects;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Usuari
 */
@Entity
public class Persona {

    @Id
    @Column(length = 15)
    private String dni;

    @Basic(optional = false)
    @Column(length = 50, nullable = false)
    private String nom;

    @Embedded
    // Si es vol sobreescriure els nom de les columnes que "s'hereten" 
    // de la definició de la classe CorreuElectronic
    // En cas d'utilitzar-ho, indicar també la longitud del camp (si és String)
    @AttributeOverrides({
        @AttributeOverride(name = "correu", column = @Column(name="e_mail",length=320))
    })    
    private CorreuElectronic correuElectronic;

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

    public CorreuElectronic getCorreuElectronic() {
        return correuElectronic;
    }

    public void setCorreuElectronic(CorreuElectronic correuElectronic) {
        this.correuElectronic = correuElectronic;
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
        return "Persona{" + "dni=" + dni + ", nom=" + nom + ", correuElectronic=" + correuElectronic + '}';
    }

}
