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
import javax.persistence.PersistenceException;
import javax.persistence.Query;
import org.milaifontanals.biblioteca.Soci;
import org.milaifontanals.biblioteca.Taller;

/**
 *
 * @author Professor
 */
public class P03_InstruccionsDeleteViaJPA_KO {

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
            HashMap<String, String> propietats = new HashMap();
            propietats.put("hibernate.show_sql", "true");
            emf = Persistence.createEntityManagerFactory(up, propietats);
            System.out.println("EntityManagerFactory creada");
            em = emf.createEntityManager();
            System.out.println();
            System.out.println("EntityManager creat");
            System.out.println("Activada la visualització d'instruccions SQL");

            System.out.println("Suposem que la BD té les dades que insereix P02");
            System.out.println("");

            String instruccio = "delete from Soci s where upper(s.cognom1)>'P'";
            Query q = em.createQuery(instruccio);
            System.out.println("Eliminem els socis amb cognom superior a 'P'");
            System.out.println("N'hi ha dos");
            em.getTransaction().begin();
            int resultat = q.executeUpdate();
            em.getTransaction().commit();
            System.out.println("Socis eliminats: " + resultat);
            System.out.println("Fixeu-vos que abans de fer el DELETE FROM SOCI");
            System.out.println("ha fet el DELETE FROM INSCRIPCIO...");

            try {
                instruccio = "delete from Taller t where t.codi='LI01'";
                q = em.createQuery(instruccio);
                System.out.println("Intentem eliminar el taller amb codi 'LI01'");
                System.out.println("Existeix i té 3 planificacions agendades");
                System.out.println("Hauria d'eliminar les planificacions agendades prèviament");
                em.getTransaction().begin();
                resultat = q.executeUpdate();
                em.getTransaction().commit();
                System.out.println("Tallers eliminats amb la seva agenda: " + resultat);
                System.out.println("Aquí ha actuat 'on delete cascade' de la FK");
            } catch (PersistenceException ex) {
                System.out.println("No s'ha pogut eliminar el taller");
                infoError(ex);
                em.getTransaction().rollback();
            }

            try {
                instruccio = "delete from Soci s where s.cognom1='Mendez'";
                q = em.createQuery(instruccio);
                System.out.println("Intentem eliminar el soci amb cognom1='Mendez'");
                System.out.println("Existeix i té 1 préstec");
                System.out.println("Hauria d'eliminar el préstec prèviament");
                em.getTransaction().begin();
                resultat = q.executeUpdate();
                em.getTransaction().commit();
                System.out.println("Socis eliminats amb els seus préstecs:" + resultat);
            } catch (PersistenceException ex) {
                System.out.println("No s'ha pogut eliminar el soci");
                infoError(ex);
                em.getTransaction().rollback();
                System.out.println("En JPQL, CascadeType no actua...");
                System.out.println("Carreguem el soci en memòria i procedim a eliminar-lo via remove");
                q = em.createQuery("select s from Soci s where cognom1='Mendez'");
                List<Soci> ll = q.getResultList();
                for (Soci s : ll) {
                    em.remove(s);
                    em.getTransaction().begin();
                    em.getTransaction().commit();
                    System.out.println("Observem que primer ha eliminat préstec (per CascadeType.REMOVE)");
                    System.out.println("i els telèfons (per la FK existent a nivell de BD");
                    System.out.println("i finalment el soci");
                }
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            infoError(ex);
        } finally {

            if (em != null) {
                if (em.getTransaction().isActive()) {
                    em.getTransaction().rollback();
                }
                em.close();
                System.out.println("EntityManager tancat");
            }
            if (emf != null) {
                emf.close();
                System.out.println("EntityManagerFactory tancada");
            }
        }
    }

    private static void infoError(Throwable ex) {
        System.out.println(ex.getMessage());
        while ((ex = ex.getCause()) != null) {
            System.out.println("\t" + ex.getMessage());
        }
    }
}
