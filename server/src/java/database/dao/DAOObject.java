package database.dao;

import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import dto.DTO;

/**
 * Manipulador padrão de objetos
 * @author frederico.pantuzza
 * @param <P> O tipo de dados manipulado.
 */
public abstract class DAOObject<P extends DTO> {
    
    /** Descritor do banco de dados. */
    private final DB database;
    
    /**
     * Construtor.
     * @param database Descritor do banco de dados.
     */
    public DAOObject(DB database) {
        this.database = database;
    }

    /**
     * Retorna database.
     * @return database.
     */
    public DB getDatabase() {
        return database;
    }
    
    /**
     * Retorna a coleção padrão do DAO.
     * @param collectionName Nome da collection.
     * @return a coleção padrão do DAO.
     */
    protected DBCollection getCollection(String collectionName) {
        return this.database.getCollection(collectionName);
    }
    
    /**
     * Converte um DTO para um Objeto DB.
     * @param dto O DTO com os dados.
     * @return Um DAO equivalente.
     */
    protected abstract DBObject convertDTOToDAO(P dto);
    
}
