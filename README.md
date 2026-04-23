# 🚀 JuegoNaves - Android Space Game

**JuegoNaves** es un juego arcade para Android desarrollado en Kotlin, donde pondrás a prueba tus reflejos esquivando oleadas de naves enemigas. El proyecto implementa una arquitectura robusta basada en el patrón **MVC (Modelo-Vista-Controlador)** y utiliza el API de **Canvas** para renderizado gráfico de alto rendimiento.

---

## 🎮 Características principales

- **Acción Infinita:** Sobrevive a oleadas aleatorias de enemigos que aparecen desde la izquierda.
- **Control Fluido:** Controla tu nave verticalmente mediante gestos táctiles o ratón.
- **Dificultad Progresiva:** La velocidad de los enemigos aumenta cada 10 segundos, desafiando tu destreza.
- **Niveles de Dificultad:** Elige entre **Fácil, Medio o Difícil** para ajustar el número de atacantes.
- **Feedback Inmersivo:** Efectos de sonido para colisiones y música de fondo durante el juego.
- **Interfaz Limpia:** Pantallas de inicio, juego y fin de juego diseñadas con Material Design.

---

## 🏗️ Arquitectura (MVC)

El proyecto está organizado siguiendo el patrón de diseño **Modelo-Vista-Controlador** para separar la lógica de negocio de la interfaz de usuario:

- **📁 model:** Define las entidades del juego (`PlayerShip`, `EnemyShip`). Gestionan sus datos y su representación gráfica.
- **📁 view:** Contiene el `GameView`, encargado del renderizado en tiempo real a 60 FPS utilizando `SurfaceView`.
- **📁 controller:** Gestiona el flujo del juego, el ciclo de vida de las actividades y el hilo principal del motor del juego (`GameThread`).

---

## 🛠️ Tecnologías y Herramientas

- **Lenguaje:** [Kotlin](https://kotlinlang.org/)
- **UI:** XML / Material Components
- **Gráficos:** Android Canvas API & SurfaceView
- **Audio:** MediaPlayer API
- **Herramientas:** Android Studio, Git

---

## 📦 Instalación y Uso

1. **Clonar el repositorio:**
   ```bash
   git clone https://github.com/tu-usuario/JuegoNaves.git
   ```
2. **Abrir en Android Studio:**
   - Selecciona "Open an existing project".
   - Navega hasta la carpeta del proyecto.
3. **Ejecutar:**
   - Conecta un dispositivo físico o inicia un emulador.
   - Presiona `Run`.

> **Nota:** Para habilitar los sonidos, asegúrate de añadir archivos `background_music.mp3` y `collision_sound.mp3` en la carpeta `app/src/main/res/raw/`.

---


## 📄 Licencia

Este proyecto está bajo la Licencia MIT. Consulta el archivo `LICENSE` para más detalles.

---
Hecho con ❤️ por [Adrian Fernandez]
