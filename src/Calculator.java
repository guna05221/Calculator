// Import required AWT and Swing classes
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

// Calculator class extends JFrame and implements ActionListener
public class Calculator extends JFrame implements ActionListener {

    // Display label for showing numbers and results
    private JLabel displayLabel;

    // Array to store number buttons (0–9)
    private JButton[] numberButtons = new JButton[10];

    // Operator and control buttons
    private JButton addButton, subButton, mulButton, divButton, eqButton, clrButton, dotButton;

    // Variables to store calculation data
    private String operator = "";        // Stores selected operator
    private float firstValue = 0;         // Stores first operand
    private boolean isOperatorClicked = false; // Checks operator click state

    // Constructor – initializes calculator UI
    public Calculator() {
        setTitle("Calculator");
        setSize(400, 500);
        setLayout(null);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        createDisplay();   // Create display label
        createButtons();   // Create calculator buttons

        setVisible(true);
    }

    // Method to create the display label
    private void createDisplay() {
        displayLabel = new JLabel();
        displayLabel.setBounds(20, 20, 340, 50);
        displayLabel.setOpaque(true);
        displayLabel.setBackground(Color.GRAY);
        displayLabel.setForeground(Color.WHITE);
        displayLabel.setFont(new Font("Arial", Font.BOLD, 22));
        displayLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        displayLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(displayLabel);
    }

    // Method to create all calculator buttons
    private void createButtons() {
        Font btnFont = new Font("Arial", Font.BOLD, 18);

        int x = 20, y = 90;

        // Create number buttons 7, 8, 9
        for (int i = 7; i <= 9; i++) {
            numberButtons[i] = createButton(String.valueOf(i), x, y, btnFont);
            x += 90;
        }

        // Create number buttons 4, 5, 6
        y += 80; x = 20;
        for (int i = 4; i <= 6; i++) {
            numberButtons[i] = createButton(String.valueOf(i), x, y, btnFont);
            x += 90;
        }

        // Create number buttons 1, 2, 3
        y += 80; x = 20;
        for (int i = 1; i <= 3; i++) {
            numberButtons[i] = createButton(String.valueOf(i), x, y, btnFont);
            x += 90;
        }

        // Create 0 and decimal buttons
        numberButtons[0] = createButton("0", 20, y + 80, btnFont);
        dotButton = createButton(".", 110, y + 80, btnFont);

        // Create operator buttons
        addButton = createButton("+", 290, 90, btnFont);
        subButton = createButton("-", 290, 170, btnFont);
        mulButton = createButton("*", 290, 250, btnFont);
        divButton = createButton("/", 290, 330, btnFont);

        // Create equal and clear buttons
        eqButton = createButton("=", 200, y + 80, btnFont);
        clrButton = createButton("C", 290, y + 80, btnFont);
    }

    // Helper method to create a button
    private JButton createButton(String text, int x, int y, Font font) {
        JButton btn = new JButton(text);
        btn.setBounds(x, y, 80, 60);
        btn.setFont(font);
        btn.setBackground(Color.BLACK);
        btn.setForeground(Color.WHITE);
        btn.addActionListener(this);
        add(btn);
        return btn;
    }

    // Handles all button click events
    @Override
    public void actionPerformed(ActionEvent e) {
        String command = e.getActionCommand();

        // Handle number and decimal input
        if (command.matches("[0-9.]")) {
            if (isOperatorClicked) {
                displayLabel.setText(command);
                isOperatorClicked = false;
            } else {
                displayLabel.setText(displayLabel.getText() + command);
            }
        }

        // Handle operator buttons
        else if (command.matches("[+\\-*/]")) {
            firstValue = Float.parseFloat(displayLabel.getText());
            operator = command;
            isOperatorClicked = true;
        }

        // Handle equal button
        else if (command.equals("=")) {
            float secondValue = Float.parseFloat(displayLabel.getText());
            float result = calculate(firstValue, secondValue, operator);
            displayLabel.setText(String.valueOf(result));
        }

        // Handle clear button
        else if (command.equals("C")) {
            displayLabel.setText("");
            operator = "";
            firstValue = 0;
        }
    }

    // Performs calculation based on operator
    private float calculate(float a, float b, String op) {
        switch (op) {
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return b != 0 ? a / b : 0; // Prevent divide by zero
            default: return 0;
        }
    }

    // Main method – program execution starts here
    public static void main(String[] args) {
        new Calculator();
    }
}
