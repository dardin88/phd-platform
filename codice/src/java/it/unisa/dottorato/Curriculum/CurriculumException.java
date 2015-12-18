/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.Curriculum;

/**
 *
 * @author tommaso
 */
public class CurriculumException extends Exception{

    public CurriculumException() {
        super("Errore oggetto curriculum");
    }
    public CurriculumException(String pMessage) {
        super(pMessage);
    }
}
