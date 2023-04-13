package ui;

import model.Event;
import model.EventLog;
import model.GameHistory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

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
        printLogWhenClose();
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);


    }

    // EFFECTS: print event log when JFrame is closed.
    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    private void printLogWhenClose() {
        frame.addWindowListener(new WindowListener() {
            @Override
            public void windowOpened(WindowEvent e) {

            }

            @Override
            public void windowClosing(WindowEvent e) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.toString());
                }
                System.exit(0);
            }

            @Override
            public void windowClosed(WindowEvent e) {

            }

            @Override
            public void windowIconified(WindowEvent e) {

            }

            @Override
            public void windowDeiconified(WindowEvent e) {

            }

            @Override
            public void windowActivated(WindowEvent e) {

            }

            @Override
            public void windowDeactivated(WindowEvent e) {

            }
        });
    }


    public static void main(String[] args) {
        new Make24GUI();
    }



}
