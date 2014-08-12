/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.BusinessException;
import business.BusinessLogin;
import java.awt.EventQueue;
import model.Contato;
import view.ViewCadastro;
import view.ViewContatos;
import view.ViewLogin;

/**
 *
 * @author Thiago
 */
public class ControllerLogin {

    private final BusinessLogin businessLogin;

    private final ViewLogin view;

    public ControllerLogin(ViewLogin view) {
        this.view = view;
        this.businessLogin = BusinessLogin.getInstance();
    }

    public void conectar() throws BusinessException {
        String chave = this.view.getChave();
        String senha = this.view.getSenha();
        final Contato contato = this.businessLogin.conectar(chave, senha);

        this.view.onConectadoComSucesso();
        
        this.view.dispose();
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ViewContatos(contato).setVisible(true);
            }
        });
    }

    public void cadastrar() {
        this.view.setVisible(false);

        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ViewCadastro(view).setVisible(true);
            }
        });
    }
}
