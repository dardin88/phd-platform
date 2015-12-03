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
public class MissingDataEccezione extends Exception{
    
     public MissingDataEccezione() {
        super("Data is missing! ");
    }
   
     public MissingDataEccezione(String pMessage) {
        super(pMessage);
    }
}
