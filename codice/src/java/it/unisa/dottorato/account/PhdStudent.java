/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.account;

/**
 *
 * @author ariemmov
 */
public class PhdStudent extends Account {
    private String telephone;
    private String link;
    private String department;
    private String researchInterest;
    private String fkCurriculum;
    private int fkCycle;
    private String fkProfessor;
    private String fkAccount;

 
    @Override
    public String getTypeAccount() {
        return "phdstudent";
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
    
    public String getLink() {
        return link;
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

    public int getfkCycle() {
        return fkCycle;
    }

    public String getfkCurriculum() {
        return fkCurriculum;
    }

    public String getfkProfessor() {
        return fkProfessor;
    }

    public void setfkCurriculum(String fkCurriculum) {
        this.fkCurriculum = fkCurriculum;
    }

    public void setfkProfessor(String fkProfessor) {
        this.fkProfessor = fkProfessor;
    }

    public void setfkCycle(int fkCycle) {
        this.fkCycle = fkCycle;
    }

    public String getfkAccount() {
        return fkAccount;
    }

    public void setfkAccount(String fkAccount) {
        this.fkAccount = fkAccount;
    }
    
    
    
    
    
    
     
    
}
