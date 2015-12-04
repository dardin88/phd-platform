/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.integrazione.model;

import it.unisa.dottorato.phdCurriculum.PhdCurriculum;

/**
 *
 * @author ariemmov
 */
public class PhdStudent extends Account {
    private final String type = "dottorando";
    private String telephone;
    private String link;
    private String department;
    private String researchInterest;
    private String fkCurriculum;
    private int fkCycle;
    private String fkProfessor;
    private String fkAccount;

 
    @Override
    public String getTypeOfAccount() {
        return type;
    }

 
    public String getDepartment() {
        return department;
    }

    public String getResearchInterest() {
        return researchInterest;
    }

    public String getTelephone() {
        return telephone;
    }
 
    public void setLink(String link) {
        this.link = link;
    }

    public void setDepartment(String department) {
        this.department = department;
    }

    public void setResearchInterest(String researchInterest) {
        this.researchInterest = researchInterest;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public int getFK_cycle() {
        return fkCycle;
    }

    public String getFK_curriculum() {
        return fkCurriculum;
    }

    public String getFK_professor() {
        return fkProfessor;
    }

    public void setFK_curriculum(String FK_curriculum) {
        this.fkCurriculum = FK_curriculum;
    }

    public void setFK_professor(String FK_professor) {
        this.fkProfessor = FK_professor;
    }

    public void setFK_cycle(int FK_cycle) {
        this.fkCycle = FK_cycle;
    }

    public String getFK_account() {
        return fkAccount;
    }

    public void setFK_account(String FK_account) {
        this.fkAccount = FK_account;
    }
    
    
    
    
    
    
     
    
}
