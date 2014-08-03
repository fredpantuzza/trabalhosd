package dto;

/**
 * Dados de um usuário.
 * @author frederico.pantuzza
 */
public class UserDTO implements DTO {

    /** Nome de usuário (apelido). */
    private String nick;
    
    @Override
    public String getId() {
        return this.nick;
    }
    
}
