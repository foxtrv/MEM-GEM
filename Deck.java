/*************************************************************
* File:         Deck.java                                    *
* Description:  Object class to store a deck of cards 	     *
* 		(question/answer pairs stored in Question).  *
*************************************************************/

import java.util.*;
import java.io.*;

public class Deck implements Serializable
{
	private ArrayList<Question> Q;	
	String title;
	int bestTime;
	
	// Read method. Reads the contents of a saved deck of Questions and
	// populates  an ArrayList with its contents. Returns that ArrayList.
	public ArrayList<Question> readFromFile() throws ClassNotFoundException, IOException
	{
		try
		{
			ArrayList<Question> temp = new ArrayList<Question>();
			Question q;
			FileInputStream inFile = new FileInputStream("questions.dat");
			ObjectInputStream inObject = new ObjectInputStream(inFile);
			temp = (ArrayList<Question>) inObject.readObject();
			return temp;
		}
		catch (IOException ex) { System.out.println("File not found..."); }
		catch (ClassNotFoundException ex) { System.out.println("Could not deserialize class..."); }
		return null;
	}

	// Save method. Writes each Question in the current ArrayList Q
	// to file. If it cannot, throws exception. Saved file is "questions.dat".
	public void saveToFile() throws IOException
	{
		try
		{
			FileOutputStream outFile = new FileOutputStream("questions.dat");
			ObjectOutputStream outObject = new ObjectOutputStream(outFile);
			outObject.writeObject(Q);
			outObject.close();
			outFile.close();
			System.out.println("(From Deck.java) Questions successfully saved!");
		}
		catch (IOException ex) 
		{ 
			System.out.println("(From Deck.java) Saving was unsuccessful..."); 
		}
	}
	
	// Settters
	public void setBestTime(int T) 		  { bestTime = T;    }
	public void setTitle(String T) 		  { title = T;	     }

	// Getters
	public String getTitle() 		  { return title;    }
	public int getBestTime() 		  { return bestTime; }
	public int numQuestions()		  { return Q.size(); }
	public ArrayList<Question> getQuestions() { return Q;        }

	// Default constructor
	public Deck()
	{
		Q = new ArrayList<Question>();
		title = "";
		bestTime = -1;
	}

	public Deck(String inTitle)
	{
		Q = new ArrayList<Question>();
		title = inTitle;
		bestTime = -1;
	}

	public Deck(Deck D)
	{
		Q = D.getQuestions();
		title = D.getTitle();
		bestTime = D.getBestTime();
	}

	public Deck writeQ(String question, String answer){
		Question q = new Question();
		q.setQuestion(question);
		q.setAnswer(answer);
		Q.add(q); // big Q is arraylist of questions (little q)
		return this;
	}
};
