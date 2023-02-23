/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelprovapracticaexamen;

import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author Ivan
 */
@Entity
public class Empleat {

    @Id
    @Column(length = 4)
    private int codi;
    @Column(length = 30)
    private String cognom;
    @Column(length = 30)
    private String ofici;
    @Column(nullable = false)
    private Date dataAlta;
    @Basic(optional = false)
    private double salari;
    @Basic(optional = false)
    private double comissio;

    protected Empleat(){}
    
    public Empleat(int codi, String cognom, String ofici, Date dataAlta, double salari, double comissio) {
        setCodi(codi);
        setCognom(cognom);
        setOfici(ofici);
        setDataAlta(dataAlta);
        setSalari(salari);
        setComissio(comissio);
    }

    
    
    public int getCodi() {
        return codi;
    }

    public void setCodi(int codi) {
        this.codi = codi;
    }

    public String getCognom() {
        return cognom;
    }

    public void setCognom(String cognom) {
        this.cognom = cognom;
    }

    public String getOfici() {
        return ofici;
    }

    public void setOfici(String ofici) {
        this.ofici = ofici;
    }

    public Date getDataAlta() {
        return dataAlta;
    }

    public void setDataAlta(Date dataAlta) {
        this.dataAlta = dataAlta;
    }

    public double getSalari() {
        return salari;
    }

    public void setSalari(double salari) {
        this.salari = salari;
    }

    public double getComissio() {
        return comissio;
    }

    public void setComissio(double comissio) {
        this.comissio = comissio;
    }
    
}
