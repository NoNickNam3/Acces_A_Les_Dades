/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Usuari
 */
public class Color implements Serializable {

    private int red;
    private int green;
    private int blue;
    private String description;

    protected Color() {

    }

    public Color(int red, int green, int blue, String description) {
        setRed(red);
        setGreen(green);
        setBlue(blue);
        setDescription(description);
    }

    public int getRed() {
        return red;
    }

    public void setRed(int red) {
        if (red<0 || red>255) {
            throw new RuntimeException("Valor \"red\" obligatori entre 0 i 255");
        }
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    public void setGreen(int green) {
        if (green<0 || green>255) {
            throw new RuntimeException("Valor \"green\" obligatori entre 0 i 255");
        }
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    public void setBlue(int blue) {
        if (blue<0 || blue>255) {
            throw new RuntimeException("Valor \"blue\" obligatori entre 0 i 255");
        }
        this.blue = blue;
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
        hash = 79 * hash + this.red;
        hash = 79 * hash + this.green;
        hash = 79 * hash + this.blue;
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
        if (this.red != other.red) {
            return false;
        }
        if (this.green != other.green) {
            return false;
        }
        if (this.blue != other.blue) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Color{" + "red=" + red + ", green=" + green + ", blue=" + blue + ", description=" + description + '}';
    }

}
