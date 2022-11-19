package ru.vsu.sc.tretyakov_d_s.tests;

//import static org.junit.jupiter.api.Assertions.*;
import static org.junit.Assert.*;
import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import ru.vsu.sc.tretyakov_d_s.chess.Position;
import ru.vsu.sc.tretyakov_d_s.chess.SideColor;
import ru.vsu.sc.tretyakov_d_s.chess.panels.GamePanelHot;
import ru.vsu.sc.tretyakov_d_s.chess.pieces.Engineer;
import ru.vsu.sc.tretyakov_d_s.chess.pieces.Chessman;
import ru.vsu.sc.tretyakov_d_s.chess.pieces.Commander;
import ru.vsu.sc.tretyakov_d_s.chess.pieces.Artillery;

class PreventCheckTest {
	
	GamePanelHot gp = new GamePanelHot();
	@Test
	void preventCheckMoves1() {
		
		Chessman[][] board = new Chessman[8][8];
		board[5][5] = new Artillery(SideColor.RED, 5 ,5);
		board[5][0] = new Commander(SideColor.BLUE , 5,0);
		Engineer bishop =new Engineer(SideColor.BLUE , 5 ,1);
		board[5][1] = bishop;
		
		assertEquals(gp.preventCheck(bishop.GetMoves(board), board, bishop).isEmpty(), true);
	}
	
	@Test
	void preventCheckMoves2() {
		
		Chessman[][] board = new Chessman[8][8];
		board[5][5] = new Artillery(SideColor.RED, 5 ,5);
		board[5][0] = new Commander(SideColor.BLUE , 5,0);
		Engineer bishop =new Engineer(SideColor.BLUE , 6 ,0);
		board[6][0] = bishop;
		
		assertEquals(gp.preventCheck(bishop.GetMoves(board), board, bishop).size()==1 , true);
		
		Position temp =gp.preventCheck(bishop.GetMoves(board), board, bishop).get(0);
		assertEquals(temp.x==5 && temp.y==1, true);
	}
	
	
	@Test
	void preventCheckMoves3() {
		
		Chessman[][] board = new Chessman[8][8];
		board[5][5] = new Artillery(SideColor.RED, 6 ,5);
		board[5][0] = new Commander(SideColor.BLUE , 5,0);
		Engineer bishop =new Engineer(SideColor.BLUE , 6 ,0);
		board[6][0] = bishop;

		ArrayList<Position> temp = bishop.GetMoves(board);
		assertSame(temp, gp.preventCheck(temp, board, bishop));
	}

}
