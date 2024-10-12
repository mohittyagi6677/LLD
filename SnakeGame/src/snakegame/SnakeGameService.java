package snakegame;

import java.util.*;

enum Direction{
	UP,DOWN,LEFT,RIGHT
}
interface ISnakeGame{
	boolean moveSnake(Direction direction);
	boolean isGameOver();
}

public class SnakeGameService implements ISnakeGame{
	Board gameBoard;
	boolean isGameOver;
	private Timer timer;
	public SnakeGameService(int rows,int columns,int initialSnakeSize) {
		this.gameBoard=new Board(rows, columns,initialSnakeSize);
		this.isGameOver=false;
		//startMovement();
	}

	@Override
	public boolean moveSnake(Direction direction) {
		// TODO Auto-generated method stub
		if(!this.isGameOver) {
			this.gameBoard.setDirection(direction);
			this.isGameOver=!this.gameBoard.move();
		} else {
			System.out.println("Can't move since game is over");
		}
		return !this.isGameOver;
	}

	@Override
	public boolean isGameOver() {
		// TODO Auto-generated method stub
		return this.isGameOver;
	}
	
	private void startMovement() {
		timer=new Timer();
		timer.schedule(new TimerTask() {
			
			@Override
			public void run() {
				if(!isGameOver) {
					isGameOver = !gameBoard.move();
					gameBoard.render();  // Render the board after every move
				} else {
					timer.cancel();
				}
				
			}
		}, 0, 1000);//0->start immediately, 1000->repeat after every 1s
	}

}
