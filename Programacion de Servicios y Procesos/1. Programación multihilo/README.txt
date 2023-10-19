Realización de un programa en java que simula un buzón de correo (recurso compartido), de forma que se pueda enviar o leer un mensaje. El buzón sólo puede almacenar un mensaje, de manera que para poder escribir se debe encontrar vacío y para poder leer debe estar lleno. Se crean varios hilos lectores y escritores que manejen dicho buzón de forma sincronizada.

Clases necesarias:

LectorHilo: codificamos las instrucciones que deben ejecutar los hilos encargados de leer los mensajes del recurso compartido buzón.

EscritorHilo: donde codificaremos las instrucciones que deben ejecutar los hilos encargados de escribir mensajes en el recurso compartido buzón.

BuzonCorreo: donde encapsularemos el recurso compartido buzón y haremos visibles los métodos disponibles por los lectores y escritores para poder acceder de forma sincronizada al buzón. La clase buzónCorreo así concebida posee la estructura de un monitor.

En el programa principal:

Se instancia un objeto BuzonCorreo y varios LectorHilo y EscritorHilo, respectivamente. Se prueba con dos de cada. Se arrancan los hilos y espera a que terminen.

Al final, el programa principal imprimirá la frase "FIN DE LA EJECUCIÓN". 

