package it.unisa.dottorato.phdCourse;

/**Classe per la gestione delle eccezioni dei luoghi
 *
 * @author Rembor
 */
class PlaceException extends Exception {
    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'Il posto e' sbagliato!'
     * 
     */
   public PlaceException() {
        super("Il posto e' sbagliato! ");
    }
   /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio pMessage
     * @param pMessage
     * 
     */
     public PlaceException(String pMessage) {
        super(pMessage);
    }
}