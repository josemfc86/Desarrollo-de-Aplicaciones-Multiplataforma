package supermercado;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

/**
 *
 * @author Jose Manuel Fernandez Cedeño
 */
public class Consultas {

    public static void main(String[] args) {
        //Mediante un booleano y una estructura while el usuario puede realizar todas las consultas que desee hasta que decida salir del programa.
        boolean b = true;
        
        while (b = true) {
            System.out.println("Seleccione una opción:");
            System.out.println("1.- Nombre de sección, descripción de producto, pvp, stock de los productos para productos cuyo pvp>11.");
            System.out.println("2.- Nombre de sección y precio medio de sus productos, teniendo en cuenta únicamente aquellos productos cuyo pvp>11.");
            System.out.println("3.- Nombre de sección y precio medio de sus productos, teniendo en cuenta únicamente aquellos productos cuyo pvp>11 y cuya  media de pvp>21.");
            System.out.println("4.- Nombre de la sección con el mayor precio medio.");
            System.out.println("5.- Solicitar datos de una sección específica.");
            System.out.println("6.- Formulario para la creación de carritos ");
            System.out.println("7.- Salir");
            System.out.println("");
            
            //Creamos nuestro objeto conexion y un objeto scanner que guarde la opcion escogida por el usuario.
            Conexion c;
            Scanner sc = new Scanner(System.in);
            int opcion = sc.nextInt();
            
            //Utilizamos la expresión switch para representar la opcion que el usuario ha escogido.
            switch (opcion) {
                case 1:
                    c = new Conexion();

                    try {
                        PreparedStatement ps = c.conectar().prepareStatement("SELECT secciones.NOMBRE, productos.DESCRIPCION, productos.PVP, productos.STOCK FROM SECCIONES JOIN PRODUCTOS ON (P_SECCION = A_SECCION) HAVING PVP>11;");
                        ResultSet rs = ps.executeQuery();
                        System.out.println("Nombre  |Descripcion| PVP |Stock");
                        while (rs.next()) {
                            System.out.println(rs.getString("NOMBRE") + "     " + rs.getString("DESCRIPCION") + "     " + rs.getString("PVP") + "  " + rs.getString("STOCK"));
                        }
                    } catch (SQLException ex) {
                        System.out.println("No se pudo conectar a la base de datos " + c.bd);
                    }
                    System.out.println("");
                    break;

                case 2:
                    c = new Conexion();

                    try {
                        PreparedStatement ps = c.conectar().prepareStatement("SELECT se.NOMBRE, AVG(PRO.PVP) AS 'PROMEDIO DE PVP' FROM SECCIONES se JOIN PRODUCTOS pro ON (P_SECCION = A_SECCION) WHERE pro.PVP > 11 GROUP BY se.NOMBRE;");
                        ResultSet rs = ps.executeQuery();
                        System.out.println("Nombre  |Promedio de PVP");
                        while (rs.next()) {
                            System.out.println(rs.getString("NOMBRE") + " " + rs.getString("PROMEDIO DE PVP"));
                        }
                    } catch (SQLException ex) {
                        System.out.println("No se pudo conectar a la base de datos " + c.bd);
                    }
                    System.out.println("");
                    break;

                case 3:
                    c = new Conexion();

                    try {
                        PreparedStatement ps = c.conectar().prepareStatement("SELECT se.NOMBRE, AVG(PRO.PVP) AS 'PROMEDIO DE PVP' FROM SECCIONES se JOIN PRODUCTOS pro ON (P_SECCION = A_SECCION) WHERE pro.PVP > 11 GROUP BY se.NOMBRE HAVING AVG(pro.PVP)>21;");
                        ResultSet rs = ps.executeQuery();
                        System.out.println("Nombre  |Promedio de PVP");
                        while (rs.next()) {
                            System.out.println(rs.getString("NOMBRE") + " " + rs.getString("PROMEDIO DE PVP"));
                        }
                    } catch (SQLException ex) {
                        System.out.println("No se pudo conectar a la base de datos " + c.bd);
                    }
                    System.out.println("");
                    break;

                case 4:
                    c = new Conexion();

                    try {
                        PreparedStatement ps = c.conectar().prepareStatement("SELECT se.NOMBRE FROM SECCIONES se JOIN PRODUCTOS pro ON (P_SECCION = A_SECCION) GROUP BY se.NOMBRE HAVING AVG(pro.PVP) = (SELECT MAX(TABLA.PROMEDIO) FROM (SELECT AVG(PVP) AS PROMEDIO FROM PRODUCTOS  GROUP BY A_SECCION)TABLA);");
                        ResultSet rs = ps.executeQuery();
                        while (rs.next()) {
                            System.out.println(rs.getString("NOMBRE"));
                        }
                        ps.close();
                    } catch (SQLException ex) {
                        System.out.println("No se pudo conectar a la base de datos " + c.bd);
                    }
                    System.out.println("");
                    break;

                case 5:

                    boolean bo = true;
                    //Creamos una estructura do-while 
                    do {
                        c = new Conexion();
                        System.out.println("Inserta la sección a consultar");
                        //Guardamos la seccion introducida por el usuario.
                        String seccion = sc.next();

                        try {
                            PreparedStatement ps = c.conectar().prepareStatement("SELECT se.NOMBRE, pro.P_PRODUCTO, pro.DESCRIPCION, c.P_CARRITO, c.FECHA, usu.P_USUARIO, usu.NOMBRE FROM CARR_PRO cr JOIN PRODUCTOS pro ON (P_PRODUCTO = A_PRODUCTO) JOIN CARRITOS c ON (P_CARRITO = A_CARRITO) JOIN SECCIONES se ON (P_SECCION = A_SECCION) JOIN USUARIOS usu ON (P_USUARIO = A_USUARIO) WHERE se.NOMBRE = '" + seccion + "'");
                            ResultSet rs = ps.executeQuery();
                            //Si la seccion existe se representan los datos de la consulta.
                            if (rs.next()) {
                                System.out.println("Nombre de Seccion|Producto|Descripcion|Cod del Carrito|Fecha del Carrito|Cod del Usuario|Nombre del Usuario");
                                while (rs.next()) {
                                    System.out.println(rs.getString(1) + "          " + rs.getInt(2) + "       " + rs.getString(3) + "        " + rs.getInt(4) + "              " + rs.getString(5) + "        " + rs.getInt(6) + "              " + rs.getString(7));
                                }
                                //la variable booleana se iguala a false para que el programa salga de la estructura do-while.
                                bo = false;
                                ps.close();
                            } else {
                            //Si la seccion no existe se le indica al usuario y vuelve a empezar el ciclo de la estructura do-while.    
                                System.out.println("No existe la sección");
                                System.out.println("");

                            }
                        } catch (SQLException ex) {
                            System.out.println("No se pudo conectar a la base de datos " + c.bd);

                        }

                    } while (bo);

                    System.out.println("");

                    break;

                case 6:
                    //Creamos un objeto del formulario y lo hacemos visible.
                    Supermercado ventana = new Supermercado();
                    ventana.setVisible(true);
                    System.out.println("");
                    break;

                case 7:
                    //Salimos del programa.
                    b = false;
                    System.exit(0);

                    break;
     
                default:
                    System.out.println("Opción erronea, indique una opción valida.");

            }
        }
    }

}
