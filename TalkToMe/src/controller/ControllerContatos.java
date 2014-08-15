/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.BusinessContatos;
import business.BusinessException;
import java.awt.EventQueue;
import java.util.List;
import model.Conexao;
import model.Contato;
import view.ViewContatos;
import view.viewAdicionarContato;
import view.viewConversa;

/**
 *
 * @author Thiago
 */
public class ControllerContatos {

    private final BusinessContatos business;
    private final ViewContatos view;
    private final Contato user;

    public ControllerContatos(ViewContatos viewContatos) {
        this.view = viewContatos;
        this.user = this.view.getContatoUser();
        this.business = BusinessContatos.getInstance();
    }

    /**
     * Carrega os contatos gravados no computador.
     */
    public void inicializarListaContatos(Contato user) {
        this.view.setSincronizacao(this.business.inicializarListaContatos(this.view.getListaContatos(), user));
    }

    public void manterListaContatos() {
        this.view.setSincronizacao(this.business.manterListaContatos(this.view.getListaContatos()));
    }

    public void adicionarContato() {
        this.view.setEnabled(false);

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new viewAdicionarContato(view).setVisible(true);
            }
        });
    }

    public void conversar() throws BusinessException {
        Contato contato = this.view.getContatoSelecionado();

        final Conexao conexao = this.business.conectarComContato(user, contato);

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new viewConversa(conexao).setVisible(true);
            }
        });
    }

    public void onContatoAdicionado(Contato contato) throws BusinessException {
        this.business.validarContatoAdicionado(this.view.getListaContatos(), contato);
    }

}
