import java.util.Vector;

/**
 * Implementación de una pila utilizando un Vector para operar en una calculadora postfix.
 */
public class VectorPila implements IStack {
    private Vector<Integer> pila;

    public VectorPila() {
        pila = new Vector<>();
    }

    @Override
    public void push(int value) {
        pila.add(value);
    }

    @Override
    public int pop() {
        if (pila.isEmpty()) {
            throw new RuntimeException("Error: La pila está vacía.");
        }
        return pila.remove(pila.size() - 1);
    }

    @Override
    public int operation(char operator, int value1, int value2) {
        switch (operator) {
            case '+': return value1 + value2;
            case '-': return value1 - value2;
            case '*': return value1 * value2;
            case '/':
                if (value2 == 0) throw new ArithmeticException("Error: División entre cero.");
                return value1 / value2;
            default:
                throw new IllegalArgumentException("Error: Operador no válido.");
        }
    }
}
