
package buzon_correo;

/**
 *
 * @author JMFC
 */
public class BuzonCorreo {
    private String mensaje = ""; //Almacenamos el contenido de los mensajes.
    private boolean lleno = true; //Indica si hay algún mensaje disponible para ser leído. 
 
    
    //Con este metodo almacenamos un mensaje en el buzón siempre que esté vacío.
    public synchronized void almacena(String men) {
        
     while(lleno){     
         try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Interrupcion del hilo...");
            }
        }
        lleno = true;
        mensaje = men;
        //Cuando el buzón este vacío, lo notifico.
        notifyAll();
     }
    
    /*Método que lee el mensaje si el buzon esta lleno, si no lo esta, se bloquea el hilo hasta que se llene el buzón.
     Cuando está lleno, sale del bloqueo, deja vacío el buzon, muestra el mensaje  y notifica a los procesos que puedan estar bloqueados esperando para llenarlo.*/
    public synchronized String lee() {
        while (!lleno) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("Interrupcion del hilo...");
            }
        }
        lleno = false;
        notifyAll();
        return mensaje;
    }
}
