/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.biblioteca;

import java.util.Date;

public class FitxaLlibre extends Fitxa {
    // És serializable per heretar de Fitxa que ja ho es

    private String editorial;   // Ha de permetre null
    private String isbn;        // Ha de ser: null o ISBN-10 o ISBN-13

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

    public final String getEditorial() {
        return editorial;
    }
    
    public final void setEditorial(String editorial) {
        this.editorial = editorial;
        setMomentModificacio();
    }

    public final String getIsbn() {
        return isbn;
    }

    private void assignIsbn(String isbn) {
        if (isbn!=null && isbn.length() != 10 && isbn.length() != 13) {
            throw new FitxaException("L'ISBN ha de ser null o ISBN-10 o ISBN-13",'I',isbn);
        }
        // Comprovar si és ISBN-10 o ISBN-13...
        this.isbn = isbn;
    }

    public final void setIsbn(String isbn) {
        assignIsbn(isbn);
        setMomentModificacio();
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
