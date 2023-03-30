package ui;

import model.GameHistory;

import javax.swing.*;
import java.awt.*;

public class ViewHistoryByDatePanel extends ViewHistoryPanel {
    public ViewHistoryByDatePanel(CardLayout cl, JPanel panelCont, GameHistory gameHistory) {
        super(cl, panelCont, gameHistory);
    }

    @Override
    public void sortGames() {
        getGameHistory().sortHistoryByDate();
    }
}
