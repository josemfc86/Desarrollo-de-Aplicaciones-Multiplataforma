
package componente_reloj_digital;

import java.util.EventObject;

/**
 *
 * @author JMFC
 */

//Clase que implementa los eventos para gestionar la comunicación entre los objetos.
public class AlarmaEvent extends EventObject {
    
    public AlarmaEvent(Object source) {
        super(source);
     
    }
    
}
