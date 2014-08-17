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

    private static final String MSG_ERRO_SENHA_INCORRETA = "As senhas informadas são diferentes!";
    private static BusinessCadastro instance = null;

    public static BusinessCadastro getInstance() {
        if (instance == null) {
            instance = new BusinessCadastro();
        }
        return instance;
    }

    private final BusinessServidor businessServidor;

    private BusinessCadastro() {
        this.businessServidor = BusinessServidor.getInstance();
    }

    /**
     * Tenta efetuar o cadastro do usuário no servidor. Exceções podem ser
     * lançadas se houverem falhas durante o cadastro.
     *
     * @param nick
     * @param senha
     * @return
     * @throws BusinessException
     */
    public Contato cadastrar(String nick, String senha) throws BusinessException {
        return businessServidor.cadastrar(nick, senha);
    }

    /**
     * Verifica se a senha digitada durante o cadastro é uma senha válida. Caso
     * as senhas digitadas nos campos "senha" e "confirmar senha" sejam
     * diferentes, a senha será considerada inválida.
     *
     * @param senha
     * @param confirmarSenha
     * @throws BusinessException
     */
    public void verificarSenha(String senha, String confirmarSenha) throws BusinessException {
        if (!senha.equals(confirmarSenha)) {
            throw new BusinessException(MSG_ERRO_SENHA_INCORRETA);
        }
    }
}
