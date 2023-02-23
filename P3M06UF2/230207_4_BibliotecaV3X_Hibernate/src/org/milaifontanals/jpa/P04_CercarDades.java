/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import org.milaifontanals.biblioteca.AgendaTallers;
import org.milaifontanals.biblioteca.AgendaTallersId;
import org.milaifontanals.biblioteca.Soci;
import org.milaifontanals.biblioteca.Telefon;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Professor
 */
public class P04_CercarDades {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Un únic argument amb el nom de la Unitat de Persistència");
            System.exit(1);
        }
        String up = args[0];
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            em = null;
            emf = null;
            System.out.println("Intent amb " + up);
            emf = Persistence.createEntityManagerFactory(up);
            System.out.println("EntityManagerFactory creada");
            em = emf.createEntityManager();
            System.out.println();
            System.out.println("EntityManager creat");

            AgendaTallers a;
            System.out.println("Cerquem taller CCNE en 15-01-2020 18:30");
            a = em.find(AgendaTallers.class,
                    new AgendaTallersId("CCNE", new GregorianCalendar(2020, 0, 15, 18, 30)));
            if (a == null) {
                System.out.println("No trobat");
            } else {
                System.out.println("Trobat:");
                System.out.println(a);
            }
            System.out.println("Cerquem taller LE01 en 15-01-2020 18:30");
            a = em.find(AgendaTallers.class,
                    new AgendaTallersId("LI01", new GregorianCalendar(2020, 0, 15, 18, 30)));
            if (a == null) {
                System.out.println("No trobat");
            } else {
                System.out.println("Trobat:");
                System.out.println(a);
            }

        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            System.out.print(ex.getCause() != null ? "Caused by:" + ex.getCause().getMessage() + "\n" : "");
            System.out.println("Traça:");
            ex.printStackTrace();
        } finally {
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
