package it.unisa.dottorato.exception;

/**Classe dell'oggetto LinkException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>link</code>
 *
 * @author andre
 */
public class LinkException extends Exception{
    
    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'Ilò link è sbagliato'
     * 
     */
     public LinkException () {
        super("Il link è sbagliato");
    }
   
     /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio pMessage
     * @param pMessage 
     * 
     */
     public LinkException(String pMessage) {
        super(pMessage);
    }
}