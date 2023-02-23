/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import org.milaifontanals.biblioteca.Fitxa;
import org.milaifontanals.biblioteca.FitxaLlibre;
import org.milaifontanals.biblioteca.FitxaRevista;
import org.milaifontanals.biblioteca.Prestec;
import org.milaifontanals.biblioteca.Soci;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Professor
 */
public class P02_InserirDades {

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

            em.getTransaction().begin();
            Soci s1 = new Soci("Gotera", "Pepe", new Date(1980 - 1900, 1 - 1, 1), 'M');
            Soci s2 = new Soci("Flores", "Maria", new Date(1980 - 1900, 1 - 1, 1), 'F');
            Fitxa f1 = new FitxaRevista("R123456789", "Revista 1", 2020, 1);
            f1.setEsDeixa(Boolean.TRUE);
            Prestec p1 = new Prestec(s1, f1, new Date(2020 - 1900, 2 - 1, 1));
            System.out.println("Fem persistent p1");
            em.persist(p1);
            Fitxa f2 = new FitxaLlibre("L123456789", "Llibre 1");
            f2.setEsDeixa(Boolean.TRUE);
            Prestec p2 = new Prestec(s2, f2, new Date(2020 - 1900, 2 - 1, 2));
            System.out.println("Fem persistent p2");
            em.persist(p2);

            p1.setMomentRetorn(new Date(2020 - 1900, 2 - 1, 5));
            p2.setMomentRetorn(new Date(2020 - 1900, 2 - 1, 6));

            Prestec p3 = new Prestec(s1, f2, new Date(2020 - 1900, 3 - 1, 1));
            Prestec p4 = new Prestec(s2, f1, new Date(2020 - 1900, 3 - 2, 2));
            em.persist(p3);
            em.persist(p4);

            Iterator<Prestec> itePrestec;

            System.out.println("Prestecs efectuats per soci " + s1.getCognom1());
            itePrestec = s1.itePrestecs();
            while (itePrestec.hasNext()) {
                System.out.println("\t" + itePrestec.next());
            }
            System.out.println("Prestecs efectuats per soci " + s2.getCognom1());
            itePrestec = s2.itePrestecs();
            while (itePrestec.hasNext()) {
                System.out.println("\t" + itePrestec.next());
            }

            System.out.println("Prestecs de la fitxa " + f1.getReferencia());
            itePrestec = f1.itePrestecs();
            while (itePrestec.hasNext()) {
                System.out.println("\t" + itePrestec.next());
            }
            System.out.println("Prestecs de la fitxa " + f2.getReferencia());
            itePrestec = f2.itePrestecs();
            while (itePrestec.hasNext()) {
                System.out.println("\t" + itePrestec.next());
            }

            System.out.println("Anem a fer commit");
            em.getTransaction().commit();
            if (mostrar) {
                System.out.println("Acabem de fer commit");
                System.out.println("Primer 10 inserts i finalment 2 updates!!!");
                System.out.println("Explicació ordenada:");
                System.out.println("1. Insert de f1 a Fitxa");
                System.out.println("2. Insert de f1 a FitxaRevista");
                System.out.println("3. Insert de s1 a Soci");
                System.out.println("4. Insert de p1 a Prestec amb momentRetorn=NULL");
                System.out.println("5. Insert de f2 a Fitxa");
                System.out.println("6. Insert de f2 a FitxaLlibre");
                System.out.println("7. Insert de s2 a Soci");
                System.out.println("8. Insert de p2 a Prestec amb momentRetorn=NULL");
                System.out.println("9. Insert de p3 a Prestec amb momentRetorn=NULL");
                System.out.println("10. Insert de p4 a Prestec amb momentRetorn=NULL");
                System.out.println("11. Update de p1 per emplenar la data de retorn");
                System.out.println("12. Update de p2 per emplenar la data de retorn");
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
