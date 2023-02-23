/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import org.milaifontanals.biblioteca.Taller;
import java.util.GregorianCalendar;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Professor
 */
public class P03_ProvesRefreshRemove {

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

            Taller t = em.find(Taller.class, "XXXX");
            if (t == null) {
                em.getTransaction().begin();
                t = new Taller("XXXX","Taller XXXX");
                em.persist(t);
                em.getTransaction().commit();
            }
            System.out.println("Comproveu a la BD que existeix taller XXXX");
            System.out.println("Premeu <intro> per continuar...");
            sc.nextLine();
            System.out.println("Taller t: " + t);
            System.out.println("Canvieu el títol del taller des de la BD i feu commit");
            System.out.println("Premeu <intro> per continuar...");
            sc.nextLine();
            System.out.println("Abans refresh: " + t);
            em.refresh(t);
            System.out.println("Després de refresh: " + t);
            System.out.println("Procedim a eliminar el taller de la BD");
            System.out.println("Abans de remove - Objecte controlat per EM? " + em.contains(t));
            em.remove(t);
            System.out.println("Després de remove - Objecte controlat per EM? " + em.contains(t));
            System.out.println("Abans de flush. Comproveu si existeix a la BD");
            em.getTransaction().begin();
            System.out.println("Premeu <intro> per continuar...");


            sc.nextLine();
            em.flush();
            System.out.println("Després de flush. Comproveu si existeix a la BD");
            System.out.println("Veureu que encara hi és, però si intenteu canviar-lo o eliminar-lo, no us deixa");
            System.out.println("El SGBD ja sap que una sessió ha fet <delete>");
            System.out.println("Premeu <intro> per continuar...");
            sc.nextLine();
            em.getTransaction().commit();
            System.out.println("Després de commit. Comproveu si existeix a la BD");
            System.out.println("Premeu <intro> per continuar...");
            sc.nextLine();
            
            System.out.println("Programa finalitzat");

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
