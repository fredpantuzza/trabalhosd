package dto;

import java.io.Serializable;

/**
 *
 * @author frederico.pantuzza
 */
public interface DTO extends Serializable {
    
    /**
     * Retorna o id do objeto.
     * @return Id do objeto.
     */
    String getId();
    
}
