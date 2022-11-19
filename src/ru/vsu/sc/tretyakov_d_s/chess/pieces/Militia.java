package ru.vsu.sc.tretyakov_d_s.chess.pieces;

import ru.vsu.sc.tretyakov_d_s.chess.Position;
import ru.vsu.sc.tretyakov_d_s.chess.SideColor;

import java.util.ArrayList;


public class Militia extends Chessman {

	public boolean startPosition= true;
	public Militia(SideColor col , int x , int y) {
		color =col;
		pos= new Position(x ,y);
		Value = 100;
		loadImage();
	}
	
	public void loadImage() {
		if(this.color==SideColor.BLUE) {
			imgSrc=8;
		}else imgSrc=19;
	}
	
	public ArrayList<Position> GetMoves(Chessman[][] board) {
		
		ArrayList<Position> moves = new ArrayList<Position>(); ;
		
		int help =0;
		if(color == SideColor.BLUE) help=-1;
		else help =1;
		
		if(board[pos.x][pos.y+help] == null) {
			moves.add(new Position(this.pos.x ,this.pos.y+help));
				
			if(startPosition &&board[pos.x][pos.y+(help*2)] == null) 
				moves.add(new Position(this.pos.x ,this.pos.y+(help*2)));
		}
			
		
		if(this.pos.x>0) {
			if(board[this.pos.x-1][this.pos.y+help] !=null) {
				if(board[this.pos.x-1][this.pos.y+help].color != this.color)
					moves.add(new Position(this.pos.x-1 ,this.pos.y+help));
			}
		}
		if(this.pos.x<7) {
			if(board[this.pos.x+1][this.pos.y+help] !=null) {
				if(board[this.pos.x+1][this.pos.y+help].color != this.color)
					moves.add(new Position(this.pos.x+1 ,this.pos.y+help));
			}
		}


		

		return moves;
		
	}
}
