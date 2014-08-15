/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.BusinessConversa;
import business.BusinessException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import model.Conexao;
import model.Mensagem;
import view.viewConversa;

/**
 *
 * @author Thiago
 */
public class ControllerConversa {

    private final BusinessConversa business;

    private final viewConversa view;
    private final Conexao conexao;
    private ServicoLeitura sLeitura = null;
    private ServicoEscrita sEscrita = null;

    private final List<Mensagem> listaMensagens;

    public ControllerConversa(viewConversa view, Conexao conexao) {
        this.business = BusinessConversa.getInstance();
        this.view = view;
        this.conexao = conexao;
        this.listaMensagens = new ArrayList<>();
    }

    public boolean iniciarServicosLeituraEscrita() {
        try {
            this.sLeitura = new ServicoLeitura();
            this.sEscrita = new ServicoEscrita();
            return true;
        } catch (IOException ex) {
            return false;
        }
    }

    public void enviarMensagem(Mensagem msg) {
        this.listaMensagens.add(msg);
        this.sEscrita.enviarMensagem(msg);
    }

    public void onMensagemRecebida(Mensagem msg) {
        this.listaMensagens.add(msg);
        this.view.atualizarConversa(this.listaMensagens);
    }

    private class ServicoLeitura {

        private final InputStream stream;

        private final Thread t;

        public ServicoLeitura() throws IOException {
            this.stream = conexao.getInputStream();

            this.t = new Thread(new Runnable() {

                @Override
                public void run() {
                    executar();
                }
            });
            this.t.start();
        }

        private void executar() {
            try {
                Mensagem msg;
                while (true) {
                    msg = business.receberMensagem(conexao);
                    onMensagemRecebida(msg);
                }
            } catch (BusinessException ex) {
                //Falha na conexão ou cliente desconectado.
            }
        }

    }

    private class ServicoEscrita {

        private final OutputStream stream;

        private final Thread t;

        private final Object lockEscrita = new Object();

        private List<Mensagem> listaMensagens = new ArrayList<>();

        public ServicoEscrita() throws IOException {
            this.stream = conexao.getOutputStream();

            this.t = new Thread(new Runnable() {

                @Override
                public void run() {
                    executar();
                }
            });
            this.t.start();
        }

        private void executar() {
            try {
                while (true) {
                    Mensagem msg = null;

                    synchronized (this.lockEscrita) {
                        if (this.listaMensagens.size() > 0) {
                            msg = this.listaMensagens.remove(0);
                        }
                    }

                    if (msg != null) {
                        business.enviarMensagem(conexao, msg);
                    }

                    synchronized (this.lockEscrita) {
                        if (this.listaMensagens.isEmpty()) {
                            this.lockEscrita.wait();
                        }
                    }
                }
            } catch (InterruptedException | BusinessException ex) {
                //Conexão interrompita.
            }
        }

        private void enviarMensagem(Mensagem msg) {
            synchronized (this.lockEscrita) {
                this.listaMensagens.add(msg);
                this.lockEscrita.notify();
            }
        }

    }
}
