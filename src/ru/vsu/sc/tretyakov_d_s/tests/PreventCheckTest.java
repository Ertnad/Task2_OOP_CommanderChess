package ru.vsu.sc.tretyakov_d_s.tests;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import ru.vsu.sc.tretyakov_d_s.chess.Position;
import ru.vsu.sc.tretyakov_d_s.chess.SideColor;
import ru.vsu.sc.tretyakov_d_s.chess.panels.GamePanel;
import ru.vsu.sc.tretyakov_d_s.chess.panels.GamePanelHot;
import ru.vsu.sc.tretyakov_d_s.chess.pieces.Artillery;
import ru.vsu.sc.tretyakov_d_s.chess.pieces.Chessman;
import ru.vsu.sc.tretyakov_d_s.chess.pieces.Commander;
import ru.vsu.sc.tretyakov_d_s.chess.pieces.Engineer;

import java.util.ArrayList;

class PreventCheckTest {
	
	GamePanelHot gp = new GamePanelHot();
	@Test
	void preventCheckMoves1() {
		
		Chessman[][] board = new Chessman[8][8];
		board[5][5] = new Artillery(SideColor.RED, 5 ,5);
		board[5][0] = new Commander(SideColor.BLUE , 5,0);
		Engineer bishop =new Engineer(SideColor.BLUE , 5 ,1);
		board[5][1] = bishop;

		Assertions.assertTrue(GamePanel.preventCheck(bishop.GetMoves(board), board, bishop).isEmpty());
	}
	
	@Test
	void preventCheckMoves2() {
		
		Chessman[][] board = new Chessman[8][8];
		board[5][5] = new Artillery(SideColor.RED, 5 ,5);
		board[5][0] = new Commander(SideColor.BLUE , 5,0);
		Engineer bishop =new Engineer(SideColor.BLUE , 6 ,0);
		board[6][0] = bishop;

		Assertions.assertEquals(1, GamePanel.preventCheck(bishop.GetMoves(board), board, bishop).size());
		
		Position temp = GamePanel.preventCheck(bishop.GetMoves(board), board, bishop).get(0);
		Assertions.assertTrue(temp.x == 5 && temp.y == 1);
	}
	
	
	@Test
	void preventCheckMoves3() {
		
		Chessman[][] board = new Chessman[8][8];
		board[5][5] = new Artillery(SideColor.RED, 6 ,5);
		board[5][0] = new Commander(SideColor.BLUE , 5,0);
		Engineer bishop =new Engineer(SideColor.BLUE , 6 ,0);
		board[6][0] = bishop;

		ArrayList<Position> temp = bishop.GetMoves(board);
		Assertions.assertSame(temp, GamePanel.preventCheck(temp, board, bishop));
	}

}
