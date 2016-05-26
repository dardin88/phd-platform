/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.activityRegister;

/**
 *
 * @author Liliana
 */
public class Typology {
    private int id;
    private String name;

    /**
     * recupera l'id della tipologia
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * recupera il nome della tipologia
     * @return nome
     */
    public String getName() {
        return name;
    }
    /**
     * setta l'id della tipologia
     * @param id 
     */
    public void setId(int id) {
        this.id = id;
    }
    /**
     * setta il nome della tipologia
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
    
    
}
