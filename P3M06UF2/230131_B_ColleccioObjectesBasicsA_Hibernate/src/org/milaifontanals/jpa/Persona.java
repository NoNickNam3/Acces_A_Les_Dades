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
import javax.persistence.Basic;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.UniqueConstraint;

/**
 *
 * @author Usuari
 */
@Entity
public class Persona {

    @Id
    @Column(length = 15)
    private String dni;

    @Basic(optional = false)
    @Column(length = 50, nullable = false)
    private String nom;

    @ElementCollection
    @CollectionTable(name = "persona_telefons",
            joinColumns = @JoinColumn(name = "dni",
                    //            joinColumns = @JoinColumn(name="dni",referencedColumnName="dni",
                    //          La clàusula "referencecColumnName" cal usar-la si la clau forana
                    //          no ésfa referència a la PK de la taula referenciada.
                    foreignKey = @ForeignKey(name = "persona_telefons_fk_dni")),
            uniqueConstraints = @UniqueConstraint(columnNames = {"dni", "telefon"},
            name = "persona_telefons_un_dni_tel"))
    @Column(name = "telefon", length = 15, nullable = false)
    private List<String> telefons = new ArrayList();

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
     * @param tel
     * @return Cert si l'ha pogut afegir i fals en cas contrari
     */
    public boolean addTelefon(String tel) {
        if (!telefons.contains(tel)) {
            telefons.add(tel);
            return true;
        } else {
            return false;
        }
    }

    public Iterator<String> iteTelefons() {
        return Collections.unmodifiableList(telefons).iterator();
    }

    /**
     * 
     * @param tel
     * @return true si l'ha pogut eliminar i false si no hi era
     */
    public boolean removeTelefon(String tel) {
        return telefons.remove(tel);
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
        return "Persona{" + "dni=" + dni + ", nom=" + nom + ", tels=" + telefons + '}';
    }

}
