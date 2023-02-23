/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import org.milaifontanals.biblioteca.Fitxa;
import org.milaifontanals.biblioteca.Prestec;
import org.milaifontanals.biblioteca.Soci;
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
public class P04_MostrarDades {

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

            String consulta;
            Query q;

            // Anem a mostrar les fitxes amb els seus prestecs
            // Però observem que fem la consulta només per fitxes
            System.out.println("Procedim a carregar les fitxes de la BD");
            consulta = "select f from Fitxa f";
            q = em.createQuery(consulta);
            List<Fitxa> lFitxa = q.getResultList();
            System.out.println("Fitxes carregades");
            if (mostrar) {
                System.out.println("Observem que només s'ha consultat les fitxes sense prestecs");
                System.out.println("Lògic, doncs OneToMany té FetchType.LAZY");
            }
            for (Fitxa f : lFitxa) {
                System.out.println("Fitxa " + f);
                Iterator<Prestec> itePrestec = f.itePrestecs();
                System.out.println("Prestecs de la fitxa carregats");
                if (mostrar) {
                    System.out.println("Ha executat la SELECT per anar a cercar els prestecs de la fitxa " + f.getReferencia());
                    System.out.println("Si teniu missatges en constructor per defecte de Prestecs,");
                    System.out.println("veureu que s'ha invocat el constructor tantes vegades com prestecs ha trobat");
                }
                System.out.println("Préstecs: ");
                while (itePrestec.hasNext()) {
                    Prestec p = itePrestec.next();
                    System.out.println("\tPrestec " + p.getNumero());
                }
            }

            // Anem a mostrar els socis amb els seus prestecs
            // Però observem que fem la consulta només pels socis
            System.out.println("Procedim a carregar els socis de la BD");
            consulta = "select s from Soci s";
            q = em.createQuery(consulta);
            List<Soci> lSoci = q.getResultList();
            if (mostrar) {
                System.out.println("Observem que només s'ha consultat els socis sense prestecs");
                System.out.println("Lògic, doncs OneToMany té FetchType.LAZY");
            }
            for (Soci s : lSoci) {
                System.out.println("Soci " + s);
                Iterator<Prestec> itePrestec = s.itePrestecs();
                System.out.println("Prestecs del soci carregats");
                if (mostrar) {
                    System.out.println("Ha executat la SELECT per anar a cercar els prestecs del soci " + s.getCodi());
                    System.out.println("Si teniu missatges en constructor per defecte de Prestecs,");
                    System.out.println("observeu que NO s'ha cridat cap constructor per què tots els préstecs");
                    System.out.println("que ha trobat ja els tenia en memòria, de quan ha recorregut les fitxes");
                }
                System.out.println("Préstecs: ");
                while (itePrestec.hasNext()) {
                    Prestec p = itePrestec.next();
                    System.out.println("\tPrestec " + p.getNumero());
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
