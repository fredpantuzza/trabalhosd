/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Thiago
 */
public class BusinessRede {

    private static BusinessRede instance = null;

    private BusinessRede() {
    }

    public static BusinessRede getInstance() {
        if (instance == null) {
            instance = new BusinessRede();
        }
        return instance;
    }
    
    public String getMeuIP(){
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException ex) {
            Logger.getLogger(BusinessRede.class.getName()).log(Level.SEVERE, null, ex);
        }
        return InetAddress.getLoopbackAddress().getHostAddress();
    }
}
