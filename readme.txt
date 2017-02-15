This is a game designed in java that mimics life, to a certain degree. It is an electronic world of 10*10 grid, where "organisms" can live. Each grid has can have food, and can hold one organism. Each organism can choose its next "move". 
A move can either be to stay, move in any of the four directions, and to reproduce. The organism can make this decision based on some given information about its own cell, and the neighboring cells. 
Food on each cell appears spontaneously. 
Organisms die if it runs out of energy. 
The game has metrics for maximum allowed energy, and the energy required for each of the legal moves. 
In my implementation of the game, there are two player classes - one's a human player that asks for user input for each move. The other's a computer player which uses a "brain" to make decisions. That is to say, I have deisngned the class such that it makes informed decisions about the move to make based on the information that is given to it. The goal is to survive at the end of the game, and possibly survive with maximum offsprings, and maximum food consumed/energy acquired. 
This project was done as a class project for CIT594 at Upenn, with Prof. Swapneel Sheth. 
