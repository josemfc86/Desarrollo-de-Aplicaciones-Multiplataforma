
//Visualizar todas las etiquetas del fichero BALONCESTO.XML utilizando la técnica DOM.

package manejodeficheros;

import java.io.File;
import java.io.IOException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Usuario
 */
public class VisualizarXML_DOM {

    public static void main(String[] args) throws SAXException, IOException, ParserConfigurationException {
        try {
            //Creo una instancia de DocumentBuilderFactory.
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            //Obtengo el documento, a partir del XML.
            Document documento = builder.parse(new File("baloncesto.xml"));
            //Normalizo el documento.
            documento.getDocumentElement().normalize();

            System.out.println(documento.getDocumentElement().getNodeName());
            //Creo una lista de los nodos "competicion".
            NodeList competiciones = documento.getElementsByTagName("competicion");
            //Recorro la lista.
            for (int i = 0; i < competiciones.getLength(); i++) {
                Node n = competiciones.item(i);
                //Pregunto si el nodo "competicion" es de tipo elemento.
                if (n.getNodeType() == Node.ELEMENT_NODE) {
                    Element e = (Element) n;
                    //Mostramos el elemento "competicion" y su atributo.
                    System.out.println(n.getNodeName() + " año " + " = " + e.getAttribute("año"));
                    // Obtengo sus hijos y los recorro.
                    NodeList hijos = n.getChildNodes();
                    for (int j = 0; j < hijos.getLength(); j++) {
                        Node hijo = hijos.item(j);
                        //Pregunto si el nodo "jornada" es de tipo elemento.
                        if (hijo.getNodeType() == Node.ELEMENT_NODE) {
                            Element e1 = (Element) hijo;
                            //Mostramos el elemento "jornada" y su atributo.
                            System.out.println(hijo.getNodeName() + " fecha " + " = " + e1.getAttribute("fecha"));
                            // Obtengo sus hijos y los recorro.
                            NodeList hijos1 = hijo.getChildNodes();
                            for (int k = 0; k < hijos1.getLength(); k++) {
                                Node hijo1 = hijos1.item(k);
                                //Pregunto si el nodo "partido" es de tipo elemento.
                                if (hijo1.getNodeType() == Node.ELEMENT_NODE) {
                                    //Mostramos el elemento "partido".
                                    System.out.println(hijo1.getNodeName());
                                    // Obtengo sus hijos y los recorro.
                                    NodeList hijos2 = hijo1.getChildNodes();
                                    for (int l = 0; l < hijos2.getLength(); l++) {
                                        Node hijo2 = hijos2.item(l);
                                        //Pregunto si el nodo "equipo" es de tipo elemento.
                                        if (hijo2.getNodeType() == Node.ELEMENT_NODE) {
                                            //Mostramos el elemento "equipo".
                                            System.out.println(hijo2.getNodeName());
                                            // Obtengo sus hijos y los recorro.
                                            NodeList hijos3 = hijo2.getChildNodes();
                                            for (int m = 0; m < hijos3.getLength(); m++) {
                                            Node hijo3 = hijos3.item(m);
                                            //Pregunto si los nodos "nombre" y "puntuacion" son de tipo elemento.
                                            if (hijo3.getNodeType() == Node.ELEMENT_NODE) {
                                            //Mostramos los elementos "nombre", "puntuacion" y sus valores.
                                            System.out.println(hijo3.getNodeName() + " " + hijo3.getTextContent() );
                                            }
                                            }
                                        }
                                    }

                                }
                                System.out.println();

                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}

