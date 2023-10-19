Ejercicio 1.

Se desarrolla un servidor HTTP que implemente multihilo, y pueda gestionar la concurrencia de manera eficiente.
 
Ejercicio 2

El objetivo fue dise�ar una aplicaci�n Java que utilice URLConnection, simulando el comportamiento de un navegador web b�sico.
La aplicaci�n tiene una interfaz gr�fica Swing sencilla a partir de un formulario JFrame.
El formulario incluye una caja de texto (JTextField), un �rea de texto (JTextArea) y tres botones de accion (JButton).

Descripci�n del funcionamiento:

El programa permite introducir una URL en la caja de texto (JTextField).
Al presionar el bot�n "Go", la aplicaci�n intentar� conectar con la URL escrita en la caja de texto. En funci�n del tipo MIME pdf o html descargar� una p�gina html o un fichero pdf. M�s concretamente hace lo siguiente:
Si la URL no es v�lida, mostrar� un mensaje con el error producido en el �rea de texto (JTextArea). El mensaje se ver� a continuaci�n de los mensajes que ya haya escritos.
Si la URL es v�lida, se analizar� el contenido de la misma y:
Si la URL es del tipo "application/pdf", se programa la descarga del contenido del archivo pdf. 
Si la URL es del tipo "text/html", se mostrar� en el �rea de texto (JTextArea) el contenido del documento representado en la URL.
En ambos casos la aplicaci�n mostrar� en el �rea de texto (JTextArea) de la aplicaci�n la siguiente informaci�n de la cabecera de la URL:
Tipo de contenido.
Longitud.
Fecha de la �ltima modificaci�n.
El comportamiento del bot�n Go se hace en un nuevo hilo. En este caso se crea una nueva clase "HiloBoton" que hereda de Thread. En su constructor recibe la URL recogida del JTextField y el objeto JTextArea para que pueda escribir en el. 
El bot�n "Limpiar" se encarga de limpiar y vaciar el contenido del �rea y de la caja de texto (JTextArea y JTextField) del formulario.
El bot�n "Salir" termina la aplicaci�n.
