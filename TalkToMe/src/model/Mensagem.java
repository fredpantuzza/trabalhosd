/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author Thiago
 */
public class Mensagem {

    private Contato remetente;
    private String mensagem;
    private int id;
    private Date time;

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
