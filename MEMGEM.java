/*************************************************************
 * File:         MEMGEM.java                                 *
 * Description:  Top level class that handles the main	     *
 * 		 display window and buttons.		     *
 *************************************************************/

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import java.util.*;
import java.io.*;

public class MEMGEM extends JFrame
{
	MEMGEMEvent evtListener = new MEMGEMEvent(this);
	JMenuBar flashCardsBar = new JMenuBar();
	JMenu helpMenu = new JMenu("Help");
	JMenuItem about = new JMenuItem("About");
	JPanel checkPanel = new JPanel();
	ButtonGroup cardType = new ButtonGroup();
	JPanel choicesPanel = new JPanel();
	JButton _start = new JButton("Start");
	JButton _create = new JButton("Create");
	JButton _shuffle = new JButton("Shuffle");
	JButton _delete = new JButton("Delete");

	// Driver
	public static void main(String[] arguments) { new MEMGEM(); }

	public MEMGEM()
	{
		super("MEM GEM");
		setSize(400, 250);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
		about.addActionListener(evtListener);
		_start.addActionListener(evtListener);
		_create.addActionListener(evtListener);
		_shuffle.addActionListener(evtListener);
		_delete.addActionListener(evtListener);
		setLayout(new BorderLayout());
		helpMenu.add(about);
		flashCardsBar.add(helpMenu);

		try {
			ArrayList<Deck> templabels = evtListener.currentEngine.readFromFile();
			String labels[] = new String[templabels.size()];
			for (int i = 0; i < templabels.size(); i++){
				String k = templabels.get(i).getTitle();
				labels[i] = k;
			}
			super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
			final JList list = new JList(labels); // display titles of decks
			// Store Deck Name Selected into string
			_start.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					evtListener.selected = (String) list.getSelectedValue();
				}
			});
			_delete.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					evtListener.selected = (String) list.getSelectedValue();
				}
			});
			JScrollPane scrollPane = new JScrollPane(list);
			Container contentPane = super.getContentPane();
			contentPane.add(scrollPane, BorderLayout.CENTER);
		}
		catch (Exception e){}
		choicesPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
		choicesPanel.add(_start);
		choicesPanel.add(_create);
		choicesPanel.add(_shuffle);
		choicesPanel.add(_delete);
		setJMenuBar(flashCardsBar);
		add(choicesPanel, BorderLayout.SOUTH);
		setVisible(true);
	}
};
