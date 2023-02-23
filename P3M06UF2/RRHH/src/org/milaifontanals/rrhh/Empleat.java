/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.rrhh;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 *
 * @author Usuari
 */
public class Empleat {
    private int id;             // Obligatori
    private String nom;         // No obligatori
    private String cognom;      // Obligatori
    private String eMail;       // Obligatori
    private String telefon;     // No obligatori
    private Date dataAlta;      // Obligatori
    private Treball treball;    // Obligatori
    private Double salari;      // No obligatori     
    private Float comissio;     // No obligatori
    private Departament departament;    // No obligatori
    private List<Contracte> contractes = new ArrayList();
};
