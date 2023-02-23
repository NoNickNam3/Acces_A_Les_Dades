/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.biblioteca;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Usuari
 */
public class Autor implements Serializable {

    private String id;      // Obligatori
    private String nom;     // Obligatori

    protected Autor() {
    }

    public Autor(String id, String nom) {
        setId(id);
        setNom(nom);
    }

    public String getId() {
        return id;
    }

    private void setId(String id) {
        if (id == null || id.length() > 4) {
            throw new RuntimeException("Id d'autor obligatori i de longitud màxima 4");
        }
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if (nom == null || nom.length() > 40) {
            throw new RuntimeException("Nom d'autor obligatori i de longitud màxima 40");
        }
        this.nom = nom;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.id);
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
        final Autor other = (Autor) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Autor{" + "id=" + id + ", nom=" + nom + '}';
    }

}
