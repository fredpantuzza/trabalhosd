/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package dto;

import java.util.List;
import common.ActionResult;

/**
 *
 * @author frederico
 */
public class UserListReturnDTO extends ReturnDTO {
    
    private List<UserDTO> userDTOs;

    public UserListReturnDTO(List<UserDTO> userDTOs, ActionResult result) {
        super(result);
        this.userDTOs = userDTOs;
    }

    public UserListReturnDTO(List<UserDTO> userDTOs, ActionResult result, String message) {
        super(result, message);
        this.userDTOs = userDTOs;
    }

    public List<UserDTO> getUserDTOs() {
        return userDTOs;
    }

    public void setUserDTOs(List<UserDTO> userDTOs) {
        this.userDTOs = userDTOs;
    }
    
}
