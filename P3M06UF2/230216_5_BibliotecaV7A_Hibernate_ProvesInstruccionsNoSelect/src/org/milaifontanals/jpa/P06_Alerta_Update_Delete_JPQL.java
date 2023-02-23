/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import org.milaifontanals.biblioteca.Soci;
import java.util.HashMap;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.PersistenceException;
import javax.persistence.Query;

/**
 *
 * @author Professor
 */
public class P06_Alerta_Update_Delete_JPQL {

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
            System.out.println("Intent amb " + up);
            HashMap<String, String> propietats = new HashMap();
            propietats.put("hibernate.show_sql", "false");
            emf = Persistence.createEntityManagerFactory(up, propietats);
            System.out.println("EntityManagerFactory creada");
            em = emf.createEntityManager();
            System.out.println();
            System.out.println("EntityManager creat");
            System.out.println("Desactivada la visualització d'instruccions SQL");

            System.out.println("Suposem que la BD té les dades que insereix P02");
            System.out.println("");

            String instruccio = "select s from Soci s where cognom1>'P'";
            Query q = em.createQuery(instruccio);
            List<Soci> ll = q.getResultList();
            System.out.println("Socis gestionats per EM:");
            for (Soci s : ll) {
                System.out.println(s);
            }

            em.getTransaction().begin();
            System.out.println("Canviem nom als socis amb cognom1>'P' via instrucció JPQL");
            instruccio = "update Soci s set s.nom= concat(s.nom,'-',s.nom) where s.cognom1>'P'";
            q = em.createQuery(instruccio);
            int n = q.executeUpdate();
            System.out.println("Nom canviats: " + n);
            em.getTransaction().commit();

            System.out.println("Socis gestionats per EM:");
            for (Soci s : ll) {
                System.out.println(s);
            }
            System.out.println("EM no mostra el nom actualitzat!!!");
            System.out.println("Socis gestionats per EM refrescant-los:");
            for (Soci s : ll) {
                System.out.println("Refresquem " + s);
                em.refresh(s);
                System.out.println(s);
            }

            em.getTransaction().begin();
            System.out.println("Eliminem els socis amb cognom1>'P' via instrucció JPQL");
            instruccio = "delete from Soci s where cognom1>'P'";
            q = em.createQuery(instruccio);
            n = q.executeUpdate();
            System.out.println("Socis eliminats: " + n);
            em.getTransaction().commit();

            System.out.println("Socis gestionats per EM:");
            for (Soci s : ll) {
                System.out.println(s);
                System.out.println("EM el gestiona? "+em.contains(s));
            }
            System.out.println("EM continua gestionant socis que ja no existeixen!");
            System.out.println("Socis gestionats per EM refrescant-los:");
            for (Soci s : ll) {
                try {
                    System.out.println("Refresquem " + s);
                    em.refresh(s);
                } catch (PersistenceException ex) {
                    infoError(ex);
                    em.detach(s);
                    System.out.println("Després de refrescat, EM el gestiona? "+em.contains(s));
                }
            }

        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            infoError(ex);
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

    private static void infoError(Throwable ex) {
        System.out.println(ex.getMessage());
        while ((ex = ex.getCause()) != null) {
            System.out.println("\t" + ex.getMessage());
        }
    }
}
