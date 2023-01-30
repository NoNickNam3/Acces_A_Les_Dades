/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.biblioteca;

import java.util.Date;

public class FitxaRevista extends Fitxa {
    private int any;
    private int num;

    public FitxaRevista(String referencia, String titol, int any, int num) {
        super(referencia, titol);
        setAny(any);
        setNum(num);
    }

    public FitxaRevista(String referencia, String titol, Boolean esDeixa, Date momentCreacio, Date momentModificacio, int any, int num) {
        super(referencia, titol, esDeixa, momentCreacio, momentModificacio);
        setAny(any);
        setNum(num);
    }

    public final int getAny() {
        return any;
    }

    public void setAny(int any) {
        assignAny(any);
        setMomentModificacio();
    }

    private void assignAny(int any) {
        if (any <= 0) {
            throw new FitxaException("L'any de la revista ha de ser >= 0", 'A', any);
        }
        this.any = any;
    }

    public final int getNum() {
        return num;
    }

    public void setNum(int num) {
        assignNum(num);
        setMomentModificacio();
    }

    private void assignNum(int num) {
        if (num <= 0) {
            throw new FitxaException("El número de la revista ha de ser > 0", 'N', num);
        }
        this.num = num;
    }

    @Override
    public String toString() {
        return "Soc revista! Ref: " + getReferencia() + " - Títol: " + getTitol() + " - Any: " + any + " - Núm.: " + num;
    }
    
    @Override
    public void visualitzar() {
        System.out.println("Soc una revista!!!");
        super.visualitzar(); // No estem obligats!!!
        System.out.println("\tAny..................: " + any);
        System.out.println("\tNúmero...............: " + num);
    }
    
    
    
   
    
    
    
}
