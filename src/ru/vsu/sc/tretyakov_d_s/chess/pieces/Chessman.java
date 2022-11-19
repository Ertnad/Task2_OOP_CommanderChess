package ru.vsu.sc.tretyakov_d_s.chess.pieces;

import java.util.ArrayList;
import ru.vsu.sc.tretyakov_d_s.chess.Position;
import ru.vsu.sc.tretyakov_d_s.chess.SideColor;

public abstract class Chessman {

	public SideColor color;
	public boolean notMoved;
	public Position pos;
	public int Value;
	
	public short imgSrc;
	
	abstract void loadImage() ;	

	
	public abstract ArrayList<Position> GetMoves(Chessman[][] board);
}
