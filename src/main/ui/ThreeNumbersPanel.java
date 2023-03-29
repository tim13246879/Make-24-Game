package ui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ThreeNumbersPanel extends JPanel {

    private JButton n1;
    private JButton n2;
    private JButton n3;
    private CardLayout cl;
    private JPanel panelCont;
    private List<JButton> selectedButtons = new ArrayList<>();
    private List<JButton> remainingButtons = new ArrayList<>();
    private List<Double> previousNumbers;
    private EndOfGamePanel endScreen;

    public ThreeNumbersPanel(CardLayout cl, JPanel panelCont, List<Double> remainingNumbers, EndOfGamePanel endScreen) {
        this.cl = cl;
        this.panelCont = panelCont;
        this.previousNumbers = remainingNumbers;
        initializeButtons();
        addAllButtonsToRemainingButtons();
        addButtonsToPanel();
        addFunctionForAllButtons();
        this.endScreen = endScreen;
    }

    private void addFunctionForAllButtons() {
        addButtonFunctions(n1);
        addButtonFunctions(n2);
        addButtonFunctions(n3);
    }

    private void initializeButtons() {
        n1 = new JButton(previousNumbers.get(0).toString());
        n2 = new JButton(previousNumbers.get(1).toString());
        n3 = new JButton(previousNumbers.get(2).toString());
    }

    private void addAllButtonsToRemainingButtons() {
        remainingButtons.add(n1);
        remainingButtons.add(n2);
        remainingButtons.add(n3);
    }

    private void addButtonsToPanel() {
        for (JButton b: remainingButtons) {
            add(b);
        }
    }

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
