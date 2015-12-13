package it.unisa.dottorato.phdProfile.publications;

import java.io.Serializable;
/** Classe dell'oggetto Pubblication; rappresenta la pubblicazione di un dottorando
 * Ha come attributi idPubblication, title, pubblicationIssue, year, numberPage, link,
 * type, otherAuthors,pAbstract, fkPhdstudent che rappresentano rispettivamente, l'id,
 * il titolo, la motivazione, l'anno, il numero di pagine, il link e  il tipo della 
 * pubblicazione, eventuali altri autori, un abstract, e l'email principale del dottorando
 * 
 * @author ???
 */
public class Publication implements Serializable{
    
    private int idPublication;
    private String title;
    private String publicationIssue;
    private String year;
    private int numberPage;
    private String link;
    private String type;
    private String otherAuthors;
    private String pAbstract;  // nel db si chiama abstract
    private String fkPhdstudent;

    /** Metodo della classe incaricato di ritornare l'id della publicazione
     * 
     * @return restituisce l'id della pubblicazione
     */
    public int getIdPublication() {
        return idPublication;
    }

    /** Metodo della classe incaricato di settare l'id della pubblicazione
     * 
     * @param idPublication 
     */
    public void setIdPublication(int idPublication) {
        this.idPublication = idPublication;
    }

    /** Metodo della classe incarticato di ritornare il titolo della pubblicazione
     * 
     * @return restituisce il titolo della pubblicazione
     */
    public String getTitle() {
        return title;
    }

    /** Metodo della classe incaricato di settare il titolo della pubblicazione
     * 
     * @param title 
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /** Metodo della classe incaricato di ritornare la motivazione della pubblicazione
     * 
     * @return restituisce la motivazione della pubblicazione
     */
    public String getPublicationIssue() {
        return publicationIssue;
    }

    /** Metodo della classe incaricato di settare la motivazione della pubblicazione
     * 
     * @param publicationIssue 
     */
    public void setPublicationIssue(String publicationIssue) {
        this.publicationIssue = publicationIssue;
    }

    /**  Metodo della classe incaricato di ritornare l'anno della pubblicazione
     * 
     * @return restituisce l'anno della pubblicazione
     */
    public String getYear() {
        return year;
    }

    /** Metodo della classe incaricato di settare l'anno della pubblicazione
     * 
     * @param year 
     */
    public void setYear(String year) {
        this.year = year;
    }
    
    /** Metodo della classe incaricato di ritornare il numero di pagine della pubblicazione
     * 
     * @return restituisce il numero di pagine della pubblicazione
     */
    public int getNumberPages() {
        return numberPage;
    }

    /** Metodo della classe incaricato di settare il numero di pagine della pubblicazione
     * @param numberPage 
     */
    public void setNumberPages(int numberPage) {
        this.numberPage = numberPage;
    }
  
    /** Metodo della classe incaricato di ritornare il link della pubblicazione
     * 
     * @return restituisce il link della pubblicazione
     */
    public String getLink() {
        return link;
    }

    /** Metodo della classe incaricato di settare il link della pubblicazione
     * 
     * @param link 
     */
    public void setLink(String link) {
        this.link = link;
    }
 
    /** Metodo della classe incaricato di ritornare il tipo di pubblicazione
     * 
     * @return restituisce il tipo di pubblicazione
     */
    public String getType() {
        return type;
    }

    /** Metodo della classe incaricato di settare il tipo di pubblicazione
     * 
     * @param type 
     */
    public void setType(String type) {
        this.type = type;
    }
 
    /** Metodo della classe incaricato di ritornare gli autori della pubblicazione
     * 
     * @return restituisce gli autori della pubblicazione
     */
     public String getAuthors() {
        return otherAuthors;
    }

     /** Metodo della classe incaricato di settare gli autori della pubblicazione
      * 
      * @param otherAuthors 
      */
    public void setAuthors(String otherAuthors) {
        this.otherAuthors = otherAuthors;
    }

    /** Metodo della classe incaricato di ritornare l'abstract della pubblicazione
     *
     * 
     * @return restituisce l'abstract della pubblicazione
     */
    public String getAbstract() {
        return pAbstract;
    }

    /**  Metodo della classe incaricato di settare l'abstract della pubblicazione
     * 
     * @param pAbstract 
     */
    public void setAbstract(String pAbstract) {
      this.pAbstract = pAbstract;
    }

    /** Metodo della classe incaricato di ritornare l'email del dottorando che ha 
     * partecipato alla pubblicazione
     * 
     * @return 
     */
    public String getFkPhdstudent() {
        return fkPhdstudent;
    }

    /**  Metodo della classe incaricato di ritornare l'email del dottorando che ha
     * partecipato alla pubblicazione
     * 
     * @param fkPhdstudent 
     */
    public void setFkPhdstudent(String fkPhdstudent) {
        this.fkPhdstudent = fkPhdstudent;
    } 
}
