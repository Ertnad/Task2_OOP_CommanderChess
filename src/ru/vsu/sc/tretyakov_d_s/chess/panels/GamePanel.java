package ru.vsu.sc.tretyakov_d_s.chess.panels;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;


import ru.vsu.sc.tretyakov_d_s.chess.Menu;
import ru.vsu.sc.tretyakov_d_s.chess.Position;
import ru.vsu.sc.tretyakov_d_s.chess.SideColor;
import ru.vsu.sc.tretyakov_d_s.chess.pieces.*;

public abstract class GamePanel extends JPanel{
	
	private final BufferedImage boardImg;
	public int SQUARE_SIZE =64;
	ArrayList<Position> possibleMoves;
	ArrayList<Position> lastMove;
	Position checkPosition;
	Chessman selected;
	
	boolean endGame=false;
	protected Position focus;
	public Chessman[][] piecesBoard;
	
	private final BufferedImage[] imgTable;
	
	public boolean blueMove;
	public boolean enabled;
	

	abstract void oponentTurn();
	
	public GamePanel() {
		boardImg = new BufferedImage(11*SQUARE_SIZE ,12*SQUARE_SIZE ,BufferedImage.TYPE_INT_ARGB);
		drawBoard();
		this.setPreferredSize(new Dimension(11*SQUARE_SIZE, 12*SQUARE_SIZE));
		
		piecesBoard = new Chessman[19][19];
		imgTable = new BufferedImage[23];
		possibleMoves= new ArrayList<>();
		lastMove = new ArrayList<>();
		focus = new Position(0,0);
		blueMove= true;
		enabled =true;
		
		
		generatePieces();
		loadImages();
		MouseListner();
	}
	
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		g.drawImage(boardImg , 0 ,0 ,11*SQUARE_SIZE,12*SQUARE_SIZE , null);
		
		for(Position lm: lastMove) {
			g.setColor(new Color(0, 102, 102,128));
			g.fillRect(lm.x*SQUARE_SIZE, lm.y*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE );
		}
		if(checkPosition!=null) {
			g.setColor(new Color(255,0,0,128));
			g.fillRect(checkPosition.x*SQUARE_SIZE, checkPosition.y*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE );
		}

		
		g.setColor(new Color(0xCD7A7A7A, true));
		g.fillOval(focus.x*SQUARE_SIZE, focus.y*SQUARE_SIZE,SQUARE_SIZE , SQUARE_SIZE);
		for(int i=0 ; i<piecesBoard.length ;i++) {
			for(int j=0 ; j<piecesBoard[i].length;j++) {
				if(piecesBoard[i][j]==null) continue;
				else {
					g.drawImage(imgTable[piecesBoard[i][j].imgSrc],  i*SQUARE_SIZE,
							j*SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE , null);
				}
			}
		}
		g.setColor(new Color(0,255,0,  192));
		if(selected!=null) {
			((Graphics2D) g).setStroke(new BasicStroke(4));
			g.drawOval(selected.pos.x*SQUARE_SIZE+2, selected.pos.y*SQUARE_SIZE+2, SQUARE_SIZE-4, SQUARE_SIZE-4 );
		}

		for(Position ch: possibleMoves) {
			if(piecesBoard[ch.x][ch.y]!= null) {
				if (selected != null && piecesBoard[ch.x][ch.y].color != selected.color) {
					g.setColor(new Color(255, 0, 0, 192));
					g.drawOval(ch.x * SQUARE_SIZE, ch.y * SQUARE_SIZE, SQUARE_SIZE, SQUARE_SIZE);
				}
			}
			else {
				g.setColor(new Color(0,255,0,  192));
				g.fillOval(ch.x*SQUARE_SIZE+SQUARE_SIZE/4, ch.y*SQUARE_SIZE+SQUARE_SIZE/4,
						SQUARE_SIZE/2, SQUARE_SIZE/2);
				
			}
			
		}
	}
	
	private void drawBoard()
	{

		Graphics2D g = (Graphics2D) boardImg.getGraphics();
		g.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

		g.setColor(new Color(0xBB7046));
		g.fillRect(SQUARE_SIZE/3,SQUARE_SIZE/3,11 * SQUARE_SIZE - SQUARE_SIZE/2 - SQUARE_SIZE/6, 12 * SQUARE_SIZE - SQUARE_SIZE/2 - SQUARE_SIZE/6);

		g.setColor(new Color(0x4E8102));
		g.fillRect(SQUARE_SIZE/2,SQUARE_SIZE/2,10*SQUARE_SIZE, 11 * SQUARE_SIZE);

		g.setColor(new Color(0x7CC5F1));
		for(int i=1 ; i<4 ;i++) {
			for(int j=0; j<11 ; j++) {
				g.fillRect(i * SQUARE_SIZE/2, SQUARE_SIZE * j + SQUARE_SIZE/2, SQUARE_SIZE, SQUARE_SIZE);
				if (j == 6) {
					int k = 0;
					while (k < 10) {
						g.fillRect(k*SQUARE_SIZE + SQUARE_SIZE/2, j * SQUARE_SIZE - SQUARE_SIZE/2, SQUARE_SIZE, SQUARE_SIZE);
						k++;
					}
				}
			}
		}

		g.setColor(Color.BLACK);
		for (int i = 0; i < 12; i++) {
			g.drawLine(SQUARE_SIZE/2, i * SQUARE_SIZE + SQUARE_SIZE/2, 11 * SQUARE_SIZE - SQUARE_SIZE/2, i * SQUARE_SIZE + SQUARE_SIZE/2);
		}

		for (int i = 0; i < 11; i++) {
			g.drawLine(i * SQUARE_SIZE + SQUARE_SIZE/2, SQUARE_SIZE/2, i * SQUARE_SIZE + SQUARE_SIZE/2, 12 * SQUARE_SIZE - SQUARE_SIZE/2);
		}

	}

	private void generatePieces() {

		piecesBoard[4][10] = new Air(SideColor.BLUE, 4, 10);
		piecesBoard[8][10] = new Air(SideColor.BLUE, 8, 10);
		piecesBoard[4][1] = new Air(SideColor.RED, 4, 1);
		piecesBoard[8][1] = new Air(SideColor.RED, 8, 1);

		piecesBoard[4][8] = new Anti_aircraft_gun(SideColor.BLUE, 4, 8);
		piecesBoard[8][8] = new Anti_aircraft_gun(SideColor.BLUE, 8, 8);
		piecesBoard[4][3] = new Anti_aircraft_gun(SideColor.RED, 4, 3);
		piecesBoard[8][3] = new Anti_aircraft_gun(SideColor.RED, 8, 3);

		piecesBoard[3][9] = new Artillery(SideColor.BLUE, 3, 9);
		piecesBoard[9][9] = new Artillery(SideColor.BLUE, 9, 9);
		piecesBoard[3][2] = new Artillery(SideColor.RED, 3, 2);
		piecesBoard[9][2] = new Artillery(SideColor.RED, 9, 2);

		piecesBoard[6][11] = new Commander(SideColor.BLUE, 6, 11);
		piecesBoard[6][0] = new Commander(SideColor.RED, 6, 0);

		piecesBoard[3][7] = new Engineer(SideColor.BLUE, 3, 7);
		piecesBoard[9][7] = new Engineer(SideColor.BLUE, 9, 7);
		piecesBoard[3][4] = new Engineer(SideColor.RED, 3, 4);
		piecesBoard[9][4] = new Engineer(SideColor.RED, 9, 4);

		piecesBoard[5][10] = new Headquarters(SideColor.BLUE, 5, 10);
		piecesBoard[7][10] = new Headquarters(SideColor.BLUE, 7, 10);
		piecesBoard[5][1] = new Headquarters(SideColor.RED, 5, 1);
		piecesBoard[7][1] = new Headquarters(SideColor.RED, 7, 1);

		piecesBoard[2][7] =new Infantry(SideColor.BLUE ,2 ,7);
		piecesBoard[10][7] =new Infantry(SideColor.BLUE ,10 ,7);
		piecesBoard[2][4] =new Infantry(SideColor.RED,2 ,4);
		piecesBoard[10][4] =new Infantry(SideColor.RED,10 ,4);

		piecesBoard[6][7] =new Militia(SideColor.BLUE ,6 ,7);
		piecesBoard[6][4] =new Militia(SideColor.RED,6 ,4);

		piecesBoard[6][9] = new Missile(SideColor.BLUE, 6, 9);
		piecesBoard[6][2] = new Missile(SideColor.RED, 6, 2);

		piecesBoard[1][10] = new Navy(SideColor.BLUE, 1, 10);
		piecesBoard[2][8] = new Navy(SideColor.BLUE, 2, 8);
		piecesBoard[1][1] = new Navy(SideColor.RED, 1, 1);
		piecesBoard[2][3] = new Navy(SideColor.RED, 2, 3);

		piecesBoard[5][8] = new Tank(SideColor.BLUE, 5, 8);
		piecesBoard[7][8] = new Tank(SideColor.BLUE, 7, 8);
		piecesBoard[5][3] = new Tank(SideColor.RED, 5, 3);
		piecesBoard[7][3] = new Tank(SideColor.RED, 7, 3);

	}
	
	public void loadImages() {
		try {
			String source= "/ru/vsu/sc/tretyakov_d_s/resources/";

			imgTable[1]  = ImageIO.read(getClass().getResource(source+"AirB.png"));
			imgTable[12]  = ImageIO.read(getClass().getResource(source+"AirR.png"));

			imgTable[2]  = ImageIO.read(getClass().getResource(source+"AAGB.png"));
			imgTable[13]  = ImageIO.read(getClass().getResource(source+"AAGR.png"));

			imgTable[3]   = ImageIO.read(getClass().getResource(source+"AB.png"));
			imgTable[14]  = ImageIO.read(getClass().getResource(source+"AR.png"));

			imgTable[4]  = ImageIO.read(getClass().getResource(source+"CB.png"));
			imgTable[15]  = ImageIO.read(getClass().getResource(source+"CR.png"));

			imgTable[5]  = ImageIO.read(getClass().getResource(source+"EB.png"));
			imgTable[16]  = ImageIO.read(getClass().getResource(source+"ER.png"));

			imgTable[6]  = ImageIO.read(getClass().getResource(source+"HB.png"));
			imgTable[17]  = ImageIO.read(getClass().getResource(source+"HR.png"));

			imgTable[7]  = ImageIO.read(getClass().getResource(source+"InB.png"));
			imgTable[18]  = ImageIO.read(getClass().getResource(source+"InR.png"));

			imgTable[8]  = ImageIO.read(getClass().getResource(source+"MB.png"));
			imgTable[19]  = ImageIO.read(getClass().getResource(source+"MR.png"));

			imgTable[9]  = ImageIO.read(getClass().getResource(source+"MSLB.png"));
			imgTable[20]  = ImageIO.read(getClass().getResource(source+"MSLR.png"));

			imgTable[10]  = ImageIO.read(getClass().getResource(source+"NB.png"));
			imgTable[21]  = ImageIO.read(getClass().getResource(source+"NR.png"));

			imgTable[11]  = ImageIO.read(getClass().getResource(source+"TB.png"));
			imgTable[22]  = ImageIO.read(getClass().getResource(source+"TR.png"));
			

		}
		catch(IOException e)
		{
			System.err.println("Error");
			e.printStackTrace();
		}

	}


	public void MouseListner() {
		 addMouseListener(new MouseAdapter(){ 
	         public void mousePressed(MouseEvent me) { 
	        	 int tempX =me.getX()/SQUARE_SIZE;
	        	 int tempY =me.getY()/SQUARE_SIZE;
	        	 if((piecesBoard[tempX][tempY]==null && selected==null) || !enabled) return;
	        	 else if(selected==null) {
	            	 selected =piecesBoard[tempX][tempY];
	            	 if(selected.color==SideColor.BLUE && !blueMove) {
	            		 selected=null;
	            		 return;
	            	 }
	            	 else if(selected.color==SideColor.RED && blueMove) {
	            		 selected=null;
	            		 return;
	            	 }
	                 possibleMoves =preventCheck(selected.GetMoves(piecesBoard), piecesBoard , selected);
	                 repaint();
	        	 }else {
	        		 if(piecesBoard[tempX][tempY]!=null && piecesBoard[tempX][tempY].color== selected.color) {
	                	 selected =piecesBoard[tempX][tempY];
	                     possibleMoves =preventCheck(selected.GetMoves(piecesBoard), piecesBoard , selected);
	                     repaint();
	        		 }else {
	        			 
	        			 checkChessmanMove(new Position(tempX ,tempY));
	        			 selected=null;
	        			 possibleMoves.clear();
	        			 repaint();
	        		 }
	        		 
	        	 }
	           
	         }
	       }); 
		 
		 addMouseMotionListener(new MouseMotionListener() {
			 @Override
			public void mouseMoved(MouseEvent me) {
	        	 focus.x=me.getX()/SQUARE_SIZE;
	        	 focus.y=me.getY()/SQUARE_SIZE;
	        	 repaint();
			}
	
			@Override
			public void mouseDragged(MouseEvent arg0) {
			}
			 
		 });
		
	}

	public void checkChessmanMove(Position newPosition) {
		boolean contains= false;
		
		for(Position m : possibleMoves) {
			
			if(m.x==newPosition.x && m.y==newPosition.y) {
				contains=true;
				break;
			}
		}
	
		if(!contains) return;
		moveChessman(newPosition , selected.pos);
		oponentTurn();
			

	

	}

	public void moveChessman(Position newPosition ,Position oldPosition) {
		lastMove.clear();
		lastMove.add(oldPosition);
		lastMove.add(newPosition);
		Chessman piece = piecesBoard[oldPosition.x][oldPosition.y];
		piecesBoard[newPosition.x][newPosition.y]=piece;
		piecesBoard[piece.pos.x][piece.pos.y] =null;
		piece.pos=newPosition;
		piece.notMoved = false;
		SideColor c =piece.color.swapColor();
		
	
		boolean isCheck =check(piecesBoard ,c);
		if(!isCheck) {
			checkPosition=null;
			checkStalemate(piece.color.swapColor() , piecesBoard);
		}
		else {
			checkPosition = findKing(piecesBoard ,c);
			checkmate(piece.color.swapColor() , piecesBoard);
		}
	}


	public static ArrayList<Position> getAllMoves(SideColor col , Chessman[][] board){
		ArrayList<Position> moves = new ArrayList<>();
	
		for(int i = 0; i <=7; i++){
			for(int j = 0; j <=7; j++) {
				if(board[i][j] != null)
				{				
					if(board[i][j].color == col) moves.addAll(board[i][j].GetMoves(board));
				}			
			}
		}
		return moves;
	}
	
	public ArrayList<Position> getAllSafeMoves(SideColor col , Chessman[][] board){
		ArrayList<Position> moves = new ArrayList<>();
	
		for(int i = 0; i <=7; i++){
			for(int j = 0; j <=7; j++) {
				if(board[i][j] != null)
				{				
					if(board[i][j].color == col) moves.addAll(preventCheck(board[i][j].GetMoves(board),board,board[i][j]));
				}			
			}
		}
		return moves;
	}
	public static Position findKing(Chessman[][] board, SideColor col) {
		Position kingPosition = new Position(0,0);
		for(int i=0; i<board.length ; i++) {
			for(int j=0 ; j<board[i].length;j++) {
				if(board[i][j]!=null) {
					if(board[i][j] instanceof Commander && board[i][j].color==col){
						return new Position(i,j);
					}
				}
			}
		}
		return kingPosition;
	}

	public static boolean check(Chessman[][] board, SideColor col) {

		Position kingPosition =findKing(board , col);
		SideColor c =col.swapColor();
		
		ArrayList<Position> enemyMoves =getAllMoves(c ,board);
		
		
		for(Position p : enemyMoves) {
			if(kingPosition.x==p.x &&kingPosition.y==p.y) {
				return true;
			}
		}
		
		return false;
	}

	public static ArrayList<Position> preventCheck(ArrayList<Position> moves , Chessman[][] board, Chessman piece){
		
		Iterator<Position> i = moves.iterator();
		while(i.hasNext()) {
			Position p = i.next();
			
			Chessman[][] tempBoard = Arrays.stream(board).map(Chessman[]::clone).toArray(Chessman[][]::new);
			tempBoard[piece.pos.x][piece.pos.y]=null;
			tempBoard[p.x][p.y]=piece;
			boolean isCheck = check(tempBoard, piece.color);
			if(isCheck)
				i.remove();
		}			
		return moves;
	}

	public void checkmate(SideColor col , Chessman[][] board) {
		ArrayList<Position> any = getAllSafeMoves(col , board);
		
		if(any.isEmpty()) {
			endGame=true;
			possibleMoves.clear();
			repaint();
			JOptionPane.showMessageDialog(null,col.getBetterString() +" King is checkmate. "+col.swapColor().getBetterString()+
					"s wins. " ,
					"Checkmate",JOptionPane.INFORMATION_MESSAGE);
			closeFrame();
		}
	}
	
	public void checkStalemate(SideColor col , Chessman[][] board) {
		ArrayList<Position> any = getAllSafeMoves(col , board);
		
		if(any.isEmpty()) {
			possibleMoves.clear();
			repaint();
			JOptionPane.showMessageDialog(null,col.getBetterString() +"s have no more available moves. The game ends with a draw. " ,
					"Stalemate",JOptionPane.INFORMATION_MESSAGE);
			closeFrame();
			
		}
		
	}
	public void closeFrame() {
		new Menu();
		SwingUtilities.windowForComponent(this).dispose();
	}

}
