## Snakey
### *Proyecto de Snake para programacion avanzada*

* Para comenzar a desarrollar en el proyecto se tiene que clonar el proyecto.

   `git clone git@github.com:PA-Rust/snakey.git`

* Hay 3 proyectos de Java distintos
  * Cliente: Es el que tiene la logica que ve el usuario como las pantallas de login, listado de salas, sala actual y el juego en cuestion.
  * Commons: Tiene clases utilizadas tanto en Cliente como en Servidor como la clase `Mapa`, `Enviable`, entre otras.
  * Server: Tiene la logica que maneja las conexiones de clientes, la logica del juego y de las salas.

Basta con importar los 3 proyectos en tu IDE de Java preferido para comenzar a probar.

Para lanzar el juego:

  * Iniciar `src.Server.java` del proyecto **Server** en la maquina servidora.
  * Iniciar `src.InterfazGrafica.MainFrame.java` del proyecto **Cliente** en los clientes y conectar a la IP de la computadora servidora.


