/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

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
            System.out.println("Intent amb " + up);
            emf = Persistence.createEntityManagerFactory(up);
            System.out.println("EntityManagerFactory creada");
            em = emf.createEntityManager();
            System.out.println("EntityManager creat");

            String consulta = "select pa.codi, pa.nom, pr.codi, pr.nom ";
            consulta = consulta + "from Pais pa left join Provincia pr on pa = pr.pais ";
            consulta = consulta + "order by 1,3";
            Query q = em.createQuery(consulta);
            List<Object[]> l = q.getResultList();
            String pais_ant = "";
            for (Object[] obj : l) {
                String pais_act = (String)obj[0];
                if (!pais_act.equals(pais_ant)) {
                    pais_ant = pais_act;
                    System.out.println("Pais: " + pais_act + " - " + (String) obj[1]);
                }
                if (obj[2] != null) { // Doncs podria ser pais sense província
                    System.out.println("\tProvincia: " + (String) obj[2] + " - " + (String) obj[3]);
                }
            }

        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            System.out.print(ex.getCause() != null ? "Caused by:" + ex.getCause().getMessage() + "\n" : "");
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
