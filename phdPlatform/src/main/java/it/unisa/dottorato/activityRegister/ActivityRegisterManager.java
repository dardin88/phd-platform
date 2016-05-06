/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.activityRegister;

import java.io.IOException;
import java.sql.Connection;

/**
 *
 * @author Liliana
 */
public class ActivityRegisterManager {
    private static ActivityRegisterManager instance;
    
     /**
     * Il costruttore della classe e' dichiarato privato, per evitare
     * l'istanziazione di oggetti della classe .
     */
    private ActivityRegisterManager(){
        super();
    }
    /**
     * metodo deve essere chiamato per restituire l'istanza del Singleton.
     * L'oggetto Singleton sara' istanziato solo alla prima invocazione del
     * metodo. Nelle successive invocazioni, invece, sara' restituito un
     * riferimento allo stesso oggetto.
     *
     * @return L'istanza della classe
     */
    public static synchronized ActivityRegisterManager getInstance() {

        if (instance == null) {
            instance = new ActivityRegisterManager();
        }
        return instance;

    }
    
 }
