/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import java.util.Iterator;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Professor
 */
public class P05_AfegirCorreuAPersona {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Un únic argument amb el nom de la Unitat de Persistència");
            System.exit(1);
        }
        String up = args[0];
        EntityManagerFactory emf = null;
        EntityManager em = null;
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Intent amb " + up);
            emf = Persistence.createEntityManagerFactory(up);
            System.out.println("EntityManagerFactory creada");
            em = emf.createEntityManager();
            System.out.println("EntityManager creat");

            // Programa que demana dni de la persona i permet afegir un correu
            // que també ha d'introduir l'usuari
            String dni = null;
            do {
                System.out.println("DNI de la persona:");
                dni = sc.nextLine();
            } while (dni.equals(""));
            Persona p = em.find(Persona.class,dni);
            if (p==null) {
                System.out.println("No hi ha cap persona amb aquest DNI. ");
                System.out.println("Programa finalitzat");
            } else {
                String correu;
                do {
                    System.out.println("Correu a afegir:");
                    correu = sc.nextLine();
                } while (correu.equals(""));
                if (p.addCorreuElectronic(new  CorreuElectronic(correu))) {
                    System.out.println("Telèfon afegit");
                    System.out.println("Telèfons actuals:");
                    Iterator<CorreuElectronic> it = p.iteCorreusElectronics();
                    while(it.hasNext()) {
                        System.out.println("\t\t"+it.next());
                    }
                    em.getTransaction().begin();
                    em.getTransaction().commit();
                } else {
                    System.out.println("Ja existia aquest correu");
                }
                
                
                
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            System.out.print(ex.getCause() != null ? "Caused by:" + ex.getCause().getMessage() + "\n" : "");
        } finally {
            if (em.getTransaction().isActive()) {
                em.getTransaction().rollback();
            }
            if (em != null) {if (em.getTransaction().isActive()) {em.getTransaction().rollback();}em.close();
                System.out.println("EntityManager tancat");
            }
            if (emf != null) {
                emf.close();
                System.out.println("EntityManagerFactory tancada");
            }
        }
    }
}
