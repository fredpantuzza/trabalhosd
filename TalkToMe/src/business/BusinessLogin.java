/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import model.Contato;

/**
 *
 * @author Thiago
 */
public class BusinessLogin {

    private static BusinessLogin instance = null;

    public static BusinessLogin getInstance() {
        if (instance == null) {
            instance = new BusinessLogin();
        }
        return instance;
    }

    private BusinessLogin() {
    }

    public Contato conectar(String chave, String senha) {
        throw new RuntimeException("Este método não foi implementado!");
    }
}
