/******
* name: Patrick Au, James Long
* date: March 2017
* code: ICS4U1
* note: Cool Platformer main class, manages different game screens with a CardLayout
*******/

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class CoolPlatformer 
{
	// Offset so JF Dimensions = JP
	private static final int offX = 6;
	private static final int offY = 29;

	public static final Dimension size = new Dimension(800, 600); // Size of Game Screen
	public static final Dimension sizeJF = new Dimension(size.width+offX, size.height+offY);
	public static JFrame JF; // Main JFrame
	public static JPanel JP; // Main JPanel
	public static JPanel[] menu; // All menu panels

	public CoolPlatformer() 
	{
		JF = new JFrame("Cool Platformer");
		JF.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JF.setPreferredSize(sizeJF);
		JF.setResizable(false);
		
		JP = new JPanel();
		JP.setLayout(new CardLayout());
		
		try 
		{
			Images.loadAll();
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}	// end catch

		menu = new JPanel[8]; // Number of Menu Screens
		menu[0] = new TitleScreen();
		menu[1] = new EditorScreen();
		menu[2] = new GameScreen(size);
		menu[3] = new PauseScreen();
		menu[4] = new InstructScreen();
		menu[5] = new MapScreen((EditorScreen)menu[1]); // This screen must be passed as an argument
		menu[6] = new WinScreen();
		menu[7] = new LoseScreen();
		
		JP.add(menu[0], "TitleScreen");
		JP.add(menu[1], "EditorScreen");
		JP.add(menu[2], "GameScreen");
		JP.add(menu[3], "PauseScreen");
		JP.add(menu[4], "InstructScreen");
		JP.add(menu[5], "MapScreen");
		JP.add(menu[6], "WinScreen");
		JP.add(menu[7], "LoseScreen");
		
		((CardLayout)JP.getLayout()).show(JP, "TitleScreen");
		
		JF.add(JP, BorderLayout.CENTER);

		JF.pack();
		JF.setLocationRelativeTo(null); // Puts JF at Centre of Screen
		JF.setVisible(true);
	}	// end constructor()

	public static void changeScreen(String screen)
	{
		CardLayout clayoutJP = (CardLayout)JP.getLayout();
		clayoutJP.show(JP, screen); // Change the visible card in the layout
	}
	
	public static void changeScreen(String screen, String cmd) // Overloaded changeScreen with a String command
	{
		if(screen.equals("MapScreen"))
			((MapScreen)menu[5]).setPurpose(cmd);
		else if(screen.equals("GameScreen"))
			((GameScreen)menu[2]).init(cmd);
		changeScreen(screen);
	}
	
	public static void main(String[] args) 
	{
		new CoolPlatformer();
	}	// end method main
}	// end class CoolPlatformer
