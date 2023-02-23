/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import org.milaifontanals.biblioteca.Soci;
import org.milaifontanals.biblioteca.AgendaTallers;
import org.milaifontanals.biblioteca.Taller;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import org.milaifontanals.biblioteca.FitxaLlibre;
import org.milaifontanals.biblioteca.Prestec;
import org.milaifontanals.biblioteca.Telefon;

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

            Soci s0 = new Soci("Mendez", "Joana", new Date(1980 - 1900, 1 - 1, 1), 'M');
            em.persist(s0);
            Soci s1 = new Soci("Sanchez", "Maria", new Date(1981 - 1900, 2 - 1, 2), 'F');
            em.persist(s1);
            Soci s2 = new Soci("Caco", "Pau", new Date(1982 - 1900, 3 - 1, 3), 'M');
            em.persist(s2);
            Soci s3 = new Soci("Rodriguez", "Joan", new Date(1984 - 1900, 44 - 1, 4), 'F');
            em.persist(s3);
            
            s0.addTelefon(new Telefon("93805000",'F'));
            s0.addTelefon(new Telefon("654121212",'M'));

            Taller t = new Taller("LI01", "Lectura infantil");
            AgendaTallers at0 = new AgendaTallers(t, new GregorianCalendar(2020, 4, 3, 19, 30));
            AgendaTallers at1 = new AgendaTallers(t, new GregorianCalendar(2020, 5, 4, 18, 0));
            // s1 participa a at1
            at1.addSoci(s1);
            AgendaTallers at2 = new AgendaTallers(t, new GregorianCalendar(2020, 4, 15, 18, 30));
            // s1 i s2 partipen a at2
            at2.addSoci(s1);
            s2.addTaller(at2);
            AgendaTallers at3 = new AgendaTallers(t, new GregorianCalendar(2020, 4, 16, 18, 30));
            // at3 amb 3 socis
            s1.addTaller(at3);
            at3.addSoci(s2);
            at3.addSoci(s3);

            // En fer persistents els socis, també es fan persistents les agendaTallers
            // (degut a Cascade.PERSIST en el camp Soci.tallers)
            // i la persistència en aquestes provoca persistència del seu taller
            // (degut a Cascade.PERSIST en el camp AgendaTallers.taller)
            // i els socis (per tenir el codi automàtic) els hem hagut de fer persistents
            // al principi, una vegada creats, per a que tinguin codi i el funcionament sigui ok
            FitxaLlibre f0 = new FitxaLlibre("R000000000", "Llibre R0");
            Prestec p0 = new Prestec(s0, f0, new Date());

            // Malgrat el soci s0 ja és persistent, aquest prestec p0 no es fa automàticament
            // persistent, per què Soci.prestecs (OneToMany) no té cascade.PERSIST. Per tant:
            em.persist(p0);
            // I com que Prestec.fitxa SÍ té cascade.PERSOIST, la fitxa f0 ja es fa persistent

            em.getTransaction().commit();

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
