package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Represents panel when 2 numbers remain
public class TwoNumbersPanel extends JPanel {

    private JButton n1;
    private JButton n2;
    private CardLayout cl;
    private JPanel panelCont;
    private List<JButton> selectedButtons = new ArrayList<>();
    private List<JButton> remainingButtons = new ArrayList<>();
    private List<Double> previousNumbers;
    private EndOfGamePanel endScreen;

    // EFFECTS: construct panel with all buttons
    public TwoNumbersPanel(CardLayout cl, JPanel panelCont, List<Double> remainingNumbers, EndOfGamePanel endScreen) {
        this.cl = cl;
        this.panelCont = panelCont;
        this.previousNumbers = remainingNumbers;
        initializeButtons();
        addAllButtonsToRemainingButtons();
        addButtonsToPanel();
        addFunctionForAllButtons();
        this.endScreen = endScreen;
    }

    // MODIFIES: n1, n2
    // EFFECTS: add action listener to all functions
    private void addFunctionForAllButtons() {
        addButtonFunctions(n1);
        addButtonFunctions(n2);
    }

    // MODIFIES: n1, n2, this
    // EFFECTS: instantiate buttons
    private void initializeButtons() {
        n1 = new JButton(previousNumbers.get(0).toString());
        n2 = new JButton(previousNumbers.get(1).toString());
    }

    // MODIFIES: remainingButtons
    // EFFECTS: Add all number buttons to remainingButtons
    private void addAllButtonsToRemainingButtons() {
        remainingButtons.add(n1);
        remainingButtons.add(n2);
    }

    // MODIFIES: this
    // EFFECTS: add all buttons to panel
    private void addButtonsToPanel() {
        for (JButton b: remainingButtons) {
            add(b);
        }
    }

    // EFFECTS: take in a button that is pressed. If it is the first one to be pressed in this panel, turn it invisible
    // and add text to verify it has been pressed. Otherwise, if it is the second one that is pressed, show operation
    // panel.
    private void addButtonFunctions(JButton button) {
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                selectedButtons.add(button);
                remainingButtons.remove(button);
                button.setVisible(false);
                add(new JLabel("Number chosen: " + button.getText()));
                if (selectedButtons.size() == 2) {
                    JPanel opPanel = new OperationPanel(
                            cl, panelCont, selectedButtons, remainingButtons, endScreen);
                    panelCont.add(opPanel, "operation");
                    cl.show(panelCont, "operation");
                }
            }
        });
    }
}
