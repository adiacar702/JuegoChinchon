# Explicación del Diseño UML

## Arquitectura General

El proyecto se ha diseñado siguiendo los principios de la Programación Orientada a Objetos y una arquitectura modular basada en responsabilidades separadas.

El proyecto puede dividirse de la siguiente forma:

* **Modelo de dominio:** `Card`, `Deck`, `Hand`, `Player`.
* **Lógica de juego:** `Game`, `Round`, `Table`.
* **Reglas del juego:** `CombinationValidator`, `Combinations`, `CombinationResult`.
* **Inteligencia Artificial:** `AIStrategy`, `RandomAI`, `SmartAI`.
* **Configuración y creación de objetos:** `GameConfig`, `PlayerFactory`.
* **Utilidades:** `ConsoleInput`, `Colors`, `GameDebugger`.

Esta separación favorece el mantenimiento, la reutilización de código y la posibilidad de incorporar nuevas funcionalidades sin afectar al resto del sistema.

---

## Modelo de Dominio

### Card

Representa una carta individual del juego.

#### Responsabilidades

* Almacenar el palo y el valor de la carta.
* Permitir comparaciones entre cartas.
* Ofrecer una representación textual para mostrarla por pantalla.

#### Atributos principales

* `suit`
* `value`

Es la entidad fundamental sobre la que se construye todo el juego.

---

### Deck

Representa una baraja de cartas.

#### Responsabilidades

* Crear las cartas del juego.
* Barajar la baraja.
* Robar cartas.

#### Atributo principal

* `cards : List<Card>`

Centraliza toda la gestión de cartas disponibles para robar.

---

### Hand

Representa la mano de un jugador.

#### Responsabilidades

* Añadir cartas.
* Eliminar cartas.
* Vaciar la mano.
* Consultar las cartas almacenadas.

#### Atributo principal

* `cards : List<Card>`

Permite encapsular la lógica relacionada con la mano del jugador y evita que otras clases manipulen directamente las listas de cartas.

---

## Player

Clase abstracta que representa cualquier participante de la partida.

### Atributos

* `name`
* `hand`
* `points`
* `eliminated`

#### Métodos comunes

* `addCard()`
* `removeCard()`
* `getHand()`
* `getPoints()`

#### Métodos abstractos

* `chooseDiscard()`
* `wantsToClose()`

Permite aplicar polimorfismo entre jugadores humanos e inteligencias artificiales.

---

## HumanPlayer

Hereda de `Player`.

Implementa los métodos abstractos mediante interacción con el usuario a través de consola.

### Funcionalidades

* Elegir qué carta descartar.
* Decidir si desea cerrar la ronda.

---

## AIPlayer

Hereda de `Player`.

No implementa directamente la lógica de juego, sino que delega las decisiones a una estrategia de inteligencia artificial.

### Relación

```text
AIPlayer
 └── AIStrategy
```

Esto permite cambiar fácilmente el comportamiento de una IA sin modificar la clase.

### RandomAI

Implementación simple basada en decisiones aleatorias.

#### Características

* Selección aleatoria de descartes.
* Decisiones aleatorias de juego.

---

### SmartAI

Implementación estratégica.

#### Características

* Evalúa las combinaciones de la mano.
* Intenta minimizar los puntos muertos.
* Selecciona las mejores cartas para conservar.

Para ello depende de la interfaz `HandEvaluator`.

---

## Sistema de Reglas

La lógica de validación se encuentra desacoplada del flujo principal del juego.

---

### Combinations

Clase especializada en detectar combinaciones válidas.

### Funciones principales

* `findTrios()`
* `findEscaleras()`
* `isChinchon()`

Contiene únicamente algoritmos relacionados con las reglas del juego.

---

### CombinationValidator

Actúa como fachada para el resto de la aplicación.

#### Funciones principales

* `analyzeHand()`
* `canClose()`
* `countValidCards()`
* `calculateNoValidNums()`

Su objetivo es ofrecer una API sencilla para validar una mano completa.

---

### CombinationResult

Objeto de transferencia de datos (DTO).

Contiene:

* Tríos encontrados.
* Escaleras encontradas.
* Cartas restantes.
* Indicador de Chinchón.

Permite devolver toda la información de una evaluación mediante un único objeto.

---

## Inversión de Dependencias

### HandEvaluator

Se ha creado la interfaz:

```java
public interface HandEvaluator
```

que define:

```java
calculateNoValidNums(List<Card> hand)
```

---

### Aplicación en SmartAI

La IA inteligente no depende de la clase concreta:

```java
CombinationValidator
```

sino de la abstracción:

```java
HandEvaluator
```

Esto reduce el acoplamiento y mejora la flexibilidad del sistema.

---

## Gestión de la Partida

### Game

Representa una partida completa.

#### Responsabilidades

* Crear rondas.
* Comprobar la puntuación máxima.
* Determinar el final de la partida.
* Mostrar el ranking final.

#### Relación principal

```text
Game
 └── List<Player>
```

---

### Round

Representa una ronda individual.

#### Responsabilidades

* Repartir cartas.
* Gestionar turnos.
* Controlar robos y descartes.
* Permitir cierres de ronda.
* Calcular puntuaciones.

#### Dependencias

* `Table`
* `Player`
* `CombinationValidator`

---

## Table

Representa el estado físico de la mesa.

### Elementos gestionados

* Deck principal.
* Zona de descarte.
* Estado del primer turno.

#### Funciones principales

* `drawDeck()`
* `drawDiscard()`
* `discard()`
* `refillDeckFromDiscard()`

Centraliza toda la manipulación de cartas durante la partida.

---

## Patrones de Diseño Utilizados

### Singleton

#### GameConfig

Se utiliza para almacenar la configuración global de la partida.

Características:

* Una única instancia.
* Acceso global mediante:

```java
GameConfig.getInstance()
```

Permite consultar:

* Número de barajas.
* Puntuación máxima.

---

## Factory

### PlayerFactory

Centraliza la creación de jugadores.

Ejemplo:

```java
PlayerFactory.createPlayer(...)
```

Ventajas:

* Evita la creación dispersa de objetos.
* Facilita añadir nuevos tipos de jugador.

## Aplicación de los Principios SOLID

### Single Responsibility Principle (SRP)

Cada clase posee una única responsabilidad:

| Clase                | Responsabilidad       |
| -------------------- | --------------------- |
| Card                 | Representar una carta |
| Deck                 | Gestionar la baraja   |
| Hand                 | Gestionar una mano    |
| Round                | Gestionar una ronda   |
| Game                 | Gestionar la partida  |
| CombinationValidator | Validar combinaciones |

---

### Open/Closed Principle (OCP)

El sistema está preparado para incorporar:

* Nuevas IA.
* Nuevas reglas.
* Nuevos tipos de jugadores.

sin modificar el código existente.

---

### Liskov Substitution Principle (LSP)

Las clases:

* `HumanPlayer`
* `AIPlayer`

pueden utilizarse en cualquier contexto donde se espere un objeto `Player`.

---

### Interface Segregation Principle (ISP)

Las interfaces son pequeñas y específicas:

* `AIStrategy`
* `HandEvaluator`

Ninguna clase se ve obligada a implementar métodos que no necesita.

---

### Dependency Inversion Principle (DIP)

Las clases de alto nivel dependen de abstracciones y no de implementaciones concretas.

Ejemplo:

```text
SmartAI
    ↓
HandEvaluator
    ↑
CombinationValidator
```

## Conclusión

Gracias a esta estructura, el juego puede evolucionar fácilmente incorporando nuevas inteligencias artificiales, reglas adicionales o incluso nuevas interfaces gráficas sin necesidad de modificar la lógica principal existente.

[Volver a la documentación](./README.md/#6-descripción-de-clases)
