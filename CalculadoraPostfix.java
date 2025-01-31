import java.util.StringTokenizer;
import java.util.Vector;

/**
 * Implementación de la calculadora postfix utilizando IStack.
 */
public class CalculadoraPostfix {

    private IStack pila;

    /**
     * Constructor que recibe una pila para evaluar expresiones.
     * @param pila Instancia de IStack que manejará la evaluación.
     */
    public CalculadoraPostfix(IStack pila) {
        this.pila = pila;
    }

    /**
     * Evalúa una expresión en notación postfix y devuelve el resultado.
     * @param expr Expresión postfix en formato String.
     * @return Resultado de la evaluación.
     */
    public int evaluarExpresion(String expr) {
        if (expr == null || expr.trim().isEmpty()) {
            throw new IllegalArgumentException("Error: La expresión está vacía.");
        }

        try {
            StringTokenizer tokens = new StringTokenizer(expr, " ");

            while (tokens.hasMoreTokens()) {
                String token = tokens.nextToken();

                if (esNumero(token)) {
                    pila.push(Integer.parseInt(token));
                } else {
                    if (obtenerTamanioPila() < 2) {
                        vaciarPila(); // ✅ Limpia la pila si hay un error
                        throw new IllegalArgumentException("Error: Expresión inválida. Faltan operandos para el operador '" + token + "'.");
                    }

                    int b = pila.pop();
                    int a = pila.pop();
                    int resultado = pila.operation(token.charAt(0), a, b);
                    pila.push(resultado);
                }
            }

            if (obtenerTamanioPila() > 1) {
                vaciarPila(); // ✅ Limpia la pila si hay demasiados operandos
                throw new IllegalArgumentException("Error: Expresión inválida. Demasiados operandos sin operar.");
            }

            return pila.pop();
        } catch (Exception e) {
            vaciarPila(); // ✅ Limpia la pila si hay un error inesperado
            throw e;
        }
    }

    /**
     * Verifica si una cadena representa un número entero.
     * @param token Token a verificar.
     * @return `true` si es un número, `false` en caso contrario.
     */
    private boolean esNumero(String token) {
        try {
            Integer.parseInt(token);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    /**
     * Método auxiliar para obtener el tamaño de la pila sin modificar sus valores.
     * @return Número de elementos en la pila.
     */
    private int obtenerTamanioPila() {
        int count = 0;
        Vector<Integer> tempStack = new Vector<>();

        while (true) {
            try {
                int value = pila.pop();
                tempStack.add(value);  
                count++;
            } catch (RuntimeException e) {
                break; // Se vació la pila
            }
        }

        for (int i = tempStack.size() - 1; i >= 0; i--) {
            pila.push(tempStack.get(i));
        }

        return count;
    }

    /**
     * Método para vaciar completamente la pila en caso de error.
     */
    private void vaciarPila() {
        while (true) {
            try {
                pila.pop();
            } catch (RuntimeException e) {
                break; // La pila ya está vacía
            }
        }
    }
}