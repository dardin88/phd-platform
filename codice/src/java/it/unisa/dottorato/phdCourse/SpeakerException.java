package it.unisa.dottorato.phdCourse;

/**Classe per la gestione delle eccezioni degli speaker
 *
 * @author Rembor
 */
class SpeakerException extends Exception {
    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'Lo speaker e' sbagliato'
     * 
     */
    public SpeakerException () {
        super("Lo speaker e' Sbagliato! ");
    }
   /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio pMessage
     * @param pMessage
     * 
     */
     public SpeakerException(String pMessage) {
        super(pMessage);
    }
}
