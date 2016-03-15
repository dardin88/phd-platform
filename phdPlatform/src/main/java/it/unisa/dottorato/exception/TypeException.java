package it.unisa.dottorato.exception;

/**Classe dell'oggetto TypeException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>type</code>
 *
 * @author andre
 */
public class TypeException extends Exception{
    
    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'Il tipo e' sbagliato'
     * 
     */
     public TypeException () {
        super("Il tipo e' sbagliato");
    }
   
     /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio pMessage
     * @param pMessage
     * 
     */
     public TypeException(String pMessage) {
        super(pMessage);
    }
}