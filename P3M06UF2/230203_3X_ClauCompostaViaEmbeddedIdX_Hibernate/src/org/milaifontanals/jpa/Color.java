/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import java.io.Serializable;
import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

/**
 *
 * @author Usuari
 */
public class Color implements Serializable {

    private ColorId colorId;
    private String description;

    protected Color() {

    }

    public Color(int red, int green, int blue, String description) {
        colorId = new ColorId(red,green,blue);
        setDescription(description);
    }

    public ColorId getColorId() {
        return colorId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        if (description == null || description.length() == 0) {
            throw new RuntimeException("Description obligatori i amb contingut");
        }
        this.description = description;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.colorId);
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
        final Color other = (Color) obj;
        if (!Objects.equals(this.colorId, other.colorId)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Color{" + "colorId=" + colorId + ", description=" + description + '}';
    }

}
