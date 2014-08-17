/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.Collections;
import java.util.Iterator;
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

    public void finalizarConexao(Conexao conexao) {
        this.businessConexao.finalizarConexao(conexao);
    }

    /**
     * Inicializa a lista de contatos. A lista é primeiro lida localmente, e
     * depois lida do servidor para fazer o merge. As regras de merge são
     * implementadas em mergeListaContato. O método retorna true se o sistema
     * conseguir se comunicar com o servidor, e false caso contrário.
     *
     * @param listaContatos
     * @param user
     * @return
     */
    public boolean inicializarListaContatos(List<Contato> listaContatos, Contato user) {
        List<Contato> listaContatosLocal = this.getListaContatosLocal(user);
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

    /**
     * Mantem as informações da lista de contatos. As informações são
     * atualizadas localmente e no servidor. O método retorna true se o sistema
     * conseguir se comunicar com o servidor, e false caso contrário.
     *
     * @param listaContatos
     * @param user
     * @return
     */
    public boolean manterListaContatos(List<Contato> listaContatos, Contato user) {
        this.manterListaContatosLocal(listaContatos, user);
        return (this.manterListaContatosServidor(listaContatos, user));
    }

    private List<Contato> getListaContatosLocal(Contato user) {
        return repositoryContato.getListaLocalContatos(user);
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
         if (listaContatosServidor == null) {
            return listaContatosLocal;
        } else {
            Iterator i = listaContatosServidor.iterator();
            List<Contato> mergedListaContatos = null;

            mergedListaContatos = listaContatosLocal;

            while (i.hasNext()) {
                Contato c = (Contato) i.next();
                if (!mergedListaContatos.contains(c)) {
                    mergedListaContatos.add(c);
                }
            }
            
            Collections.sort(mergedListaContatos);
            return mergedListaContatos;
        }
    }

    private void manterListaContatosLocal(List<Contato> listaContatos, Contato user) {
        this.repositoryContato.manterListaContatos(listaContatos, user);
    }

    private boolean manterListaContatosServidor(List<Contato> listaContatos, Contato user) {
        return this.businessServidor.manterListaContatos(listaContatos, user);
    }

    public void validarContatoAdicionado(List<Contato> listaContatos, Contato contato) throws BusinessException {
        if (listaContatos.contains(contato)) {
            throw new BusinessException(MSG_ERRO_CONTATO_JA_EXISTE);
        }
    }

    public void manterRegistroLogin(Contato user) {
        this.repositoryContato.manterRegistroLogin(user);
    }

    public Conexao receberConexao(Contato user) throws BusinessException {
        return this.businessConexao.receberConexao(user);
    }
    
    public void enviarAck(Contato user) throws BusinessException  {
        this.businessServidor.enviarAck(user);
    }

}
