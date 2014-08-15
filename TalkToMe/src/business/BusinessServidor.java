/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.ArrayList;
import java.util.List;
import model.Contato;

/**
 *
 * @author Thiago
 */
public class BusinessServidor {

    private static final String MSG_ERRO_SENHA_INVALIDA = "Chave ou senha inválida!";
    private static final String MSG_ERRO_SERVIDOR_OFFLINE = "Não foi possível contactar o servidor.";
    private static final String MSG_ERRO_CADASTRAR = "Não foi possível realizar o cadastro!";

    private static BusinessServidor instance = null;

    private BusinessServidor() {
    }

    public static BusinessServidor getInstance() {
        if (instance == null) {
            instance = new BusinessServidor();
        }
        return instance;
    }

    /**
     * Retorna a lista de contatos do usuário mantida no servidor.
     *
     * @param user
     * @return null, caso o servidor não puder ser sentactado.
     */
    public List<Contato> getListaContatosServidor(Contato user) {
        //TODO: implementar!
        return new ArrayList<Contato>();
    }

    public Contato login(String chave, String senha) throws BusinessException {
        //throw new BusinessException(MSG_ERRO_SENHA_INVALIDA);
        throw new BusinessException(MSG_ERRO_SERVIDOR_OFFLINE);
    }

    /**
     * Verifica se a exceção foi lançada por que o servidor não pode ser
     * contactado.
     *
     * @param ex
     * @return
     */
    public boolean isErrorServidorOffline(BusinessException ex) {
        return ex.getMessage().equals(MSG_ERRO_SERVIDOR_OFFLINE);
    }

    public Contato cadastrar(String nick, String senha) throws BusinessException {
        //TODO: Isto é um teste. A verdadeira verificação deve ser implementada!
        if ("".equals("")) {
            return new Contato("000110045", senha, nick, null);
        }
        throw new BusinessException(MSG_ERRO_CADASTRAR);
    }

    /**
     * Mantém a lista de contatos do contato no servidor. A lista do contato é
     * sobreposta. Dessa forma, o lista no servidor é totalmente apagada e
     * depois a nova lista é sobreposta.
     *
     * @param listaContatos Nova lista de contatos do usuário.
     * @param user Contato que representa o usuário.
     * @return
     */
    public boolean manterListaContatos(List<Contato> listaContatos, Contato user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
