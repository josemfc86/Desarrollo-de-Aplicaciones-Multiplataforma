package buzon_correo;

import static java.lang.Thread.sleep;

/**
 *
 * @author JMFC
 */
public class EscritorHilo extends Thread {

    private BuzonCorreo bc;//Guardamos la referencia al buzón.
    private String p_nombre;//Identificador de los nombres.
  

    public EscritorHilo(String p_nombre, BuzonCorreo p_bc) {
        bc = p_bc;
        this.p_nombre = p_nombre;
    }

    
    public void run() {//El hilo escritor invoca al método "almacena" para llenar el buzon de correo. 
        //Hacemos un bucle "for" para iscribir 20 mensajes. 
        for (int i = 0; i < 20; i++) {
            //Imprimimos el nombre y el numero de mensaje
            bc.almacena(p_nombre + " escribe mensaje numero: " + (i+1));
            try {
                sleep((int) (Math.random() * 1500)); //Dormiremos el hilo entre "0" y "1,5" segundos aleatoriamente.
            } catch (InterruptedException e) {
                System.out.println("Interrupción del hilo...");
            }
        }
        
        //Se indica que ha terminado de escribir.
        System.out.println(p_nombre + " ha terminado de escribir");
    }
}
