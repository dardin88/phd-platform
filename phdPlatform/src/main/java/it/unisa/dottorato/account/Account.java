package it.unisa.dottorato.account;

/**Classe dell'oggetto Account; ha come attributi email, secondayEmail
 * password, name, surname, typeAccount e isAdmin, i quali rappresentano
 * rispettivamente l'email, l'email secondaria, il nome, il cognome, il tipo di
 * account e isAdmin che è un valore booleano per stabilire se l'account e' o no
 * un amministratore
 *
 * @author ariemmov
 */
public class Account {

    private String email;
    private String secondaryEmail;
    private String password;
    private String name;
    private String surname;
    private String typeAccount = "basic";
    private boolean isAdmin;

    /** Metodo della classe incaricato a ritornare l'email principale dell'account
     * 
     * @return restituisce l'email principale dell'account
     */
    public String getEmail() {
        return email;
    }
    
    /**Metodo della classe incaricato a settare l'email principale dell'account
     * 
     * @param email 
     */
    public void setEmail(String email) {
        this.email = email;
    }
      
    /** Metodo della classe incaricato a ritornare l'email principale dell'account
     * 
     * @return restituisce l'email principale dell'account
     */
    public String getSecondaryEmail() {
        return secondaryEmail;
    }
    
     /**Metodo della classe incaricato a settare l'email secondaria dell'account
     * 
     * @param secondary 
     */
    public void setSecondaryEmail(String secondary) {
        secondaryEmail = secondary;
    }

    /** Metodo della classe incaricato a ritornare il nome dell'account
     * 
     * @return restituisce il nome dell'account
     */
    public String getName() {
        return name;
    }
    
     /**Metodo della classe incaricato a settare il nome dell'account
     * 
     * @param name 
     */
    public void setName(String name) {
        this.name = name;
    }
 
    /** Metodo della classe incaricato a ritornare il cognome dell'account
     * 
     * @return restituisce il cognome dell'account
     */
    public String getSurname() {
        return surname;
    }
    
     /**Metodo della classe incaricato a settare il cognome dell'account
     * 
     * @param surname 
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /** Metodo della classe incaricato a ritornare la password dell'account
     * 
     * @return restituisce la password dell'account
     */
    public String getPassword() {
        return password;
    }

     /**Metodo della classe incaricato a settare la password dell'account
     * 
     * @param password
     */
    public void setPassword(String password) {
        this.password = password;
    }  

     /**Metodo della classe incaricato a settare il tipo di account dell'account
     * 
     * @param typeAccount 
     */
    public void setTypeAccount(String typeAccount) {
        this.typeAccount = typeAccount;
    }

    /** Metodo della classe incaricato a ritornare il tipo di account dell'account
     * 
     * @return restituisce il tipo di accoun dell'account
     */
    public String getTypeAccount() {
        return typeAccount;
    }
    
    /** Metodo della classe incaricato a ritornare isAdmin
     * 
     * @return restituisce <code>true</code> se è un amministratore, <code>false</code> altrimenti
     */
    public boolean isAdmin() {
        return isAdmin;
    }
 
     /**Metodo della classe incaricato a settare l'attributo isAdmin dell'account
     * 
     * @param var 
     */
    public void setAdmin(boolean var) {
        isAdmin = var;
    }
}
