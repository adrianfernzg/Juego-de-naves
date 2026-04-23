# Informe Técnico del Proyecto: JuegoNaves

## 1. Descripción General
**JuegoNaves** es una aplicación de juego para Android donde el usuario controla una nave espacial que debe esquivar naves enemigas que aparecen aleatoriamente desde la izquierda de la pantalla. El objetivo es sobrevivir el mayor tiempo posible y obtener la máxima puntuación.

## 2. Arquitectura del Sistema (Patrón MVC)
El proyecto ha sido reestructurado siguiendo el patrón **Modelo-Vista-Controlador** para asegurar un código limpio, escalable y organizado.

### 2.1. Modelo (`com.example.juegonaves.model`)
Contiene las entidades de datos y la lógica de representación de los objetos del juego.
*   **PlayerShip**: Gestiona las coordenadas de la nave del jugador, su límite de movimiento en pantalla y su representación gráfica.
*   **EnemyShip**: Gestiona la posición de las naves enemigas y su lógica de movimiento lateral.

### 2.2. Vista (`com.example.juegonaves.view`)
Se encarga de la interfaz de usuario y el renderizado en tiempo real.
*   **GameView**: Extiende de `SurfaceView`. Es el lienzo donde se dibuja el juego a 60 FPS. Gestiona los eventos táctiles y la detección de colisiones.

### 2.3. Controlador (`com.example.juegonaves.controller`)
Gestiona el flujo de la aplicación y la comunicación entre el modelo y la vista.
*   **MainActivity**: Punto de entrada. Gestiona el formulario de nombre y la selección de dificultad.
*   **GameActivity**: Actividad principal que aloja la vista del juego y gestiona su ciclo de vida (pausa/reanudación).
*   **GameOverActivity**: Se activa tras una colisión. Muestra el nombre del jugador, la dificultad y la puntuación final.
*   **GameThread**: Hilo secundario encargado de ejecutar el bucle principal del juego para mantener la fluidez.

## 3. Funcionalidades Implementadas
*   **Control de Nave**: Movimiento vertical fluido mediante interacción táctil o ratón.
*   **Sistema de Dificultad**: Tres niveles (Fácil, Medio, Difícil) que escalan el número de enemigos y su velocidad inicial.
*   **Velocidad Incremental**: La dificultad aumenta automáticamente cada 10 segundos, incrementando la velocidad de los enemigos.
*   **Detección de Colisiones**: Algoritmo de distancia euclidiana para detectar choques precisos.
*   **Gestión de Audio**: Soporte para música de fondo y efectos de sonido de colisión (vía recursos `raw`).
*   **UX Mejorada**: Ocultación automática del teclado y validación de campos en la pantalla de inicio.

## 4. Tecnologías Utilizadas
*   **Lenguaje**: Kotlin
*   **UI**: XML / Material Components
*   **Gráficos**: Android Canvas API
*   **Gestión de Hilos**: Threads / SurfaceHolder
*   **Persistencia Volátil**: Intents para transferencia de datos entre actividades.

---
*Informe generado automáticamente por el Asistente de Desarrollo.*
