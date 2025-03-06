package hw12;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Calculator extends JFrame {
    private JTextField display;
    private StringBuilder input;

    public Calculator() {
        setTitle("Calculator");
        setSize(600, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);

        input = new StringBuilder();

        display = new JTextField();
        display.setEditable(false);
        display.setHorizontalAlignment(JTextField.RIGHT);
        display.setFont(new Font("Consolas", Font.PLAIN, 28));
        display.setPreferredSize(new Dimension(600, 120));

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.BOTH;
        gbc.weightx = 1;
        gbc.weighty = 1;

        String[][] buttonsStrings = {
                {"C", "/", "*", "-"},
                {"7", "8", "9", "+"},
                {"4", "5", "6", ""},
                {"1", "2", "3", "="},
                {"0", "", ".", ""}
        };

        for (int row = 0; row < buttonsStrings.length; row++) {
            for (int col = 0; col < buttonsStrings[row].length; col++) {
                String buttonText = buttonsStrings[row][col];
                if (!buttonText.isEmpty()) {
                    JButton button = new JButton(buttonText);
                    button.setFont(new Font("Consolas", Font.PLAIN, 24));
                    button.setFocusPainted(false);

                    gbc.gridx = col;
                    gbc.gridy = row;

                    if (buttonText.equals("0")) {
                        gbc.gridwidth = 2;
                    } else if (buttonText.equals("+")) {
                        gbc.gridheight = 2;
                    } else if (buttonText.equals("=")) {
                        gbc.gridheight = 2;
                    } else {
                        gbc.gridwidth = 1;
                        gbc.gridheight = 1;
                    }

                    panel.add(button, gbc);
                    button.addActionListener(new ButtonClickListener(this));
                }
            }
        }

        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(display, BorderLayout.NORTH);
        mainPanel.add(panel, BorderLayout.CENTER);

        addKeyListener(new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent e) {
                char keyChar = e.getKeyChar();
                if (Character.isDigit(keyChar) || keyChar == '+' || keyChar == '-' || keyChar == '*' || keyChar == '/') {
                    input.append(keyChar);
                    display.setText(input.toString());
                } else if (keyChar == 'c' || keyChar == 'C') {
                    input.setLength(0);
                    display.setText("");
                } else if (keyChar == '=') {
                    try {
                        String result = eval(input.toString());
                        display.setText(result);
                        input.setLength(0);
                        input.append(result);
                    } catch (Exception ex) {
                        display.setText("Error");
                        input.setLength(0);
                    }
                }
            }
        });

        mainPanel.requestFocus();
        add(mainPanel);

        setVisible(true);
        setFocusable(true);
    }

    private class ButtonClickListener implements ActionListener {
        private Calculator context;

        ButtonClickListener(Calculator context) {
            this.context = context;
        }

        @Override
        public void actionPerformed(ActionEvent actionEvent) {
            JButton source = (JButton) actionEvent.getSource();
            String command = source.getText();

            if (command.equals("C")) {
                input.setLength(0);
                display.setText("");
            } else if (command.equals("=")) {
                try {
                    String result = eval(input.toString());
                    display.setText(result);
                    input.setLength(0);
                    input.append(result);
                } catch (Exception e) {
                    display.setText("Error");
                    input.setLength(0);
                }
            } else {
                input.append(command);
                display.setText(input.toString());
            }

            context.requestFocus();
        }
    }

    private String eval(String expression) {
        expression = expression.replaceAll("\\s+", "");
        String[] tokens = expression.split("(?=[-+*/])|(?<=[-+*/])");
        List<String> tokenList = new ArrayList<>();
        Collections.addAll(tokenList, tokens);

        for (int i = 0; i < tokenList.size(); ++i) {
            String token = tokenList.get(i);
            if (token.equals("*") || token.equals("/")) {
                double n1 = Double.parseDouble(tokenList.get(i - 1));
                double n2 = Double.parseDouble(tokenList.get(i + 1));
                double result = 0;

                if (token.equals("*")) {
                    result = n1 * n2;
                } else if (token.equals("/")) {
                    if (n2 == 0) {
                        return "Error";
                    }
                    result = n1 / n2;
                }

                tokenList.set(i - 1, String.valueOf(result));
                tokenList.remove(i);
                tokenList.remove(i);
                i--;
            }
        }

        //Second iteration to consume * and /
        for (int i = 0; i < tokenList.size(); ++i) {
            String token  = tokenList.get(i);
            if (token.equals("+") || token.equals("-")) {
                double n1 = Double.parseDouble(tokenList.get(i - 1));
                double n2 = Double.parseDouble(tokenList.get(i + 1));
                double result = 0;

                if (token.equals("+")) {
                    result = n1 + n2;
                } else if (token.equals("-")) {
                    result = n1 - n2;
                }

                tokenList.set(i - 1, String.valueOf(result));
                tokenList.remove(i);
                tokenList.remove(i);
                i--;
            }
        }

        return tokenList.get(0);
    }

    public static void main(String[] args) {
        new Calculator();
    }
}