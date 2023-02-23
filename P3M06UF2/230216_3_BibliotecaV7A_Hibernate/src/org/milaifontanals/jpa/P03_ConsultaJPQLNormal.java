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
public class P03_ConsultaJPQLNormal {

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

            String consulta = "select s from Soci s where upper(s.cognom1)>'P'";
            Query q = em.createQuery(consulta);
            List<Soci> llSoci = q.getResultList();
            System.out.println("Socis amb cognom superior a 'P'");
            for (Soci s : llSoci) {
                System.out.println("\t" + s);
            }
            consulta = "select s.codi, s.cognom1 from Soci s order by s.cognom1";
            q = em.createQuery(consulta);
            List<Object[]> llA = q.getResultList();
            System.out.println("Codi i nom dels socis");
            for (Object[] t : llA) {
                System.out.println("\tCodi  : " + t[0]);
                System.out.println("\tCognom: " + ((String)t[1]).toUpperCase());
            }

            consulta = "select s from Soci s";
            q = em.createQuery(consulta);
            llSoci = q.getResultList();
            System.out.println("Socis amb les seves inscripcions");
            for (Soci s : llSoci) {
                System.out.println("\t" + s);
                System.out.println("\t Inscripcions:");
                Iterator<AgendaTallers> iat = s.iteTallers();
                while (iat.hasNext()) {
                    System.out.println("\t\t" + iat.next());
                }
            }

            consulta = "select t, a.momentInici from Taller t ";
            consulta = consulta + "left join AgendaTallers a on a.codiTaller = t.codi";
            q = em.createQuery(consulta);
            List<Object[]> llB = q.getResultList();
            System.out.println("Tallers amb les seves agendes");
            for (Object[] t : llB) {
                System.out.println("\t" + t[0]);
                System.out.println("\t\tMoment Inici:" + t[1]);
            }

            // Mostrar Socis (codi, cognom) i qtat. d'inscripcions a tallers
            consulta = "select s.codi, s.cogn   om1, size(s.tallers) ";
            consulta += "from Soci s";
            // Info de funcions suportades en JPQL
//            https://en.wikibooks.org/wiki/Java_Persistence/JPQL#JPQL_supported_functions
            q = em.createQuery(consulta);
            List<Object[]> llC = q.getResultList();
            System.out.println("Socis amb qtat. d'inscripcions:");
            for (Object[] t : llC) {
                System.out.println("Soci: " + t[0] + " - " + t[1] + " - Inscripcions: " + t[2]);
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
