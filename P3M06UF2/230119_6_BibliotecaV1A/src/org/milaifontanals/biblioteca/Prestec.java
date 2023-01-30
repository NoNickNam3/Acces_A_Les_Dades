/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.biblioteca;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Prestec implements Serializable {

    @Id
    private long numero;    // Obligatori - Immutable

    @ManyToOne(optional = false,
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "soci", nullable = false,
            foreignKey = @ForeignKey(name = "prestec_fk_soci"))
    private Soci soci;      // Obligatori - Immutable

    @ManyToOne(optional = false,
            cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY
    )
    @JoinColumn(name = "fitxa", nullable = false,
            foreignKey = @ForeignKey(name = "prestec_fk_fitxa"))
    private Fitxa fitxa;    // Obligatori - Immutable

    @Basic(optional = false)
    @Column(name = "moment_prestec", nullable = false)
    private Date momentPrestec;   // Obligatori   (moment temporal) - Immutable

    @Column(name = "moment_retorn")
    private Date momentRetorn;    // No obligatori (moment temporal) - Posterior a momentPrestec
    // null = Pendent de retorn
    // Check interessant a nivell BD:
//    check (moment_retorn is null or moment_retorn>moment_prestec)
    // JPA 2.2 no permet definir constraints que afectin a vàries columnes de la BD

    protected Prestec() {

    }

    public Prestec(long numero, Soci soci, Fitxa fitxa, Date momentPrestec) {
        setNumero(numero);
        setSoci(soci);
        setFitxa(fitxa);
        setMomentPrestec(momentPrestec);
    }

    public final long getNumero() {
        return numero;
    }

    private final void setNumero(long numero) {
        if (numero <= 0) {
            throw new PrestecException("En un préstec, el número és estrictament positiu");
        }
        this.numero = numero;
    }

    public final Soci getSoci() {
        return soci;
    }

    private final void setSoci(Soci soci) {
        if (soci == null) {
            throw new PrestecException("En un préstec, el soci és obligatori");
        }
        this.soci = soci;
    }

    public final Fitxa getFitxa() {
        return fitxa;
    }

    private final void setFitxa(Fitxa fitxa) {
        if (fitxa == null) {
            throw new PrestecException("En un préstec, la fitxa és obligatòria");
        }
        this.fitxa = fitxa;
    }

    public final Date getMomentPrestec() {
        return momentPrestec;
    }

    private final void setMomentPrestec(Date momentPrestec) {
        if (momentPrestec == null) {
            throw new PrestecException("En un préstec, la data de préstec és obligatòria");
        }
        this.momentPrestec = momentPrestec;
    }

    public final Date getMomentRetorn() {
        return momentRetorn;
    }

    public final void setMomentRetorn(Date momentRetorn) {
        if (momentRetorn != null && momentRetorn.compareTo(momentPrestec) <= 0) {
            throw new PrestecException("El retorn d'un préstec ha de ser posterior al moment en què s'ha efectuat el préstec");
        }
        this.momentRetorn = momentRetorn;
    }

    @Override
    public String toString() {
        return "Prestec{" + "numero=" + numero + ", soci=" + soci + ", fitxa=" + fitxa + ", momentPrestec=" + momentPrestec + ", momentRetorn=" + momentRetorn + '}';
    }

    @Override
    public final int hashCode() {
        int hash = 3;
        hash = 47 * hash + (int) (this.numero ^ (this.numero >>> 32));
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
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
