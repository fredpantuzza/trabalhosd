/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author Thiago
 */
public class Mensagem implements Serializable {

    private Contato remetente;
    private String mensagem;
    private int id;
    private Date time;

    public Mensagem(Contato remetente, String mensagem, int id, Date time) {
        this.remetente = remetente;
        this.mensagem = mensagem;
        this.id = id;
        this.time = time;
    }

    public Contato getRemetente() {
        return this.remetente;
    }

    public String getMensagem() {
        return this.mensagem;
    }

    public int getId() {
        return this.id;
    }

    public Date getTime() {
        return this.time;
    }
}
