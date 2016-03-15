/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.phdCourse;

/**
 *
 * @author Giuseppe
 */
class CourseException extends Exception {
    
    
    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'Errore oggetto curriculum'
     * 
     */
    public CourseException() {
        super("Errore oggetto corso");
    }
    
    /** Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio <code>pMessage</code>
     * 
     * @param pMessage 
     */
    public CourseException(String pMessage) {
        super(pMessage);
    }
    
}
