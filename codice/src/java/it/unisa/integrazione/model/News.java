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
    private int id;
    private String title;
  private String content;
 public News(){
    
  }

    public News(int id, String title, String content) {
        this.id = id;
        this.title = title;
        this.content = content;
    }

    

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

   public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

}
