package utils;

import java.util.*;

public class ConsoleInput {
	private Scanner kb;

	public ConsoleInput(Scanner kb) {
		this.kb = kb;
	}

	//void cleanInput(): 
	//limpia el teclado.
	private void cleanInput() {
		kb.nextLine();
	}

	//int readInt(): 
	//retorna un int introducido por el usuario.
	public int readInt() {
		boolean isIntValid;
		int n = 0;

		do {
			isIntValid = true;
			try {
				n = kb.nextInt();

			} catch (InputMismatchException e) {
				System.err.println("Error, debe introducir un numero entero");
				isIntValid = false;
			} finally {
				cleanInput();
			}

		} while (!isIntValid);

		return n;
	}

	//	int readIntLessThan(int upperBound): 
	//	retorna un int introducido por el usuario inferior al parámetro.
	public int readIntLessThan(int upperBound) {
		int n;
		do {
			n = readInt();
			if (n >= upperBound) {
				System.err.printf("Error, debe introducir un numero entero menor que %d \n", upperBound);
			}
		} while (n >= upperBound);

		return n;
	}

	//int readIntLessOrEqualThan(int upperBound):
	//retorna un int introducido por el usuario inferior o igual al parámetro.
	public int readIntLessOrEqualThan(int upperBound) {
		int n;
		do {
			n = readInt();
			if (n > upperBound) {
				System.err.printf("Error, debe introducir un numero entero menor o igual que %d \n", upperBound);
			}
		} while (n > upperBound);

		return n;
	}

	//int readIntGreaterThan(int lowerBound):
	//retorna un int introducido por el usuario superior al parámetro.
	public int readIntGreaterThan(int lowerBound) {
		int n;
		do {
			n = readInt();
			if (n <= lowerBound) {
				System.err.printf("Error, debe introducir un numero entero mayor que %d \n", lowerBound);
			}
		} while (n <= lowerBound);

		return n;
	}

	//int readIntGreaterOrEqualThan(int lowerBound):
	//retorna un int introducido por el usuario superior o igual al parámetro.
	public int readIntGreaterOrEqualThan(int lowerBound) {
		int n;
		do {
			n = readInt();
			if (n < lowerBound) {
				System.err.printf("Error, debe introducir un numero entero mayor o igual que %d \n", lowerBound);
			}
		} while (n < lowerBound);

		return n;
	}

	//int readIntInRange(int lowerBound, int upperBound):
	//retorna un int introducido por el usuario cuyo valor esté 
	//en el rango [lowerBound, upperBound], ambos incluidos.
	public int readIntInRange(int lowerBound, int upperBound) {
		int n;
		if (lowerBound > upperBound) {
			throw new IllegalArgumentException("Orden de rango incorrecto");
		}
		do {
			n = readInt();
			if (n < lowerBound || n > upperBound) {
				System.err.printf("Error, debe introducir un numero dentro del rango: %d - %d", lowerBound, upperBound);
			}
		} while (n < lowerBound || n > upperBound);

		return n;
	}

	//char readChar(): retorna un carácter introducido por el usuario.
	//Si éste introduce más de un carácter, se le vuelve a solicitar.
	public char readChar() {
		String caracter;
		char c;
		do {
			caracter = kb.next();
			if (caracter.length() != 1) {
				System.err.println("Error hay más de un caracter");
			}
			c = caracter.charAt(0);

		} while (caracter.length() != 1);
		return c;
	}

	//	String readString():
	//	retorna una cadena de caracteres introducida por el usuario.
	public String readString() {
		return kb.nextLine();
	}
	//	String readString(int maxLength):
	//	retorna una cadena de caracteres de como máximo maxLength caracteres.

	public String readString(int maxLenght) {
		String chain;
		if (maxLenght <= 0) {
			throw new IllegalArgumentException("Error la cadena debe tener mas caracteres");
		}
		do {
			chain = readString();
			if (chain.length() > maxLenght) {
				throw new IllegalArgumentException("Error la cadena no puede superar el limite indicado");
			}
		} while (chain.length() > maxLenght);

		return chain;
	}

	//boolean readBooleanUsingChar(char affirmativeValue, char negativeValue):
	//retorna un booleano a partir de un carácter introducido por el usuario,
	//de manera que si coincide con affirmativeValue (en mayúsculas o minúsculas) retornará true
	//y si coincide con negativeValue (en mayúsculas o minúsculas), retornará false.
	public boolean readBooleanUsingChar(char affirmativeValue, char negativeValue) {
		char c, afir, nega;
		afir = Character.toLowerCase(affirmativeValue);
		nega = Character.toLowerCase(negativeValue);

		boolean value = false;
		if (afir == nega) {
			throw new IllegalArgumentException("Los caracteres especificados no pueden ser el mismo");
		}

		do {
			c = Character.toLowerCase(readChar());
			if (c == afir) {
				value = true;
			} else if (c == nega) {
				value = false;
			} else {
				System.err.println("El caracter introducido debe ser uno de los indicados");
			}

		} while (c != afir && c != nega);

		return value;
	}

	//readDouble
	//devuelve un double indicado por el usuario
	public double readDouble() {
		boolean isDoubleValid;
		double n = 0;

		do {
			isDoubleValid = true;
			try {
				n = kb.nextDouble();

			} catch (InputMismatchException e) {
				System.err.println("Error, debe introducir un numero con decimales");
				isDoubleValid = false;
			} finally {
				cleanInput();
			}

		} while (!isDoubleValid);

		return n;
	}

	//byte readByte(): 
	//retorna un byte introducido por el usuario.
	public byte readByte() {
		boolean isByteValid;
		byte b = 0;

		do {
			isByteValid = true;
			try {
				b = kb.nextByte();

			} catch (InputMismatchException e) {
				System.err.println("Error, debe introducir un numero byte");
				isByteValid = false;
			} finally {
				cleanInput();
			}

		} while (!isByteValid);

		return b;
	}
}
