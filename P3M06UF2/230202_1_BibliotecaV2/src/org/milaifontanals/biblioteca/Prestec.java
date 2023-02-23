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
public class Prestec implements Serializable {
    private long numero;    // Obligatori - Immutable - GESTIONAT per JPA
    private Soci soci;  // Obligatori - Immutable
    private Fitxa fitxa;    // Obligatori - Immutable
    private Date momentPrestec;   // Obligatori   (moment temporal) - Immutable
    private Date momentRetorn;    // No obligatori (moment temporal) - Posterior a momentPrestec
                                // null = Pendent de retorn

    protected Prestec() {
        
    }
    
    public Prestec(Soci soci, Fitxa fitxa, Date momentPrestec) {
        setSoci(soci);
        setFitxa(fitxa);
        setMomentPrestec(momentPrestec);
    }

    public long getNumero() {
        return numero;
    }

    public Soci getSoci() {
        return soci;
    }
    
    private  void setSoci(Soci soci) {
        if (soci==null) {
            throw new PrestecException("En un préstec, el soci és obligatori");
        }
        this.soci = soci;
    }

    public Fitxa getFitxa() {
        return fitxa;
    }

    private  void setFitxa(Fitxa fitxa) {
        if (fitxa==null) {
            throw new PrestecException("En un préstec, la fitxa és obligatòria");
        }
        this.fitxa = fitxa;
    }

    public Date getMomentPrestec() {
        return momentPrestec;
    }

    private  void setMomentPrestec(Date momentPrestec) {
        if (momentPrestec==null) {
            throw new PrestecException("En un préstec, la data de préstec és obligatòria");
        }
        this.momentPrestec = momentPrestec;
    }

    public Date getMomentRetorn() {
        return momentRetorn;
    }

    public void setMomentRetorn(Date momentRetorn) {
        if (momentRetorn!=null && momentRetorn.compareTo(momentPrestec)<=0) {
           throw new PrestecException("El retorn d'un préstec ha de ser posterior al moment en què s'ha efectuat el préstec");
        }
        this.momentRetorn = momentRetorn;
    }

    @Override
    public String toString() {
        return "Prestec{" + "numero=" + numero + ", soci=" + soci + ", fitxa=" + fitxa + ", momentPrestec=" + momentPrestec + ", momentRetorn=" + momentRetorn + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + (int) (this.numero ^ (this.numero >>> 32));
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
        final Prestec other = (Prestec) obj;
        if (this.numero != other.numero) {
            return false;
        }
        return true;
    }



}
