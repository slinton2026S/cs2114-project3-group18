# cs2114-project3-group18
A maze solver that creates a maze and solves it efficiently.

## Reflection
### What changed from your specification?
The spec planned for the display to only update after it was solved, but we changed it to update after every move to have a more animated look. We also changed the symbols that would be used to display the maze in the console after finding a more interesting way to display the maze. The possible dimensions of the maze were changed to be anything above 3.

### Did the model really stand alone?
The model classes StoreMaze, CreateMaze, and Solver are independent from the Display class. The model classes do not call the display, they just use the stored information from the grid.

### What happened to your unknowns?
We learned how to create what appears as an animation using a while loop and pausing the code so it updates a slower frame rate. We also learned how to clear the console just by using a large loop that consistently prints empty lines to reset the console more cleanly.

### What doesn't work yet?
We have not yet implemented things like enabling the user to restart the game once it finishes. This would require us to change the way the scanner works within the loop. We also have not worked to implement the stretch goal of allowing the user to personally edit the maze/ solve the maze themselves.

## Next Steps
### What is left to finish the MVP?
To fix possible errors in the scanner that could make the loop close too early, especially when adding the "Play Again" option. We also need to further test and catch errors that would happen in the case of an unsolvable maze. We have not debugged enough to find where these errors occur.

### Which stretch goals will you attempt?
We will most likely attempt letting the user solve the maze with some simple challenges, and if we have extra time we will attempt letting the user edit the maze.

### What new tests will you need?
We will need to test the restart option and make sure the tests already written for the scanner continue to cover everything after the change. We also need more tests covering invalid inputs like letters or numbers that are too large. We will need to write more boundary tests and tests that confirm our saved data is accurate like elapsed time and total steps. Finally we need to ensure that the grid/maze is displayed properly in console and provides a clean picture for the user.

### Who does what?
Sophie - Clean up the scanner loop, implement the "play again" option, write more tests in RunMazeTest for edge cases and other invalid inputs, fix the console display to be more visually appealing for the user.
Oliver - Move the display to a JFrame object so that user input can be used to control the character and the gameplay can be implemented.

## Attribution
CreateMaze:

CreateMazeTest:

DisplayMaze: Sophie - I wrote all the methods and reviewed what I wrote after going back and running my code along with asking GenAI for ways of improvement. I also used GenAI to learn more interesting symbols I could make the console display beyond standard keys.

DisplayMazeTest: Sophie - I wrote all the tests in the DisplayMazeTest class. I also went back and added tests for possible exceptions.

Queue:

QueueTest:

RunMaze: Sophie - I wrote all the methods and fields for RunMaze. I figured out how to clear and update the console after each move which is something we previously didn't know how to do. I added in how the console will display things like the statistics for time and steps, and a game title at the top of the display.

RunMazeTest: Sophie - I wrote all the test methods in RunMazeTest. I used GenAI to help me figure out how to test a scanner with user input, since I wasn't sure how to effectively cover those tests completely.

Solver:

SolverTest:

StoreMaze:

StoreMazeTest:

### GenAI note
GenAI was used to assist with things we didn't know like how to test certain methods, improving method design, and discovering a better way to display our idea for the maze in the console.
