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
public class BusinessServidorGambiarra extends BusinessServidor {

    protected BusinessServidorGambiarra() {
        super();
    }

    /**
     * Retorna a lista de contatos do usuário mantida no servidor.
     *
     * @param user
     * @return null, caso o servidor não puder ser sentactado.
     */
    @Override
    public List<Contato> getListaContatosServidor(Contato user) {
        return new ArrayList<>();
    }

    @Override
    public Contato login(String chave, String senha) throws BusinessException {
        return new Contato(chave, senha, chave, null);
    }

    @Override
    public Contato cadastrar(String nick, String senha) throws BusinessException {
        return new Contato(nick, senha, nick, null);
    }

    /**
     * Mantém a lista de contatos do contato no servidor. A lista do contato é
     * sobreposta. Dessa forma, o lista no servidor é totalmente apagada e
     * depois a nova lista é sobreposta.
     *
     * @param listaContatos Nova lista de contatos do usuário.
     * @param user Contato que representa o usuário.
     * @return Resultado da operação.
     */
    @Override
    public boolean manterListaContatos(List<Contato> listaContatos, Contato user) {
        return true;
    }

    @Override
    public Contato pesquisarContato(String chave) throws BusinessException {
        return new Contato(chave, chave, chave, chave);
    }

    @Override
    public void enviarAck(Contato user) throws BusinessException {
    }

}
