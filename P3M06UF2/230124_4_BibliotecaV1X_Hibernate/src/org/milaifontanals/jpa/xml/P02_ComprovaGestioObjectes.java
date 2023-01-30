/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa.xml;

import org.milaifontanals.biblioteca.Fitxa;
import org.milaifontanals.biblioteca.Prestec;
import org.milaifontanals.biblioteca.Soci;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Professor
 */
public class P02_ComprovaGestioObjectes {

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

            Fitxa f = em.find(Fitxa.class, "R111111111");
            if (f == null) {
                f = new Fitxa("R111111111", "R111111111");
            }
            Soci s = new Soci("C1", "Nom", new Date(), 'F');
            Prestec p = new Prestec(s, f, new Date());

            Soci s2 = new Soci("C2", "Nom", new Date(), 'M');

            em.persist(p);
            em.persist(s2);

            em.getTransaction().begin();
            em.getTransaction().commit();
            System.out.println("Fitxa R111111111 prestada");
            System.out.println("a soci " + s.getCodi());
            System.out.println("i prestec " + p.getNumero());

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
