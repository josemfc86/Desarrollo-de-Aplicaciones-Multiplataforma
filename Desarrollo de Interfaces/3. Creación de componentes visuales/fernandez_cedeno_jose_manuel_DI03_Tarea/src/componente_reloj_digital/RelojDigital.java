
package componente_reloj_digital;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.JLabel;

/**
 *
 * @author JMFC
 */
//Creamos la clase del componente que extenderá de un JLabel.
public class RelojDigital extends JLabel implements Serializable, ActionListener {

    private boolean formato24h; //Determina el formato de la hora.
    private boolean alarmaActiva; //Indica si la alarma esta activa o no.
    private int alarmaHora; //Indica la hora que el usuario asigna a la alarma.
    private int alarmaMinuto; //Indica el minuto que el usuario asigna a la alarma.
    private String alarmaMensaje; //Mensaje que el usuario asigna al panel de que aparece cuando la alarma se activa.
    private SimpleDateFormat f24h = new SimpleDateFormat("HH:mm:ss"); //Objeto que nos da la hora en formato de 24hrs.
    private SimpleDateFormat f12h = new SimpleDateFormat("hh:mm:ss a"); //Objeto que nos da la hora en formato de 12hrs.
    private AlarmaListener aListener;

    public RelojDigital() {
        Timer t = new Timer(); //Utilizamos la clase timer para que se ejecute cada segundo el código siguiente.
        t.schedule(new TimerTask() {
            @Override
            public void run() {
                Date horaActual = new Date();
                if (formato24h) { //Con este "if" determinamos si el reloj muestra la hora en formato de 24hrs o de 12hrs de aciuerdo a la eleccion que haya hecho el usuario en las propiedades.
                    setText(f24h.format(horaActual)); 
                } else {
                    setText(f12h.format(horaActual));
                }
                if (alarmaActiva) { //Igualmente con este "if" verificamos a través de método "horasCoinciden" si la hora actual y la hora de la alarma coinciden.
                    if (horasCoinciden(horaActual, getAlarmaHora(), getAlarmaMinuto())) {
                        if (aListener != null) {
                            aListener.suenaAlarma(new AlarmaEvent(this)); //Si la hora actual y la de la alarma coinciden, se dispara el evento.
                            setAlarmaActiva(false);
                        }
                    }
                }

            }

        }, 0, 1000);
    }

    // Getters y Setters de las propiedades.
    public String getAlarmaMensaje() {
        return alarmaMensaje;
    }

    public void setAlarmaMensaje(String mensaje) {
        this.alarmaMensaje = mensaje;
    }

    public boolean isFormato24h() {
        return formato24h;
    }

    public void setFormato24h(boolean formato24h) {
        this.formato24h = formato24h;
    }

    public boolean isAlarmaActiva() {
        return alarmaActiva;
    }

    public void setAlarmaActiva(boolean alarmaActiva) {
        this.alarmaActiva = alarmaActiva;
    }

    public int getAlarmaHora() {
        return alarmaHora;
    }

    public void setAlarmaHora(int horaAlarma) {
        this.alarmaHora = horaAlarma;
    }

    public int getAlarmaMinuto() {
        return alarmaMinuto;
    }

    public void setAlarmaMinuto(int minutoAlarma) {
        this.alarmaMinuto = minutoAlarma;
    }

    public AlarmaListener getaListener() {
        return aListener;
    }

    public void setaListener(AlarmaListener aListener) {
        this.aListener = aListener;
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }

    public void addAlarmaListener(AlarmaListener aListener) {
        this.aListener = aListener;
    }

    public void removeAlarmaListener(AlarmaListener aListener) {
        this.aListener = null;
    }

    public boolean horasCoinciden(Date horaActual, int horaAlarma, int minAlarma) { //Método para comparar la hora actual y la hora de la alarma usando la clase "Calendar".

        Calendar calendar = Calendar.getInstance();
        calendar.setTime(horaActual);
        int horasActual, minActual;

        horasActual = calendar.get(Calendar.HOUR_OF_DAY);
        minActual = calendar.get(Calendar.MINUTE);

        horaAlarma = getAlarmaHora(); //Obtengo la hora de la alarma que me pasan por propiedad.
        minAlarma = getAlarmaMinuto(); //obtengo los minutos de la alarma que me pasan por propiedad.

        //Compruebo si son iguales.
        if (horasActual == horaAlarma && minActual == minAlarma) {
            return true;
        } else {
            return false;

        }
    }
}
