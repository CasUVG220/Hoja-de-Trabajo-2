/**
 * Interfaz para una calculadora que evalúa expresiones matemáticas en notación postfix.
 */
public interface ICalculadora {
    
    /**
     * Evalúa una expresión en notación postfix y devuelve el resultado.
     * @param expr Expresión en notación postfix como un string.
     * @return Resultado de la evaluación.
     * @throws IllegalArgumentException Si la expresión es inválida.
     */
    int evaluarExpresion(String expr);
}