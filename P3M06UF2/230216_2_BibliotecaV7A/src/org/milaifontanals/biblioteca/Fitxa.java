/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.biblioteca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.DiscriminatorType;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.JOINED)
// En Hibernate NO és obligatòria una columna Discriminator - Es podria eliminar
@DiscriminatorColumn(name = "tipus", discriminatorType = DiscriminatorType.CHAR)
@DiscriminatorValue(" ")        // Per imperatiu legal, doncs MAI tindrem una fitxa (abstracta)
public abstract class Fitxa implements Comparable<Fitxa>, Serializable {

    @Id
    @Column(length = 10)
    private String referencia;          // Obligatòria i de 10 caràcters
    @Basic(optional = false)
    @Column(columnDefinition = "varchar(60) check(length(titol)>0)")
    private String titol;               // Obligatori no buit
    @Column(name = "es_deixa")
    private Boolean esDeixa;            // No obligatori
    @Basic(optional = false)
    @Column(nullable = false, updatable = false, name = "moment_creacio")
    private Date momentCreacio = new Date();  // Inicialitzada amb la data del sistema
    @Column(name = "moment_modificacio")
    private Date momentModificacio;  // Inicialitzada a NULL
    // Només apuntarà una data a partir del moment en que
    // es modifiqui per primera vegada
    @OneToMany(mappedBy = "fitxa")
    private List<Prestec> prestecs = new ArrayList();

    protected Fitxa() {
//        System.out.println("Entro CONSTRUCTOR Fitxa");
    }

    public Fitxa(String referencia, String titol) {
        assignReferencia(referencia);
        assignTitol(titol);
    }

    public Fitxa(String referencia, String titol, Boolean esDeixa) {
        assignReferencia(referencia);
        assignTitol(titol);
        assignEsDeixa(esDeixa);
    }

    public Fitxa(String referencia, String titol, Boolean esDeixa, Date momentCreacio, Date momentModificacio) {
        if (momentCreacio == null) {
            throw new FitxaException("Intent de creació de fitxa amb data de creació nul·la", 'C');
        }
        assignReferencia(referencia);
        assignTitol(titol);
        assignEsDeixa(esDeixa);
        this.momentCreacio = (Date) momentCreacio.clone();
        if (momentModificacio != null) {
            if (momentModificacio.compareTo(momentCreacio) <= 0) {
                throw new FitxaException("Intent de creació de fitxa amb data de modificació anterior a data de creació", 'M');
            }
            this.momentModificacio = momentModificacio;
        }
    }

    // Mètodes "getter" per consultar el contingut de les dades membre
    /**
     * Permet obtenir la referència de la fitxa
     *
     * @return La referència
     */
    public String getReferencia() {
        return referencia;
    }

    /**
     * Permet obtenir el títol de la fitxa
     *
     * @return El títol
     */
    public String getTitol() {
        return titol;
    }

    /**
     * Permet obtenir la marca de préstec de la fitxa
     *
     * @return true si es pot prestar, false si no es pot prestar, null si es
     * desconeix.
     */
    public Boolean getEsDeixa() {
        return esDeixa;
    }

    /**
     * Permet consultar la data de creació de la fitxa
     *
     * @return
     */
    public Date getMomentCreacio() {
        return momentCreacio;
    }

    /**
     * Permet consultar la data de la darrera modificació de la fitxa
     *
     * @return
     */
    public Date getMomentModificacio() {
        return momentModificacio;
    }

    // Mètodes "setter" 
    /**
     * Permet assignar la referéncia a una fitxa
     *
     * @param referencia Referència a assignar
     * @throws FitxaException si la referència és nul·la o si té menys de 10
     * caràcters
     */
    private void setReferencia(String referencia) {
        assignReferencia(referencia);
        setMomentModificacio();
    }

    private void assignReferencia(String referencia) {
        if (referencia == null) {
            throw new FitxaException("La referència és obligatòria.", 'R', referencia);
        }
        if (referencia.length() != 10) {
            throw new FitxaException("La referència ha de tenir 10 caràcters de llargada", 'R', referencia);
        }
        this.referencia = referencia;
    }

    /**
     * Permet assignar el títol a una fitxa
     *
     * @param titol Títol a assignar
     * @throws FitxaException si el títol és null o buït
     */
    public void setTitol(String titol) {
        assignTitol(titol);
        setMomentModificacio();
    }

    private void assignTitol(String titol) {
        if (titol == null || titol.length() == 0) {
            throw new FitxaException("Títol obligatori i no buït", 'T', titol);
        }
        this.titol = titol;
    }

    /**
     * Permet assignar la marca de préstec a una fitxa
     *
     * @param esDeixa true si és deixa, false si no es deixa o null si es
     * desconeix
     */
    public void setEsDeixa(Boolean esDeixa) {
        assignEsDeixa(esDeixa);
        setMomentModificacio();
    }

    private void assignEsDeixa(Boolean esDeixa) {
        this.esDeixa = esDeixa;
    }

    @Override
    public String toString() {
        return "";
    }

    @Override
    /**
     * /*
     * Dues fitxes són iguals si pertanyen a la mateixa classe i tenen la
     * mateixa referència
     */
    public final boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null) {
            return false;
        }
        if (getClass() != o.getClass()) {
            return false;
        }
        Fitxa f = (Fitxa) o;
        return referencia.equals(f.referencia);
    }

    @Override
    public final int hashCode() {
        return 7 * referencia.hashCode();
    }

    public void visualitzar() {
        System.out.println("\tReferència...........: " + referencia);
        System.out.println("\tTítol................: " + titol);
        System.out.println("\tEs Deixa?............: " + esDeixa);
        System.out.println("\tData Creació.........: " + momentCreacio);
        System.out.println("\tData Last Modificació: " + momentModificacio);
    }

    protected void setMomentModificacio() {
        if (momentModificacio == null) {
            momentModificacio = new Date();
        } else {
            momentModificacio.setTime(System.currentTimeMillis());
        }
    }

    @Override
    public int compareTo(Fitxa o) {
        return referencia.compareTo(o.referencia);
    }

    /**
     * Iterator que permet accedir als prestecs d'una fitxa.
     */
    public Iterator<Prestec> itePrestecs() {
        return prestecs.iterator();
    }

    /**
     * Mètode que assigna el préstec a la llista de préstecs de la fitxa. Necessari
     * per poder afegir el préstec a la fitxa en crear el prestec però sense
     * utilitat per al desenvolupament d'aplicacions, ja que un préstec té una
     * fitxa des de la seva creació i és immutable. Per això no és public.
     * L'utilitza el mètode Prestec.setFitxa(). Les classes del mateix paquet hi
     * tenen accés
     *
     * @param prestec
     */
    final void addPrestec(Prestec prestec) {
        prestecs.add(prestec);
    }

};
