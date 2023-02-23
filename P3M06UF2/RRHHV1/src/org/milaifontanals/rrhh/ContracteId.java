/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.rrhh;

import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

/**
 *
 * @author Usuari
 */
public class ContracteId implements Serializable {

    private int idEmpleat;        // Obligatori
    private Date dataInici;         // Obligatori

    protected ContracteId() {

    }

    public ContracteId(int idEmpleat, Date dataInici) {
        this.idEmpleat = idEmpleat;
        this.dataInici = dataInici;
    }
    
    public ContracteId(Empleat empleat, Date dataInici) {
        this.idEmpleat = empleat.getId();
        this.dataInici = dataInici;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + this.idEmpleat;
        hash = 59 * hash + Objects.hashCode(this.dataInici);
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
        final ContracteId other = (ContracteId) obj;
        if (this.idEmpleat != other.idEmpleat) {
            return false;
        }
        if (!Objects.equals(this.dataInici, other.dataInici)) {
            return false;
        }
        return true;
    }



}
