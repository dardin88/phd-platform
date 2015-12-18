package it.unisa.dottorato.phdCourse;

/** Classe per la gestione delle eccezioni delle classi
 *
 * @author Rembor
 */
class ClassroomException extends Exception {
    
    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'La classe e' sbagliata! '
     * 
     */
    public ClassroomException() {
        super("La classe e' Sbagliata! ");
    }
    
    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio pMessage
     * @param pMessage
     * 
     */
     public ClassroomException(String pMessage) {
        super(pMessage);
    }
}
