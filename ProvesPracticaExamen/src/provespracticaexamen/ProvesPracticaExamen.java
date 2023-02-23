/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package provespracticaexamen;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Date;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import modelprovapracticaexamen.Departament;
import modelprovapracticaexamen.Empleat;
import modelprovapracticaexamen.Client;

/**
 *
 * @author Ivan
 */
public class ProvesPracticaExamen {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        if (args.length != 1) {
            System.out.println("Un únic argument amb el nom de la Unitat de Persistència");
            System.exit(1);
        }
        String up = args[0];
        Properties p = new Properties();
        try {
            p.load(new FileInputStream("config.properties"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(ProvesPracticaExamen.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(ProvesPracticaExamen.class.getName()).log(Level.SEVERE, null, ex);
        }
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            System.out.println("Intent amb " + up);
            emf = Persistence.createEntityManagerFactory(up, p);
            System.out.println("EntityManagerFactory creada");
            em = emf.createEntityManager();
            System.out.println("EntityManager creat");
            
            em.getTransaction().begin();
            Empleat emp = new Empleat(02, "soriano", "panadero", new Date(2022, 1, 5), 5500.23, 222.44331);
            em.persist(emp);
            Departament dept = new Departament("departament 01", "local");
            em.persist(dept);
            Client cl = new Client("CLIENT 01", "", "", "", "", 0, "", 0, 0, "");
            em.persist(cl);
            em.getTransaction().commit();
            
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
