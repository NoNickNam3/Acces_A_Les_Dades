/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 *
 * @author Usuari
 */
@Entity
@Table(name="clau_sequence1")
public class ClauSequence1ViaAnotacions {
    @Id
    @SequenceGenerator(name="gen_clau_seq", sequenceName="sequence1_clau",
            // Valor inicial (si no es posa, agafa 1):
            initialValue=1001,
            // Per a que no RESERVI varis valors a cada accés a la taula de comptadors...
            // Si no es posa, agafa 50!!!
            allocationSize=1)
    @GeneratedValue(strategy=GenerationType.SEQUENCE, generator="gen_clau_seq")
    Integer codi;
    @Basic(optional=false)
    @Column(length=30, nullable=false)
    String descripcio;

    protected ClauSequence1ViaAnotacions() {
    }

    public ClauSequence1ViaAnotacions(String descripcio) {
        this.descripcio = descripcio;
    }

    public Integer getCodi() {
        return codi;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.codi);
        hash = 89 * hash + Objects.hashCode(this.descripcio);
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
        final ClauSequence1ViaAnotacions other = (ClauSequence1ViaAnotacions) obj;
        if (!Objects.equals(this.descripcio, other.descripcio)) {
            return false;
        }
        if (!Objects.equals(this.codi, other.codi)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Registre{" + "codi=" + codi + ", descripcio=" + descripcio + '}';
    }

    

}
