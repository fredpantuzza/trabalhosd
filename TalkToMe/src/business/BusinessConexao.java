/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import model.Conexao;
import model.Contato;

/**
 *
 * @author Thiago
 */
public class BusinessConexao {

    private static final String MSG_ERRO_CONTATO_OFFLINE = "Não foi possível conectar com o contato.";
    private static BusinessConexao instance = null;

    private BusinessConexao() {
    }

    public static BusinessConexao getInstance() {
        if (instance == null) {
            instance = new BusinessConexao();
        }
        return instance;
    }

    public Conexao getConexaoContato(Contato user, Contato contato) throws BusinessException {
        throw new BusinessException(MSG_ERRO_CONTATO_OFFLINE);
    }

}