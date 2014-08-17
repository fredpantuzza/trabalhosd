/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.BusinessContatos;
import business.BusinessException;
import java.awt.EventQueue;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
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

    private final List<Conexao> listaConexoes;

    private ServicoConexao sConexao = null;
    private ServicoAck sAck = null;

    public ControllerContatos(ViewContatos viewContatos) {
        this.view = viewContatos;
        this.user = this.view.getContatoUser();
        this.listaConexoes = new ArrayList<>();
        this.business = BusinessContatos.getInstance();
    }

    public boolean iniciarServicosConexao() {
        try {
            this.sConexao = new ServicoConexao();
            this.sAck = new ServicoAck();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    /**
     * Carrega os contatos gravados no computador.
     *
     * @param user
     */
    public void inicializarListaContatos(Contato user) {
        this.view.setSincronizacao(this.business.inicializarListaContatos(this.view.getListaContatos(), user));
    }

    public void manterListaContatos() {
        this.view.setSincronizacao(this.business.manterListaContatos(this.view.getListaContatos(), this.user));
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

        this.registrarNovaConexao(conexao);

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

    private void registrarNovaConexao(Conexao conexao) {
        this.listaConexoes.add(conexao);
    }

    public void finalizarConexoes() {
        for (Conexao conexao : this.listaConexoes) {
            this.finalizarConexao(conexao);
        }
    }

    private void finalizarConexao(Conexao conexao) {
        this.business.finalizarConexao(conexao);
    }

    public void manterRegistroLogin() {
        this.business.manterRegistroLogin(this.user);
    }

    private class ServicoConexao {

        private final Thread t;

        public ServicoConexao() throws IOException {
            this.t = new Thread(new Runnable() {

                @Override
                public void run() {
                    executar();
                }
            });
            this.t.start();
        }

        private void executar() {
            while (true) {
                try {
                    final Conexao conexao = business.receberConexao(user);

                    registrarNovaConexao(conexao);

                    EventQueue.invokeLater(new Runnable() {
                        @Override
                        public void run() {
                            new viewConversa(conexao).setVisible(true);
                        }
                    });
                } catch (BusinessException ex) {
                    Logger.getLogger(ControllerContatos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    private class ServicoAck {

        private final Thread t;

        public ServicoAck() throws IOException {
            this.t = new Thread(new Runnable() {

                @Override
                public void run() {
                    executar();
                }
            });
            this.t.start();
        }

        private void executar() {
            while (true) {
                try {
                    business.enviarAck(user);
                } catch (BusinessException ex) {
                    Logger.getLogger(ControllerContatos.class.getName()).log(Level.SEVERE, null, ex);
                }
                
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(ControllerContatos.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

}
