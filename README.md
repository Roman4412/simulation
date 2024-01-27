# Simulation

### Creatures
- The objects of the natural world include predators and herbivores. 
Their goal is to search for food and find the shortest path to it, bypassing obstacles. 
- The logic of the creatures movement is based on the A* algorithm. 
- When the population of herbivores is reduced to a certain limit, new individuals are generated.

### Static Objects
- Rocks, trees, and grass are static objects. 
- Grass, like herbivores, is generated when its quantity is reduced to a certain minimum limit.

## User Interface and Display
![Симуляция](https://media.giphy.com/media/v1.Y2lkPTc5MGI3NjExcGNtNTI0azYxN2Vxam94cDUwaHU2cWdjczhndDZuY3Q1bXJ0NnB4OCZlcD12MV9pbnRlcm5hbF9naWZfYnlfaWQmY3Q9Zw/UnStB2OgBXCNKH8ebL/giphy.gif)

### Display of Entities:

  ![#F2E205](https://placehold.it/15/F2E205/000000?text=) - herbivores

  ![#F20505](https://placehold.it/15/F20505/000000?text=) - predators

  ![#0DF205](https://placehold.it/15/0DF205/000000?text=) - grass

  ![#737156](https://placehold.it/15/737156/000000?text=) - rocks

  ![#078C03](https://placehold.it/15/078C03/000000?text=) - trees


### Available Commands:


  `s` - start the simulation

  `p` - stop the simulation

  `n` - perform a single iteration
  
  `e` - exit


## Application Launch
  To launch the application, you need to:

  1. Install JDK 21 if it is not installed.
  2. Download the repository and unzip it.
  3. Launch the command line.
  4. Prepare the console using the command `chcp 65001`.
  5. Navigate to the project root using the command `cd path to the root`.
  6. Compile the program using the command `javac Main.java`.
  7. Run the program using the command `java Main`.

## Project Motivation
To practice and reinforce object-oriented programming skills.

Technical specification taken from this course https://zhukovsd.github.io/java-backend-learning-course/

## Technologies
The project is developed in Java 21.

## Project Status
The project is on hold indefinitely.
