/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.milaifontanals.rrhh.Empleat;

/**
 *
 * @author Usuari
 */
public class P02_ConsultaEmpleats {

    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        String up = "UP-Oracle-Hibernate";
        try {
            em = null;
            emf = null;
            System.out.println("Intent amb " + up);
            emf = Persistence.createEntityManagerFactory(up);
            System.out.println("EntityManagerFactory creada");
            em = emf.createEntityManager();
            System.out.println();
            System.out.println("EntityManager creat");

            Query q = em.createNamedQuery("trobaTots");
            List<Empleat> ll = q.getResultList();
            if (ll.isEmpty()) {
                System.out.println("No hi ha empleats");
            } else {
                for (Empleat e : ll) {
                    System.out.println(e);
                }
            }

        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            System.out.print(ex.getCause() != null ? "Caused by:" + ex.getCause().getMessage() + "\n" : "");
            System.out.println("Traça:");
            ex.printStackTrace();
        } finally {
            if (em != null) {
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
