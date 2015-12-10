/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.exception;

/**
 *
 * @author Rembor
 */
public class DateException extends Exception{
    
     public DateException () {
        super("La data e' sbagliata! ");
    }
   
     public DateException(String pMessage) {
        super(pMessage);
    }
}
