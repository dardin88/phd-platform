package it.unisa.dottorato.Cycle;

import it.unisa.dottorato.Curriculum.Curriculum;
import it.unisa.dottorato.Curriculum.CurriculumManager;
import it.unisa.dottorato.curriculumcic.CurriculumcicManager;
import it.unisa.dottorato.account.Professor;
import it.unisa.dottorato.curriculumcic.Curriculumcic;
import it.unisa.dottorato.curriculumcic.CurriculumcicException;
import it.unisa.dottorato.exception.IdException;
import it.unisa.dottorato.exception.DescriptionException;
import it.unisa.dottorato.exception.DateException;
import it.unisa.dottorato.exception.ReferenceException;
import it.unisa.dottorato.exception.NameException;
import it.unisa.dottorato.utility.Utility;
import it.unisa.integrazione.database.DBConnection;
import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


/**Classe per la gestione dei cicli
 *
 * @author Tommaso Minichiello
 */
public class CycleManager {

    /**
     * I nomi delle tabelle
     */
    private static final String TABLE_CYCLE = "cycle";
    private static final String TABLE_CURRICULUMCIC = "curriculumcic";
    private static final String TABLE_TEACH = "teach";
    private static final String TABLE_PROFESSOR = "professor";
    private static final String TABLE_ACCOUNT = "account";
    private static final String TABLE_CURRICULUM = "curriculum";
    
    //	 istanza della classe
    private static CycleManager instance;

    /**
     * Il costruttore della classe e' dichiarato privato, per evitare
     * l'istanziazione di oggetti della classe .
     */
    private CycleManager() {
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
    public static synchronized CycleManager getInstance() {
        if (instance == null) {
            instance = new CycleManager();
        }
        return instance;
    }

    /**
     * Metodo della classe incaricato dell'inserimento di una nuova entita'
     * nella tabella phdCycle del database.
     *
     * @param pCycle il nuovo ciclo da inserire
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * @throws it.unisa.dottorato.Cycle.CycleException
     * @throws it.unisa.dottorato.exception.IdException
     * @throws it.unisa.dottorato.exception.DateException
     * @throws it.unisa.dottorato.exception.DescriptionException
     * 
     */
    public synchronized void insertCycle(Cycle pCycle) throws ClassNotFoundException, 
            SQLException, IOException, CycleException, IdException, DateException,DescriptionException  {
        Connection connect=null;
        try{
            connect = DBConnection.getConnection();
            testCycle(pCycle);
            /*
             * Prepariamo la stringa SQL per inserire un nuovo record 
             * nella tabella phdCycle
             */
            String tSql = "INSERT INTO "
                    + CycleManager.TABLE_CYCLE
                    + " (number, description, year, fkProfessor)"
                    + " VALUES ("
                    + testNumber(pCycle.getNumber())
                    + ",'"
                    + testDescription(pCycle.getDescription())
                    + "','"
                    + testYear(pCycle.getYear())
                    + "',null";

            
            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        }finally {
            DBConnection.releaseConnection(connect);
        }
    }

    /**
     * Metodo della classe incaricato della modifica di un'entita' nella tabella
     * phdCycle del database.
     *
     * @param Number  numero del ciclo da selezionare
     * @param pCycle il nuovo ciclo
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * @throws it.unisa.dottorato.Cycle.CycleException
     * @throws it.unisa.dottorato.exception.IdException
     * @throws it.unisa.dottorato.exception.DateException
     * @throws it.unisa.dottorato.exception.DescriptionException
     */
    public synchronized void updateCycle(int Number, Cycle pCycle)throws ClassNotFoundException, SQLException, 
            IOException, CycleException,IdException,DateException,DescriptionException {
        Connection connect=null;
        try{
            connect = DBConnection.getConnection();
            testCycle(pCycle);
            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "UPDATE "
                    + CycleManager.TABLE_CYCLE
                    + " set description = '"
                    + testDescription(pCycle.getDescription())
                    + "', year = '"
                    + testYear(pCycle.getYear())
                    + "' WHERE number = "
                    + testNumber(Number);           

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        }finally {
            DBConnection.releaseConnection(connect);
        }
    }

    /**
     * Metodo della classe incaricato della modifica di un'entita' nella tabella
     * cycle 
     *
     * @param number numero del il ciclo da selezionare
     * @param fkProfessor il coordinatore da inserire nel ciclo
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * @throws it.unisa.dottorato.exception.IdException
     * @throws it.unisa.dottorato.exception.ReferenceException
     */
    public synchronized void insertCycleCoordinator(int number, String fkProfessor) throws ClassNotFoundException, 
            SQLException, IOException,IdException, ReferenceException, Exception {
        Connection connect=null;
        try{
            connect = DBConnection.getConnection();
            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "UPDATE "
                    + CycleManager.TABLE_CYCLE
                    + " set fkProfessor = '"
                    + testFkProfessor(fkProfessor)
                    + "' WHERE number = "
                    + testNumber(number);           

            //Inviamo la Query al DataBase
            int res = Utility.executeOperation(connect, tSql);
            if (res==0){
                throw new Exception();
            }

            connect.commit();
        }finally {
            DBConnection.releaseConnection(connect);
        }
    }
      /**
     * Metodo della classe incaricato della modifica di un'entita' nella tabella
     * cycle 
     *
     * @param number numero del ciclo
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * @throws it.unisa.dottorato.exception.IdException
     */
    public synchronized void deleteCycleCoordinator(int number) throws ClassNotFoundException, 
            SQLException, IOException, IdException, Exception {
        Connection connect=null;
        try{
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "UPDATE "
                    + CycleManager.TABLE_CYCLE
                    + " set fkProfessor = null WHERE number = "
                    + testNumber(number);           

            //Inviamo la Query al DataBase
            if (Utility.executeOperation(connect, tSql)==0){
                throw new Exception();
            }connect.commit();
        }finally {
            DBConnection.releaseConnection(connect);
        }
    }
     /**
     * Metodo della classe incaricato di cercare il coordinatore di un ciclo
     *
     * @param number  il ciclo da selezionare
     * @return l'account del coordinatore se esistente
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * @throws it.unisa.dottorato.exception.IdException
     */
    public synchronized Professor viewCycleCoordinator(int number) throws 
            ClassNotFoundException, SQLException, IOException, IdException {
        Connection connect = null;
        Professor cord=null;
        try {
            cord=new Professor();
           connect = DBConnection.getConnection();
           /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
             String tSql = "SELECT email, secondaryEmail, surname, name, link, department FROM "
                    + CycleManager.TABLE_CYCLE
                    +","
                    + CycleManager.TABLE_PROFESSOR
                    + ","
                    + CycleManager.TABLE_ACCOUNT
                    + " WHERE number = "
                    + testNumber(number)
                    +" AND fkProfessor = fkAccount AND fkAccount = secondaryEmail";   


            connect.commit();
            ResultSet result = Utility.queryOperation(connect, tSql);

             if (result.next()) {
                cord.setEmail(result.getString("email"));
                cord.setSecondaryEmail(result.getString("secondaryEmail"));
                cord.setSurname(result.getString("surname"));
                cord.setName(result.getString("name"));
                cord.setLink(result.getString("link"));
                cord.setDepartment(result.getString("department"));
                cord.setPassword(null);
                cord.setAdmin(false);
                cord.setTypeAccount(null);
                cord.setfkAccount(cord.getSecondaryEmail());
            }
            return cord;
        }finally {
            DBConnection.releaseConnection(connect);
        }
    }
    
    /**
     * Metodo della classe incaricato della cancellazione di un'entita' nella
     * tabella phdCycle del database.
     *
     * @param number il numero del ciclo da cancellare
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * @throws it.unisa.dottorato.exception.IdException
     */
    public synchronized void deleteCycle(int number) throws ClassNotFoundException, 
            SQLException, IOException, IdException, Exception {
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "DELETE FROM "
                    + CycleManager.TABLE_CYCLE
                    + " WHERE number = "
                    + testNumber(number);

            //Inviamo la Query al DataBase
            if (Utility.executeOperation(connect, tSql)==0){
                throw new Exception();
            }connect.commit();
        } finally {
            DBConnection.releaseConnection(connect);
        }
    }

    /**
     * Metodo della classe incaricato della ricerca delle informazioni di un
     * ciclo contenuto nella tabella phdCycle.
     *
     * @param number il numero del ciclo da selezionare
     * @return ritorna il ciclo <code>number</code> se esiste, lancia un'eccezione
     * altrimenti
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * @throws it.unisa.dottorato.exception.IdException
     */
    public synchronized Cycle getCycleByNumber(int number) throws ClassNotFoundException,
            SQLException, IOException, IdException {
        Connection connect = null;
        Cycle cycle=null;
        try {
            cycle = new Cycle();
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "SELECT * FROM "
                    + CycleManager.TABLE_CYCLE
                    + " WHERE number = "
                    + testNumber(number);

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);
            
            if (result.next()) {
                cycle.setNumber(result.getInt("number"));
                cycle.setDescription(result.getString("description"));
                cycle.setYear(result.getString("year"));
                cycle.setFkProfessor(result.getString("fkProfessor"));
            }

            return cycle;

        } finally {
            DBConnection.releaseConnection(connect);
        }
    }

    /**
     * Metodo della classe incaricato della ricerca dei cicli esistenti.
     *
     * @return restituisce un array list di cicli
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized ArrayList<Cycle> getCycleList() throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
            ArrayList<Cycle> cycles = new ArrayList<>();
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "SELECT * FROM "
                    + CycleManager.TABLE_CYCLE
                    + " ORDER BY number desc";

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);
            Cycle cycle= new Cycle();
            while (result.next()) {
                cycle.setNumber(result.getInt("number"));
                cycle.setDescription(result.getString("description"));
                cycle.setYear(result.getString("year"));
                cycle.setFkProfessor(result.getString("fkProfessor"));
                cycles.add(cycle);
            }

            return cycles;

        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
    
    /**
     * Metodo della classe incaricato della ricerca dei cicli esistenti.
     *
     * @return restituisce un array list dei numeri dei cicli
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     */
    public synchronized ArrayList<Integer> getCyclesListNumers() throws ClassNotFoundException, SQLException, IOException {
        Connection connect = null;
        try {
            ArrayList<Integer> cycles = new ArrayList<>();
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella phdCycle
             */
            String tSql = "SELECT number FROM "
                    + CycleManager.TABLE_CYCLE
                    + " ORDER BY number desc";

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);
            while (result.next()) {
                cycles.add(result.getInt("number"));
            }

            return cycles;

        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
    
     /**
     * Metodo della classe incaricato della ricerca dei cicli esistenti.
     *@param number il numero del ciclo da ricercare
     * @return restituisce un array list di professori che formano il collegio
     * dei docenti del ciclo
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * @throws it.unisa.dottorato.exception.IdException
     */
    public synchronized ArrayList<Professor> viewCollegeCycle(int number) throws ClassNotFoundException,
            SQLException, IOException, IdException {
        Connection connect = null;
        ArrayList<Professor> prof=null;
        try {
            prof = new ArrayList<>();
            Professor cord=new Professor();
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella teach
             */
            String tSql = "SELECT email, secondaryEmail, surname, name, link, department FROM "
                    + CycleManager.TABLE_TEACH+","+CycleManager.TABLE_PROFESSOR
                    +","+CycleManager.TABLE_ACCOUNT
                    + " WHERE fkCycle = "
                    + testNumber(number)
                    +" AND secondaryEmail=fkAccount AND fkAccount=fkProfessor ";

            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);
            while (result.next()) {
                cord.setEmail(result.getString("email"));
                cord.setSecondaryEmail(result.getString("secondaryEmail"));
                cord.setSurname(result.getString("surname"));
                cord.setName(result.getString("name"));
                cord.setLink(result.getString("link"));
                cord.setDepartment(result.getString("department"));
                cord.setPassword(null);
                cord.setAdmin(false);
                cord.setTypeAccount(null);
                cord.setfkAccount(cord.getSecondaryEmail());
                prof.add(cord);
            }

            return prof;

        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
       
    /**
     * Metodo della classe incaricato dell'inserimento di una nuova entita'
     * nella tabella phdlass del database.
     *
     * @param pCurriculumcic il nuovo curriculum da inserire
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * @throws it.unisa.dottorato.exception.NameException
     * @throws it.unisa.dottorato.curriculumcic.CurriculumcicException
     * @throws it.unisa.dottorato.exception.IdException
     */
    public synchronized void insertCurriculumcic(Curriculumcic pCurriculumcic) throws ClassNotFoundException, 
            SQLException, IOException, NameException,CurriculumcicException, IdException {
        Connection connect=null;
        try{
            connect = DBConnection.getConnection(); 
            CurriculumcicManager.getInstance().testCurriculucic(pCurriculumcic);
            /*
             * Prepariamo la stringa SQL per inserire un nuovo record 
             * nella tabella curriculumcic
             */
            String tSql = "INSERT INTO "
                    + CycleManager.TABLE_CURRICULUMCIC
                    + " (fkCurriculum, fkCycle, fkProfessor)"
                    + " VALUES ('"
                    + CurriculumManager.getInstance().testName(pCurriculumcic.getfkCurriculum())
                    + "',"
                    + testNumber(pCurriculumcic.getfkCycle())
                    + ",null";

            //Inviamo la Query al DataBase
            Utility.executeOperation(connect, tSql);

            connect.commit();
        } finally {
            DBConnection.releaseConnection(connect);
        }
    }

    
    /**
     * Metodo della classe incaricato della cancellazione di un'entita' nella
     * tabella curriculumcic del database.
     *
     * @param fkCurriculum il curriculum
     * @param fkCycle il ciclo
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * @throws it.unisa.dottorato.exception.IdException
     * @throws it.unisa.dottorato.exception.NameException
     */
    public synchronized void deleteCurriculumcic(int fkCycle, String fkCurriculum) 
            throws ClassNotFoundException, SQLException, IOException, IdException, NameException, Exception  {
        Connection connect = null;
        try {
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per modificare un record 
             * nella tabella curriculumcic
             */
            String tSql = "DELETE FROM "
                    + CycleManager.TABLE_CURRICULUMCIC
                    + " WHERE fkCycle = "
                    + testNumber(fkCycle) + " AND "
                    +" fkCurriculum = '"
                    + CurriculumManager.getInstance().testName(fkCurriculum) + "'";

            //Inviamo la Query al DataBase
            if (Utility.executeOperation(connect, tSql)==0){
                throw new Exception();
            }
            connect.commit();
        } finally {
            DBConnection.releaseConnection(connect);
        }
    }

   
    /**
     * Metodo della classe incaricato della ricerca delle classi esistenti.
     *@param number il numero del ciclo da selezionare
     * @return restituisce un array list dei curriculum del ciclo <code>number</code>
     * @throws java.lang.ClassNotFoundException
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * @throws it.unisa.dottorato.exception.IdException
     */
    public synchronized ArrayList<Curriculum> getCurriculumcicList(int number) throws ClassNotFoundException, 
            SQLException, IOException, IdException {
        Connection connect = null;
        ArrayList<Curriculum> List=null;
        try {
            List= new ArrayList<>();
            Curriculum c=new Curriculum();
            // Otteniamo una Connessione al DataBase
            connect = DBConnection.getConnection();

            /*
             * Prepariamo la stringa SQL per la ricerca dei record 
             * nella tabella curriculumcic
             */
            String tSql = "SELECT * FROM "
                    + CycleManager.TABLE_CURRICULUMCIC 
                    + ","
                    + CycleManager.TABLE_CURRICULUM
                    + " WHERE fkCurriculum=curriculum.name AND fkCycle ="
                    + testNumber(number);
            
            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            while (result.next()) {
                c.setName(result.getString("curriculum.name"));
                c.setDescription(result.getString("curriculum.description"));
                List.add(c);
            }

            return List;

        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
    
    /**
     * Metodo della classe incaricato di calcolare il numero del prossimo ciclo da inserire
     * nella tabella Cycle del database.
     * @return restituisce il prossimo numero
     * @throws java.sql.SQLException
     * @throws java.io.IOException
     * 
     */
    public synchronized int nextNumber() throws SQLException, IOException {
        int c=1;
        
       
        try (Connection connect = DBConnection.getConnection()) {
            String tSql = "SELECT number FROM "
                    + CycleManager.TABLE_CYCLE
                    + "ORDER BY number DESC LIMIT 1";
            //Inviamo la Query al DataBase
             ResultSet result = Utility.queryOperation(connect, tSql);
            if(result.next()){
                c = result.getInt("number")+1;
            }
            connect.commit();
            return c;
        } 
    }
    
    /** Metodo per il testing del numero di un ciclo; non può essere minore o uguale
     * di 0 e maggiore di 999
     * 
     * @param number il numero da testare
     * @return restituisce il numero se valido, lancia un'eccezione altrimenti
     * @throws IdException 
     */
     public Integer testNumber(int number) throws IdException {
        if(number <=0 || number > 999) 
            throw new IdException("il numero del ciclo è sbagliato");
        return number;
    }
    
       /**Metodo per il testing della descrizione del ciclo; verifica che la stringa
     * <code>description</code> non sia vuota o non abbia una lunghezza superiore
     * ai 65535 caratteri
     * 
     * @param description descrizione del ciclo
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws DescriptionException 
     */
    public String testDescription(String description) throws DescriptionException {
        if(description.isEmpty() || description.length() > 65536) 
            throw new DescriptionException("Descrizione ciclo errata.");
        return description;
    }
    
    /** Metodo per il testing dell'anno di un ciclo; non può essere una stringa
     * vuota o maggiore di 4 caratteri
     * 
     * @param year la stringa da testare
     * @return restituisce la stringa se valida, lancia un'eccezione altrimenti
     * @throws DateException 
     */
    public String testYear(String year) throws DateException {
        if(year.length()!=4) 
            throw new DateException("Anno ciclo errato.");
        int n1=0;
        for(int i=0; i<4; i++){
            n1=Integer.parseInt(year.substring(i, i+1));
            if(n1>=0 && n1<=9)
                return year;
            else
                throw new DateException("Anno ciclo errato.");
        }
        return year;
    }
    
     /** Metodo della classe incaricato di verificare l'esistenza di un ciclo
     * 
     * @param c il ciclo da ricercare
     * @return restituisce true se il ciclo esiste, false altrimenti
     * @throws ClassNotFoundException
     * @throws SQLException
     * @throws IOException 
     */
    public boolean existCycle(Cycle c)throws ClassNotFoundException, SQLException, IOException{
         Connection connect = null;
        try {         
            connect = DBConnection.getConnection();
            /*
             * Prepariamo la stringa SQL per ricercare il ciclo c
             * nella tabella cycle
             */
            String tSql = "SELECT * FROM "
                    + CycleManager.TABLE_CYCLE
                    + " WHERE name = '"
                    + c.getNumber()+ "'";
            //Inviamo la Query al DataBase
            ResultSet result = Utility.queryOperation(connect, tSql);

            if (result.next()) {
               return true;
            }
            return false;
        } finally {
            DBConnection.releaseConnection(connect);
        }
    }
    /** Metodo della classe per il testing di un ciclo; verifica che il ciclo non 
     * sia <code>null</code>
     * 
     * @param c il ciclo da testare
     * @throws CycleException 
     */
    public void testCycle(Cycle c)throws CycleException{
        if (c==null)
            throw new CycleException();
    }
    
    /** Metodo della classe per il testing dell'email del professore; verifica che 
     * non sia di lunghezza minore di 10 caratteri o maggiore di 50 caratteri, e che ci
     * sia il carattere '@'
     * 
     * @param s stringa da testare
     * @return restituisce la stringa s se valida, lancia un'eccezione altrimenti
     * @throws ReferenceException 
     */
    public String testFkProfessor(String s) throws ReferenceException{
        if (s==null) return s;
        if(s.length()<10 || s.length()>50 || s.indexOf("@")==-1)
            throw new ReferenceException();
        return s;
    }
}
