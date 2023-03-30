package ui;

import model.Game;
import model.GameHistory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;

// Represents panel that shows up when a game is complete
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
    private long timeToSolve;
    
    private JButton saveGame;
    private JButton toMenu;

    // EFFECTS: construct panel and sets game and game history.
    public EndOfGamePanel(Game game, CardLayout cl, JPanel panelCont, GameHistory gameHistory) {
        this.game = game;
        setVisible(false);
        this.cl = cl;
        this.panelCont = panelCont;
        this.gameHistory = gameHistory;
    }

    // MODIFIES: game, gameHistory
    // EFFECTS: update game with start time, solution, and time to solve, and add game to game history
    private void updateGameAndGameHistory() {
        game.setDateTime(startTime);
        game.setSolution(solution);
        game.setTimeToSolve(timeToSolve);
        gameHistory.addGame(game);
    }

    // EFFECTS: set up elements of end screen, including all buttons and labels, solution, and time to solve
    public void setUpEndScreen() {
        solution = outputSolution();
        timeToSolve = ChronoUnit.SECONDS.between(startTime, endTime);
        setUpButtonsAndLabels();
    }

    // EFFECTS: return solution in this form: (n1 n2 n3 n4) -> (m1 m2 m3) -> (x1 x2) -> 24
    private String outputSolution() {
        return fourNumbers() + threeNumbers() + twoNumbers() + "24";
    }

    // EFFECTS: return final two numbers
    private String twoNumbers() {
        return "(" + twoRemainingNumbers.get(0) + " " + twoRemainingNumbers.get(1) + ") -> ";
    }

    // EFFECTS: return final three numbers
    private String threeNumbers() {
        return "(" + threeRemainingNumbers.get(0) + " "
                + threeRemainingNumbers.get(1) + " " + threeRemainingNumbers.get(2) + ") -> ";
    }

    // EFFECTS: return all four numbers
    private String fourNumbers() {
        return "(" + game.get(1) + " " + game.get(2) + " " + game.get(3) + " " + game.get(4) + ") -> ";
    }

    public void setThreeRemainingNumbers(List<Double> threeRemainingNumbers) {
        this.threeRemainingNumbers = threeRemainingNumbers;
    }

    public void setTwoRemainingNumbers(List<Double> twoRemainingNumbers) {
        this.twoRemainingNumbers = twoRemainingNumbers;
    }

    public void setStartTime() {
        startTime = LocalDateTime.now();
    }

    public void setEndTime() {
        endTime = LocalDateTime.now();
    }

    // MODIFIES: saveGame, toMenu, this
    // EFFECTS: instantiate buttons and add to this
    public void setUpButtonsAndLabels() {
        add(new JLabel("You got it!"));
        saveGame = new JButton("save game");
        toMenu = new JButton("back to menu");
        add(saveGame);
        add(toMenu);
        add(new JLabel("elapsed time: " + timeToSolve + " seconds"));
        addButtonFunctions();
    }

    // MODIFIES: saveGame, toMenu
    // EFFECTS: add Action listener to each button
    private void addButtonFunctions() {
        saveGame.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateGameAndGameHistory();
                cl.show(panelCont, "menu");
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
