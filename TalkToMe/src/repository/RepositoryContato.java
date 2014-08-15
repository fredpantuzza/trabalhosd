/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.util.ArrayList;
import java.util.List;
import model.Contato;

/**
 *
 * @author Thiago
 */
public class RepositoryContato {

    private static RepositoryContato instance = null;

    private RepositoryContato() {
    }

    public static RepositoryContato getInstance() {
        if (instance == null) {
            instance = new RepositoryContato();
        }
        return instance;
    }

    /**
     * Retorna a lista de contatos mantida localmente pelo usuário.
     *
     * @return
     */
    public List<Contato> getListaLocalContatos() {
        List<Contato> lista = new ArrayList<>();
        lista.add(new Contato("0000001", "123456", "Kuem", null));
        lista.add(new Contato("0000002", "malu", "Malu H-tinha", null));
        lista.add(new Contato("0000003", "meu nome eh eneias", "Fred", null));
        return lista;
    }

    /**
     * Tenta fazer login local para o usuário. Esta tentativa é efetuada quando
     * o servidor não puder ser contactado.
     *
     * @param chave
     * @param senha
     * @return null, se o login não puder ser efetuado.
     */
    public Contato login(String chave, String senha) {
        return null;
    }

    public void manterListaContatos(List<Contato> listaContatos, Contato user) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
