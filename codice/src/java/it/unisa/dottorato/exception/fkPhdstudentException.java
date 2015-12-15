package it.unisa.dottorato.exception;

/**Classe dell'oggetto IstitutionException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>fkPhdstudent</code>
 *
 * @author andre
 */
public class fkPhdstudentException extends Exception{
    
     public fkPhdstudentException () {
        super("il campo per il riferimento al PhdStudent e' sbagliato");
    }
   
     public fkPhdstudentException(String pMessage) {
        super(pMessage);
    }
}
