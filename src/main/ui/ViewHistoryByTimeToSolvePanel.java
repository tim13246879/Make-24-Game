package ui;

import model.GameHistory;

import javax.swing.*;
import java.awt.*;

public class ViewHistoryByTimeToSolvePanel extends ViewHistoryPanel {
    public ViewHistoryByTimeToSolvePanel(CardLayout cl, JPanel panelCont, GameHistory gameHistory) {
        super(cl, panelCont, gameHistory);
    }

    @Override
    public void sortGames() {
        getGameHistory().sortHistoryByTimeToSolve();
    }
}
