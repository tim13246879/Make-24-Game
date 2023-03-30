package ui;

import model.Game;
import model.GameHistory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public abstract class ViewHistoryPanel extends JPanel {

    private JButton backToMenu;
    private CardLayout cl;
    private JPanel panelCont;
    private GameHistory gameHistory;

    public ViewHistoryPanel(CardLayout cl, JPanel panelCont, GameHistory gameHistory) {
        this.cl = cl;
        this.panelCont = panelCont;
        this.gameHistory = gameHistory;
        backToMenu = new JButton("Back To Menu");

        addBackToMenuFunction();
        sortGames();
        displayHistory();
    }

    public abstract void sortGames();

    private void displayHistory() {
        for (Game g: gameHistory.getGames()) {
            add(new JLabel(g.getSolution() + " || " + g.getTimeToSolve() + " seconds" + " || " + g.getDateTime()));
        }
    }

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
