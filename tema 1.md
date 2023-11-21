# Definición y estructura de programa y proceso
## Fichero ejecutable
> programa es un fichero ejecutable.

Puede ser:
- Guión o script: debe ser ejecutado por un intérprete.
- Binario: tras compilar ficheros fuentes, en Unix ELF.

### ELF
Tiene cabecera al principio del archivo con información general<br>
Tiene dos vistas<br>
- de segmentos: para decirle al kernel que porciones del fichero se cargan en memoria, donde del espacio de direcciones virtuales y con que permisos
- de secciones:
    - .text: código maquina ejecutable
    - .data: variables globales o locales estáticas inicializadas
    - .bss: variables globales y locales sin inicializar
    - .rodata: datos de lectura, cadenas literales y constantes

## Proceso
> proceso es un programa en ejecución
> un proceso es la entidad que crea al hacer una llamada al sistema fork

> todos los procesos excepto el primero, 0, son creados con fork.
es creado cuando arranca el sistema
el proceso 0 será el intercambiador (gestiona la memoria virtual) cuando haga su primer fork.
su hijo será el init que arrancará los procesos de /etc/inittab o /etc/init.

### espacio de direcciones de memoria virtual
- Segmento de texto: código máquina. Copia de la sección de texto del programa.
- Segmento de datos:
    - tamaño fijo: constantes de cadena, variables globales y locales estáticas inicializadas o no
    - tamaño variables: el heap, memoria dinámica

    - Segmento de pila: lo crea el núcleo y lo gestiona dinámicamente.
(lo de las funciones)
marco de pila se crea con las funciones, contiene los parámetros, variables locales automáticas, el return, contador de programa para poder volver y puntero de pila.
-----------------
El núcleo controla el proceso através de:
- tabla de procesos:
    - estado, localización en memoria, pid de él y padre, eventos, planificación
- área de usuario:
    - datos como: directorio trabajo, máscara de permisos


# Estados de un proceso
> modo usuario o supervisor con un bit (PS) en el microprocesador

los procesos están en modo usuario hasta que hay una interrupción y pasa a modo protegido, el kernel hace lo que sea y vuelve

## Diagrama de estados de un proceso
jeje
## Planificación de procesos 
:)
## Control de procesos
--------------
### fork
<pre>
pid_t pid;
if ( ( pid = fork() ) == -1 )
{
/** Error **/
}
else if ( pid == 0 )
{
/** Código fuente del hijo **/
}
else
{
/** Código fuente del padre **/
}
</pre>
### exit y wait
