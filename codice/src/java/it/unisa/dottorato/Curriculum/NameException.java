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
public class NameException extends Exception {
    public NameException() {
        super("Nome del Curriculum non corretto.");
    }
}