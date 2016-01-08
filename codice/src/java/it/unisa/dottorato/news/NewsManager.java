package it.unisa.dottorato.news;
import it.unisa.dottorato.exception.DescriptionException;
import it.unisa.dottorato.exception.IdException;
import it.unisa.dottorato.exception.TitleException;
import it.unisa.dottorato.utility.Utility;
import it.unisa.integrazione.database.DBConnection;
import it.unisa.integrazione.database.exception.ConnectionException;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

/**Classe della gestione delle news
 *
 * @author Rembor
 */
public class NewsManager {
 
private static final String TABLE_News = "news";
    //istanza della classe
    private static NewsManager instance;

    /**
     * Il costruttore della classe e' dichiarato privato, per evitare
     * l'istanziazione di oggetti della classe .
     */
    private NewsManager() {
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
    public static NewsManager getInstance() {

        if (instance == null) {
            instance = new NewsManager();
        }
        return instance;

    }


    
    /**Metodo della classe incaricato di inserire una nuova news
     * 
     * @param anews news da inserire
     * @throws SQLException 
     * @throws it.unisa.dottorato.exception.IdException 
     * @throws it.unisa.dottorato.exception.TitleException 
     * @throws it.unisa.dottorato.exception.DescriptionException 
     * @throws java.io.IOException 
     */
    public void insertNews(News anews) throws SQLException,IdException,TitleException,DescriptionException,IOException {
        //connessione al database
        Connection connect = DBConnection.getConnection();
     try {
            /*
             *stringa SQL per effettuare l'inserimento nella 
             * tabella news
             */
             String tSql = "INSERT INTO "
                    + NewsManager.TABLE_News
                    + " (idNews,title,description)"
                    + " VALUES ("
                    + testid(nextNumber())
                    + ",'"
                    + Utility.Replace(testTitle(anews.getTitle()))
                    + "','"
                    + Utility.Replace(testDescription(anews.getDescription()))
                    + "')";      
            Statement stmt = connect.createStatement();
            stmt.executeUpdate(tSql);
            connect.commit();
        }finally {
            DBConnection.releaseConnection(connect);
        }
    }

    /**Metodo della classe incaricato di ricercare una news dato il suo id
     * 
     * @param aidnews id della news da ricercare
     * @return restituisce la news se trovata, lancia un'eccezione altrimenti
     * @throws SQLException
     * @throws ConnectionException
     * @throws ClassNotFoundException 
     */
    public News getNewsById(int aidnews) throws 
            SQLException, ConnectionException,ClassNotFoundException,IdException, Exception{
        Statement stmt = null;
        ResultSet rs = null;
        Connection connection = null;
        News anews = null;
                try {
            //connessione al database
            connection = DBConnection.getConnection();
            /*
             *stringa SQL per effettuare la ricerca nella 
             * tabella news
             */
            String sql;
            sql = "SELECT * From "
                    + NewsManager.TABLE_News
                    + " WHERE idNews = "
                    +    testid(aidnews);
            
                       if (connection == null) {
                throw new ConnectionException();
            }
            //esecuzione query
            stmt = connection.createStatement();
         rs=  Utility.queryOperation(connection, sql);

            if (rs.next()) {
                anews = new News();
                anews.setId(rs.getInt("idnews"));
                anews.setTitle(rs.getString("title"));
                anews.setDescription(rs.getString("description"));
            }else{
                throw new Exception();
            }
            
        }finally {
            DBConnection.releaseConnection(connection);
        }

        return anews;
    }
    /**Metodo della classe incaricato di cancellare una news dato il suo id
     *
     * @param aidnews l'id della news da cancellare
     * @throws it.unisa.dottorato.exception.IdException
     * @throws java.sql.SQLException
     * 
     */
   public synchronized void deleteNews(int aidnews)throws IdException,SQLException, Exception {
        Connection connect = null;
        try {
            //connessione al database
            connect = DBConnection.getConnection();
            
            /*
             *stringa SQL per effettuare l'aggiornamento nella 
             * tabella news
             */
            String tSql = "DELETE FROM "
                    + NewsManager.TABLE_News
                    + " WHERE idNews = "
                    + testid(aidnews);

            if(Utility.executeOperation(connect, tSql)==0)
                throw new Exception();
            connect.commit();
        }finally {
            DBConnection.releaseConnection(connect);
        }
    }
   
   
   
   
   /** Metodo della classe incaricato di aggiornare una news
    * 
    * @param oldNewsId l'id della news da aggiornare
    * @param pNews news aggiornata
    * @throws ClassNotFoundException
    * @throws SQLException
    * @throws IOException 
     * @throws it.unisa.dottorato.exception.IdException 
     * @throws it.unisa.dottorato.exception.TitleException 
     * @throws it.unisa.dottorato.exception.DescriptionException 
    */
   public synchronized void update_news(int oldNewsId, News pNews) throws 
           ClassNotFoundException, SQLException, IOException, IdException, TitleException,DescriptionException, Exception {
            Connection connect = null;
            try {
            //connessione al database
            connect = DBConnection.getConnection();

            /*
             *stringa SQL per effettuare l'aggiornamento nella 
             * tabella news
             */
            String tSql = "UPDATE "
                    + NewsManager.TABLE_News
                    + " set title = '"
                    + Utility.Replace(testTitle(pNews.getTitle()))
                    + "', description = '"
                    +Utility.Replace(testDescription(pNews.getDescription()))
                    + "' WHERE idNews = "
                    + testid(oldNewsId);           
            //esecuzione query
            if(Utility.executeOperation(connect, tSql)==0)
                throw new Exception();

            connect.commit();
        }finally {
            DBConnection.releaseConnection(connect);
        }
    }

    /** Metodo della classe incaricato di visualizzare tutte le news 
     * presenti nella piattaforma
     * 
     * @return restituisce un array list di tutte le news
     * @throws SQLException
     * @throws it.unisa.integrazione.database.exception.ConnectionException 
     */
    public ArrayList<News> getAllNews() throws SQLException, it.unisa.integrazione.database.exception.ConnectionException {
        Statement stmt = null;
        ResultSet rs = null;
        Connection connection = null;
        News avviso = new News();
        ArrayList<News> listAvviso = new ArrayList<News>();

        try {
            //connessione al database
            connection = DBConnection.getConnection();
            /*
             *stringa SQL per effettuare la ricerca nella 
             * tabella news
             */
        String query = "select * from "
        + NewsManager.TABLE_News;

            if (connection == null) {
                throw new it.unisa.integrazione.database.exception.ConnectionException();
            }
            //esecuzione query
            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);
               while (rs.next()) {
                avviso = new News();
                avviso.setId(rs.getInt("idNews"));
                avviso.setTitle(rs.getString("title"));
                avviso.setDescription(rs.getString("description"));
                               
               listAvviso.add(avviso);

            }
        } finally {

            if (rs != null) {
                rs.close();
            }

            if (stmt != null) {
                stmt.close();
            }

            if (connection != null) {
                connection.close();
            }
        }

        return listAvviso;
    }
    
    /** Metodo della classe per il testing dell'id; non può essere minore di 0
     * 
     * @param id id da testare
     * @return restituisce l'id se valido, lancia un'eccezione altrimenti
     * @throws IdException 
     */
    public int testid(int id) throws IdException {
        if(id<0 || id>999999){
            throw new IdException("l'id non puo' essere minore di 0");
        }
        return id;
    }  
    
    /**Metodo della classe per il testing del titolo; non puo' essere
     * <code>null</code> o avere una lunghezza maggiore di 49 caratteri
     * 
     * @param title stringa da testare
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws TitleException 
     */
    public String testTitle(String title) throws TitleException {
        if(title.equals("") || title.length()>50){
            throw new TitleException("il titolo e' sbagliato"); 
        }
        return title;
    }
    
    /**Metodo della classe per il testing della descrizione; non puo' essere nulla
     * 
     * @param description descrizione da testare
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws DescriptionException 
     */
    public String testDescription(String description) throws DescriptionException{
         if(description.isEmpty() || description.equals("") || (description.length()>65536)){
            throw new DescriptionException("la descrizione e' sbagliata"); 
        }
         return description;
    }
    
    /**
     * Metodo della classe incaricato di calcolare il numero della prossima news
     * nella tabella Cycle del database.
     * @return restituisce il prossimo numero
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * 
     */
    public synchronized int nextNumber() throws SQLException, IOException {
        int c=1;
        
       
        try (Connection connect = DBConnection.getConnection()) {
            String tSql = "SELECT idNews FROM "
                    + NewsManager.TABLE_News
                    + " ORDER BY idNews DESC LIMIT 1";
            //Inviamo la Query al DataBase
             ResultSet result = Utility.queryOperation(connect, tSql);
            if(result.next()){
                c = result.getInt("IdNews")+1;
            }else{
                return 1;
            }
            connect.commit();
            return c;
        } 
    }
}
