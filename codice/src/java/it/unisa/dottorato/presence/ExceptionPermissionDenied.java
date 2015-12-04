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
public class ExceptionPermissionDenied extends Exception {

    /**
     * 
     */
    private static final long serialVersionUID = -6593436034986073011L;

    /**
     * 
     */
    public ExceptionPermissionDenied() {
        super("permesso negato!");
    }

    /**
     * Genera l'eccezione con un messagio di errore associato
     * 
     * @param pMessage
     *            Il messaggio di errore che deve essere associato all'eccezione
     */
    public ExceptionPermissionDenied(String pMessage) {
        super(pMessage);
    }

    
}
  

