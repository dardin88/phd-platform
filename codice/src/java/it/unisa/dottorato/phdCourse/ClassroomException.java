/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.phdCourse;

/**
 *
 * @author Rembor
 */
class ClassroomException extends Exception {
    public ClassroomException() {
        super("La classe e' Sbagliata! ");
    }
   
     public ClassroomException(String pMessage) {
        super(pMessage);
    }
}
