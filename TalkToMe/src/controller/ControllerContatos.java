/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import business.BusinessContatos;
import java.util.List;
import model.Contato;
import view.ViewContatos;

/**
 *
 * @author Thiago
 */
public class ControllerContatos {
    private final BusinessContatos business;
    private final ViewContatos viewContatos;

    public ControllerContatos(ViewContatos viewContatos) {
        this.viewContatos = viewContatos;
        this.business = BusinessContatos.getInstance();
    }

    public void getListaContatos(List<Contato> listaContatos) {
        listaContatos.clear();
        listaContatos.addAll(business.getListaContatos());
    }

}
