/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.rrhh;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Usuari
 */
public class Treball {
    private String id;      // Obligatori
    private String titol;   // Obligatori 
    private Integer salariMinim;       // No obligatori
    private Integer salariMaxim;       // No obligatori
    private List<Contracte> contractes = new ArrayList();
};
