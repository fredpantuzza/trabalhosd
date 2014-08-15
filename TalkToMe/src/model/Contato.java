/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;

/**
 *
 * @author Thiago
 */
public class Contato implements Serializable{
    
    private String chave;
    private String senha;
    private String nick;
    private String lastIP;

    public Contato(String chave, String senha, String nick, String lastIP) {
        this.chave = chave;
        this.senha = senha;
        this.nick = nick;
        this.lastIP = lastIP;
    }

    public String getChave() {
        return this.chave;
    }

    public String getNick() {
        return this.nick;
    }

}
