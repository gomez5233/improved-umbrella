# InfoApp (Android nativo)

Aplicación Android nativa simple (Kotlin, vistas XML) con 3 pantallas conectadas y CRUD en memoria:
- **Lista**: muestra los elementos y permite crear nuevos.
- **Detalle**: muestra la información de un elemento y permite editarlo o eliminarlo.
- **Formulario**: crear o editar un elemento (título y descripción).

Compilación pensada para la nube con **GitHub Actions** (sin necesidad de Android Studio ni emulador).

## Cómo generar el APK en GitHub (paso a paso)
1. Crea un repositorio **nuevo y vacío** en GitHub.
2. Sube **todo el contenido** de esta carpeta (`proyectoAndroid`) a la **raíz** del repositorio. Es importante que la carpeta oculta `.github` se incluya, para que exista `.github/workflows/build-apk.yml`.
   - En Windows, activa *Ver → Mostrar → Elementos ocultos* para verla.
3. Ve a la pestaña **Actions** del repositorio:
   - Si no se ejecutó solo, entra en **Build Android APK** y pulsa **Run workflow**.
   - El estado pasará de `Queued` a `In progress` y luego a `Success`.
4. Cuando termine en verde, abre la ejecución y descarga el artefacto **`app-debug-apk`** (sección **Artifacts**, al final). Extrae el ZIP para obtener `app-debug.apk`.

## Instalar en tu dispositivo físico (copiando el APK)
1. Copia `app-debug.apk` a tu teléfono (USB, correo, nube, etc.).
2. Ábrelo desde el gestor de archivos e instala (permite "orígenes desconocidos" si el sistema lo pide).
3. Abre **InfoApp**.

> Los datos se guardan en memoria y se reinician al cerrar la app.

## Detalles del proyecto
- Paquete: `com.lg316.infoapp`
- `minSdk`: 24 (Android 7.0) — `compileSdk`/`targetSdk`: 34
