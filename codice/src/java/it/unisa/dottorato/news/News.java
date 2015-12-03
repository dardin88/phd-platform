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
import java.util.Date;

/**
 *
 * @author Rembor
 */
public class News implements Serializable{
    private int id;
    private String title;
  private String description;
 public News(){
    
  }
 public News( String title, String description) {
        this.id = 0;
        this.title = title;
        this.description= description;
    }


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

    public void setId() {
        this.id = id++;
    }

    @Override
    public String toString() {
        return "News{" + "id=" + id + ", title=" + title + ", description=" + description + '}';
    }

    
}