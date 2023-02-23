/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import org.milaifontanals.biblioteca.Autor;
import org.milaifontanals.biblioteca.FitxaLlibre;
import java.util.HashMap;
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

            System.out.println("Llibres amb la seva autoria");
            System.out.println("***************************");
            String consulta = "select fl from FitxaLlibre fl order by fl.referencia";
            Query q = em.createQuery(consulta);
            List<FitxaLlibre> llibres = q.getResultList();
            for (FitxaLlibre ll : llibres) {
                System.out.println(ll);
                boolean trobat = false;
                System.out.println("Autoria:");
                Iterator<Autor> ite = ll.iteAutors();
                while (ite.hasNext()) {
                    trobat=true;
                    System.out.println("\t" + ite.next());
                }
                if (!trobat) {
                    System.out.println("\t***Desconegut***");
                }
            }

            System.out.println("Autors amb la seva autoria");
            System.out.println("**************************");
            consulta = "select a from Autor a order by nom";
            q = em.createQuery(consulta);
            List<Autor> autors = q.getResultList();
            for (Autor a : autors) {
                System.out.println(a);
                System.out.println("Llibres:");
                // Des d'autor NO tenim accés als seus llibres...
                // Cal executar una consulta!!!
                String sAux = "select fl from FitxaLlibre fl ";
                sAux = sAux + "join fl.autors a ";
                sAux = sAux + "where a.id = '" + a.getId() + "' ";
                sAux = sAux + " order by fl.referencia";
                // Per eficiència, la consulta caldria fer-la parametritzada
                // Com que id és String, cal introduir a.getId() entre cometes simples!!!
                // Amb una consulta parametritzada (veure P4) és més fàcil i eficient!!!
                Query qAux = em.createQuery(sAux);
                List<FitxaLlibre> llista = qAux.getResultList();
                if (llista.size() == 0) {
                    System.out.println("\t***Cap***");
                } else {
                    for (FitxaLlibre ll : llista) {
                        System.out.println("\t"+ll);
                    }
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
