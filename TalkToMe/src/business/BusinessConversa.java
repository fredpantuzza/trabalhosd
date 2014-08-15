/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import model.Conexao;
import model.Mensagem;

/**
 *
 * @author Thiago
 */
public class BusinessConversa {

    private static BusinessConversa instance;

    public static BusinessConversa getInstance() {
        if (instance == null) {
            instance = new BusinessConversa();
        }
        return instance;
    }

    private final BusinessConexao businessConexao;

    private BusinessConversa() {
        this.businessConexao = BusinessConexao.getInstance();
    }

    public Mensagem receberMensagem(Conexao conexao) throws BusinessException {
        return this.businessConexao.receberMensagem(conexao);
    }

    public void enviarMensagem(Conexao conexao, Mensagem msg) throws BusinessException {
        this.businessConexao.enviarMensagem(conexao, msg);
    }

}
