package ui;

import model.Game;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MenuPanel extends JPanel {


    private JButton newGameButton;
    private JButton viewHistoryButton;
    private JButton saveGameButton;
    private JButton loadGameButton;
    private JButton quitButton;
    private Game game;

    private CardLayout cl;
    private JPanel panelCont;
    private EndOfGamePanel endScreen;

    public MenuPanel(CardLayout cl, JPanel panelCont) {
        this.cl = cl;
        this.panelCont = panelCont;
        game = new Game();

        initializeButtons();
        initializePanel();
        setUpActionListeners();
    }


    private void initializeButtons() {
        newGameButton = new JButton("New Game");
        viewHistoryButton = new JButton("View History");
        saveGameButton = new JButton("Save Game");
        loadGameButton = new JButton("Load Game");
        quitButton = new JButton("Quit");
    }

    private void initializePanel() {
        setPreferredSize(new Dimension(game.WIDTH, game.HEIGHT));
        add(newGameButton);
        add(viewHistoryButton);
        add(saveGameButton);
        add(loadGameButton);
        add(quitButton);
    }



    public void setUpActionListeners() {
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelCont.add(new FourNumbersPanel(cl, panelCont), "4numbers");
                cl.show(panelCont, "4numbers");
            }
        });
    }



}
