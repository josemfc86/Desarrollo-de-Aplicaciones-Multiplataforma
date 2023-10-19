package ejercicio3;

import javax.xml.xquery.XQConnection;
import javax.xml.xquery.XQDataSource;
import javax.xml.xquery.XQException;
import javax.xml.xquery.XQPreparedExpression;
import javax.xml.xquery.XQResultSequence;
import net.xqj.exist.ExistXQDataSource;

/**
 *
 * @author José Manuel Fernández Cedeño
 */
public class ExistDB {

    public static void main(String[] args) {
        try {
            //Mediante las siguientes API creamos la conexión a la base de datos "exist".
            XQDataSource ds = new ExistXQDataSource();
            //Definimos una serie de propiedades antes de establecer la conexión.
            //Configuramos puerto y servidor.
            ds.setProperty("serverName", "localhost");
            ds.setProperty("port", "8080");
            //A través del método getConnection() pasamos el usuario y contraseña para iniciar la conexión.
            XQConnection con = ds.getConnection("admin", "admin");
            //Definimos la consulta en una variable de tipo String.
            String query = "for $l in collection(/ejercicioAD)//libro "
                    + "return $l";
            //Preparamos la consulta para la su ejecución
            XQPreparedExpression expr = con.prepareExpression(query);
            //Obtenemos el resultado de nuesta consulta al ejecutarla.
            XQResultSequence result = expr.executeQuery();
            System.out.println("_____________Relación de Libros_____________");
            while (result.next()) { //Mostramos el resultado por consola.
                System.out.println(result.getSequenceAsString(null));
            }
            result.close();
            expr.close();
            //Cerramos la conexión.
            con.close();
        } catch (XQException e) {
            System.out.println("Fallo al conectar con eXist. Los datos de conexión no son válidos");
        }

    }

}
