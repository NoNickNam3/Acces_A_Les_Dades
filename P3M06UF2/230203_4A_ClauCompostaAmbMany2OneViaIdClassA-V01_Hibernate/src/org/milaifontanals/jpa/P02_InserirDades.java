/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

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
            System.out.println("Intent amb " + up);
            emf = Persistence.createEntityManagerFactory(up);
            System.out.println("EntityManagerFactory creada");
            em = emf.createEntityManager();
            System.out.println("EntityManager creat");

            Pais pa;
            pa= new Pais("ES","Espanya");
            em.persist(pa);
            pa = new Pais("FR","França");
            em.persist(pa);
            
            Provincia pr;
            pr = new Provincia(pa, "T", "Toulouse");    // pa apunta a França
            em.persist(pr);
            
            pa = em.find(Pais.class, "ES");
            pr = new Provincia(pa, "B", "Barcelona");
            em.persist(pr);
            pr = new Provincia(pa, "L", "Lleida");
            em.persist(pr);
            pr = new Provincia(pa, "T", "Tarragona");
            em.persist(pr);
            pr = new Provincia(pa, "GI", "Girona");
            em.persist(pr);
            
            em.getTransaction().begin();
            em.getTransaction().commit();

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
