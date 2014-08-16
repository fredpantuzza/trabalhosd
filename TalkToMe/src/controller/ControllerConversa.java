/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.BusinessConversa;
import business.BusinessException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
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
    private int currentID = 0;

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

    public void enviarMensagem(String mensagem) {
        proximoMsgID();
        Mensagem msg = new Mensagem(this.conexao.getUser(), mensagem, currentID, new Date());
        this.listaMensagens.add(msg);
        this.sEscrita.enviarMensagem(msg);
    }

    private void proximoMsgID() {
        this.currentID++;
    }

    public void onMensagemRecebida(Mensagem msg) {
        this.listaMensagens.add(msg);
        this.view.atualizarConversa(this.listaMensagens);
    }

    private class ServicoLeitura {

        private final Thread t;

        public ServicoLeitura() throws IOException {
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

        private final Thread t;

        private final Object lockEscrita = new Object();

        private List<Mensagem> listaMensagens = new ArrayList<>();

        public ServicoEscrita() throws IOException {
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
