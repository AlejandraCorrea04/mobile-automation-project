# 📱 Mobile Automation Project - WebdriverIO Native Demo App

Este proyecto contiene la suite de automatización de pruebas móviles End-to-End (E2E) para aplicaciones Android, desarrollada con **Java 21**, **Appium (UiAutomator2)**, **TestNG** y alineada bajo el patrón de diseño **Page Object Model (POM)**.

---

## 🎯 Objetivo del Proyecto

El propósito de esta práctica es automatizar escenarios clave de navegación y funcionalidad sobre la aplicación nativa de prueba de WebdriverIO (`native-demo-app.apk`):

1. **Navegación en la Barra Inferior (Bottom Menu Bar Navigation):**
    - Verificar la navegación hacia las secciones **Home**, **Webview**, **Login**, **Forms**, **Swipe** y **Drag**.
    - Asertar la carga y visibilidad correcta de los componentes principales en cada pantalla.
2. **Registro Exitoso (Successful Sign Up):**
    - Completar el flujo de registro generando direcciones de correo dinámicas mediante `UUID` para garantizar que las ejecuciones sean repetibles e independientes.
3. **Inicio de Sesión Exitoso (Successful Login):**
    - Ejecutar la autenticación de un usuario creado dentro de la misma prueba (sin depender de datos preexistentes en el dispositivo ni de ejecuciones previas).
4. **Desplazamiento Horizontal y Vertical (Swipe & Scroll):**
    - Realizar swipe horizontal en las tarjetas del carrusel hasta alcanzar la última posición.
    - Desplazarse verticalmente hasta localizar e identificar el texto oculto `"You found me!!!"`.

---

## 🛠️ Tecnologías y Herramientas

- **Lenguaje:** Java 21
- **Driver de Automatización:** Appium Java Client (v8.x / v9.x) / Appium UiAutomator2 Driver
- **Framework de Testing:** TestNG
- **Gestión de Proyecto y Construcción:** Apache Maven
- **Registro de Logs:** SLF4J / Logback
- **Patrón de Arquitectura:** Page Object Model (POM)
- **App de Prueba:** [WDIO Native Demo App APK](https://github.com/webdriverio/native-demo-app/releases)

---

## 🏗️ Estructura del Proyecto

```text
mobile-automation-project/
├── src/
│   └── test/
│       └── java/
│           ├── screens/                  # Page Objects y controladores de componentes
│           │   ├── AlertHandler.java     # Interfaz para manejo de alertas
│           │   ├── DragScreen.java
│           │   ├── FormsScreen.java
│           │   ├── HomeScreen.java
│           │   ├── LoginScreen.java
│           │   ├── SwipeScreen.java
│           │   └── WebviewScreen.java
│           ├── tests/                    # Clases de prueba automatizadas (TestNG)
│           │   ├── LoginTest.java
│           │   ├── NavigationTest.java
│           │   ├── SignupTest.java
│           │   └── SwipeTest.java
│           └── util/                     # Clases de soporte y utilidades
│               ├── data/
│               │   └── RandomDataGenerator.java
│               ├── screens/
│               │   ├── BaseScreen.java
│               │   └── CustomWait.java
│               └── tests/
│                   └── BaseMobileTest.java
├── testng.xml                            # Suite de ejecución de TestNG
├── pom.xml                               # Configuración de dependencias de Maven
└── README.md                             # Documentación general del proyecto