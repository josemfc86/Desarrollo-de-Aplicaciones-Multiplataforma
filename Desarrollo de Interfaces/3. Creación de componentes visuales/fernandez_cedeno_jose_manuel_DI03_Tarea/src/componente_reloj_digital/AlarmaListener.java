
package componente_reloj_digital;

import java.util.EventListener;
import javax.swing.JOptionPane;

/**
 *
 * @author JMFC
 */

//Interface para que el usuario del componente implente en el método "suenaAlarma" el código que desee cuando se dispare el evento.
public interface AlarmaListener extends EventListener { 
    
    public void suenaAlarma(AlarmaEvent ev);
    
}
