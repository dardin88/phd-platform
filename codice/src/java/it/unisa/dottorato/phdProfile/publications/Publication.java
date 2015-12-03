package it.unisa.dottorato.phdProfile.publications;

import java.io.Serializable;

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

    public int getIdPublication() {
        return idPublication;
    }

    public void setIdPublication(int idPublication) {
        this.idPublication = idPublication;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPublicationIssue() {
        return publicationIssue;
    }

    public void setPublicationIssue(String publicationIssue) {
        this.publicationIssue = publicationIssue;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }
    
    public int getNumberPages() {
        return numberPage;
    }

    public void setNumberPages(int numberPage) {
        this.numberPage = numberPage;
    }
    
    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }
    
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    
     public String getAuthors() {
        return otherAuthors;
    }

    public void setAuthors(String otherAuthors) {
        this.otherAuthors = otherAuthors;
    }

    public String getAbstract() {
        return pAbstract;
    }

    public void setAbstract(String pAbstract) {
      this.pAbstract = pAbstract;
    }

    public String getFkPhdstudent() {
        return fkPhdstudent;
    }

    public void setFkPhdstudent(String fkPhdstudent) {
        this.fkPhdstudent = fkPhdstudent;
    } 
}
