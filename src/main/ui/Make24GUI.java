package ui;

import model.GameHistory;

import javax.swing.*;
import java.awt.*;

// Represents the frame that holds all the panels
public class Make24GUI {

    private JFrame frame;
    private JPanel panelCont;
    private CardLayout cl;
    private GameHistory gameHistory;

    // EFFECTS: construct main frame and add card layout panel.
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


    public static void main(String[] args) {
        new Make24GUI();
    }



}
