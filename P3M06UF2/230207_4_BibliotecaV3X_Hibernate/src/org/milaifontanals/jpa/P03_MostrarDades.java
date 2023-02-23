/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import org.milaifontanals.biblioteca.Soci;
import org.milaifontanals.biblioteca.Telefon;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;
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
            emf = Persistence.createEntityManagerFactory(up);
            System.out.println("EntityManagerFactory creada");
            em = emf.createEntityManager();
            System.out.println();
            System.out.println("EntityManager creat");

            // Anem a mostrar tallers i les seves agendes
            String consulta = "select t.codi, t.titol, a.momentInici ";
            consulta = consulta + "from Taller t left join AgendaTallers a on t = a.taller ";
            consulta = consulta + "order by 1,3 desc";
            Query q = em.createQuery(consulta);
            List<Object[]> l = q.getResultList();
            String taller_ant = "";
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy HH:mm");
            for (Object[] obj : l) {
                String taller_act = (String) obj[0];
                if (!taller_ant.equals(taller_act)) {
                    taller_ant = taller_act;
                    System.out.println("Taller: " + taller_act + " - " + (String) obj[1]);
                }
                if (obj[2]!=null) {
                    GregorianCalendar gc = (GregorianCalendar)obj[2];
                    System.out.println("\tData: "+sdf.format(gc.getTime()));
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
