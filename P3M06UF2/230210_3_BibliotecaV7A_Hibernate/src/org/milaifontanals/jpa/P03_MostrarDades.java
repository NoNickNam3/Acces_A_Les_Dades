/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import org.milaifontanals.biblioteca.Soci;
import org.milaifontanals.biblioteca.AgendaTallers;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author Professor
 */
public class P03_MostrarDades {

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
            boolean mostrar = Utils.mostrarInstruccionsSQL();
            if (mostrar) {
                HashMap<String, String> propietats = new HashMap();
                propietats.put("hibernate.show_sql", "true");
                emf = Persistence.createEntityManagerFactory(up, propietats);
            } else {
                emf = Persistence.createEntityManagerFactory(up);
            }
            System.out.println("EntityManagerFactory creada");
            em = emf.createEntityManager();
            System.out.println();
            System.out.println("EntityManager creat");

            System.out.println("Agendes amb la seva participació");
            System.out.println("********************************");
                    String consulta = "select at from AgendaTallers at order by at.momentInici desc";
            Query q = em.createQuery(consulta);
            List<AgendaTallers> ll = q.getResultList();
            for (AgendaTallers at : ll) {
                System.out.println(at);
                boolean trobat = false;
                System.out.println("Participació:");
                Iterator<Soci> ite = at.iteSocis();
                while (ite.hasNext()) {
                    trobat=true;
                    System.out.println("\t" + ite.next());
                }
                if (!trobat) {
                    System.out.println("\t***Cap***");
                }
            }
            System.out.println("Socis amb els seus tallers");
            System.out.println("**************************");
            consulta = "select s from Soci s order by cognom1,nom";
            q = em.createQuery(consulta);
            List<Soci> socis = q.getResultList();
            for (Soci s : socis) {
                System.out.println(s);
                boolean trobat = false;
                System.out.println("Tallers:");
                Iterator<AgendaTallers> ite = s.iteTallers();
                while (ite.hasNext()) {
                    trobat=true;
                    System.out.println("\t" + ite.next());
                }
                if (!trobat) {
                    System.out.println("\t***Cap***");
                }
            }

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
