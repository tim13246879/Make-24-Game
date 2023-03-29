package testing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelTwo extends JPanel {

    JButton buttonTwo;
    CardLayout cl;
    JPanel panelCont;

    PanelTwo(CardLayout cl, JPanel panelCont) {
        this.cl = cl;
        this.panelCont = panelCont;
        buttonTwo = new JButton("switch to first panel");
        add(buttonTwo);
        setBackground(Color.GREEN);

        buttonTwo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelCont, "1");
            }
        });
    }

}
