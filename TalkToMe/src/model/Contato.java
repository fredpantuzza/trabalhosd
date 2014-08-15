/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Objects;

/**
 *
 * @author Thiago
 */
public class Contato implements Serializable {

    private final String chave;
    private final String senha;
    private final String nick;
    private final String lastIP;

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

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Contato) {
            Contato contato = (Contato) obj;
            return (this.chave == null ? contato.chave == null : this.chave.equals(contato.chave));
        }
        return false;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 73 * hash + Objects.hashCode(this.chave);
        return hash;
    }

}
