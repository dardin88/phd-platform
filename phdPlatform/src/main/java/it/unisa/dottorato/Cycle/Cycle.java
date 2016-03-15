package it.unisa.dottorato.Cycle;

import java.io.Serializable;

/** Classe dell'oggetto Cycle; ha come attributi number, description, year e 
 * fkProfessor che rappresentano il numero, la descrizione, l'anno, il
 * coordinatore, di un ciclo, rispettivamente
 *
 * @author Tommaso Minichiello
 */
public class Cycle implements Serializable {

    private int number;
    private String description;
    private String year;
    private String fkProfessor;

     /** Metodo della classe incaricato a ritornare il numero del ciclo
    **  @return numero del ciclo
    */
    public int getNumber() {
        return number;
    }

     /** Metodo della classe incaricato a settare il numero del ciclo
    **   @param number il numero del ciclo
    */
    public void setNumber(int number) {
        this.number = number;
    }

     /** Metodo della classe incaricato a ritornare la descrizione del ciclo
    **  @return descrizione del ciclo
    */
    public String getDescription() {
        return description;
    }

    /** Metodo della classe incaricato a settare la descrizione del ciclo
    **   @param description la descrizione del ciclo
    */
    public void setDescription(String description) {
        this.description = description;
    }

     /** Metodo della classe incaricato a ritornare l'anno del ciclo
    **  @return anno del ciclo
    */
    public String getYear() {
        return year;
    }

      /** Metodo della classe incaricato a settare l'anno del ciclo
    **   @param year anno del ciclo
    */
    public void setYear(String year) {
        this.year = year;
    }

      /** Metodo della classe incaricato a ritornare il coordinatore del ciclo
    **  @return coordinatore del ciclo
    */
    public String getFkProfessor() {
        return fkProfessor;
    }

    /** Metodo della classe incaricato a settare il coordinatore del ciclo
    **   @param fkProfessor il coordinatore del ciclo
    */
    public void setFkProfessor(String fkProfessor) {
        this.fkProfessor = fkProfessor;
    }

}
