package screen;

import javax.swing.JFrame;
import java.awt.Dimension;

public final class WindowConfig {
    public static final String TITLE = "Calculadora con Prolog";
    public static final Dimension SIZE = new Dimension(540, 460);

    private WindowConfig() {
    }

    public static void apply(JFrame window) {
        window.setTitle(TITLE);
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setMinimumSize(SIZE);
        window.setSize(SIZE);
        window.setLocationRelativeTo(null);
        window.setResizable(false);
    }
}
