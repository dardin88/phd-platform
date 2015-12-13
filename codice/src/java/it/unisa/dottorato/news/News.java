package it.unisa.dottorato.news;

import java.io.Serializable;


/**Classe dell'oggetto News; ha come attributi id, title e description, i quali
 * rappresentano l'id, il titolo e la descrizione di una news
 *
 * @author Rembor
 */
public class News implements Serializable{
    private int id;
    private String title;
  private String description;
  
  /**Metodo della classe incaricato di settare la descrizione della news
   * 
   * @param description 
   */
  public void setDescription(String description) {
        this.description = description;
    }

    /**Metodo della classe incaricato di ritornare la descrizione della news
     * 
     * @return restituisce la descrizione della news
     */
    public String getDescription() {
        return description;
    }

    /**Metodo della classe incaricato di ritornare il titolo della news
     * 
     * @return restituisce il titolo della news
     */
     public String getTitle() {
        return title;
    }

     /** Metodo della classe incaricato di settare il titolo della news
      * 
      * @param title 
      */
    public void setTitle(String title) {
        this.title = title;
    }

    /**Metodo della classe incaricato di ritornare l'id della news
     * 
     * @return restituisce l'id della news
     */
    public int getId() {
        return id;
        
    }

    /** Metodo della classe incaricato di settare l'id della news
     * 
     * @param pd 
     */
    public void setId(int pd) {
        this.id = pd;
    }

   
    
}