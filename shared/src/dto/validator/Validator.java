/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto.validator;

import dto.DTO;

/**
 *
 * @author frederico
 */
public interface Validator<T extends DTO> {
    
    boolean isValid(T dto);
    
}
