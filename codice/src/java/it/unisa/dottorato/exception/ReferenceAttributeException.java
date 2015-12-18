/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.exception;

/**Classe dell'oggetto ReferenceAttributeException che estende Exception;
 * gestisce le eccezioni per gli attributi <code>reference</code>
 *
 * @author andre
 */
public class ReferenceAttributeException extends Exception{
    
     public ReferenceAttributeException () {
        super("Il campo delle referenze e' sbagliato");
    }
   
     public ReferenceAttributeException(String pMessage) {
        super(pMessage);
    }
}