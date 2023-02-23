/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Embeddable;

/**
 *
 * @author Usuari
 */
public class ColorId implements Serializable {

    private int red;
    private int green;
    private int blue;

    protected ColorId() {

    }

    public ColorId(int red, int green, int blue) {
        this.red = red;
        this.green = green;
        this.blue = blue;
    }

    public int getRed() {
        return red;
    }

    private void setRed(int red) {
        if (red < 0 || red > 255) {
            throw new RuntimeException("Valor \"red\" obligatori entre 0 i 255");
        }
        this.red = red;
    }

    public int getGreen() {
        return green;
    }

    private void setGreen(int green) {
        if (green < 0 || green > 255) {
            throw new RuntimeException("Valor \"green\" obligatori entre 0 i 255");
        }
        this.green = green;
    }

    public int getBlue() {
        return blue;
    }

    private void setBlue(int blue) {
        if (blue < 0 || blue > 255) {
            throw new RuntimeException("Valor \"blue\" obligatori entre 0 i 255");
        }
        this.blue = blue;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 67 * hash + this.red;
        hash = 67 * hash + this.green;
        hash = 67 * hash + this.blue;
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
        final ColorId other = (ColorId) obj;
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
        return "ColorId{" + "red=" + red + ", green=" + green + ", blue=" + blue + '}';
    }

}
