package ui;

// Represents the user interaction application

import model.Game;
import model.GameHistory;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Make24App {
    private static final String JSON_STORE = "./data/gameHistory.json";
    private GameHistory gameHistory;
    private Scanner input;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;


    // The structure of following code is from the AccountNotRobust program

    // EFFECTS: runs the make 24 application
    public Make24App() throws FileNotFoundException {
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);
        runApp();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runApp() {
        boolean keepGoing = true;
        String command = null;

        init();

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
    }

    // MODIFIES: this
    // EFFECTS: Initialize a game history
    private void init() {
        gameHistory = new GameHistory();
        input = new Scanner(System.in);
        input.useDelimiter("\n");
    }

    // EFFECTS: displays menu of options to user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tn -> new game");
        System.out.println("\th -> view history");
        System.out.println("\ts -> save game history to file");
        System.out.println("\tl -> load game history from file");
        System.out.println("\tq -> quit");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) {
        if (command.equals("n")) {
            startNewGame();
        } else if (command.equals("h")) {
            viewHistory();
        } else if (command.equals("s")) {
            saveGameHistory();
        } else if (command.equals("l")) {
            loadGameHistory();
        } else {
            System.out.println("Selection not valid...");
        }
    }



    // MODIFIES: this
    // EFFECTS: start new game and prompt user to compute 24. If the user does not reach 24 after a series of
    // computations, restart the same game to allow the user to try again.
    private void startNewGame() {
        Game game = new Game();
        double finalNumber = 0;
        int n1 = game.get(1);
        int n2 = game.get(2);
        int n3 = game.get(3);
        int n4 = game.get(4);
        long startTime = System.currentTimeMillis();
        while (finalNumber != 24) {
            List<Double> remainingThreeNumbers = firstOperation(n1, n2, n3, n4);
            double newN1 = remainingThreeNumbers.get(0);
            double newN2 = remainingThreeNumbers.get(1);
            double newN3 = remainingThreeNumbers.get(2);
            List<Double> remainingTwoNumbers = secondOperation(newN1, newN2, newN3);
            double finalN1 = remainingTwoNumbers.get(0);
            double finalN2 = remainingTwoNumbers.get(1);
            finalNumber = startOperation(finalN1, finalN2);
            if (finalNumber != 24) {
                System.out.println("Hmmmm... That doesn't equal 24. Try Again!");
            } else {
                System.out.println("That's 24! Well Done!");
                updateGameAndGameHistory(n1, n2, n3, n4, newN1, newN2, newN3, finalN1, finalN2, startTime, game);
            }
        }
    }


    // EFFECTS: completes first operation. The current game should only have 3 numbers remaining for operation after
    // this method finishes running. Return the 3 remaining numbers in a list.
    private List<Double> firstOperation(int n1, int n2, int n3, int n4) {
        displayFourRandomNumbers(n1, n2, n3, n4);
        int inputNumber1 = input.nextInt();
        double chosenNumber1 = chooseOneFromFourNumbers(n1, n2, n3, n4, inputNumber1);
        int rn1 = firstRemainingNumberAfterSelectingFromFour(n1, n2, n3, n4, inputNumber1);
        int rn2 = secondRemainingNumberAfterSelectingFromFour(n1, n2, n3, n4, inputNumber1);
        int rn3 = thirdRemainingNumberAfterSelectingFromFour(n1, n2, n3, n4, inputNumber1);
        displayThreeRemainingNumbers(rn1, rn2, rn3);
        int inputNumber2 = input.nextInt();
        double chosenNumber2 = chooseOneFromThreeNumbers(rn1, rn2, rn3, inputNumber2);
        double nrn1 = firstRemainingNumberAfterSelectingFromThree(rn1, rn2, rn3, inputNumber2);
        double nrn2 = secondRemainingNumberAfterSelectingFromThree(rn1, rn2, rn3, inputNumber2);
        System.out.println("The two numbers chosen for this operation is: " + chosenNumber1 + " and " + chosenNumber2);
        double newNumber = startOperation(chosenNumber1, chosenNumber2);
        displayThreeRemainingNumbersAfterFirstOperation(newNumber, nrn1, nrn2);
        List<Double> lod = Arrays.asList(newNumber, nrn1, nrn2);
        return lod;
    }

    // EFFECTS: completes second operation. The current game should only have 2 numbers remaining for operation after
    // this method finishes running. Return the two remaining numbers in a list.
    private List<Double> secondOperation(double newN1, double newN2, double newN3) {
        System.out.println("\nSelect the first number to operate on (only input 1, 2 or 3)");
        int inputNumber1 = input.nextInt();
        double chosenNumber1 = chooseOneFromThreeNumbers(newN1, newN2, newN3, inputNumber1);
        double rn1 = firstRemainingNumberAfterSelectingFromThree(newN1, newN2, newN3, inputNumber1);
        double rn2 = secondRemainingNumberAfterSelectingFromThree(newN1, newN2, newN3, inputNumber1);
        displayTwoRemainingNumbers(rn1, rn2);
        int inputNumber2 = input.nextInt();
        double chosenNumber2 = chooseOneFromTwoNumbers(rn1, rn2, inputNumber2);
        double nrn1 = onlyRemainingNumberAfterSelectingFromTwo(rn1, rn2, inputNumber2);
        System.out.println("The two numbers chosen for this operation is: " + chosenNumber1 + " and " + chosenNumber2);
        double newNumber = startOperation(chosenNumber1, chosenNumber2);
        displayTwoRemainingNumbersAfterSecondOperation(newNumber, nrn1);
        List<Double> lod = Arrays.asList(newNumber, nrn1);
        return lod;
    }

    // EFFECTS: Prompts user to choose operation to operate on the two consumed numbers. Return the new number that is
    // computed using the consumed numbers and operation.
    private double startOperation(double chosenNumber1, double chosenNumber2) {
        displayOperations();
        String chosenOperation = chooseOperation();
        System.out.println("Operating: " + chosenNumber1 + " " + chosenOperation + " " + chosenNumber2);
        double newNumber = completeOperation(chosenNumber1, chosenNumber2, chosenOperation);
        System.out.println("That equals: " + newNumber);
        return newNumber;
    }

    // REQUIRES: user input must be one of 1, 2, 3 or 4
    // EFFECTS: prompts user to choose one of four operations. Return chosen operation.
    private String chooseOperation() {
        int chosenOperation = input.nextInt();
        if (chosenOperation == 1) {
            System.out.println("The operation chosen is: +");
            return "+";
        } else if (chosenOperation == 2) {
            System.out.println("The operation chosen is: -");
            return "-";
        } else if (chosenOperation == 3) {
            System.out.println("The operation chosen is: *");
            return "*";
        } else {
            System.out.println("The operation chosen is: /");
            return "/";
        }
    }

    // REQUIRES: operation must be one of "+", "-", "*", "/"
    // EFFECTS: complete operation using two numbers and operation
    private double completeOperation(double chosenNumber1, double chosenNumber2, String operation) {
        if (operation.equals("+")) {
            return chosenNumber1 + chosenNumber2;
        } else if (operation.equals("-")) {
            return chosenNumber1 - chosenNumber2;
        } else if (operation.equals("*")) {
            return chosenNumber1 * chosenNumber2;
        } else {
            return chosenNumber1 / chosenNumber2;
        }
    }

    // REQUIRES: User only inputs 1 or 2 when prompted to input number
    // MODIFIES: this
    // EFFECTS: prompt user to choose from two ways of sorting, either sorting by time and date of completion OR amount
    // of time required to compute 24. Sort game history according to user input.
    // Then, for each game in game history, print the following:
    // (a) the 4 numbers,
    // (b) the solution used by user to arrive at 24
    // (c) amount of time it took the user to arrive at 24,
    // (d) date and time of game
    private void viewHistory() {
        System.out.println("\tSort by (input only 1 or 2): ");
        System.out.println("\n1. Date and time of completion");
        System.out.println("\n2. Required time to complete game");
        int inputNumber = input.nextInt();
        if (inputNumber == 1) {
            gameHistory.sortHistoryByDate();
        } else {
            gameHistory.sortHistoryByTimeToSolve();
        }
        for (Game g: gameHistory.getGames()) {
            System.out.println(g.getSolution() + " || " + g.getTimeToSolve() + " seconds" + " || " + g.getDateTime());
        }
    }

    // MODIFIES: this, game
    // EFFECTS: (a) Stop timer and update game object with amount of time required to complete the problem
    //          (b) Print message regarding amount of time required to solve problem
    //          (c) Update game object with solution
    //          (d) Update game object with date and time of completion
    //          (e) Add this game to game history
    private void updateGameAndGameHistory(int n1, int n2, int n3, int n4, double newN1, double newN2, double newN3,
                                          double finalN1, double finalN2, long startTime, Game game) {
        long endTime = System.currentTimeMillis();
        double timeElapsed = (double) (endTime - startTime) / 1000;
        game.setTimeToSolve(timeElapsed);
        System.out.println("Total elapsed time: " + timeElapsed + " seconds");
        String solution = "(" + n1 + " " + n2 + " " + n3 + " " + n4 + ")" + " -> "
                + "(" + newN1 + " " + newN2 + " " + newN3 + ")" + " -> "
                + "(" + finalN1 + " " + finalN2 + ")" + " -> " + "24";
        game.setSolution(solution);
        LocalDateTime dateTimeNow = LocalDateTime.now();
        game.setDateTime(dateTimeNow);
        gameHistory.addGame(game);
    }

    // REQUIRES: inputNumber must be one of 1, 2, 3 or 4
    // EFFECTS: prompts user to choose a number from 4, returns the chosen number, and prints a statement to verify it
    private int chooseOneFromFourNumbers(int n1, int n2, int n3, int n4, int inputNumber) {
        if (inputNumber == 1) {
            System.out.println("The number chosen is: " + n1);
            return n1;
        } else if (inputNumber == 2) {
            System.out.println("The number chosen is: " + n2);
            return n2;
        } else if (inputNumber == 3) {
            System.out.println("The number chosen is: " + n3);
            return n3;
        } else {
            System.out.println("The number chosen is: " + n4);
            return n4;
        }
    }

    // REQUIRES: inputNumber must be one of 1, 2 or 3
    // EFFECTS: prompts user to choose a number from 3, returns the chosen number, and prints a statement to verify it
    private double chooseOneFromThreeNumbers(double n1, double n2, double n3, int inputNumber) {
        if (inputNumber == 1) {
            System.out.println("The number chosen is: " + n1);
            return n1;
        } else if (inputNumber == 2) {
            System.out.println("The number chosen is: " + n2);
            return n2;
        } else {
            System.out.println("The number chosen is: " + n3);
            return n3;
        }
    }

    // REQUIRES: inputNumber must either be 1 or 2
    // EFFECTS: prompts user to choose a number from 2, returns the chosen number, and prints a statement to verify it
    private double chooseOneFromTwoNumbers(double rn1, double rn2, int inputNumber2) {
        if (inputNumber2 == 1) {
            System.out.println("The number chosen is: " + rn1);
            return rn1;
        } else {
            System.out.println("The number chosen is: " + rn2);
            return rn2;
        }
    }

    // REQUIRES: inputNumber must be either 1, 2, 3 or 4
    // EFFECTS: Return the first of three remaining numbers after selecting one number from four.
    private int firstRemainingNumberAfterSelectingFromFour(int n1, int n2, int n3, int n4, int inputNumber) {
        if (inputNumber == 1) {
            return n2;
        } else {
            return n1;
        }
    }

    // REQUIRES: inputNumber must be either 1, 2, 3 or 4
    // EFFECTS: Return the second of three remaining numbers after selecting one number from four.
    private int secondRemainingNumberAfterSelectingFromFour(int n1, int n2, int n3, int n4, int inputNumber) {
        if (inputNumber == 1 || inputNumber == 2) {
            return n3;
        } else {
            return n2;
        }
    }

    // REQUIRES: inputNumber must be either 1, 2, 3 or 4
    // EFFECTS: Return the third of three remaining numbers after selecting one number from four.
    private int thirdRemainingNumberAfterSelectingFromFour(int n1, int n2, int n3, int n4, int inputNumber) {
        if (inputNumber == 4) {
            return n3;
        } else {
            return n4;
        }
    }

    // REQUIRES: inputNumber must be either 1, 2 or 3
    // EFFECTS: Return the first of two remaining numbers after selecting one number from three.
    private double firstRemainingNumberAfterSelectingFromThree(double rn1, double rn2, double rn3, int inputNumber) {
        if (inputNumber == 1) {
            return rn2;
        } else {
            return rn1;
        }
    }

    // REQUIRES: inputNumber must be either 1, 2 or 3
    // EFFECTS: Return the second of two remaining numbers after selecting one number from three.
    private double secondRemainingNumberAfterSelectingFromThree(double rn1, double rn2, double rn3, int inputNumber) {
        if (inputNumber == 3) {
            return rn2;
        } else {
            return rn3;
        }
    }

    // REQUIRES: inputNumber2 must be either 1 or 2
    // EFFECTS: returns the only number remaining after choosing one number from two
    private double onlyRemainingNumberAfterSelectingFromTwo(double rn1, double rn2, int inputNumber2) {
        if (inputNumber2 == 1) {
            return rn2;
        } else {
            return rn1;
        }
    }

    // EFFECTS: print out the remaining 3 numbers that can be chosen after first number has been chosen from 4.
    private void displayThreeRemainingNumbers(int rn1, int rn2, int rn3) {
        System.out.println("\nSelect the second number to operate on (only input 1, 2 or 3)");
        System.out.println("\t1. " + rn1);
        System.out.println("\t2. " + rn2);
        System.out.println("\t3. " + rn3);
    }

    // EFFECTS: print out the remaining 2 numbers that can be chosen after first number has been chosen from 3.
    private void displayTwoRemainingNumbers(double rn1, double rn2) {
        System.out.println("\nSelect the second number to operate on (only input 1 or 2):");
        System.out.println("\t1. " + rn1);
        System.out.println("\t2. " + rn2);
    }

    // EFFECTS: prints the three numbers remaining after computing first operation
    private void displayThreeRemainingNumbersAfterFirstOperation(double newNumber, double nrn1, double nrn2) {
        System.out.println("\nThe three numbers remaining after the first operation are: ");
        System.out.println("\t1. " + newNumber);
        System.out.println("\t2. " + nrn1);
        System.out.println("\t3. " + nrn2);
    }

    // EFFECTS: prints the two numbers remaining after computing second operation
    private void displayTwoRemainingNumbersAfterSecondOperation(double newNumber, double nrn1) {
        System.out.println("\nThe two numbers remaining after the second operation are: ");
        System.out.println("\t1. " + newNumber);
        System.out.println("\t2. " + nrn1);

    }


    // EFFECTS: display all four operations for user to choose from.
    private void displayOperations() {
        System.out.println("\nChoose an operation to operate on the 2 numbers (input only one of 1, 2, 3 or 4): ");
        System.out.println("\t1. +");
        System.out.println("\t2. -");
        System.out.println("\t3. *");
        System.out.println("\t4. /");
    }


    // EFFECTS: Display options for the four numbers
    private void displayFourRandomNumbers(int n1, int n2, int n3, int n4) {
        System.out.println("\nCompute 24 using the following 4 numbers:");
        System.out.println("\t1. " + n1);
        System.out.println("\t2. " + n2);
        System.out.println("\t3. " + n3);
        System.out.println("\t4. " + n4);
        System.out.println("\nSelect the first number to operate on (only input 1, 2, 3 or 4)");
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
