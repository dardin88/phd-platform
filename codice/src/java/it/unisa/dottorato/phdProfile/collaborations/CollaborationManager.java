
package it.unisa.dottorato.phdProfile.collaborations;

import it.unisa.dottorato.account.PhdStudent;
import it.unisa.dottorato.exception.CollaborationException;
import it.unisa.dottorato.exception.DateException;
import it.unisa.dottorato.exception.DescriptionException;
import it.unisa.dottorato.exception.IdException;
import it.unisa.dottorato.exception.IstitutionException;
import it.unisa.dottorato.exception.ReferenceException;
import it.unisa.dottorato.utility.Utility;
import it.unisa.integrazione.database.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**Classe per la gestione delle collaborazioni
 *
 * @author gemmacatolino
 */
public class CollaborationManager {
    
    /**
     * Il nome della tabella
     */
    private static final String TABLE_COLLABORATION = "collaboration";
    
    //	 istanza della classe
    private static CollaborationManager instance;

    /**
     * Il costruttore della classe e' dichiarato privato, per evitare
     * l'istanziazione di oggetti della classe .
     */
    private CollaborationManager() {
        super();
    }

    /**
     * Metodo della classe incaricato della produzione degli oggetti, tale
     * metodo deve essere chiamato per restituire l'istanza del Singleton.
     * L'oggetto Singleton sara' istanziato solo alla prima invocazione del
     * metodo. Nelle successive invocazioni, invece, sara' restituito un
     * riferimento allo stesso oggetto.
     *
     * @return L'istanza della classe
     */
    public static synchronized CollaborationManager getInstance() {
        if (instance == null) {
            instance = new CollaborationManager();
        }
        return instance;
    }
   
    /** Metodo della classe incaricato di inserire una nuova collaborazione
     * 
     * @param pCollaboration la nuova collaborazione
     * @throws SQLException 
     * @throws it.unisa.dottorato.exception.CollaborationException 
     * @throws it.unisa.dottorato.exception.DateException 
     * @throws it.unisa.dottorato.exception.ReferenceException 
     * @throws it.unisa.dottorato.exception.DescriptionException 
     * @throws it.unisa.dottorato.exception.IstitutionException 
     * @throws java.io.IOException 
     * @throws it.unisa.dottorato.exception.IdException 
     */
    public synchronized void insert(Collaboration pCollaboration) throws SQLException, CollaborationException,
            DateException, ReferenceException, DescriptionException, IstitutionException, IOException, IdException {
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            testCollaboration(pCollaboration);
            /*
             * Prepariamo la stringa SQL per inserire un nuovo record 
             * nella tabella collaboration
             */
            String tSql = "INSERT INTO "
                    + CollaborationManager.TABLE_COLLABORATION
                    + " (idCollaboration,description, startDate, endDate, istitution, fkPhdstudent)"
                    + " VALUES ("
                    + testId(nextNumber())
                    + ",'"
                    + Utility.Replace(CollaborationManager.getInstance().testDescription(pCollaboration.getDescription()))
                    + "','"
                    + CollaborationManager.getInstance().testStartDate(pCollaboration.getStartDate())
                    + "','"
                    + CollaborationManager.getInstance().testEndDate(pCollaboration.getEndDate())
                    + "','"
                    + Utility.Replace(CollaborationManager.getInstance().testIstitution(pCollaboration.getIstitution()))
                    + "','"
                    + CollaborationManager.getInstance().testfkPhdStudent(pCollaboration.getFkPhdstudent())
                    + "')";

            System.out.println("La query: " +tSql);
            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        } finally {
            DBConnection.releaseConnection(connect);
        }
        
    }
   
    /** Metodo della classe incaricato di aggiornare una collaborazione
     * 
     * @param oldCollaborationID l'id della collaborazione da aggiornare
     * @param pCollaboration la collaborazione aggiornata
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException 
     * @throws it.unisa.dottorato.exception.DateException 
     * @throws it.unisa.dottorato.exception.ReferenceException 
     * @throws it.unisa.dottorato.exception.DescriptionException 
     * @throws it.unisa.dottorato.exception.IstitutionException 
     * @throws it.unisa.dottorato.exception.CollaborationException 
     * @throws it.unisa.dottorato.exception.IdException 
     */
    public synchronized void update(int oldCollaborationID, Collaboration pCollaboration) throws ClassNotFoundException, SQLException,
            IOException, DateException, ReferenceException, DescriptionException, IstitutionException, IOException, IdException, CollaborationException, Exception {
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();
            
            testId(oldCollaborationID);
            testCollaboration(pCollaboration);
            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella collaboration
             */
            String tSql = "UPDATE "
                    + CollaborationManager.TABLE_COLLABORATION
                    + " set description = '"
                    + Utility.Replace(CollaborationManager.getInstance().testDescription(pCollaboration.getDescription()))
                    + "', startDate = '"
                    + CollaborationManager.getInstance().testStartDate(pCollaboration.getStartDate())
                    + "', endDate = '"
                    + CollaborationManager.getInstance().testEndDate(pCollaboration.getEndDate())
                    + "', istitution = '"
                    + Utility.Replace(CollaborationManager.getInstance().testIstitution(pCollaboration.getIstitution()))
                    + "' WHERE idCollaboration = "
                    + oldCollaborationID;           

            System.out.println(tSql);
            //Inviamo la Query al DataBase
            if(Utility.executeOperation(connect, tSql)==0)
                throw new Exception();

            connect.commit();
        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
    
    /** Metodo della classe incaricato di cancellare una collaborazione
     * 
     * @param idCollaboration l'id della collaborazione da cancellare
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException 
     */
    public synchronized void delete(int idCollaboration) throws ClassNotFoundException, 
            SQLException, IOException, IdException, Exception {
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();
            /*
             * Prepariamo la stringa SQL per cancellare un record 
             * nella tabella collaboration
             */
            String tSql = "DELETE FROM "
                    + CollaborationManager.TABLE_COLLABORATION
                    + " WHERE idCollaboration = "
                    + testId(idCollaboration);

            //Inviamo la Query al DataBase
            if(Utility.executeOperation(connect, tSql)==0)
                throw new Exception();

            connect.commit();
        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
    
    /** Metodo della classe incaricato di ricercare una collaborazione dato
     * il suo id
     * 
     * @param pCollaborationID l'id della collaborazione da ricercare
     * @return restituisce la collaborazione se trovata, lancia un'eccezione altrimenti
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException 
     * @throws it.unisa.dottorato.exception.IdException 
     */
    public synchronized Collaboration getCollaborationById(int pCollaborationID) throws 
            ClassNotFoundException, SQLException, IOException, IdException {
        Connection connect = null;
        Collaboration collaboration = new Collaboration();
        
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();
            
            testId(pCollaborationID);
            /*
             * Prepariamo la stringa SQL per ricercare un record 
             * nella tabella collaboration
             */
            String tSql = "SELECT * FROM "
                    + CollaborationManager.TABLE_COLLABORATION
                    + " WHERE idCollaboration = '"
                    + pCollaborationID + "'";

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            if (result.next()) {
                collaboration.setIdCollaboration(result.getInt("idCollaboration"));
                collaboration.setDescription(result.getString("description"));
                collaboration.setStartDate(result.getDate("startDate"));
                collaboration.setEndDate(result.getDate("endDate"));
                collaboration.setIstitution(result.getString("istitution"));
                collaboration.setFkPhdstudent(result.getString("fkPhdstudent"));
            }
        } finally {
            DBConnection.releaseConnection(connect);
        }
        return collaboration;
    }
    
    /** Metodo della classe incaricato di ricercare tutte le collaborazioni
     * dato un dottorando
     * 
     * @param phdStudent dottorando di cui si vogliono conoscere le collaborazioni
     * @return restituisce una lista di tutte le collaborazioni del dottorando,
     * lancia un'eccezione altrimenti
     * @throws SQLException 
     * @throws it.unisa.dottorato.exception.ReferenceException 
     */
    public synchronized List<Collaboration> getAllCollaborationOf(PhdStudent phdStudent) throws 
            SQLException, ReferenceException { 
        List<Collaboration> collaborations = new ArrayList<>();
        
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per ricercare un record 
             * nella tabella collaboration
             */
            String tSql = "SELECT * FROM "
                    + CollaborationManager.TABLE_COLLABORATION
                    + " WHERE fkPhdstudent = '"
                    + testfkPhdStudent(phdStudent.getfkAccount())+ "'"; 

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            while (result.next()) {
                Collaboration collaboration = new Collaboration();
                
                collaboration.setIdCollaboration(result.getInt("idCollaboration"));
                collaboration.setDescription(result.getString("description"));
                collaboration.setStartDate(result.getDate("startDate"));
                collaboration.setEndDate(result.getDate("endDate"));
                collaboration.setIstitution(result.getString("istitution"));
                collaboration.setFkPhdstudent(result.getString("fkPhdstudent"));
                
                collaborations.add(collaboration);
            }

        } finally {
            DBConnection.releaseConnection(connect);
        }
        
        
        return collaborations;
    }
    
    /** Metodo della classe per il testing dell'id; non può essere minore di 0
     * 
     * @param id id da testare
     * @return restituisce l'id se valido, lancia un'eccezione altrimenti
     * @throws IdException 
     */
    public int testId(int id) throws IdException {
        if(id<0 || id>999999){
            throw new IdException("L'id non puo' essere minore di 0");
        }
        return id;
    } 
    
    /**Metodo della classe per il testing della descrizione; non puo' essere nulla
     * 
     * @param description descrizione da testare
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws DescriptionException 
     */
    public String testDescription(String description) throws DescriptionException{
         if(description.equals(null)|| (description.length()>65536)){
            
            throw new DescriptionException("la descrizione e' sbagliata"); 
        }
         return description;
    }
    
    /**Metodo della classe per il testing della data di inizio; non puo' essere nulla
     * 
     * @param startDate data da testare
     * @return restituisce la data se valida, lancia un'eccezione altrimenti
     * @throws DateException 
     */
    public Date testStartDate(Date startDate) throws DateException{
         if(startDate.equals(null)){
            
            throw new DateException("la data e' nulla"); 
        }
         return startDate;
    }
    
    /**Metodo della classe per il testing della data di inizio; non puo' essere nulla
     * 
     * @param endDate data da testare
     * @return restituisce la data se valida, lancia un'eccezione altrimenti
     * @throws DateException 
     */
    public Date testEndDate(Date endDate) throws DateException{
         if(endDate.equals(null)){
            
            throw new DateException("la data e' nulla"); 
        }
         return endDate;
    }
    
    /**Metodo della classe per il testing del campo istitution; non puo' avere una lunghezza maggiore di 70 caratteri
     * 
     * @param istitution stringa da testare
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws IstitutionException 
     */
    public String testIstitution(String istitution) throws IstitutionException {
        if(istitution.length()<1 || istitution.length()>70){
            throw new IstitutionException("L'istituzione e' sbagliata"); 
        }
        return istitution;
    }
    
    /**Metodo della classe per il testing dell'attributo collaborazione; non puo' essere nulla
     * 
     * @param c collaborazione da testare
     * @return restituisce la collaborazione se valida, lancia un'eccezione altrimenti
     * @throws CollaborationException 
     */
    public Collaboration testCollaboration(Collaboration c) throws CollaborationException{
        if(c==null)
            throw new CollaborationException();
        return c;
    }
    
    
    /**Metodo della classe per il testing della chiave esterna per la tabella PhdStudent; non puo' essere
     * <code>null</code> o avere una lunghezza maggiore di 49 caratteri
     * 
     * @param fkPhdstudent stringa da testare
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws ReferenceException 
     */
    public String testfkPhdStudent(String fkPhdstudent) throws ReferenceException {
        if(fkPhdstudent.equals(null) || fkPhdstudent.length()>50){
            
            throw new ReferenceException("il campo per il riferimento al PhdStudent e' sbagliato"); 
        }
        return fkPhdstudent;
    }
     public synchronized int nextNumber() throws SQLException, IOException {
        int c=1;
        
       
        try (Connection connect = DBConnection.getConnection()) {
            String tSql = "SELECT idCollaboration FROM "
                    + CollaborationManager.TABLE_COLLABORATION
                    + " ORDER BY idCollaboration DESC LIMIT 1";
            //Inviamo la Query al DataBase
             ResultSet result = Utility.queryOperation(connect, tSql);
            if(result.next()){
                c = result.getInt("idCollaboration")+1;
            }
            connect.commit();
            return c;
        } 
     }
}