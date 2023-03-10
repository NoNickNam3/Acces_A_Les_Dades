/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.biblioteca;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Id;
import javax.persistence.Persistence;
import javax.persistence.PersistenceUnit;
import javax.persistence.Transient;
import jdk.nashorn.internal.objects.annotations.Setter;
import net.bytebuddy.build.Plugin;

/**
 *
 * @author Usuari
 */
/*
@Table(
        name = "SOCI", 
        indexes = { 
            @Index(columnList="cognom1, congom2, nom", unique = true, name = "SOCI_IDX_COGNOMS_NOM")
        },
        uniqueConstraints = {
            @UniqueConstraint(columnNames = {"data_naix"})
        }
)
*/
@Entity
public class Soci implements Serializable {
    
    @Id
    private int codi;           // Estrictament positiu   
    
    @Basic(optional = false)
    @Column(length = 30, nullable = false)
    private String cognom1;     // Obligatori i no buit
    
    @Column(length = 30)
    private String cognom2;     // No obligatori o buit
    
    @Basic(optional = false)
    @Column(length = 30, nullable = false)
    private String nom;         // Obligatori i no buit

    @Basic(optional = false)
    @Column(name = "data_naix", nullable = false)
    private Date dataNaix;     // Obligatori
    
    @Basic(optional = false)
    @Column(columnDefinition = "char not null check(sexe in ('M', 'F'))")
    private char sexe;          // (M)ale / (F)emale
    
    @Transient
    private long prova;
    
    //EntityManagerFactory emf = Persistence.createEntityManagerFactory(up);
    //EntityManager em = emf.createEntityManager();
    
    protected Soci() {
    }
    
    public Soci(int codi, String cognom1, String nom, Date dataNaix, char sexe) {
        setCodi(codi);
        setCognom1(cognom1);
        setNom(nom);
        setDataNaix(dataNaix);
        setSexe(sexe);
    }

    public int getCodi() {
        return codi;
    }

    private final void setCodi(int codi) {
        if (codi<=0) {
            throw new SociException("Codi de soci ha de ser estrictament positiu");
        }
        this.codi = codi;
    }

    public String getCognom1() {
        return cognom1;
    }

    public final void setCognom1(String cognom1) {
        if (cognom1==null || cognom1.length()==0) {
            throw new SociException("Cognom1 obligatori i no buit");
        }
        this.cognom1 = cognom1;
    }

    public String getCognom2() {
        return cognom2;
    }

    public void setCognom2(String cognom2) {
        if (cognom2!=null && cognom2.length()==0) {
            throw new SociException("Cognom2 null o amb contingut");
        }
        this.cognom2 = cognom2;
    }

    public String getNom() {
        return nom;
    }

    public final void setNom(String nom) {
        if (nom==null || nom.length()==0) {
            throw new SociException("Nom obligatori i no buit");
        }
        this.nom = nom;
    }

    public Date getDataNaix() {
        return dataNaix;
    }

    public final void setDataNaix(Date dataNaix) {
        if (dataNaix==null) {
            throw new SociException("Data de naixement obligat??ria");
        }
        this.dataNaix = (Date) dataNaix.clone();
    }

    public char getSexe() {
        return sexe;
    }

    public final void setSexe(char sexe) {
        sexe = Character.toUpperCase(sexe);
        if (sexe!='M' && sexe!='F') {
            throw new SociException("Valors v??lids per sexe: (M)ale / (F)emale");
        }
        this.sexe = sexe;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + this.codi;
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Soci other = (Soci) obj;
        return this.codi == other.codi;
    }

    @Override
    public String toString() {
        return "Soci{" + "codi=" + codi + ", cognom1=" + cognom1 + ", cognom2=" + cognom2 + ", nom=" + nom + ", dataNaix=" + dataNaix + ", sexe=" + sexe + '}';
    }
}
