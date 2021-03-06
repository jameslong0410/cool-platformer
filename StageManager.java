/******
* name: Patrick Au, James Long
* date: March 2017
* code: ICS4U1
* note: Stage Loader and Saver Class
*******/
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.StringTokenizer;

import javax.swing.*;

class StageManager 
{
	static BufferedReader in;
	static BufferedWriter out;
	static StringTokenizer st;

	static String lastDir  = new File("include", "levels").getPath();
	static String lastFile = "blank.txt";

	public static String gets() throws IOException 
	{
		while(st==null || !st.hasMoreTokens())
			st = new StringTokenizer(in.readLine().trim());
		return st.nextToken();
	}	// end method gets

	public static void loadMap(Block[][] blockArr)
	{
		loadMap(new File(lastDir).getPath(), lastFile, blockArr);
	}	// end method loadMap

	public static void loadMap(final String dir, final String file, Block[][] blockArr)
	{	// Load level
		try
		{
			in = new BufferedReader(new FileReader(new File(dir, file)));
			for(int i = 0; i < blockArr.length; i++)
				for(int j = 0; j < blockArr[i].length; j++)
					blockArr[i][j].setBlock(Integer.parseInt(gets()));
			for(int i = 0; i < blockArr.length; i++)
				for(int j = 0; j < blockArr[i].length; j++)
					blockArr[i][j].setField(Integer.parseInt(gets()));
			in.close();
			// Save Filenames - remembers last level
			lastDir = dir;
			lastFile = file;
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}	// end method loadMap
	
	public static void saveMap(final String dir, final String file, final Block[][] blockArr)
	{	// Save level
		try
		{
			out = new BufferedWriter(new FileWriter(new File(dir, file)));
			for(int i = 0; i < blockArr.length; i++)
			{
				for(int j = 0; j < blockArr[i].length; j++)
				{
					out.write(blockArr[i][j].getBlock() + " ");
				}
				out.newLine();
			}
			out.newLine();
			for(int i = 0; i < blockArr.length; i++)
			{
				for(int j = 0; j < blockArr[i].length; j++)
				{
					out.write(blockArr[i][j].getField() + " ");
				}
				out.newLine();
			}
			out.close();
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	public static Block getGoal(final Block[][] blockArr)
	{
		for(int i = 0; i < blockArr.length; i++)
			for(int j = 0; j < blockArr[i].length; j++)
				if(blockArr[i][j].getBlock() == Block.GOAL)
					return blockArr[i][j];
		return null;
	}	// end method getGoal
}	// end class StageManager
