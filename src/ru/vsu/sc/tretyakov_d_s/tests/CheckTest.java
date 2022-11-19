package ru.vsu.sc.tretyakov_d_s.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import ru.vsu.sc.tretyakov_d_s.chess.SideColor;
import ru.vsu.sc.tretyakov_d_s.chess.panels.GamePanel;
import ru.vsu.sc.tretyakov_d_s.chess.pieces.Engineer;
import ru.vsu.sc.tretyakov_d_s.chess.pieces.Chessman;
import ru.vsu.sc.tretyakov_d_s.chess.pieces.Commander;
import ru.vsu.sc.tretyakov_d_s.chess.pieces.Tank;
import ru.vsu.sc.tretyakov_d_s.chess.pieces.Infantry;
import ru.vsu.sc.tretyakov_d_s.chess.pieces.Air;

class CheckTest {

	@Test
	void testCheck1() {
		Chessman[][] board = new Chessman[8][8];
		board[0][0] = new Commander(SideColor.RED, 0 ,0);
		board[0][5] = new Air(SideColor.BLUE , 0, 5);

		assertTrue(GamePanel.check(board, SideColor.RED));
		
	}
	
	@Test
	void testCheck2() {
		Chessman[][] board = new Chessman[8][8];
		board[1][6] = new Commander(SideColor.BLUE , 1 ,6);
		board[6][1] = new Engineer(SideColor.RED, 6, 1);

		assertTrue(GamePanel.check(board, SideColor.BLUE));
		
	}

	
	@Test
	void testCheck3() {
		Chessman[][] board = new Chessman[8][8];
		board[5][5] = new Commander(SideColor.BLUE , 4 ,5);
		board[3][5] = new Infantry(SideColor.RED, 3, 5);

		assertFalse(GamePanel.check(board, SideColor.BLUE));
		
	}
	
	
	@Test
	void testCheck4() {
		Chessman[][] board = new Chessman[8][8];
		board[4][5] = new Commander(SideColor.RED, 4 ,5);
		board[5][5] = new Infantry(SideColor.BLUE , 5, 5);
		board[2][3] = new Commander(SideColor.BLUE , 2 ,2);
		board[1][5] = new Tank(SideColor.RED,1 ,5);

		assertTrue(GamePanel.check(board, SideColor.BLUE));
	}
}
