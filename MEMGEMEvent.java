/*************************************************************
 * File:         MEMGEMEvent.java                            *
 * Description:  Action Listener class to handle the	     *
 *		 GUI events from MEMGEM.java.		     *
 *************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class MEMGEMEvent implements ActionListener, ItemListener
{
	MEMGEM currentGui;
	FlashEngine currentEngine = new FlashEngine();
	String selected;

	public MEMGEMEvent(MEMGEM currentGui)
	{
		this.currentGui = currentGui;
		selected = "";
	}

	public void itemStateChanged(ItemEvent evt){}

	public void actionPerformed(ActionEvent evt)
	{
		/*******************/
		/** START  BUTTON **/
		/*******************/
		if (evt.getSource() == currentGui._start)
		{
			if (selected == null){
				System.out.println("Please select a deck first!");
				return;
			}
			currentEngine.reset();
			int numCorrect = 0;
			Deck d = currentEngine.getDeck(selected);
			for (int i = 0; i < d.numQuestions(); i++)
			{
				String cardQ = d.getQuestions().get(i).getQuestion();
				String cardA = d.getQuestions().get(i).getAnswer();
				try
				{
					String numQ = "Card #"+i;
					String userA = (String)(JOptionPane.showInputDialog(null, cardQ+"\nYour Answer: ", numQ, JOptionPane.PLAIN_MESSAGE));
					if (userA.equals(cardA)){
						numCorrect++;
						JOptionPane.showMessageDialog(currentGui, "Correct");
					}
					else 	JOptionPane.showMessageDialog(currentGui, "Incorrect\n"+cardA);
				} catch (Exception e) { JOptionPane.showMessageDialog(currentGui, "Incorrect"); }
			}
			JOptionPane.showMessageDialog(currentGui, "Questions Correct: " + numCorrect + "\nTotal Questions: " + d.numQuestions());
		}

		/*******************/
		/** CREATE BUTTON **/
		/*******************/
		else if (evt.getSource() == currentGui._create)
		{
			Deck d = new Deck(JOptionPane.showInputDialog("Enter Title of Deck: "));
			int num = Integer.parseInt(JOptionPane.showInputDialog("Enter Number of Cards: "));
			for (int i = 0; i < num; i++){
				String question = JOptionPane.showInputDialog("Enter Question: ");
				String answer = JOptionPane.showInputDialog("Enter Answer: ");
				d.writeQ(question, answer);
			}
			// after done writing all cards, save the deck
			try 
			{
				d.saveToFile();
				currentEngine.addDeck(d);
				currentEngine.saveToFile();
			}
			catch (Exception e) { System.out.println("(From MEMGEMEvent.java) Exception caught: "+e); }
			// kills the original JFrame window and opens a new one
			currentGui.dispose();
			currentGui = new MEMGEM();
		}

		/*******************/
		/** DELETE BUTTON **/
		/*******************/
		else if (evt.getSource() == currentGui._delete){
			if (selected == null){
				System.out.println("Please select a deck first!");
				return;
			}
			currentEngine.removeDeck(selected);
			try { currentEngine.saveToFile(); }
			catch (Exception e) { System.out.println("(From MEMGEMEvent.java) Exception caught: "+e); }
			currentGui.dispose();
			currentGui = new MEMGEM();
		}

		/********************/
		/** SHUFFLE BUTTON **/
		/********************/
		else if (evt.getSource() == currentGui._shuffle){
			currentEngine.shuffleDeck();
			try { currentEngine.saveToFile(); }
			catch (Exception e) { System.out.println("(From MEMGEMEvent.java) Exception caught: "+e); }
			currentGui.dispose();
			currentGui = new MEMGEM();
		}

		else if (evt.getSource() == currentGui.about)
		{
			JOptionPane.showMessageDialog(currentGui, "MEM GEM\nTrevor Fox & Nick Butler\nDecember 2016, CS 360");
		}
	}
};
