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
public class Contato implements Serializable, Comparable {

    private String chave;
    private String senha;
    private String nick;
    private String lastIP;

    public Contato() {
        this.chave = null;
        this.senha = null;
        this.nick = null;
        this.lastIP = null;
    }

    public Contato(String chave, String senha, String nick, String lastIP) {
        this.chave = chave;
        this.senha = senha;
        this.nick = nick;
        this.lastIP = lastIP;
    }

    public String getChave() {
        return chave;
    }

    public void setChave(String chave) {
        this.chave = chave;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    public String getLastIP() {
        return lastIP;
    }

    public void setLastIP(String lastIP) {
        this.lastIP = lastIP;
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
    
    @Override
    public int compareTo(Object o) {
         Contato a = (Contato) o;
         int compare = this.getNick().compareToIgnoreCase(a.getNick());
         return compare;
    }

}
