package testing;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class PanelOne extends JPanel {

    JButton buttonOne;
    CardLayout cl;
    JPanel panelCont;

    PanelOne(CardLayout cl, JPanel panelCont) {
        this.cl = cl;
        this.panelCont = panelCont;
        buttonOne = new JButton("switch to second panel");
        add(buttonOne);
        setBackground(Color.BLUE);

        buttonOne.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cl.show(panelCont, "2");
            }
        });
    }

}
