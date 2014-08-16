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
        DBObject findObj = new BasicDBObject(DAOObject.DB_FIELD_ID, new ObjectId(id));
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
        if (user.getContacts() != null) {
            for (String userId : user.getContacts()) {
                UserDTO contact = this.getUser(userId);
                if (contact != null) {
                    contacts.add(contact);
                }
            }
        }
        return contacts;
    }
    
    /**
     * Verifica nick e senha de um usuário.
     * @param id Id.
     * @param pass Senha.
     * @return Resultado da verificação.
     * @throws Exception Caso ocorra algum erro.
     */
    public UserDTO login(String id, String pass) throws Exception {
        DBObject doc = this.convertDTOToDAO(new UserDTO(id, null, pass, null, null));
        doc = this.getCollection(UserDAO.COLLECTION_NAME).findOne(doc);
        if (doc != null) {
            return this.convertDAOToDTO(doc);
        }
        return null;
    }
    
    /**
     * Insere um usuário.
     * @param user O usuário.
     */
    public ObjectId insert(UserDTO user) throws Exception {
        DBObject doc = this.convertDTOToDAO(user);
        WriteResult result = this.getCollection(UserDAO.COLLECTION_NAME).insert(doc);
        
        return (ObjectId) doc.get(DAOObject.DB_FIELD_ID);
    }
    
    /**
     * Adiciona um contato a um usuário (o contato também recebe como contato o usúario).
     * @param userId Id do usuário.
     * @param contactId Id do contato.
     * @return O resultado da operação.
     */
    public boolean addContact(String userId, String contactId) {
        return this.addContactToUser(userId, contactId) && this.addContactToUser(contactId, userId);
    }
    
    /**
     * Remove um contato de um usuário.
     * @param userId Id do usuário.
     * @param contactId Id do contato.
     * @return O resultado da operação.
     */
    public boolean removeContact(String userId, String contactId) {
        return this.removeContactFromUser(userId, contactId) && this.removeContactFromUser(contactId, userId);
    }
    
    /**
     * Substitui todos os contatos de um usuário.
     * @param userId Id do usuário.
     * @param contactsIds Novos contatos.
     * @return O resultado da operação.
     */
    public boolean replaceContacts(String userId, List<String> contactsIds) {
        UserDTO userDTO = this.getUser(userId);
        
        // remove contatos atuais
        for (String contactId : userDTO.getContacts()) {
            this.removeContactFromUser(userId, contactId);
        }
        
        // adiciona novos contatos
        for (String contactId : contactsIds) {
            this.addContactToUser(userId, contactId);
        }
        
        return true;
    }
    
    /**
     * Adiciona um contato a um usuário.
     * @param userId Id do usuário.
     * @param contactId Id do contato.
     * @return O resultado da operação.
     */
    private boolean addContactToUser(String userId, String contactId) {
        DBObject userObj = new BasicDBObject(DAOObject.DB_FIELD_ID, new ObjectId(userId));
        userObj = this.getCollection(UserDAO.COLLECTION_NAME).findOne(userObj);
        
        List<String> contacts = new ArrayList<String>();
        if (userObj.get(UserDAO.DB_FIELD_CONTACTS) instanceof List) {
            contacts = (List) userObj.get(UserDAO.DB_FIELD_CONTACTS);
        }
        
        // adiciona contato à lista
        contacts.add(contactId);
        userObj.put(UserDAO.DB_FIELD_CONTACTS, contacts);
        
        WriteResult result = this.getCollection(UserDAO.COLLECTION_NAME).save(userObj);
        System.out.println(result.getN());
        return result.getN() > 0;
    }
    
    /**
     * Remove um contato de um usuário.
     * @param userId Id do usuário.
     * @param contactId Id do contato.
     * @return O resultado da operação.
     */
    private boolean removeContactFromUser(String userId, String contactId) {
        DBObject userObj = new BasicDBObject(DAOObject.DB_FIELD_ID, new ObjectId(userId));
        userObj = this.getCollection(UserDAO.COLLECTION_NAME).findOne(userObj);
        
        List<String> contacts = new ArrayList<String>();
        if (userObj.get(UserDAO.DB_FIELD_CONTACTS) instanceof List) {
            contacts = (List) userObj.get(UserDAO.DB_FIELD_CONTACTS);
        }
        
        // adiciona contato à lista
        contacts.remove(contactId);
        userObj.put(UserDAO.DB_FIELD_CONTACTS, contacts);
        
        WriteResult result = this.getCollection(UserDAO.COLLECTION_NAME).save(userObj);
        System.out.println(result.getN());
        return result.getN() > 0;
    }

    @Override
    protected DBObject convertDTOToDAO(UserDTO dto) throws InvalidDTOException {
        if (!new UserValidator().isValid(dto)) {
            throw new InvalidDTOException("Objeto inválido.");
        }

        BasicDBObjectBuilder obj = BasicDBObjectBuilder.start();
        if (dto.getId() != null) {
            obj.add(DAOObject.DB_FIELD_ID, new ObjectId(dto.getId()));
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