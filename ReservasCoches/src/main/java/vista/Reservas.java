/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package vista;

import controladores.ControladorReservas;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author USUARIO
 */
public class Reservas extends javax.swing.JFrame {

    String nomArchivo = "listadoDeportistas.dat";
    ControladorReservas ctrlReservas = new ControladorReservas();
    
    String[] nomColumnas = {"NOMBRE",
        "DEPORTE",
        "A�O NAC.",
        "ALTURA"};
    Object[][] matrizDatos = {
//        {"Carolina Mar�n", "B�dminton", 1993, 1.72},
//        {"Pau Gasol", "Baloncesto", 1980, 2.15},
//        {"Rafa Nadal", "Tenis", 1986, 1.85},
    };
    
   
    DefaultTableModel dtm = new DefaultTableModel(matrizDatos, nomColumnas);
    
    /**
     * Creates new form VentanaMiniAgenda
     */
    public Reservas() {
        initComponents();
        ctrlReservas.conectarBD();
        dtm = new DefaultTableModel(matrizDatos, nomColumnas) {

            //para impedir edici�n de las celdas
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;

            }

        };
        // Establecimiento de los datos a la tabla

        jDatos.setModel(dtm);

        // Configuraci�n del tipo de selecci�n de la tabla

        jDatos.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);

        // Ajuste de anchos

        jDatos.getColumn("NOMBRE").setPreferredWidth(260);

        jDatos.getColumn("DEPORTE").setPreferredWidth(160);

        jDatos.getColumn("A�O NAC.").setPreferredWidth(100);

        jDatos.getColumn("ALTURA").setPreferredWidth(200);

        actualizaTabla();
    }
        
        
    
    
    
    
    private void actualizaTabla() {
        //miniAgenda.a�adir(new Deportista("Ana","Futbol",2011, 1.76f)); // registro de ejemplo directo
        matrizDatos = ctrlReservas.convertirAMatrizObject();
        dtm = new DefaultTableModel(matrizDatos, nomColumnas) {
            //para impedir edici�n de las celdas
            @Override
            public boolean isCellEditable(int fila, int columna) {
                return false;
            }
        };
        jDatos.setModel(dtm);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel2 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jTF_NomYApell = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        jTF_Correo = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jTF_Telefono = new javax.swing.JTextField();
        jButton_VaciarCampos = new javax.swing.JButton();
        jButton_Anadir = new javax.swing.JButton();
        jButton_Actualizar = new javax.swing.JButton();
        jPanel1 = new javax.swing.JPanel();
        jButton_BorrarPorNombre = new javax.swing.JButton();
        jButton_BorrarSeleccionado = new javax.swing.JButton();
        jButton_BorrarTodo = new javax.swing.JButton();
        jPanel4 = new javax.swing.JPanel();
        jButton_GuardarFichreo = new javax.swing.JButton();
        jButton_CargarFichero = new javax.swing.JButton();
        jButton_CargarEjemplos = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        jDatos = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jPanel2.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel1.setText("Nombre y apellidos: ");

        jTF_NomYApell.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        jTF_NomYApell.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTF_NomYApellActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel2.setText("Correo: ");

        jTF_Correo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));

        jLabel3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        jLabel3.setText("Telefono:");

        jTF_Telefono.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        jTF_Telefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTF_TelefonoActionPerformed(evt);
            }
        });

        jButton_VaciarCampos.setFont(new java.awt.Font("Alef", 1, 12)); // NOI18N
        jButton_VaciarCampos.setText("Vaciar campos");
        jButton_VaciarCampos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_VaciarCamposActionPerformed(evt);
            }
        });

        jButton_Anadir.setFont(new java.awt.Font("Alef", 1, 12)); // NOI18N
        jButton_Anadir.setText("Añadir");
        jButton_Anadir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_AnadirActionPerformed(evt);
            }
        });

        jButton_Actualizar.setFont(new java.awt.Font("Alef", 1, 12)); // NOI18N
        jButton_Actualizar.setText("Actualizar");
        jButton_Actualizar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ActualizarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                        .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addComponent(jLabel3)
                    .addComponent(jButton_VaciarCampos))
                .addGap(59, 59, 59)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTF_NomYApell, javax.swing.GroupLayout.DEFAULT_SIZE, 454, Short.MAX_VALUE)
                    .addComponent(jTF_Correo)
                    .addComponent(jTF_Telefono, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGap(64, 64, 64)
                        .addComponent(jButton_Anadir, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jButton_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 111, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(36, 36, 36)))
                .addContainerGap(22, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTF_NomYApell, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jTF_Correo, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(29, 29, 29)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTF_Telefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_VaciarCampos, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Anadir, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_Actualizar, javax.swing.GroupLayout.PREFERRED_SIZE, 35, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(40, 40, 40))
        );

        jPanel1.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));

        jButton_BorrarPorNombre.setText("Borrar por nombre");
        jButton_BorrarPorNombre.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        jButton_BorrarPorNombre.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_BorrarPorNombreMouseClicked(evt);
            }
        });
        jButton_BorrarPorNombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_BorrarPorNombreActionPerformed(evt);
            }
        });

        jButton_BorrarSeleccionado.setText("Borrar seleccionado");
        jButton_BorrarSeleccionado.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        jButton_BorrarSeleccionado.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_BorrarSeleccionadoActionPerformed(evt);
            }
        });

        jButton_BorrarTodo.setText("Borrar todo");
        jButton_BorrarTodo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        jButton_BorrarTodo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jButton_BorrarTodoMouseClicked(evt);
            }
        });
        jButton_BorrarTodo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_BorrarTodoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addComponent(jButton_BorrarPorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(47, 47, 47)
                .addComponent(jButton_BorrarSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 177, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jButton_BorrarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_BorrarPorNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_BorrarSeleccionado, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_BorrarTodo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(27, Short.MAX_VALUE))
        );

        jPanel4.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));

        jButton_GuardarFichreo.setText("Guardar en fichero");
        jButton_GuardarFichreo.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        jButton_GuardarFichreo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_GuardarFichreoActionPerformed(evt);
            }
        });

        jButton_CargarFichero.setText("Cargar de fichero ");
        jButton_CargarFichero.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));

        jButton_CargarEjemplos.setText("Cargar ejemplos");
        jButton_CargarEjemplos.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray, java.awt.Color.lightGray));
        jButton_CargarEjemplos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_CargarEjemplosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(40, 40, 40)
                .addComponent(jButton_GuardarFichreo, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(44, 44, 44)
                .addComponent(jButton_CargarFichero, javax.swing.GroupLayout.PREFERRED_SIZE, 179, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(60, 60, 60)
                .addComponent(jButton_CargarEjemplos, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addGap(28, 28, 28)
                .addGroup(jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jButton_CargarFichero, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_CargarEjemplos, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButton_GuardarFichreo, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(33, Short.MAX_VALUE))
        );

        jDatos.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jDatos);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(34, 34, 34)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 551, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(17, 17, 17)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 469, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTF_NomYApellActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTF_NomYApellActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTF_NomYApellActionPerformed

    private void jTF_TelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTF_TelefonoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTF_TelefonoActionPerformed

    private void jButton_VaciarCamposActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_VaciarCamposActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_VaciarCamposActionPerformed

    private void jButton_ActualizarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ActualizarActionPerformed
        actualizaTabla();
    }//GEN-LAST:event_jButton_ActualizarActionPerformed

    private void jButton_BorrarPorNombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_BorrarPorNombreActionPerformed
        ctrlReservas.borrarPorNombre(nomArchivo);
        actualizaTabla();
    }//GEN-LAST:event_jButton_BorrarPorNombreActionPerformed

    private void jButton_BorrarSeleccionadoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_BorrarSeleccionadoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_BorrarSeleccionadoActionPerformed

    private void jButton_BorrarTodoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_BorrarTodoActionPerformed
       ctrlReservas.vaciar();
       actualizaTabla();
       
    }//GEN-LAST:event_jButton_BorrarTodoActionPerformed

    private void jButton_GuardarFichreoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_GuardarFichreoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jButton_GuardarFichreoActionPerformed

    private void jButton_BorrarPorNombreMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_BorrarPorNombreMouseClicked
        ctrlReservas.borrarPorNombre(nomArchivo);
    }//GEN-LAST:event_jButton_BorrarPorNombreMouseClicked

    private void jButton_BorrarTodoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jButton_BorrarTodoMouseClicked
//        miniAgenda.vaciar(dep);
          
    }//GEN-LAST:event_jButton_BorrarTodoMouseClicked

    private void jButton_CargarEjemplosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_CargarEjemplosActionPerformed
        ctrlReservas.exportarEjemplos();
        actualizaTabla();
    }//GEN-LAST:event_jButton_CargarEjemplosActionPerformed

    private void jButton_AnadirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_AnadirActionPerformed
//        miniAgenda.
    }//GEN-LAST:event_jButton_AnadirActionPerformed

    /**
     * @param args the command line arguments
     */
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
            java.util.logging.Logger.getLogger(Reservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Reservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Reservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Reservas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Reservas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Actualizar;
    private javax.swing.JButton jButton_Anadir;
    private javax.swing.JButton jButton_BorrarPorNombre;
    private javax.swing.JButton jButton_BorrarSeleccionado;
    private javax.swing.JButton jButton_BorrarTodo;
    private javax.swing.JButton jButton_CargarEjemplos;
    private javax.swing.JButton jButton_CargarFichero;
    private javax.swing.JButton jButton_GuardarFichreo;
    private javax.swing.JButton jButton_VaciarCampos;
    private javax.swing.JTable jDatos;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTF_Correo;
    private javax.swing.JTextField jTF_NomYApell;
    private javax.swing.JTextField jTF_Telefono;
    // End of variables declaration//GEN-END:variables
}
