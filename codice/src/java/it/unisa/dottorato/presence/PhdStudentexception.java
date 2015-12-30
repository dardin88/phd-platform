/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.presence;

/**
 *
 * @author Rembor
 */
class PhdStudentexception extends Exception {
  public PhdStudentexception() {
        super("la stringa è vuota! ");
    }
   
     /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio pMessage
     * @param pMessage
     * 
     */
     public PhdStudentexception(String pMessage) {
        super(pMessage);
    }   
}
