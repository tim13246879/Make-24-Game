# Make 24!

## Introduction

The game of make 24 involves using only the four basic arithmetic operations (addition, subtraction, multiplication or
division) to compute 24 from four random numbers from 1 to 13. Each of the four numbers must be used EXACTLY once.
For example, if the four numbers are 1, 2, 3, and 4, one way to compute 24 is by: 1 * 2 * 3 * 4 = 24. Another way is:
(4 + 2) * (3 + 1) = 24.

In a nutshell, the application will generate 4 random numbers from 1 to 13, and present them to the user. The user will
then apply the arithmetic operations to make 24. If they get stuck solving the problem, they have options to generate
hints and the solution. Once a game is complete, it is saved to game history, which allows players to look back at their
past games. There will be different modes for different users and occasions, including a recreational mode for casual
play with family and friends and a practice mode for competitive players. More detailed functions of the application is
discussed below in user stories.

I find this project interesting as I enjoy the game of make 24. Something I used to do a lot is play make 24 with
numbers on car plates when I'm bored on the road. I find it a good way to pass the time as I get rewarded for every game
I solve.

## User Stories

### Essential
- As a user (AAU), I only want to attempt games that are solvable. For example, I do not want to be given the four 
  numbers 1, 1, 1, 1, since it is impossible to compute 24 with these four numbers using basic arithmetics.
- AAU, I want every played game to be added to my game history (add an arbitrary number of games to game history), which 
  should include all the games I have played. 
- AAU, I want to view the following for each game in the game history: the 4 random numbers, the method I used to arrive at 24,
  and the amount of time I took. 
- AAU, I want to have a timer to tell me how fast I solved each problem.
- AAU, I want to have two ways to sort/view my game history. Either based on date of completion or amount of time required to reach solution.

### Extras
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
- AAU, I also want to play recreationally. I want my history in this mode to be separate from my competitive practice mode.