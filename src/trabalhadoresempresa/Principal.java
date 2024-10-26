package trabalhadoresempresa;

import java.io.EOFException;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import javax.swing.JOptionPane;

public class Principal extends javax.swing.JFrame {
    private ArrayList trab = new ArrayList();
    private ArrayList femaleWorkers = new ArrayList();
    private AVL listaGrafo = new AVL();
    private BinaryMinHeap heap = new BinaryMinHeap(16);
    
    public Principal() {
        initComponents();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jButton1 = new javax.swing.JButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        jToolBar1 = new javax.swing.JToolBar();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea2 = new javax.swing.JTextArea();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setTitle("Projeto - ED-II");

        jButton1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jButton1.setText("<html>Mostrar os trabalhadores</html>");
        jButton1.setActionCommand("");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jScrollPane2.setBackground(new java.awt.Color(204, 204, 204));
        jScrollPane2.setBorder(null);

        jTextArea1.setEditable(false);
        jTextArea1.setBackground(new java.awt.Color(204, 204, 204));
        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Segoe UI", 0, 10)); // NOI18N
        jTextArea1.setLineWrap(true);
        jTextArea1.setRows(3);
        jTextArea1.setText("Ao clicar no botão ao lado serão carregados no ArrayList 'trab' os dados de vários objetos da classe Trabalhador, que se encontram no arquivo trabs.dat (deve estar em C:\\temp) e mostrados na área de texto.");
        jTextArea1.setWrapStyleWord(true);
        jTextArea1.setAutoscrolls(false);
        jTextArea1.setBorder(null);
        jTextArea1.setEnabled(false);
        jTextArea1.setFocusable(false);
        jScrollPane2.setViewportView(jTextArea1);

        jToolBar1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jToolBar1.setRollover(true);

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/trabalhadoresempresa/about.png"))); // NOI18N
        jButton2.setFocusable(false);
        jButton2.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton2.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton2);

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/trabalhadoresempresa/exit.png"))); // NOI18N
        jButton3.setFocusable(false);
        jButton3.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        jButton3.setVerticalTextPosition(javax.swing.SwingConstants.BOTTOM);
        jToolBar1.add(jButton3);

        jButton4.setText("Processar");
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        jButton5.setText("Crescente");
        jButton5.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton5ActionPerformed(evt);
            }
        });

        jButton6.setText("Decrescente");
        jButton6.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton6ActionPerformed(evt);
            }
        });

        jButton7.setText("Autores");

        jButton8.setText("Capacitação");

        jScrollPane1.setAutoscrolls(true);

        jTextArea2.setColumns(20);
        jTextArea2.setRows(5);
        jScrollPane1.setViewportView(jTextArea2);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jToolBar1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(layout.createSequentialGroup()
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 94, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton8)
                    .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 95, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 93, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addComponent(jButton6)))
                .addGap(73, 73, 73)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jScrollPane2)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 602, Short.MAX_VALUE))
                .addContainerGap(65, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jToolBar1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGap(36, 36, 36)
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 42, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 255, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(34, 34, 34)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(48, 48, 48)
                        .addComponent(jButton4)
                        .addGap(18, 18, 18)
                        .addComponent(jButton6)
                        .addGap(18, 18, 18)
                        .addComponent(jButton5)
                        .addGap(18, 18, 18)
                        .addComponent(jButton7)
                        .addGap(18, 18, 18)
                        .addComponent(jButton8)))
                .addContainerGap(174, Short.MAX_VALUE))
        );

        setSize(new java.awt.Dimension(898, 576));
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        carregar("C:/temp/trabs.dat", trab);
        mostraTrabalhadoresCadastrados();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        pegaTrabalhadoras();
        colocarNaAvl();
        inserirQueue();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton6ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton6ActionPerformed
        // Decrescente
        mostraInverso();
    }//GEN-LAST:event_jButton6ActionPerformed

    private void jButton5ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton5ActionPerformed
        // Crescente
        mostraNaOrdem();
    }//GEN-LAST:event_jButton5ActionPerformed

    public boolean carregar (String filename, ArrayList arr) {
            //Este método carrega no vetor 'arr' ('trab') os objetos serializados,
            //que estão gravados em filename (C:/temp/trabs.dat). Copie 
            //este arquivo na pasta C:/temp/ de seu computador.
            FileInputStream fis;
            ObjectInputStream in;       
            try {
                fis = new FileInputStream(filename);
                in = new ObjectInputStream(fis);
                arr.clear(); //esvaziamos o ArrayList
                boolean sair = false;
                do {                
                     try {
                         Object info = in.readObject(); // lê um objeto do arquivo
                         arr.add(info); // adiciona na lista o objeto lido; supondo memória suficiente
                      }
                      catch (EOFException normalEof) {                         
                         sair=true;  // EOF (end of file), é uma situação normal => acabaram os objetos
                      }            
                } while (!sair);            
                in.close();  fis.close();
            } catch(Exception e) {
                JOptionPane.showMessageDialog(null, "Erro carregando objetos... " + e.getMessage());
                return false;
            }
            return true;
    } 
 
    public void mostraTrabalhadoresCadastrados () {     
        //Mostra na tela os trabalhadores cadastrados no ArrayList 'trab'.
        String cad = "";
        for (int i=0; i < trab.size(); i++) {
          cad +=  trab.get(i).toString()+ "\n";
        }
        jTextArea2.setText(cad);
        jTextArea2.setCaretPosition(0);
    }
    
    public void mostraNaOrdem() {
        String cad = "";
        for(int i=0; i < femaleWorkers.size(); i++) {
            cad += femaleWorkers.get(i).toString() + "\n";
        }
        jTextArea2.setText(cad);
        jTextArea2.setCaretPosition(0);
    }
    
    public void mostraInverso() {
        String cad = "";
        for(int i=femaleWorkers.size()-1; i >= 0; i--) {
            cad += femaleWorkers.get(i).toString() + "\n";
        }
        jTextArea2.setText(cad);
        jTextArea2.setCaretPosition(0);
    }
    
    private void pegaTrabalhadoras() {
        String cad = "";
        for(int i=0; i < trab.size(); i++) {
            Trabalhador temp = (Trabalhador) trab.get(i);
            if(temp.getSexo() == 'F') {
                femaleWorkers.add(temp);
            }
        }
        orderWoman(femaleWorkers);
        for(int i=0; i < femaleWorkers.size(); i++) {
            Trabalhador temp = (Trabalhador) femaleWorkers.get(i);
            cad += temp.toString()+ "\n";
        }
//        jTextArea2.setText(cad);
//        jTextArea2.setCaretPosition(0);
    }
    
    private void orderWoman(ArrayList lista) {
        Collections.sort(lista, new Comparator<Trabalhador>() {
            @Override
            public int compare(Trabalhador pessoa1, Trabalhador pessoa2) {
                return pessoa1.getNome().compareTo(pessoa2.getNome());
            }
        });
    }
    
    private void colocarNaAvl() {
        for(int i=0; i < femaleWorkers.size(); i++) {
            listaGrafo.insereAVL(femaleWorkers.get(i));
        }
//        jTextArea2.setText(listaGrafo.emOrdem());
//        jTextArea2.setCaretPosition(0);
    }
    
    private void inserirQueue() {
        for(int i=0; i < trab.size(); i++) {
            heap.insert((Trabalhador)trab.get(i));
        }
    }
    
    private void mostrarHeap() {
        jTextArea2.setText(heap.toString());
        jTextArea2.setCaretPosition(0);
    }

    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Principal.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Principal().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTextArea jTextArea1;
    private javax.swing.JTextArea jTextArea2;
    private javax.swing.JToolBar jToolBar1;
    // End of variables declaration//GEN-END:variables
}
