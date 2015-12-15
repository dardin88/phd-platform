package it.unisa.dottorato.exception;

/**Classe dell'oggetto IstitutionException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>publicationIssue</code>
 *
 * @author andre
 */
public class PublicationIssueException extends Exception{
    
     public PublicationIssueException () {
        super("Il campo publicationIssue è sbagliato");
    }
   
     public PublicationIssueException(String pMessage) {
        super(pMessage);
    }
}