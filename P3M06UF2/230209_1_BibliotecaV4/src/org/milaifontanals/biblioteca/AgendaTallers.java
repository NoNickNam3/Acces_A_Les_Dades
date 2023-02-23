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
public class AgendaTallers implements Serializable {

    private String codiTaller;
    private Taller taller;              // Obligatori
    private Calendar momentInici;       // Obligatori
    private String detalls;             // Optatiu o amb contingut

    protected AgendaTallers() {
    }

    public AgendaTallers(Taller taller, Calendar momentInici) {
       setTaller(taller);
       setMomentInici(momentInici);
    }

    public Taller getTaller() {
        return taller;
    }

        private void setTaller(Taller taller) {
        if (taller == null) {
            throw new RuntimeException("Taller obligatori");
        }

        this.taller = taller;
        this.codiTaller = taller.getCodi();
    }

    public Calendar getMomentInici() {
        return momentInici;
    }

    private void setMomentInici(Calendar momentInici) {
        if (momentInici == null) {
            throw new RuntimeException("Moment d'inici obligatori");
        }
        this.momentInici = momentInici;
    }

    public String getDetalls() {
        return detalls;
    }

    public void setDetalls(String detalls) {
        this.detalls = detalls;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.taller);
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
        final AgendaTallers other = (AgendaTallers) obj;
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
        return "AgendaTallers{" + "taller=" + taller + ", momentInici=" + momentInici + ", detalls=" + detalls + '}';
    }
    
    
    
}
