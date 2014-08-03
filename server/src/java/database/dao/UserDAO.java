package database.dao;

import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBObject;
import dto.UserDTO;

/**
 * Manipulador de usuários no banco de dados.
 * @author frederico.pantuzza
 */
public class UserDAO extends DAOObject<UserDTO> {
    
    /** Nome da coleção que armazena dados de usúarios. */
    private static final String COLLECTION_NAME = "user";
    
    /**
     * Construtor.
     * @param database Descritor do banco de dados.
     */
    public UserDAO(DB database) {
        super(database);
    }
    
    /**
     * Insere um usuário.
     * @param user O usuário.
     */
    public void insert(UserDTO user) {
        this.getCollection(UserDAO.COLLECTION_NAME).insert(this.convertDTOToDAO(user));
    }

    @Override
    protected DBObject convertDTOToDAO(UserDTO dto) {
        return BasicDBObjectBuilder.start()
                .add("_id", dto.getId())
                .get();
    }
    
}
