Actividad 1. Modificamos el proyecto �Comunicaciones en red� para que el servidor permita trabajar de forma concurrente con varios clientes.

Actividad 2. Se crea una aplicaci�n cliente-servidor (multihilo), es decir, que acepte la conexi�n de varios clientes. El cliente enviar� uno de los comandos (ls, get o exit) y el servidor actuar� en consecuencia.
- ls que va al estado 2 mostrando el contenido del directorio y vuelve autom�ticamente al estado 1.
- get que le lleva al estado 3 donde le solicita al cliente el nombre del archivo a mostrar. Al introducir el nombre del archivo se desplaza al estado 4 donde muestra el contenido del archivo y vuelve autom�ticamente al estado 1.
- exit que le lleva directamente al estado donde finaliza la conexi�n del cliente (estado -1).
- Cualquier otro comando hace que vuelva al estado 1 solicit�ndole al cliente que introduzca un comando v�lido.

Actividad 3. A partir del ejercicio anterior se crea un servidor que una vez iniciada sesi�n a trav�s de un nombre de usuario y contrase�a espec�fico (por ejemplo javier / secreta) el sistema permita ver el contenido del directorio actual, mostrar el contenido de un determinado archivo y salir.
Para realizar este ejercicio se modifico el diagrama de estados para a�adir el paso previo de login con usuario y contrase�a. 
