package ca.lakeeffect.eventreader;

import java.awt.BorderLayout;
import java.awt.Button;
import java.awt.Choice;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;

public class Selection extends JFrame implements MouseListener, ActionListener, KeyListener {

	private static final long serialVersionUID = 2L;

	private JPanel contentPane;
	
	
	//contains two search panels
	private JSplitPane inputSplitPaneSplitPane;
	private JSplitPane inputSplitPaneSplitPaneSplitPane;
	
	Choice choice;
	JTextArea robotSearch;
	private JSplitPane inputSplitPane;
	
	Choice choice2;
	JTextArea robotSearch2;
	private JSplitPane inputSplitPane2;
	
	Choice choice3;
	JTextArea robotSearch3;
	private JSplitPane inputSplitPane3;
	
	Button selectRobot;
	DirectoryChooser dirChooser;
	private Button selectDir;
	String directory = "C:\\Users\\Ajay\\git\\Scouting-Event-Reader\\src\\ca\\lakeeffect\\eventreader\\EventData";
	private JSplitPane splitPane;
	private JLabel dirLabel;
	
	ArrayList<String> robotNumbers = new ArrayList<String>();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Selection frame = new Selection();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Selection() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		contentPane.setLayout(new BorderLayout(0, 0));
		setContentPane(contentPane);
		setSize(260, 195);
		setResizable(false);
		setTitle("Event Viewer");
		
		inputSplitPaneSplitPane = new JSplitPane();
		inputSplitPaneSplitPane.setDividerSize(0);
		inputSplitPaneSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		inputSplitPaneSplitPane.setDividerLocation(50);
		
		inputSplitPaneSplitPaneSplitPane = new JSplitPane();
		inputSplitPaneSplitPaneSplitPane.setDividerSize(0);
		inputSplitPaneSplitPaneSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		inputSplitPaneSplitPaneSplitPane.setDividerLocation(50);
		
		inputSplitPaneSplitPaneSplitPane.setBottomComponent(inputSplitPane);
		
		inputSplitPane = new JSplitPane();
		inputSplitPane.setDividerSize(0);
		inputSplitPane.setOrientation(JSplitPane.VERTICAL_SPLIT);
		inputSplitPane.setDividerLocation(25);
		inputSplitPaneSplitPane.setTopComponent(inputSplitPane);
		
		robotSearch = new JTextArea();
		robotSearch.addKeyListener(this);
		
		choice = new Choice();
		
		inputSplitPane.setTopComponent(robotSearch);
		inputSplitPane.setBottomComponent(choice);
		
		//second search box
		
		inputSplitPane2 = new JSplitPane();
		inputSplitPane2.setDividerSize(0);
		inputSplitPane2.setOrientation(JSplitPane.VERTICAL_SPLIT);
		inputSplitPane2.setDividerLocation(25);
		inputSplitPaneSplitPane.setBottomComponent(inputSplitPane2);

		contentPane.add(inputSplitPaneSplitPane, BorderLayout.CENTER);
		
		robotSearch2 = new JTextArea();
		robotSearch2.addKeyListener(this);
		
		choice2 = new Choice();
		
		inputSplitPane2.setTopComponent(robotSearch2);
		inputSplitPane2.setBottomComponent(choice2);
		
		selectRobot = new Button("Select");
		contentPane.add(selectRobot, BorderLayout.SOUTH);
		selectRobot.addMouseListener(this);
		
		splitPane = new JSplitPane();
		splitPane.setDividerSize(1);
		splitPane.setDividerLocation(200);//(int) (contentPane.getWidth()/4*3));
		contentPane.add(splitPane, BorderLayout.NORTH);
		
		selectDir = new Button("CD");
		splitPane.setRightComponent(selectDir);
		
		dirLabel = new JLabel(directory);
		changeDir(directory);
		splitPane.setLeftComponent(dirLabel);
		selectDir.addMouseListener(this);
		

	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == selectDir){
			System.out.println("selectDir");
			DirectoryChooser frame = new DirectoryChooser(this, directory);
			frame.setVisible(true);
		}
		if(e.getSource() == selectRobot){
			selectRobot();
		}
	}
	
	public void selectRobot() {

		int amountChosen = 1;
		if(!choice2.getSelectedItem().equals("None")) {
			amountChosen += 1;
		}
		
		int[] choices = new int[amountChosen];
		choices[0] = Integer.parseInt(choice.getSelectedItem());
		if(amountChosen > 1) {
			choices[1] = Integer.parseInt(choice2.getSelectedItem());
		}
		
		String[] files = new String[amountChosen];
		for(int i=0; i < files.length; i++) {
			files[i] = directory+"\\"+choices[i]+".csv";
		}
		
		new Field(choices, 640, 480, files);
	}

	public void changeDir(String directory){
		this.directory = directory;
		System.out.println(directory);
		dirLabel.setText(directory);
		File dir = new File(directory);
		String[] children = dir.list();
		choice.removeAll();
		choice2.removeAll();
		choice2.add("None");
		for(String c : children){
			if(c.endsWith(".csv")){
				choice.add(c.replace(".csv", ""));
				robotNumbers.add(c.replace(".csv", ""));
				
				choice2.add(c.replace(".csv", ""));
			}
		}
	}
	
	@Override
	public void mousePressed(MouseEvent e) {
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println();
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		if(e.getKeyCode() == KeyEvent.VK_ENTER) {
			if(robotSearch.hasFocus()) {
				robotSearch.setText(robotSearch.getText().substring(0, robotSearch.getText().length() - 1));
				
				robotSearch2.requestFocus();
			} else if(robotSearch2.hasFocus()) {
				robotSearch2.setText(robotSearch2.getText().substring(0, robotSearch2.getText().length() - 1));
				
				robotSearch.requestFocus();
				
			}
			
			selectRobot();
		}
		
		if(e.getKeyCode() == KeyEvent.VK_TAB) {
			if(robotSearch.hasFocus()) {
				robotSearch.setText(robotSearch.getText().substring(0, robotSearch.getText().length() - 1));
				
				robotSearch2.requestFocus();
			} else if(robotSearch2.hasFocus()) {
				robotSearch2.setText(robotSearch2.getText().substring(0, robotSearch2.getText().length() - 1));
				
				robotSearch.requestFocus();
				
			}
			
			keyTyped(e);

		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
		String fullSearch = "";
		Choice currentChoice = null;
		
		if (e.getSource() == robotSearch) {
			fullSearch = robotSearch.getText();
			currentChoice = choice;
		} else if(e.getSource() == robotSearch2) {
			fullSearch = robotSearch2.getText();
			currentChoice = choice2;
		}
		
		if(isNumber(e.getKeyChar() + "")) {
			fullSearch += e.getKeyChar();
		} else if(e.getKeyChar() == '\n') {
			return;
		}
		
		currentChoice.removeAll();
		if(fullSearch.equals("") && currentChoice != choice) {
			currentChoice.add("None");
		}
		for(String robotNumber : robotNumbers){
			if(robotNumber.contains(fullSearch)) {
				currentChoice.add(robotNumber);
			}
		}
	}
	
	public boolean isNumber(String string) {
		return "1234567890".contains(string);
	}

}
