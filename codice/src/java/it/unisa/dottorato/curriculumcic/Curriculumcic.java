package it.unisa.dottorato.curriculumcic;

import java.io.Serializable;

/**
 *
 * @author tommaso Minichiello
 */
public class Curriculumcic implements Serializable{
    
    private String fkProfessor;
    private int fkCycle;
    private String fkCurriculum;

    public String getfkProfessor() {
        return fkProfessor;
    }

    public void setfkProfessor(String fkProfessor) {
        this.fkProfessor = fkProfessor;
    }

    public int getfkCycle() {
        return fkCycle;
    }

    public void setFK_PhdCycle(int fkCycle) {
        this.fkCycle = fkCycle;
    }

    public String getfkCurriculum() {
        return fkCurriculum;
    }

    public void setfkCurriculum(String fkCurriculum) {
        this.fkCurriculum = fkCurriculum;
    }
    
}
