Actividad 1. Modificamos el proyecto “Comunicaciones en red” para que el servidor permita trabajar de forma concurrente con varios clientes.

Actividad 2. Se crea una aplicación cliente-servidor (multihilo), es decir, que acepte la conexión de varios clientes. El cliente enviará uno de los comandos (ls, get o exit) y el servidor actuará en consecuencia.
- ls que va al estado 2 mostrando el contenido del directorio y vuelve automáticamente al estado 1.
- get que le lleva al estado 3 donde le solicita al cliente el nombre del archivo a mostrar. Al introducir el nombre del archivo se desplaza al estado 4 donde muestra el contenido del archivo y vuelve automáticamente al estado 1.
- exit que le lleva directamente al estado donde finaliza la conexión del cliente (estado -1).
- Cualquier otro comando hace que vuelva al estado 1 solicitándole al cliente que introduzca un comando válido.

Actividad 3. A partir del ejercicio anterior se crea un servidor que una vez iniciada sesión a través de un nombre de usuario y contraseña específico (por ejemplo javier / secreta) el sistema permita ver el contenido del directorio actual, mostrar el contenido de un determinado archivo y salir.
Para realizar este ejercicio se modifico el diagrama de estados para añadir el paso previo de login con usuario y contraseña. 
