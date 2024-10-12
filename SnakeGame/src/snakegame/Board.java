package snakegame;

import java.util.*;

public class Board {
	int rows;
	int columns;
	Snake snake;
	
	
//	3rd follow-up: snake grows when it eats food
    private int[] foodPosition;  // (x, y) position of the food
    private Random random;
	
	public Board(int rows,int columns,int initialSnakeSize) {
		this.rows=rows;
		this.columns=columns;
		this.snake=new Snake(Direction.RIGHT,initialSnakeSize);
		
		// 3rd follow up
		this.random=new Random();
		//generateFood();
	}
	
	public void setDirection(Direction direction) {
		this.snake.setDirection(direction);
	}
	
	public boolean move() {
		 if(!this.snake.move() || !isLocationInsideBoard(this.snake.getHeadPosition())) {
			 System.out.println("Game Over");
			 return false;
		 }
		 this.render();
		 /*if(isFoodOnSnake()) { // food eaten by snake
			 System.out.println("Food eaten by snake");
			 //generateFood();
		 } else {
			 snake.removeTail();
		 }*/
		 return true;
	}
	
	private boolean isLocationInsideBoard(Integer[] location) {
		return location[0]>=0 && location[0]<rows && location[1]>=0 && location[1]<columns;
	}
	
	private void generateFood() {
        do {
            foodPosition = new int[]{random.nextInt(this.rows), random.nextInt(this.columns)};
        } while (isFoodOnSnake());
    }
	
	// Check if food is on the snake's body
    private boolean isFoodOnSnake() {
        //return snake.isLocationOnSnakeBody(foodPosition);
    	return false;
    }

    public void render() {
        char[][] board = new char[rows][columns];

        // Fill the board with empty space
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                board[i][j] = '.';
                if(snake.isLocationOnSnakeBody(new int[] {i,j})) {
                		board[i][j]='S'; // Draw the snake on the board
                }
            }
        }
        
        // Place the food
        //board[foodPosition[0]][foodPosition[1]] = 'F';

        // Print the board
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < columns; j++) {
                System.out.print(board[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }
    
    

}
