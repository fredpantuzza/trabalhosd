/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.BusinessAdicionarContato;
import business.BusinessException;
import model.Contato;
import view.ViewContatos;
import view.viewAdicionarContato;

/**
 *
 * @author Thiago
 */
public class ControllerAdicionarContato {

    private final BusinessAdicionarContato business;

    private final viewAdicionarContato view;
    private final ViewContatos viewContatos;
    private Contato contato;

    public ControllerAdicionarContato(viewAdicionarContato view, ViewContatos viewContatos) {
        this.view = view;
        this.viewContatos = viewContatos;
        this.contato = null;
        this.business = BusinessAdicionarContato.getInstance();
    }

    public void pesquisar() throws BusinessException {
        this.contato = null;

        String chave = this.view.getChavePesquisa();
        this.contato = this.business.pesquisar(chave);

        this.view.onPesquisadoComSucesso(this.contato);
    }

    public void confirmar() throws BusinessException {
        this.business.confirmar(contato);
        this.viewContatos.onContatoAdicionado(contato);
        
        this.view.onConfirmado(this.contato.getChave());
        this.view.dispose();
    }

    public void onWindowClosed() {
        this.viewContatos.setEnabled(true);
    }

}
