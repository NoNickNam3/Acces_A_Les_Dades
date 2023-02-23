/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.biblioteca;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Usuari
 */
@Embeddable
public class Telefon implements Serializable {

    @Basic(optional=false)
    @Column(length=15, nullable=false)
    private String numero;      // Obligatori i amb contingut
    
    @Basic(optional = false)
    @Column(columnDefinition = "char check (tipus in ('M','F'))")
    private char tipus;         // Valors possibles: (F)ix / (M)obil

    protected Telefon() {

    }

    public Telefon(String numero, char tipus) {
        setNumero(numero);
        setTipus(tipus);
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        if (numero==null || numero.length()==0) {
            throw new RuntimeException("Numero de telèfon obligatori i amb contingut");
        }
        this.numero = numero.toLowerCase();
    }

    public char getTipus() {
        return tipus;
    }

    public void setTipus(char tipus) {
        tipus = Character.toUpperCase(tipus);
        if (tipus!='F' && tipus!='M') {
            throw new RuntimeException("Tipus de telèfon: (F)ix / (M)obil");
        }
        this.tipus = tipus;
    }

    @Override
    public String toString() {
        return "Telefon{" + "numero=" + numero + ", tipus=" + tipus + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 53 * hash + Objects.hashCode(this.numero);
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
        final Telefon other = (Telefon) obj;
        if (!Objects.equals(this.numero, other.numero)) {
            return false;
        }
        return true;
    }

};
