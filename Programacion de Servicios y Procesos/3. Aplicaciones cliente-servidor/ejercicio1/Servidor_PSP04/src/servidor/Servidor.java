
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
public class Servidor extends Thread {
    DataInputStream fEntrada;
    DataOutputStream fSalida;
    String mensaje;
    BufferedReader br;
    String nombreCliente;
    Socket miSocket;

    public Servidor(Socket Cliente) {
        this.miSocket = Cliente;
    }

    @Override
    public void run() {

        try {
                fEntrada = new DataInputStream(miSocket.getInputStream()); //Abrimos el flujo de entrada.
                nombreCliente = fEntrada.readUTF();//Recibimos el nombre del cliente.
                System.out.println("El cliente que se ha conectado es "+nombreCliente);//Mostramos por consola el nombre del cliente conectado.
                mensaje = fEntrada.readUTF();//Leemos la petición enviada por el cliente.
                fSalida = new DataOutputStream(miSocket.getOutputStream());
                File f = new File(mensaje);//Leemos el archivo.
                if (f.exists()) {//Si el archivo existe en el servidor, este se leera y enviará al cliente.
                    fSalida.writeBoolean(true);
                    br = new BufferedReader(new FileReader(mensaje));
                    String linea = "";
                    while ((linea = br.readLine()) != null) {
                        fSalida.writeUTF(linea);
                    }

                    fSalida.writeUTF("EOF");//Se le indica al cliente que la lectura del archivo ha finalizado.
                } else {
                    fSalida.writeBoolean(false);
                }
             
                miSocket.close();//Se cierra el socket del cliente.
                System.out.println("Se ha desconectado el cliente "+nombreCliente);
            
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        } 
    }
        
}
