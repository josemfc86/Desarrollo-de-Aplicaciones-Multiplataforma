
/*Crear un fichero DEPARTAMENTOS.DAT de acceso aleatorio, que contenga al menos cinco empleados.
Dicho fichero contendrá los campos siguientes: NUMERO_DPTO (int), NOMBRE (string), LOCALIDAD (sTRING).*/

package manejodeficheros;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;

/**
 *
 * @author Usuario
 */

public class Departamento { //Creamos la clase "Departamento" que nos servira como objeto para guardar la información de cada departamento.

    private int numero_dpto;
    private String nombre, localidad;

    public Departamento(int numero_dpto, String nombre, String localidad) {
        this.numero_dpto = numero_dpto;
        this.nombre = nombre;
        this.localidad = localidad;
    }

    public int getNumero_dpto() {
        return numero_dpto;
    }

    public void setNumero_dpto(int numero_dpto) {
        this.numero_dpto = numero_dpto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getLocalidad() {
        return localidad;
    }

    public void setLocalidad(String localidad) {
        this.localidad = localidad;
    }

    public static void main(String[] args) {
        File f = new File("Departamentos.dat"); //Creamos un objeto File con la ruta y el nombre del documento que queremos crear.
        //Creamos un ArrayList donde guardaremos la informacion de cada departamento en un objeto.
        ArrayList<Departamento> departamentos = new ArrayList<>();
        //Agregamos los objetos "Departamentos" al ArrayList.
        departamentos.add(new Departamento(1, "Gerencia", "Santander"));
        departamentos.add(new Departamento(2, "Operaciones", "Castro Urdiales"));
        departamentos.add(new Departamento(3, "Administracion", "Bilbao"));
        departamentos.add(new Departamento(4, "Finanzas", "Renedo"));
        departamentos.add(new Departamento(5, "Logistica", "Santander"));

        try (RandomAccessFile acceso = new RandomAccessFile(f, "rw")) { //Creamos un objeto RandomAccesFile y le pasamos como argumentos el objeto file y el modo lectura y escritura.

            //Recorremos el ArrayList y vamos escribiendo el fichero.
            for (Departamento d : departamentos) {
                acceso.writeInt(d.getNumero_dpto());
                StringBuilder sb = new StringBuilder(d.getNombre());
                sb.setLength(14); //Establecemos los 14 caracteres que necesito para el nombre.  
                acceso.writeChars(sb.toString());
                StringBuilder sb2 = new StringBuilder(d.getLocalidad());
                sb2.setLength(15); //Establecemos los  15 caracteres que necesito para la localidad.
                acceso.writeChars(sb2.toString());

            }
            // 4+28+30=62 bytes para cada registro; 4 bytes de int, 28 del String del nombre y 30 bytes de la localidad.
            // La suma de bytes para posicionarnos en el siguiente alumno es 62, siendo 0 el primero, 62 la segunda posicion y asi sucesivamente. 
            
           
            // Llamando al método "acceso.seek(0)" podemos posicionarnos en el registro que queramos y con el código que sigue podemos leer por consola dicho registro.
            
            
            System.out.println(acceso.readInt());

            StringBuilder sb3 = new StringBuilder("");
            for (int i = 0; i < 14; i++) {
                sb3.append(acceso.readChar());
            }
            System.out.println(sb3);

            StringBuilder sb4 = new StringBuilder("");
            for (int i = 0; i < 15; i++) {
                sb4.append(acceso.readChar());
            }
            System.out.println(sb4);

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
            System.out.println(e.getMessage());
        } catch (IOException e) {

        }

    }

}
