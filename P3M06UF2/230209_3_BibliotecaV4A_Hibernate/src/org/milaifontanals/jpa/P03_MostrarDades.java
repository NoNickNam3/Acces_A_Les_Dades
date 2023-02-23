/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import org.milaifontanals.biblioteca.Prestec;
import org.milaifontanals.biblioteca.Soci;
import java.util.HashMap;
import java.util.Iterator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
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

            // Anem a recuperar un prestec, concretament el primer ordenat per numero
            System.out.println("Cerquem el préstec amb menor número");
            String consulta = "select p from Prestec p order by p.numero";
            Query q = em.createQuery(consulta);
            q.setMaxResults(1);     // Per obtenir només 1 resultat !!!
            Prestec p;
            p = (Prestec) q.getSingleResult();    // Sabem que només hi haurà un resultat
            // i suposant que prèviament s'ha executat P02, segur que hi ha un resultat!!!
            // Si no... petarà amb NoResultException
            if (mostrar) {
                System.out.println("Hem efectuat la consulta");
            }
            System.out.println("Primer prestec: " + p.getNumero());
            // No hem invocat p.toString per que tal i com està programat, mostraria informació del 
            // soci i de la fitxa i això forçaria que JPA anés a cercar el soci i la fitxa
            System.out.println(">> Soci: " + p.getSoci().getCodi());
            System.out.println(">> Fitxa: " + p.getFitxa().getReferencia());
            if (mostrar) {
                System.out.println("Hem mostrat el codi del soci sense que hagi fet SELECT from soci");
                System.out.println("Hem mostrar la refer+encia de la fitxa sense que hagi fet SELECT from fitxa");
            }
            // Anem a cercar el soci del prestec anterior, que com a mínim sabem que té aquest prestec i
            // (segons dades introduïdes en P02), tindrà un altre prestec
            System.out.println("Cerquem el soci del préstec");
            Soci s = em.find(Soci.class, p.getSoci().getCodi());
            if (mostrar) {
                System.out.println("Ara ha efectuat la consulta SELECT from soci");
            }
            System.out.println("Soci: " + s);
            System.out.println(">> Prestecs: ");
            Iterator<Prestec> itePrestecs = s.itePrestecs();
            if (mostrar) {
                System.out.println("Ara ha efectuat la consulta SELECT from prestec");
            }
            while (itePrestecs.hasNext()) {
                System.out.println("\t" + itePrestecs.next().getNumero());
            }
        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            System.out.print(ex.getCause() != null ? "Caused by:" + ex.getCause().getMessage() + "\n" : "");
            System.out.println("Traça:");
            ex.printStackTrace();
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
}
