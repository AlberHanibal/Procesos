## Protocolos

- **Transferencia de Hipertexto (HTTP)**: define el contenido, el formato de las solicitudes y despuestas intercambiadas entre el cliente y el servidor.
- **Control de transmisión (TCP)**: se encarga de garantizar la entrega fiable de la información y gestionar el control de flujo.
- **Protocolo de Internet (IP)**: es responsable de entregar los mensajes del remitente al receptor.
- **Ethernet**: es responsable de la entrega de mensajes en la misma red de área local.

### Encapsulamiento de datos

El formato de los datos en una capa se llama **unidad de datos del protocolo (PDU)**
- Capa aplicación: datos
- Capa transporte: segmentos o datagramas
- Capa internet: paquete o datagrama-IP
- Capa acceso a la red: trama

## Capa de red

Servicios para permitir transportar información de un host a otro host a través de redes. IPv4 e IPv6.
Utilizan direccionamiento de dispositivos finales, encapsulación, enrutamiento y desencapsulación.

## Capa de transporte

Utiliza el número de puerto.

#### TCP
Protocolo orientado a conexión. Confiable. Operaciones:
- enumera y rastrea segmentos de datos.
- confirma datos recibidos.
- retransmite información no reconocida después de un tiempo.
- secuencias datos que llegan en orden incorrecto.
- enviar datos a velocidad eficiente.

#### UDP
Protocolo sin conexión. Rápido, baja sobrecarga, no reconocimiento, no reenvia datos perdidos, entrega datos a medida que llegan.

### Números de puertos
Autoridad de Números Asignados de Internet (IANA)
- **Puertos bien conocidos (0-1023)**: reservados para servicios comunes y aplicaciones nav. web, correo electrónico y acceso remoto.
- **Puertos registrados (1024-49151)**: asignados por IANA para procesos o aplicaciones específicos.
- **Puertos dinámicos o efímeros (49152-65535)**: asignados dinámicamente por el SO para identificar la aplicación del cliente durante la comunicación.

## Capa de aplicación
nada

## Comunicación punto a punto
Tres opciones de entrega de mensajes:
- Unidifusión, unicast. 2 modelos: cliente-servidor y P2P.
- Multidifusión, multicast.
- Amplia difusión, broadcast.

### Modelo entre iguales, P2P - Peer to Peer
Una aplicación P2P funciona simultáneamente como cliente y servidor.
Protocolos Gnutella y BitTorrent.

# Sockets
Representa un extremo de una comunicación bidireccional.

## Dominios y direcciones
Dominio de comunicación como una familia de protocoles que se puede emplear para conseguir el intercambio de datos entre sockets. (PF protocol family)
- **Dominio Unix**(PF_UNIX o PF_LOCAL): comunicación entre procesos dentro de la misma máquina.
- **Dominio Internet**(PF_INET): comunicación entre 2 computadores conectados mediante los protocolos de internet (TCP-UDP / IP).
Cada socket está asociado a un dominio y solo se pueden comunicar del mismo dominio. (AF address family)
- **Formato Unix**(AF_UNIX): una dirección de socket es un nombre de fichero.
- **Formato Internet**(AF_INET): tres campos: dirección IP, protocolo (TCP o UDP) y puerto correspondiente a ese protocolo.

## Estilos de comunicación
- **Orientado a conexión**: pasa por 3 fases: apertura conexión, intercambio de datos y cierre de conexión. El intercambio de datos es por bytes que se reciben de forma fiable y ordenada. La conexión es permanente. SOCK_STREAM
- **Sin conexión**: comunicación con datagramas. El emisor envia mensajes autónomos, autocontenidos. SOCK_DGRAM

## Escenario cliente/servidor
- **Servidores interactivos**: el servidor recoge la petición y la atiende. Si tarda en atenderla puede originar tiempos de espera grandes.
- **Servidores concurrentes**: el servidor recoge la petición y crea procesos o hilos que los atienden. Recomendable en tiempos de servicios variables.

### Orientado a conexión
Llamadas al sistema.
- **socket**: crea un socket.
- **bind**: asigna una dirección local. El cliente no suele hacer bind, se le dará una dirección automáticamente en su primer uso (connect o sendto). El servidor si lo usa para poder decir al mundo su dirección, puerto.
- **connect**: el cliente realiza una solicitud de conexión a un servidor.
- **listen**: el servidor prepara el socket para aceptar conexiones (lo convierte en socket de escucha).
- **accept**: el proceso espera una petición de conexión. Cuando recibe la petición devuelve un nuevo socket (de diálogo) conectado al socket del cliente. El socket de escucha queda libre.
- **send/write**: envian datos por el socket.
- **recv/read**: reciben datos por el socket.
- **close**: cierra un socket tanto en sentido entrante como saliente.

### Sin conexión
- **sendto y recvfrom**: tienen que indicar el socket destino para send e informar el socket origen para recv.
- se puede hacer un connect para proporcionar la dirección del socket destino para poder usar send/write.


# Sockets en Java
- TPC: clases `Socket` y `ServerSocket`.
- UDP: clases `DatagramSocket` y `DatagramPacket`.

## Cliente TCP
[ClienteEcho](https://github.com/AlberHanibal/Procesos/blob/master/Tema%205/TCPEchoClient/src/TCPEchoClient.java), [ClienteSencillo](https://github.com/AlberHanibal/Procesos/blob/master/Tema%203/Ejercicios/cliente/src/cliente/Cliente.java), [ClienteFotos](https://github.com/AlberHanibal/Procesos/blob/master/Tema%205/ImageClient/src/ImageClient.java)

El constructor de `Socket` pide el host (nombre dominio o dirección IP) y puerto. Lanza IOException si el servidor no escucha en ese puerto. `Socket` implementa la interfaz `Autocloseable`, se puede poner en un try-con-recursos.
Se puede usar el constructor vacío y darle dirección después.
```
try {
Socket socket = new Socket();
...
SocketAddress direccion = new InetSocketAddress("time.nist.gov", 13);
socket.connect(direccion);
...
} catch (IOException ex) {
System.err.println(ex);
}

```
El tiempo máximo de espera se puede introducir desde `socket.connect(direccion, timeout))` o desde `socket.setSoTimeout(milisegundos)`