/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.exception;

/**
 *
 * @author tommaso
 */
public class FkProfessorException extends Exception{

    public FkProfessorException() {
        super("errore nella chiave esterna del professore");
    }

    public FkProfessorException(String message) {
        super(message);
    }
    
    
}
