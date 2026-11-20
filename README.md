# cs2114-project3-group18
A maze solver that creates a maze and solves it efficiently.

## Reflection
### How did your design absorb the change?
When implementing the Jframe, we had to edit the DisplayMaze and RunMaze class the most. Everything we had done previously with scanner was changed to Jframe, so we had to make sure all the other classes were calling the updated methods instead of the old ones that didn't exist anymore. The DisplayMazeTest and RunMazeTest classes also changed since we had to test aspects of a Jframe instead of console outputs.

### Did the change cross the model/view line?
The model/view line was still not crossed as DisplayMaze contrinues to only handle graphics and StoreMaze holds all the information for the maze. The view had to learn the updated way we stored it as a Jframe, along player positioning (playerX and playerY). It also had to learn how to form the display with the new methods like showInputScreen, showMazeScreen, and showPlayAgainButton. The changes did not ripple through places it was not supposed to.

### What changed from your D3 plan?
The most major change from our D3 plan was implementing the Jframe. We used to have it run based on a scanner in DisplayMaze that ran on System.in and System.out. Now, DisplayMaze uses grid rendering with MazePanel, and can change the current screen view depending on the player's progress in the came with showInputScreen, showMazeScreen, and showPlayAgainButton. We also implement keybinds to move around the maze, instead of having to type inputs which would have only worked with the scanner. We also had to remove methods like promptUser() and updateDisplay() as they no longer served a purpose with the Jframe implementation. We had to change the way we handle bad inputs since it was previously evaluated inside the scanner when the user provided input through promptUser(). It is now handled directly in DisplayMaze with text fields. We had to heavily change the tests in DisplayMazeTest and RunMazeTests by adding things like testMazePanel to test the graphics of the Jframe.

### What doesn't work yet?
While our gameplay itself works well, we definitely need to cover more testing that focus on edge cases and bad inputs. We would specifically need to tests if callbacks are triggered and assert they are equal to 0 through the tests, while inputting both valid and invalid heights and widths with widthField.setText() and heighField.setText() methods.

## Next Steps
### What is left for the final build?
For the final build can attempt to implement things like enemies into our gameplay. They could move around the maze and make it harder for the player to reach the finish. Besides that, we really only have to finish up testing.

### Which stretch goals are you committing to, and which are you dropping?
We have committed to allowing the user to solve the maze and having the computer judge the path they chose with stats and efficiency. We also committed to implementing the Jframe instead of the console visual.

### What will your 3-minute demo show?
Our three minute demo will include starting the program, inputting the height and width through the display, then clicking generate maze. Then we will solve the maze and show how the computer checks our efficiency also outputting stats in the display. We will then show how the play again button works and create a maze of completely different dimensions. We can show taking wrong turns in the maze, taking the most efficient path versus the longest path, and also showing bad inputs for inputting maze dimensions.

### Who does what?
We can both help with all but mainly in charge of...
Oliver - Possibly adding enemies
Sophie - Fixing testing

## Attribution
CreateMaze: Oliver -

CreateMazeTest: Oliver -

DisplayMaze: Sophie - I wrote all the methods and reviewed what I wrote after going back and running my code along with asking GenAI for ways of improvement. I also used GenAI to learn more interesting symbols I could make the console display beyond standard keys.

DisplayMazeTest: Sophie - I wrote all the tests in the DisplayMazeTest class. I also went back and added tests for possible exceptions.

Queue: Oliver - 

QueueTest: Oliver -

RunMaze: Sophie - I wrote all the methods and fields for RunMaze. I figured out how to clear and update the console after each move which is something we previously didn't know how to do. I added in how the console will display things like the statistics for time and steps, and a game title at the top of the display.

RunMazeTest: Sophie - I wrote all the test methods in RunMazeTest. I used GenAI to help me figure out how to test a scanner with user input, since I wasn't sure how to effectively cover those tests completely. I updated the tests to cover the new methods after implementing the Jframe.

Solver: Oliver - 

SolverTest: Oliver -

StoreMaze: Oliver

StoreMazeTest: Oliver -

### GenAI note
GenAI was used to assist with things we didn't know like how to test certain methods, improving method design, and discovering a better way to display our idea for the maze in the console.
