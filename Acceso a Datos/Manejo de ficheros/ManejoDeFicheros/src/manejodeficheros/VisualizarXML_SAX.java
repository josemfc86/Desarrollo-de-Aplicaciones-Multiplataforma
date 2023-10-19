
//Visualizar todas las etiquetas del fichero BALONCESTO.XML utilizando la técnica SAX.

package manejodeficheros;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author Usuario
 */
public class VisualizarXML_SAX extends DefaultHandler { //Creamos la clase que se encargará de manejar las etiquetas de apertura, cierre, atributos y el contenido de las etiquetas. 

    private StringBuilder sb = new StringBuilder(); //Creamos una variable StringBuilder que se encargará de guardar el contenido de las etiquetas.

    @Override
    public void characters(char[] ch, int start, int length) throws SAXException { //Método sobrescrito que maneja el contenido de las etiquetas.
        sb.append(ch, start, length); //Agreagamos el contenido de la etiqueta al StringBuilder
    }

    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException { //Método sobrescrito que maneja las etiquetas de cierre.
        switch (qName) {

            case "nombre":
                System.out.println("\t\t\t\t\t" + sb); //Sacamos por pantalla el contenido de la etiqueta nombre, guardado en el StringBuilder.
                break;

            case "puntuacion":
                System.out.println("\t\t\t\t\tpuntuacion = " + sb); //Sacamos por pantalla el contenido de la etiqueta puntuacion, guardado en el StringBuilder.
                break;

            case "jornada":
                System.out.println("");
                break;

        }
    }

    @Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException { //Método sobrescrito que maneja las etiquetas de apertura y sus atributos.

        switch (qName) {
            
             case "eurobasket":
                System.out.println("eurobasket");
                break;
            case "competicion":
                System.out.println("\tcompeticion año= " + attributes.getValue("año")); //Leemos el atributo año.
                break;

            case "jornada":
                System.out.println("\t\tjornada fecha= " + attributes.getValue("fecha")); //Leemos el atributo fecha.
                break;

            case "partido":
                System.out.println("\t\t\tpartido");
                break;

            case "equipo":
                System.out.println("\t\t\t\tequipo");
                break;

            case "nombre":
                sb.delete(0, sb.length()); // En la etiqueta de apertura vaciamos el StringBuilder antes de que se ingrese un nuevo valor.
                break;

            case "puntuacion":
                sb.delete(0, sb.length());//En la etiqueta de apertura vaciamos el StringBuilder antes de que se ingrese un nuevo valor.
                break;
        }
    }

    public static void main(String[] args) throws ParserConfigurationException, SAXException, IOException {
        SAXParserFactory saxParserFactory = SAXParserFactory.newInstance();
        SAXParser saxParser = saxParserFactory.newSAXParser(); //Instanciamos el objeto SAXParser.
        File f = new File("baloncesto.xml"); //Creamos un objeto File con la ruta del fichero xml que queremos leer.
        VisualizarXML_SAX handler = new VisualizarXML_SAX();
        saxParser.parse(f, handler); // Llamamos al método parse y le pasamos el archivo xml y el manejador. 

    }

}
