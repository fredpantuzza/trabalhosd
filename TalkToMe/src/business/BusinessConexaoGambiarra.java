/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import static business.BusinessConexao.MSG_WELCOME;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
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
public class BusinessConexaoGambiarra extends BusinessConexao {

    protected BusinessConexaoGambiarra() throws IOException {
        super();
    }

    @Override
    public Conexao receberConexao(Contato user) throws BusinessException {
        try {
            Socket clientSocket = this.serverSocket.accept();
            Conexao connection = new Conexao(user, clientSocket);

            // espera mensagem de boas vindas
            Mensagem welcomeMessage = this.receberMensagem(connection);
            if (welcomeMessage.getMensagem().equals(MSG_WELCOME)) {
                // envia resposta de início
                this.enviarMensagem(connection, new Mensagem(user, MSG_WELCOME, 0, new Date()));

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
