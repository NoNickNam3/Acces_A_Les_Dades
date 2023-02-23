/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import org.milaifontanals.biblioteca.Taller;
import java.util.Scanner;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author Professor
 */
public class P02_ProvesMerge {

    public static void main(String[] args) {

        if (args.length != 1) {
            System.out.println("Un únic argument amb el nom de la Unitat de Persistència");
            System.exit(1);
        }
        String up = args[0];
        EntityManagerFactory emf = null;
        EntityManager em = null;
        Scanner sc = new Scanner(System.in);
        try {
            System.out.println("Intent amb " + up);
            emf = Persistence.createEntityManagerFactory(up);
            System.out.println("EntityManagerFactory creada");
            em = emf.createEntityManager();
            System.out.println("EntityManager creat");

            /* Es suposa que la BD conté taller ('LI01','Lectura Infantil',null) */
            System.out.println("");
            System.out.println("PROVES de MERGE sobre OBJECTE NO PERSISTENT");
            Taller t = new Taller("XXXX", "Taller XXXX");
            System.out.println("Objecte t: " + t);
            System.out.println("t controlat per EM? " + em.contains(t));
            System.out.println("Fem mt=em.merge(t)");
            Taller mt = em.merge(t);
            System.out.println("Objecte mt resultant de merge(t) == t? " + (t == mt));
            System.out.println("Objecte mt: " + mt);
            System.out.println("mt i t no són el mateix objecte però sí tenen mateix contingut");
            System.out.println("t controlat per EM? " + em.contains(t));
            System.out.println("mt controlat per EM? " + em.contains(mt));
            System.out.println("=> merge per un nou objecte és equivalent a un persist però per l'objecte resultant ");

            System.out.println("");
            System.out.println("PROVES de MERGE sobre OBJECTE JA PERSISTENT via persist o merge");
            System.out.println("Fem mmt=em.merge(mt)");
            Taller mmt = em.merge(mt);
            System.out.println("Objecte mmt resultant de merge(mt) == mt? " + (mt == mmt));
            System.out.println("=> merge per un objecte ja persistent no te efecte; retorna el mateix objecte");

            System.out.println("");
            System.out.println("PROVES de MERGE sobre OBJECTE JA PERSISTENT recuperat de la BD");
            Taller tbd = em.find(Taller.class, "LI01");
            System.out.println("Objecte tbd: "+tbd);
            System.out.println("Fem mtbd=em.merge(tbd)");
            Taller mtbd = em.merge(tbd);
            System.out.println("Objecte mtbd resultant de merge(tbd) == tbd? " + (tbd == mtbd));
            System.out.println("=> merge per un objecte ja persistent no te efecte; retorna el mateix objecte");

            System.out.println("");
            System.out.println("PROVES de MERGE sobre OBJECTE NO PERSISTENT amb idèntic ID a un ja PERSISTENT");
            Taller tbis = new Taller("XXXX", "Taller DUPLICAT");
            System.out.println("Objecte tbis: " + tbis);
            System.out.println("tbis controlat per EM? " + em.contains(tbis));
            System.out.println("Recordem que mt creat anteriorment és persistent i té mateix CODI");
            System.out.println("Objecte mt: " + mt);
            System.out.println("Fem mtbis=em.merge(tbis)");
            Taller mtbis = em.merge(tbis);
            System.out.println("Objecte mtbis resultant de merge(tbis): " + mtbis);
            System.out.println("mtbis == tbis? " + (mtbis == tbis));
            System.out.println("memtbis == mt? " + (mtbis == mt));
            System.out.println("Objecte mt: " + mt);
            System.out.println("=> En fer merge d'un objecte amb ID igual a un ja persistent, es modifica el persistent!");
            System.out.println("En canvi, si haguessim fet persist d'un objecte amb ID igual a un ja persistent, donaria error!");

            System.out.println("Programa finalitzat");

        } catch (Exception ex) {
            System.out.println("Exception: " + ex.getMessage());
            System.out.print(ex.getCause() != null ? "Caused by:" + ex.getCause().getMessage() + "\n" : "");
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
