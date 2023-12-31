package ui;

import model.Event;
import model.EventLog;
import model.Game;
import model.GameHistory;
import persistence.JsonReader;
import persistence.JsonWriter;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;

import static java.lang.System.exit;

// Represents the main menu panel
public class MenuPanel extends JPanel {


    private JButton newGameButton;
    private JButton viewHistoryByDateButton;
    private JButton viewHistoryByTimeToSolveButton;
    private JButton saveGameHistoryButton;
    private JButton loadGameHistoryButton;
    private JButton quitButton;
    private Game game;
    private GameHistory gameHistory;

    private CardLayout cl;
    private JPanel panelCont;

    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private static final String JSON_STORE = "./data/gameHistory.json";

    // MODIFIES: game, gameHistory
    // EFFECTS: construct menu with buttons
    public MenuPanel(CardLayout cl, JPanel panelCont, GameHistory gameHistory) {
        this.cl = cl;
        this.panelCont = panelCont;
        game = new Game();
        this.gameHistory = gameHistory;

        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);

        initializeButtons();
        initializePanel();
        setUpAllActionListeners();
        addImage();
    }

    // EFFECTS: add image to panel
    private void addImage() {
        BufferedImage myPicture = null;
        try {
            myPicture = ImageIO.read(new File("menupic.png"));
        } catch (IOException e) {
            System.out.println("Can't read file");
        }
        JLabel picLabel = new JLabel(new ImageIcon(myPicture.getScaledInstance(100, 100, Image.SCALE_FAST)));
        add(picLabel);
    }

    // MODIFIES: this
    // EFFECTS: instantiate all buttons with their names
    private void initializeButtons() {
        newGameButton = new JButton("New Game");
        viewHistoryByDateButton = new JButton("View History By Date");
        viewHistoryByTimeToSolveButton = new JButton("View History By Time To Solve");
        saveGameHistoryButton = new JButton("Save Game History");
        loadGameHistoryButton = new JButton("Load Game History");
        quitButton = new JButton("Quit");
    }

    // MODIFIES: this
    // EFFECTS: add all buttons to this panel
    private void initializePanel() {
        setPreferredSize(new Dimension(game.WIDTH, game.HEIGHT));
        add(newGameButton);
        add(viewHistoryByDateButton);
        add(viewHistoryByTimeToSolveButton);
        add(saveGameHistoryButton);
        add(loadGameHistoryButton);
        add(quitButton);
    }

    // EFFECTS: setup action listeners for all buttons
    public void setUpAllActionListeners() {
        setUpNewGameButton();
        setUpViewHistoryByDateButton();
        setUpViewHistoryByTimeToSolveButton();
        setUpSaveGameButton();
        setUpLoadGameButton();
        setUpQuitButton();
    }

    // EFFECTS: Exit program if quitButton is pressed
    private void setUpQuitButton() {
        quitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                for (Event event : EventLog.getInstance()) {
                    System.out.println(event.toString());
                }
                exit(0);
            }
        });
    }

    // EFFECTS: load game history from data if loadGame button pressed
    private void setUpLoadGameButton() {
        loadGameHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadGameHistory();
            }
        });
    }

    // EFFECTS: save game history if button pressed
    private void setUpSaveGameButton() {
        saveGameHistoryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveGameHistory();
            }
        });
    }

    // EFFECTS: create and show history sorted by time to solve if button pressed
    private void setUpViewHistoryByTimeToSolveButton() {
        viewHistoryByTimeToSolveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelCont.add(new ViewHistoryByTimeToSolvePanel(cl, panelCont, gameHistory), "history by time");
                cl.show(panelCont, "history by time");
            }
        });
    }

    // EFFECTS: create and show history sorted by date if button pressed
    private void setUpViewHistoryByDateButton() {
        viewHistoryByDateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelCont.add(new ViewHistoryByDatePanel(cl, panelCont, gameHistory), "history by date");
                cl.show(panelCont, "history by date");
            }
        });
    }

    // EFFECTS: start new game in new panel if button pressed
    private void setUpNewGameButton() {
        newGameButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                panelCont.add(new FourNumbersPanel(cl, panelCont, gameHistory), "4numbers");
                cl.show(panelCont, "4numbers");
            }
        });
    }

    // EFFECTS: saves the game history to file
    private void saveGameHistory() {
        try {
            jsonWriter.open();
            jsonWriter.write(gameHistory);
            jsonWriter.close();
            System.out.println("Saved current game history to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }

    // MODIFIES: this
    // EFFECTS: loads game history from file
    private void loadGameHistory() {
        try {
            gameHistory = jsonReader.read();
            System.out.println("Loaded previous game history from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }



}
