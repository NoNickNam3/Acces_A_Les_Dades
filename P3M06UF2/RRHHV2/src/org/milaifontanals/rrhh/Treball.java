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
import java.util.Objects;

/**
 *
 * @author Usuari
 */
public class Treball implements Serializable {

    private String id;      // Obligatori
    private String titol;   // Obligatori 
    private Integer salariMinim;       // No obligatori
    private Integer salariMaxim;       // No obligatori
    private List<Contracte> contractes = new ArrayList();

    protected Treball() {

    }

    public Treball(String id, String titol) {
        setId(id);
        setTitol(titol);
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        this.id = id;
    }

    public String getTitol() {
        return titol;
    }

    public void setTitol(String titol) {
        this.titol = titol;
    }

    public Integer getSalariMinim() {
        return salariMinim;
    }

    public void setSalariMinim(Integer salariMinim) {
        this.salariMinim = salariMinim;
    }

    public Integer getSalariMaxim() {
        return salariMaxim;
    }

    public void setSalariMaxim(Integer salariMaxim) {
        this.salariMaxim = salariMaxim;
    }

    Iterator<Contracte> iteContractes () {
        return contractes.iterator();
    }
    
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 11 * hash + Objects.hashCode(this.id);
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
        final Treball other = (Treball) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

};
