/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.news;

/**
 *
 * @author Rembor
 */
public class ExceptionErroreDescriprion extends Exception{
    
     public ExceptionErroreDescriprion() {
        super("La descrizione è sbagliata! ");
    }
   
     public ExceptionErroreDescriprion(String pMessage) {
        super(pMessage);
    }
}
