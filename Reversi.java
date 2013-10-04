import java.io.*;
import java.awt.*;
import java.applet.*;
import javax.swing.*;
import java.util.*;
import java.awt.image.*;
import java.net.*;
import java.util.Random;
import java.awt.event.*;
import java.lang.Math;
import javax.sound.sampled.*;

/** 
 *	Lance Elliott
 *  
 **/


 
public class KeyHero extends Applet implements MouseListener
{
	int fontsize=30;
	Font Enter = new Font("Serif",Font.BOLD,fontsize);
	Font title = new Font("Serif",Font.BOLD,19);
	Font script = new Font("Serif",Font.PLAIN,12);
	Font Failed = new Font("Serif",Font.ITALIC,80);
	Font Title = new Font("Serif",Font.BOLD,80);
	Vector NoteVector;
	Color Background = new Color(238,	230, 133);
	//addMouseListener(this);
	Vector ColorVector;
	String yourgrapher;
	int xaxislength = 800;
	int yaxislength = 600;
	int clicks = 0;
	public static final int apwidth =800;
	public static final int apheight =600;
	boolean mouse_clicked=false;
	int p=0;
	int boardoffset = 100;
	
	//pieces
	final int blank = 0; 
	final int black = 1;  
	final int white = 2; 
	final int green = 3;
	int board[][] = new int[8][8];
	
	int wordloc=30; //shifting any words
	
	int turn = black;
	int otherturn = white;
	int mX = 0;//clicked
	int mY = 0;
	
	public void init()
	{
		start();
		
		

	}
	 public static void main(String arg[])throws Exception {
  	{
	
  	
    
    //Frame frame = new Frame();
		Frame frame = new Frame();
		Applet applet = new KeyHero();
		frame.addWindowListener(new WindowAdapter()
		{
		public void windowClosing(WindowEvent e)
			{
				System.exit(0);
			}	
		});

    frame.add(applet);
    frame.setSize(apwidth,apheight);
	
    frame.show();
  	}
	}
	public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
		mX = e.getX();
    	mY = e.getY();
        mouse_clicked=true;
	    //System.out.println("uhoh");
	    repaint();
    }


	public void update(Graphics g) {
    Graphics offgc;
    Image offscreen = null;
    Dimension d = size();

    // create the offscreen buffer and associated Graphics
    offscreen = createImage(d.width, d.height);
    offgc = offscreen.getGraphics();
    // clear the exposed area
    offgc.setColor(getBackground());
    offgc.fillRect(0, 0, d.width, d.height);
    offgc.setColor(getForeground());
    // do normal redraw
    paint(offgc);
    // transfer offscreen to window
    g.drawImage(offscreen, 0, 0, this);
    }
	public void paint(Graphics g)
	{
		
		if(p==0)
		{
			addMouseListener(this); 
			for (int i=0; i < 8; i++)
				for (int j=0; j < 8; j++)
					board[i][j] = blank;
				
			board[3][3] = white;
			board[4][4] = white;
			board[3][4] = black;
			board[4][3] = black;
			p=1;
		}
		
		else if(!gameover()&&mouse_clicked&&isPlayable(mX,mY))
		{
		play(mX,mY);
		//System.out.print("clicked");
		otherturn = otherturn == white ? black : white;
		turn = turn == white ? black : white;
		ResetMoveList();
		mouse_clicked=false;
		}
		drawBoard(g);
		findMoves();
		drawPieces(g);	
		g.setFont(Enter);
		g.setColor(Color.black);
		if (gameover())
		{
		System.out.println("game over");
		getWinner(g);
		}
		if (!gameover())
		{
		g.drawString(turn == white ? "white's turn" : "black's turn",200,550);
		}
			/*g.drawString("mX = " + mX + " mY = " + mY ,200,550+wordloc);
			if (mX !=0 && mY != 0)
			{
			g.drawString("mX = " + mX + " mY = " + mY ,200,500+wordloc);
			}*/
	}
	public boolean gameover () {
		int spaces=0;
		int movepossibilities = 0;
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
			{
			if(board[i][j]==blank)
			spaces++;
			if(board[i][j]==green)
			movepossibilities++;
			}
		if (spaces == 0 && movepossibilities == 0)
			return true;
		else if (movepossibilities == 0)
			return true;
		else 
			return false;
    }
	
	public void getWinner (Graphics g) {
		int spaces=0;
		int movepossibilities = 0;
		int blackpieces = 0;
		int whitepieces = 0;
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
			{
			if(board[i][j]==blank)
			spaces++;
			else if(board[i][j]==black)
			blackpieces++;
			else if(board[i][j]==white)
			whitepieces++;
			else if(board[i][j]==green)
			movepossibilities++;
			}
		System.out.println("blank = " + blank +"black = " + blackpieces +"white = " + whitepieces +"green = " + green);
		g.setColor(Color.black);
		if (movepossibilities == 0 && spaces>0)
		{
			g.drawString(turn == white ? "Black Wins!" : "White Wins!",200,550);
			System.out.println("win by moves");
		}
		else if (blackpieces != whitepieces)
		{
		g.drawString(blackpieces > whitepieces ? "Black Wins!" : "White Wins!",200,550);
		System.out.println("win by number");
		}
		else
		{
		g.drawString("TIE!",200,550);
		System.out.println("tie game");
		}
    }
	public boolean isPlayable (int x, int y) {
		x=(x-boardoffset)/50;
		y=(y-boardoffset)/50;
		if (x>=0&&x<=7&&y>=0&&y<=7)
		{
		if (board[x][y]==green)
			return true;
		}
			return false;
    }
	
	public void play(int x, int y) {
	x=(x-boardoffset)/50;
	y=(y-boardoffset)/50;
		board[x][y]=turn;
		ChangePieces(x,y);
    }
	
	public void drawBoard(Graphics g) {
		g.setColor(Background);
		g.fillRect(0,0,apwidth,apheight);
		g.setColor(Color.black);
		g.drawRect(boardoffset,boardoffset,400,400);
		for(int i=boardoffset; i<500; i+=50)
		{
			g.drawLine(i,boardoffset,i,boardoffset + 400);
			g.drawLine(boardoffset,i,boardoffset + 400,i);
		}
    }
	public void drawPieces(Graphics g) {
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
			{
			if(board[i][j]==white)
			{
				g.setColor(Color.white);
				g.fillOval(boardoffset + 50*i,boardoffset + 50*j,50,50);
			}
			else if(board[i][j]==black)
			{
				g.setColor(Color.black);
				g.fillOval(boardoffset + 50*i,boardoffset + 50*j,50,50);
			}
			else if(board[i][j]==green)
			{
				g.setColor(Color.green);
				g.fillOval(boardoffset+ 15 + 50*i,boardoffset+ 15 + 50*j,20,20);
			}
			}
    }
	public void findMoves() {
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
			{
			if(board[i][j]==turn)
				{
				//System.out.println(turn + " i = " + i + " j = " + j);
				setMoves(i,j);
				}
			}
    }
	public void ResetMoveList() {
		for (int i=0; i<8; i++)
			for (int j=0; j<8; j++)
			{
			if(board[i][j]==green)
				{
				board[i][j]=blank;
				}
			}
    }
	public void ChangePieces(int i,int j) {
		//left
		int resetj=j;
		int reseti=i;
		int counter = 0;
		if(j>0&&board[i][j-1]!=blank&&board[i][j-1]!=green)
		{
			while(j>=0&&j<=7&&i>=0&&i<=7&&board[i][j]!=blank && board[i][j]!=green)
			{
				if (board[i][j]==turn)
					counter++;
				j--;
			}
			i=reseti;
			j=resetj;
			while(j>=0&&j<=7&&i>=0&&i<=7&&board[i][j]!=blank && board[i][j]!=green&&counter>0)
			{
				if (board[i][j]==turn)
				{
				counter--;
				}
				board[i][j]=turn;
				j--;
			}
			i=reseti;
			j=resetj;
		}
		
		//right
		if(j<7&&board[i][j+1]!=blank&&board[i][j+1]!=green)
		{
			while(j>=0&&j<=7&&i>=0&&i<=7&&board[i][j]!=blank && board[i][j]!=green)
			{
				if (board[i][j]==turn)
					counter++;
				j++;
			}
			i=reseti;
			j=resetj;
			while(j>=0&&j<=7&&i>=0&&i<=7&&board[i][j]!=blank && board[i][j]!=green&&counter>0)
			{
				if (board[i][j]==turn)
				{
				counter--;
				}
				board[i][j]=turn;
				j++;
			}
			i=reseti;
			j=resetj;
			counter=0;
		}
		//up
		if(i>0&&board[i-1][j]!=blank&&board[i-1][j]!=green)
		{
			while(j>=0&&j<=7&&i>=0&&i<=7&&board[i][j]!=blank && board[i][j]!=green)
			{
				if (board[i][j]==turn)
					counter++;
				i--;
			}
			i=reseti;
			j=resetj;
			while(j>=0&&j<=7&&i>=0&&i<=7&&board[i][j]!=blank && board[i][j]!=green&&counter>0)
			{
				if (board[i][j]==turn)
				{
				counter--;
				}
				board[i][j]=turn;
				i--;
			}
			i=reseti;
			j=resetj;
			counter=0;
		}
		//down
		if(i<7&&board[i+1][j]!=blank&&board[i+1][j]!=green)
		{
			while(j>=0&&j<=7&&i>=0&&i<=7&&board[i][j]!=blank && board[i][j]!=green)
			{
				if (board[i][j]==turn)
					counter++;
				i++;
			}
			i=reseti;
			j=resetj;
			while(j>=0&&j<=7&&i>=0&&i<=7&&board[i][j]!=blank && board[i][j]!=green&&counter>0)
			{
				if (board[i][j]==turn)
				{
				counter--;
				}
				board[i][j]=turn;
				i++;
			}
			i=reseti;
			j=resetj;
			counter=0;
		}	
		//up left
		if(i<7&&j>0&&board[i+1][j-1]!=blank&&board[i+1][j-1]!=green)
		{
			while(j>=0&&j<=7&&i>=0&&i<=7&&board[i][j]!=blank && board[i][j]!=green)
			{
				if (board[i][j]==turn)
					counter++;
				j--;
				i++;
			}
			i=reseti;
			j=resetj;
			while(j>=0&&j<=7&&i>=0&&i<=7&&board[i][j]!=blank && board[i][j]!=green&&counter>0)
			{
				if (board[i][j]==turn)
				{
				counter--;
				}
				board[i][j]=turn;
				j--;
				i++;
			}
			i=reseti;
			j=resetj;
			counter=0;
		}
		
		//up right
		if(i<7&&j<7&&board[i+1][j+1]!=blank&&board[i+1][j+1]!=green)
		{
			while(j>=0&&j<=7&&i>=0&&i<=7&&board[i][j]!=blank && board[i][j]!=green)
			{
				if (board[i][j]==turn)
					counter++;
				j++;
				i++;
			}
			i=reseti;
			j=resetj;
			while(j>=0&&j<=7&&i>=0&&i<=7&&board[i][j]!=blank && board[i][j]!=green&&counter>0)
			{
				if (board[i][j]==turn)
				{
				counter--;
				}
				board[i][j]=turn;
				j++;
				i++;
			}
			i=reseti;
			j=resetj;
			counter=0;
		}
		//down left
		if(i>0&&j>0&&board[i-1][j-1]!=blank&&board[i-1][j-1]!=green)
		{
			while(j>=0&&j<=7&&i>=0&&i<=7&&board[i][j]!=blank && board[i][j]!=green)
			{
				if (board[i][j]==turn)
					counter++;
				j--;
				i--;
			}
			i=reseti;
			j=resetj;
			while(j>=0&&j<=7&&i>=0&&i<=7&&board[i][j]!=blank && board[i][j]!=green&&counter>0)
			{
				if (board[i][j]==turn)
				{
				counter--;
				}
				board[i][j]=turn;
				j--;
				i--;
			}
			i=reseti;
			j=resetj;
			counter=0;
		}
		//down right
		if(i>0&&j<7&&board[i-1][j+1]!=blank&&board[i-1][j+1]!=green)
		{
			while(j>=0&&j<=7&&i>=0&&i<=7&&board[i][j]!=blank && board[i][j]!=green)
			{
				if (board[i][j]==turn)
					counter++;
				j++;
				i--;
			}
			i=reseti;
			j=resetj;
			while(j>=0&&j<=7&&i>=0&&i<=7&&board[i][j]!=blank && board[i][j]!=green&&counter>0)
			{
				if (board[i][j]==turn)
				{
				counter--;
				}
				board[i][j]=turn;
				j++;
				i--;
			}
			i=reseti;
			j=resetj;
			counter=0;
		}	
    }
	
	public void setMoves(int i,int j) {
		//left
		int resetj=j;
		int reseti=i;
		if(j>0&&board[i][j-1]==otherturn)
		{
			while(j>=0&&j<=7&&i>=0&&i<=7&&board[i][j]!=blank && board[i][j]!=green)
			{
				j--;
			}
			if(j>=0&&j<=7&&i>=0&&i<=7&&board[i][j]==blank)
			board[i][j]=green;
			i=reseti;
			j=resetj;
		}
		
		//right
		if(j<7&&board[i][j+1]==otherturn)
		{
			while(j>=0&&j<=7&&i>=0&&i<=7&&board[i][j]!=blank && board[i][j]!=green)
			{
				j++;
			}
			if(j>=0&&j<=7&&i>=0&&i<=7&&board[i][j]==blank)
			board[i][j]=green;
			i=reseti;
			j=resetj;
		}
		//up
		if(i>0&&board[i-1][j]==otherturn)
		{
			while(j>=0&&j<=7&&i>=0&&i<=7&&board[i][j]!=blank && board[i][j]!=green)
			{
				i--;
			}
			if(j>=0&&j<=7&&i>=0&&i<=7&&board[i][j]==blank)
			board[i][j]=green;
			i=reseti;
			j=resetj;
		}
		//down
		if(i<7&&board[i+1][j]==otherturn)
		{
			while(j>=0&&j<=7&&i>=0&&i<=7&&board[i][j]!=blank && board[i][j]!=green)
			{
				i++;
			}
			if(j>=0&&j<=7&&i>=0&&i<=7&&board[i][j]==blank)
			board[i][j]=green;
			i=reseti;
			j=resetj;
		}	
		//up left
		if(i<7&&j>0&&board[i+1][j-1]==otherturn)
		{
			while(j>=0&&j<=7&&i>=0&&i<=7&&board[i][j]!=blank && board[i][j]!=green)
			{
				j--;
				i++;
			}
			if(j>=0&&j<=7&&i>=0&&i<=7&&board[i][j]==blank)
			board[i][j]=green;
			i=reseti;
			j=resetj;
		}
		
		//up right
		if(i<7&&j<7&&board[i+1][j+1]==otherturn)
		{
			while(j>=0&&j<=7&&i>=0&&i<=7&&board[i][j]!=blank && board[i][j]!=green)
			{
				j++;
				i++;
			}
			if(j>=0&&j<=7&&i>=0&&i<=7&&board[i][j]==blank)
			board[i][j]=green;
			i=reseti;
			j=resetj;
		}
		//down left
		if(i>0&&j>0&&board[i-1][j-1]==otherturn)
		{
			while(j>=0&&j<=7&&i>=0&&i<=7&&board[i][j]!=blank && board[i][j]!=green)
			{
				j--;
				i--;
			}
			if(j>=0&&j<=7&&i>=0&&i<=7&&board[i][j]==blank)
			board[i][j]=green;
			i=reseti;
			j=resetj;
		}
		//down right
		if(i>0&&j<7&&board[i-1][j+1]==otherturn)
		{
			while(j>=0&&j<=7&&i>=0&&i<=7&&board[i][j]!=blank && board[i][j]!=green)
			{
				j++;
				i--;
			}
			if(j>=0&&j<=7&&i>=0&&i<=7&&board[i][j]==blank)
			board[i][j]=green;
			i=reseti;
			j=resetj;
		}	
    }
    	
    	public void delay(double n)
	{
		long startDelay = System.currentTimeMillis();
		long endDelay = 0;
		while (endDelay - startDelay < n)
			endDelay = System.currentTimeMillis();	
	}

}	