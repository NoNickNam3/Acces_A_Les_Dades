/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa.xml;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.milaifontanals.biblioteca.Fitxa;
import org.milaifontanals.biblioteca.Prestec;
import org.milaifontanals.biblioteca.Soci;

/**
 *
 * @author Professor
 */
public class P01_ComprovaEsquemaBD {

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
            em.getTransaction().begin();
            Fitxa f = new Fitxa("0123456789", "Fitxa 01");
            f.setEsDeixa(Boolean.TRUE);
            em.persist(f);
            em.getTransaction().commit();
            Prestec pr = new Prestec(01, em.find(Soci.class, 1), em.find(Fitxa.class, "0123456789"), new Date());
            em.persist(pr);
            System.out.println();
            System.out.println("EntityManager creat");
            
            
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
