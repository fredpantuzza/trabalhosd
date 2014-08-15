/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import business.BusinessException;
import controller.ControllerAdicionarContato;
import javax.swing.JOptionPane;
import model.Contato;

/**
 *
 * @author Thiago
 */
public class viewAdicionarContato extends javax.swing.JFrame {

    private final ControllerAdicionarContato controller;

    /**
     * Creates new form viewAdicionarContato
     * @param viewContatos
     */
    public viewAdicionarContato(ViewContatos viewContatos) {
        this.controller = new ControllerAdicionarContato(this, viewContatos);
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollTextInfo = new javax.swing.JScrollPane();
        textInfo = new javax.swing.JTextArea();
        labelChavePesquisa = new javax.swing.JLabel();
        textChavePesquisa = new javax.swing.JTextField();
        labelChave = new javax.swing.JLabel();
        textChave = new javax.swing.JTextField();
        buttonPesquisar = new javax.swing.JButton();
        buttonConfirmar = new javax.swing.JButton();
        labelNick = new javax.swing.JLabel();
        textNick = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosed(java.awt.event.WindowEvent evt) {
                formWindowClosed(evt);
            }
        });

        textInfo.setEditable(false);
        textInfo.setColumns(20);
        textInfo.setLineWrap(true);
        textInfo.setRows(5);
        textInfo.setText("Digite a chave do contato que vc deseja adicionar e clique em pesquisar para conferir os dados deste contato. Para confirmar a operação clique em Confirmar.");
        textInfo.setWrapStyleWord(true);
        scrollTextInfo.setViewportView(textInfo);

        labelChavePesquisa.setText("Chave:");

        labelChave.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        labelChave.setForeground(new java.awt.Color(204, 0, 0));
        labelChave.setText("Chave:");

        textChave.setEnabled(false);

        buttonPesquisar.setText("Pesquisar");
        buttonPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonPesquisarActionPerformed(evt);
            }
        });

        buttonConfirmar.setText("Confirmar");
        buttonConfirmar.setEnabled(false);
        buttonConfirmar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                buttonConfirmarActionPerformed(evt);
            }
        });

        labelNick.setFont(new java.awt.Font("Tahoma", 1, 13)); // NOI18N
        labelNick.setForeground(new java.awt.Color(204, 0, 0));
        labelNick.setText("Nick:");

        textNick.setEnabled(false);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(labelNick)
                        .addGap(20, 20, 20)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textNick)
                            .addComponent(buttonConfirmar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(scrollTextInfo, javax.swing.GroupLayout.PREFERRED_SIZE, 266, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(labelChavePesquisa)
                            .addComponent(labelChave))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(textChave)
                            .addComponent(buttonPesquisar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(textChavePesquisa))))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(scrollTextInfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelChavePesquisa)
                    .addComponent(textChavePesquisa, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonPesquisar)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelChave)
                    .addComponent(textChave, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(labelNick)
                    .addComponent(textNick, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(buttonConfirmar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void buttonPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonPesquisarActionPerformed
        try {
            this.controller.pesquisar();
            this.buttonConfirmar.setEnabled(true);
        } catch (BusinessException ex) {
            this.textChave.setText("");
            this.textNick.setText("");
            this.buttonConfirmar.setEnabled(false);
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_buttonPesquisarActionPerformed

    private void buttonConfirmarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_buttonConfirmarActionPerformed
        try {
            this.controller.confirmar();
        } catch (BusinessException ex) {
            JOptionPane.showMessageDialog(this, ex.getMessage());
        }
    }//GEN-LAST:event_buttonConfirmarActionPerformed

    private void formWindowClosed(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosed
        this.controller.onWindowClosed();
    }//GEN-LAST:event_formWindowClosed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton buttonConfirmar;
    private javax.swing.JButton buttonPesquisar;
    private javax.swing.JLabel labelChave;
    private javax.swing.JLabel labelChavePesquisa;
    private javax.swing.JLabel labelNick;
    private javax.swing.JScrollPane scrollTextInfo;
    private javax.swing.JTextField textChave;
    private javax.swing.JTextField textChavePesquisa;
    private javax.swing.JTextArea textInfo;
    private javax.swing.JTextField textNick;
    // End of variables declaration//GEN-END:variables

    public String getChavePesquisa() {
        return this.textChavePesquisa.getText();
    }

    public void onConfirmado(String chave) {
        JOptionPane.showMessageDialog(this, "O contato foi adicionado com sucesso!");
    }

    public void onPesquisadoComSucesso(Contato contato) {
        this.textChave.setText(contato.getChave());
        this.textNick.setText(contato.getNick());
        JOptionPane.showMessageDialog(this, "O contato foi encontrado!");
    }
}