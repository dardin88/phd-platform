package it.unisa.dottorato.exception;

/**Classe dell'oggetto PublicationIssueException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>publicationIssue</code>
 *
 * @author andre
 */
public class PublicationIssueException extends Exception{
    
    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'Il campo publicationIssueException e' sbagliato'
     * 
     */
     public PublicationIssueException () {
        super("Il campo publicationIssue è sbagliato");
    }
   
     /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio pMessage
     * @param pMessage
     * 
     */
     public PublicationIssueException(String pMessage) {
        super(pMessage);
    }
}