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
    private Cycle cycle;
    private PhdCurriculum curriculum;
    private String FK_curriculum;
    private int FK_cycle;
    private String FK_professor;
    private String FK_account;

 
    @Override
    public String getTypeOfAccount() {
        return type;
    }

    public PhdCurriculum getCurriculum() {
        return curriculum;
    }

    public Cycle getCycle() {
        return cycle;
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

    public void setCurriculum(PhdCurriculum curriculum) {
        this.curriculum = curriculum;
    }

 
    public void setLink(String link) {
        this.link = link;
    }
   

    public void setCycle(Cycle cycle) {
        this.cycle = cycle;
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
        return FK_cycle;
    }

    public String getFK_curriculum() {
        return FK_curriculum;
    }

    public String getFK_professor() {
        return FK_professor;
    }

    public void setFK_curriculum(String FK_curriculum) {
        this.FK_curriculum = FK_curriculum;
    }

    public void setFK_professor(String FK_professor) {
        this.FK_professor = FK_professor;
    }

    public void setFK_cycle(int FK_cycle) {
        this.FK_cycle = FK_cycle;
    }

    public String getFK_account() {
        return FK_account;
    }

    public void setFK_account(String FK_account) {
        this.FK_account = FK_account;
    }
    
    
    
    
    
    
     
    
}
