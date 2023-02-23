/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelprovapracticaexamen;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

/**
 *
 * @author Ivan
 */
@Entity
public class Client {
    @Id
    @TableGenerator(name = "genClauClient", table = "ComptadorsTaula",
            pkColumnName = "cl",
            valueColumnName = "comptadorClients",
            pkColumnValue = "cl",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "genClauClient")
    private int client_cod;
    private String nom;
    private String domicili;
    private String ciutat;
    private String estat;
    private String codiPostal;
    private int area;
    private String telefon;
    private int repr_cod;
    private double limitCredit;
    private String observacions;

    protected Client(){}
    
    public Client(String nom, String domicili, String ciutat, String estat, String codiPostal, int area, String telefon, int repr_cod, double limitCredit, String observacions) {
        this.nom = nom;
        this.domicili = domicili;
        this.ciutat = ciutat;
        this.estat = estat;
        this.codiPostal = codiPostal;
        this.area = area;
        this.telefon = telefon;
        this.repr_cod = repr_cod;
        this.limitCredit = limitCredit;
        this.observacions = observacions;
    }

    
    
    public int getClient_cod() {
        return client_cod;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDomicili() {
        return domicili;
    }

    public void setDomicili(String domicili) {
        this.domicili = domicili;
    }

    public String getCiutat() {
        return ciutat;
    }

    public void setCiutat(String ciutat) {
        this.ciutat = ciutat;
    }

    public String getEstat() {
        return estat;
    }

    public void setEstat(String estat) {
        this.estat = estat;
    }

    public String getCodiPostal() {
        return codiPostal;
    }

    public void setCodiPostal(String codiPostal) {
        this.codiPostal = codiPostal;
    }

    public int getArea() {
        return area;
    }

    public void setArea(int area) {
        this.area = area;
    }

    public String getTelefon() {
        return telefon;
    }

    public void setTelefon(String telefon) {
        this.telefon = telefon;
    }

    public int getRepr_cod() {
        return repr_cod;
    }

    public void setRepr_cod(int repr_cod) {
        this.repr_cod = repr_cod;
    }

    public double getLimitCredit() {
        return limitCredit;
    }

    public void setLimitCredit(double limitCredit) {
        this.limitCredit = limitCredit;
    }

    public String getObservacions() {
        return observacions;
    }

    public void setObservacions(String observacions) {
        this.observacions = observacions;
    }
    
    
}
