/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import model.Contato;

/**
 *
 * @author Thiago
 */
public class BusinessContatos {

    private static BusinessContatos instance = null;

    public static BusinessContatos getInstance() {
        if (instance == null) {
            instance = new BusinessContatos();
        }
        return instance;
    }

    private BusinessContatos() {
    }

    public Collection<? extends Contato> getListaContatos() {
        List<Contato> lista = new ArrayList<>();
        lista.add(new Contato("0000001", "123456", "Kuem", null));
        lista.add(new Contato("0000002", "malu", "Malu H-tinha", null));
        lista.add(new Contato("0000003", "meu nome eh eneias", "Fred", null));
        return lista;
    }

}
