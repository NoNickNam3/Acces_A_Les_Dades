/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.rrhh;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.PreRemove;
import javax.persistence.Table;

/**
 *
 * @author Usuari
 */
@Entity
@Table(name = "DEPARTMENTS")
@Access(AccessType.FIELD)
public class Departament implements Serializable {

    @Id
    @Column(name = "DEPARTMENT_ID", columnDefinition = "NUMBER(4,0)")
    private int id;             // Obligatori

    @Basic(optional = false)
    @Column(name = "DEPARTMENT_NAME", length = 30, nullable = false)
    private String nom;         // Obligatori

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "MANAGER_ID",
            foreignKey = @ForeignKey(name = "DEPT_MGR_FK", 
                    foreignKeyDefinition="FOREIGN KEY(MANAGER_ID) REFERENCES EMPLOYEES")
    )
    private Empleat cap;        // No obligatori

    @OneToMany(mappedBy="departament", cascade=CascadeType.PERSIST)
    private List<Empleat> empleats = new ArrayList();

    @PreRemove
    private void posarNullEnEmpleats() {
        empleats.forEach(e -> e.setDepartament(null));
    }
            
    protected Departament() {

    }

    public Departament(int id, String nom) {
        setId(id);
        setNom(nom);
    }

    public int getId() {
        return id;
    }

    private void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public Empleat getCap() {
        return cap;
    }

    public void setCap(Empleat cap) {
        this.cap = cap;
    }

    public List<Empleat> getEmpleats() {
        return empleats;
    }

    public void setEmpleats(List<Empleat> empleats) {
        this.empleats = empleats;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + this.id;
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
        final Departament other = (Departament) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    Iterator<Empleat> iteEmpleats() {
        return empleats.iterator();
    }

    public void addEmpleat(Empleat e) {
        if (!empleats.contains(e)) {
            empleats.add(e);
            if (e.getDepartament() != this) {
                if (e.getDepartament() != null) {
                    e.getDepartament().empleats.remove(e);
                }
                e.setDepartament(this);
            }
        }
    }

    public void removeEmpleat(Empleat e) {
        if (empleats.contains(e)) {
            empleats.remove(e);
            if (e.getDepartament() == this) {
                e.setDepartament(null);
            }
        }
    }

};
