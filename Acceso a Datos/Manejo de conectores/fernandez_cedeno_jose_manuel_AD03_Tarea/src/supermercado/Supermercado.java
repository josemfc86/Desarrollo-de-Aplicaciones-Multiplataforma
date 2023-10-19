    package supermercado;

import java.sql.*;
import java.time.LocalDate;

/**
 *
 * @author Jose Manuel Fernandez Cedeño
 */
public class Supermercado extends javax.swing.JFrame {

    //Creamos nuestro objeto conexion y una variable para elcodigo de usuario que podremos usar en el código de distintos botones.
    Conexion c;
    int p_usuario;

    Connection c1;

    public Supermercado() {
        initComponents();
        this.setLocationRelativeTo(null);
        this.setTitle("SUPERMERCADO");
        this.setResizable(false);
        c = new Conexion();

    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabelUsuario = new javax.swing.JLabel();
        jLabelContrasena = new javax.swing.JLabel();
        jTextFieldNroCarrito = new javax.swing.JTextField();
        jTextFieldFecha = new javax.swing.JTextField();
        jLabelNroCarrito = new javax.swing.JLabel();
        jLabelPVP = new javax.swing.JLabel();
        jTextFieldUsuario = new javax.swing.JTextField();
        jLabelDescArticulo = new javax.swing.JLabel();
        jTextFieldDescArticulo = new javax.swing.JTextField();
        jButtonConsultar = new javax.swing.JButton();
        jTextFieldPVP = new javax.swing.JTextField();
        jLabelCantidad = new javax.swing.JLabel();
        jTextFieldCantidad = new javax.swing.JTextField();
        jButtonLogin = new javax.swing.JButton();
        jButtonComprar = new javax.swing.JButton();
        jLabelFecha = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextPane1 = new javax.swing.JTextPane();
        jPassword = new javax.swing.JPasswordField();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabelUsuario.setText("USUARIO");

        jLabelContrasena.setText("CONTRASEÑA");

        jTextFieldNroCarrito.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldNroCarritoActionPerformed(evt);
            }
        });

        jLabelNroCarrito.setText("Nº DE CARRITO");

        jLabelPVP.setText("PVP");

        jTextFieldUsuario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldUsuarioActionPerformed(evt);
            }
        });

        jLabelDescArticulo.setText("Desc. Articulo");

        jTextFieldDescArticulo.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jTextFieldDescArticuloActionPerformed(evt);
            }
        });

        jButtonConsultar.setText("Consultar");
        jButtonConsultar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonConsultarActionPerformed(evt);
            }
        });

        jLabelCantidad.setText("Cantidad");

        jButtonLogin.setText("Login");
        jButtonLogin.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonLoginActionPerformed(evt);
            }
        });

        jButtonComprar.setText("Comprar");
        jButtonComprar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButtonComprarActionPerformed(evt);
            }
        });

        jLabelFecha.setText("FECHA");

        jScrollPane1.setViewportView(jTextPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(37, 37, 37)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabelNroCarrito)
                            .addComponent(jLabelUsuario)
                            .addComponent(jLabelDescArticulo))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldNroCarrito, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jTextFieldDescArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 136, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addGap(66, 66, 66)
                                .addComponent(jLabelContrasena))
                            .addGroup(layout.createSequentialGroup()
                                .addGap(18, 18, 18)
                                .addComponent(jButtonConsultar)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(jLabelPVP))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabelFecha)))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jTextFieldFecha)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jTextFieldPVP, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(jLabelCantidad))
                            .addComponent(jPassword))
                        .addGap(18, 18, 18)
                        .addComponent(jTextFieldCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 70, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jButtonLogin, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jButtonComprar, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
                .addGap(50, 50, 50))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(59, 59, 59)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelContrasena, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldUsuario, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonLogin)
                    .addComponent(jPassword, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(34, 34, 34)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelNroCarrito, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldFecha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldNroCarrito, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelFecha, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(51, 51, 51)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabelDescArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldDescArticulo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonConsultar)
                    .addComponent(jLabelPVP, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldPVP, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabelCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, 31, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jTextFieldCantidad, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jButtonComprar))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 60, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(42, 42, 42))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jTextFieldNroCarritoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldNroCarritoActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldNroCarritoActionPerformed

    private void jTextFieldUsuarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldUsuarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldUsuarioActionPerformed

    private void jTextFieldDescArticuloActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jTextFieldDescArticuloActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_jTextFieldDescArticuloActionPerformed

    private void jButtonLoginActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonLoginActionPerformed
        try {
            //Tomamos los datos ingresados por el usuario en los campos de texto para usuario y contraseña.
            String usuario = jTextFieldUsuario.getText().trim();
            String contrasena = String.valueOf(jPassword.getPassword()).trim();
            String fecha;
            jTextPane1.setText("");
            int p_carrito;
            //Comprobamos que el login del usuario sea correcto.
            PreparedStatement ps = c.conectar().prepareStatement("SELECT P_USUARIO FROM USUARIOS WHERE NOMBRE = ? AND CONTRASENA = ?;");
            ps.setString(1, usuario);
            ps.setString(2, contrasena);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                //Guardamos el codigo del usuario.
                p_usuario = rs.getInt(1);
                //Asignamos un nuevo carrito al usuario y la fecha de registro.
                Statement s = c.conectar().createStatement();
                rs = s.executeQuery("SELECT (MAX(P_CARRITO)+2) FROM CARRITOS;");
                if (rs.next()) {
                    p_carrito = rs.getInt(1);
                    fecha = String.valueOf(LocalDate.now());
                    jTextFieldNroCarrito.setText(String.valueOf(p_carrito));
                    jTextFieldFecha.setText(fecha);
                }
                //En el caso de que el usuario y contraseña no sean correctos, se le indica al usuario.        
            } else {
                jTextPane1.setText("El usuario y/o la contraseña son incorrectos.");
            }
        } catch (SQLException ex) {
            jTextPane1.setText("No se pudo conectar a la BD " + c.bd);
        }
    }//GEN-LAST:event_jButtonLoginActionPerformed

    private void jButtonConsultarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonConsultarActionPerformed
        try {
            //Tomamos el dato ingresado por el usuario.
            String descripcion = jTextFieldDescArticulo.getText().trim();
            jTextPane1.setText("");
            jTextFieldPVP.setText("");
            //Hacemos la consulta a la base de datos.
            PreparedStatement ps = c.conectar().prepareStatement("SELECT PVP FROM PRODUCTOS WHERE DESCRIPCION = ?;");
            ps.setString(1, descripcion);
            ResultSet rs = ps.executeQuery();
            //Si la descripcion del producto es correcta mostramos el PVP de este.
            if (rs.next()) {

                jTextFieldPVP.setText("PVP: " + String.valueOf(rs.getFloat(1)));

                //En caso contrario le indicamos al usuario que no existe ningun producto con esa descripcion.    
            } else {
                jTextPane1.setText("Artículo inexistente");
            }
        } catch (SQLException ex) {
            jTextPane1.setText("No se pudo conectar a la BD " + c.bd);
        }
    }//GEN-LAST:event_jButtonConsultarActionPerformed

    private void jButtonComprarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButtonComprarActionPerformed
        try {
            //Guardamos los datos de descripcion, nro de carrito, fecha y cantidad.
            String descripcion = jTextFieldDescArticulo.getText().trim();
            int carrito = Integer.parseInt(jTextFieldNroCarrito.getText().trim());
            String fecha = jTextFieldFecha.getText().trim();
            int p_carr_pro, producto;
            int cantidad = Integer.parseInt(jTextFieldCantidad.getText().trim());
            int stock;
            //Consultamos el stock del producto y guardamos su codigo para utilizarlo en consultas posteriores.
            PreparedStatement ps = c.conectar().prepareStatement("SELECT P_PRODUCTO, STOCK FROM PRODUCTOS WHERE DESCRIPCION = ?;");
            ps.setString(1, descripcion);
            ResultSet rs = ps.executeQuery();
            if (rs.next()) {
                producto = rs.getInt(1);
                stock = rs.getInt(2);
                //Si la cantidad a comprar es menor o igual a la del stock del producto, entonces procedemos a la transaccion de la compra.
                if (cantidad <= stock) {
                    //Creamos un nuevo codigo P_CARR_PRO que obtenemos sumandole 10 al maximo P_CARR_PRO existente.
                    PreparedStatement ps1 = c.conectar().prepareStatement("SELECT (MAX(P_CARR_PRO)+10) FROM CARR_PRO;");
                    rs = ps1.executeQuery();
                    if (rs.next()) {
                        p_carr_pro = rs.getInt(1);

                        //Comprobamos si existe el carrito del usuario.
                        PreparedStatement ps0 = c.conectar().prepareStatement("SELECT P_CARRITO FROM CARRITOS WHERE P_CARRITO = ?;");
                        ps0.setInt(1, carrito);
                        rs = ps0.executeQuery();
                       
                        if (rs.next()) {//Si el carrito existe solo insertamos en la base de datos el nuevo CARR_PRO y actualizamos el stock.
                            try {
                                //Iniciamos con el proceso de transaccion.
                                c1 = c.conectar();
                                c1.setAutoCommit(false);
                                //Registramos en la base de datos el nuevo carr_pro.
                                PreparedStatement ps3 = c1.prepareStatement("INSERT INTO CARR_PRO (P_CARR_PRO, A_CARRITO, A_PRODUCTO, CANTIDAD) VALUES (?,?,?,?);");
                                ps3.setInt(1, p_carr_pro);
                                ps3.setInt(2, carrito);
                                ps3.setInt(3, producto);
                                ps3.setString(4, String.valueOf(cantidad));
                                ps3.executeUpdate();
                                //Actualizamos el stock del producto comprado.
                                PreparedStatement ps4 = c1.prepareStatement("UPDATE PRODUCTOS SET STOCK = (STOCK - ?) WHERE P_PRODUCTO = ?;");
                                ps4.setInt(1, cantidad);
                                ps4.setInt(2, producto);
                                ps4.executeUpdate();
                                //Ejecutamos la transaccion.
                                c1.commit();

                                jTextPane1.setText("Compra realizada con éxito.");

                            } catch (Exception e) {
                                /*Si algo sale mal en el proceso de transaccion, esta se interrumpe y no se ejecutan ninguna de las clausulas, evitando que se realice algún
                            cambio en la base de datos*/
                                c1.rollback();
                                jTextPane1.setText("Fallo en la transacción de compra.");
                            }
                        } else { //Si el carro no existe insertamos el nuevo carrito en la base de datos junto con el nuevo CARR_PRO y la actualizacion del stock.

                            try {
                                //Iniciamos con el proceso de transaccion.
                                c1 = c.conectar();
                                c1.setAutoCommit(false);
                                //Regisramos en la base de datos el nuevo carrito.
                                PreparedStatement ps2 = c1.prepareStatement("INSERT INTO CARRITOS (P_CARRITO, A_USUARIO, FECHA) VALUES (?,?,?);");
                                ps2.setInt(1, carrito);
                                ps2.setInt(2, p_usuario);
                                ps2.setString(3, fecha);
                                ps2.executeUpdate();
                                //Regisramos en la base de datos el nuevo carr_pro.
                                PreparedStatement ps3 = c1.prepareStatement("INSERT INTO CARR_PRO (P_CARR_PRO, A_CARRITO, A_PRODUCTO, CANTIDAD) VALUES (?,?,?,?);");
                                ps3.setInt(1, p_carr_pro);
                                ps3.setInt(2, carrito);
                                ps3.setInt(3, producto);
                                ps3.setString(4, String.valueOf(cantidad));
                                ps3.executeUpdate();
                                //Actualizamos el stock del producto comprado.
                                PreparedStatement ps4 = c1.prepareStatement("UPDATE PRODUCTOS SET STOCK = (STOCK - ?) WHERE P_PRODUCTO = ?;");
                                ps4.setInt(1, cantidad);
                                ps4.setInt(2, producto);
                                ps4.executeUpdate();
                                //Ejecutamos la transaccion.
                                c1.commit();

                                jTextPane1.setText("Compra realizada con éxito.");

                            } catch (Exception e) {
                                /*Si algo sale mal en el proceso de transaccion, esta se interrumpe y no se ejecutan ninguna de las clausulas, evitando que se realice algún
                            cambio en la base de datos*/
                                c1.rollback();
                                jTextPane1.setText("Fallo en la transacción de compra.");
                            }
                        }
                    }

                } else {
                    //Si el stock es inferiror a la cantidad solicitada por el usuario, se le indica a este.
                    jTextPane1.setText("Cantidad excesiva, Stock disponible: " + String.valueOf(stock));
                }

            } else {
                //Se le indica al usuario que no existe el articulo, si la descripcion suministrada no es correcta.
                jTextPane1.setText("Artículo inexistente.");
            }
        } catch (SQLException ex) {
            jTextPane1.setText("No se pudo conectar a la BD " + c.bd);
        }
    }//GEN-LAST:event_jButtonComprarActionPerformed

    public static void main(String args[]) {

        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Supermercado().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButtonComprar;
    private javax.swing.JButton jButtonConsultar;
    private javax.swing.JButton jButtonLogin;
    private javax.swing.JLabel jLabelCantidad;
    private javax.swing.JLabel jLabelContrasena;
    private javax.swing.JLabel jLabelDescArticulo;
    private javax.swing.JLabel jLabelFecha;
    private javax.swing.JLabel jLabelNroCarrito;
    private javax.swing.JLabel jLabelPVP;
    private javax.swing.JLabel jLabelUsuario;
    private javax.swing.JPasswordField jPassword;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextField jTextFieldCantidad;
    private javax.swing.JTextField jTextFieldDescArticulo;
    private javax.swing.JTextField jTextFieldFecha;
    private javax.swing.JTextField jTextFieldNroCarrito;
    private javax.swing.JTextField jTextFieldPVP;
    private javax.swing.JTextField jTextFieldUsuario;
    private javax.swing.JTextPane jTextPane1;
    // End of variables declaration//GEN-END:variables
}
