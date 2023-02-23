/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa.xml;

import org.milaifontanals.biblioteca.Fitxa;
import org.milaifontanals.biblioteca.FitxaLlibre;
import org.milaifontanals.biblioteca.FitxaRevista;
import org.milaifontanals.biblioteca.Prestec;
import org.milaifontanals.biblioteca.Soci;
import org.milaifontanals.biblioteca.Telefon;
import org.milaifontanals.biblioteca.Tutoria;
import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Professor
 */
public class P02_InserirSocisAmbTelefonsTutoria {

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

            em.getTransaction().begin();
            for (int i = 1; i <= 3; i++) {
                Soci s = new Soci("Cognom " + i, "Nom " + i, new Date(100, 1, i), (i % 2 == 0 ? 'F' : 'M'));
                s.addTelefon(new Telefon("12345F" + i, 'F'));
                s.addTelefon(new Telefon("12345M" + i, 'M'));
                switch (i) {
                    case 1:
                        s.setTutoria(new Tutoria("Tutor1", "Tutor2"));
                        break;
                    case 2:
                        s.setTutoria(new Tutoria("Tutor1", null));
                        break;
                }
                em.persist(s);
            }
            em.getTransaction().commit();
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
