/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import controller.ControllerConversa;
import java.util.Date;
import java.util.List;
import model.Conexao;
import model.Mensagem;

/**
 *
 * @author Thiago
 */
public class viewConversa extends javax.swing.JFrame {

    private final Conexao conexao;
    private final ControllerConversa controller;

    /**
     * Creates new form viewConversa
     *
     * @param conexao
     */
    public viewConversa(Conexao conexao) {
        this.conexao = conexao;
        initComponents();
        this.textChaveContato.setText(conexao.getContatoDestino().getChave());
        this.textNickContato.setText(conexao.getContatoDestino().getNick());
        this.controller = new ControllerConversa(this, this.conexao);
        if (!this.controller.iniciarServicosLeituraEscrita()) {
            this.cancelarInicializacaoTela();
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton2 = new javax.swing.JButton();
        labelChaveContato = new javax.swing.JLabel();
        textChaveContato = new javax.swing.JTextField();
        labelNickContato = new javax.swing.JLabel();
        textNickContato = new javax.swing.JTextField();
        scrollConversa = new javax.swing.JScrollPane();
        textConversa = new javax.swing.JTextArea();
        buttonEnviar = new javax.swing.JButton();
        scrollMensagem = new javax.swing.JScrollPane();
        textMensagem = new javax.swing.JTextArea();

        jButton2.setText("jButton2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        labelChaveContato.setText("Chave:");

        textChaveContato.setEnabled(false);

        labelNickContato.setText("Nick:");

        textNickContato.setEnabled(false);

        textConversa.setColumns(20);
        textConversa.setRows(5);
        scrollConversa.setViewportView(textConversa);

        buttonEnviar.setText("Enviar");
        buttonEnviar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonEnviarActionPerformed(evt);
            }
        });

        textMensagem.setColumns(20);
        textMensagem.setForeground(new java.awt.Color(153, 153, 153));
        textMensagem.setRows(5);
        textMensagem.setText("Escreva uma mensagem...");
        scrollMensagem.setViewportView(textMensagem);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(scrollConversa, javax.swing.GroupLayout.DEFAULT_SIZE, 532, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelChaveContato)
                            .addComponent(labelNickContato))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(textChaveContato, javax.swing.GroupLayout.DEFAULT_SIZE, 200, Short.MAX_VALUE)
                            .addComponent(textNickContato))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(buttonEnviar))
                    .addComponent(scrollMensagem))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelChaveContato)
                    .addComponent(textChaveContato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNickContato)
                    .addComponent(textNickContato, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollConversa, javax.swing.GroupLayout.DEFAULT_SIZE, 271, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(scrollMensagem, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonEnviar)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonEnviarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonEnviarActionPerformed
        this.controller.enviarMensagem(this.textMensagem.getText());
    }//GEN-LAST:event_buttonEnviarActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonEnviar;
    private javax.swing.JButton jButton2;
    private javax.swing.JLabel labelChaveContato;
    private javax.swing.JLabel labelNickContato;
    private javax.swing.JScrollPane scrollConversa;
    private javax.swing.JScrollPane scrollMensagem;
    private javax.swing.JTextField textChaveContato;
    private javax.swing.JTextArea textConversa;
    private javax.swing.JTextArea textMensagem;
    private javax.swing.JTextField textNickContato;
    // End of variables declaration//GEN-END:variables

    /**
     * Em casos de falhas na inicialização do controle desta tela, tudo será
     * cancelado.
     */
    private void cancelarInicializacaoTela() {
        this.dispose();
    }

    public void atualizarConversa(List<Mensagem> listaMensagens) {
        StringBuilder texto = new StringBuilder();
        for (Mensagem msg : listaMensagens) {
            texto.append(msg.getRemetente().getNick()).append(" disse (").append(apresentarHora(msg.getTime())).append("):\n");
            texto.append(msg.getMensagem()).append("\n\n");
        }
    }

    private String apresentarHora(Date time) {
        return time.toGMTString();
    }
}
