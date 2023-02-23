/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.biblioteca;

import java.util.Objects;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author Usuari
 */
@Embeddable
public class Tutoria {

//    @Basic(optional = false)
//    @Column(length = 40, nullable = false)
//    Les dues anotacions anteriors obliguen que allà on s'incrusti la classe
//    Tutoria, tutor1 sempre contingui valor i aixo NO interessa en la taula
//    Soci, doncs un soci pot no tenir tutoria i columna tutor1 ha de permetre NULL
    @Column(length = 40)
    private String tutor1;      // Obligatori i amb contingut

    @Column(length = 40)
    private String tutor2;      // No obligatori o amb contingut

    protected Tutoria() {

    }

    public Tutoria(String tutor1, String tutor2) {
        setTutor1(tutor1);
        setTutor2(tutor2);
    }

    public String getTutor1() {
        return tutor1;
    }

    public void setTutor1(String tutor1) {
        if (tutor1 == null || tutor1.length() == 0) {
            throw new RuntimeException("Tutor1 obligatori i amb contingut");
        }
        this.tutor1 = tutor1;
    }

    public String getTutor2() {
        return tutor2;
    }

    public void setTutor2(String tutor2) {
        if (tutor2 != null && tutor2.length() == 0) {
            throw new RuntimeException("Tutor2 no obligatori o amb contingut");
        }
        this.tutor2 = tutor2;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.tutor1);
        hash = 17 * hash + Objects.hashCode(this.tutor2);
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
        final Tutoria other = (Tutoria) obj;
        if (!Objects.equals(this.tutor1.toLowerCase(), other.tutor1.toLowerCase())) {
            return false;
        }
        if (this.tutor2 != null && other.tutor2 != null) {
            if (!Objects.equals(this.tutor2.toLowerCase(), other.tutor2.toLowerCase())) {
                return false;
            }
        } else {
            return Objects.equals(this.tutor2, other.tutor2);
        }
        return true;
    }

    @Override
    public String toString() {
        return "Tutoria{" + "tutor1=" + tutor1 + ", tutor2=" + tutor2 + '}';
    }

}
