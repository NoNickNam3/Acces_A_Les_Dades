/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals;

import java.util.Date;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.milaifontanals.rrhh.Departament;
import org.milaifontanals.rrhh.Empleat;
import org.milaifontanals.rrhh.Treball;

/**
 *
 * @author Usuari
 */
public class P04_GestioDades1 {
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

            Departament d = new Departament (33,"Festes");
            em.persist(d);
            Treball t = new Treball("PS","Professor de Secundària");
            Empleat e = new Empleat("Gotera","gotera@gmail.com",new Date(),t);
            e.setDepartament(d);
            d.setCap(e);
            em.persist(e);
            em.getTransaction().begin();
            em.getTransaction().commit();
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
