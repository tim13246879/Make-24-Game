package ui;

import model.GameHistory;

import javax.swing.*;
import java.awt.*;

// Represents panel to view history by time to solve
public class ViewHistoryByTimeToSolvePanel extends ViewHistoryPanel {
    public ViewHistoryByTimeToSolvePanel(CardLayout cl, JPanel panelCont, GameHistory gameHistory) {
        super(cl, panelCont, gameHistory);
    }

    // EFFECTS: sort all games by time to solve
    @Override
    public void sortGames() {
        getGameHistory().sortHistoryByTimeToSolve();
    }
}
