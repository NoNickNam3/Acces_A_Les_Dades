/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.milaifontanals.biblioteca;

import java.io.Serializable;
import java.util.Calendar;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Ivan
 */
@Entity
@IdClass(AgendaTallersIdentificador.class)
public class AgendaTallers implements Serializable{
    
    @Id
    @Column(length = 4, name = "codi_taller")
    private String codiTaller;
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST, fetch = FetchType.LAZY)
    @JoinColumn(name = "codi_taller", insertable = false, updatable = false, foreignKey = @ForeignKey(name = "agenda_taller_fk_taller"))
    private Taller taller;
    @Id
    @Column(name = "moment_inici")
    @Temporal(value = TemporalType.DATE)
    private Calendar momentInici;
    @Lob
    private String detalls;
    
    protected AgendaTallers(){}
    
    public AgendaTallers(Taller t, Calendar m, String d){
        setTaller(t);
        setCodiTaller(t.getCodi());
    }
    
    public Taller getTaller() {
        return taller;
    }

    public void setTaller(Taller taller) {
        this.taller = taller;
    }

    public Calendar getMomentInici() {
        return momentInici;
    }

    public void setMomentInici(Calendar momentInici) {
        this.momentInici = momentInici;
    }

    public String getDetalls() {
        return detalls;
    }

    public void setDetalls(String detalls) {
        this.detalls = detalls;
    }
    
    public String getCodiTaller() {
        return codiTaller;
    }

    public void setCodiTaller(String codiTaller) {
        this.codiTaller = codiTaller;
    }
}
