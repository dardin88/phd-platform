package it.unisa.dottorato.curriculumcic;

import java.io.Serializable;

/**
 *
 * @author Elisa D'Eugenio
 */
public class StudentCurriculumcic implements Serializable{
    
    private String fkCurriculum;
    private String fkPhdstudent;
    private int fkCycle;

    public String getfkCurriculum(){
        return fkCurriculum;
    }
    
    public void setfkCurriculum(String fkCurriculum) {
        this.fkCurriculum = fkCurriculum;
    }
    public String getfkPhdstudent() {
        return fkPhdstudent;
    }

    public void setfkPhdstudent(String fkPhdstudent) {
        this.fkPhdstudent = fkPhdstudent;
    }

    public int getfkCycle() {
        return fkCycle;
    }

    public void setfkCycle(int fkCycle) {
        this.fkCycle = fkCycle;
    }


}
