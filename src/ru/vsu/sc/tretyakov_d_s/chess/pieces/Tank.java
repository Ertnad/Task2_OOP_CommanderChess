package ru.vsu.sc.tretyakov_d_s.chess.pieces;

import java.util.ArrayList;

import ru.vsu.sc.tretyakov_d_s.chess.Position;
import ru.vsu.sc.tretyakov_d_s.chess.SideColor;

public class Tank extends Chessman {

	public Tank(SideColor col , int x , int y) {
		color =col;
		pos= new Position(x ,y);
		Value = 350;
		loadImage();
		
	}
	
	public void loadImage() {
		if(this.color==SideColor.BLUE) {
			imgSrc=11;
		}else imgSrc=22;
	}
	public ArrayList<Position> GetMoves(Chessman[][] board) {
		
		ArrayList<Position> moves = new ArrayList<Position>(); ;
		
				
		if(pos.x+1 <= 7 && pos.y+2 <= 7 ) {
			
			if(board[pos.x+1][pos.y+2] == null ){
				moves.add(new Position(this.pos.x+1 ,this.pos.y+2));
			}
			if(board[pos.x+1][pos.y+2] != null ){
				if(board[pos.x+1][pos.y+2].color != this.color) {
					
					moves.add(new Position(this.pos.x+1 ,this.pos.y+2));
				}
			}		
			
		}
		if(pos.x+2 <= 7 && pos.y+1 <= 7 ) {
			if(board[pos.x+2][pos.y+1] == null){
				moves.add(new Position(this.pos.x+2 ,this.pos.y+1));		
			}
			if(board[pos.x+2][pos.y+1] != null ){
				if(board[pos.x+2][pos.y+1].color != this.color) {
					
					moves.add(new Position(this.pos.x+2 ,this.pos.y+1));
				}
			}		
		}
		if(pos.x+1 <= 7 && pos.y-2 >= 0 ) {
			if(board[pos.x+1][pos.y-2] == null){
				moves.add(new Position(this.pos.x+1 ,this.pos.y-2));											
			}
			if(board[pos.x+1][pos.y-2] != null ){
				if(board[pos.x+1][pos.y-2].color != this.color) {
					
					moves.add(new Position(this.pos.x+1 ,this.pos.y-2));
				}
			}		
		}	
				
		if(pos.x+2 <= 7 && pos.y-1 >= 0 ) {
							
			if(board[pos.x+2][pos.y-1] == null){
				moves.add(new Position(this.pos.x+2 ,this.pos.y-1));
			}
			if(board[pos.x+2][pos.y-1] != null ){
				if(board[pos.x+2][pos.y-1].color != this.color) {
					
					moves.add(new Position(this.pos.x+2 ,this.pos.y-1));
				}
			}		
		}
	
		
		if(pos.x-1 >= 0 && pos.y+2 <= 7 ) {
			
			if(board[pos.x-1][pos.y+2] == null){
				moves.add(new Position(this.pos.x-1 ,this.pos.y+2));
			}
			if(board[pos.x-1][pos.y+2] != null ){
				if(board[pos.x-1][pos.y+2].color != this.color) {
					
					moves.add(new Position(this.pos.x-1 ,this.pos.y+2));
				}
			}					
		}
		
		
		if(pos.x-2 >= 0 && pos.y+1 <= 7 ) {
			
			if(board[pos.x-2][pos.y+1] == null){
				moves.add(new Position(this.pos.x-2 ,this.pos.y+1));
			}
			if(board[pos.x-2][pos.y+1] != null ){
				if(board[pos.x-2][pos.y+1].color != this.color) {
					
					moves.add(new Position(this.pos.x-2 ,this.pos.y+1));
				}
			}					
		}
		
		if(pos.x-1 >= 0 && pos.y-2 >= 0 ) {
			if(board[pos.x-1][pos.y-2] == null){
				moves.add(new Position(this.pos.x-1 ,this.pos.y-2));			
			}
			if(board[pos.x-1][pos.y-2] != null ){
				if(board[pos.x-1][pos.y-2].color != this.color) {
					
					moves.add(new Position(this.pos.x-1 ,this.pos.y-2));
				}
			}		
		}
			
		if(pos.x-2 >= 0 && pos.y-1 >= 0 ) {
				
			if(board[pos.x-2][pos.y-1] == null){
				moves.add(new Position(this.pos.x-2 ,this.pos.y-1));						
			}
			if(board[pos.x-2][pos.y-1] != null ){
				if(board[pos.x-2][pos.y-1].color != this.color) {
					
					moves.add(new Position(this.pos.x-2 ,this.pos.y-1));
				}
			}		
		}
		

		return moves;
		
	}
}
