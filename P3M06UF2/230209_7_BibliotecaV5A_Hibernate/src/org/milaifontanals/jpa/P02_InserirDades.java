/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import org.milaifontanals.biblioteca.Autor;
import org.milaifontanals.biblioteca.FitxaLlibre;
import java.util.HashMap;
import java.util.Iterator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Professor
 */
public class P02_InserirDades {

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
            boolean mostrar = Utils.mostrarInstruccionsSQL();
            if (mostrar) {
                HashMap<String, String> propietats = new HashMap();
                propietats.put("hibernate.show_sql", "true");
                emf = Persistence.createEntityManagerFactory(up, propietats);
            } else {
                emf = Persistence.createEntityManagerFactory(up);
            }
            System.out.println("EntityManagerFactory creada");
            em = emf.createEntityManager();
            System.out.println();
            System.out.println("EntityManager creat");

            em.getTransaction().begin();

            Autor a0 = new Autor("A000", "Autor A000");
            Autor a1 = new Autor("A001", "Autor A001");
            Autor a2 = new Autor("A002", "Autor A002");
            Autor a3 = new Autor("A003", "Autor A003");
            FitxaLlibre f0 = new FitxaLlibre("R000000000", "Llibre R0");
            FitxaLlibre f1 = new FitxaLlibre("R000000001", "Llibre R1");
            // Llibre f1 amb 1 autor
            f1.addAutor(a1);
            FitxaLlibre f2 = new FitxaLlibre("R000000002", "Llibre R2");
            // Llibre f2 amb 2 autors
            f2.addAutor(a1);
            f2.addAutor(a2);
            FitxaLlibre f3 = new FitxaLlibre("R000000003", "Llibre R3");
            // Llibre f3 amb 3 autors
            f3.addAutor(a1);
            f3.addAutor(a2);
            f3.addAutor(a3);

            // En fer persistents els llibres, també es fan persistents els autors
            // per què tenim Cascade.PERSIST
            em.persist(f0);
            em.persist(f1);
            em.persist(f2);
            em.persist(f3);
            // Hem de fer persistent l'autor a0, doncs no és autor de cap llibre!!!
            em.persist(a0);

            em.getTransaction().commit();

            System.out.println("Autors i Llibres creats");
            
            System.out.println("Autors:");
            System.out.println("*******");
            Autor[] autors = {a0,a1,a2,a3};
            for (Autor a: autors) {
                System.out.println(a);
            }

            System.out.println("Llibres");
            System.out.println("*******");
            FitxaLlibre[] ll = {f0, f1, f2, f3};
            for (FitxaLlibre f : ll) {
                System.out.println(f);
                Iterator<Autor> iteAutor = f.iteAutors();
                System.out.println("Autoria:");
                boolean trobat = false;
                while (iteAutor.hasNext()) {
                    trobat = true;
                    System.out.println("\t"+iteAutor.next());
                }
                if (!trobat) System.out.println("\t***Desconeguda***");
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
