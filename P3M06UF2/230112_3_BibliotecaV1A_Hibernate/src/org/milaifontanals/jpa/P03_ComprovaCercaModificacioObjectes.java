/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import org.milaifontanals.biblioteca.Soci;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Professor
 */
public class P03_ComprovaCercaModificacioObjectes {

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

            // Anem a cercar el soci de codi 1:
            Soci s1 = em.find(Soci.class, 1);
            System.out.println("Soci 1: " + s1);
            Soci s100 = em.find(Soci.class, 100);
            System.out.println("Soci 100: " + s100);
            Soci s2 = new Soci(2, "Rodriguez", "Pepe", new Date(100, 0, 1), 'M');
            em.persist(s2);

            // Canviem el nom al soci s1
            if (s1 != null) {
                s1.setNom("Pepa");
                s1.setCognom2("Gotero");
                s2.setNom("Maria");
            }
            em.getTransaction().begin();
            em.getTransaction().commit();

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
