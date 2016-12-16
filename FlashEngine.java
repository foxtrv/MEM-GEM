/*************************************************************
 * File:         FlashEngine.java                            *
 * Description:  Engine behind the flash card GUI. Runs the  *
 * 		 Deck class and keeps track of the 	     *
 *		 statistics.				     *
 *************************************************************/

import java.util.*;
import java.io.*;

public class FlashEngine implements Serializable
{
	public ArrayList<Deck> D = new ArrayList<Deck>();
	int numberCorrect;
	int totalNumber;
	int currentQ;
	String answer;

	// Constructor
	public FlashEngine()
	{
		try { D = readFromFile(); }
		catch (IOException ex) { System.out.println("Could not find previous decks..."); }
		catch (ClassNotFoundException ex) { System.out.println("File format not recognized..."); }
		finally {
			if (D == null) 
				D = new ArrayList<Deck>();
		}
		numberCorrect = 0;
		totalNumber = 0;
		currentQ = 0;
	}

	public boolean checkAnswer(String answer)
	{
		totalNumber++;
		if (this.answer.equals(answer))
		{
			numberCorrect++;
			return true;
		}
		return false;
	}

	// Setters
	public void addDeck(Deck d){ this.D.add(d); }

	// Getters
	public int getCorrect()   { return numberCorrect; }
	public int getTotal()     { return totalNumber;	  }
	public int getCurrentQ()  { return currentQ; 	  }
	public String getAnswer() { return answer; 	  }
	public int numDecks()     { return D.size(); 	  }

	public void reset()
	{
		numberCorrect = 0;
		totalNumber = 0;
		currentQ = 0;
	}

	public ArrayList<Deck> readFromFile() throws ClassNotFoundException, IOException
	{
		try
		{
			FileInputStream inFile = new FileInputStream("decks.dat");
			ObjectInputStream inObject = new ObjectInputStream(inFile);
			ArrayList<Deck> temp = (ArrayList<Deck>) inObject.readObject();
			inFile.close();
			inObject.close();                   
			return temp;
		}
		catch (IOException ex) { System.out.println("File not found..."); }
		catch (ClassNotFoundException ex) { System.out.println("Could not deserialize class..."); }
		return null;
	}

	public void saveToFile() throws IOException
	{
		try
		{
			FileOutputStream outFile = new FileOutputStream("decks.dat");
			ObjectOutputStream outObject = new ObjectOutputStream(outFile);
			outObject.writeObject(D);
			outObject.close();
			outFile.close();
			System.out.println("(From FlashEngine) Decks successfully saved!");
		}
		catch (IOException ex) { System.out.println("(From Flash Engine) Saving was unsuccessful..."); }
	}

	public Deck getDeck(String decktitle){
		for (int i = 0; i < D.size(); i++){
			if (decktitle.equals(D.get(i).getTitle()))
				return D.get(i);
		}
		return null;
	}

	public void removeDeck(String decktitle){
		for (int i = 0; i < D.size(); i++)
			if (decktitle.equals(D.get(i).getTitle()))
				D.remove(D.get(i));
	}

	public void shuffleDeck(){ Collections.shuffle(D); }
};
