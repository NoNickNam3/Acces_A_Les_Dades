/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.biblioteca;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;

/**
 *
 * @author Ivan
 */
public class AgendaTallersIdentificador implements Serializable {

    private String codiTaller;
    private Calendar momentInici;

    public AgendaTallersIdentificador() {}
    
    public AgendaTallersIdentificador(String codiTaller, Calendar momentInici) {
        setCodiTaller(codiTaller);
        setMomentInici(momentInici);
    }
    
    public AgendaTallersIdentificador(Taller taller, Calendar momentInici) {
        setCodiTaller(taller.getCodi());
        setMomentInici(momentInici);
    }
    
    public String getCodiTaller() {
        return codiTaller;
    }

    public void setCodiTaller(String codiTaller) {
        this.codiTaller = codiTaller;
    }
    
    public Calendar getMomentInici() {
        return momentInici;
    }

    public void setMomentInici(Calendar momentInici) {
        this.momentInici = momentInici;
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
        
        final AgendaTallersIdentificador other = (AgendaTallersIdentificador) obj;
        if (!this.getCodiTaller().equals(other.getCodiTaller())) {
            return false;
        }
        
        if (this.getMomentInici() != other.getMomentInici()) {
            return false;
        }
        
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.getCodiTaller());
        hash = 59 * hash + Objects.hashCode(this.momentInici);
        return hash;
    }

    @Override
    public String toString() {
        return "AgendaTaller{" + "Taller=" + getCodiTaller() + ", Moment Inici=" + getMomentInici() + '}';
    }
    
}
