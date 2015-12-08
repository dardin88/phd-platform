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
public class ExceptionErroreTitleNews extends Exception{
    
     public ExceptionErroreTitleNews() {
        super("Il titolo è sbagliato! ");
    }
   
     public ExceptionErroreTitleNews(String pMessage) {
        super(pMessage);
    }
}
