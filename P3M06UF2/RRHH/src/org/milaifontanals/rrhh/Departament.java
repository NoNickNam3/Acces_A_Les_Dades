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
public class Departament {

    private int id;             // Obligatori
    private String nom;         // Obligatori
    private Empleat cap;        // No obligatori
    private List<Empleat> empleats = new ArrayList();
};
