/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package repository;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Contato;

/**
 *
 * @author Thiago
 */
public class RepositoryContato {

    private static RepositoryContato instance = null;

    private RepositoryContato() {
    }

    public static RepositoryContato getInstance() {
        if (instance == null) {
            instance = new RepositoryContato();
        }
        return instance;
    }

    /**
     * Retorna a lista de contatos mantida localmente pelo usuário.
     *
     * @param user
     * @return
     */
    public List<Contato> getListaLocalContatos(Contato user) {
        ArrayList<Contato> listaContatos = new ArrayList<>();

        FileInputStream stream = null;
        try {
            stream = new FileInputStream(user.getChave() + ".lst");
            ObjectInputStream objStream = new ObjectInputStream(stream);
            Contato contato;
            while ((contato = (Contato) objStream.readObject()) != null) {
                try {
                    listaContatos.add(contato);
                } catch (Exception e) {

                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RepositoryContato.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RepositoryContato.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(RepositoryContato.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            try {
                if (stream != null) {
                    stream.close();
                }
            } catch (IOException ex) {
                Logger.getLogger(RepositoryContato.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

        return listaContatos;
    }

    public void manterListaContatos(List<Contato> listaContatos, Contato user) {
        FileOutputStream stream;
        try {
            File file = new File(user.getChave() + ".lst");
            if (file.exists()) {
                file.delete();
            }

            stream = new FileOutputStream(file);
            ObjectOutputStream objStream = new ObjectOutputStream(stream);
            for (Contato contato : listaContatos) {
                try {
                    objStream.writeObject(contato);
                } catch (Exception e) {

                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(RepositoryContato.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(RepositoryContato.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Tenta fazer login local para o usuário. Esta tentativa é efetuada quando
     * o servidor não puder ser contactado.
     *
     * @param chave
     * @param senha
     * @return null, se o login não puder ser efetuado.
     */
    public Contato login(String chave, String senha) {
        return null;
    }

}
