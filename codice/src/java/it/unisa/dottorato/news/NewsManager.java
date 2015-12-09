/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Rembor
 */
public class NewsManager {
 
private static final String TABLE_News = "news";
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


    
    /**
     * 
     *
     * @param  anews
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * @throws IdException
     * @throws TitleException
     * @throws DescriptionException
     *  
     */
    public void insertNews(News anews) throws SQLException{
        Connection connect = DBConnection.getConnection();
     try {
             String tSql = "INSERT INTO "
                    + NewsManager.TABLE_News
                    + " (id,title,description,)"
                    + " VALUES ('"
                    + testid(anews.getId())
                    + "','"
                    + Utility.Replace(testTitle(anews.getTitle()))
                    + "','"
                    + Utility.Replace(testDescription(anews.getDescription()))
                    + "',"
                     + ")";      
            
            
            
            Statement stmt = connect.createStatement();
            stmt.executeUpdate(tSql);
            connect.commit();
        }
    catch (IdException ex) {
        Logger.getLogger(NewsManager.class.getName()).log(Level.SEVERE, null, ex);
    } catch (TitleException ex) {
        Logger.getLogger(NewsManager.class.getName()).log(Level.SEVERE, null, ex);
    } catch (DescriptionException ex) {
        Logger.getLogger(NewsManager.class.getName()).log(Level.SEVERE, null, ex);
    }         finally {
            DBConnection.releaseConnection(connect);
        }
    }

    public News getNewsById(int aidnews) throws SQLException, ConnectionException{
        Statement stmt = null;
        ResultSet rs = null;
        Connection connection = null;
        News anews = null;
                try {
            connection = DBConnection.getConnection();
            String sql = "SELECT * From "
                    + NewsManager.TABLE_News
                    + " WHERE number = "
                    +    testid(anews.getId());
            
                       if (connection == null) {
                throw new ConnectionException();
            }

            stmt = connection.createStatement();
         rs=  Utility.queryOperation(connection, sql);

            if (rs.next()) {
                anews = new News();
                anews.setId(rs.getInt("idnews"));
                anews.setTitle(rs.getString("title"));
                anews.setDescription(rs.getString("description"));
            }
            
        } catch (IdException ex) {
        Logger.getLogger(NewsManager.class.getName()).log(Level.SEVERE, null, ex);
    } finally {

            DBConnection.releaseConnection(connection);
        }

        return anews;
    }
    /**
     *
     * @param aidnews
     * @return
     */
   public synchronized void deleteNews(int aidnews) {
        Connection connect = null;
        try {
            
            connect = DBConnection.getConnection();

           
            String tSql = "DELETE FROM "
                    + NewsManager.TABLE_News
                    + " WHERE idNews = "
                    + testid(aidnews);

            
            Utility.executeOperation(connect, tSql);

            connect.commit();
        } catch (IdException ex) {
        Logger.getLogger(NewsManager.class.getName()).log(Level.SEVERE, null, ex);
    } catch (SQLException ex) {
        Logger.getLogger(NewsManager.class.getName()).log(Level.SEVERE, null, ex);
    } finally {
            DBConnection.releaseConnection(connect);
        }
    }
   
   
   
   
   
   public synchronized void update_news(int oldNewsId, News pNews) throws ClassNotFoundException, SQLException, IOException{
         try (Connection connect = DBConnection.getConnection()) {

            
            String tSql = "UPDATE "
                    + NewsManager.TABLE_News
                    + " set title = '"
                    + Utility.Replace(testTitle(pNews.getTitle()))
                    + "', description = '"
                    +Utility.Replace(testDescription(pNews.getDescription()))
                    + "' WHERE number = "
                    + oldNewsId;           

            Utility.executeOperation(connect, tSql);

            connect.commit();
        } catch (TitleException ex) {
        Logger.getLogger(NewsManager.class.getName()).log(Level.SEVERE, null, ex);
    } catch (DescriptionException ex) {
        Logger.getLogger(NewsManager.class.getName()).log(Level.SEVERE, null, ex);
    }
    }
     
   
   

    
    public ArrayList<News> getNewsByTypeOfTitle(String title) throws SQLException, it.unisa.integrazione.database.exception.ConnectionException {
        Statement stmt = null;
        ResultSet rs = null;
        Connection connection = null;
        News avviso = new News();
        ArrayList<News> listAvviso = new ArrayList<News>();
 try {
            String sql= "select * from "
                + NewsManager.TABLE_News
                +"where title= '" + testTitle(title) + "'";

            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new it.unisa.integrazione.database.exception.ConnectionException();
            }
            stmt = connection.createStatement();
            rs  = Utility.queryOperation(connection, sql);

            while (rs.next()) {
                avviso = new News();
                avviso.setId(rs.getInt("idNews"));
                avviso.setTitle(rs.getString("title"));
                avviso.setDescription(rs.getString("description"));
                  listAvviso.add(avviso);

            }
        } catch (TitleException ex) {
        Logger.getLogger(NewsManager.class.getName()).log(Level.SEVERE, null, ex);
    }  finally {

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
    
    
    
    public ArrayList<News> getAllNews() throws SQLException, it.unisa.integrazione.database.exception.ConnectionException {
        Statement stmt = null;
        ResultSet rs = null;
        Connection connection = null;
        News avviso = new News();
        ArrayList<News> listAvviso = new ArrayList<News>();

        try {
            connection = DBConnection.getConnection();
String query = "select * from "
        + NewsManager.TABLE_News;

            if (connection == null) {
                throw new it.unisa.integrazione.database.exception.ConnectionException();
            }
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
    
    public int testid(int id) throws IdException {
        if(id<0){
            throw new IdException("l'id non puo' essere minore di 0");
        }
        return id;
    }  
    
    public String testTitle(String title) throws TitleException {
        if(title.equals(null)&&title.length()>20){
            
            throw new TitleException("il titolo e' sbagliato"); 
        }
        return title;
    }
    
    public String testDescription(String description) throws DescriptionException{
         if(description.equals(null)){
            
            throw new DescriptionException("la descrizione e' sbagliata"); 
        }
         return description;
    }
}
