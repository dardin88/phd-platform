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
public class DescriptionException extends Exception{
    
     public DescriptionException () {
        super("Il titolo e' sbagliato! ");
    }
   
     public DescriptionException(String pMessage) {
        super(pMessage);
    }
}
