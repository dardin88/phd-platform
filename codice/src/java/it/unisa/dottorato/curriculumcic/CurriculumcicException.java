/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.curriculumcic;

/**
 *
 * @author tommaso
 */
public class CurriculumcicException extends Exception{

    public CurriculumcicException() {
        super("errore oggetto Curriculumcic");
    }

    public CurriculumcicException(String message) {
        super(message);
    }

}
