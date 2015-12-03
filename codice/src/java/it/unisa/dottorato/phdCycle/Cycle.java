package it.unisa.dottorato.phdCycle;

import java.io.Serializable;

/**
 *
 * @author Elisa D'Eugenio
 */
public class Cycle implements Serializable {

    private int number;
    private String description;
    private String year;
    private String fkProfessor;

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getYear() {
        return year;
    }

    public void setYear(String year) {
        this.year = year;
    }

    public String getFkProfessor() {
        return fkProfessor;
    }

    public void setFkProfessor(String fkProfessor) {
        this.fkProfessor = fkProfessor;
    }

}
