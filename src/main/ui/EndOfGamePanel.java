package ui;

import model.Game;
import model.GameHistory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.List;

public class EndOfGamePanel extends JPanel {
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Game game;
    private GameHistory gameHistory;
    private List<Double> threeRemainingNumbers;
    private List<Double> twoRemainingNumbers;
    private String solution;
    private CardLayout cl;
    private JPanel panelCont;
    
    private JButton saveGame;
    private JButton toMenu;

    public EndOfGamePanel(Game game, CardLayout cl, JPanel panelCont) {
        this.game = game;
        setVisible(false);
        this.cl = cl;
        this.panelCont = panelCont;
    }

    public void setUpScreen() {
        solution = outputSolution();
        setUpButtonsAndLabels();
    }

    private String outputSolution() {
        return fourNumbers() + threeNumbers() + twoNumbers() + "24";
    }

    private String twoNumbers() {
        return "(" + twoRemainingNumbers.get(0) + " " + twoRemainingNumbers.get(1) + ") -> ";
    }

    private String threeNumbers() {
        return "(" + threeRemainingNumbers.get(0) + " "
                + threeRemainingNumbers.get(1) + " " + threeRemainingNumbers.get(2) + ") -> ";
    }

    private String fourNumbers() {
        return "(" + game.getN1() + " " + game.getN2() + " " + game.getN3() + " " + game.getN4() + ") -> ";
    }

    public void setThreeRemainingNumbers(List<Double> threeRemainingNumbers) {
        this.threeRemainingNumbers = threeRemainingNumbers;
    }

    public void setTwoRemainingNumbers(List<Double> twoRemainingNumbers) {
        this.twoRemainingNumbers = twoRemainingNumbers;
    }

    public void setStartTime(LocalDateTime startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(LocalDateTime endTime) {
        this.endTime = endTime;
    }

    public void setUpButtonsAndLabels() {
        add(new JLabel("You got it!"));
        saveGame = new JButton("save game");
        toMenu = new JButton("back to menu");
        add(saveGame);
        add(toMenu);
        addButtonFunctions();
    }

    private void addButtonFunctions() {
        saveGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });
        toMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelCont, "menu");
            }
        });
    }
}
