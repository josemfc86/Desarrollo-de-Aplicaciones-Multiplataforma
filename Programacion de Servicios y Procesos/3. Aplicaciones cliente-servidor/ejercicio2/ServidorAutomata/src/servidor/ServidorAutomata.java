
package servidor;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

/**
 *
 * @author JMFC
 */
public class ServidorAutomata extends Thread {

    DataInputStream fEntrada;
    DataOutputStream fSalida;
    String comando;
    BufferedReader br;
    String nombreCliente;
    Socket miSocket;
    int estado;

    public ServidorAutomata(Socket Cliente) {
        this.miSocket = Cliente;
    }

    @Override
    public void run() {

        try {
            fEntrada = new DataInputStream(miSocket.getInputStream()); //Abrimos el flujo de entrada.
            nombreCliente = fEntrada.readUTF();//Recibimos el nombre del cliente.
            System.out.println("Se ha conectado el cliente " + nombreCliente);//Mostramos por consola el nombre del cliente conectado.
            fSalida = new DataOutputStream(miSocket.getOutputStream());

            estado = 1;

            do {
                switch (estado) {

                    case 1:
                        fSalida.writeUTF("Introduce comando (ls/get/exit)"); //Le pedimos al cliente el comando.
                        comando = fEntrada.readUTF(); //Recibimos el comando

                        if (comando.equals("ls")) {
                            fSalida.writeBoolean(true);
                            System.out.println("\tEl cliente " +nombreCliente+" quiere ver el contenido del directorio");
                            File directorio = new File("./directorio");//Ingresamos en el directorio.
                            File[] ficheros = directorio.listFiles();
                            ArrayList<String> nombresFicheros = new ArrayList<>();
                            for (int i = 0; i < ficheros.length; i++) {
                                nombresFicheros.add(ficheros[i].getName());
                            }
                            fSalida.writeInt(nombresFicheros.size());

                            for (String a : nombresFicheros) {
                                fSalida.writeUTF(a);
                            }

                            estado = 1;
                            break;
                        } else if (comando.equals("get")) {
                            fSalida.writeBoolean(true);
                            // Voy al estado 3 para mostrar el fichero
                            estado = 3;
                            break;
                        } else {
                            estado = 1;
                            if (comando.equals("exit")) {
                                fSalida.writeBoolean(true);
                            } else {
                                fSalida.writeBoolean(false);
                            }
                        }
                        break;

                    case 3://voy a mostrar el archivo
                        fSalida.writeUTF("Introduzca el nombre del archivo a mostrar.");
                        String fichero = fEntrada.readUTF().trim();
                        String ruta = "./directorio/"+fichero;
                        File f = new File(ruta);//Leemos el archivo.
                        if (f.exists()) {//Si el archivo existe en el servidor, este se leera y enviará al cliente.
                            System.out.println("\tMostrando contenido del fichero '"+fichero+"' al cliente "+nombreCliente+".");
                            fSalida.writeBoolean(true);
                            br = new BufferedReader(new FileReader(ruta));
                            String linea = "";
                            while ((linea = br.readLine()) != null) {
                                fSalida.writeUTF(linea);
                            }

                            fSalida.writeUTF("EOF");//Se le indica al cliente que la lectura del archivo ha finalizado.
                        } else {
                            fSalida.writeBoolean(false);

                        }
                        estado = 1;
                        break;
                }

                if (comando.equals("exit")) {
                    estado = -1;
                }
            } while (estado != -1);

            miSocket.close();//Se cierra el socket del cliente.
            System.out.println("Se ha desconectado el cliente "+nombreCliente+".");

        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        try {
            ServerSocket servidor = new ServerSocket(2223); //Inicio la escucha del servidor en un determinado puerto
            while (true) { //Creamos un bucle "while" para que el servidor se mantenga a la escucha de cualquier petición.
                // Se conecta un cliente
                Socket miSocket = servidor.accept(); //Espero a que se conecte un cliente y creo un nuevo socket para el cliente.
                new ServidorAutomata(miSocket).start(); //Atiendo al cliente mediante un thread.
            }
        } catch (Exception e) {;
        }
    }
}
