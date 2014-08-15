/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexao;
import model.Mensagem;
import view.viewConversa;

/**
 *
 * @author Thiago
 */
public class ControllerConversa {

    private final viewConversa view;
    private final Conexao conexao;
    private ServicoLeitura sLeitura = null;
    private ServicoEscrita sEscrita = null;

    private final List<Mensagem> listaMensagens;

    public ControllerConversa(viewConversa view, Conexao conexao) {
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
        this.sEscrita.enviarMensagem(msg);
        this.listaMensagens.add(msg);
        //TODO: tratar mecanismos de mensagens...
    }

    public void onMensagemRecebida(Mensagem msg) {
        this.listaMensagens.add(msg);
        //TODO: tratar mecanismos de mensagens...

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

        }

    }

    private class ServicoEscrita {

        private final OutputStream stream;

        private final Thread t;

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

        }

        private void enviarMensagem(Mensagem msg) {
            throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
        }

    }
}
