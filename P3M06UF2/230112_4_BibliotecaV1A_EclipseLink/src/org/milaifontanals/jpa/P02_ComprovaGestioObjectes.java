/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import org.milaifontanals.biblioteca.Fitxa;
import org.milaifontanals.biblioteca.FitxaRevista;
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

            Soci s1 = new Soci(1, "Gotera", "Pepe", new Date(100, 0, 1), 'M');
            Soci s2 = new Soci(2, "Rodriguez", "Pepe", new Date(100, 0, 1), 'M');
            Fitxa fr = new FitxaRevista("1234567891", "Er gato", 2000, 1);
            // Fer persistent s1
            em.persist(s1);     // Més lògic fer-ho després de begin().
            // Però funciona!!!
//            em.persist(fr); // No possible per què FitxaRevista NO és Entity
            em.getTransaction().begin();
            em.flush();     // Envia les instruccions SQL a la BD, però sense commit
            em.getTransaction().commit();

// Comprovar què fa el SGBD si ha rebut instruccions via flush però encara no estan
// validades i no hi ha cap comprovació abans de tancar (depèn del SGBD!!!)
            
            
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            System.out.print(ex.getCause() != null ? "Caused by:" + ex.getCause().getMessage() + "\n" : "");
            System.out.println("Traça:");
            ex.printStackTrace();
        } finally {
            if (em != null) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                em.close();
                System.out.println("EntityManager tancat");
            }
            if (emf != null) {
                emf.close();
                System.out.println("EntityManagerFactory tancada");
            }
        }
    }
}
