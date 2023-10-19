package supermercado;

import java.sql.*;


/**
 *
 * @author Jose Manuel Fernandez Cedeño
 */

//Creamos una clase para realizar la conexion
public class Conexion {
    String bd = "supermercado_r";
    String url = "jdbc:mysql://localhost:3306/";
    String user = "root";
    String password = "";
    String driver = "com.mysql.cj.jdbc.Driver";
    Connection cn;

// Creamos un método que utilizaremos para realizar las conexiones a la base de datos cuando vayamos a operar con esta.
    public Connection conectar(){
        try {
            Class.forName(driver);
            this.cn = DriverManager.getConnection(url+bd, user, password);
          
        } catch (Exception e) {
            System.out.println("No se conecto a la BD "+bd);
        }
        return cn;
    }
    
}
