package it.unisa.dottorato.exception;

/**Classe dell'oggetto IstitutionException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>istitution</code>
 *
 * @author andre
 */
public class IstitutionException extends Exception{
    
    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'L'istituzione è sbagliata'
     * 
     */
     public IstitutionException () {
        super("L'istituzione è sbagliata");
    }
   
     /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio pMessage
     * @param pMessage
     * 
     */
     public IstitutionException(String pMessage) {
        super(pMessage);
    }
}
