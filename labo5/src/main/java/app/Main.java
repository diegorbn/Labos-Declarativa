package app;

import screen.CalculatorWindow;
import screen.Theme;

import javax.swing.SwingUtilities;

public final class Main {
    private Main() {
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            Theme.apply();
            new CalculatorWindow().setVisible(true);
        });
    }
}
