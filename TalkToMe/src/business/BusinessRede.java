/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

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
        return "192.168.0.1:6340";
    }
}
