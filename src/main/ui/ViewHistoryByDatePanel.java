package ui;

import model.GameHistory;

import javax.swing.*;
import java.awt.*;

// Represents panel to view history by date
public class ViewHistoryByDatePanel extends ViewHistoryPanel {
    public ViewHistoryByDatePanel(CardLayout cl, JPanel panelCont, GameHistory gameHistory) {
        super(cl, panelCont, gameHistory);
    }

    // EFFECTS: sort games by date
    @Override
    public void sortGames() {
        getGameHistory().sortHistoryByDate();
    }
}
