/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.rrhh;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

/**
 *
 * @author Usuari
 */
public class Empleat implements Serializable {

    private int id;             // Obligatori
    private String nom;         // No obligatori
    private String cognom;      // Obligatori
    private String eMail;       // Obligatori
    private String telefon;     // No obligatori
    private Date dataAlta;      // Obligatori
    private Treball treball;    // Obligatori
    private Double salari;      // No obligatori     
    private Float comissio;     // No obligatori
    private Departament departament;    // No obligatori
    private List<Contracte> contractes = new ArrayList();

    protected Empleat() {

    }

    public Empleat(String cognom, String eMail, Date dataAlta, Treball treball) {
        // id autonumèric
        setCognom(cognom);
        seteMail(eMail);
        setDataAlta(dataAlta);
        setTreball(treball);
    }

    public int getId() {
        return id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public String geteMail() {
        return eMail;
    }

    public void seteMail(String eMail) {
        this.eMail = eMail;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public Date getDataAlta() {
        return dataAlta;
    }

    public void setDataAlta(Date dataAlta) {
        this.dataAlta = dataAlta;
    }

    public Treball getTreball() {
        return treball;
    }

    public void setTreball(Treball treball) {
        this.treball = treball;
    }

    public Double getSalari() {
        return salari;
    }

    public void setSalari(Double salari) {
        this.salari = salari;
    }

    public Float getComissio() {
        return comissio;
    }

    public void setComissio(Float comissio) {
        this.comissio = comissio;
    }

    public Departament getDepartament() {
        return departament;
    }

    public void setDepartament(Departament departament) {
        if (departament == null) {
            if (this.departament != null) {
                this.departament.removeEmpleat(this);
                this.departament = null;
            }
        } else {
            if (this.departament != departament) {
                departament.addEmpleat(this);
                this.departament = departament;
            }
        }
    }

    Iterator<Contracte> iteContractes () {
        return contractes.iterator();
    }
    
    void addContracte(Contracte contracte) {
        if (contracte == null) {
            throw new RuntimeException("Intent d'afegir contracte NULL a l'empleat");
        };
        if (contracte.getEmpleat() == null) {
            if (!this.contractes.contains(contracte)) {
                contractes.add(contracte);
            }
        }
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + this.id;
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
        final Empleat other = (Empleat) obj;
        if (this.id != other.id) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Empleat{" + "id=" + id + ", nom=" + nom + ", cognom=" + cognom + ", eMail=" + eMail + ", telefon=" + telefon + ", dataAlta=" + dataAlta + '}';
    }

};
