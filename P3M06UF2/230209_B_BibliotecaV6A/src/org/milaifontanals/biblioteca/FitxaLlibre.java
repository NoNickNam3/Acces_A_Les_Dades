/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.biblioteca;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

@Entity
@Table(name = "f_llibre")
@DiscriminatorValue("L")
public class FitxaLlibre extends Fitxa {
    // És serializable per heretar de Fitxa que ja ho es

    @Column(length = 30)
    private String editorial;   // Ha de permetre null
    @Column(length = 13)
    private String isbn;        // Ha de ser: null o ISBN-10 o ISBN-13
    
    @ManyToMany(cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "autoria", // nom de la taula 
            joinColumns = @JoinColumn(name = "llibre", referencedColumnName = "referencia",
                    foreignKey = @ForeignKey(name = "autoria_fk_f_llibre")), // def. columna "llibre"
            inverseJoinColumns = @JoinColumn(name = "autor", referencedColumnName = "id",
                    foreignKey = @ForeignKey(name = "autoria_fk_autor")), // def. columna "autor"
            uniqueConstraints = @UniqueConstraint(columnNames = {"llibre", "autor"},
            name = "autoria_pk_llibre_autor") // def PK
    )
    private List<Autor> autors = new ArrayList();

    protected FitxaLlibre() {
//        System.out.println("Entro CONSTRUCTOR FitxaLlibre");
    }

    public FitxaLlibre(String referencia, String titol) {
        super(referencia, titol);
        isbn = "";
    }

    public FitxaLlibre(String referencia, String titol,
            String editorial, String isbn) {
        super(referencia, titol);
        // Alternativament: this(referencia,titol)
        this.editorial = editorial;
        assignIsbn(isbn);
    }

    public FitxaLlibre(String referencia, String titol, Boolean esDeixa, Date momentCreacio, Date momentModificacio, String editorial, String isbn) {
        super(referencia, titol, esDeixa, momentCreacio, momentModificacio);
        this.editorial = editorial;
        assignIsbn(isbn);
    }

    public String getEditorial() {
        return editorial;
    }

    public void setEditorial(String editorial) {
        this.editorial = editorial;
        setMomentModificacio();
    }

    public String getIsbn() {
        return isbn;
    }

    private void assignIsbn(String isbn) {
        if (isbn != null && isbn.length() != 10 && isbn.length() != 13) {
            throw new FitxaException("L'ISBN ha de ser null o ISBN-10 o ISBN-13", 'I', isbn);
        }
        // Comprovar si és ISBN-10 o ISBN-13...
        this.isbn = isbn;
    }

    public void setIsbn(String isbn) {
        assignIsbn(isbn);
        setMomentModificacio();
    }

    public Iterator<Autor> iteAutors() {
        return Collections.unmodifiableCollection(autors).iterator();
    }

    public void addAutor(Autor a) {
        if (a==null) {
            throw new RuntimeException("Intent d'afegir autor null");
        }
        if (!autors.contains(a)) {
            autors.add(a);
            a.addLlibre(this);
        }
    }

    public void removeAutor(Autor a) {
        if (a==null) {
            throw new RuntimeException("Intent d'eliminar autor null");
        }
       if (autors.contains(a)) {
            autors.remove(a);
            a.removeLlibre(this);
        }
    }

    @Override
    public void visualitzar() {
        System.out.println("Soc un llibre!!!");
        super.visualitzar(); // No estem obligats!!!
        System.out.println("\tEditorial............: " + editorial);
        System.out.println("\tISBN.................: " + isbn);
    }

    @Override
    public String toString() {
        return "Soc llibre! Ref: " + getReferencia() + " - Títol: " + getTitol() + " - ISBN: " + isbn;
    }

}
