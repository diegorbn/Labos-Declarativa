package screen;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.UIManager;
import javax.swing.border.Border;
import java.awt.Color;
import java.awt.Cursor;
import java.awt.Font;

public final class Theme {
    public static final Color BACKGROUND = new Color(24, 26, 32);
    public static final Color SURFACE = new Color(34, 37, 45);
    public static final Color INPUT = new Color(45, 49, 59);
    public static final Color TEXT = new Color(238, 240, 245);
    public static final Color MUTED_TEXT = new Color(166, 171, 184);
    public static final Color ACCENT = new Color(94, 129, 244);
    public static final Color SECONDARY = new Color(80, 86, 101);
    public static final Color ERROR = new Color(244, 99, 99);

    public static final Font TITLE_FONT = new Font("SansSerif", Font.BOLD, 24);
    public static final Font BODY_FONT = new Font("SansSerif", Font.PLAIN, 15);
    public static final Font BUTTON_FONT = new Font("SansSerif", Font.BOLD, 18);
    public static final Font RESULT_FONT = new Font("SansSerif", Font.BOLD, 22);

    private Theme() {
    }

    public static void apply() {
        UIManager.put("OptionPane.background", SURFACE);
        UIManager.put("Panel.background", SURFACE);
        UIManager.put("OptionPane.messageForeground", TEXT);
        UIManager.put("Button.focus", new Color(0, 0, 0, 0));
    }

    public static Border inputBorder() {
        return BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(SECONDARY, 1, true),
                BorderFactory.createEmptyBorder(9, 11, 9, 11)
        );
    }

    public static void styleButton(JButton button, Color background) {
        button.setBackground(background);
        button.setForeground(TEXT);
        button.setFont(BUTTON_FONT);
        button.setFocusPainted(false);
        button.setBorderPainted(false);
        button.setOpaque(true);
        button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
    }

    public static void makeTransparent(JComponent component) {
        component.setOpaque(false);
    }
}
