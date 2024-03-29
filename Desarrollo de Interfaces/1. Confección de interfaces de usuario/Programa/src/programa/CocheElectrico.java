/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JDialog.java to edit this template
 */
package programa;

import java.awt.Color;
import java.awt.Image;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author Usuario
 */
public class CocheElectrico extends javax.swing.JDialog {

    String usuario;
    
    public CocheElectrico(java.awt.Frame parent, boolean modal) {
        super(parent, modal);
        initComponents();
        setSize(740,500);
        setResizable(false);
        setTitle("Coche Eléctrico");
        setLocationRelativeTo(null);
        
        ImageIcon wallpaper= new ImageIcon("src\\imagenes\\wallpaper.png");
        Icon icono= new ImageIcon(wallpaper.getImage().getScaledInstance(jLabel_Wallpaper.getWidth(),jLabel_Wallpaper.getHeight(), Image.SCALE_DEFAULT));
        jLabel_Wallpaper.setIcon(icono);
        this.repaint();
        
        usuario = jTextField_Nombre.getText();
        
        jLabel_Edad.setVisible(false);
        jTextField_Edad.setVisible(false);
        jRadioButton_Cables.setVisible(false);
                     
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel_Titulo = new javax.swing.JLabel();
        jLabel_Nombre = new javax.swing.JLabel();
        jLabel_Apellidos = new javax.swing.JLabel();
        jLabel_Telefono = new javax.swing.JLabel();
        jLabel_FRecogida = new javax.swing.JLabel();
        jLabel_FDevolucion = new javax.swing.JLabel();
        jLabel_Tipo = new javax.swing.JLabel();
        jLabel_Kms = new javax.swing.JLabel();
        jLabel_Extras = new javax.swing.JLabel();
        jTextField_Nombre = new javax.swing.JTextField();
        jTextField_Apellidos = new javax.swing.JTextField();
        jTextField_Dni = new javax.swing.JTextField();
        jButton_Reservar = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jComboBox_Tipo = new javax.swing.JComboBox<>();
        jCheckBox_Cadenas = new javax.swing.JCheckBox();
        jCheckBox_SillaBebe = new javax.swing.JCheckBox();
        jCheckBox_Seguro = new javax.swing.JCheckBox();
        jCheckBox_CancelacionG = new javax.swing.JCheckBox();
        jCheckBox_NoPrecisa = new javax.swing.JCheckBox();
        jSeparator2 = new javax.swing.JSeparator();
        jLabel_Edad = new javax.swing.JLabel();
        jTextField_Kms = new javax.swing.JTextField();
        jRadioButton_Cables = new javax.swing.JRadioButton();
        jTextField_Telefono1 = new javax.swing.JTextField();
        jLabel_Dni = new javax.swing.JLabel();
        jSpinner_FRecogida = new javax.swing.JSpinner();
        jSpinner_FDevolucion = new javax.swing.JSpinner();
        jTextField_Edad = new javax.swing.JTextField();
        jLabel_Wallpaper = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setMinimumSize(new java.awt.Dimension(740, 500));
        setModal(true);
        setName("dialog0"); // NOI18N
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel_Titulo.setFont(new java.awt.Font("Segoe UI", 1, 22)); // NOI18N
        jLabel_Titulo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel_Titulo.setText("COCHE ELÉCTRICO");
        jLabel_Titulo.setAlignmentY(0.0F);
        getContentPane().add(jLabel_Titulo, new org.netbeans.lib.awtextra.AbsoluteConstraints(200, 20, 350, 30));

        jLabel_Nombre.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_Nombre.setText("NOMBRE");
        getContentPane().add(jLabel_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 70, -1, -1));

        jLabel_Apellidos.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_Apellidos.setText("APELLIDOS");
        getContentPane().add(jLabel_Apellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(32, 140, -1, -1));

        jLabel_Telefono.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_Telefono.setText("DNI/CIF");
        getContentPane().add(jLabel_Telefono, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 70, -1, -1));

        jLabel_FRecogida.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_FRecogida.setText("FECHA DE RECOGIDA");
        getContentPane().add(jLabel_FRecogida, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 220, -1, -1));

        jLabel_FDevolucion.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_FDevolucion.setText("FECHA DE DEVOLUCIÓN");
        getContentPane().add(jLabel_FDevolucion, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 290, -1, -1));

        jLabel_Tipo.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_Tipo.setText("TIPO");
        getContentPane().add(jLabel_Tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 220, -1, -1));

        jLabel_Kms.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_Kms.setText("NÚMERO DE KILOMETROS");
        getContentPane().add(jLabel_Kms, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 290, -1, -1));

        jLabel_Extras.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_Extras.setText("EXTRAS");
        getContentPane().add(jLabel_Extras, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 360, -1, -1));

        jTextField_Nombre.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField_Nombre.setToolTipText("Ingrese su nombre.");
        jTextField_Nombre.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jTextField_Nombre.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextField_NombreActionPerformed(evt);
            }
        });
        getContentPane().add(jTextField_Nombre, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 90, 250, -1));

        jTextField_Apellidos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField_Apellidos.setToolTipText("Ingrese sus apellidos.");
        jTextField_Apellidos.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(jTextField_Apellidos, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 160, 250, -1));

        jTextField_Dni.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField_Dni.setToolTipText("Indique su DNI o CIF.");
        jTextField_Dni.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(jTextField_Dni, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 90, 200, -1));

        jButton_Reservar.setFont(new java.awt.Font("Segoe UI", 1, 16)); // NOI18N
        jButton_Reservar.setText("RESERVAR");
        jButton_Reservar.setToolTipText("");
        jButton_Reservar.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));
        jButton_Reservar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton_ReservarActionPerformed(evt);
            }
        });
        getContentPane().add(jButton_Reservar, new org.netbeans.lib.awtextra.AbsoluteConstraints(550, 380, 130, 40));
        getContentPane().add(jSeparator1, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 60, 650, 10));

        jComboBox_Tipo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jComboBox_Tipo.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "COMPACTO", "BERLINA", "SUV", "DEPORTIVO", "TESLA" }));
        jComboBox_Tipo.setToolTipText("Seleccione el tipo de coche que requiere.");
        jComboBox_Tipo.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        jComboBox_Tipo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jComboBox_TipoActionPerformed(evt);
            }
        });
        getContentPane().add(jComboBox_Tipo, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 240, -1, -1));

        jCheckBox_Cadenas.setText("Cadenas");
        jCheckBox_Cadenas.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(jCheckBox_Cadenas, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 360, -1, -1));

        jCheckBox_SillaBebe.setText("Silla de bebe");
        jCheckBox_SillaBebe.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(jCheckBox_SillaBebe, new org.netbeans.lib.awtextra.AbsoluteConstraints(220, 360, -1, -1));

        jCheckBox_Seguro.setText("Seguro a todo riesgo");
        jCheckBox_Seguro.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(jCheckBox_Seguro, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 360, -1, -1));

        jCheckBox_CancelacionG.setText("Cancelación gratuita");
        jCheckBox_CancelacionG.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(jCheckBox_CancelacionG, new org.netbeans.lib.awtextra.AbsoluteConstraints(110, 400, -1, -1));

        jCheckBox_NoPrecisa.setText("No precisa");
        jCheckBox_NoPrecisa.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(jCheckBox_NoPrecisa, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 400, -1, -1));
        getContentPane().add(jSeparator2, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 200, 650, 10));

        jLabel_Edad.setText("Edad del conductor:");
        getContentPane().add(jLabel_Edad, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 230, -1, -1));

        jTextField_Kms.setToolTipText("Ingrese los Kms que va a realizar con el coche.");
        jTextField_Kms.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(jTextField_Kms, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 310, 90, -1));

        jRadioButton_Cables.setText("Requiere cables de carga");
        jRadioButton_Cables.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(jRadioButton_Cables, new org.netbeans.lib.awtextra.AbsoluteConstraints(180, 260, -1, -1));

        jTextField_Telefono1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jTextField_Telefono1.setToolTipText("Indique un número de contacto.");
        jTextField_Telefono1.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(jTextField_Telefono1, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 160, 200, -1));

        jLabel_Dni.setFont(new java.awt.Font("Segoe UI", 1, 14)); // NOI18N
        jLabel_Dni.setText("TELEFONO");
        getContentPane().add(jLabel_Dni, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 140, -1, -1));

        jSpinner_FRecogida.setModel(new javax.swing.SpinnerDateModel());
        jSpinner_FRecogida.setToolTipText("INDIQUE LA FECHA EN LA QUE DEVOLVERÁ EL VEHÍCULO");
        jSpinner_FRecogida.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(jSpinner_FRecogida, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 310, -1, -1));

        jSpinner_FDevolucion.setModel(new javax.swing.SpinnerDateModel());
        jSpinner_FDevolucion.setToolTipText("INDIQUE LA FECHA EN LA QUE RECOGERÁ EL VEHÍCULO");
        jSpinner_FDevolucion.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(jSpinner_FDevolucion, new org.netbeans.lib.awtextra.AbsoluteConstraints(480, 240, -1, -1));

        jTextField_Edad.setBorder(javax.swing.BorderFactory.createEtchedBorder());
        getContentPane().add(jTextField_Edad, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 230, 40, -1));
        getContentPane().add(jLabel_Wallpaper, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 740, 500));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextField_NombreActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextField_NombreActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextField_NombreActionPerformed

    private void jButton_ReservarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton_ReservarActionPerformed
        String nombre, apellidos, telefono;
        int tipo, validacion = 0;

        nombre = jTextField_Nombre.getText().trim();
        apellidos = jTextField_Apellidos.getText().trim();
        telefono = jTextField_Dni.getText().trim();
        tipo = jComboBox_Tipo.getSelectedIndex();

        if(nombre.equals("")){
            jTextField_Nombre.setBackground(Color.red);
            validacion++;
        }

        if(apellidos.equals("")){
            jTextField_Apellidos.setBackground(Color.red);
            validacion++;
        }

        if(telefono.equals("")){
            jTextField_Dni.setBackground(Color.red);
            validacion++;
        }

        if (validacion == 0) {
            Limpiar();
            jTextField_Nombre.setBackground(Color.GREEN);
            jTextField_Apellidos.setBackground(Color.GREEN);
            jTextField_Dni.setBackground(Color.GREEN);

            JOptionPane.showMessageDialog(null, "El vehículo se ha reservado con éxito.");
            jTextField_Nombre.setBackground(Color.WHITE);
            jTextField_Apellidos.setBackground(Color.WHITE);
            jTextField_Dni.setBackground(Color.WHITE);
        }
        else
        JOptionPane.showMessageDialog(null, "Debes rellenar los campos indicados.");
    }//GEN-LAST:event_jButton_ReservarActionPerformed

    private void jComboBox_TipoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jComboBox_TipoActionPerformed
        if (jComboBox_Tipo.getSelectedItem().toString().equals("TESLA")){
            jLabel_Edad.setVisible(true);
            jTextField_Edad.setVisible(true);
            jRadioButton_Cables.setVisible(true);
        }
        else{
            jLabel_Edad.setVisible(false);
            jTextField_Edad.setVisible(false);
            jRadioButton_Cables.setVisible(false);
        }
    }//GEN-LAST:event_jComboBox_TipoActionPerformed

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
            java.util.logging.Logger.getLogger(CocheElectrico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(CocheElectrico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(CocheElectrico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(CocheElectrico.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the dialog */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                CocheElectrico dialog = new CocheElectrico(new javax.swing.JFrame(), true);
                dialog.addWindowListener(new java.awt.event.WindowAdapter() {
                    @Override
                    public void windowClosing(java.awt.event.WindowEvent e) {
                        System.exit(0);
                    }
                });
                dialog.setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton_Reservar;
    private javax.swing.JCheckBox jCheckBox_Cadenas;
    private javax.swing.JCheckBox jCheckBox_CancelacionG;
    private javax.swing.JCheckBox jCheckBox_NoPrecisa;
    private javax.swing.JCheckBox jCheckBox_Seguro;
    private javax.swing.JCheckBox jCheckBox_SillaBebe;
    private javax.swing.JComboBox<String> jComboBox_Tipo;
    private javax.swing.JLabel jLabel_Apellidos;
    private javax.swing.JLabel jLabel_Dni;
    private javax.swing.JLabel jLabel_Edad;
    private javax.swing.JLabel jLabel_Extras;
    private javax.swing.JLabel jLabel_FDevolucion;
    private javax.swing.JLabel jLabel_FRecogida;
    private javax.swing.JLabel jLabel_Kms;
    private javax.swing.JLabel jLabel_Nombre;
    private javax.swing.JLabel jLabel_Telefono;
    private javax.swing.JLabel jLabel_Tipo;
    private javax.swing.JLabel jLabel_Titulo;
    private javax.swing.JLabel jLabel_Wallpaper;
    private javax.swing.JRadioButton jRadioButton_Cables;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSpinner jSpinner_FDevolucion;
    private javax.swing.JSpinner jSpinner_FRecogida;
    private javax.swing.JTextField jTextField_Apellidos;
    private javax.swing.JTextField jTextField_Dni;
    private javax.swing.JTextField jTextField_Edad;
    private javax.swing.JTextField jTextField_Kms;
    private javax.swing.JTextField jTextField_Nombre;
    private javax.swing.JTextField jTextField_Telefono1;
    // End of variables declaration//GEN-END:variables

 public void Limpiar(){
        jTextField_Nombre.setText("");
        jTextField_Apellidos.setText("");
        jTextField_Dni.setText("");
        jComboBox_Tipo.setSelectedIndex(0);
    }
}
