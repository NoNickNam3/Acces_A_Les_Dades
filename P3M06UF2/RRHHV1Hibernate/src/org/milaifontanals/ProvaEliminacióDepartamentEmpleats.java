/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals;

import java.util.HashMap;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.milaifontanals.rrhh.Departament;
import org.milaifontanals.rrhh.Empleat;

/**
 *
 * @author Usuari
 */
public class ProvaEliminacióDepartamentEmpleats {

    /**
     * @param args the command line arguments
     */
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

            // En situació inicial, només hi ha 1 empleat de departament 10
            // i en canvi hi ha 6 empleats de departament 30
            Departament d;
            int deptsPerProvar[] = {10, 30};
            for (int x : deptsPerProvar) {
                d = em.find(Departament.class, x);
                System.out.println("Intentem eliminar departament " + x);
                if (d == null) {
                    throw new LocalException("Ja no existeix departament " + x);
                }
                System.out.println("Existeix departament "+x);
                System.out.println("Procedim a eliminar-lo");
                em.remove(d);
                System.out.println("Eliminació efectuada, pendent de commit");
            }
            em.getTransaction().begin();
            em.getTransaction().commit();
            System.out.println("Commit efectuat!");
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
