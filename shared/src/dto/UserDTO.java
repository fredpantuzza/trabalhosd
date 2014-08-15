package dto;

import java.util.List;

/**
 * Dados de um usuário.
 * @author frederico.pantuzza
 */
public class UserDTO implements DTO {

    /** Chave do usuário. */
    private String id;
    /** Nome de usuário (apelido). */
    private String nick;
    /** Senha do usuário. */
    private String password;
    /** Último IP válido. */
    private String lastIp;
    /** Ids dos contatos do usuário. */
    private List<String> contacts;

    public UserDTO(String id, String nick, String password, String lastIp, List<String> contacts) {
        this.id = id;
        this.nick = nick;
        this.password = password;
        this.lastIp = lastIp;
        this.contacts = contacts;
    }

    public UserDTO(String nick, String password, String lastIp) {
        this.nick = nick;
        this.password = password;
        this.lastIp = lastIp;
    }

    @Override
    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastIp() {
        return lastIp;
    }

    public void setLastIp(String lastIp) {
        this.lastIp = lastIp;
    }

    public List<String> getContacts() {
        return contacts;
    }

    public void setContacts(List<String> contacts) {
        this.contacts = contacts;
    }
    
}
