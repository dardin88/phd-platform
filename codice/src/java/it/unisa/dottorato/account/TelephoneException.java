/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.account;

/**
 *
 * @author Tommaso
 */
public class TelephoneException extends Exception{

    public TelephoneException() {
        super("il numero è sbagliato");
    }   
}
