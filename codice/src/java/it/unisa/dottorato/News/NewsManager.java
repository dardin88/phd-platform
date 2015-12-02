/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.News;

import it.unisa.dottorato.utility.Utility;
import it.unisa.integrazione.database.DBConnection;
import it.unisa.integrazione.model.Person;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
/**
 *
 * @author Giuseppe Picciocchi
 */
public class NewsManager {
    
    /**
     *  macro per indicare il nome della tabella
     */
    private static final String TABLE_NEWS = "news";
    
    
       // istanza della classe
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
    public static synchronized NewsManager getInstance() {
        if (instance == null) {
            instance = new NewsManager();
        }
        return instance;
    }
    
    
    // metodo per aggiungere un record alla tabella news del db
    public synchronized void insert_news(News pNews) throws SQLException {
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per inserire un nuovo record 
             * nella tabella course
             */
            String tSql = "INSERT INTO "
                    + NewsManager.TABLE_NEWS
                    + " ( idNews, title, description)"
                    + " VALUES ('"
                    + pNews.getIdNews()
                    + "','"
                    + Utility.Replace(pNews.getTitle())
                    + "','"
                    + Utility.Replace(pNews.getDescription())
                    + "')";

            System.out.println("La query: " +tSql);
            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        }
    }
    
    
    
    // metodo per modificare una news
     public synchronized void update_news(int oldNewsId, News pNews) throws ClassNotFoundException, SQLException, IOException {
        try (Connection connect = DBConnection.getConnection()) {

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "UPDATE "
                    + NewsManager.TABLE_NEWS
                    + " set idNews = '"
                    + pNews.getIdNews()
                    + "', title = '"
                    + Utility.Replace(pNews.getTitle())
                    + "', description = '"
                    + Utility.Replace(pNews.getDescription())
                    + "' WHERE idLesson = "
                    + oldNewsId;           

            System.out.println(tSql);
            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        }
    }
     
     
     // metodo per eliminare una news 
     public synchronized void delete_news(int idNews) throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "DELETE FROM "
                    + NewsManager.TABLE_NEWS
                    + " WHERE idLesson = '"
                    + idNews + "'";

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
     
     
     // metodo che restituisce una news in base all'Id
     public synchronized News getNewsById(int pNewsID) throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
            News news = new News();
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "SELECT * FROM "
                    + NewsManager.TABLE_NEWS
                    + " WHERE idLesson = '"
                    + pNewsID + "'";

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            if (result.next()) {
                news.setIdNews(result.getInt("idNews"));
                news.setTitle(result.getString("title"));
                news.setDescription(result.getString("description"));
            }

            return news;

        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
     
     
     
}
