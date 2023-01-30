/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Usuari
 */
@Embeddable
/**
 * Cada dirección de e-mail esta compuesta de 2 partes. La parte local que es el
 * nombre que vienen antes de la @, y la parte del dominio que es lo que viene
 * después. Por ejemplo en luke @ soytupadre.com, la parte local es luke y la
 * parte del dominio es soytupadre.com
 *
 * Cada una de estas partes tiene un limite:
 *
 * La parte local no puede exceder los 64 caracteres. La parte del dominio no
 * puede ser más de 255 caracteres. Sumadas ambas partes, la longitud máxima es
 * 320 caracteres.
 */
public class CorreuElectronic {

    @Column(length = 320, nullable=false)
    private String correu;

    private static Pattern pattern = Pattern.compile("^(.+)@(.+)$");

    protected CorreuElectronic() {
    }

    public CorreuElectronic(String correu) {
        setCorreu(correu);
    }

    public String getCorreu() {
        return correu;
    }

    public void setCorreu(String correu) {
        Matcher matcher = pattern.matcher(correu);
        if (!matcher.matches() || correu.length()>320 || correu.substring(0,correu.indexOf('@')).length()>64) {
            throw new RuntimeException(correu + " no és un correu electrònic vàlid");
        }
        this.correu = correu.toLowerCase();
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 29 * hash + Objects.hashCode(this.correu);
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
        final CorreuElectronic other = (CorreuElectronic) obj;
        if (!Objects.equals(this.correu, other.correu)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "CorreuElectronic{" + "correu=" + correu + '}';
    }

}
