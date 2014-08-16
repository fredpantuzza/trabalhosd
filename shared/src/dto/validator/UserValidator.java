/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto.validator;

import dto.UserDTO;

/**
 *
 * @author frederico
 */
public class UserValidator implements Validator<UserDTO> {

    @Override
    public boolean isValid(UserDTO dto) {
        boolean valid = true;
        
        valid &= (!this.isEmpty(dto.getId()) || (this.isEmpty(dto.getId()) && !this.isEmpty(dto.getNick())));
        valid &= (!this.isEmpty(dto.getPassword()));
        
        return valid;
    }
    
    private boolean isEmpty(String s) {
        return (s == null) || s.isEmpty();
    }
    
}
