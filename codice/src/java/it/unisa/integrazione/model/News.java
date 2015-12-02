/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.integrazione.model;

/**
 *
 * @author Rembor
 */
import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Rembor
 */
public class News implements Serializable{
    private int id=0;
    private String title;
  private String description;
 public News(){
    
  }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public News( String title, String description) {
        //this.id = id;
        this.title = title;
        this.description= description;
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

    public void setId() {
        this.id = id++;
    }

    @Override
    public String toString() {
        return "News{" + "id=" + id + ", title=" + title + ", description=" + description + '}';
    }

    
}
