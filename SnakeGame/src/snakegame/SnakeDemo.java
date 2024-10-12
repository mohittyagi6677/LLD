package snakegame;

public class SnakeDemo {
	
	public static void main(String[] args) {
		SnakeGameService snakeGame= new SnakeGameService(9, 9, 3);
		snakeGame.moveSnake(Direction.RIGHT);
		snakeGame.moveSnake(Direction.RIGHT);
		snakeGame.moveSnake(Direction.RIGHT);
		snakeGame.moveSnake(Direction.DOWN);
		snakeGame.moveSnake(Direction.DOWN);
		snakeGame.moveSnake(Direction.DOWN);
		snakeGame.moveSnake(Direction.LEFT);
		snakeGame.moveSnake(Direction.LEFT);
		snakeGame.moveSnake(Direction.LEFT);
		snakeGame.moveSnake(Direction.LEFT);
		snakeGame.moveSnake(Direction.LEFT);
		//game over move
		snakeGame.moveSnake(Direction.LEFT);
		//can't move since game is over
		snakeGame.moveSnake(Direction.LEFT);
	}

}
