package it.unisa.dottorato.account;

/** Classe dell'oggetto Professor che estende Account; ha come attributi link,
 * department, fkAccount, che rappresentano rispettivamente il link ad una pagina
 * esterna alla piattaforma, il dipartimento del professore e l'email principale del professore
 *
 * @author serter
 */
public class Professor extends Account {
    private String link;
    private String department;
    private String fkAccount;    


    /** Metodo della classe incaricato a settare il dipartimento del professore
     * 
     * @param department 
     */
    public void setDepartment(String department) {
        this.department = department;
    }
    /**Metodo della classe incaricato a ritornare il tipo di Account; e' un
     * override in quanto sarà sempre 'professor'
     * 
     * @return restituisce la stringa 'professor'
     */
    @Override
    public String getTypeAccount() {
        return "professor";
    }

    /** Metodo della classe incaricato a settare il link di una pagina esterna
     * alla piattaforma
     * 
     * @param link 
     */
    public void setLink(String link) {
        this.link = link;
    }

    /** Metodo della classe incaricato a ritornare il dipartimento del professore
     * 
     * @return restituisce il dipartimento del professore
     */
    public String getDepartment() {
        return department;
    }

      /** Metodo della classe incaricato a ritornare il link di una pagina esterna
     * alla piattaforma
     * 
     * @return restituisce il link di una pagina esterna alla piattaforma
     */
    public String getLink() {
        return link;
    }

   /** Metodo della classe incaricato a ritornare l'email principale dell'account
     * 
     * @return restituisce l'email principale dell'account
     */  
    public String getfkAccount() {
        return fkAccount;
    }

    /** Metodo della classe incaricato a settare l'email principale dell'account
     * 
     * @param fkAccount
     */ 
    public void setfkAccount(String fkAccount) {
        this.fkAccount = fkAccount;
    }
    
    


    

    
}
