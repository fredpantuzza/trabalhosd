package business;

import database.Connection;
import database.dao.UserDAO;
import dto.UserDTO;
import java.util.List;
import org.bson.types.ObjectId;

/**
 *
 * @author frederico
 */
public class UserManager {
    
    public UserDTO findUserById(String id) throws Exception {
        Connection con = null;
        try {
            con = new Connection();
            
            UserDAO userDAO = new UserDAO(con.getDatabase());
            return userDAO.getUser(id);
        } catch (Exception e) {
            throw e;
        } finally {
            Connection.tryDisconnect(con);
        }
    }
    
    public List<UserDTO> findUserContactsById(String id) throws Exception {
        Connection con = null;
        try {
            con = new Connection();
            
            UserDAO userDAO = new UserDAO(con.getDatabase());
            return userDAO.getUserContacts(id);
        } catch (Exception e) {
            throw e;
        } finally {
            Connection.tryDisconnect(con);
        }
    }
    
    public UserDTO insertUser(String nick, String pass, String ip) throws Exception {
        UserDTO userDTO = new UserDTO(nick, pass, ip);
        
        Connection con = null;
        try {
            con = new Connection();
            
            UserDAO userDAO = new UserDAO(con.getDatabase());
            ObjectId id = userDAO.insert(userDTO);
            userDTO.setId(id.toString());
            
            return userDTO;
        } catch (Exception e) {
            throw e;
        } finally {
            Connection.tryDisconnect(con);
        }
    }
    
    public boolean login(String nick, String pass) throws Exception {
        Connection con = null;
        try {
            con = new Connection();
            
            return new UserDAO(con.getDatabase()).login(nick, pass);
        } catch (Exception e) {
            throw e;
        } finally {
            Connection.tryDisconnect(con);
        }
    }
    
}
