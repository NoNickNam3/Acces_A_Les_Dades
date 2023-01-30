package org.milaifontanals;


import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;
import java.util.Properties;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.milaifontanals.biblioteca.*;

/**
 *
 * @author Ivan
 * 
 */
public class persist {
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
            Logger.getLogger(persist.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(persist
                    .class.getName()).log(Level.SEVERE, null, ex);
        }
        EntityManagerFactory emf = null;
        EntityManager em = null;
        try {
            System.out.println("Intent amb " + up);
            emf = Persistence.createEntityManagerFactory(up, p);
            System.out.println("EntityManagerFactory creada");
            em = emf.createEntityManager();
            System.out.println("EntityManager creat");
            
            String nom = UUID.randomUUID().toString();
            FitxaLlibre fl = new FitxaLlibre(nom.substring(0, 10),  "Llibre");
            FitxaRevista fr = new FitxaRevista(nom.substring(1, 11),  "Revista", 5, 5);
            
            em.getTransaction().begin();
            em.persist(fl);
            em.persist(fr);
            em.getTransaction().commit();
            
            Query q = em.createQuery("select fl from FitxaLlibre fl", Fitxa.class);
            List<Fitxa> fitxes = q.getResultList();
            em.getTransaction().begin();
            
            for(Fitxa f : fitxes){
                if(f instanceof FitxaRevista) {
                    ((FitxaRevista)f).setTitol("Titolo de la revista <" + f.getReferencia() + ">");
                }else if(f instanceof FitxaLlibre){
                    ((FitxaLlibre)f).setTitol("Titolo del llibre <" + f.getReferencia() + ">");
                }
                em.persist(f);
            }
            
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
