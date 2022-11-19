package ru.vsu.sc.tretyakov_d_s.tests;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.Test;

import ru.vsu.sc.tretyakov_d_s.chess.Position;
import ru.vsu.sc.tretyakov_d_s.chess.SideColor;
import ru.vsu.sc.tretyakov_d_s.chess.panels.GamePanel;
import ru.vsu.sc.tretyakov_d_s.chess.pieces.Engineer;
import ru.vsu.sc.tretyakov_d_s.chess.pieces.Chessman;
import ru.vsu.sc.tretyakov_d_s.chess.pieces.Commander;

class FindKingTest {

	@Test
	void findKingTest1() {

		Chessman[][] board = new Chessman[8][8];
		board[5][5] = new Commander(SideColor.RED, 5 ,5);
		
		Position p = GamePanel.findKing(board, SideColor.RED);
		assertEquals(p.x==5 && p.y ==5 , true);
		
	}
	
	
	@Test
	void findKingTest2() {

		Chessman[][] board = new Chessman[8][8];
		board[3][4] = new Commander(SideColor.RED, 3 ,4);
		board[2][2] = new Commander(SideColor.BLUE , 2 ,2);
		board[2][3] = new Engineer(SideColor.RED, 2 ,3);
		
		Position p = GamePanel.findKing(board, SideColor.RED);
		assertEquals(p.x==3 && p.y ==4 , true);
		
	}

}
