/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import java.util.Calendar;
import java.util.GregorianCalendar;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Temporal;
import static javax.persistence.TemporalType.DATE;

/**
 *
 * @author Usuari
 */
@Entity
public class Alumne extends Persona{
    @Basic(optional=false)
    @Column(name="data_naixement", nullable=false)
    @Temporal(DATE)
    Calendar dataNaixement;

    protected Alumne() {
    }

    public Alumne(String dni, String nom, GregorianCalendar dataNaixement) {
        super(dni, nom);
        this.dataNaixement = dataNaixement;
    }

    public Calendar getDataNaixement() {
        return dataNaixement;
    }

    public void setDataNaixement(Calendar dataNaixement) {
        this.dataNaixement = dataNaixement;
    }

    @Override
    public String toString() {
        return "Alumne{" + "dni=" + this.getDni() + ", nom=" + this.getNom() + ", dataNaixement=" + dataNaixement +'}';
    }
    
}
