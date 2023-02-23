/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.rrhh;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author Usuari
 */
@Entity
@Table(name = "JOB_HISTORY")
@Access(AccessType.FIELD)
@IdClass(ContracteId.class)
public class Contracte implements Serializable {

    @Id
    @Column(name = "EMPLOYEE_ID")
    private int idEmpleat;        // Obligatori
    @ManyToOne(optional = false, cascade = CascadeType.PERSIST,
            fetch = FetchType.LAZY)
    @JoinColumn(name = "EMPLOYEE_ID", insertable = false, updatable = false,
            foreignKey=@ForeignKey(name="JHIST_EMP_FK",
                    foreignKeyDefinition="FOREIGN KEY (EMPLOYEE_ID) REFERENCES EMPLOYEES"))
    private Empleat empleat;              // Obligatori

    @Id
    @Column(name = "START_DATE")
    @Temporal(value=TemporalType.DATE)
    private Date dataInici;         // Obligatori
    @Basic(optional = false)
    @Column(name = "END_DATE", nullable=false)
    @Temporal(TemporalType.DATE)
    private Date dataFi;            // Obligatori
    @ManyToOne(optional = false, fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "JOB_ID", nullable=false,
            foreignKey = @ForeignKey(name="JHIST_JOB_FK",
                    foreignKeyDefinition="FOREIGN KEY (JOB_ID) REFERENCES JOBS"))
    private Treball treball;        // Obligatori
    @ManyToOne(fetch = FetchType.LAZY, cascade = CascadeType.PERSIST)
    @JoinColumn(name = "DEPARTMENT_ID",
            foreignKey = @ForeignKey(name="JHIST_DEPT_FK",
                    foreignKeyDefinition="FOREIGN KEY (DEPARTMENT_ID) REFERENCES DEPARTMENTS"))
    private Departament departament;    // No obligatori

    protected Contracte() {

    }

    public Contracte(Empleat empleat, Date dataInici, Date dataFi, Treball treball) {
        setEmpleat(empleat);
        setDataInici(dataInici);
        setDataFi(dataFi);
        setTreball(treball);
    }

    public Empleat getEmpleat() {
        return empleat;
    }

    private void setEmpleat(Empleat empleat) {
        if (empleat == null) {
            throw new RuntimeException("En un préstec, el soci és obligatori");
        }
        empleat.addContracte(this);
        this.empleat = empleat;
        this.idEmpleat = empleat.getId();
    }

    public Date getDataInici() {
        return dataInici;
    }

    private void setDataInici(Date dataInici) {
        this.dataInici = dataInici;
    }

    public Date getDataFi() {
        return dataFi;
    }

    public void setDataFi(Date dataFi) {
        this.dataFi = dataFi;
    }

    public Treball getTreball() {
        return treball;
    }

    public void setTreball(Treball treball) {
        this.treball = treball;
    }

    public Departament getDepartament() {
        return departament;
    }

    public void setDepartament(Departament departament) {
        this.departament = departament;
    }

    @Override
    public String toString() {
        return "Contracte{" + "idEmpleat=" + idEmpleat + ", empleat=" + empleat + ", dataInici=" + dataInici + ", dataFi=" + dataFi + '}';
    }

};
