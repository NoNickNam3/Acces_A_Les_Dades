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
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TemporalType;

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

            // Anem a cercar les fitxes creades en P02
            Fitxa f1 = em.find(FitxaRevista.class, "1234567891");
            System.out.println("Fitxa 1: " + f1);
            Fitxa f2 = em.find(FitxaLlibre.class, "1234567892");
            System.out.println("Fitxa 2: " + f2);
            // Modifiquem-les
            em.getTransaction().begin();
            Fitxa t[] = {f1, f2};
            int i = 0;
            for (Fitxa f : t) {
                f.setTitol("Fitxa " + (++i));
                if (f instanceof FitxaLlibre) {
                    ((FitxaLlibre) f).setIsbn("0000000000123");
                    continue;
                }
                if (f instanceof FitxaRevista) {
                    ((FitxaRevista) f).setAny(3000);
                }
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
