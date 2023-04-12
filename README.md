# Make 24!

## Introduction

The game of make 24 involves using only the four basic arithmetic operations (addition, subtraction, multiplication or
division) to compute 24 from four random numbers from 1 to 13. Each of the four numbers must be used EXACTLY once.
For example, if the four numbers are 1, 2, 3, and 4, one way to compute 24 is by: 1 * 2 * 3 * 4 = 24. Another way is:
(4 + 2) * (3 + 1) = 24.

In a nutshell, the application will generate 4 random numbers from 1 to 13, and present them to the user. The user will
then apply the arithmetic operations to make 24. If they get stuck solving the problem, they have options to generate
hints and the solution (not in phase 1, but in the future). Once a game is complete, it is saved to game history, which
allows players to look back at their past games. More detailed functions of the application is discussed below in user
stories.

I find this project interesting as I enjoy the game of make 24. Something I used to do a lot is play make 24 with
numbers on car plates when I'm bored on the road. I find it a good way to pass the time as I get rewarded for every game
I solve.

## User Stories

### Essential (realized in phase 2)

- As a user (AAU), I want to have the option to add any completed game to the game history (add an arbitrary number of
  games to game history).
- AAU, I want to view the following for each game in the game history: the 4 random numbers, the method I used to arrive
  at 24, the amount of time I took, and the time and date of completion.
- AAU, I want to know how fast I solved the problem after completing each game.
- AAU, I want to have two ways of viewing my game history. Either based on date of completion or amount of time
  required to reach solution.
- AAU, I want to have the option to save the game history when I quit the application.
- AAU, I want to have the option to continue with my previous game history or to create a new game history when I open
  the application and start a new game.

### Extras (to be implemented after phase 2)

- AAU, I only want to attempt games that are solvable. For example, I do not want to be given the four
  numbers 1, 1, 1, 1, since it is impossible to compute 24 with these four numbers using basic arithmetics.
- AAU, I want to be given hints and/or solutions when I'm stuck.
- AAU, I want to classify my past games based on the amount of time it took me to solve. This can help me identify
  problems that are more difficult for me, which hints at my weaknesses as a competitive player.
- AAU, I want to reattempt problems that took me long periods of time to solve in the past.
- AAU, I want to be able to pause a game, and come back to it later.
- AAU, while a game is paused, I want to be able to attempt other problems before I return to the paused game.
- AAU, I want to be able to have multiple paused games.
- AAU, I want to be able to delete games from my game history, and move one game from the history of one mode to the
  other. This will be helpful if I selected the wrong mode to play in and don't want the game histories of two modes to
  be mixed up.
- AAU, I also want to play recreationally. I want my history in this mode to be separate from my competitive practice
  mode.

## Other information about the project

- This project, especially the ui package, is significantly inspired by the AccountNotRobust program.
- The method of persisting data (including the persistent package and its test) is significantly inspired by the
  JsonSerializationDemo.

## Instructions for Grader - Phase 3

- (*LOCATE VISUAL*) The visual is an image with the four arithmetic operations, and is found as soon as the program runs
  alongside the
  buttons on the start menu.
- Since this program involves playing a game (solving a problem), the grader must play the game and complete it
  before adding "multiple X's to a Y". The steps to playing the game is as follows:
    - From the start menu, first click "new game".
    - 4 numbers will be presented.
    - Before clicking on any button, solve the problem (try to compute 24 using each number exactly once).
      Alternatively, to save time, use http://24solver.us-west-2.elasticbeanstalk.com/ to come up with the solution.
    - Note that it is possible for there to be no solution. In that case, click "try another problem (back to menu)" to
      head back to the menu. Then click "new game" again to start new game.
    - If a solution to the problem is found, start clicking through the numbers and operations to arrive at 24.
- (*GENERATE FIRST REQUIRED ACTION*) Once a game is complete (24 is computed), to "add multiple Xs to a Y", click "save
  game" to save the game to game history.
- (*GENERATE SECOND REQUIRED ACTION*) from start menu, click "view history by date" or "view history by time to solve"
  to sort "Xs" in "Y" in two different ways and displays the "Xs".
- (*SAVE DATA*) from start menu, click "save game history".
- (*RELOAD DATA*) from start menu, click "load game history".

## Phase 4: Task 2

Representative sample of events (after user plays a few games, saves 4 games to game history, and views game history in
both available ways):

Thu Apr 06 16:35:36 PDT 2023\
Game added to game history\
Thu Apr 06 16:36:14 PDT 2023\
Game added to game history\
Thu Apr 06 16:37:25 PDT 2023\
Game added to game history\
Thu Apr 06 16:39:21 PDT 2023\
Game added to game history\
Thu Apr 06 16:39:23 PDT 2023\
Game history sorted by time to solve\
Thu Apr 06 16:39:34 PDT 2023\
Game history sorted by date

## Phase 4: Task 3

Potential refactoring:

1. Create an abstract class called NumbersPanel, and have FourNumbersPanel, ThreeNumbersPanel and TwoNumbersPanel extend
   it. This reduces a lot of duplication in the code and allows for single point of control. Since the three classes all
   access the same instance of EndOfGamePanel, the abstract class can have a protected EndOfGamePanel field, also
   reducing coupling.
2. This isn't shown on the UML diagram, but all JPanel objects (classes that end with "Panel") depend on a single
   instance of CardLayout and another instance of JPanel. These two objects allow the GUI to switch between the panels
   when buttons are pressed. Currently, this is unsatisfactory as the two objects are passed around the program as
   parameters in each class's constructor. A possible solution to this is to create an abstract class that has the two
   instantiated objects as protected fields. All JPanels can then extend it (except ViewHistoryByDatePanel and
   ViewHistoryByTimeToSolvePanel since ViewHistoryPanel would extend it) to gain access to the two objects. 