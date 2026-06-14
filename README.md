# Proyecto final de curso 1º CFGS "CHINCHÓN DIGITAL"

 Autor: Adrián Díaz Cárdenas

## Introducción del proyecto

Este documento va referido a mi trabajo de final curso con la intención de aprender mas sobre programación y conseguir una persperctiva mas amplia sobre el trabajo al que se dedican los estudiosos de este sector.

Para la realización de este proyecto se han utilizado las herramientas indicadas en este enlace -> [TOOLS](./TOOLS.md)

## 1. Introducción al proyecto

Chinchón Digital es una implementación completa del clásico juego de cartas Chinchón desarrollada desde cero en Java utilizando Programación Orientada a Objetos, principios SOLID y diferentes patrones de diseño.

El objetivo principal del proyecto ha sido aplicar los conocimientos adquiridos durante las asignaturas de Programación y Entornos de Desarrollo, implementando un software mantenible, escalable y correctamente documentado.

## 2. Explicación del Juego

### ¿Qué es el Chinchón?

El Chinchón es un juego tradicional de cartas muy popular en España. Los jugadores deben formar combinaciones de cartas para minimizar los puntos de las cartas no agrupadas.

### Tipos de jugadores

#### Jugador Humano

Permite introducir decisiones manualmente mediante consola.

#### Random AI

Realiza acciones aleatorias.

#### Smart AI

Evalúa las cartas de la mano y toma decisiones estratégicas para maximizar sus posibilidades de formar
combinaciones.

### Objetivo del juego

Conseguir la menor puntuación posible al finalizar la partida formando:

- Escaleras (cartas consecutivas del mismo palo).
- Tríos (cartas del mismo valor).
- Chinchón (escalera de siete cartas).

### Reglas

Reglas a seguir para la conseguir una experiencia completa del juego -> [RULES](./RULES.md)

## 3. Transcurso de partida

En el siguiente enlace se muestra un ejemplo de una partida realizada entre dos IAs con diferetes estrategias para facilitar la comprensión del juego -> [GAME IA VS IA](./GAME.md)

## 4. Estructura del Proyecto

Estructura realizada para la ordenación del proyecto.

```java
ChinchonDigital/
│
├── src/
│ ├── ai/
│ ├── app/
│ ├── factory/
│ ├── game/
│ ├── gameConfig/
│ ├── playerUsers/
│ ├── resources/
│ ├── rules/
│ └── utils/
│
├── test/
│
├── assets/
│
├── doc/
│
└── README.md
```

[Descripción de Carpetas y Paquetes](./FOLDERS.md)

## 5. Diseño UML

Diagrama de Clases

<img src=assets/UMLchinchonAdrianDiaz.png width="1000">

El UML representa la estructura completa del sistema mostrando:

- Herencia.

- Asociaciones.

- Dependencias.

- Interfaces implementadas.

En el siguiente enlace se explica mas detalladamente la estructura -> [UML](./UML.md)

## 6. Descripción de Clases

### Game

Responsable de controlar el flujo principal de la partida.

Funciones principales:

- Iniciar partida

- Gestionar rondas

- Calcular puntuaciones

- Determinar ganador

### Player

Clase base para todos los jugadores.

Responsabilidades:

- Gestionar mano de cartas.

- Robar cartas.

- Descartar cartas.

### HumanPlayer

Implementa el comportamiento de un jugador humano.

### RandomAIPlayer

Implementa una IA basada en decisiones aleatorias.

### SmartAIPlayer

Implementa una IA basada en evaluación estratégica de la mano

### Deck

Gestiona el mazo de cartas.

Responsabilidades:

- Crear baraja.

- Barajar.

- Repartir.

- Reconstruir el mazo.

### Card

Representa una carta individual.

### CombinationValidator

Valida:

- Tríos

- Escaleras

- Chinchón

---

## 7. Patrones de Diseño Utilizados

### Factory

Centralizar la creación de jugadores.

Implementación:

`PlayerFactory.createPlayer(...)`

Ventajas

- Evita instanciaciones dispersas.

- Facilita añadir nuevos tipos de jugador.

### Singleton

Garantizar una única instancia de configuración global.

Implementación:

`Config.getInstance();`

Ventajas

- Acceso global controlado.

- Evita duplicidad de configuraciones.

## 8. Pruebas Unitarias

El objetivo de las pruebas es verificar que todos los componentes del juego funcionan correctamente y que las reglas del Chinchón se aplican de forma adecuada.

Las pruebas realizadas permiten validar:

- La detección de tríos, escaleras y chinchón.

- El cálculo correcto de puntuaciones.

- La gestión de cartas durante la partida.

- El comportamiento de las inteligencias artificiales.

- El correcto funcionamiento de las rondas y del final de la partida.

### Estrategia de Pruebas

Se han utilizado dos enfoques complementarios:

#### Pruebas de Caja Blanca -> [Detalles](./WHITEBOX.md)

#### Pruebas de Caja Negra -> [Detalles](./BLACKBOX.md)

Estas pruebas verifican que todos los caminos importantes del código son ejecutados al menos una vez.

## 9. Organización de los Tests

Los tests se organizan siguiendo la misma estructura de paquetes que el proyecto principal.

```text
src
└── test
    ├── rules
    │   └── CombinationsTest
    │
    ├── game
    │   └── TableTest
    │
    ├── ai
    │   ├── RandomAITest
    │   └── SmartAITest
    │
    └── resources
        ├── DeckTest
        └── CardTest
```

## 10. Casos de Prueba Seleccionados

En este apartado se recogen las pruebas pensadas para analizar con metodos internos. -> [Casos de Prueba](./REALIZEDTESTS.md)

---

## 11. Evidencias de Ejecución

Las evidencias consisten en capturas de pantalla obtenidas durante la ejecución de las pruebas.

Evidencias recogidas -> [Detalles](./BBOXPNGS.md)

### Gracias por su atención, espero les haya gustado mi proyecto, un saludo y que tengan un buen dia
