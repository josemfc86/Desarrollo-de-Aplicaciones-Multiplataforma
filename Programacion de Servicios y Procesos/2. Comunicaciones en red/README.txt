Se crea una aplicaci�n cliente/servidor que permite el env�o de ficheros de texto (.txt) al cliente. Para ello, el cliente se conectar� al servidor por el puerto 2223 y le solicitar� el nombre de un fichero del servidor.

Primero, el cliente le manda al servidor el nombre del fichero.

Si el fichero existe, el servidor, le enviar� el fichero al cliente y �ste lo mostrar� por pantalla y crear� un fichero en local con el mismo nombre que el que est� en el servidor.

Para realizarlo se ha tenido en cuenta lo siguiente: 

El servidor abrir� el fichero y enviar� l�nea a l�nea el contenido del fichero al cliente. Cuando termine de enviar todas las l�neas, el servidor enviar� al cliente un mensaje de que ya ha terminado de enviar las l�neas (EOF).

El cliente, leer� las l�neas enviadas por el servidor y las mostrar� y guardar� en un fichero por pantalla hasta que se reciba el mensaje EOF.

Si el fichero no existe, el servidor le enviar� al cliente un mensaje de error. Una vez que el cliente ha mostrado el fichero se finalizar� la conexi�n.
