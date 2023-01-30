/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

/**
 *
 * @author Usuari
 */
@Entity
@DiscriminatorValue("PR")
public class Professor extends Persona {

    @Basic(optional = false)
    @Column(name = "es_durillo")
    // No podem indicar nullable=false per què aquesta columna ha de permetre
    // valors nuls per als objecte Alumne que es guarden a la mateixa taula
    boolean esDurillo;

    protected Professor() {

    }

    public Professor(String dni, String nom, Boolean esDurillo) {
        super(dni, nom);
        this.esDurillo = esDurillo;
    }

    public Boolean getEsDurillo() {
        return esDurillo;
    }

    public void setEsDurillo(Boolean esDurillo) {
        this.esDurillo = esDurillo;
    }

    @Override
    public String toString() {
        return "Professor{" + "dni=" + this.getDni() + ", nom=" + this.getNom() + ", esDurillo=" + esDurillo + '}';
    }

}
