package it.unisa.dottorato.exception;

/**
 *
 * @author andre
 */
public class MissionException extends Exception{

    public MissionException() {
        super("errore oggetto Missione");
    }

    public MissionException(String message) {
        super(message);
    }

}
