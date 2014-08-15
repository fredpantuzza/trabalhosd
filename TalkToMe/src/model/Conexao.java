/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 *
 * @author Thiago
 */
public class Conexao {

    private Contato user;
    private Contato destino;
    private Socket socket;

    public Contato getContatoUser() {
        return this.user;
    }

    public Contato getContatoDestino() {
        return this.destino;
    }

    public InputStream getInputStream() throws IOException {
        return this.socket.getInputStream();
    }

    public OutputStream getOutputStream() throws IOException {
        return this.socket.getOutputStream();
    }
}
