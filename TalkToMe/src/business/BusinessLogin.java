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

    private static final String MSG_ERRO_SENHA_INVALIDA = "Chave ou senha inválida!";

    private static BusinessLogin instance = null;

    public static BusinessLogin getInstance() {
        if (instance == null) {
            instance = new BusinessLogin();
        }
        return instance;
    }

    private BusinessLogin() {
    }

    public Contato conectar(String chave, String senha) throws BusinessException {
        throw new BusinessException(MSG_ERRO_SENHA_INVALIDA);
    }
}
