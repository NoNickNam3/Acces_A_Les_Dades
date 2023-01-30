/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.milaifontanals.biblioteca;

/**
 *
 * @author Usuari
 */
public class PrestecException extends RuntimeException {

    public PrestecException(String message) {
        super(message);
    }

    public PrestecException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
