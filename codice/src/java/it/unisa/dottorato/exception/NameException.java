/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.exception;

/**
 *
 * @author Rembor
 * poichè la madonna è bastarda ci sta pure nella gestione account, curriculum,seminario
 */
public class NameException extends Exception{
    
     public NameException () {
        super("Il porco dio di nome  e' sbagliato! ");
    }
   
     public NameException(String pMessage) {
        super(pMessage);
    }
}
