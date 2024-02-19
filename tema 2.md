[GitHub Pages](https://pages.github.com/)

## Situaciones hilos

> *Se han identificado situaciones en las que resulte útil la utilización de varios hilos en unprograma.*

- **Trabajo interactivo y en segundo plano**: aumenta velocidad que se percibe la aplicación.
- **Procesamiento asíncrono**.
- **Aceleración de la ejecución**: se procesa un registro mientras otro hilo va leyendo otro.
- **Estructuración modular de los programas**.


## 2.1 Estados

> *Se han identificado los posibles estados de ejecución de un hilo y programado aplicaciones que los gestionen*

- **Nace**: solo declarado con el `new Thread()`.
- **Listo**: se ha hecho el `.start()`. El planificador le tiene que dar marcha.
- **Ejecutandose**: en CPU
    - **Dormido**: si se ha hecho un `sleep()`, tiempo definido.
    - **Bloqueado**: pendiente de I/O, después a listo cuando termine.
    - **Esperando**: por un `wait()`, se reanudará con un `notify(), notifyAll()`.
    - **Muerto**: porque se terminó o se llamó a `stop()`.
##### Métodos
- **`isAlive()`**: devuelve `false` si está en Nace o Muerto, true sino.
- **`yield()`**: indica a la JVM que el hilo está dispuesto a ceder la CPU, no garantizado.
- **`join()`**: hilo se queda esperando hasta que otro hilo termine.

## 2.2 Creación y ejecución de hilos
> *Se han reconocido los mecanismos para crear, iniciar y finalizar hilos*
- Extendiendo la clase `Thread` y sobreescribiendo el método `run()`. [1](https://github.com/AlberHanibal/Procesos/blob/master/Tema%203/Ejercicios/E-PSP-T3-2.4-A/src/com/packtpub/java7/concurrency/chapter1/recipe3/task/PrimeGenerator.java) [2](https://github.com/AlberHanibal/Procesos/blob/master/Tema%203/Ejercicios/E-PSP-T3-2.4-A/src/com/packtpub/java7/concurrency/chapter1/recipe3/core/Main.java)
- Creando clase que implemente la interfaz `Runnable` y después usarlo como parámetro en el constructor de `Thread`. [1](https://github.com/AlberHanibal/Procesos/blob/master/Tema%203/Ejercicios/E-PSP-T3-2.2/src/com/packtpub/java7/concurrency/chapter1/recipe1/core/Main.java) [2](https://github.com/AlberHanibal/Procesos/blob/master/Tema%203/Ejercicios/E-PSP-T3-2.2/src/com/packtpub/java7/concurrency/chapter1/recipe1/task/Calculator.java)

## 2.3 Atributos hilo
> *Se ha establecido y controlado la prioridad de cada uno de los hilos de ejecución.*
- **ID**: `hilo.getId()`
- **Nombre**: `hilo.getName() y setName()`
- **Prioridad**: un hilo hereda la prioridad.
    - 1 `Thread.MIN_PRIORIRY`
    - 5 `Thread.NORM_PRIORITY`
    - 10 `Thread.MAX_PRIORITY`
- **Estado**: `hilo.getState()`
A los atributos se accede con `Thread.currentThread()` que devuelve el `Thread`
[1](https://github.com/AlberHanibal/Procesos/blob/master/Tema%203/Ejercicios/E-PSP-T3-2.3/src/com/packtpub/java7/concurrency/chapter1/recipe2/core/Main.java) [2](https://github.com/AlberHanibal/Procesos/blob/master/Tema%203/Ejercicios/E-PSP-T3-2.3/src/com/packtpub/java7/concurrency/chapter1/recipe2/task/Calculator.java)

## 2.4 Interrumpiendo ejecución y 2.5 Durmiendo
> *Estados de ejecución*. 

La clase `Theread` tiene un boolean de si le han interrumpido o no. Con el método `interrupt()` se pone a true (el set) y para verlo es `isInterrupted()` (el get). Pueden decidir si hacerlo o no.
Salta el `InterruptedException` si quieres con el `isInterrupted()` capturándola en el `run()`. También la lanza `sleep()`.
[1](https://github.com/AlberHanibal/Procesos/blob/master/Tema%203/Ejercicios/E-PSP-T3-2.4-A/src/com/packtpub/java7/concurrency/chapter1/recipe3/task/PrimeGenerator.java) [2](https://github.com/AlberHanibal/Procesos/blob/master/Tema%203/Ejercicios/E-PSP-T3-2.4-A/src/com/packtpub/java7/concurrency/chapter1/recipe3/core/Main.java)
[1](https://github.com/AlberHanibal/Procesos/blob/master/Tema%203/Ejercicios/E-PSP-T3-2.4-B/src/com/packtpub/java7/concurrency/chapter1/recipe4/task/FileSearch.java) [2](https://github.com/AlberHanibal/Procesos/blob/master/Tema%203/Ejercicios/E-PSP-T3-2.4-B/src/com/packtpub/java7/concurrency/chapter1/recipe4/core/Main.java)
[1](https://github.com/AlberHanibal/Procesos/blob/master/Tema%203/Ejercicios/E-PSP-T3-2.5/src/com/packtpub/java7/concurrency/chapter1/recipe5/task/FileClock.java) [2](https://github.com/AlberHanibal/Procesos/blob/master/Tema%203/Ejercicios/E-PSP-T3-2.5/src/com/packtpub/java7/concurrency/chapter1/recipe5/core/Main.java)

## 2.6 Esperando la finalización de otro hilo
> *Estados de ejecución*. 

`hiloQueQuieresEsperar.join()`, se puede dar parámetro de tiempo máximo esperado.
[1](https://github.com/AlberHanibal/Procesos/blob/master/Tema%203/Ejercicios/E-PSP-T3-2.6/src/com/packtpub/java7/concurrency/chapter1/recipe6/core/Main.java) [2](https://github.com/AlberHanibal/Procesos/blob/master/Tema%203/Ejercicios/E-PSP-T3-2.6/src/com/packtpub/java7/concurrency/chapter1/recipe6/task/NetworkConnectionsLoader.java) [3](https://github.com/AlberHanibal/Procesos/blob/master/Tema%203/Ejercicios/E-PSP-T3-2.6/src/com/packtpub/java7/concurrency/chapter1/recipe6/task/DataSourcesLoader.java) 

## 2.7 Hilos demonio
- Prioridad muy baja.
- Si solo hay demonio o hilos de usuario se termina el programa.
- `setDeamon() y isDaemon()`.


## 2.8 Procesamiento de excepciones

- **Comprobadas**: deben ser especificadas en la cláusula `throws` o capturadas.
- **No comprobadas**: para evitar que terminen hay que implementar una clase para su tratamiento.
    - Implementando la interfaz `UncaughtExceptionHandler` y el método `uncaughtException()`.
    - Después `hilo.setUngaughtExceptionHandler(new ExceptionHandler())` antes del `start()`.
[1](https://github.com/AlberHanibal/Procesos/blob/master/Tema%203/Ejercicios/E-PSP-T3-2.8/src/com/packtpub/java7/concurrency/chapter1/recipe8/core/Main.java) [2](https://github.com/AlberHanibal/Procesos/blob/master/Tema%203/Ejercicios/E-PSP-T3-2.8/src/com/packtpub/java7/concurrency/chapter1/recipe8/handler/ExceptionHandler.java) [3](https://github.com/AlberHanibal/Procesos/blob/master/Tema%203/Ejercicios/E-PSP-T3-2.8/src/com/packtpub/java7/concurrency/chapter1/recipe8/task/Task.java) 

## 2.9 Grupos de hilos
`ThreadGroup`, `activeCount()` se pueden detener todos con `interrupt()`
[1](https://github.com/AlberHanibal/Procesos/blob/master/Tema%203/Ejercicios/E-PSP-T3-2.9/src/com/packtpub/java7/concurrency/chapter1/recipe10/core/Main.java) [2](https://github.com/AlberHanibal/Procesos/blob/master/Tema%203/Ejercicios/E-PSP-T3-2.9/src/com/packtpub/java7/concurrency/chapter1/recipe10/task/Result.java) [3](https://github.com/AlberHanibal/Procesos/blob/master/Tema%203/Ejercicios/E-PSP-T3-2.9/src/com/packtpub/java7/concurrency/chapter1/recipe10/task/SearchTask.java) 

## 2.10 Pools de hilos

Usando una clase factoría `Executors` se crean objetos `ThreadPoolExecutor`

- **Pool de hilos ilimitado**: se van creando según se necesitan `ThreadPoolExecutor executor=(ThreadPoolExecutor)Executors.newCachedThreadPool()` y `execute(runnable, callable)`.
- **Pool de hilos de tamaño fijo**: `newFixedThreadPoll(numHilos)`.
- **Pool con hilo único**: `ExecutorService	newSingleThreadExecutor()`.
- **Pool de hilos planificados**.
Hay que terminar los ejecutores `shutdown()`
[1](https://github.com/AlberHanibal/Procesos/blob/master/Tema%203/Ejercicios/E-PSP-T3-2.10/src/com/packtpub/java7/concurrency/chapter4/recipe1/core/Main.java) [2](https://github.com/AlberHanibal/Procesos/blob/master/Tema%203/Ejercicios/E-PSP-T3-2.10/src/com/packtpub/java7/concurrency/chapter4/recipe1/task/Server.java) [3](https://github.com/AlberHanibal/Procesos/blob/master/Tema%203/Ejercicios/E-PSP-T3-2.10/src/com/packtpub/java7/concurrency/chapter4/recipe1/task/Task.java) 

## 2.11 Tareas periódicas

- **Tareas retardadas**: se ejecutan una vez después de un tiempo. Con `Callable y Runnable`, `schedule`
[1](https://github.com/AlberHanibal/Procesos/blob/master/Tema%203/Ejercicios/E-PSP-T3-2.11-A/src/com/packtpub/java7/concurrency/chapter4/recipe7/core/Main.java) [2](https://github.com/AlberHanibal/Procesos/blob/master/Tema%203/Ejercicios/E-PSP-T3-2.11-A/src/com/packtpub/java7/concurrency/chapter4/recipe7/task/Task.java)

- **Tareas periódicas**: se ejecutan después de un retardo y luego periódicamente cada x tiempo. `Runnable`, `scheduledAtFixedRate() y sheduledWithFixedDelay`
[1](https://github.com/AlberHanibal/Procesos/blob/master/Tema%203/Ejercicios/E-PSP-T3-2.11-B/src/com/packtpub/java7/concurrency/chapter4/recipe8/core/Main.java) [2](https://github.com/AlberHanibal/Procesos/blob/master/Tema%203/Ejercicios/E-PSP-T3-2.11-B/src/com/packtpub/java7/concurrency/chapter4/recipe8/task/Task.java)