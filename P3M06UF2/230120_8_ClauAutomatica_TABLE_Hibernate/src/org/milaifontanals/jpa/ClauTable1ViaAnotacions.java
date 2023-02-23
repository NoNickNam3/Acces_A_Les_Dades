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
import javax.persistence.Table;
import javax.persistence.TableGenerator;

/**
 *
 * @author Usuari
 */
@Entity
@Table(name = "clau_table1")
public class ClauTable1ViaAnotacions {

    @Id
    @TableGenerator(name = "gen_clau_table", table = "clau_comptadors",
            // Nom de la columna de la taula comptadors que conté nom identificatiu
            // de la taula a la que correspon el comptador:
            pkColumnName = "taula",
            // Nom de la columna de la taula comptadors que conté el valor del comptador:
            valueColumnName = "comptador",
            // Nom identificatiu de la taula a la que correspon el comptador
            // (pot ser el nom de la taula o qualsevol nom que la identifiqui):
            pkColumnValue = "table1",
            // Valor inicial (si no es posa, agafa 1):
            initialValue=1001,      // És el darrer valor; Començarà amb 1002!
            // Per a que l'eina JPA no RESERVI varis valors a cada accés a la taula de comptadors...
            // Si no es posa, agafa 50!!!
            allocationSize = 1
            // No hi ha manera d'indicar SALT => Són números correlatius
    )
    // Els valors de pkColumnName i valueColumnName haurien de ser els mateixos
    // per les diferents taules amb aquesta mateixa taula com a TableGenerator
    @GeneratedValue(strategy=GenerationType.TABLE, generator="gen_clau_table")
    Integer codi;
    @Basic(optional = false)
    @Column(length = 30, nullable = false)
    String descripcio;

    protected ClauTable1ViaAnotacions() {
    }

    public ClauTable1ViaAnotacions(String descripcio) {
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
        final ClauTable1ViaAnotacions other = (ClauTable1ViaAnotacions) obj;
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
