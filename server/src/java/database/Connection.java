package database;

import com.mongodb.DB;
import com.mongodb.MongoClient;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Conexão com banco de dados MongoDB.
 * @author frederico.pantuzza
 */
public class Connection {
    
    /** Endereço do servidor de banco de dados. */
    private static final String ADDRESS = "localhost";
    /** Porta do serviço. */
    private static final Integer PORT = 27017;
    /** Nome do banco. */
    private static final String DBNAME = "chat";
    /** Nome de usuário. */
    private static final String USERNAME = "system";
    /** Senha do usuário. */
    private static final String USERPASS = "systempwd";
    
    /** Conexão com o banco. */
    private MongoClient connection;

    /**
     * Constrói objeto e conecta ao banco.
     */
    public Connection() {
        this(true);
    }

    /**
     * Constrói objeto.
     * @param connect Se <tt>true</tt> já tenta conectar.
     */
    public Connection(boolean connect) {
        if (connect) {
            this.connect();
        }
    }

    /**
     * Inicia conexão com o banco.
     * @return <tt>true</tt> em caso de sucesso e <tt>false</tt> se não.
     */
    public boolean connect() {
        try {
            List<MongoCredential> credentials = new ArrayList<MongoCredential>();
            credentials.add(MongoCredential.createMongoCRCredential(Connection.USERNAME, Connection.DBNAME, Connection.USERPASS.toCharArray()));
            
            this.connection = new MongoClient(new ServerAddress(Connection.ADDRESS, Connection.PORT), credentials);
            return true;
        } catch (UnknownHostException ex) {
            Logger.getLogger(Connection.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    /**
     * Finaliza conexão com banco de dados.
     */
    public void close() {
        this.connection.close();
    }
    
    /**
     * Obtém um manipulador do banco de dados.
     * @return O manipulador do banco de dados.
     */
    public DB getDatabase() {
        DB database = this.connection.getDB(Connection.DBNAME);
        return database;
    }

    public static void tryDisconnect(Connection con) {
        if (con != null) {
            con.close();
        }
    }

}
