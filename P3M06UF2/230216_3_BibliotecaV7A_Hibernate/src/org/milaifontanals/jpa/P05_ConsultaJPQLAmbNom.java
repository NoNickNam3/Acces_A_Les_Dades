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
public class P05_ConsultaJPQLAmbNom {

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

            // Crida de NamedQuery sense paràmetres
            Query q = em.createNamedQuery("SocisOrdenatsPerCognom");
            // O equivalent, com que aquesta consulta retorna objectes Soci:
//            Query q = em.createNamedQuery("SocisOrdenatsPerCognom",Soci.class);
            List<Soci> llSoci = q.getResultList();
            System.out.println("Socis ordenats per cognom");
            for (Soci s : llSoci) {
                System.out.println("\t" + s);
            }
            // Crida de NamedQuery parametritzada
            q = em.createNamedQuery("SocisAmbCognom1SuperiorA");
            String inicials[] = {"C", "P"};
            for (String inicial : inicials) {
                q.setParameter("inicial", inicial);
                llSoci = q.getResultList();
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
