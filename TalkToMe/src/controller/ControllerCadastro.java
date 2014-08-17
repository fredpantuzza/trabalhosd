/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.BusinessCadastro;
import business.BusinessException;
import java.awt.EventQueue;
import model.Contato;
import view.ViewCadastro;
import view.ViewContatos;
import view.ViewLogin;

/**
 *
 * @author Thiago
 */
public class ControllerCadastro {

    private final BusinessCadastro business;

    private final ViewLogin viewLogin;
    private final ViewCadastro view;
    private Contato contato;

    public ControllerCadastro(ViewCadastro view, ViewLogin viewLogin) {
        this.view = view;
        this.viewLogin = viewLogin;
        this.contato = null;
        this.business = BusinessCadastro.getInstance();
    }

    public void confirmarCadastro() throws BusinessException {
        String nick = this.view.getNick();
        String senha = this.view.getSenha();
        String confirmarSenha = this.view.getConfirmarSenha();
        this.business.verificarSenha(senha, confirmarSenha);
        this.contato = this.business.cadastrar(nick, senha);

        this.view.onCadastradoComSucesso(this.contato.getChave());
        
        this.view.dispose();
        this.viewLogin.dispose();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ViewContatos(contato).setVisible(true);
            }
        });
    }

    public void onWindowClosed() {
        if (this.contato == null) {
            this.viewLogin.setVisible(true);
        }
    }

}
