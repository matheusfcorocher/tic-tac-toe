package jogo.cliente;

import java.awt.Color;
import java.io.IOException;
import javax.swing.JOptionPane;
import jogo.cliente.JogoVelhaClient;
import jogo.lib.JogoVelhaServerMessage;

public class JogoVelhaClientView extends javax.swing.JFrame {

    public JogoVelhaClientView() {
        initComponents();
    }

    private void colorizeSquare(Integer q, Integer p) {
        Color color = new Color(204, 204, 204);
        if (p == 1) {
            color = Color.GREEN;
        } else if (p == 2) {
            color = Color.RED;
        }
        
        PlayerColorTile.setBackground(color);
        
        switch (q) {
            case 1:
                Q1.setBackground(color);
                break;
            case 2:
                Q2.setBackground(color);
                break;
            case 3:
                Q3.setBackground(color);
                break;
            case 4:
                Q4.setBackground(color);
                break;
            case 5:
                Q5.setBackground(color);
                break;
            case 6:
                Q6.setBackground(color);
                break;
            case 7:
                Q7.setBackground(color);
                break;
            case 8:
                Q8.setBackground(color);
                break;
            case 9:
                Q9.setBackground(color);
                break;
        }
    }

    public void resetSquares() {
        Color color = new Color(204, 204, 204);
        PlayerColorTile.setBackground(color);
        Q1.setBackground(color);
        Q2.setBackground(color);
        Q3.setBackground(color);
        Q4.setBackground(color);
        Q5.setBackground(color);
        Q6.setBackground(color);
        Q7.setBackground(color);
        Q8.setBackground(color);
        Q9.setBackground(color);
    }
    
    public void updateView(JogoVelhaServerMessage response) {
        this.colorizeSquare(1, response.getQ1());
        this.colorizeSquare(2, response.getQ2());
        this.colorizeSquare(3, response.getQ3());
        this.colorizeSquare(4, response.getQ4());
        this.colorizeSquare(5, response.getQ5());
        this.colorizeSquare(6, response.getQ6());
        this.colorizeSquare(7, response.getQ7());
        this.colorizeSquare(8, response.getQ8());
        this.colorizeSquare(9, response.getQ9());
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        EIp = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        EPort = new javax.swing.JTextField();
        BConnect = new javax.swing.JButton();
        BDisconnect = new javax.swing.JButton();
        Q3 = new javax.swing.JPanel();
        Q1 = new javax.swing.JPanel();
        Q2 = new javax.swing.JPanel();
        Q4 = new javax.swing.JPanel();
        Q5 = new javax.swing.JPanel();
        Q6 = new javax.swing.JPanel();
        Q7 = new javax.swing.JPanel();
        Q8 = new javax.swing.JPanel();
        PlayerColorTile = new javax.swing.JPanel();
        Q9 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        EIp.setText("localhost");

        jLabel1.setText("Server:");

        jLabel2.setText("Porta:");

        EPort.setText("6789");

        BConnect.setText("Conectar");
        BConnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BConnectActionPerformed(evt);
            }
        });

        BDisconnect.setText("Desconectar");
        BDisconnect.setEnabled(false);
        BDisconnect.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BDisconnectActionPerformed(evt);
            }
        });

        Q3.setBackground(new java.awt.Color(204, 204, 204));
        Q3.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Q3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Q3MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Q3Layout = new javax.swing.GroupLayout(Q3);
        Q3.setLayout(Q3Layout);
        Q3Layout.setHorizontalGroup(
            Q3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );
        Q3Layout.setVerticalGroup(
            Q3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );

        Q1.setBackground(new java.awt.Color(204, 204, 204));
        Q1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Q1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Q1MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Q1Layout = new javax.swing.GroupLayout(Q1);
        Q1.setLayout(Q1Layout);
        Q1Layout.setHorizontalGroup(
            Q1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );
        Q1Layout.setVerticalGroup(
            Q1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );

        Q2.setBackground(new java.awt.Color(204, 204, 204));
        Q2.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Q2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Q2MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Q2Layout = new javax.swing.GroupLayout(Q2);
        Q2.setLayout(Q2Layout);
        Q2Layout.setHorizontalGroup(
            Q2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );
        Q2Layout.setVerticalGroup(
            Q2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );

        Q4.setBackground(new java.awt.Color(204, 204, 204));
        Q4.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Q4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Q4MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Q4Layout = new javax.swing.GroupLayout(Q4);
        Q4.setLayout(Q4Layout);
        Q4Layout.setHorizontalGroup(
            Q4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );
        Q4Layout.setVerticalGroup(
            Q4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );

        Q5.setBackground(new java.awt.Color(204, 204, 204));
        Q5.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Q5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Q5MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Q5Layout = new javax.swing.GroupLayout(Q5);
        Q5.setLayout(Q5Layout);
        Q5Layout.setHorizontalGroup(
            Q5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );
        Q5Layout.setVerticalGroup(
            Q5Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );

        Q6.setBackground(new java.awt.Color(204, 204, 204));
        Q6.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Q6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Q6MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Q6Layout = new javax.swing.GroupLayout(Q6);
        Q6.setLayout(Q6Layout);
        Q6Layout.setHorizontalGroup(
            Q6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );
        Q6Layout.setVerticalGroup(
            Q6Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );

        Q7.setBackground(new java.awt.Color(204, 204, 204));
        Q7.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Q7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Q7MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Q7Layout = new javax.swing.GroupLayout(Q7);
        Q7.setLayout(Q7Layout);
        Q7Layout.setHorizontalGroup(
            Q7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );
        Q7Layout.setVerticalGroup(
            Q7Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );

        Q8.setBackground(new java.awt.Color(204, 204, 204));
        Q8.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Q8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Q8MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Q8Layout = new javax.swing.GroupLayout(Q8);
        Q8.setLayout(Q8Layout);
        Q8Layout.setHorizontalGroup(
            Q8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );
        Q8Layout.setVerticalGroup(
            Q8Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );

        PlayerColorTile.setBackground(new java.awt.Color(204, 204, 204));

        javax.swing.GroupLayout PlayerColorTileLayout = new javax.swing.GroupLayout(PlayerColorTile);
        PlayerColorTile.setLayout(PlayerColorTileLayout);
        PlayerColorTileLayout.setHorizontalGroup(
            PlayerColorTileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 24, Short.MAX_VALUE)
        );
        PlayerColorTileLayout.setVerticalGroup(
            PlayerColorTileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 0, Short.MAX_VALUE)
        );

        Q9.setBackground(new java.awt.Color(204, 204, 204));
        Q9.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        Q9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                Q9MouseClicked(evt);
            }
        });

        javax.swing.GroupLayout Q9Layout = new javax.swing.GroupLayout(Q9);
        Q9.setLayout(Q9Layout);
        Q9Layout.setHorizontalGroup(
            Q9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );
        Q9Layout.setVerticalGroup(
            Q9Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 116, Short.MAX_VALUE)
        );

        jLabel3.setText("Cor do Jogador");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(EIp, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(EPort, javax.swing.GroupLayout.PREFERRED_SIZE, 114, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 74, Short.MAX_VALUE)
                        .addComponent(BConnect))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Q4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Q5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Q6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Q1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Q2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Q3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(Q7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Q8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(Q9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(PlayerColorTile, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabel3)))))
                .addGap(16, 16, 16)
                .addComponent(BDisconnect)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(14, 14, 14)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.CENTER)
                    .addComponent(jLabel2)
                    .addComponent(EPort, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(BConnect)
                    .addComponent(BDisconnect)
                    .addComponent(EIp, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addGap(12, 12, 12)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(PlayerColorTile, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, 25, Short.MAX_VALUE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Q3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Q2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Q1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Q6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Q5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Q4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(Q9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Q8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(Q7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BConnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BConnectActionPerformed
        try {
            String server = EIp.getText();
            int porta = Integer.parseInt(EPort.getText());
            tcpClient = new JogoVelhaClient(server, porta, this);
            tcpClient.start();
            BConnect.setEnabled(false);
            BDisconnect.setEnabled(true);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    e.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BConnectActionPerformed

    private void BDisconnectActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BDisconnectActionPerformed
        try {
            tcpClient.close();
            BConnect.setEnabled(true);
            BDisconnect.setEnabled(false);
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage(), "ERRO", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_BDisconnectActionPerformed

    private void Q1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Q1MouseClicked
//        if (Q1.getBackground() == new Color(204, 204, 204))
        tcpClient.handler.sendMessage(1);
    }//GEN-LAST:event_Q1MouseClicked

    private void Q7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Q7MouseClicked
//        if (Q7.getBackground() == new Color(204, 204, 204))
        tcpClient.handler.sendMessage(7);
    }//GEN-LAST:event_Q7MouseClicked

    private void Q8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Q8MouseClicked
//        if (Q8.getBackground() == new Color(204, 204, 204))
        tcpClient.handler.sendMessage(8);
    }//GEN-LAST:event_Q8MouseClicked

    private void Q9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Q9MouseClicked
//        if (Q9.getBackground() == new Color(204, 204, 204))
        tcpClient.handler.sendMessage(9);
    }//GEN-LAST:event_Q9MouseClicked

    private void Q6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Q6MouseClicked
//        if (Q6.getBackground() == new Color(204, 204, 204))
        tcpClient.handler.sendMessage(6);
    }//GEN-LAST:event_Q6MouseClicked

    private void Q5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Q5MouseClicked
//        if (Q5.getBackground() == new Color(204, 204, 204))
        tcpClient.handler.sendMessage(5);
    }//GEN-LAST:event_Q5MouseClicked

    private void Q4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Q4MouseClicked
//        if (Q4.getBackground() == new Color(204, 204, 204))
        tcpClient.handler.sendMessage(4);
    }//GEN-LAST:event_Q4MouseClicked

    private void Q3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Q3MouseClicked
//        if (Q3.getBackground() == new Color(204, 204, 204))
        tcpClient.handler.sendMessage(3);
    }//GEN-LAST:event_Q3MouseClicked

    private void Q2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_Q2MouseClicked
//        if (Q2.getBackground() == new Color(204, 204, 204))
        tcpClient.handler.sendMessage(2);
    }//GEN-LAST:event_Q2MouseClicked

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> {
            new JogoVelhaClientView().setVisible(true);
        });
    }
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BConnect;
    private javax.swing.JButton BDisconnect;
    private javax.swing.JTextField EIp;
    private javax.swing.JTextField EPort;
    private javax.swing.JPanel PlayerColorTile;
    private javax.swing.JPanel Q1;
    private javax.swing.JPanel Q2;
    private javax.swing.JPanel Q3;
    private javax.swing.JPanel Q4;
    private javax.swing.JPanel Q5;
    private javax.swing.JPanel Q6;
    private javax.swing.JPanel Q7;
    private javax.swing.JPanel Q8;
    private javax.swing.JPanel Q9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    // End of variables declaration//GEN-END:variables
    private JogoVelhaClient tcpClient;
}
