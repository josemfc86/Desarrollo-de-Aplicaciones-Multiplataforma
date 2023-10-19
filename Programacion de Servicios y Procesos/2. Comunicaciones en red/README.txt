Se crea una aplicación cliente/servidor que permite el envío de ficheros de texto (.txt) al cliente. Para ello, el cliente se conectará al servidor por el puerto 2223 y le solicitará el nombre de un fichero del servidor.

Primero, el cliente le manda al servidor el nombre del fichero.

Si el fichero existe, el servidor, le enviará el fichero al cliente y éste lo mostrará por pantalla y creará un fichero en local con el mismo nombre que el que está en el servidor.

Para realizarlo se ha tenido en cuenta lo siguiente: 

El servidor abrirá el fichero y enviará línea a línea el contenido del fichero al cliente. Cuando termine de enviar todas las líneas, el servidor enviará al cliente un mensaje de que ya ha terminado de enviar las líneas (EOF).

El cliente, leerá las líneas enviadas por el servidor y las mostrará y guardará en un fichero por pantalla hasta que se reciba el mensaje EOF.

Si el fichero no existe, el servidor le enviará al cliente un mensaje de error. Una vez que el cliente ha mostrado el fichero se finalizará la conexión.
