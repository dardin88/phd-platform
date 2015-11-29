/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.integrazione.model;

/**
 *
 * @author ariemmov
 */
public class Account {

    private String email;
    private String secondaryEmail;
    private String password;
    private String name;
    private String surname;
    private String typeOfAccount;
    private boolean isAdmin;

    public String getEmail() {
        return email;
    }
    
    public void setEmail(String email) {
        this.email = email;
    }
      
    public String getSecondaryEmail() {
        return secondaryEmail;
    }
    
    public void setSecondaryEmail(String secondary) {
        secondaryEmail = secondary;
    }

    public String getName() {
        return name;
    }
    
    public void setName(String name) {
        this.name = name;
    }
    
    public String getSurname() {
        return surname;
    }
    
    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }  

    public void setTypeOfAccount(String typeOfAccount) {
        this.typeOfAccount = typeOfAccount;
    }

    public String getTypeOfAccount() {
        return typeOfAccount;
    }
    
    public boolean isAdmin() {
        return isAdmin;
    }
    
    public void setAdmin(boolean var) {
        isAdmin = var;
    }
}
