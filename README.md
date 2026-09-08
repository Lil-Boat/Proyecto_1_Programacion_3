#  Sistema de Control de Membresías para Gimnasio

Proyecto 1 de **Programación III (EIF206)** — Universidad Nacional de Costa Rica.

Este es nuestro sistema de escritorio en Java para administrar los socios de un gimnasio: registrar a la gente, asignarles un plan, cobrar sus membresías y simular el control de acceso a la entrada (si el socio está al día o no con el pago).

##  Integrantes

- Mariano Martinez Alfaro
- Daniel Granados Perez

##  ¿Qué hace el sistema?

El programa tiene 3 pantallas (pestañas) principales:

1. **Socios** — Aqui se registra a los socios nuevos (nombre, teléfono, correo, contacto de emergencia, condiciones médicas). 
También se pueden ver todos en una tabla y eliminarlos.
2. **Membresías y Pagos** — Se le asigna un plan a un socio (Mensual, Anual o VIP) y el sistema cobra automáticamente, calculando cuándo vence. 
También sirve para registrar pagos de renovación.
3. **Control de Acceso** — Uno escribe el número de socio y el sistema dice si el acceso está **Permitido** o **Denegado por Morosidad**, dependiendo de si la membresía sigue vigente.

##  Tecnologías usadas

- **Java** (JDK 25)
- **Swing** para toda la interfaz gráfica
- Sin librerías externas ni frameworks — todo con lo que da el JDK

##  Estructura del proyecto

```
src/
├── Main.java              → punto de entrada del programa
├── modelo/                → las clases de datos (Socio, Plan, Membresia, etc.)
├── servicio/                → una estructura genérica para guardar dato toda la lógica de negocio (validaciones, cálculos)
└── vista/                   → las pantallas hechas con Swing
```

##  Conceptos de POO que aplicamos

- **Herencia:** `Socio extends Persona`, y los planes (`PlanMensual`, `PlanAnual`, `PlanVIP`) todos heredan de `PlanBase`.
- **Interfaces:** `Plan` es una interfaz que obliga a todo plan a saber calcular su precio. También hicimos `IGestorSocios`, `IGestorMembresias` e `IControlAcceso` para separar la lógica de negocio del GUI (esto nos ayudó a aplicar los principios **SOLID**, sobre todo el de Inversión de Dependencias).
- **Polimorfismo:** cuando se llama `plan.calcularPrecio()`, no importa si es un plan Mensual, Anual o VIP — cada uno calcula su precio a su manera, sin necesidad de hacer `if/else` para revisar de cuál tipo es.
- **Colecciones y genéricos:** hicimos una clase `Repositorio<T>` que sirve tanto para guardar socios como membresías, reutilizando el mismo código.

##  Cómo correrlo

1. Abrir el proyecto en IntelliJ IDEA.
2. Correr la clase `Main.java`.
3. Se abre la ventana con las 3 pestañas mencionadas arriba.


##  Estado del proyecto

- [x] Estructura base (modelo, gestión, GUI)
- [x] Registro y eliminación de socios
- [x] Asignación de planes y cobro
- [x] Control de acceso simulado


##  Notas

Este proyecto se hizo usando herramientas de IA como apoyo (permitido según la política del curso), pero todo el código fue revisado y entendido por ambos antes de subirlo.

---
Curso: EIF206 - Programación III · Profesora: Kristel Duarte Pérez
