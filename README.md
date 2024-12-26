# YouTube Simplificado - Gestión de Base de Datos

## 📄 Descripción
Este proyecto diseña un modelo básico de base de datos para una versión reducida de YouTube. Incluye funcionalidades como usuarios, videos, canales, listas de reproducción, comentarios, suscripciones y reacciones.

### Características
1. **Conexión a la base de datos:**
    - Clase `Conexion` para conectar con MySQL.
2. **Creación de tablas:**
    - Clase `CreateTables` para crear y configurar:
        - Usuarios.
        - Videos.
        - Canales.
        - Playlists.
        - Comentarios.
        - Etiquetas.
        - Reacciones a videos y comentarios.
3. **Relaciones:**
    - Estructura relacional para:
        - Usuarios y videos.
        - Videos y etiquetas.
        - Usuarios y canales.
        - Playlists y videos.
        - Comentarios y videos.
    - Soporte para reacciones a videos y comentarios.
4. **Triggers automáticos:**
    - Actualización automática de likes y dislikes en videos y comentarios.

---

## 💻 Tecnologías Utilizadas
- **Java 17**
- **MySQL**
- **IDE recomendado:** IntelliJ IDEA.

---

## 📊 Requisitos
- **Java Development Kit (JDK):** Versión 17 o superior.
- **MySQL:** Servidor en funcionamiento.

---

## 🛠️ Instalación
1. Clona este repositorio:
   ```bash
   git clone https://github.com/eze-ms/S2.01-N2-E1
   ```
2. Configura la base de datos:
    - Crea una base de datos llamada `youtube_db`.
    - Ajusta las credenciales en la clase `Conexion`:
      ```java
      var usuario = "root";
      var password = "tu_contraseña";
      ```
3. Ejecuta el archivo `CreateTables.java` para inicializar las tablas.

---

## 🔧 Ejecución
1. Asegúrate de tener la base de datos configurada correctamente.
2. Compila y ejecuta la clase `CreateTables` desde tu IDE o terminal.
3. Verifica que las tablas y triggers se han creado correctamente en MySQL.

---

## ✨ Características Adicionales
El modelo es escalable y permite agregar futuras funcionalidades como:
- Reportes de popularidad de videos.
- Estadísticas de interacciones de usuarios.
- Integración con servicios externos para gestión avanzada de contenido.

---

## 📢 Notas
Si tienes problemas:
- Revisa que el servidor MySQL esté activo.
- Verifica la configuración de las credenciales en `Conexion`.
- Comprueba los logs para errores en la ejecución de las consultas.

---

© 2024. Proyecto desarrollado por Ezequiel Macchi Seoane.
