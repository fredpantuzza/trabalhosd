/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package business;

import model.Contato;

/**
 *
 * @author Thiago
 */
public class BusinessAdicionarContato {

    private static final String MSG_ERRO_CONTATO_NULL = "É preciso pesquisar um contato para confirmar a operação.";
    
    private static BusinessAdicionarContato instance = null;

    public static BusinessAdicionarContato getInstance() {
        if (instance == null) {
            instance = new BusinessAdicionarContato();
        }
        return instance;
    }

    private final BusinessServidor businessServidor;

    private BusinessAdicionarContato() {
        this.businessServidor = BusinessServidor.getInstance();
    }

    public void confirmar(Contato contato) throws BusinessException {
        if (contato == null) {
            throw new BusinessException(MSG_ERRO_CONTATO_NULL);
        }
    }

    public Contato pesquisar(String chave) throws BusinessException {
        return this.businessServidor.pesquisarContato(chave);
    }

}
