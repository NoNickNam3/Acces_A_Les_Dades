/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.milaifontanals.rrhh.Empleat;
import org.milaifontanals.rrhh.Treball;

/**
 *
 * @author Usuari
 */
public class P05_GestioDades2 {

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

            Scanner sc = new Scanner(System.in);
            System.out.println("Codi de treball");
            String codi = sc.nextLine();
            Treball t = em.find(Treball.class, codi);
            if (t == null) {
                throw new LocalException("No existeix el treball de codi " + codi);
            }
            System.out.println("% augment estrictament positiu");
            float p = 0;
            try {
                p = Float.parseFloat(sc.nextLine());
                if (p<=0) {
                    throw new LocalException("Valor no vàlid com a percentatge");
                }
            } catch (NumberFormatException nfe) {
                throw new LocalException("Valor no vàlid com a percentatge");
            }
            t.setSalariMaxim(Math.round(t.getSalariMaxim()*(1+p/100)));
            t.setSalariMinim(Math.round(t.getSalariMinim()*(1+p/100)));
            Query q = em.createQuery("select e from Empleat e where e.treball = :x");
            q.setParameter("x", t);
            List<Empleat> ll = q.getResultList();
            for (Empleat e: ll) {
                e.setSalari(e.getSalari()*(1+p/100));
            }
            em.getTransaction().begin();
            em.getTransaction().commit();
        } catch (LocalException ex) {
            System.out.println(ex.getMessage());
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

