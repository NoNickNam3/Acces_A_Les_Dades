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
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.TableGenerator;

@Entity
public class Prestec implements Serializable {

    @Id
    @TableGenerator(name = "gen_clau_table2", table = "comptador",
            pkColumnName = "taula",
            valueColumnName = "comptador",
            pkColumnValue = "prestec",
            initialValue = 100,
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "gen_clau_table2")
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
    // JPA 2.2 no permet definir constraints que afectin a v??ries columnes de la BD

    protected Prestec() {

    }

    public Prestec(Soci soci, Fitxa fitxa, Date momentPrestec) {
        setSoci(soci);
        setFitxa(fitxa);
        setMomentPrestec(momentPrestec);
    }

    public  long getNumero() {
        return numero;
    }

    public  Soci getSoci() {
        return soci;
    }

    private  void setSoci(Soci soci) {
        if (soci == null) {
            throw new PrestecException("En un pr??stec, el soci ??s obligatori");
        }
        this.soci = soci;
    }

    public  Fitxa getFitxa() {
        return fitxa;
    }

    private  void setFitxa(Fitxa fitxa) {
        if (fitxa == null) {
            throw new PrestecException("En un pr??stec, la fitxa ??s obligat??ria");
        }
        this.fitxa = fitxa;
    }

    public  Date getMomentPrestec() {
        return momentPrestec;
    }

    private  void setMomentPrestec(Date momentPrestec) {
        if (momentPrestec == null) {
            throw new PrestecException("En un pr??stec, la data de pr??stec ??s obligat??ria");
        }
        this.momentPrestec = momentPrestec;
    }

    public  Date getMomentRetorn() {
        return momentRetorn;
    }

    public  void setMomentRetorn(Date momentRetorn) {
        if (momentRetorn != null && momentRetorn.compareTo(momentPrestec) <= 0) {
            throw new PrestecException("El retorn d'un pr??stec ha de ser posterior al moment en qu?? s'ha efectuat el pr??stec");
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
