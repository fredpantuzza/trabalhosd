/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.BusinessLogin;
import java.awt.EventQueue;
import javax.swing.JOptionPane;
import model.Contato;
import view.ViewContatos;
import view.ViewLogin;

/**
 *
 * @author Thiago
 */
public class ControllerLogin {

    private static final String MSG_ERRO_SENHA_INVALIDA = "Chave ou senha inválida!";
    private final BusinessLogin businessLogin;

    private final ViewLogin view;

    public ControllerLogin(ViewLogin view) {
        this.view = view;
        this.businessLogin = BusinessLogin.getInstance();
    }

    public void conectar() {
        String chave = view.getChave();
        String senha = view.getSenha();

        final Contato contato = businessLogin.conectar(chave, senha);

        if (contato == null) {
            JOptionPane.showMessageDialog(view, MSG_ERRO_SENHA_INVALIDA);
        } else {
            view.dispose();

            EventQueue.invokeLater(new Runnable() {
                @Override
                public void run() {
                    new ViewContatos(contato).setVisible(true);
                }
            });
        }
    }
}
