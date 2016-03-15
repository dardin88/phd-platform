package it.unisa.dottorato.Curriculum;

import java.io.Serializable;

/**Classe dell'oggetto curriculum; ha come attributi name e description, 
 * che rappresentano il nome e la descrizione del curriculum, rispettivamente.
 * 
 *
 * @author Tommaso Minichiello
 */
public class Curriculum implements Serializable {

    private String name;
    private String description;

    /** Metodo della classe incaricato a ritornare il nome del curriculum
    **  @return nome del curriculum
    */
    public String getName() {
        return name;
    }

    /** Metodo della classe incaricato a settare il nome del curriculum
    **   @param name nome del curriculum
    */
    public void setName(String name) {
        this.name = name;
    }

    /** Metodo della classe incaricato a ritornare la descrizione del curriculum
    **  @return descrizione del curriculum
    */
    public String getDescription() {
        return description;
    }

    /** Metodo della classe incaricato a settare la descrizione del curriculum
    **   @param description descrizione del curriculun
    */
    public void setDescription(String description) {
        this.description = description;
    }


}
