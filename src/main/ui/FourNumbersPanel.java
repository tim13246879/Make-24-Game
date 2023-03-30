package ui;

import model.Game;
import model.GameHistory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

// Represents panel when displaying all four numbers at beginning
public class FourNumbersPanel extends JPanel {
    private JButton n1;
    private JButton n2;
    private JButton n3;
    private JButton n4;
    private Game game;
    private CardLayout cl;
    private JPanel panelCont;
    private List<JButton> selectedButtons = new ArrayList<>();
    private List<JButton> remainingButtons = new ArrayList<>();
    private List<JLabel> chosenNumbers = new ArrayList<>();
    private EndOfGamePanel endScreen;


    // EFFECTS: construct panel with buttons and start new game
    public FourNumbersPanel(CardLayout cl, JPanel panelCont, GameHistory gameHistory) {
        this.cl = cl;
        this.panelCont = panelCont;
        game = new Game();
        initializeButtons();
        addAllButtonsToRemainingButtons();
        addButtonsToPanel();
        addFunctionForAllButtons();
        endScreen = new EndOfGamePanel(game, cl, panelCont, gameHistory);
        panelCont.add(endScreen, "end screen");
        endScreen.setStartTime();
    }

    // MODIFIES: n1, n2, n3, n4
    // EFFECTS: add action listener to all functions
    private void addFunctionForAllButtons() {
        addButtonFunctions(n1);
        addButtonFunctions(n2);
        addButtonFunctions(n3);
        addButtonFunctions(n4);
    }

    // MODIFIES: n1, n2, n3, n4, this
    // EFFECTS: instantiate buttons
    private void initializeButtons() {
        n1 = new JButton(Integer.toString(game.getN1()));
        n2 = new JButton(Integer.toString(game.getN2()));
        n3 = new JButton(Integer.toString(game.getN3()));
        n4 = new JButton(Integer.toString(game.getN4()));
        addBackToMenuButton();
    }

    // MODIFIES: this
    // EFFECTS: add a button that takes user back to main menu
    private void addBackToMenuButton() {
        JButton backToMenu = new JButton("Try another problem (back to menu)");
        add(backToMenu);
        backToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelCont, "menu");
                resetPanel();
            }
        });
    }

    // MODIFIES: remainingButtons
    // EFFECTS: Add all number buttons to remainingButtons
    private void addAllButtonsToRemainingButtons() {
        remainingButtons.add(n1);
        remainingButtons.add(n2);
        remainingButtons.add(n3);
        remainingButtons.add(n4);
    }

    // MODIFIES: this
    // EFFECTS: add all buttons to panel
    private void addButtonsToPanel() {
        for (JButton b : remainingButtons) {
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
                JLabel chosenNumber = new JLabel("Number chosen: " + button.getText());
                add(chosenNumber);
                chosenNumbers.add(chosenNumber);
                if (selectedButtons.size() == 2) {
                    JPanel opPanel = new OperationPanel(cl, panelCont, selectedButtons, remainingButtons, endScreen);
                    panelCont.add(opPanel, "operation");
                    cl.show(panelCont, "operation");
                    resetPanel();
                }
            }
        });
    }

    // MODIFIES: this
    // EFFECTS: reset panel to what it was before any button was pressed
    private void resetPanel() {
        for (JButton b : selectedButtons) {
            b.setVisible(true);
        }
        for (JLabel l : chosenNumbers) {
            l.setVisible(false);
        }
        selectedButtons = new ArrayList<>();
        remainingButtons = new ArrayList<>();
        addAllButtonsToRemainingButtons();
    }


}
