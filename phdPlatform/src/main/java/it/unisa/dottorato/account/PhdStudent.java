package it.unisa.dottorato.account;

/**Classe dell'oggetto PhdStudent che estende Account; ha come attributi telephone,
 * link, department, researchInterest, fkCurriculum, fkCycle, fkProfessor, fkAccount
 * che rappresentano rispettivamente il numero di telefono, il link ad una pagina 
 * esterna alla piattaforma, il dipartimento, gli interessi di ricerca, il curriculum, 
 * il ciclo, il tutor e l'account del dottorando
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

    /**Metodo della classe incaricato a ritornare il tipo di Account; e' un
     * override in quanto sarà sempre 'phdstudent'
     * 
     * @return restituisce la stringa 'phdstudent'
     */
    @Override
    public String getTypeAccount() {
        return "phdstudent";
    }

    /** Metodo della classe incaricato a ritornare il dipartimento del dottorando
     * 
     * @return restituisce il dipartimento del dottorando
     */
    public String getDepartment() {
        return department;
    }

    /** Metodo della classe incaricato a ritornare gli interessi di ricerca del
     * dottorando
     * 
     * @return restituisce gli interessi di ricerca del dottorando
     */
    public String getResearchInterest() {
        return researchInterest;
    }

    /** Metodo della classe incaricato a ritornare il numero di telefono del
     * dottorando
     * 
     * @return restituisce il numero di telefono del dottorando
     */
    public String getTelephone() {
        return telephone;
    }
    
    /** Metodo della classe incaricato a ritornare il link di una pagina esterna
     * alla piattaforma
     * 
     * @return restituisce il link di una pagina esterna alla piattaforma
     */
    public String getLink() {
        return link;
    }
 
    /** Metodo della classe incaricato a settare il link di una pagina esterna
     * alla piattaforma
     * 
     * @param link 
     */
    public void setLink(String link) {
        this.link = link;
    }

    /** Metodo della classe incaricato a settare il dipartimento del dottorando
     * 
     * @param department 
     */
    public void setDepartment(String department) {
        this.department = department;
    }

    /** Metodo della classe incaricato a settare gli interessi di ricerca del 
     * dottorando
     * 
     * @param researchInterest 
     */
    public void setResearchInterest(String researchInterest) {
        this.researchInterest = researchInterest;
    }

    /** Metodo della classe incaricato a settare il numero di telefono del dottorando
     * 
     * @param telephone 
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    /** Metodo della classe incaricato a ritornare il numero del ciclo che il
     * dottorando sta frequentando
     * 
     * @return restituisce il numero del ciclo che il dottorando sta frequentando
     */
    public int getfkCycle() {
        return fkCycle;
    }

    /** Metodo della classe incaricato a ritornare il nome del curriculum che il
     * dottorando sta frequentando
     * 
     * @return restituisce il nome del curriculunmche il dottorando sta frequentando
     */
    public String getfkCurriculum() {
        return fkCurriculum;
    }

    /** Metodo della classe incaricato a ritornare l'email del tutor che ha il
     * dottorando 
     * 
     * @return restituisce l'email del tutor che ha il dottorando 
     */
    public String getfkProfessor() {
        return fkProfessor;
    }

      /** Metodo della classe incaricato a settare il nome del curriculum che il
     * dottorando sta frequentando
     * 
     * @param fkCurriculum 
     */
    
    public void setfkCurriculum(String fkCurriculum) {
        this.fkCurriculum = fkCurriculum;
    }

    /** Metodo della classe incaricato a settare l'email del tutor che ha il
     * dottorando 
     * 
     * @param fkProfessor 
     */
    public void setfkProfessor(String fkProfessor) {
        this.fkProfessor = fkProfessor;
    }

    /** Metodo della classe incaricato a settare il numero del ciclo che il
     * dottorando sta frequentando
     * 
     * @param fkCycle
     */
    public void setfkCycle(int fkCycle) {
        this.fkCycle = fkCycle;
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
