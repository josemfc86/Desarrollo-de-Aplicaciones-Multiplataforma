package cliente;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.util.Scanner;

/**
 *
 * @author JMFC
 */
public class Cliente {

    private Scanner sc;
    private String mensaje;
    DataOutputStream fSalida;
    DataInputStream fEntrada;

    public Cliente() {
        try {
            Socket miSocket = new Socket("localhost", 2223); //Creamos un socket a nivel local y que se comuncarar con el servidor por el puerto "2223".
            sc = new Scanner(System.in); //Iniciamos un scanner par solicitarle al usuario el nombre del archivo que quiere buscar en el servidor
            System.out.println("Introduzca la ruta del archivo a buscar.");
            mensaje = sc.nextLine();
            fSalida = new DataOutputStream(miSocket.getOutputStream()); //Creamos el flujo de salida.
            fSalida.writeUTF(mensaje);//Enviamos el mensaje al servidor.
            fEntrada = new DataInputStream(miSocket.getInputStream()); //Creamos el flujo de entrada.
            boolean existe = fEntrada.readBoolean(); //Leemos el booleano que nos envía el servidor indicandonos si existe o no el archivo solictado por el usuario.

            if (existe) { //Si el archivo existe  creamos un nuevo fichero con el nombre indicado por el usuario y escribimos la información enviada desde el servidor.

                BufferedWriter escritor = new BufferedWriter(new FileWriter(mensaje));//Buffer para escribir la información.
                String lectura = fEntrada.readUTF(); //Leemos la información enviada por el servidor.
                while ((!lectura.equalsIgnoreCase("Fin de lectura de archivo."))) //Leo el flujo de entrada hasta que el servidor me envíe el mensaje "EOF".
                {
                    System.out.println(lectura);//Mostramos por consola la información que me envía el servidor.
                    escritor.write(lectura); //Se escribe el fichero.
                    escritor.newLine();
                    escritor.flush();//Si se llena el buffer vacia y ejecuta los datos. 
                    lectura = fEntrada.readUTF();
                }
                escritor.close(); //Cerramos el fichero.
                System.out.println("Fin de lectura de archivo."); //Indicamos por consola que la lectura del fichero ha finalizado luego de recibir el mensaje "EOF" del servidor.
            } else { //Si no existe el fichero se lo indicamos por consola al usuario.
                System.out.println("Error, no existe el archivo.");
            }
            //Cerramos flujo de entrada y salida.
            fEntrada.close();
            fSalida.close();
            
            miSocket.close();//Cerramos el socket.
        } catch (IOException ex) {
            System.out.println(ex.getMessage());
        }
    }

}
