package buzon_correo;

import static java.lang.Thread.sleep;

/**
 *
 * @author JMFC
 */
public class LectorHilo extends Thread {

    private BuzonCorreo bc;
    private String p_nombre;

    public LectorHilo(BuzonCorreo p_bc, String p_nombre) {

        bc = p_bc;
        this.p_nombre = p_nombre;
    }

    public void run() {
        //Hacemos un bucle "for" para que lea los 20 mensajes. 
        for (int i = 0; i < 20; i++) {

            System.out.println(p_nombre + " lee mensaje : " + bc.lee());
        }
        try {
            sleep((int) (Math.random() * 1500)); //Dormiremos el hilo entre "0" y "1" segundo aleatoriamente.
        } catch (InterruptedException e) {
            System.out.println("Interrupción del hilo...");
        }
        //Se indica que ha terminado de leer.
        System.out.println(p_nombre + " ha terminado de leer");
    }
}

