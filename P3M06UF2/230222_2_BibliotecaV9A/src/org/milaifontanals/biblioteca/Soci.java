/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.biblioteca;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.TableGenerator;
import javax.persistence.Transient;
import javax.persistence.UniqueConstraint;
import java.util.Collections;

@Entity
@Table(name = "SOCI", // No cal, per què coincideix amb el que crearia JPA automàticament
        indexes = {
            @Index(name = "SOCI_IDX_COGNOMS_NOM",
                    columnList = "cognom1,cognom2,nom"
            )
        }
)
@NamedQueries({
    @NamedQuery(name = "SocisOrdenatsPerCognom",
            query = "select s from Soci s order by s.cognom1"),
    @NamedQuery(name = "SocisAmbCognom1SuperiorA",
            query = "select s from Soci s where upper(s.cognom1)>:inicial")
})
public class Soci implements Serializable {

    @Id
    @TableGenerator(name = "gen_clau_table", table = "comptadors",
            pkColumnName = "taula",
            valueColumnName = "comptador",
            pkColumnValue = "soci",
            initialValue = 100,
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "gen_clau_table")
    private int codi;           // Estrictament positiu - GESTIONAT per JPA
    @Basic(optional = false)
    @Column(nullable = false, length = 30)
    private String cognom1;     // Obligatori i no buit
    @Column(length = 30)
    private String cognom2;     // No obligatori o buit
    @Basic(optional = false)
    @Column(nullable = false, length = 30)
    private String nom;         // Obligatori i no buit
    @Basic(optional = false)
    @Column(name = "data_naix", nullable = false)
    private Date dataNaix;     // Obligatori
//    @Column(nullable = false, columnDefinition = "char check (sexe in ('M','F'))")
    // La marca anterior no funciona en MySQL, per la sintaxi "create table" que genera Hibernate
    @Basic(optional = false)
    @Column(columnDefinition = "char check (sexe in ('M','F'))")
    private char sexe;          // (M)ale / (F)emale
    @Embedded
    private Tutoria tutoria;    // No obligatori
    @ElementCollection
    @CollectionTable(name = "soci_telefons",
            joinColumns = @JoinColumn(name = "soci",
                    foreignKey = @ForeignKey(name = "soci_telefons_fk_soci")),
            uniqueConstraints = @UniqueConstraint(columnNames = {"soci", "numero"},
            name = "soci_telefons_un_soci_numero"))
    private List<Telefon> telefons = new ArrayList();

    @OneToMany(mappedBy = "soci")
    private List<Prestec> prestecs = new ArrayList();
    
    @ManyToMany(mappedBy = "socis", cascade = CascadeType.PERSIST)
    @Access(AccessType.PROPERTY)
    private List<AgendaTallers> tallers = new ArrayList();
    
    @Transient
    private int numTallers;
    
    public int getNumTallers() {
        return numTallers;
    }

    private List<AgendaTallers> getTallers() {
        return tallers;
    }

    private void setTallers(List<AgendaTallers> tallers) {
        this.tallers = tallers;
        this.numTallers = tallers.size();
    }
    
    protected Soci() {
//        System.out.println("Entro CONSTRUCTOR Soci");
    }

    public Soci(String cognom1, String nom, Date dataNaix, char sexe) {
        setCognom1(cognom1);
        setNom(nom);
        setDataNaix(dataNaix);
        setSexe(sexe);
    }

    public int getCodi() {
        return codi;
    }

    public String getCognom1() {
        return cognom1;
    }

    public void setCognom1(String cognom1) {
        if (cognom1 == null || cognom1.length() == 0) {
            throw new SociException("Cognom1 obligatori i no buit");
        }
        this.cognom1 = cognom1;
    }

    public String getCognom2() {
        return cognom2;
    }

    public void setCognom2(String cognom2) {
        if (cognom2 != null && cognom2.length() == 0) {
            throw new SociException("Cognom2 null o amb contingut");
        }
        this.cognom2 = cognom2;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        if (nom == null || nom.length() == 0) {
            throw new SociException("Nom obligatori i no buit");
        }
        this.nom = nom;
    }

    public Date getDataNaix() {
        return dataNaix;
    }

    public void setDataNaix(Date dataNaix) {
        if (dataNaix == null) {
            throw new SociException("Data de naixement obligatòria");
        }
        this.dataNaix = (Date) dataNaix.clone();
    }

    public char getSexe() {
        return sexe;
    }

    public void setSexe(char sexe) {
        sexe = Character.toUpperCase(sexe);
        if (sexe != 'M' && sexe != 'F') {
            throw new SociException("Valors vàlids per sexe: (M)ale / (F)emale");
        }
        this.sexe = sexe;
    }

    public Tutoria getTutoria() {
        return tutoria;
    }

    public void setTutoria(Tutoria tutoria) {
        this.tutoria = tutoria;
    }

    public boolean addTelefon(Telefon tel) {
        if (!telefons.contains(tel)) {
            telefons.add(tel);
            return true;
        } else {
            return false;
        }
    }

    public Iterator<Telefon> iteTelefons() {
        return Collections.unmodifiableCollection(telefons).iterator();
    }

    public boolean removeTelefon(Telefon tel) {
        return telefons.remove(tel);
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

    /**
     * Iterator que permet accedir als prestecs d'un soci.
     */
    public Iterator<Prestec> itePrestecs() {
        return prestecs.iterator();
    }

    /**
     * Mètode que assigna el préstec a la llista de préstecs del soci. Necessari
     * per poder afegir el préstec al soci en crear el prestec però sense
     * utilitat per al desenvolupament d'aplicacions, ja que un préstec té un
     * soci des de la seva creació i és immutable. Per això no és public.
     * L'utilitza el mètode Prestec.setSoci(). Les classes del mateix paquet hi
     * tenen accés
     *
     * @param prestec
     */
    final void addPrestec(Prestec prestec) {
        prestecs.add(prestec);
    }

    public Iterator<AgendaTallers> iteTallers() {
        return Collections.unmodifiableCollection(tallers).iterator();
    }

    public void addTaller(AgendaTallers at) {
        if (at == null) {
            throw new RuntimeException("No es pot afegir una agendaTallers nul");
        }
        if (!tallers.contains(at)) {
            tallers.add(at);
            at.addSoci(this);
        }
    }

    public void removeTaller(AgendaTallers at) {
        if (tallers.contains(at)) {
            tallers.remove(at);
            at.removeSoci(this);
        }
    }

}
