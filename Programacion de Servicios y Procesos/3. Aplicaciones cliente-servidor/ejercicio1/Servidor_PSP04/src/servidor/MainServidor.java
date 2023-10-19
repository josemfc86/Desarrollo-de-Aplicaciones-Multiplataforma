
package servidor;

import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author JMFC
 */
public class MainServidor {
    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(2223); //Inicio la escucha del servidor en un determinado puerto
            while (true) { //Creamos un bucle "while" para que el servidor se mantenga a la escucha de cualquier petición.
                // Se conecta un cliente
                Socket miSocket = servidor.accept(); //Espero a que se conecte un cliente y creo un nuevo socket para el cliente.
                System.out.println("Cliente conectado");
                new Servidor(miSocket).start(); //Atiendo al cliente mediante un thread.
            }
        } catch (Exception e) {;
        }
    }
    
}
