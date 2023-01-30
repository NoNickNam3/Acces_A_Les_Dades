/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa.xml;

import org.milaifontanals.biblioteca.Fitxa;
import org.milaifontanals.biblioteca.Prestec;
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

            // Per veure funcionament del fetch, executar
            // en debugger mirant instrucció a instrucció
            // Anem a cercar el soci 300
            Soci s = em.find(Soci.class, 300);
            if (s==null) {
                throw new RuntimeException("No hi ha soci 300. Executi programa P2 prèviament");
            }
            System.out.println("Soci 300: " + s);
            // Anem a cercar el prestec 1000
            Prestec p = em.find(Prestec.class, 1000L);
            if (p==null) {
                throw new RuntimeException("No hi ha prestec 1000. Executi programa P2 prèviament");
            }
            System.out.println("Prestec 1000 trobat");
            System.out.println("Anem a mostrar-lo");
            System.out.println(p);

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
