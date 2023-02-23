/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.biblioteca;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

/**
 *
 * @author Usuari
 */
public class AgendaTallersId implements Serializable{
    private String codiTaller;
    private Calendar momentInici;

    protected AgendaTallersId() {
    }

    public AgendaTallersId(String codiTaller, Calendar momentInici) {
        this.codiTaller = codiTaller;
        this.momentInici = momentInici;
    }

    public AgendaTallersId(Taller taller, Calendar momentInici) {
        this(taller.getCodi(), momentInici);
    }
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + Objects.hashCode(this.codiTaller);
        hash = 67 * hash + Objects.hashCode(this.momentInici);
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
        final AgendaTallersId other = (AgendaTallersId) obj;
        if (!Objects.equals(this.codiTaller, other.codiTaller)) {
            return false;
        }
        if (!Objects.equals(this.momentInici, other.momentInici)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "AgendaTallersId{" + "codiTaller=" + codiTaller + ", momentInici=" + momentInici + '}';
    }
  
    
}
