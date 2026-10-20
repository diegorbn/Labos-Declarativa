package screen;

import backend.Connection;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.Insets;
import java.math.BigDecimal;

public final class CalculatorWindow extends JFrame {
    private final JTextField firstNumberField = createNumberField();
    private final JTextField secondNumberField = createNumberField();
    private final JLabel resultLabel = new JLabel("—", SwingConstants.CENTER);
    private final Connection connection;

    public CalculatorWindow() {
        WindowConfig.apply(this);
        connection = new Connection();
        setContentPane(createContent());
    }

    private JPanel createContent() {
        JPanel root = new JPanel(new BorderLayout());
        root.setBackground(Theme.BACKGROUND);
        root.setBorder(BorderFactory.createEmptyBorder(25, 30, 28, 30));

        JLabel title = new JLabel("Calculadora");
        title.setForeground(Theme.TEXT);
        title.setFont(Theme.TITLE_FONT);

        root.add(title, BorderLayout.NORTH);
        root.add(createForm(), BorderLayout.CENTER);
        return root;
    }

    private JPanel createForm() {
        JPanel form = new JPanel(new GridBagLayout());
        form.setBackground(Theme.BACKGROUND);

        GridBagConstraints constraints = new GridBagConstraints();
        constraints.gridx = 0;
        constraints.weightx = 1;
        constraints.fill = GridBagConstraints.HORIZONTAL;
        constraints.insets = new Insets(5, 0, 5, 0);

        constraints.gridy = 0;
        form.add(createFieldPanel("Primer número", firstNumberField), constraints);

        constraints.gridy = 1;
        form.add(createFieldPanel("Segundo número", secondNumberField), constraints);

        constraints.gridy = 2;
        constraints.insets = new Insets(13, 0, 8, 0);
        form.add(createButtons(), constraints);

        constraints.gridy = 3;
        constraints.insets = new Insets(8, 0, 0, 0);
        form.add(createResultPanel(), constraints);
        return form;
    }

    private JPanel createFieldPanel(String labelText, JTextField field) {
        JLabel label = new JLabel(labelText);
        label.setForeground(Theme.MUTED_TEXT);
        label.setFont(Theme.BODY_FONT);

        JPanel panel = new JPanel(new BorderLayout(0, 5));
        panel.setBackground(Theme.BACKGROUND);
        panel.add(label, BorderLayout.NORTH);
        panel.add(field, BorderLayout.CENTER);
        return panel;
    }

    private JPanel createButtons() {
        JButton addButton = new JButton("+");
        JButton subtractButton = new JButton("−");
        Theme.styleButton(addButton, Theme.ACCENT);
        Theme.styleButton(subtractButton, Theme.SECONDARY);
        addButton.setPreferredSize(new Dimension(0, 45));
        subtractButton.setPreferredSize(new Dimension(0, 45));

        addButton.addActionListener(event -> calculate(Operation.ADD));
        subtractButton.addActionListener(event -> calculate(Operation.SUBTRACT));

        JPanel buttons = new JPanel(new GridLayout(1, 2, 10, 0));
        buttons.setBackground(Theme.BACKGROUND);
        buttons.add(addButton);
        buttons.add(subtractButton);
        return buttons;
    }

    private JPanel createResultPanel() {
        JLabel caption = new JLabel("Resultado");
        caption.setForeground(Theme.MUTED_TEXT);
        caption.setFont(Theme.BODY_FONT);

        resultLabel.setForeground(Theme.TEXT);
        resultLabel.setFont(Theme.RESULT_FONT);
        resultLabel.setBorder(BorderFactory.createEmptyBorder(7, 0, 3, 0));

        JPanel result = new JPanel(new BorderLayout());
        result.setBackground(Theme.SURFACE);
        result.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Theme.INPUT, 1, true),
                BorderFactory.createEmptyBorder(9, 12, 9, 12)
        ));
        result.add(caption, BorderLayout.NORTH);
        result.add(resultLabel, BorderLayout.CENTER);
        return result;
    }

    private static JTextField createNumberField() {
        JTextField field = new JTextField();
        field.setBackground(Theme.INPUT);
        field.setForeground(Theme.TEXT);
        field.setCaretColor(Theme.TEXT);
        field.setSelectionColor(Theme.ACCENT);
        field.setSelectedTextColor(Color.WHITE);
        field.setFont(Theme.BODY_FONT);
        field.setBorder(Theme.inputBorder());
        return field;
    }

    private void calculate(Operation operation) {
        String firstText = firstNumberField.getText().trim();
        String secondText = secondNumberField.getText().trim();

        if (firstText.isEmpty() || secondText.isEmpty()) {
            showError("Debe ingresar ambos números.");
            focusMissingField(firstText, secondText);
            return;
        }

        try {
            double firstNumber = parseNumber(firstText);
            double secondNumber = parseNumber(secondText);
            double result = operation == Operation.ADD
                    ? connection.add(firstNumber, secondNumber)
                    : connection.subtract(firstNumber, secondNumber);

            resultLabel.setText(formatResult(result));
            clearFields();
        } catch (NumberFormatException exception) {
            showError("Ingrese únicamente números válidos.");
        } catch (RuntimeException exception) {
            showError("No fue posible realizar la operación.");
        }
    }

    private static double parseNumber(String text) {
        double value = Double.parseDouble(text.replace(',', '.'));
        if (!Double.isFinite(value)) {
            throw new NumberFormatException("Non-finite number");
        }
        return value;
    }

    private static String formatResult(double result) {
        if (result == 0.0d) {
            return "0";
        }
        return BigDecimal.valueOf(result).stripTrailingZeros().toPlainString();
    }

    private void clearFields() {
        firstNumberField.setText("");
        secondNumberField.setText("");
        firstNumberField.requestFocusInWindow();
    }

    private void focusMissingField(String firstText, String secondText) {
        if (firstText.isEmpty()) {
            firstNumberField.requestFocusInWindow();
        } else if (secondText.isEmpty()) {
            secondNumberField.requestFocusInWindow();
        }
    }

    private void showError(String message) {
        JOptionPane.showMessageDialog(
                this,
                message,
                "Error de validación",
                JOptionPane.ERROR_MESSAGE
        );
    }

    private enum Operation {
        ADD,
        SUBTRACT
    }
}
