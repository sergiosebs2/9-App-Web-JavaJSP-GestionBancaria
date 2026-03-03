# 🏦 Banco UTN - Sistema de Gestión Bancaria

Trabajo Práctico Integrador de **Laboratorio IV** — Universidad Tecnológica Nacional.
Aplicación web bancaria completa desarrollada en **Java con JSP, Servlets y MySQL**, con arquitectura de tres capas, interfaces y manejo de excepciones propias. El sistema contempla dos tipos de usuarios: administrador del banco y clientes.

## 🛠️ Tecnologías

![Java](https://img.shields.io/badge/java-%23ED8B00.svg?style=for-the-badge&logo=openjdk&logoColor=white)
![MySQL](https://img.shields.io/badge/mysql-%2300f.svg?style=for-the-badge&logo=mysql&logoColor=white)
![Bootstrap](https://img.shields.io/badge/bootstrap-%238511FA.svg?style=for-the-badge&logo=bootstrap&logoColor=white)
![Apache Tomcat](https://img.shields.io/badge/apache%20tomcat-%23F8DC75.svg?style=for-the-badge&logo=apache-tomcat&logoColor=black)

## 👤 Funcionalidades del Administrador

- ABML de clientes con asignación de usuario y contraseña
- ABML de cuentas bancarias (Caja de Ahorro / Cuenta Corriente) y asignación a clientes
- Autorización o rechazo de préstamos solicitados por clientes
- Administración de usuarios con cambio de contraseña
- Generador de informes estadísticos con filtro por rango de fechas
- Listados paginados con búsquedas y filtros avanzados

## 👥 Funcionalidades del Cliente

- Dashboard con datos personales y saldo actual
- Historial de movimientos por cuenta
- Transferencias a otras cuentas mediante CBU
- Solicitud de préstamos con selección de cuotas y cuenta de depósito
- Pago de cuotas de préstamos con descuento automático del saldo

## 🏗️ Arquitectura

Proyecto organizado en tres capas con interfaces y paquete de excepciones propias:

- **JSP (Vista)** — interfaz web con Bootstrap
- **Servlets (Controlador)** — manejo de requests y sesiones
- **Negocio** — lógica de la aplicación con interfaces
- **DAO** — acceso a datos con JDBC y MySQL
- **Entidades** — Cliente, CuentaBancaria, Prestamo, Cuotas, Movimiento, Usuario y más
- **Excepciones** — excepciones propias en paquete separado

## 📌 Características técnicas

- Bajas lógicas (no físicas)
- Sesión de usuario en todas las pantallas
- Validaciones de formularios y mensajes aclaratorios
- Listados paginados con búsquedas tipo `LIKE %contenido%`
- Filtros por rangos en listados de movimientos
- Sin saldos negativos — validación antes de transferir o pagar cuotas
- Máximo 3 cuentas por cliente
- Monto inicial fijo de $10.000 al crear una cuenta
