/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package business.converter;

import dto.UserDTO;
import model.Contato;

/**
 *
 * @author frederico
 */
public class UserDTOToContatoConverter {
    
    public Contato convert(UserDTO from) {
        Contato to = new Contato();
        
        to.setChave(from.getId());
        to.setNick(from.getNick());
        to.setSenha(from.getPassword());
        to.setLastIP(from.getLastIp());
        
        return to;
    }
    
}
