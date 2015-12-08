/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.dottorato.news;
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
 

    private static NewsManager instance;

    public static NewsManager getInstance() {

        if (instance == null) {
            instance = new NewsManager();
        }
        return instance;

    }


    public void insertNews(News anews) throws SQLException, MissingDataEccezione, ExceptionErroreIdNews, ExceptionErroreTitleNews, ExceptionErroreDescriprion {
        Connection connect = DBConnection.getConnection();
        
testid(anews.getId());
testTitle(anews.getTitle());
testDescription(anews.getDescription());
        String sql = "INSERT INTO news (idnews, title,description) VALUES ('" + anews.getId() + "','" + anews.getTitle() + "','"+anews.getDescription()+ "')";
                                                                         //  ('"+codice +"','"+descrizione +"','"+ora +"','"+costo +"','"+iscritti+"')");
         anews.setId();
        try {
            Statement stmt = connect.createStatement();
            stmt.executeUpdate(sql);
            connect.commit();
        }
        catch(Exception exc){
        exc.printStackTrace();
        throw new   MissingDataEccezione("mancano i dati");
        }
        finally {
            DBConnection.releaseConnection(connect);
        }
    }

    public News getNewsByNumber(int aidnews) throws SQLException, ConnectionException, MissingDataEccezione, ExceptionErroreIdNews {
        Statement stmt = null;
        ResultSet rs = null;
        Connection connection = null;
        News anews = null;
testid(anews.getId());
        String query = "select * from news where idnews = " + aidnews;

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new ConnectionException();
            }

            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);

            if (rs.next()) {
                anews = new News();
                anews.setId(/*rs.getInt("idnews")*/);
                anews.setTitle(rs.getString("title"));
            }
            else throw new   MissingDataEccezione("Avviso non trovato");
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
   boolean deleteNews (int aidnews) throws ExceptionErroreIdNews {
        
Statement stmt = null;
        Connection connection = null;
        testid(aidnews);
        try {
            connection = DBConnection.getConnection();
            stmt = connection.createStatement();
            if (stmt.executeUpdate("DELETE FROM  news WHERE idnews='"+ aidnews+"'")==1){
                                  //("DELETE  FROM corso WHERE CodiceCorso='"+codice +"'")
            return true;
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
            throw new RuntimeException("Delete Query failed!");
        } finally {
            DBConnection.releaseConnection(connection);
        }
        return false;
        
    }
   // Modificherò appena  inizio con le form 
   public synchronized void update_news(int oldNewsId, News pNews) throws ClassNotFoundException, SQLException, IOException, ExceptionErroreTitleNews, ExceptionErroreDescriprion {
        try (Connection connect = DBConnection.getConnection()) {

           testid(oldNewsId);
            testTitle(pNews.getTitle());
            testDescription(pNews.getDescription());
   String tSql = "UPDATE news SET title='"+ Utility.Replace(pNews.getTitle()) +"' AND SET description='"+Utility.Replace(pNews.getDescription())+"'WHERE idnews="+oldNewsId+"'";
                           

            System.out.println(tSql);
            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        } catch (ExceptionErroreIdNews ex) {
            Logger.getLogger(NewsManager.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
     
   
   /*public News modNews(int idnews) {
        Statement stmt = null;
        ResultSet rs = null;
        News oldavviso= new News();
        News oldavviso= avviso.getNewsByNumber(idnews);
        String titolo=avviso.getTitle();
        String content=avviso.getContent();
        Connection connection = null;
        if ( aidnews<0 ) {
            throw new IllegalArgumentException("Non esiste questo avviso");
        } else {
            try {
                connection = DBConnection.getConnection();
                stmt = connection.createStatement();
                rs = stmt.executeQuery("SELECT * FROM news ");
                   
                        
                while (rs.next()) {
                     stmt.executeUpdate("UPDATE news SET title='"+ titolo +"' AND SET description='"+content+"'WHERE idnews="+aidnews+"'");    
                }
            } catch (SQLException ex) {
                ex.printStackTrace();
                throw new RuntimeException("Avviso not found ");
            } finally {
                DBConnection.releaseConnection(connection);
            }
        }
        return null;
    }
*/
    // anche questa aspetto di creare le form per una visione + pulita
   
   public ArrayList<News> getNewsByTypeOfTitle(String title) throws SQLException, it.unisa.integrazione.database.exception.ConnectionException {
        Statement stmt = null;
        ResultSet rs = null;
        Connection connection = null;
        News avviso = new News();
        ArrayList<News> listAvviso = new ArrayList<>();

        String query = "select * from news where title= '" + title + "'";

        try {
            testTitle(title);
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new it.unisa.integrazione.database.exception.ConnectionException();
            }

            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                avviso = new News();
                avviso.getId();
                avviso.setTitle(rs.getString("title"));
                avviso.setDescription(rs.getString("description"));
               
                
               listAvviso.add(avviso);

            }
        } catch (ExceptionErroreTitleNews ex) {
            Logger.getLogger(NewsManager.class.getName()).log(Level.SEVERE, null, ex);
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
    public ArrayList<News> getAllNews() throws SQLException, it.unisa.integrazione.database.exception.ConnectionException {
        Statement stmt = null;
        ResultSet rs = null;
        Connection connection = null;
        News avviso = new News();
        ArrayList<News> listAvviso = new ArrayList<>();

        String query = "select * from news ";

        try {
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new it.unisa.integrazione.database.exception.ConnectionException();
            }

            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                avviso = new News();
                avviso.getId();
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
    
    public void testid(int id) throws ExceptionErroreIdNews{
        if(id<0){
            throw new ExceptionErroreIdNews();
        }
        
    }  
    
    public void testTitle(String title) throws ExceptionErroreTitleNews{
        if(title.equals(null)){
            
            throw new ExceptionErroreTitleNews(); 
        }
    }
    
    public void testDescription(String description) throws ExceptionErroreDescriprion{
         if(description.equals(null)){
            
            throw new ExceptionErroreDescriprion(); 
        }
    }
}
