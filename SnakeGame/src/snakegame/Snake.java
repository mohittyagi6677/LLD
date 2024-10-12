package snakegame;

import java.util.*;

public class Snake {
	Direction direction;
	Deque<Integer[]> body;
	Set<String> visited;
	final int moveThresholdForSize=5;
	int totalMoves=0;
	
	public Snake(Direction direction,int initialSize) {
		this.direction=direction;
		body=new LinkedList<Integer[]>();
		visited = new HashSet<String>();
		for(int i=0;i<initialSize;i++) {
			body.add(new Integer[] {0,i});
			visited.add(0+","+i);
		}
	}
	
	public void setDirection(Direction newDirection) {
		if((this.direction==Direction.RIGHT && newDirection!=Direction.LEFT) || (this.direction==Direction.LEFT && newDirection!=Direction.RIGHT)
				|| (this.direction==Direction.UP && newDirection!=Direction.DOWN) ||(this.direction==Direction.DOWN && newDirection!=Direction.UP)) {
			this.direction=newDirection;
		}
	}
	
	public Integer[] getHeadPosition() {
		return this.body.peekLast();
	}
	
	public boolean move() {
		Integer[] currentHead = getHeadPosition();
		Integer[] newHead = new Integer[2];
		newHead[0]=currentHead[0];
		newHead[1]=currentHead[1];
		switch(this.direction) {
			case Direction.UP:
				newHead[0]--;
				break;
			case Direction.DOWN:
				newHead[0]++;
				break;
			case Direction.LEFT:
				newHead[1]--;
				break;
			case Direction.RIGHT:
				newHead[1]++;
				break;
		}
		
		this.totalMoves++;
		if(this.totalMoves%moveThresholdForSize!=0) {
			removeTail(); // important to handle the tail first so as to detect possible collision in the move
		} 
		
		
		if(visited.contains(newHead[0]+","+newHead[1])) {
			return false;
		}
		body.addLast(newHead);
		System.out.println("Added new head: "+newHead[0]+","+newHead[1]);
		visited.add(newHead[0]+","+newHead[1]);
		
		return true;
		
	}
	
	public void removeTail() {
		Integer[] tail = this.body.pollFirst();
		visited.remove(tail[0]+","+tail[1]);
	}
	
	public boolean isLocationOnSnakeBody(int[] location) {
    		return visited.contains(location[0]+","+location[1]);
    }

}
