/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.account;

/**Classe per la gestione delle eccezioni sui numeri di telefono
 *
 * @author Tommaso
 */
public class TelephoneException extends Exception{

    /**Metodo della classe incaricato di ritornare alla superclasse
     * Exception il messaggio 'il numero è sbagliato'
     * 
     */
    public TelephoneException() {
        super("il numero è sbagliato");
    }   
}
