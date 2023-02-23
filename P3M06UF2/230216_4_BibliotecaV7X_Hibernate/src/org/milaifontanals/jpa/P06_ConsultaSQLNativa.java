/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

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
public class P06_ConsultaSQLNativa {

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

            // Consulta de socis amb número d'inscripcions efectuades
            String consulta = "select s.codi, s.cognom1, s.nom, count(i.soci) ";
            consulta = consulta + "from soci s left join inscripcio i on s.codi = i.soci ";
            consulta = consulta + "group by s.codi, s.cognom1, s.nom ";
            consulta = consulta + "order by 2,3";
            
            Query q = em.createNativeQuery(consulta);
            List<Object []> ll = q.getResultList();
            System.out.println("Socis amb número d'inscripcions:");
            for (Object[] t : ll) {
                System.out.println("Soci :"+t[0]);
                System.out.println("\tCognom1     : "+t[1]);
                System.out.println("\tNom         : "+t[2]);
                System.out.println("\tInscripcions: "+t[3]);
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
