package ru.vsu.sc.tretyakov_d_s.chess;

import javax.swing.JFrame;
import javax.swing.JMenuBar;

import ru.vsu.sc.tretyakov_d_s.chess.panels.GamePanel;


public class GameFrame extends JFrame  {


	public GameFrame(GamePanel gamePanel){
		super("Командирские Шахматы");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		this.add(gamePanel);


		this.setJMenuBar(new JMenuBar());
		this.setResizable(false);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}

}
