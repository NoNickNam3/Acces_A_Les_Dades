/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa.xml;

import org.milaifontanals.biblioteca.Fitxa;
import org.milaifontanals.biblioteca.Prestec;
import org.milaifontanals.biblioteca.Soci;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import javax.persistence.TemporalType;

/**
 *
 * @author Professor
 */
public class P03_ComprovaCercaModificacioObjectes {

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
            
            // Suposant que hem executat el programa P02 ara mateix,
            // podem cercar els prestecs efectuats de la fitxa R111111111
            // amb data d'avui...
            // Per cert... L'aplicació no controla que una fitxa no pugui
            // tenir prestecs simultanis... Ja ho millorarem més endavant!!!

            String consulta = "select p from Prestec p where p.fitxa.referencia='R111111111' ";
            // ATENCIÓ!!! El camp momentPrestec és Date... Conté hora-minut-segon
            // i només sé que s'ha creat avui, però no hora-minut-segon.
            // JPA incorpora la funció to_char, com en Oracle
            // i podem introduir la data d'avui com un paràmetre (:nom) a la consulta:
            consulta = consulta + " and to_char(p.momentPrestec,'dd-mm-yyyy') = to_char(:data,'dd-mm-yyyy')";
            Query q = em.createQuery(consulta);
            // Abans d'executar hem d'emplenar el paràmetre
            q.setParameter("data", new Date());
            // Executem i recuperem els resultats... 
            // Per si hem executat vàries vegades avui el P02, hi haurà varis préstecs
            // i, per tant, cal recollir-los amb un q.getResultList()
            List<Prestec> l = q.getResultList();
            System.out.println("Prestecs efectuats avui de la fitxa R111111111");
            for (Prestec p: l) {
                System.out.println("Prestec número "+p.getNumero());
                System.out.println("al soci "+p.getSoci().getCodi());
                System.out.println("a les "+p.getMomentPrestec());
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
