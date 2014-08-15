/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import model.Contato;
import repository.RepositoryContato;

/**
 * Esta classe implementa os serviços necessários para a tela ViewLogin.
 *
 * @author Thiago
 */
public class BusinessLogin {

    /* Erro gerado quando o servidor não pode ser contactado e o login local também falha. */
    private static final String MSG_ERRO_FALHA_LOGIN = "Não foi possível efetuar o login.";

    private static BusinessLogin instance = null;

    public static BusinessLogin getInstance() {
        if (instance == null) {
            instance = new BusinessLogin();
        }
        return instance;
    }

    private final BusinessServidor businessServidor;
    private final RepositoryContato repositoryContato;

    private BusinessLogin() {
        businessServidor = BusinessServidor.getInstance();
        repositoryContato = RepositoryContato.getInstance();
    }

    public Contato login(String chave, String senha) throws BusinessException {
        Contato user = null;
        try {
            user = businessServidor.login(chave, senha);
        } catch (BusinessException ex) {
            if (businessServidor.isErrorServidorOffline(ex)) {
                user = repositoryContato.login(chave, senha);
            } else {
                throw ex;
            }
        }
        if (user == null) {
            user = repositoryContato.login(chave, senha);
        }
        if (user == null) {
            throw new BusinessException(MSG_ERRO_FALHA_LOGIN);
        } else {
            return user;
        }
    }
}
