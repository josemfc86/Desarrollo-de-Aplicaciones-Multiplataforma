package servidor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 *
 * @author JMFC
 */
public class Servidor implements Runnable {

    DataInputStream fEntrada;
    DataOutputStream fSalida;
    String mensaje;

    public Servidor() {
        Thread hilo = new Thread(this); //Creamos el hilo de ejecución.
        hilo.start();//Iniciamos el hilo.
    }

    @Override
    public void run() {

        try {
            ServerSocket servidor = new ServerSocket(2223);// Inicio la escucha del servidor en un determinado puerto

            while (true) { //Creamos un bucle "while" para que el servidor se mantenga a la escucha de cualquier petición.
                Socket miSocket = servidor.accept(); // Espero a que se conecte un cliente y creo un nuevo socket para el cliente
                fEntrada = new DataInputStream(miSocket.getInputStream()); //Abrimos el flujo de entrada.
                mensaje = fEntrada.readUTF();//Leemos la petición enviada por el cliente.
                fSalida = new DataOutputStream(miSocket.getOutputStream());
                File f = new File(mensaje);//Leemos el archivo.
                if (f.exists()) {//Si el archivo existe en el servidor, este se leera y enviará al cliente.
                    fSalida.writeBoolean(true);
                    BufferedReader br = new BufferedReader(new FileReader(mensaje));
                    String linea = "";
                    while ((linea = br.readLine()) != null) {
                        fSalida.writeUTF(linea);
                    }
                    br.close();
                    fSalida.writeUTF("Fin de lectura de archivo.");//Se le indica al cliente que la lectura del archivo ha finalizado.
                } else {
                    fSalida.writeBoolean(false);
                }
                fEntrada.close();
                fSalida.close();
                miSocket.close();//Se cierra el socket.
            }
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
