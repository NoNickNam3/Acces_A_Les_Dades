/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelprovapracticaexamen;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.TableGenerator;

/**
 *
 * @author Ivan
 */
@Entity
public class Departament {
    @Id
    @TableGenerator(name = "genClauDepartament", table = "ComptadorsTaula",
            pkColumnName = "dept",
            valueColumnName = "comptadorDepartaments",
            pkColumnValue = "dept",
            allocationSize = 1
    )
    @GeneratedValue(strategy = GenerationType.TABLE, generator = "genClauDepartament")
    private int dept_no;
    @Column(length = 30)
    private String dNom;
    @Column(length = 30)
    private String localitat;

    protected Departament(){}
    
    public Departament(String nom, String loc){
        setdNom(nom);
        setLocalitat(loc);
    }
    
    public int getDept_no() {
        return dept_no;
    }

    public String getdNom() {
        return dNom;
    }

    public void setdNom(String dNom) {
        this.dNom = dNom;
    }

    public String getLocalitat() {
        return localitat;
    }

    public void setLocalitat(String localitat) {
        this.localitat = localitat;
    }

    
}
