package it.unisa.dottorato.exception;

/**
 *
 * @author andre
 */
public class CollaborationException extends Exception{

    public CollaborationException() {
        super("errore oggetto Collaboration");
    }

    public CollaborationException(String message) {
        super(message);
    }

}
