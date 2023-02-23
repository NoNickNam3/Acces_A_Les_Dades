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
public class P05_InstruccióNoSelectSQLNativa {

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

            // Instrucció SQL (no JPA) que crea una taula (DDL)
            System.out.println("Procedim a intentar crear la taula PROVA");
            String instruccio = "create table prova ( ";
            instruccio = instruccio + "  codi number(2) constraint prova_PK primary key, ";
            instruccio = instruccio + "  nom varchar2(20)) ";
            em.getTransaction().begin();
            Query q = em.createNativeQuery(instruccio);
            try {
                int resultat = q.executeUpdate();   // No caldria recollir el resultat... Si no va bé, peta... Per això dins try... catch...
                System.out.println("Taula creada");
                // Comprovació que resultat és ZERO en DDL
                System.out.println("Resultat = "+resultat);
            } catch (Exception ex) {
                System.out.println("No s'ha creat la taula");
                System.out.println("+Info: " + ex.getMessage());
                Throwable t = ex.getCause();
                while (t!=null) {
                    System.out.println("\t"+t.getMessage());
                    t= t.getCause();
                }
            }
            em.getTransaction().rollback();   // Recordeu que una instrucció DDL fa commmit automàtic a la BD...
            // Per tant, aquesta instrucció no te efecte... La taula, si s'ha creat, ha quedat creada SÍ o SÍ.
            
            System.out.println("Procedim a afegir tants socis com socis existents, ");
            System.out.println("amb igual codi+10000");
            instruccio = "insert into soci (codi, cognom1, nom, data_naix, sexe) ";
            instruccio = instruccio + "select codi+1000, cognom1, nom, data_naix, sexe ";
            instruccio = instruccio + "from soci ";
            em.getTransaction().begin();
            q = em.createNativeQuery(instruccio);
            try {
                int resultat = q.executeUpdate();   
                em.getTransaction().commit(); // Aquí sí que és necessari fer el commit
                System.out.println("S'ha inserit "+resultat+" socis");
                System.out.println("Comprovar-ho a la BD via SQL");
            } catch (Exception ex) {
                System.out.println("No s'ha efectuat la inserció");
                System.out.println("+Info: " + ex.getMessage());
                Throwable t = ex.getCause();
                while (t!=null) {
                    System.out.println("\t"+t.getMessage());
                    t= t.getCause();
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
