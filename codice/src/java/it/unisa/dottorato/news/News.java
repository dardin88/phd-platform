/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.news;

/**
 *
 * @author Rembor
 */
import java.io.Serializable;


/**
 *
 * @author Rembor
 */
public class News implements Serializable{
    private int id;
    private String title;
  private String description;
  
  public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

     public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getId() {
        return id;
        
    }

    public void setId(int pd) {
        this.id = pd;
    }

   
    
}