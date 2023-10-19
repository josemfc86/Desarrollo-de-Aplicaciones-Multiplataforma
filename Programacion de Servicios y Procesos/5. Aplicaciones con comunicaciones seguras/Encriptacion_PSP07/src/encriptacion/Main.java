
package encriptacion;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.security.InvalidKeyException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Arrays;
import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.SecretKeySpec;

/**
 *
 * @author JMFC
 */
public class Main {

    public static void main(String[] args) throws IOException {
        String textoACifrar = "Este es un texto de prueba para cifrar y luego descifrar usando el algoritmo AES.";
        String password = "josemfc86";
        //Iniciamos el FileWriter como null
        FileWriter fw = null;
        File fichero = new File("fichero");
        //Declara e incializa objeto tipo clave secreta
        SecretKeySpec skeySpec = null;

        //creamos el archivo
        if (!fichero.exists()) {
            try {
                fichero.createNewFile();

                //control de posible excepcion
            } catch (IOException ex) {
                ex.getMessage();
            }
        }
        //escribimos en el contenido
        fw = new FileWriter(fichero);
        BufferedWriter bw = new BufferedWriter(fw);
        bw.write(textoACifrar);
        bw.close();

        try {

            MessageDigest sha256 = MessageDigest.getInstance("SHA-256"); //Realizamos el hash sha-256.
            sha256.update(password.getBytes("UTF-8"));
            byte[] resumen = sha256.digest(); //Obtiene el resumen.
            //miro los bytes que tiene hashed y tiene 32Bytes.
            // System.out.println(resumen.length);

            skeySpec = crearClave(resumen); //Creamos la clave.
            File ficheroEncriptado = cifrar(skeySpec, fichero); //Ciframos el contenido del fichero y a la vez el método nos regresa el fichero cifrado.
            descifrar(skeySpec, ficheroEncriptado); //Desciframos el contenido del fichero.

        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            e.printStackTrace();
        } catch (InvalidKeyException e) {
            e.printStackTrace();
        } catch (IllegalBlockSizeException e) {
            e.printStackTrace();
        } catch (BadPaddingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    //Clave de encriptación/desencriptación
    public static SecretKeySpec crearClave(byte[] resumen) {
        try {

            byte[] raw192 = Arrays.copyOf(resumen, 24);
            SecretKeySpec skeySpec = new SecretKeySpec(raw192, "AES");
            System.out.print("La Clave es: ");
            mostrarBytes(skeySpec.getEncoded()); //metodo mostrarBytes para leer la clave
            System.out.println();
            return skeySpec;
        } catch (Exception e) {
            return null;
        }
    }
    
    //Este método nos cifra el fichero con el texto llano y nos devuelve un nuevo fichero cifrado.
    public static File cifrar(SecretKeySpec skeySpec, File fichero)
            throws NoSuchPaddingException, NoSuchAlgorithmException, InvalidKeyException, IllegalBlockSizeException,
            BadPaddingException, IOException {
        FileInputStream fe = null; //fichero de entrada
        FileOutputStream fs = null; //fichero de salida
        int bytesLeidos;
        //Se Crea el objeto Cipher para cifrar, utilizando el algoritmo AES.
        try {
            Cipher cifrador = Cipher.getInstance("AES/ECB/PKCS5Padding");
            //Se inicializa el cifrador en modo CIFRADO o ENCRIPTACIÓN.
            cifrador.init(Cipher.ENCRYPT_MODE, skeySpec);

            System.out.println("\n2. Ciframos con AES/ECB/PKCS5Padding el fichero: " + fichero);
            //Encriptamos los datos.
            byte[] bufferCifrado;
            byte[] leidos = new byte[1000];
            try {
                fe = new FileInputStream(fichero); //objeto fichero de entrada

                //control de posible excepcion
            } catch (FileNotFoundException ex) {
                ex.getMessage();
            }

            fs = new FileOutputStream(fichero + ".cifrado"); //fichero de salida
            //lee el fichero de 1k en 1k y pasa los fragmentos leidos al cifrador
            bytesLeidos = fe.read(leidos, 0, 1000);

            //mientras no se llegue al final del fichero
            while (bytesLeidos != -1) {
                //pasa texto llano al cifrador y lo cifra, asignándolo a bufferCifrado
                bufferCifrado = cifrador.update(leidos, 0, bytesLeidos);

                //Graba el texto cifrado en fichero
                fs.write(bufferCifrado);
                bytesLeidos = fe.read(leidos, 0, 1000);
            }
            bufferCifrado = cifrador.doFinal(); //Completa el cifrado
            fs.write(bufferCifrado); //Graba el final del texto cifrado, si lo hay

            //Cierra ficheros
            fe.close();
            fs.close();

            return new File(fichero + ".cifrado");
            //control de posibles excepciones
        } catch (NoSuchAlgorithmException ex) {
            ex.getMessage();
            return null;
        } catch (NoSuchPaddingException ex) {
            ex.getMessage();
            return null;
        } catch (InvalidKeyException ex) {
            ex.getMessage();
            return null;
        } catch (FileNotFoundException ex) {
            ex.getMessage();
            return null;
        } catch (IOException ex) {
            ex.getMessage();
            return null;
        } catch (IllegalBlockSizeException ex) {
            ex.getMessage();
            return null;
        } catch (BadPaddingException ex) {
            ex.getMessage();
            return null;
        }
        //Se inicializa el cifrador en modo CIFRADO o ENCRIPTACIÓN
    }

    //este método nos descifra el fichero encriptado que le pasamos por parámetro.
    public static void descifrar(SecretKeySpec skeySpec, File ficheroEncriptado)
            throws IOException, NoSuchPaddingException, NoSuchAlgorithmException,
            InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        //Ahora a la inversa, leo los datos encriptados.
        try {
            //fichero de entrada
            FileInputStream fe = null;
            String descifrar = null;
            byte[] arrayDescif = null;
            fe = new FileInputStream(ficheroEncriptado);

            Cipher cifrador = Cipher.getInstance("AES/ECB/PKCS5Padding");

            //Cifrador en modo DESCIFRADO o DESENCRIPTACIÓN
            cifrador.init(Cipher.DECRYPT_MODE, skeySpec);
            System.out.println("\n... Aqui lee el fichero y lo muestra por pantalla");

            int bytesLeidos;
            byte[] buffer = new byte[1000]; //array de bytes

            //lee el fichero de 1k en 1k y pasa los fragmentos leidos al cifrador
            bytesLeidos = fe.read(buffer, 0, 1000);

            while (bytesLeidos != -1) {

                //pasa texto cifrado al cifrador y lo descifra, asignándolo a buffer
                arrayDescif = cifrador.update(buffer, 0, bytesLeidos);
                bytesLeidos = fe.read(buffer, 0, 1000);
            }

            descifrar = new String(arrayDescif) + new String(cifrador.doFinal());
            System.out.println("\n3. Leemos el fichero descifrado:\n\n" + descifrar);

            //cierra archivos
            fe.close();

            //contorl de posibles excepciones
        } catch (FileNotFoundException ex) {
            ex.getMessage();
        } catch (NoSuchAlgorithmException ex) {
            ex.getMessage();
        } catch (NoSuchPaddingException ex) {
            ex.getMessage();
        } catch (InvalidKeyException ex) {
            ex.getMessage();
        } catch (IOException ex) {
            ex.getMessage();
        } catch (IllegalBlockSizeException ex) {
            ex.getMessage();
        } catch (BadPaddingException ex) {
            ex.getMessage();
        }

    }
    
    //Metodo para mostrarBytes que recibe un array de byte como parámetro.
    private static void mostrarBytes(byte[] buffer) {
        System.out.write(buffer, 0, buffer.length);

    }

}
