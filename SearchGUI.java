/************************************************************************
 *
 * 1. This software is for the purpose of demonstrating one of many
 * ways to implement the algorithms in Introduction to Algorithms,
 * Second edition, by Thomas H. Cormen, Charles E. Leiserson, Ronald
 * L. Rivest, and Clifford Stein.  This software has been tested on a
 * limited set of test cases, but it has not been exhaustively tested.
 * It should not be used for mission-critical applications without
 * further testing.
 *
 * 2. McGraw-Hill licenses and authorizes you to use this software
 * only on a microcomputer located within your own facilities.
 *
 * 3. You will abide by the Copyright Law of the United Sates.
 *
 * 4. You may prepare a derivative version of this software provided
 * that your source code indicates that it based on this software and
 * also that you have made changes to it.
 *
 * 5. If you believe that you have found an error in this software,
 * please send email to clrs-java-bugs@mhhe.com.  If you have a
 * suggestion for an improvement, please send email to
 * clrs-java-suggestions@mhhe.com.
 *
 ***********************************************************************/
/*Credits to McGraw-Hill this is an derivative version
 * Included different Searching algorithms.
 * Issues fixed MergeSearch
 * 
 * # Searching-Algorithms-in-Java-with-GUI
Searching algorithm implemented in java


Purpose:- To implement Searching algorithms.


Algorithms used:- Linear Search, Binary Search, Jump Search, Exponential Search, Fibonacci Search.


References:- Introduction to Algorithms, Second edition [1] geeksforgeeks.org [2].

Credits:- McGraw-Hill software implementation. This is a derived version of McGraw Hill software as per McGraw-Hill licenses. 
 * 
 * */
package org.neeraj.algorithms.searching;
import javax.swing.*;
import javax.swing.event.*;
import java.awt.*;
import java.awt.event.*;
import java.text.*;
import javax.swing.border.*;
import java.util.Random;

public class SearchGUI extends JFrame
{
    protected JCheckBox integersOnly;
    protected JTextField randText;
    static ListPanel listPanel;
    protected Algorithm algorithm = Algorithm.LINEAR;
    
    protected static class Algorithm{
		final public String name;
		final public Class factory;
		
		private Algorithm(String name, Class factory) {
		    this.name = name;
		    this.factory = factory;
		}
	
		public String toString() {
		    return name;
		}
	
		
		public static final Algorithm LINEAR = 
			    new Algorithm("LinearSearch", LinearSearch.class);
			public static final Algorithm BINARY = 
			    new Algorithm("BinarySearch", BinarySearch.class);
			public static final Algorithm JUMP = 
			    new Algorithm("JumpSearch", JumpSearch.class);
			public static final Algorithm EXPONENTIAL = 
			    new Algorithm("ExponentialSearch", ExponentialSearch.class);
			public static final Algorithm FIBONACCI = 
				    new Algorithm("FibonacciSearch", FibonacciSearch.class);
			
		public static final Algorithm[] SearchAlgo = {
				LINEAR, BINARY , JUMP ,  
				 EXPONENTIAL , FIBONACCI};
    }

    public SearchGUI(String title)
    {
		super(title);
		makeContentPane();
    }

    protected void makeContentPane()
    {
		JPanel algPanel = makeAlgorithmPanel();
		JPanel randPanel = makeRandomPanel();
		JPanel csPanel = makeClearSearchPanel();
		listPanel = new ListPanel(NumberFormat.getInstance());
		/*integersOnly.setSelected(true);
		integersOnly.setEnabled(false);*/
	
		Container contentPane = getContentPane();
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gc = new GridBagConstraints();
		contentPane.setLayout(gb);
	
		gc.weightx = 0.5; gc.weighty = 0.5;
		gc.fill = GridBagConstraints.BOTH;
	
		gc.gridx = 0; gc.gridy = 0; gc.gridwidth = 2;
		gb.setConstraints(algPanel, gc);
		contentPane.add(algPanel);
	
		gc.gridwidth = 1; gc.gridx = 2; gc.gridheight = 2;
		gb.setConstraints(listPanel, gc);
		contentPane.add(listPanel);
	
		gc.gridx = 0; gc.gridy = 1; gc.gridheight = 1;
		gb.setConstraints(randPanel, gc);
		contentPane.add(randPanel);
		
		gc.gridx = 1; gc.gridy = 1;
		gb.setConstraints(csPanel, gc);
		contentPane.add(csPanel);	
	}
	
	protected JPanel makeAlgorithmPanel() {
		JRadioButton[] algButtons;
		ButtonGroup group;
	
		JPanel panel = new JPanel();
		ActionListener algListener = new AlgorithmListener();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	
		group = new ButtonGroup(); 
		algButtons = new JRadioButton[Algorithm.SearchAlgo.length];
	
		for (int i = 0; i < Algorithm.SearchAlgo.length; i++) {
		    algButtons[i] = new JRadioButton(Algorithm.SearchAlgo[i].name);
		    algButtons[i].setActionCommand(Algorithm.SearchAlgo[i].name);
		    algButtons[i].addActionListener(algListener);
		    group.add(algButtons[i]);
		    panel.add(algButtons[i]);
		}
	
		algButtons[0].setSelected(true);
	
		integersOnly = new JCheckBox("Restrict to Integers");
		integersOnly.addItemListener(new IntegerListener());
		panel.add(integersOnly);
		
		panel.setBorder(BorderFactory.createTitledBorder
				(BorderFactory.createEtchedBorder
				 (EtchedBorder.RAISED),
				 "Algorithm"));
		return panel;
	 }
	
	    protected JPanel makeRandomPanel() {
		JPanel panel = new JPanel();
		JPanel subpanel = new JPanel();
		subpanel.setLayout(new BoxLayout(subpanel, BoxLayout.X_AXIS));
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
		JLabel randLabel = new JLabel("Count");
		randText = new JTextField(3);	
		JButton randButton = new JButton("Generate");	
		randButton.addActionListener(new RandomListener());
		randText.addActionListener(new RandomListener());
		subpanel.add(randLabel);
		subpanel.add(randText);
		panel.add(subpanel);
		panel.add(randButton);
		panel.setBorder(BorderFactory.createTitledBorder
				    (BorderFactory.createEtchedBorder
				     (EtchedBorder.RAISED),
				     "Random Input"));
		return panel;
    }

    protected JPanel makeClearSearchPanel()
    {
	JPanel panel = new JPanel();
	panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));

	JButton clear = new JButton("Clear");
	clear.addActionListener(new ClearListener());


	panel.add(clear);
	
	/*panel.setBorder(BorderFactory.createTitledBorder
			(BorderFactory.createEtchedBorder
			 (EtchedBorder.RAISED),
			 "Operations"));*/
	return panel;
    }

    protected class AlgorithmListener implements ActionListener
    {
	public void actionPerformed(ActionEvent ev)
	{
	    String name = ev.getActionCommand();
	    for (int i = 0; i < Algorithm.SearchAlgo.length; i++)
		if (Algorithm.SearchAlgo[i].name == name)
		    algorithm = Algorithm.SearchAlgo[i];
	    

	    /*if (algorithm == Algorithm.COUNT ||	algorithm == Algorithm.RADIX) {
			integersOnly.setSelected(true);
			integersOnly.setEnabled(false);
	    }
	    
	    else 
	    	integersOnly.setEnabled(true);*/
	}
    }
    protected class IntegerListener implements ItemListener
    {
	public void itemStateChanged(ItemEvent ev)
	{
	    if (ev.getStateChange() == ItemEvent.SELECTED) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setParseIntegerOnly(true);
		listPanel.setFormat(nf);
	    }	    
	    else if (ev.getStateChange() == ItemEvent.DESELECTED) {
		NumberFormat nf = NumberFormat.getInstance();
		nf.setParseIntegerOnly(false);
		listPanel.setFormat(nf);
	    }
	}
    }

    protected class RandomListener implements ActionListener
    {
		protected Random rand;
	
		public RandomListener() {
		    rand = new Random();
		}
	
		public void actionPerformed(ActionEvent ev)	{
			    int count = Integer.parseInt(randText.getText());
			    if (integersOnly.isSelected()) {
				for (int i = 0; i < count; i++)
				    listPanel.addElement(new Integer(rand.nextInt(100000)));
			    }
			    else {
				for (int i = 0; i < count; i++)
				    listPanel.addElement(new Double(rand.nextDouble()));
			    }
			}
	    }
	
	    protected class ClearListener implements ActionListener {
			public void actionPerformed(ActionEvent ev)	{
			    listPanel.clear();
			}
    }

    

    public static void main(String[] args)
    {
		JFrame gui = new SearchGUI("Searching Algorithms");
		gui.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		gui.pack();
		gui.setVisible(true);
    }
}
