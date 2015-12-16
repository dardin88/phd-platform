package it.unisa.dottorato.exception;

/**
 *
 * @author andre
 */
public class PublicationException extends Exception{

    public PublicationException() {
        super("errore oggetto Pubblicazione");
    }

    public PublicationException(String message) {
        super(message);
    }

}
