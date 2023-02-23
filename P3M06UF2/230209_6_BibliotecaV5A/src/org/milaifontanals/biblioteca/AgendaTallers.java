/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.biblioteca;

import java.io.Serializable;
import java.util.Calendar;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Usuari
 */
@Entity
@Table(name="agenda_tallers")
@IdClass(AgendaTallersId.class)
public class AgendaTallers implements Serializable {
    @Id
    @Column(length=4, name="codi_taller")
    private String codiTaller;
    
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "codi_taller", insertable=false, updatable=false,
            foreignKey = @ForeignKey(name = "agenda_taller_fk_taller"))
    private Taller taller;              // Obligatori
    
    @Id
    @Column(name="moment_inici")
    @Temporal(value=TemporalType.DATE)
    private Calendar momentInici;       // Obligatori
    @Lob
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
