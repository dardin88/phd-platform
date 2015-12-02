/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unisa.integrazione.database;
import it.unisa.integrazione.database.exception.MissingDataEccezione;
import it.unisa.integrazione.model.News;
import com.sun.mail.iap.ConnectionException;
import it.unisa.integrazione.model.Person;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

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


    public void add(News anews) throws SQLException, MissingDataEccezione {
        Connection connect = DBConnection.getConnection();
        

        String sql = "INSERT INTO news (idnews, title,description) VALUES ('" + anews.getId() + "','" + anews.getTitle() + "','"+anews.getDescription()+ "')";
                                                                         //  ('"+codice +"','"+descrizione +"','"+ora +"','"+costo +"','"+iscritti+"')");

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

    public News getNewsByNumber(int aidnews) throws SQLException, ConnectionException, MissingDataEccezione {
        Statement stmt = null;
        ResultSet rs = null;
        Connection connection = null;
        News anews = null;

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
   boolean cancellaAvvisi (int aidnews) {
        
Statement stmt = null;
        Connection connection = null;
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
    /*
   public News modNews(int idnews) {
        Statement stmt = null;
        ResultSet rs = null;
        
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
            connection = DBConnection.getConnection();

            if (connection == null) {
                throw new it.unisa.integrazione.database.exception.ConnectionException();
            }

            stmt = connection.createStatement();
            rs = stmt.executeQuery(query);

            while (rs.next()) {
                avviso = new News();
                avviso.setId(/*rs.getInt("idnews")*/);
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
}

   
