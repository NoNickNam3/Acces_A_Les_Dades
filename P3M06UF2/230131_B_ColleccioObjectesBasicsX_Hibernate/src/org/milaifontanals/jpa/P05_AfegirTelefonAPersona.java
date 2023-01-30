/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import java.util.Iterator;
import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Professor
 */
public class P05_AfegirTelefonAPersona {

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

            // Programa que demana dni de la persona i permet afegir un telèfon
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
                String tel;
                do {
                    System.out.println("Telèfon a afegir:");
                    tel = sc.nextLine();
                } while (tel.equals(""));
                if (p.addTelefon(tel)) {
                    System.out.println("Telèfon afegit");
                    System.out.println("Telèfons actuals:");
                    Iterator<String> it = p.iteTelefons();
                    while (it.hasNext()) {
                        String t = it.next();
                        System.out.println("\t"+t);
                    }
                    em.getTransaction().begin();
                    em.getTransaction().commit();
                } else {
                    System.out.println("Ja existia aquest telèfon");
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
