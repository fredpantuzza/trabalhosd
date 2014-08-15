/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.List;
import model.Conexao;
import model.Contato;
import repository.RepositoryContato;

/**
 *
 * @author Thiago
 */
public class BusinessContatos {

    private static final String MSG_ERRO_CONTATO_JA_EXISTE = "O contato já existe em sua lista de contatos.";
    private static BusinessContatos instance = null;

    public static BusinessContatos getInstance() {
        if (instance == null) {
            instance = new BusinessContatos();
        }
        return instance;
    }

    private final BusinessServidor businessServidor;
    private final BusinessConexao businessConexao;
    private final RepositoryContato repositoryContato;

    private BusinessContatos() {
        businessServidor = BusinessServidor.getInstance();
        businessConexao = BusinessConexao.getInstance();
        repositoryContato = RepositoryContato.getInstance();
    }

    public Conexao conectarComContato(Contato user, Contato contato) throws BusinessException {
        return businessConexao.getConexaoContato(user, contato);
    }

    public boolean inicializarListaContatos(List<Contato> listaContatos, Contato user) {
        List<Contato> listaContatosLocal = this.getListaContatosLocal();
        List<Contato> listaContatosServidor = this.getListaContatosServidor(user);
        List<Contato> listaMerged = this.mergeListaContato(listaContatosLocal, listaContatosServidor);

        listaContatos.clear();
        listaContatos.addAll(listaMerged);

        listaContatosLocal.clear();
        listaMerged.clear();

        if (listaContatosServidor == null) {
            return false;
        } else {
            listaContatosServidor.clear();
            return true;
        }
    }

    public boolean manterListaContatos(List<Contato> listaContatos) {
        this.manterListaContatosLocal(listaContatos);
        return (this.manterListaContatosServidor(listaContatos));
    }

    private List<Contato> getListaContatosLocal() {
        return repositoryContato.getListaLocalContatos();
    }

    private List<Contato> getListaContatosServidor(Contato user) {
        return businessServidor.getListaContatosServidor(user);
    }

    /**
     * Implementa a regra de mesclar a lista local com a lista do servidor.
     * Tanto a lista local quanto a lista do servidor podem estar
     * desatualizadas. Se o servidor ficou muito tempo offline, e o usuário fez
     * alterações na lista, a lista no servidor pode estar desatualizada. Se o
     * usuário está em um computador diferente do que usou na última vez que
     * usou o programa, a lista local pode estar desatualizada em relação ao
     * servidor. Se o servidor estiver offline, a lista do servidor será nula.
     * As regras devem levar em considerações todos estas situações.
     *
     * @param listaContatosLocal
     * @param listaContatosServidor
     * @return
     */
    private List<Contato> mergeListaContato(List<Contato> listaContatosLocal, List<Contato> listaContatosServidor) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private void manterListaContatosLocal(List<Contato> listaContatos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    private boolean manterListaContatosServidor(List<Contato> listaContatos) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    public void validarContatoAdicionado(List<Contato> listaContatos, Contato contato) throws BusinessException {
        if (listaContatos.contains(contato)) {
            throw new BusinessException(MSG_ERRO_CONTATO_JA_EXISTE);
        }
    }

}
