
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
public class ServidorAutomata2 extends Thread {

    DataInputStream fEntrada;
    DataOutputStream fSalida;
    String comando, usuario, contrasena;
    String usuarioClave = "javier";
    String contrasenaClave = "secreta";
    BufferedReader br;
    String nombreCliente;
    Socket miSocket;
    boolean acceso = false;
    int estado;

    public ServidorAutomata2(Socket Cliente) {
        this.miSocket = Cliente;
    }

    @Override
    public void run() {

        try {
            fEntrada = new DataInputStream(miSocket.getInputStream()); //Abrimos el flujo de entrada.
            fSalida = new DataOutputStream(miSocket.getOutputStream()); //Abrimos el flujo de salida.
            fSalida.writeUTF("Inicie sesion");
            estado = 1;

            do {
                switch (estado) {

                    case 1:
                        fSalida.writeUTF("Introduzca usuario"); //
                        usuario = fEntrada.readUTF().trim();
                        fSalida.writeUTF("Introduzca contraseña");
                        contrasena = fEntrada.readUTF().trim();

                        if (usuario.equals(usuarioClave) && contrasena.equals(contrasenaClave)) { //Verificamos usuario y contraseña.
                            estado = 2; //Si son correctos pasamos al estado 2.
                            fSalida.writeBoolean(true); //Le indicamos al cliente que el acceso es correcto.
                            nombreCliente = fEntrada.readUTF();//Recibimos el nombre del cliente.
                            System.out.println("Se ha conectado el cliente " + nombreCliente);//Mostramos por consola el nombre del cliente conectado.
                        } else {
                            estado = 1; //En caso de que el usuario o contraseña sean incorrectos denegamos el acceso y volvemos a pedir las credenciales.
                            fSalida.writeBoolean(false); //Le indicamos al cliente que el acceso no ha sido exitoso.
                            fSalida.writeUTF("El usuario o contrasena son incorrectos.");
                        }

                        break;

                    case 2:
                        fSalida.writeUTF("Introduce comando (ls/get/exit)"); //Le pedimos al cliente el comando.
                        comando = fEntrada.readUTF(); //Recibimos el comando

                        if (comando.equals("ls")) {
                            fSalida.writeBoolean(true);
                            System.out.println("\tEl cliente quiere ver el contenido del directorio");
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

                            estado = 2;
                            break;
                        } else if (comando.equals("get")) {
                            fSalida.writeBoolean(true);
                            // Voy al estado 3 para mostrar el fichero
                            estado = 4;
                            break;
                        } else {
                            estado = 2;
                            if (comando.equals("exit")) {
                                fSalida.writeBoolean(true);
                                estado = -1; //Si el usuario ha ingresado el comando "exit", pasamos al estado -1.
                            } else {
                                fSalida.writeBoolean(false); //En caso de que no se haya ingresado ningún comando válido, se lo notificamos al cliente.
                            }
                        }
                        break;

                    case 4://voy a mostrar el archivo
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
                        estado = 2;
                        break;
                }
       
            } while (estado != -1);

            miSocket.close();//Se cierra el socket del cliente.
            System.out.println("Se ha desconectado el cliente " + nombreCliente);

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
                new ServidorAutomata2(miSocket).start(); //Atiendo al cliente mediante un thread.
            }
        } catch (Exception e) {;
        }
    }
}
