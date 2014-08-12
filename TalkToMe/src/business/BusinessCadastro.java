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
public class BusinessCadastro {

    private static final String MSG_ERRO_CADASTRAR = "Não foi possível realizar o cadastro!";
    private static final String MSG_ERRO_SENHA_INCORRETA = "As senhas informadas são diferentes!";
    private static BusinessCadastro instance = null;

    public static BusinessCadastro getInstance() {
        if (instance == null) {
            instance = new BusinessCadastro();
        }
        return instance;
    }

    private BusinessCadastro() {
    }

    public Contato cadastrar(String nick, String senha) throws BusinessException {
        //TODO: Isto é um teste. A verdadeira verificação deve ser implementada!
        if ("".equals("")) {
            return new Contato("000110045", senha, nick, null);
        }
        throw new BusinessException(MSG_ERRO_CADASTRAR);
    }

    public String getMeuIPAtual() {
        return "192.168.0.1";
    }

    public void verificarSenha(String senha, String confirmarSenha) throws BusinessException {
        if (!senha.equals(confirmarSenha)) {
            throw new BusinessException(MSG_ERRO_SENHA_INCORRETA);
        }
    }
}
