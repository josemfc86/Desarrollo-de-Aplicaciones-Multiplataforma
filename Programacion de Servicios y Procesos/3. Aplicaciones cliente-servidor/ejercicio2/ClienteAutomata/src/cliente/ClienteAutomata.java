
package cliente;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author JMFC
 */
public class ClienteAutomata {

    private Scanner sc;
    private String mensaje;
    DataOutputStream fSalida;
    DataInputStream fEntrada;
    static String nombreCliente = "";

    public ClienteAutomata(String nombreCliente) {

        try {
            Socket miSocket = new Socket("localhost", 2223); //Creamos un socket a nivel local y que se comunicara con el servidor por el puerto "2223".
            fSalida = new DataOutputStream(miSocket.getOutputStream()); //Creamos el flujo de salida.
            fEntrada = new DataInputStream(miSocket.getInputStream()); //Creamos el flujo de entrada.
            fSalida.writeUTF(nombreCliente); //Le enviamos el nombre del cliente al servidor.
            boolean salir = false;
            while (!salir) {
                mensaje = fEntrada.readUTF(); //Recibimos el mensaje del servidor.
                System.out.println(mensaje); //Mostramos el mensaje.  
                sc = new Scanner(System.in); //Iniciamos un scanner para que el usuario introduzca el comando solicitado por el servidor.
                mensaje = sc.nextLine().trim();
                fSalida.writeUTF(mensaje); //Enviamos el comando al servidor.

                boolean exito = fEntrada.readBoolean();

                while (exito == false) {
                    System.out.println("Comando invalido");
                    mensaje = fEntrada.readUTF();
                    System.out.println(mensaje);
                    mensaje = sc.nextLine().trim();
                    fSalida.writeUTF(mensaje); //Enviamos el comando al servidor.
                    exito = fEntrada.readBoolean();
                }

                switch (mensaje) {
                    case "ls":
                        int numFicheros = fEntrada.readInt();
                        for (int i = 0; i < numFicheros; i++) {
                            String nombreFicheros = fEntrada.readUTF();
                            System.out.println(nombreFicheros);
                        }
                        System.out.println("");
                        break;

                    case "get":
                        System.out.println(fEntrada.readUTF()); //El servidor nos pide el nombre del archivo.
                        mensaje = sc.nextLine();
                        fSalida.writeUTF(mensaje); //Enviamos el nombre del archivo al servidor.
                        boolean existe = fEntrada.readBoolean(); //Leemos el booleano que nos envía el servidor indicandonos si existe o no el archivo solicitado por el usuario.
                        if (existe) { //Si el archivo existe  creamos un nuevo fichero con el nombre indicado por el usuario y escribimos la información enviada desde el servidor.
                            String lectura = fEntrada.readUTF(); //Leemos la información enviada por el servidor.
                            while ((!lectura.equalsIgnoreCase("EOF"))) //Leo el flujo de entrada hasta que el servidor me envíe el mensaje "EOF".
                            {
                                System.out.println(lectura);//Mostramos por consola la información que me envía el servidor.
                                lectura = fEntrada.readUTF();
                            }
                            System.out.println("Fin de lectura del archivo."); //Indicamos por consola que la lectura del fichero ha finalizado luego de recibir el mensaje "EOF" del servidor.
                            System.out.println("");
                            salir = false;
                        } else { //Si no existe el fichero se lo indicamos por consola al usuario.
                            System.out.println("Error, no existe el archivo.");
                            System.out.println("");
                            salir = false;
                        }
                        break;

                    case "exit":
                        salir = true; //Salimos de la aplicación.
                        break;
                }
            }

            //Cerramos flujo de entrada y salida.
            fEntrada.close();
            fSalida.close();

            miSocket.close();//Cerramos el socket.
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static void main(String[] args) {
        for (int i = 0; i <= 3; i++) {
            int num = (int) ((Math.random() * (('Z' - 'A') + 1)) + 'A');
            char letra = (char) num;
            nombreCliente = nombreCliente + letra;
        }

        ClienteAutomata c = new ClienteAutomata(nombreCliente);
    }
}
