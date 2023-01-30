/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa.xml;

import org.milaifontanals.biblioteca.Fitxa;
import org.milaifontanals.biblioteca.Soci;
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
            Fitxa f1 = em.find(Fitxa.class,"R123456789");
            Fitxa f2 = em.find(Fitxa.class,"RRRRRRRRRR");
            System.out.println("f1: "+f1);
            System.out.println("f2: "+f2);
            
            
            // Canviem el nom al soci s1
            if (f1!=null) {
                f1.setTitol("Allò que el vent s'endugué");
                f1.setEsDeixa(Boolean.TRUE);
                em.getTransaction().begin();
                em.getTransaction().commit();
            }
            
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
