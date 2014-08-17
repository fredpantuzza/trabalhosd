/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import business.converter.UserDTOToContatoConverter;
import common.ActionResult;
import common.ObjectSerialization;
import dto.ReturnDTO;
import dto.UserDTO;
import dto.UserListReturnDTO;
import dto.UserReturnDTO;
import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import model.Contato;

/**
 *
 * @author Thiago
 */
public class BusinessServidor {

    private static final String SERVER_BASE_URL = "http://localhost:8084/server";
    private static final String SERVER_GET_USER_CONTACTS_ACTION = "/GetUserContactsByIdAction";
    private static final String SERVER_INSERT_USER_ACTION = "/InsertUserAction";
    private static final String SERVER_LOGIN_ACTION = "/LoginAction";
    private static final String SERVER_GET_USER_ACTION = "/GetUserByIdAction";
    private static final String SERVER_REPLACE_USER_CONTACTS_ACTION = "/ReplaceUserContactsAction";
    private static final String SERVER_UPDATE_USER_IP_ACTION = "/UpdateUserIp";
    
    private static final String MSG_ERRO_SENHA_INVALIDA = "Chave ou senha inválida!";
    private static final String MSG_ERRO_SERVIDOR_OFFLINE = "Não foi possível contactar o servidor.";
    private static final String MSG_ERRO_CADASTRAR = "Não foi possível realizar o cadastro!";
    private static final String MSG_ERRO_CONTATO_NAO_ENCONTRADO = "Não foi encontrado nenhum contato com esta chave.";

    private static BusinessServidor instance = null;

    private BusinessServidor() {
    }

    public static BusinessServidor getInstance() {
        if (instance == null) {
            instance = new BusinessServidor();
        }
        return instance;
    }

    /**
     * Retorna a lista de contatos do usuário mantida no servidor.
     *
     * @param user
     * @return null, caso o servidor não puder ser sentactado.
     */
    public List<Contato> getListaContatosServidor(Contato user) {
        String parameters = "id=" + user.getChave();
        try {
            String response = this.sendPost(BusinessServidor.SERVER_BASE_URL, BusinessServidor.SERVER_GET_USER_CONTACTS_ACTION, parameters);
            UserListReturnDTO returnDTO = (UserListReturnDTO) ObjectSerialization.fromString(response);
            if (returnDTO.getResult().name().equals(ActionResult.SUCCESS.name())) {
                List<Contato> contacts = new ArrayList<>();
                if (returnDTO.getUserDTOs() != null) {
                    UserDTOToContatoConverter converter = new UserDTOToContatoConverter();
                    for (UserDTO userDTO : returnDTO.getUserDTOs()) {
                        contacts.add(converter.convert(userDTO));
                    }
                }
                return contacts;
            } else {
                throw new Exception(returnDTO.getMessage());
            }
        } catch (Exception ex) {
            return null;
        }
    }

    public Contato login(String chave, String senha) throws BusinessException {
        String parameters = "id=" + chave + "&pass=" + senha;
        try {
            String response = this.sendPost(BusinessServidor.SERVER_BASE_URL, BusinessServidor.SERVER_LOGIN_ACTION, parameters);
            UserReturnDTO returnDTO = (UserReturnDTO) ObjectSerialization.fromString(response);
            if (returnDTO.getResult().name().equals(ActionResult.SUCCESS.name())) {
                return new UserDTOToContatoConverter().convert(returnDTO.getUserDTO());
            } else {
                throw new Exception(returnDTO.getMessage());
            }
        } catch (Exception ex) {
            throw new BusinessException(MSG_ERRO_SENHA_INVALIDA);
        }
    }

    /**
     * Verifica se a exceção foi lançada por que o servidor não pode ser
     * contactado.
     *
     * @param ex
     * @return
     */
    public boolean isErrorServidorOffline(BusinessException ex) {
        return ex.getMessage().equals(MSG_ERRO_SERVIDOR_OFFLINE);
    }

    public Contato cadastrar(String nick, String senha) throws BusinessException {
        String parameters = "nick=" + nick + "&pass=" + senha;
        try {
            String response = this.sendPost(BusinessServidor.SERVER_BASE_URL, BusinessServidor.SERVER_INSERT_USER_ACTION, parameters);
            UserReturnDTO returnDTO = (UserReturnDTO) ObjectSerialization.fromString(response);
            if (returnDTO.getResult().name().equals(ActionResult.SUCCESS.name())) {
                return new UserDTOToContatoConverter().convert(returnDTO.getUserDTO());
            } else {
                throw new Exception(returnDTO.getMessage());
            }
        } catch (Exception ex) {
            throw new BusinessException(MSG_ERRO_CADASTRAR);
        }
    }

    /**
     * Mantém a lista de contatos do contato no servidor. A lista do contato é
     * sobreposta. Dessa forma, o lista no servidor é totalmente apagada e
     * depois a nova lista é sobreposta.
     *
     * @param listaContatos Nova lista de contatos do usuário.
     * @param user Contato que representa o usuário.
     * @return Resultado da operação.
     */
    public boolean manterListaContatos(List<Contato> listaContatos, Contato user) {
        List<String> contactsIds = new ArrayList<>();
        for (Contato contato : listaContatos) {
            contactsIds.add(contato.getChave());
        }
        String contactsList = ObjectSerialization.toString((Serializable) contactsIds);
        String parameters = "id=" + user.getChave() + "&contacts=" + contactsList;
        
        try {
            String response = this.sendPost(BusinessServidor.SERVER_BASE_URL, BusinessServidor.SERVER_REPLACE_USER_CONTACTS_ACTION, parameters);
            ReturnDTO returnDTO = (ReturnDTO) ObjectSerialization.fromString(response);
            if (returnDTO.getResult().name().equals(ActionResult.SUCCESS.name())) {
                return true;
            } else {
                throw new Exception(returnDTO.getMessage());
            }
        } catch (Exception ex) {
            return false;
        }
    }

    public Contato pesquisarContato(String chave) throws BusinessException {
        String parameters = "id=" + chave;
        try {
            String response = this.sendPost(BusinessServidor.SERVER_BASE_URL, BusinessServidor.SERVER_GET_USER_ACTION, parameters);
            UserReturnDTO returnDTO = (UserReturnDTO) ObjectSerialization.fromString(response);
            if (returnDTO.getResult().name().equals(ActionResult.SUCCESS.name())) {
                return new UserDTOToContatoConverter().convert(returnDTO.getUserDTO());
            } else {
                throw new Exception(returnDTO.getMessage());
            }
        } catch (Exception ex) {
            throw new BusinessException(MSG_ERRO_CONTATO_NAO_ENCONTRADO);
        }
    }
    
    public void enviarAck(Contato user) throws BusinessException {
        String parameters = "id=" + user.getChave();
        try {
            String response = this.sendPost(BusinessServidor.SERVER_BASE_URL, BusinessServidor.SERVER_UPDATE_USER_IP_ACTION, parameters);
            UserReturnDTO returnDTO = (UserReturnDTO) ObjectSerialization.fromString(response);
            if (returnDTO.getResult().name().equals(ActionResult.SUCCESS.name())) {
                user.setLastIP(returnDTO.getUserDTO().getLastIp());
            } else {
                throw new Exception(returnDTO.getMessage());
            }
        } catch (Exception ex) {
            throw new BusinessException(MSG_ERRO_CADASTRAR);
        }
    }
    
    /**
     * Envia requisição HTTP Post.
     * @param url URL do servidor.
     * @param servlet Serviço (servlet).
     * @param parameters Parâmetros da requisição.
     * @return Resposta da chamada.
     * @throws Exception Caso ocorra algum erro.
     */
    private String sendPost(String url, String servlet, String parameters) throws Exception {
        URL obj = new URL(url + servlet);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();

        //add reuqest header
        con.setRequestMethod("POST");

        // Send post request
        con.setDoOutput(true);
        DataOutputStream wr = new DataOutputStream(con.getOutputStream());
        wr.writeBytes(parameters);
        wr.flush();
        wr.close();

        int responseCode = con.getResponseCode();
        System.out.println("\nSending 'POST' request to URL : " + url);
        System.out.println("Post parameters : " + parameters);
        System.out.println("Response Code : " + responseCode);

        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();

        return response.toString();
    }

}
