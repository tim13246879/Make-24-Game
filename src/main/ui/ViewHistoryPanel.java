package ui;

import model.Game;
import model.GameHistory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// Represents a panel that is used to view history
public abstract class ViewHistoryPanel extends JPanel {

    private JButton backToMenu;
    private CardLayout cl;
    private JPanel panelCont;
    private GameHistory gameHistory;

    // EFFECTS: construct panel with buttons and display game history as labels
    public ViewHistoryPanel(CardLayout cl, JPanel panelCont, GameHistory gameHistory) {
        this.cl = cl;
        this.panelCont = panelCont;
        this.gameHistory = gameHistory;
        backToMenu = new JButton("Back To Menu");

        addBackToMenuFunction();
        sortGames();
        displayHistory();
    }

    // EFFECTS: sort games with either date of time to solve
    public abstract void sortGames();

    // EFFECTS: display all games in game history
    private void displayHistory() {
        for (Game g: gameHistory.getGames()) {
            add(new JLabel(g.getSolution() + " || " + g.getTimeToSolve() + " seconds" + " || " + g.getDateTime()));
        }
    }

    // MODIFIES: this
    // EFFECTS: add button to return to main menu
    private void addBackToMenuFunction() {
        backToMenu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelCont, "menu");
            }
        });
        add(backToMenu);
    }

    public GameHistory getGameHistory() {
        return gameHistory;
    }
}
