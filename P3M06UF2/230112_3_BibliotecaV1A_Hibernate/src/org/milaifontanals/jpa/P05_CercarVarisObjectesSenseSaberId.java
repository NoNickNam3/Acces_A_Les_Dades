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
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Professor
 */
public class P05_CercarVarisObjectesSenseSaberId {

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

            // Recuperació de TOTS els socis
            String consulta = "select s from Soci s";
            Query q = em.createQuery(consulta);
            List<Soci> ls = q.getResultList();
            for (Soci s : ls) {
                System.out.println(s);
                s.setCognom2("Prova");
                if (s.getSexe() == 'F') {
                    s.setSexe('M');
                } else {
                    s.setSexe('F');
                }
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
