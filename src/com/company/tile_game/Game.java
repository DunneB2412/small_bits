package com.company.tile_game;

import java.util.Random;
import java.util.Scanner;

public class Game {

	public static void main(String[] args) {
		int[] score = new int[] {0};// so as it's a complex data type and 'call by refference'
		Scanner systemIn = new Scanner(System.in);
		int size = getInt(systemIn, "What size do you want the grid to be?[4->8]");
		while(size<4||size>8) {
			size = getInt(systemIn, "What size do you want the grid to be?[4->8]\n"
					+ size + (size<4? " is to small": " is to big"));
		}
		int[][][] game = initiliseGame(size, 2);
		int turn = 0;
		while(true){
			int moveId;
			do {	
				moveId = getMoveSimple(systemIn, game, "Turn:"+turn+", Score:"+score[0], "\n1/w: Up\n2/s: Down\n3/a: Left\n4/d: right");
			}while(!applyMove(moveId, game, score));
			
			if(!fillRandomEmptyTile(game)) {
				showGame(game,"you Lose!!!");
				break;
			}
			turn++;
		}
		
		systemIn.close();
	}
	
	private static boolean applyMove(int moveId, int[][][] game, int[] score) {
		switch (moveId) {
		case 1:
			return applyUp(game, score);
		case 2:
			return applyDown(game, score);
		case 3:
			return applyLeft(game, score);
		case 4:
			return applyRight(game, score);
		default:
			return false;
		}
	}
		
	private static boolean applyRight(int[][][] game, int[] score) {
		boolean hasMoved = false;
		for(int x=game[0].length-2; x>=0;x--) {
			for(int y=0; y<game.length; y++) {
				if(handleTileMovement(new int[] {y,x}, new int[] {0,1}, game, score)) {
					hasMoved = true;
				}
			}
		}
		return hasMoved;
	}
	private static boolean applyLeft(int[][][] game, int[] score) {
		boolean hasMoved = false;
		for(int x=1; x<game[0].length;x++) {
			for(int y=0; y<game.length; y++) {
				if(handleTileMovement(new int[] {y,x}, new int[] {0,-1}, game, score)) {
					hasMoved = true;
				}
			}
		}
		return hasMoved;
	}
	private static boolean applyDown(int[][][] game, int[] score) {
		boolean hasMoved = false;
		for(int y=game.length-2; y>=0; y--) {
			for(int x=0; x<game[y].length;x++) {
				if(handleTileMovement(new int[] {y,x}, new int[] {1,0}, game, score)) {
					hasMoved = true;
				}
			}
		}
		return hasMoved;
	}
	private static boolean applyUp(int[][][] game, int[] score) {
		boolean hasMoved = false;
		for(int y=1; y<game.length; y++) {
			for(int x=0; x<game[y].length;x++) {
				if(handleTileMovement(new int[] {y,x}, new int[] {-1,0}, game, score)) {
					hasMoved = true;
				}
			}
		}
		return hasMoved;
	}
	
	private static boolean handleTileMovement(int[] position, int[] moveVecor, int[][][]game, int[] score) {
		if(game[position[0]][position[1]][0]>0) {
			int[] target = getTarget(moveVecor, position, game);
			if(applySubMove(target, position, game, score)) {
				return true;
			}
		}
		return false;
	}
	
	private static boolean applySubMove(int[] target, int[] position, int[][][] game, int[] score) {
		if(target[0]==position[0]&&target[1]==position[1]) {
			return false;
		}
		if(game[target[0]][target[1]][0]==game[position[0]][position[1]][0]) {
			score[0] += game[position[0]][position[1]][0] += game[position[0]][position[1]][0];
			game[position[0]][position[1]][1] = 1;
		}
		game[target[0]][target[1]] = game[position[0]][position[1]];
		game[position[0]][position[1]] = new int[] {0,0};
		return true;
	}
	private static int[] getTarget(int[] vectorMov, int[] vectorPos,int[][][] game) {
		int steps = 0;
		int[] currentTarget = vectorPos.clone();
		while(canMove(vs(vectorPos, vcm(vectorMov,steps+1)), game[vectorPos[0]][vectorPos[1]][0], game)) {
			steps++;
			currentTarget = vs(vectorPos, vcm(vectorMov,steps));
		}
		return currentTarget;
	}
	private static boolean canMove(int[] position, int value, int[][][] game) {
		if(position[0]<0||position[0]>game.length-1||position[1]<0||position[1]>game[position[0]].length-1) {
			return false; // out of bounds 
		}
		if((game[position[0]][position[1]][0]==value&&game[position[0]][position[1]][1]==0)
				||game[position[0]][position[1]][0]==0) {
			return true;
		}
		return false;
	}
	private static int[] vs(int[] vectorPos, int[] vectorMove) {
		int[] out = vectorPos.clone();
		for(int i=0; i<out.length; i++) {
			out[i] = out[i] + vectorMove[i];
		}
		return out;
	}
	private static int[] vcm(int[] vector, int constantM) {
		int[] out = new int[vector.length];
		for(int i=0; i<vector.length; i++) {
			out[i] = vector[i]*constantM;
		}
		return out;
	}	
	
	private static int getMoveSimple(Scanner systemIn, int[][][] game, String gameStateName, String controllInstructions) {
		int choice = crudeMove(systemIn, "Choose your move"+controllInstructions+"\n>>>", game, gameStateName);
		while(choice<1||choice>4){
			System.out.println("<"+choice+"> is not a valic move id(-1 means invalid non intiger value).");
			choice = crudeMove(systemIn, "Choose your move"+controllInstructions+"\n>>>", game, gameStateName);
		}
		return choice;
	}

	private static int crudeMove(Scanner systemIn, String prompt, int[][][] game, String gameStateName) {
		showGame(game, gameStateName);
		System.out.print(prompt);
		int out = 0;
		String input = systemIn.nextLine().toLowerCase();
		Scanner intFinder = new Scanner(input);
		if(intFinder.hasNextInt()) {
			out = intFinder.nextInt();	
		}else {
			switch (input) {
			case "w":
				out = 1;
				break;
			case "a":
				out = 3;
				break;
			case "s":
				out = 2;
				break;
			case "d":
				out = 4;
				break;
			default:
				out = -1;
				break;
			}
		}
		intFinder.close();
		
		return out;
	}

	private static int[][][] initiliseGame(int size, int startingTilesCount) {
		int[][][] out = new int[size][size][2];
		for(int i = 0; i<startingTilesCount; i++) {
			fillRandomEmptyTile(out);
		}
		showGame(out,"start");
		return out;
	}
	private static boolean fillRandomEmptyTile(int[][][] game) {
		int[] emptySpaces = findEmptySpaces(game);
		if(emptySpaces.length==0) {
			return false;
		}
		applyRandomFill(game, emptySpaces);
		return true;
	}
	private static void applyRandomFill(int[][][] game, int[] emptySpaces) {
		Random random = new Random();
		int index = random.nextInt(emptySpaces.length);
		int y = emptySpaces[index]/10;
		int x = emptySpaces[index]%10;
		game[y][x][0]=1;	
	}
	private static int[] findEmptySpaces(int[][][] game) {
		int[] out = new int[]{};
		for(int y = 0; y< game.length; y++) {
			for(int x = 0; x< game[y].length; x++) {
				if(game[y][x][0]==0) {
					int[] tOut = new int[out.length+1];
					for(int i=0; i< out.length; i++){
						tOut[i] = out[i];
					}
					out = tOut;
					out[out.length-1] = y*10+x; 
					
				}
			}
		}
		return out;
	}


	private static void showGame(int[][][] game, String gameStaheName) {
		boolean reached2048 = false;
		int width = game.length;
		System.out.print("\n["+gameStaheName+"]\n");
		buildHLine(width);
		for(int[][] row: game) {
			System.out.print("|");
			for(int tileValue[]: row){
				if(tileValue[0] >2048) {
					reached2048 = true;
				}
				showTileBody(tileValue[0]);
				tileValue[1]=0;
				System.out.print("|");
			}
		System.out.println();
		buildHLine(width);
		if(reached2048) {
			System.out.println("2048 reached, you have beet the game.");
		}
		}	
	}
	private static void showTileBody(int tileValue) {
		if(tileValue==0) {
			System.out.print("    ");
			return;
		}
		if(tileValue<10) {
			System.out.print("   "+tileValue);
			return;
		}
		if(tileValue<100) {
			System.out.print("  "+tileValue);
			return;
		}
		if(tileValue<1000) {
			System.out.print(" "+tileValue);
			return;
		}
		if(tileValue<10000) {
			System.out.print(""+tileValue);
			return;
		}
		System.out.print(((tileValue+"").charAt(0)+"")+((tileValue+"").charAt(1)+"")+"xx");
	}
	private static void buildHLine(int length) {
		System.out.print("+");
		for(int i =0; i<length; i++) {
			System.out.print("----+");
		}
		System.out.println();
		
	}


	private static int getInt(Scanner systemIn, String prompt) {
		System.out.print(prompt+":");
		String input = systemIn.nextLine();
		Scanner getInt = new Scanner(input);
		while(!getInt.hasNextInt()) {
			System.out.print("<"+input+"> is not a valid int\n"+prompt);
			input = systemIn.nextLine();
			getInt = new Scanner(input);
		}
		int out = getInt.nextInt();
		getInt.close();
		return out;
	}

}

