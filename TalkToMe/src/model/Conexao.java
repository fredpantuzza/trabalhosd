/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

/**
 *
 * @author Thiago
 */
public class Conexao {

    public static final int DEFAULT_SERVER_PORT = 8089;
    
    private Contato user;
    private Contato destino;
    private Socket clientSocket;

    public Conexao(Contato user, Contato destino, String clientIp) throws UnknownHostException, IOException {
        this.user = user;
        this.destino = destino;
        this.clientSocket = new Socket(clientIp, Conexao.DEFAULT_SERVER_PORT);
    }

    public Conexao(Contato user, Socket clientSocket) {
        this.user = user;
        this.clientSocket = clientSocket;
    }

    public Socket getClientSocket() {
        return clientSocket;
    }

    public Contato getUser() {
        return user;
    }

    public void setUser(Contato user) {
        this.user = user;
    }

    public Contato getDestino() {
        return destino;
    }

    public void setDestino(Contato destino) {
        this.destino = destino;
    }

}
