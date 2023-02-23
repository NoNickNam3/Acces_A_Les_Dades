/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class P02_InserirColors {

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

            Color c;
            c = new Color(255,0,0,"Vermell"); em.persist(c);
            c = new Color(0,255,0,"Verd"); em.persist(c);
            c = new Color(0,0,255,"Blau"); em.persist(c);
            c = new Color(0,0,0,"Negre"); em.persist(c);
            c = new Color(255,255,255,"Blanc"); em.persist(c);
            c = new Color(255,255,0,"Groc"); em.persist(c);
            c = new Color(255,0,255,"Magenta"); em.persist(c);
            c = new Color(0,255,255,"Aiguamarina"); em.persist(c);
            
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
