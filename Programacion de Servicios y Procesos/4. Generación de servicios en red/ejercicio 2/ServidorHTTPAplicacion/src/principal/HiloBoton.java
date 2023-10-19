package principal;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Date;
import javax.swing.JFileChooser;
import javax.swing.JTextArea;


/**
 *
 * @author JMFC
 */
public class HiloBoton extends Thread {
    //variables locales

    private final String cadenaURL;
    JTextArea areaTexto;

    public HiloBoton(String cadenaURL, JTextArea areaTexto) {
        this.cadenaURL = cadenaURL;
        this.areaTexto = areaTexto;
    }

    @Override
    public void run() {
        //variables locales
        int leido, contentLength;
        char[] bufChar;
        byte[] bufByte;
        //
        try {
            //crea objeto url
            URL url = new URL(cadenaURL);
            //obtiene una conexión al recurso URL
            URLConnection conexion = url.openConnection();
            //se conecta pudiendo interactuar con parámetros
            conexion.connect();
            //obtiene el tipo de contenido
            String contentType = conexion.getContentType();
            //obtiene el tamaño del contenido
            contentLength = conexion.getContentLength();
            //obtiene la fecha de la  última modificación
            Date fecha = new Date(conexion.getLastModified());
            //imprimo la información de la cabecera de la URL.
            areaTexto.setText(imprimirCabecera(contentType, contentLength, fecha));
            //según el tipo de contenido...
            //...si se trata de un fichero pdf **************************************
            if (contentType.equals("application/pdf")) {
                //muestra un cuadro de diálogo modal para generar el fichero de destino
                File archivoElegido = ficheroDestino();
                //si fichero generado correctamente
                if (archivoElegido != null) {
                    //flujo de descarga desde la url
                    InputStream reader = url.openStream();
                    //flujo de escritura en el fichero
                    FileOutputStream writer = new FileOutputStream(archivoElegido.getPath());
                    //buffer intermedio ajustado al Content-Length enviado por el Servidor
                    bufByte = new byte[contentLength];
                    areaTexto.append("\nDescargando pdf en el directorio elegido...");
                    //mientras quedan bytes por leer en el buffer intermedio
                    while ((leido = reader.read(bufByte)) > 0) {
                        writer.write(bufByte, 0, leido);
                    }
                    //cierra el flujo de escritura
                    writer.close();
                    areaTexto.append("\nEl pdf ha sido descargado correctamente");
                }
            } //si se trata de texto o contenido html *******************************
            else if (contentType.startsWith("text/html")) {
                //flujo para descargar el cuerpo del texto o página html
                InputStream imputString = conexion.getInputStream();
                BufferedReader bufferedReader
                        = new BufferedReader(new InputStreamReader(imputString));
                //buffer intermedio de tamaño medio
                bufChar = new char[512];
                //
                areaTexto.append("\nEscribiendo el cuerpo de texto en la Salida...");
                areaTexto.append("\nCuerpo: \n");
                //mientras quedan caracteres por leer
                while ((leido = bufferedReader.read(bufChar)) > 0) {
                    //los escribe en la Salida
                    areaTexto.append(new String(bufChar, 0, leido));
                }
                //
                areaTexto.append("\nCuerpo de texto obtenido"
                        + " correctamente.");
            } //en cualquier otro caso **********************************************
            else {
                areaTexto.append("No sé que hacer con el tipo de "
                        + "contenido indicado.");
            }
        } catch (MalformedURLException e) {
            areaTexto.setText("URL sin sentido.");
        } catch (IOException e) {
            areaTexto.setText("Error de lectura/escritura.");
        } 
    }

    /**
     * **************************************************************************
     * muestra un cuadro de diálogo para crear un fichero pdf en la ruta
     * indicada por el usuario
     *
     * @return
     */
    private File ficheroDestino() {
        //cuadro de diálogo 'guardar como' de Java...
        JFileChooser fc = new JFileChooser();
        //...posicionado en el archivo de nombre tomado de la url
        fc.setSelectedFile(new File(cadenaURL.substring(cadenaURL.lastIndexOf("/"))
                + (cadenaURL.endsWith(".pdf") ? "" : ".pdf")));
        //muestra el cuadro de diálogo en pantalla
        int showSaveDialog = fc.showSaveDialog(null);
        //si se pulsa 'Aceptar'
        if (showSaveDialog == JFileChooser.APPROVE_OPTION) {
            //devuelve el archivo indicado por el usuario
            return fc.getSelectedFile();
        }
        //devuelve nulo
        return null;
    }

    //Creo un método para imprimir la información de la cabecera de la URL.
    private String imprimirCabecera(String contentType, int contentLength, Date fecha) {
        String str = "Encabezados destacados:\n* Content-Type: " + contentType + "\n* Content-Length: " + contentLength + "\n* Fecha de la última modificación: " + fecha;
        return str;
    }

}
