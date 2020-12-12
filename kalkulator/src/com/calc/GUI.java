package com.calc;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class GUI extends JFrame {
    NorthContainer northContainer;
    static Font textFieldFont = new Font("Sans Serif", Font.BOLD, 20);
    static Font characterLabelFont = new Font("Sans Serif", Font.BOLD, 20);

    public GUI() {
        setTitle("Calculator");

        var gui = new JPanel();
        gui.setLayout(new BorderLayout());

        northContainer = new NorthContainer();
        gui.add(northContainer, BorderLayout.NORTH);

        EastContainer eastContainer = new EastContainer();
        gui.add(eastContainer, BorderLayout.EAST);

        CenterContainer centerContainer = new CenterContainer();
        gui.add(centerContainer, BorderLayout.CENTER);

        add(gui);
        pack();


    }

// north container with equation and select buttons
static class NorthContainer extends JPanel {
        EquationBox equationBox1;
        JLabel operator;
        EquationBox equationBox2;
        JLabel equals;
        JTextField result;
        public NorthContainer() {
            var northPanel = new JPanel();
            northPanel.setLayout(new BoxLayout(northPanel, BoxLayout.LINE_AXIS));
            add(northPanel);

            var boxGroup = new ButtonGroup();
            equationBox1 = new EquationBox(textFieldFont, 1);
            boxGroup.add(equationBox1.equationRadioButton);
            equationBox1.equationRadioButton.setSelected(true);
            northPanel.add(equationBox1);

            operator = new JLabel("+");
            operator.setFont(characterLabelFont);
            operator.setAlignmentY(Component.TOP_ALIGNMENT);
            northPanel.add(operator);

            equationBox2 = new EquationBox(textFieldFont, 2);
            boxGroup.add(equationBox2.equationRadioButton);
            northPanel.add(equationBox2);

            equals = new JLabel("=");
            equals.setFont(characterLabelFont);
            equals.setAlignmentY(Component.TOP_ALIGNMENT);
            northPanel.add(equals);

            result = new JTextField("", 5);
            result.setFont(textFieldFont);
            result.setAlignmentX(Component.CENTER_ALIGNMENT);
            result.setAlignmentY(Component.TOP_ALIGNMENT);
            result.setEditable(false);
            northPanel.add(result);

        }

        static class EquationBox extends JPanel {
            JRadioButton equationRadioButton;
            JTextField equationField;
            public EquationBox(Font textFieldFont, int id) {
                var equationBox = new JPanel();
                equationBox.setLayout(new BoxLayout(equationBox, BoxLayout.PAGE_AXIS));
                add(equationBox);

                equationRadioButton = new JRadioButton(String.valueOf(id));
                equationBox.setAlignmentX(Component.CENTER_ALIGNMENT);
                equationBox.add(equationRadioButton);

                equationField = new JTextField("", 5);
                equationField.setFont(textFieldFont);
                equationField.setAlignmentX(Component.CENTER_ALIGNMENT);
                equationField.setAlignmentY(Component.TOP_ALIGNMENT);
                equationField.setEditable(false);
                equationBox.add(equationField);

            }
        }

    }

// east container with operator selection
    class EastContainer extends JPanel {
        JPanel operatorBox;
        public EastContainer() {
            operatorBox = new JPanel();
            operatorBox.setLayout(new BoxLayout(operatorBox, BoxLayout.PAGE_AXIS));
            add(operatorBox);
            var separator = new Dimension(0,10);

            var operator = new OperatorAction();
            addButton("+", operator);
            operatorBox.add(Box.createRigidArea(separator));
            addButton("-", operator);
            operatorBox.add(Box.createRigidArea(separator));
            addButton("*", operator);
            operatorBox.add(Box.createRigidArea(separator));
            addButton("/", operator);

        }
        private void addButton(String label, ActionListener listener) {
            var button = new JButton(label);
            button.addActionListener(listener);
            button.setFont(characterLabelFont);
            button.setAlignmentX(Component.CENTER_ALIGNMENT);
            operatorBox.add(button);
        }

    }

// center container with numbers selection
    class CenterContainer extends JPanel {
        private final JPanel numpad;
        public CenterContainer() {
            var insert = new InsertAction();
            var delete = new DelAction();
            var calculate = new CalculateAction();
            numpad = new JPanel();
            numpad.setLayout(new GridLayout(4, 3,10,10));

            add(numpad);
            addButton("7", insert);
            addButton("8", insert);
            addButton("9", insert);

            addButton("4", insert);
            addButton("5", insert);
            addButton("6", insert);

            addButton("1", insert);
            addButton("2", insert);
            addButton("3", insert);

            addButton("0", insert);
            addButton("DEL", delete);
            addButton("=", calculate);
        }
        private void addButton(String label, ActionListener listener) {
            var button = new JButton(label);
            button.addActionListener(listener);
            button.setFont(textFieldFont);
            numpad.add(button);
        }

}
    private class InsertAction implements ActionListener {
        public void actionPerformed(ActionEvent event)
        {
            clearResult();
            String input = event.getActionCommand();
            if (northContainer.equationBox1.equationRadioButton.isSelected()) {
                northContainer.equationBox1.equationField.setText(northContainer.equationBox1.equationField.getText() + input);
            } else if (northContainer.equationBox2.equationRadioButton.isSelected()) {
                    northContainer.equationBox2.equationField.setText(northContainer.equationBox2.equationField.getText() + input);
                }
        }
    }

    private class OperatorAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            String input = event.getActionCommand();
            northContainer.operator.setText(input);
            clearResult();
        }
    }

    private class DelAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {
            clearResult();
            if (northContainer.equationBox1.equationRadioButton.isSelected()) {
                northContainer.equationBox1.equationField.setText("");
            } else if (northContainer.equationBox2.equationRadioButton.isSelected()) {
                northContainer.equationBox2.equationField.setText("");
            }
        }
    }

    private class CalculateAction implements ActionListener {
        public void actionPerformed(ActionEvent event) {

            double i = Integer.parseInt(northContainer.equationBox1.equationField.getText());
            double j = Integer.parseInt(northContainer.equationBox2.equationField.getText());
            double result = 0;
            switch (northContainer.operator.getText()) {
                case "+" -> result = i + j;
                case "-" -> result = i - j;
                case "*" -> result = i * j;
                case "/" -> result = i / j;
            }
            northContainer.result.setText(String.valueOf(result));
        }
    }

    public void clearResult() {
        northContainer.result.setText("");
    }

}