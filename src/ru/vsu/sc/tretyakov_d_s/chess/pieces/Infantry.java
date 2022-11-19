package ru.vsu.sc.tretyakov_d_s.chess.pieces;

import java.util.ArrayList;

import ru.vsu.sc.tretyakov_d_s.chess.Position;
import ru.vsu.sc.tretyakov_d_s.chess.SideColor;


public class Infantry extends Chessman {

	public boolean startPosition= true;
	public Infantry(SideColor col , int x , int y) {
		color =col;
		pos= new Position(x ,y);
		Value = 100;
		loadImage();
	}
	
	public void loadImage() {
		if(this.color==SideColor.BLUE) {
			imgSrc=7;
		}else imgSrc=18;
	}
	
	public ArrayList<Position> GetMoves(Chessman[][] board) {


		ArrayList<Position> moves = new ArrayList<>();

		int movesRight = 9 - this.pos.x;
		int movesLeft = this.pos.x;
		int movesUp = this.pos.y;
		int movesDown = 10 - this.pos.y;
		int i = 0;

		while(movesUp>0 ) {

			movesUp--;
			i++;

			if(board[pos.x][pos.y-i] == null){
				moves.add(new Position(this.pos.x ,this.pos.y-i));

			}

			if(board[pos.x][pos.y-i] != null) {
				if(board[this.pos.x][this.pos.y-i].color != this.color) {
					moves.add(new Position(this.pos.x ,this.pos.y-i));
				}
				break;
			}
		}

		i = 0;

		while(movesDown>0 ) {

			movesDown--;
			i++;
			if(board[pos.x][pos.y+i] == null) {
				moves.add(new Position(this.pos.x ,this.pos.y+i));
			}

			if(board[pos.x][pos.y+i] != null) {
				if(board[this.pos.x][this.pos.y+i].color != this.color) {
					moves.add(new Position(this.pos.x ,this.pos.y+i));
				}
				break;
			}
		}

		i = 0;

		while(movesLeft>2) {

			movesLeft--;
			i++;
			if(board[pos.x-i][pos.y] == null) {
				moves.add(new Position(this.pos.x-i ,this.pos.y));
			}

			if(board[pos.x-i][pos.y] != null) {
				if(board[this.pos.x-i][this.pos.y].color != this.color) {
					moves.add(new Position(this.pos.x-i ,this.pos.y));
				}
				break;
			}
		}

		i = 0;

		while(movesRight>0 ) {

			movesRight--;
			i++;
			if(board[pos.x+i][pos.y] == null) {
				moves.add(new Position(this.pos.x+i ,this.pos.y));
			}
			if(board[pos.x+i][pos.y] != null) {
				if(board[this.pos.x+i][this.pos.y].color != this.color) {
					moves.add(new Position(this.pos.x+i ,this.pos.y));
				}
				break;
			}
		}

		return moves;
	}
}
