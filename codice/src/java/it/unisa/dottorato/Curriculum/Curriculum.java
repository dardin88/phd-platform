package it.unisa.dottorato.Curriculum;

import java.io.Serializable;

/**
 *
 * @author Tommaso Minichiello
 */
public class Curriculum implements Serializable {

    private String name;
    private String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


}
