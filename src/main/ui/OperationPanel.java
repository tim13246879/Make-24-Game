package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class OperationPanel extends JPanel {

    private JButton add;
    private JButton subtract;
    private JButton multiply;
    private JButton divide;
    private CardLayout cl;
    private JPanel panelCont;
    private List<JButton> selectedButtons;
    private List<JButton> remainingButtons;
    private String n1;
    private String n2;
    private List<Double> remainingNumbers;
    private Double resultNumber;
    private EndOfGamePanel endScreen;

    public OperationPanel(CardLayout cl, JPanel panelCont, List<JButton> selectedButtons,
                          List<JButton> remainingButtons, EndOfGamePanel endScreen) {
        this.cl = cl;
        this.panelCont = panelCont;
        this.selectedButtons = selectedButtons;
        this.remainingButtons = remainingButtons;
        remainingNumbers = new ArrayList<>();
        buttonsToNumbers();
        setupButtons();
        n1 = selectedButtons.get(0).getText();
        n2 = selectedButtons.get(1).getText();
        add(new JLabel("Select operation for: " + n1 + " ? " + n2));
        addAllButtonFunctions();
        this.endScreen = endScreen;

    }


    private void addAllButtonFunctions() {
        addButtonFunctions(add);
        addButtonFunctions(subtract);
        addButtonFunctions(multiply);
        addButtonFunctions(divide);
    }

    private void buttonsToNumbers() {
        for (JButton b : remainingButtons) {
            remainingNumbers.add(Double.parseDouble(b.getText()));
        }
    }

    private void addButtonFunctions(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (button.getText() == "+") {
                    resultNumber = Double.parseDouble(n1) + Double.parseDouble(n2);
                } else if (button.getText() == "-") {
                    resultNumber = Double.parseDouble(n1) - Double.parseDouble(n2);
                } else if (button.getText() == "*") {
                    resultNumber = Double.parseDouble(n1) * Double.parseDouble(n2);
                } else if (button.getText() == "/") {
                    resultNumber = Double.parseDouble(n1) / Double.parseDouble(n2);
                }
                remainingNumbers.add(resultNumber);
                displayNextNumbers();
            }
        });
    }

    private void displayNextNumbers() {
        if (remainingNumbers.size() == 3) {
            endScreen.setThreeRemainingNumbers(remainingNumbers);
            panelCont.add(new ThreeNumbersPanel(cl, panelCont, remainingNumbers, endScreen), "3numbers");
            cl.show(panelCont, "3numbers");
        } else if (remainingNumbers.size() == 2) {
            endScreen.setTwoRemainingNumbers(remainingNumbers);
            panelCont.add(new TwoNumbersPanel(cl, panelCont, remainingNumbers, endScreen), "2numbers");
            cl.show(panelCont, "2numbers");
        } else if (remainingNumbers.size() == 1) {
            if (resultNumber != 24) {
                cl.show(panelCont, "4numbers");
            } else {
                endScreen.setEndTime(LocalDateTime.now());
                endScreen.setUpButtonsAndLabels();
                cl.show(panelCont, "end screen");
            }
        }
    }

    private void setupButtons() {
        add = new JButton("+");
        subtract = new JButton("-");
        multiply = new JButton("*");
        divide = new JButton("/");
        add(add);
        add(subtract);
        add(multiply);
        add(divide);
    }


}
