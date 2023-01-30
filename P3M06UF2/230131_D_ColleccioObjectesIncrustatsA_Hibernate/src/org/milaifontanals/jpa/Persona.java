/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;
import javax.persistence.AttributeOverride;
import javax.persistence.AttributeOverrides;
import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OrderColumn;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Usuari
 */
@Entity
public class Persona {
    @Id
    @Column(length=15)
    private String dni;
    
    @Basic(optional=false)
    @Column(length=50, nullable=false)
    private String nom;

    @ElementCollection
    // En aquest cas, els elements de la col·leccio són incrustats
    // Si es vol sobreescriure els nom de les columnes que "s'hereten" 
    // de la definició de la classe CorreuElectronic
    // En cas d'utilitzar-ho, indicar també la longitud del camp (si és String)
    @AttributeOverrides({
        @AttributeOverride(name = "correu", column = @Column(name="e_mail", length=320))
    })
    @CollectionTable(name="persona_correus",
            joinColumns = @JoinColumn(name="dni",
                    foreignKey= @ForeignKey(name="persona_correus_fk_dni")),
            uniqueConstraints = @UniqueConstraint(columnNames={"dni","e_mail"},
                    name="persona_correus_un_dni_mail"))
    @OrderColumn(name="ordre", nullable=false, columnDefinition="numeric(2)")
    private List<CorreuElectronic> correusElectronics = new ArrayList();
    
    protected Persona() {
    }

    public Persona(String dni, String nom) {
        this.dni = dni;
        this.nom = nom;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }
    
    /**
     * 
     * @param correu
     * @return Cert si l'ha pogut afegir i fals en cas contrari
     */
    public boolean addCorreuElectronic(CorreuElectronic correu) {
        if (!correusElectronics.contains(correu)) {
            correusElectronics.add(correu);
            return true;
        } else {
            return false;
        }
    }

    public Iterator<CorreuElectronic> iteCorreusElectronics() {
        return Collections.unmodifiableCollection(correusElectronics).iterator();
    }
    
    public boolean removeCorreuElectronic(CorreuElectronic correu) {
        return correusElectronics.remove(correu);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 53 * hash + Objects.hashCode(this.dni);
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
        final Persona other = (Persona) obj;
        if (!Objects.equals(this.dni, other.dni)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Persona{" + "dni=" + dni + ", nom=" + nom + ", correusElectronics=" + correusElectronics + '}';
    }

    
}
