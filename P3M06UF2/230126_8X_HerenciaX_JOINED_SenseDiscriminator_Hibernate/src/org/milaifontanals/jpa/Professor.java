/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

/**
 *
 * @author Usuari
 */
public class Professor extends Persona {

    Boolean esDurillo;

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
