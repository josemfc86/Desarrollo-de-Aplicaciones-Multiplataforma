
//A partir de los datos del fichero DEPARTAMENTOS.DAT crear un fichero llamado DEPARTAMENTOS.XML usando DOM.

package manejodeficheros;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;


/**
 *
 * @author Usuario
 */
public class CrearXML {
   public static void main(String[] args) throws FileNotFoundException, IOException, ParserConfigurationException, TransformerException {
        File fichero = new File("Departamentos.dat");
        RandomAccessFile f = new RandomAccessFile(fichero, "r");////Creamos un objeto RandomAccesFile y le pasamos como argumentos el objeto file y el modo lectura.
        int nro_dpto, pos = 0;// Nos ponemos al principio del fichero con la variable "pos".
        char nombre[] = new char[14], arr;
        char localidad[] = new char[15], arr1;
        
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();        
        DOMImplementation implementation = builder.getDOMImplementation();
        Document document = (Document) implementation.createDocument(null,"Departamentos", null);
        document.setXmlVersion("1.0"); // Versión de XML con la que vamos a trabajar.
       
        
            for (;;) { //Recorremos el fichero.
                f.seek(pos);
                nro_dpto = f.readInt();// Se obtiene identificador del número de departamento.
                for (int i = 0; i < nombre.length; i++) {
                    arr = f.readChar();// Recorremos los caracteres del nombre.
                    nombre[i] = arr;//Se guardan en un vector los caracteres del nombre.
                    
                }// Hacemos lo mismo con la localidad.
                for (int i = 0; i < localidad.length; i++) {
                    arr1 = f.readChar();
                    localidad[i] = arr1;

                }
                // Convertimos los vectores a cadenas.
                String nom = new String(nombre);
                String loc = new String(localidad);
                

                if (nro_dpto > 0) {
                    Element raiz = document.createElement("Departamento");
                    document.getDocumentElement().appendChild(raiz);
                    CrearElemento("Numero_dpto", Integer.toString(nro_dpto), raiz, document);
                    CrearElemento("Nombre", nom.trim(), raiz, document);
                    CrearElemento("Localidad", loc.trim(), raiz, document);
                }
                pos = pos + 62;// Nos posicionamos en el siguiente registro.
                if (f.getFilePointer() == f.length()) {
                    break;
                }
            }
            
            Source source = new DOMSource(document);
            Result result = new StreamResult(new java.io.File("Departamentos.xml"));  // Nombre del fichero XML.      
            Transformer transformer = TransformerFactory.newInstance().newTransformer();
            transformer.transform(source, result);
            Result console = new StreamResult(System.out);
            transformer.transform(source, console);

       
            f.close();
        
    }

    static void CrearElemento(String dat_dpto, String valor, Element raiz, Document document) {
        Element element = document.createElement(dat_dpto); // Creamos el nodo hijo.
        element.appendChild(document.createTextNode(valor)); // Le damos el valor.
        raiz.appendChild(element); // Agregamos el elemento hijo a la raiz.	
    }
       
}
