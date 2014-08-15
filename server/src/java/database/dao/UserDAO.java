package database.dao;

import com.mongodb.BasicDBObject;
import com.mongodb.BasicDBObjectBuilder;
import com.mongodb.DB;
import com.mongodb.DBObject;
import com.mongodb.WriteResult;
import dto.UserDTO;
import dto.validator.InvalidDTOException;
import dto.validator.UserValidator;
import java.util.ArrayList;
import java.util.List;
import org.bson.types.ObjectId;

/**
 * Manipulador de usuários no banco de dados.
 * @author frederico.pantuzza
 */
public class UserDAO extends DAOObject<UserDTO> {
    
    /** Nome da coleção que armazena dados de usúarios. */
    private static final String COLLECTION_NAME = "user";
    
    private static final String DB_FIELD_NICK     = "nick";
    private static final String DB_FIELD_PASSWORD = "password";
    private static final String DB_FIELD_LAST_IP  = "lastIp";
    private static final String DB_FIELD_CONTACTS = "contacts";
    
    /**
     * Construtor.
     * @param database Descritor do banco de dados.
     */
    public UserDAO(DB database) {
        super(database);
    }
    
    /**
     * Recupera o usuário pelo id.
     * @param id Id do usuário.
     * @return O DTO do usuário.
     */
    public UserDTO getUser(String id) {
        DBObject findObj = new BasicDBObject("_id", new ObjectId(id));
        findObj = this.getCollection(UserDAO.COLLECTION_NAME).findOne(findObj);
        if (findObj != null) {
            return this.convertDAOToDTO(findObj);
        }
        return null;
    }
    
    /**
     * Obtém os contatos de um usúario.
     * @param userId Id do usuários.
     * @return Lista de contatos do usuário.
     */
    public List<UserDTO> getUserContacts(String userId) {
        UserDTO user = this.getUser(userId);
        return this.getUserContacts(user);
    }
    
    /**
     * Obtém os contatos de um usúario.
     * @param user Usuário.
     * @return Lista de contatos do usuário.
     */
    public List<UserDTO> getUserContacts(UserDTO user) {
        List<UserDTO> contacts = new ArrayList<UserDTO>();
        for (String userId : user.getContacts()) {
            UserDTO contact = this.getUser(userId);
            if (contact != null) {
                contacts.add(contact);
            }
        }
        return contacts;
    }
    
    /**
     * Verifica nick e senha de um usuário.
     * @param nick Nick.
     * @param pass Senha.
     * @return Resultado da verificação.
     * @throws Exception Caso ocorra algum erro.
     */
    public boolean login(String nick, String pass) throws Exception {
        DBObject doc = this.convertDTOToDAO(new UserDTO(nick, pass, null));
        return this.getCollection(UserDAO.COLLECTION_NAME).findOne(doc) != null;
    }
    
    /**
     * Insere um usuário.
     * @param user O usuário.
     */
    public ObjectId insert(UserDTO user) throws Exception {
        DBObject doc = this.convertDTOToDAO(user);
        WriteResult result = this.getCollection(UserDAO.COLLECTION_NAME).insert(doc);
        // TODO: lançar exceção em caso de erro
        return (ObjectId) doc.get("_id");
    }

    @Override
    protected DBObject convertDTOToDAO(UserDTO dto) throws InvalidDTOException {
        if (!new UserValidator().isValid(dto)) {
            throw new InvalidDTOException("Objeto inválido.");
        }

        BasicDBObjectBuilder obj = BasicDBObjectBuilder.start();
        if (dto.getId() != null) {
            obj.add(DAOObject.DB_FIELD_ID, dto.getId());
        }
        if (dto.getNick() != null) {
            obj.add(UserDAO.DB_FIELD_NICK, dto.getNick());
        }
        if (dto.getPassword() != null) {
            obj.add(UserDAO.DB_FIELD_PASSWORD, dto.getPassword());
        }
        if (dto.getLastIp() != null) {
            obj.add(UserDAO.DB_FIELD_LAST_IP, dto.getLastIp());
        }
        if (dto.getContacts() != null) {
            obj.add(UserDAO.DB_FIELD_CONTACTS, dto.getContacts());
        }
        return obj.get();
    }

    @Override
    protected UserDTO convertDAOToDTO(DBObject dao) {
        String id             = null;
        String nick           = null;
        String lastIp         = null;
        List<String> contacts = null;
        
        if (dao.get(DAOObject.DB_FIELD_ID) instanceof String) {
            id       = (String) dao.get(DAOObject.DB_FIELD_ID);
        }
        if (dao.get(UserDAO.DB_FIELD_NICK) instanceof String) {
            nick     = (String) dao.get(UserDAO.DB_FIELD_NICK);
        }
        if (dao.get(UserDAO.DB_FIELD_LAST_IP) instanceof String) {
            lastIp   = (String) dao.get(UserDAO.DB_FIELD_LAST_IP);
        }
        if (dao.get(UserDAO.DB_FIELD_CONTACTS) instanceof List) {
            contacts = (List) dao.get(UserDAO.DB_FIELD_CONTACTS);
        }
        
        return new UserDTO(id, nick, null, lastIp, contacts);
    }

}