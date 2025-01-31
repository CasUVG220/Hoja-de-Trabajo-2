import java.util.Scanner;

/**
 * Clase principal para probar la calculadora postfix.
 */
public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        IStack pila = new VectorPila();
        CalculadoraPostfix calculadora = new CalculadoraPostfix(pila);

        System.out.println("=== Calculadora Postfix ===");
        System.out.println("Ingrese una expresión en notación Postfix.");
        System.out.println("Ejemplo válido: '3 4 + 2 *' (Esto equivale a (3 + 4) * 2 = 14)");
        System.out.println("Escriba 'exit' para salir.");

        while (true) {
            System.out.print("\nExpresión: ");
            String expresion = scanner.nextLine();

            if (expresion.equalsIgnoreCase("exit")) {
                System.out.println("Saliendo del programa...");
                break;
            }

            try {
                int resultado = calculadora.evaluarExpresion(expresion);
                System.out.println("Resultado: " + resultado);
            } catch (Exception e) {
                System.out.println("\n❌ " + e.getMessage());
                System.out.println("⚠️ Verifique que la expresión esté en formato postfix y contenga solo números y operadores válidos (+, -, *, /).");
            }
        }

        scanner.close();
    }
}