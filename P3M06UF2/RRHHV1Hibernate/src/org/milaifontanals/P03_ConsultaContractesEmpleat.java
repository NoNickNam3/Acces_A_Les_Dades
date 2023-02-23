/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals;

import java.util.List;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.milaifontanals.rrhh.Contracte;
import org.milaifontanals.rrhh.Empleat;

/**
 *
 * @author Usuari
 */
public class P03_ConsultaContractesEmpleat {

    public static void main(String[] args) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        String up = "UP-Oracle-Hibernate";
        try {
            em = null;
            emf = null;
            System.out.println("Intent amb " + up);
            emf = Persistence.createEntityManagerFactory(up);
            System.out.println("EntityManagerFactory creada");
            em = emf.createEntityManager();
            System.out.println();
            System.out.println("EntityManager creat");

            Scanner sc = new Scanner(System.in);
            System.out.println("Introdueixi codi d'empleat");
            int codi = 0;
            try {
                codi = Integer.parseInt(sc.nextLine());
            } catch (NumberFormatException ex) {
                throw new LocalException("Valor erroni");
            }
            Empleat e = em.find(Empleat.class, codi);
            if (e == null) {
                throw new LocalException("No existeix empleat amb codi " + codi);
            }
            System.out.println("Empleat: " + e);
            Query q = em.createNamedQuery("trobaContractesDeEmpleatPerId");
            q.setParameter("id", codi);
            List<Contracte> ll = q.getResultList();
            if (ll.isEmpty()) {
                System.out.println("No té contractes");
            } else {
                System.out.println("Contractes:");
                System.out.println("**********");
                for (Contracte c : ll) {
                    System.out.println("\t" + c);
                }
            }
        } catch (LocalException ex) {
            System.out.println(ex);
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            System.out.print(ex.getCause() != null ? "Caused by:" + ex.getCause().getMessage() + "\n" : "");
            System.out.println("Traça:");
            ex.printStackTrace();
        } finally {
            if (em != null) {
                em.close();
                System.out.println("EntityManager tancat");
            }
            if (emf != null) {
                emf.close();
                System.out.println("EntityManagerFactory tancada");
            }
        }
    }

}
