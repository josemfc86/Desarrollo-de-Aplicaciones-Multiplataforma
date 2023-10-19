
package buzon_correo;

/**
 *
 * @author JMFC
 */
public class Main {
     public static void main(String[] args) {

        BuzonCorreo bc = new BuzonCorreo();
        LectorHilo l1 = new LectorHilo(bc, "Vega");
        LectorHilo l2 = new LectorHilo(bc, "Pablo");
        EscritorHilo e1 = new EscritorHilo("Ana", bc);
        EscritorHilo e2 = new EscritorHilo("Javier", bc);

        l1.start();
        l2.start();
 
        e1.start();
        e2.start();
  

        try {
            l1.join();
            l2.join();

            e1.join();
            e2.join();


        } catch (InterruptedException e) {
        }

        System.out.println("\n\tFIN DE LA EJECUCIÓN");
    }
}
