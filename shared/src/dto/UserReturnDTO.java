/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import common.ActionResult;

/**
 *
 * @author frederico
 */
public class UserReturnDTO extends ReturnDTO {
    
    private UserDTO userDTO;

    public UserReturnDTO(UserDTO userDTO, ActionResult result) {
        super(result);
        this.userDTO = userDTO;
    }

    public UserReturnDTO(UserDTO userDTO, ActionResult result, String message) {
        super(result, message);
        this.userDTO = userDTO;
    }

    public UserDTO getUserDTO() {
        return userDTO;
    }

    public void setUserDTO(UserDTO userDTO) {
        this.userDTO = userDTO;
    }
    
}
