# Pruebas de Caja Blanca

Se centran en la lógica interna del código.

Permiten comprobar:

* Condiciones booleanas.
* Bucles.
* Caminos de ejecución.
* Casos límite.

Estas pruebas verifican que todos los caminos importantes del código son ejecutados al menos una vez.

## Diseño y Justificación de las Pruebas de Caja Blanca

### CombinationValidator

Método: `canClose()`

Este método presenta tres caminos de ejecución principales:

### Camino 1

El jugador tiene Chinchón.

Resultado esperado:

```java
true
```

### Camino 2

El jugador tiene una única carta no combinada con valor menor o igual a 5.

Resultado esperado:

```java
true
```

### Camino 3

El jugador no cumple ninguna condición de cierre.

Resultado esperado:

```java
false
```

---

## Table

Método: `drawDeck()`

Se prueban dos escenarios:

### Escenario 1

El deck contiene cartas.

Resultado esperado:

```java
return deck.draw();
```

### Escenario 2

El deck está vacío.

Resultado esperado:

```java
refillDeckFromDiscard();
```

Se verifica que el deck se reconstruye correctamente a partir del descarte.

---

## SmartAI

Método: `chooseDiscard()`

Se comprueba que:

* Nunca devuelve null.
* Conserva combinaciones útiles.
* Descarta cartas con menor valor estratégico.

[Volver a la documentación](./README.md/#8-pruebas-unitarias)
