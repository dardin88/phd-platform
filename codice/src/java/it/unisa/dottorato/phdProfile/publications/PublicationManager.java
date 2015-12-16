package it.unisa.dottorato.phdProfile.publications;

import it.unisa.dottorato.account.PhdStudent;
import it.unisa.dottorato.exception.IdException;
import it.unisa.dottorato.exception.LinkException;
import it.unisa.dottorato.exception.NumberPageException;
import it.unisa.dottorato.exception.OtherAuthorsException;
import it.unisa.dottorato.exception.PublicationException;
import it.unisa.dottorato.exception.PublicationIssueException;
import it.unisa.dottorato.exception.ReferenceException;
import it.unisa.dottorato.exception.TitleException;
import it.unisa.dottorato.exception.TypeException;
import it.unisa.dottorato.exception.YearException;
import it.unisa.dottorato.exception.pAbstractException;
import it.unisa.dottorato.utility.Utility;
import it.unisa.integrazione.database.DBConnection;
import java.io.IOException;
import static java.lang.Integer.parseInt;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**Classe per la gestione delle pubblicazioni
 *
 * @author gemmacatolino
 */
public class PublicationManager {
    
    /**
     * Il nome della tabella
     */
    private static final String TABLE_PUBLICATION = "publication";
    
    //	 istanza della classe
    private static PublicationManager instance;

    /**
     * Il costruttore della classe e' dichiarato privato, per evitare
     * l'istanziazione di oggetti della classe .
     */
    private PublicationManager() {
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
    public static synchronized PublicationManager getInstance() {
        if (instance == null) {
            instance = new PublicationManager();
        }
        return instance;
    }
  
    /** Metodo della classe incaricato di inserire una nuova pubblicazione
     * 
     * @param pPublication la nuova pubblicazione da inserire
     * @throws SQLException 
     */
    public synchronized void insert(Publication pPublication) throws SQLException {
        try (Connection connect = DBConnection.getConnection()) {

            testPublication(pPublication);
            /*
             * Prepariamo la stringa SQL per inserire un nuovo record 
             * nella tabella publication
             */
            String tSql = "INSERT INTO "
                    + PublicationManager.TABLE_PUBLICATION
                    + " (idPublication,title, publicationIssue, year, numberPage, link, type, otherAuthors, abstract, fkPhdstudent)"
                    + " VALUES ("
                    + testId(nextNumber())
                    + ",'"
                    + Utility.Replace(PublicationManager.getInstance().testTitle(pPublication.getTitle()))
                    + "','"
                    + Utility.Replace(PublicationManager.getInstance().testPubliocationIssue(pPublication.getPublicationIssue()))
                    + "','"
                    + Utility.Replace(PublicationManager.getInstance().testYear(pPublication.getYear()))
                    + "','"
                    + PublicationManager.getInstance().testNumberPage(pPublication.getNumberPages())
                    + ",'"
                    + Utility.Replace(PublicationManager.getInstance().testLink(pPublication.getLink()))
                    + "','"
                    + Utility.Replace(PublicationManager.getInstance().testType(pPublication.getType()))
                    + "','"
                    + Utility.Replace(PublicationManager.getInstance().testOtherAutors(pPublication.getAuthors()))
                    + "','"
                    + Utility.Replace(PublicationManager.getInstance().testAbstract(pPublication.getAbstract()))
                    + "','"
                    + PublicationManager.getInstance().testfkPhdStudent(pPublication.getFkPhdstudent())
                    + "')";

            System.out.println("La query: " +tSql);
            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        } catch (PublicationException ex) {
            Logger.getLogger(PublicationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TitleException ex) {
            Logger.getLogger(PublicationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PublicationIssueException ex) {
            Logger.getLogger(PublicationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (YearException ex) {
            Logger.getLogger(PublicationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberPageException ex) {
            Logger.getLogger(PublicationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ReferenceException ex) {
            Logger.getLogger(PublicationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LinkException ex) {
            Logger.getLogger(PublicationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TypeException ex) {
            Logger.getLogger(PublicationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (OtherAuthorsException ex) {
            Logger.getLogger(PublicationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (pAbstractException ex) {
            Logger.getLogger(PublicationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(PublicationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IdException ex) {
            Logger.getLogger(PublicationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /** Metodo della classe incaricato di aggiornare una pubblicazione
     * 
     * @param oldPublicationID l'id della pubblicazione da aggionare
     * @param pPublication la pubblicazione aggiornata
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException 
     */
    public synchronized void update(int oldPublicationID, Publication pPublication) throws ClassNotFoundException, SQLException, IOException {
        try (Connection connect = DBConnection.getConnection()) {
            
            testId(oldPublicationID);
            testPublication(pPublication);
            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella publication
             */
            String tSql = "UPDATE "
                    + PublicationManager.TABLE_PUBLICATION
                    + " set title = '"
                    + Utility.Replace(PublicationManager.getInstance().testTitle(pPublication.getTitle()))
                    + "', publicationIssue = '"
                    + Utility.Replace(PublicationManager.getInstance().testPubliocationIssue(pPublication.getPublicationIssue()))
                    + "', year = '"
                    + Utility.Replace(PublicationManager.getInstance().testYear(pPublication.getYear()))
                    + "', numberPages = "
                    + PublicationManager.getInstance().testNumberPage(pPublication.getNumberPages())
                    + "', link = '"
                    + Utility.Replace(PublicationManager.getInstance().testLink(pPublication.getLink()))
                    + "', type = '"
                    + Utility.Replace(PublicationManager.getInstance().testType(pPublication.getType()))
                    + "', otherAuthors = '"
                    + Utility.Replace(PublicationManager.getInstance().testOtherAutors(pPublication.getAuthors()))
                    + "', abstract = '"
                    + Utility.Replace(PublicationManager.getInstance().testAbstract(pPublication.getAbstract()))
                    + " WHERE idPublication = "
                    + oldPublicationID + "";           

            System.out.println(tSql);
            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        } catch (IdException ex) {
            Logger.getLogger(PublicationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PublicationException ex) {
            Logger.getLogger(PublicationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TitleException ex) {
            Logger.getLogger(PublicationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (PublicationIssueException ex) {
            Logger.getLogger(PublicationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (YearException ex) {
            Logger.getLogger(PublicationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (NumberPageException ex) {
            Logger.getLogger(PublicationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (LinkException ex) {
            Logger.getLogger(PublicationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (TypeException ex) {
            Logger.getLogger(PublicationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (OtherAuthorsException ex) {
            Logger.getLogger(PublicationManager.class.getName()).log(Level.SEVERE, null, ex);
        } catch (pAbstractException ex) {
            Logger.getLogger(PublicationManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    /** Metodo della classe incaricato di cancellare una pubblicazione
     * 
     * @param idPublication l'id della pubblicazione da cancellare 
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException 
     */
    public synchronized void delete(String idPublication) throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            int id = parseInt(idPublication);
            testId(id);
            /*
             * Prepariamo la stringa SQL per cancellare un record 
             * nella tabella publication
             */
            String tSql = "DELETE FROM "
                    + PublicationManager.TABLE_PUBLICATION
                    + " WHERE idPublication = '"
                    + idPublication + "'";

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        } catch (IdException ex) {
            Logger.getLogger(PublicationManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
 
    /** Metodo della classe incaricato di ricercare una pubblicazione dato il suo id
     * 
     * @param pPublicationID l'id della pubblicazione da ricercare
     * @return restituisce la pubblicazione se trovata, lancia un'eccezione altrimenti
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException 
     */
    public synchronized Publication getPublicationById(int pPublicationID) throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        Publication publication = new Publication();
        try {
            
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            testId(pPublicationID);
            /*
             * Prepariamo la stringa SQL per selezionare un record 
             * nella tabella publication
             */
            String tSql = "SELECT * FROM "
                    + PublicationManager.TABLE_PUBLICATION
                    + " WHERE idPublication = '"
                    + pPublicationID + "'";

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            if (result.next()) {
                publication.setIdPublication(result.getInt("idPublication"));
                publication.setTitle(result.getString("title"));
                publication.setPublicationIssue(result.getString("publicationIssue"));
                publication.setYear(result.getString("year"));
                publication.setNumberPages(result.getInt("numberPage"));
                publication.setLink(result.getString("link"));
                publication.setType(result.getString("type"));
                publication.setAuthors(result.getString("otherAuthors"));
                publication.setAbstract(result.getString("abstract"));
                publication.setFkPhdstudent(result.getString("fkPhdstudent"));
            }

        } catch (IdException ex) {
            Logger.getLogger(PublicationManager.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            DBConnection.releaseConnection(connect);
        }
        return publication;
    }
 
    /** Metodo della classe incaricato di ricercare tutte le pubblicazioni di un
     * dato dottorando
     * 
     * @param phdStudent il dottorando
     * @return restituisce la lista di pubblicazioni del dottorando
     * @throws SQLException 
     */
    public synchronized List<Publication> getAllPublicationsOf(PhdStudent phdStudent) throws SQLException {
        List<Publication> publications = new ArrayList<Publication>();
        
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per selezionare uno o piu record 
             * nella tabella publication
             */
            String tSql = "SELECT * FROM "
                    + PublicationManager.TABLE_PUBLICATION
                    + " WHERE fkPhdstudent = '"
                    + phdStudent.getfkAccount() + "'"; // da verificare

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            while (result.next()) {
                Publication publication = new Publication();
                
                publication.setIdPublication(result.getInt("idPublication"));
                publication.setTitle(result.getString("title"));
                publication.setPublicationIssue(result.getString("publicationIssue"));
                publication.setYear(result.getString("year"));
                publication.setNumberPages(result.getInt("numberPage"));
                publication.setLink(result.getString("link"));
                publication.setType(result.getString("type"));
                publication.setAuthors(result.getString("otherAuthors"));
                publication.setAbstract(result.getString("abstract"));
                publication.setFkPhdstudent(result.getString("fkPhdstudent"));
                
                publications.add(publication);
            }

        } finally {
            DBConnection.releaseConnection(connect);
        }
        
        
        return publications;
    }
    
    /** Metodo della classe per il testing dell'id; non può essere minore di 0
     * 
     * @param id id da testare
     * @return restituisce l'id se valido, lancia un'eccezione altrimenti
     * @throws IdException 
     */
    public int testId(int id) throws IdException {
        if(id<0){
            throw new IdException("L'id non puo' essere minore di 0");
        }
        return id;
    } 
    
    /**Metodo della classe per il testing del campo place; non puo' essere
     * <code>null</code> o avere una lunghezza maggiore di 50 caratteri
     * 
     * @param title stringa da testare
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws TitleException 
     */
    public String testTitle(String title) throws TitleException {
        if(title.equals(null)&&title.length()>50){
            
            throw new TitleException("il posto e' sbagliato"); 
        }
        return title;
    }
    
    /**Metodo della classe per il testing del campo publicationIssue; non puo' essere
     * <code>null</code> o avere una lunghezza maggiore di 60 caratteri
     * 
     * @param publicationIssue stringa da testare
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws PublicationIssueException 
     */
    public String testPubliocationIssue(String publicationIssue) throws PublicationIssueException {
        if(publicationIssue.equals(null)&&publicationIssue.length()>50){
            
            throw new PublicationIssueException("il campo pubblicationIssue e' sbagliato"); 
        }
        return publicationIssue;
    }
    
    /** Metodo della classe per il testing dell'anno; non può essere maggiore di 4 e non puo' essere <code>null</code>
     * 
     * @param year stringa da testare
     * @return restituisce la stringa, lancia un'eccezione altrimenti
     * @throws YearException 
     */
    public String testYear(String year) throws YearException {
        if((year.length()>4)&&(year.equals(null))){
            throw new YearException("L'anno è sbagliato");
        }
        return year;
    } 
    
    /** Metodo della classe per il testing del numero delle pagine; non può essere minore di 0
     * 
     * @param numberPage id da testare
     * @return restituisce il numero di pagine se valido, lancia un'eccezione altrimenti
     * @throws NumberPageException 
     */
    public int testNumberPage(int numberPage) throws NumberPageException {
        if(numberPage<0){
            throw new NumberPageException("Il numero delle pagine è sbagliato");
        }
        return numberPage;
    }
    
    /**Metodo della classe per il testing del campo link; non puo' avere una lunghezza maggiore di 150 caratteri
     * 
     * @param link stringa da testare
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws LinkException 
     */
    public String testLink(String link) throws LinkException {
        if(link.length()>150){
            
            throw new LinkException("il link e' sbagliato"); 
        }
        return link;
    }
    
    /**Metodo della classe per il testing del campo type; non puo' avere una lunghezza maggiore di 20 caratteri
     * 
     * @param type stringa da testare
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws TypeException 
     */
    public String testType(String type) throws TypeException {
        if(type.length()>20){
            
            throw new TypeException("il tipo e' sbagliato"); 
        }
        return type;
    }
    
    
    /**Metodo della classe per il testing del campo otherAutors; non puo' avere una lunghezza maggiore di 255 caratteri
     * 
     * @param otherAutors stringa da testare
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws OtherAuthorsException 
     */
    public String testOtherAutors(String otherAutors) throws OtherAuthorsException {
        if(otherAutors.length()>255){
            
            throw new OtherAuthorsException("il campo degli altri autori e' sbagliato"); 
        }
        return otherAutors;
    }
    
    /**Metodo della classe per il testing del campo pAbstract; non puo' essere <code>null</code>
     * 
     * @param pAbstract stringa da testare
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws pAbstractException 
     */
    public String testAbstract(String pAbstract) throws pAbstractException {
        if(pAbstract.equals(null)){
            
            throw new pAbstractException("il campo dell'abstract e' sbagliato"); 
        }
        return pAbstract;
    }
    
    /**Metodo della classe per il testing dell'attributo pubblicazione; non puo' essere nulla
     * 
     * @param p pubblicazione da testare
     * @return restituisce la pubblicazione se valida, lancia un'eccezione altrimenti
     * @throws PublicationException 
     */
    public Publication testPublication(Publication p) throws PublicationException{
        if(p==null)
            throw new PublicationException("errore oggetto Missione");
        return p;
    }
    
    
    /**Metodo della classe per il testing della chiave esterna per la tabella PhdStudent; non puo' essere
     * <code>null</code> o avere una lunghezza maggiore di 49 caratteri
     * 
     * @param fkPhdstudent stringa da testare
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws ReferenceException 
     */
    public String testfkPhdStudent(String fkPhdstudent) throws ReferenceException {
        if(fkPhdstudent.equals(null)&&fkPhdstudent.length()>50){
            
            throw new ReferenceException("il campo per il riferimento al PhdStudent e' sbagliato"); 
        }
        return fkPhdstudent;
    }   
    /**
     * Metodo della classe incaricato di calcolare il numero della prossima pubblicazione da inserire
     * nella tabella Cycle del database.
     * @return restituisce il prossimo numero
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * 
     */
    public synchronized int nextNumber() throws SQLException, IOException {
        int c=1;
        
       
        try (Connection connect = DBConnection.getConnection()) {
            String tSql = "SELECT idPublication FROM "
                    + PublicationManager.TABLE_PUBLICATION
                    + "ORDER BY idPublication DESC LIMIT 1";
            //Inviamo la Query al DataBase
             ResultSet result = Utility.queryOperation(connect, tSql);
            if(result.next()){
                c = result.getInt("idPublication")+1;
            }
            connect.commit();
            return c;
        } 
    }
    
}