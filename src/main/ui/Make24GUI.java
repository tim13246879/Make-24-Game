package ui;

import model.Game;
import model.GameHistory;

import javax.swing.*;
import java.awt.*;

public class Make24GUI {

    private Game game;
    private JFrame frame;
    private JPanel panelCont;
    private CardLayout cl;
    private GameHistory gameHistory;



    public Make24GUI() {
        frame = new JFrame("Make 24!");
        panelCont = new JPanel();
        cl = new CardLayout();
        panelCont.setLayout(cl);

        gameHistory = new GameHistory();


        panelCont.add(new MenuPanel(cl, panelCont, gameHistory), "menu");
        cl.show(panelCont, "menu");

        frame.add(panelCont);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }

//    private void initializeFrame() {
//        add(menuPanel, BorderLayout.CENTER);
//        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
//        pack();
//        setVisible(true);
//    }


    public static void main(String[] args) {
        new Make24GUI();
    }



}
