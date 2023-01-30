/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Professor
 */
public class P04_EliminarTelefonDePersona {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Un únic argument amb el nom de la Unitat de Persistència");
            System.exit(1);
        }
        String up = args[0];
        EntityManagerFactory emf = null;
        EntityManager em = null;
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Intent amb " + up);
            emf = Persistence.createEntityManagerFactory(up);
            System.out.println("EntityManagerFactory creada");
            em = emf.createEntityManager();
            System.out.println("EntityManager creat");

            // Programa que demana nom de persona i telèfon i cerca persona per
            // eliminar-li aquest telèfon... controlant les possibles anomalies
            String nom = null;
            do {
                System.out.println("Nom de la persona:");
                nom = sc.nextLine();
            } while (nom.equals(""));
            Query q = em.createQuery("select p from Persona p where p.nom=:nom")
                    .setParameter("nom", nom);
            List<Persona> ll = q.getResultList();
            if (ll.size() == 0) {
                System.out.println("No hi ha cap persona amb aquest nom. ");
                System.out.println("Programa finalitzat");
            } else {
                String tel;
                do {
                    System.out.println("Telèfon a eliminar:");
                    tel = sc.nextLine();
                } while (tel.equals(""));
                boolean eliminat = false;
                for (Persona p : ll) {
                    if (p.removeTelefon(tel)) {
                        System.out.println("\tTel. " + tel + " eliminat a " + p);
                        eliminat = true;
                    }
                }
                if (!eliminat) {
                    System.out.println("Cap persona amb nom " + nom + " tenia tel. " + tel);
                } else {
                    em.getTransaction().begin();
                    em.getTransaction().commit();
                }
            }
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
