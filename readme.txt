Included files: 
1. Cell.java
2. ComputerPlayer.ava
3. Constants.java
4. GameConfig.java5. GameConfiguration.java6. GameHelper.java7. HumanPlayer.java8. IndividualOrganismData.java9. Main.java10. Move.java11. OrganismsGame.java12. OrganismsGameInterface.java13. Player.java14. PlayerData.java15. PlayerRoundData.java16. Point.java17. RandomPlayer.java





My organisms game follows the rules given in the question PDF. 
Other than the mandatory classes and interface implementations, I have the following extra classes: 
1. Cell - It represents a physical cell in the grid of the game. It holds certain attributes, such as - if the cell is occupied, who is occupying it, the x-y coordinate of the cell, and the amount of food left on the cell. It is important to note that only the OrganismsGame has access to this class, and its objects. 

2. I decided not to have a Grid class that holds all the cells together. Instead, my OrganismsGame class holds a 2d arraylist of cells that make up the grid. 

3. Point - I also have a separate class for Points on the cell - these are essentially just the x and y coordinate of the cell. In retrospect, I could have merged this into the cell class, but it makes the cell class less cluttered. 

4. GameHelper - In order to conduct the game with all its rules, I created a separate class called the GameHelper, that implements the rules of the game. Once again, only the OrganismsGame class has access to this class. It has methods that do things like - scatter food randomly on grid, place players on the grid, generate direction commands, generate east/west/north/south cells etc. 
	a. I did not use the CXTRans and CYTrans arrays from the Constants class, and created my own methods to generate the east/west/north/south points in this game. I did so because in my grid my rows map to x, and columns map to y, whereas in the given array, its flipped. 
	b. This class also helps me DRY out the code for the OrganismsGame class a lot. It also abstracts out the finer functionalities of the game. 

5. IndividualOrgnanismData - this is the layer that lies between the player and the final results. Since the player can reproduce, and each of its offsprings will have a separate set of energy and cell that it occupies, I needed a class to keep track of that. This class is also only available to the OrganismsGame class. Because each organism that springs from an original player shares the key with its parent, I use that to create a link between the original player and all of its generations. I have a HashMap of Player to IndividualOrganismData, and another HashMap of player and their key/ID, that help me put it all together to get the final results. 

6. I unfortunately did not get to writing test code for this assignment. 

7. The HumanPlayer class is asked for input each time, and if the input does not match the required numbers, the organism loses the move for the round and is forced to stay put. 

8. My ComputerPlayer implements a brain, that uses the information given to it to make informed decisions about the next move to survive the game. 

9. My GameConfig implementation has constant values assigned to the various parameters for the game. 

10. I decided to register the players in my OrganismsGame class, as opposed to the Main, as it seemed closer to real life. It should be up to the game to register its players, or atleast the person conducting the game. 

11. Also note that my PlayerRoundData implementation is not updated after every round, it is only updated at the end of the game - which makes more sense to me. There is no way for someone to access the results from outside of the game for each round, it can only happen once. 

Extra Credit:

My ComputerPlayer class should be used to check for extra credit. 




