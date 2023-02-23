/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import org.milaifontanals.biblioteca.AgendaTallers;
import org.milaifontanals.biblioteca.Taller;
import java.util.GregorianCalendar;
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
            emf = Persistence.createEntityManagerFactory(up);
            System.out.println("EntityManagerFactory creada");
            em = emf.createEntityManager();
            System.out.println();
            System.out.println("EntityManager creat");

            em.getTransaction().begin();
            Taller t1 = new Taller("LI01","Lectura per infants - Nivell 1");
            AgendaTallers a11 = new AgendaTallers(t1,new GregorianCalendar(2020,0,15,18,30));
            AgendaTallers a12 = new AgendaTallers(t1,new GregorianCalendar(2020,1,15,17,30));
            AgendaTallers a13 = new AgendaTallers(t1,new GregorianCalendar(2020,2,15,18,0));
//            em.persist(t1);   // No necessari per què AgendaTaller te CascadeType.PERSIST
            em.persist(a11);
            em.persist(a12);
            em.persist(a13);
            Taller t2 = new Taller("CCNE","Cine Club Nits Estiu");
            em.persist(t2);
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
