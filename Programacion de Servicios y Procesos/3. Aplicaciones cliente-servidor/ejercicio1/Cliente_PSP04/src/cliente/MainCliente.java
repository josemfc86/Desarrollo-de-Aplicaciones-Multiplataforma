
package cliente;

/**
 *
 * @author JMFC
 */
public class MainCliente {
static String nombreCliente = "";
    public static void main(String[] args) {
        for (int i = 0; i <= 3; i++) {
           int num =(int) ((Math.random()*(('Z'-'A')+1))+'A');
           char letra = (char)num;
           nombreCliente = nombreCliente+letra;
        }
       
        Cliente c = new Cliente(nombreCliente);
    }

}
