/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.News;

/**
 *
 * @author Giuseppe Picciocchi
 */
public class News {
    
    
    private int idNews;
    private String title;
    private String description;
    
     
    // metodo utilizzato per restituire l'id della news
    public int getIdNews(){
        return idNews;            
    }
    
    // metodo per settare l'id della news
    public void setIdNews(int id){
        this.idNews = id;
    }
    
    // metodo utilizzato per restituire il titolo della news
    public String getTitle(){
        return title;            
    }
    
    // metodo per settare il titolo della news
    public void setTitle(String titolo){
        this.title = titolo;
    }
    
    
    // metodo utilizzato per restituire la descrizione della news
    public String getDescription(){
        return description;            
    }
    
    // metodo per settare la descrizione della news
    public void setDescription(String descrizione){
        this.description = descrizione;
    }
    
}
