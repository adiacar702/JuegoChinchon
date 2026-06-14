# Listado de tests pensados para analizar

## Comparación de dos cartas con mismo valor y palo

| Campo              | Valor                    |
| ------------------ | ------------------       |
| Entrada            | (5⚔️)  (5⚔️)             |
| Acción             | assertEquals(c1, c2)     |
| Resultado esperado | True                     |

## Comparación de dos cartas con mismo valor y diferente palo

| Campo              | Valor                    |
| ------------------ | ------------------       |
| Entrada            | (5⚔️)  (5🟡)             |
| Acción             | assertNotEquals(c1, c2)  |
| Resultado esperado | True                     |

## Detección de Trío

| Campo              | Valor              |
| ------------------ | ------------------ |
| Entrada            | 5🟡 5⚔️ 5🍷        |
| Acción             | findTrios()        |
| Resultado esperado | Se detecta un trío |

---

## Detección de Escalera

| Campo              | Valor                   |
| ------------------ | ----------------------- |
| Entrada            | 4🟡 5🟡 6🟡             |
| Acción             | findEscaleras()         |
| Resultado esperado | Se detecta una escalera |

---

## Robar del Deck reduce su tamaño

| Campo              | Valor                                   |
| ------------------ | --------------------------------------- |
| Entrada            | Deck de 40 cartas, si roba 39           |
| Acción             | draw()                                  |
| Resultado esperado | Deck reducido                           |

---

## Comprobación de carta en zona de descarte

| Campo              | Valor                                         |
| ------------------ | ---------------------------------------       |
| Entrada            | descarte carta y añadir carta a pila descarte |
| Acción             | discard(card) , peekDiscard()                 |
| Resultado esperado | Carta descartada visible en pila descarte     |

---

## Selección de Robar carta IA Aleatoria

| Campo              | Valor                                |
| ------------------ | ------------------------------------ |
| Entrada            | Mano con dos opciones de robo        |
| Acción             | drawCard()                           |
| Resultado esperado | Roba una carta al azar               |

---

## Selección de Robar carta IA Inteligente

| Campo              | Valor                                |
| ------------------ | ------------------------------------ |
| Entrada            | Mano con dos opciones de robo        |
| Acción             | drawCard()                           |
| Resultado esperado | Roba la carta mas útil               |

[Volver a la documentación](./README.md/#10-casos-de-prueba-seleccionados)
