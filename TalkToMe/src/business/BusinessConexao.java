/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Conexao;
import model.Contato;
import model.Mensagem;

/**
 *
 * @author Thiago
 */
public class BusinessConexao {

    private static final String MSG_ERRO_CONTATO_OFFLINE = "Não foi possível conectar com o contato.";
    private static final String MSG_ERRO_DESCONHECIDO    = "Ocorreu um erro inesperado ao conectar com o usuário.";
    
    private static final String MSG_WELCOME = "hello";
    
    private ServerSocket serverSocket;
    
    private static BusinessConexao instance = null;

    private BusinessConexao() throws IOException {
        this.serverSocket = new ServerSocket(Conexao.DEFAULT_SERVER_PORT);
    }

    public static BusinessConexao getInstance() throws IOException {
        if (instance == null) {
            instance = new BusinessConexao();
        }
        return instance;
    }

    public Conexao getConexaoContato(Contato user, Contato contato) throws BusinessException {
        try {
            Conexao connection = new Conexao(user, contato, contato.getLastIP());
            
            try {
                // envia mensagem de início
                this.enviarMensagem(connection, new Mensagem(user, MSG_WELCOME, 0, new Date()));
                // espera resposta
                Mensagem response = this.receberMensagem(connection);
                if (response.getId() == 0) {
                    return connection;
                } else {
                    throw new Exception();
                }
            } catch(Exception ex) {
                this.finalizarConexao(connection);
                throw new BusinessException(MSG_ERRO_DESCONHECIDO);
            }
        } catch (UnknownHostException ex) {
            Logger.getLogger(BusinessConexao.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessException(MSG_ERRO_CONTATO_OFFLINE);
        } catch (IOException ex) {
            Logger.getLogger(BusinessConexao.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessException(MSG_ERRO_DESCONHECIDO);
        }
    }

    void finalizarConexao(Conexao conexao) {
        if (!conexao.getClientSocket().isClosed()) {
            try {
                conexao.getClientSocket().close();
            } catch (IOException ex) {
                Logger.getLogger(BusinessConexao.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public void enviarMensagem(Conexao conexao, Mensagem msg) throws BusinessException {
        try {
            ObjectOutputStream oos = new ObjectOutputStream(conexao.getClientSocket().getOutputStream());
            oos.writeObject(msg);
            oos.flush();
            oos.close();
        } catch (IOException ex) {
            Logger.getLogger(BusinessConexao.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessException(MSG_ERRO_DESCONHECIDO);
        }
    }

    public Mensagem receberMensagem(Conexao conexao) throws BusinessException {
        try {
            ObjectInputStream ois = new ObjectInputStream(conexao.getClientSocket().getInputStream());
            Object readObj = ois.readObject();
            ois.close();
            
            if (readObj instanceof Mensagem) {
                return (Mensagem) readObj;
            } else {
                throw new BusinessException(MSG_ERRO_DESCONHECIDO);
            }
        } catch (IOException ex) {
            Logger.getLogger(BusinessConexao.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessException(MSG_ERRO_DESCONHECIDO);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BusinessConexao.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessException(MSG_ERRO_DESCONHECIDO);
        }
    }

    public Conexao receberConexao(Contato user) throws BusinessException {
        try {
            Socket clientSocket = this.serverSocket.accept();
            Conexao connection = new Conexao(user, clientSocket);
            
            // espera mensagem de boas vindas
            Mensagem welcomeMessage = this.receberMensagem(connection);
            if (welcomeMessage.getMensagem().equals(MSG_WELCOME)) {
                connection.setUser(user);
                connection.setDestino(welcomeMessage.getRemetente());
                return connection;
            } else {
                throw new BusinessException(MSG_ERRO_DESCONHECIDO);
            }
        } catch (IOException ex) {
            Logger.getLogger(BusinessConexao.class.getName()).log(Level.SEVERE, null, ex);
            throw new BusinessException(MSG_ERRO_DESCONHECIDO);
        }
    }
}
