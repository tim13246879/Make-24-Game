package testing;

import javax.swing.*;
import java.awt.*;

public class Clayout {
    JFrame frame = new JFrame("CardLayout demo");
    JPanel panelCont = new JPanel();

    CardLayout cl = new CardLayout();

    public Clayout() {
        panelCont.setLayout(cl);

        panelCont.add(new PanelOne(cl, panelCont), "1");
        panelCont.add(new PanelTwo(cl, panelCont), "2");
        cl.show(panelCont, "1");


        frame.add(panelCont);
        frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        frame.pack();
        frame.setVisible(true);
    }

    public static void main(String[] args) {
        new Clayout();
    }

}
