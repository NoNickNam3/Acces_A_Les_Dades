/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.jpa;

import java.util.Scanner;

/**
 *
 * @author Usuari
 */
public class Utils {

    static boolean mostrarInstruccionsSQL() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Vol visualitzar les instruccions SQL (S/N)?");
        String resposta = null;
        do {
            resposta = sc.nextLine();
            resposta = resposta.toUpperCase();
        } while (!resposta.equals("S") && !resposta.equals("N"));
        return resposta.equals("S");
    }

}
