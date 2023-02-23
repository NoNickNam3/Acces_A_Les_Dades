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
import javax.persistence.Query;

/**
 *
 * @author Professor
 */
public class P04_ConsultaJPQLParametritzada {

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

            // Exemple de paràmetres amb nom
            String consulta = "select s from Soci s where upper(s.cognom1)>:inici";
            Query q = em.createQuery(consulta);
            String inicials[] = {"C", "P"};
            for (String inicial : inicials) {
                q.setParameter("inici", inicial);
                List<Soci> llSoci = q.getResultList();
                System.out.println("Socis amb cognom superior a "+inicial+":");
                for (Soci s : llSoci) {
                    System.out.println("\t" + s);
                }
            }
            // Exemple de paràmetres per posició
            consulta = "select s from Soci s where upper(s.cognom1)>?1";
            q = em.createQuery(consulta);
            for (String inicial : inicials) {
                q.setParameter(1, inicial);
                List<Soci> llSoci = q.getResultList();
                System.out.println("Socis amb cognom superior a "+inicial+":");
                for (Soci s : llSoci) {
                    System.out.println("\t" + s);
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
