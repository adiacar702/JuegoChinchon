# Pruebas de Caja Negra

Se centran en el comportamiento observable del sistema.

No tienen en cuenta la implementación interna, sino únicamente:

* Entradas.
* Salidas esperadas.
* Comportamiento funcional.

Permiten comprobar que el juego responde correctamente a las acciones de los usuarios.

## Diseño y Justificación de las Pruebas de Caja Negra

### Caso 1: Detección de Trío

Entrada:

```text
3♠ 3♥ 3♦
```

Resultado esperado:

```text
Trío válido
```

---

### Caso 2: Detección de Escalera

Entrada:

```text
4♠ 5♠ 6♠
```

Resultado esperado:

```text
Escalera válida
```

---

### Caso 3: Detección de Chinchón

Entrada:

```text
1♠ 2♠ 3♠ 4♠ 5♠ 6♠ 7♠
```

Resultado esperado:

```text
isChinchon = true
```

---

### Caso 4: Fin de Partida

Entrada:

```text
Jugador = 100 puntos
Puntuación máxima = 100
```

Resultado esperado:

```text
checkMaxPoints() = true
```

[Volver a la documentación](./README.md/#8-pruebas-unitarias)
