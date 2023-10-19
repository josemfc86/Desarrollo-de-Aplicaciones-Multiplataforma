Ejercicio 1.

Se desarrolla un servidor HTTP que implemente multihilo, y pueda gestionar la concurrencia de manera eficiente.
 
Ejercicio 2

El objetivo fue diseñar una aplicación Java que utilice URLConnection, simulando el comportamiento de un navegador web básico.
La aplicación tiene una interfaz gráfica Swing sencilla a partir de un formulario JFrame.
El formulario incluye una caja de texto (JTextField), un área de texto (JTextArea) y tres botones de accion (JButton).

Descripción del funcionamiento:

El programa permite introducir una URL en la caja de texto (JTextField).
Al presionar el botón "Go", la aplicación intentará conectar con la URL escrita en la caja de texto. En función del tipo MIME pdf o html descargará una página html o un fichero pdf. Más concretamente hace lo siguiente:
Si la URL no es válida, mostrará un mensaje con el error producido en el área de texto (JTextArea). El mensaje se verá a continuación de los mensajes que ya haya escritos.
Si la URL es válida, se analizará el contenido de la misma y:
Si la URL es del tipo "application/pdf", se programa la descarga del contenido del archivo pdf. 
Si la URL es del tipo "text/html", se mostrará en el área de texto (JTextArea) el contenido del documento representado en la URL.
En ambos casos la aplicación mostrará en el área de texto (JTextArea) de la aplicación la siguiente información de la cabecera de la URL:
Tipo de contenido.
Longitud.
Fecha de la última modificación.
El comportamiento del botón Go se hace en un nuevo hilo. En este caso se crea una nueva clase "HiloBoton" que hereda de Thread. En su constructor recibe la URL recogida del JTextField y el objeto JTextArea para que pueda escribir en el. 
El botón "Limpiar" se encarga de limpiar y vaciar el contenido del área y de la caja de texto (JTextArea y JTextField) del formulario.
El botón "Salir" termina la aplicación.
