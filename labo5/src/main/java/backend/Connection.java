package backend;

import alice.tuprolog.Prolog;
import alice.tuprolog.SolveInfo;
import alice.tuprolog.Term;
import alice.tuprolog.Theory;

import java.io.InputStream;
import java.util.Locale;

/** Conecta Swing con la base de conocimiento */
public final class Connection {
    private static final String KNOWLEDGE_BASE = "/resource/calculator.pl";
    private final Prolog engine;

    public Connection() {
        engine = new Prolog();
        loadKnowledgeBase();
    }

    public double add(double firstNumber, double secondNumber) {
        return solve("add", firstNumber, secondNumber);
    }

    public double subtract(double firstNumber, double secondNumber) {
        return solve("subtract", firstNumber, secondNumber);
    }

    private void loadKnowledgeBase() {
        try (InputStream stream = Connection.class.getResourceAsStream(KNOWLEDGE_BASE)) {
            if (stream == null) {
                throw new IllegalStateException("No se encontró la base de conocimiento: " + KNOWLEDGE_BASE);
            }
            engine.setTheory(new Theory(stream));
        } catch (Exception exception) {
            throw new IllegalStateException("No se pudo cargar la base de conocimiento.", exception);
        }
    }

    private double solve(String predicate, double firstNumber, double secondNumber) {
        requireFinite(firstNumber);
        requireFinite(secondNumber);

        String query = String.format(
                Locale.ROOT,
                "%s(%s, %s, Result).",
                predicate,
                Double.toString(firstNumber),
                Double.toString(secondNumber)
        );

        try {
            SolveInfo solution = engine.solve(query);
            if (!solution.isSuccess()) {
                throw new IllegalStateException("Prolog no encontró una solución para: " + query);
            }

            Term result = solution.getVarValue("Result");
            return Double.parseDouble(result.toString());
        } catch (Exception exception) {
            throw new IllegalStateException("No se pudo calcular el resultado en Prolog.", exception);
        }
    }

    private static void requireFinite(double number) {
        if (!Double.isFinite(number)) {
            throw new IllegalArgumentException("El valor debe ser un número finito.");
        }
    }
}
