package com.capricorn.view;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.CompoundBorder;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import com.capricorn.RequestController.ExitGameRequest;
import com.capricorn.RequestController.FindWordRequest;
import com.capricorn.RequestController.LockGameRequest;
import com.capricorn.RequestController.RepositionBoardRequest;
import com.capricorn.RequestController.ResetGameRequest;
import com.capricorn.entity.Model;
import com.capricorn.entity.Player;
import com.capricorn.entity.Word;
import com.capricorn.listener.ClickButton_multiGame;
import com.capricorn.listener.Exit_practiceGame;
// TODO: Auto-generated Javadoc
/** MultiGame is designed by JFrame and be designed to let players join the game.
 *  It is main board of the game in order to communication with server and players as a boundary
 *  @author Chen Li, Tianyu Wu, Yu Li
 */
public class MultiGame extends JFrame {
	
	/** The text field word. */
	public JTextField textField_word;
	
	/** The text field escore. */
	public JTextField textField_escore;
	
	/** The model. */
	private Model model;
	
	/** The table. */
	private JTable table;
	
	/** The table 2. */
	private JTable table2;
	
	/** The chosenbtns. */
	private List<JButton> chosenbtns;
	
	/** The all cellsbtns. */
	private List<JButton> allCellsbtns;
	
	/** The word score sum. */
	public int wordScoreSum;
	
	/** The panel. */
	private JPanel panel;
	
	/** The app. */
	private Application app;
	
	/** The message. */
	public JLabel message;
	
	/** The text field name. */
	private JTextField textField_name;
	
	/** The text field game id. */
	private JTextField textField_gameId;
	
	/** The text field manager. */
	private JTextField textField_manager;
	
	/** The text field score. */
	private JTextField textField_score;
	
	/** The button lock. */
	public JButton btnLock;
	
	/** The button reset. */
	public JButton button_reset;
	
	/** The button xmlc. */
	public JButton btn_xmlc;
	
	/** The button xmlo. */
	public JButton btn_xmlo;
	
	/** The button left. */
	public JButton button_left;
	
	/** The button up. */
	public JButton button_up;
	
	/** The button right. */
	public JButton button_right;
	
	/** The button down. */
	public JButton button_down;
	
	/** The button submit. */
	public JButton button_submit;
	
	/** The btn exit. */
	public JButton btnExit;
	
	/** The btn clear. */
	public JButton btnClear;
	
	/** The text field player num. */
	private JTextField textField_playerNum;
	
/**
 *  Construct MultiGame object to use default port number.
 *
 * @param m the Model
 * @param app the Application
 */
	public MultiGame(Model m, Application app)  {
		this.app = app;
		this.model = m;
		

		getContentPane().setLayout(null);
		chosenbtns = new ArrayList<JButton>();
		allCellsbtns = new ArrayList<JButton>();

		setSize(920, 800); 
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);

		setTitle("Your Game");
		getContentPane().setLayout(null);

		textField_word = new JTextField();
		textField_word.setBounds(603, 65, 262, 22);
		getContentPane().add(textField_word);
		textField_word.setColumns(10);
		textField_word.setEditable(false);

		JLabel lblYourWord = new JLabel("Your Word :");
		lblYourWord.setBounds(516, 66, 85, 20);
		getContentPane().add(lblYourWord);

		JLabel lblYourScore = new JLabel("Expected Word Score :");
		lblYourScore.setBounds(444, 98, 147, 16);
		getContentPane().add(lblYourScore);

		textField_escore = new JTextField();
		textField_escore.setColumns(10);
		textField_escore.setBounds(603, 92, 262, 28);
		getContentPane().add(textField_escore);
		textField_escore.setText("0");
		textField_escore.setEditable(false);

		button_up = new JButton("UP");
		button_up.setBackground(Color.WHITE);
		button_up.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		/**Adding an actionListener when click the button up, the game board domain will shift up forward.*/
		button_up.addActionListener(new ActionListener() {
			int previousRow;
			int newRow;
			@Override
			public void actionPerformed(ActionEvent e) {
				Integer[] change = { -1, 0 };
				clearAllChosen();
				previousRow = model.getBoard().getGlobalStartingRow();
				model.getBoard().setRequestColChange(1);
				new RepositionBoardRequest(model, MultiGame.this.app, change).process();
				try {
					Thread.sleep(300);
				} catch (InterruptedException e1) {
					
					e1.printStackTrace();
				}

				newRow = model.getBoard().getGlobalStartingRow();
				if (previousRow == newRow) {
					message.setText("No More Up!");
				}
			}

		});
		button_up.setToolTipText("");
		button_up.setBounds(641, 163, 69, 38);
		getContentPane().add(button_up);

		button_left = new JButton("LEFT");
		button_left.setBackground(Color.WHITE);
		button_left.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		button_left.setBounds(555, 197, 75, 38);
		getContentPane().add(button_left);
		/**Adding an actionListener when click the button left, the game board domain will shift left forward.*/
		button_left.addActionListener(new ActionListener() {
			int previousCol;
			int newCol;

			@Override
			public void actionPerformed(ActionEvent e) {
				Integer[] change = { 0, -1 };
				clearAllChosen();
				previousCol = model.getBoard().getGlobalStartingCol();
				model.getBoard().setRequestColChange(1);
				new RepositionBoardRequest(model, MultiGame.this.app, change).process();
				try {
					Thread.sleep(200);
				} catch (InterruptedException e1) {
					
					e1.printStackTrace();
				}

				newCol = model.getBoard().getGlobalStartingCol();
				if (previousCol == newCol) {
					message.setText("No More Left!");
				}
			}

		});

		button_right = new JButton("RIGHT");
		button_right.setBackground(Color.WHITE);
		button_right.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		button_right.setBounds(716, 197, 75, 38);
		/**Adding an actionListener when click the button right, the game board domain will shift right forward.*/
		button_right.addActionListener(new ActionListener() {
			int previousCol;
			int newCol;

			@Override
			public void actionPerformed(ActionEvent e) {
				Integer[] change = { 0, 1 };
				clearAllChosen();
				previousCol = model.getBoard().getGlobalStartingCol();
				model.getBoard().setRequestColChange(1);
				new RepositionBoardRequest(model, MultiGame.this.app, change).process();
				try {
					Thread.sleep(200);
				} catch (InterruptedException e1) {
					
					e1.printStackTrace();
				}

				newCol = model.getBoard().getGlobalStartingCol();
				if (previousCol == newCol) {
					message.setText("No More Right");
				}
			}

		});
		getContentPane().add(button_right);

		button_down = new JButton("DOWN");
		button_down.setBackground(Color.WHITE);
		button_down.setFont(new Font("Lucida Grande", Font.PLAIN, 10));
		button_down.setBounds(641, 231, 69, 38);
		getContentPane().add(button_down);
		/**Adding an actionListener when click the button down, the game board domain will shift down forward.*/
		button_down.addActionListener(new ActionListener() {
			int previousRow;
			int newRow;

			@Override
			public void actionPerformed(ActionEvent e) {
				Integer[] change = { 1, 0 };
				clearAllChosen();
				previousRow = model.getBoard().getGlobalStartingRow();
				model.getBoard().setRequestColChange(1);
				new RepositionBoardRequest(model, MultiGame.this.app, change).process();
				try {
					Thread.sleep(200);
				} catch (InterruptedException e1) {
					
					e1.printStackTrace();
				}

				newRow = model.getBoard().getGlobalStartingRow();
				if (previousRow == newRow) {
					message.setText("No More Down!");
				}
			}

		});

		button_submit = new JButton("Submit");
		button_submit.setBackground(Color.WHITE);
		button_submit.setBounds(472, 13, 147, 40);
		getContentPane().add(button_submit);
		/**Adding an actionListener when click the button submit, the game board domain will show the validation of the word
		 * and add scores to the score area. 
		 */
		button_submit.addActionListener(new ActionListener() {
			
			long localExpectedWordScore;

			@Override
			public void actionPerformed(ActionEvent e) {

				String word = textField_word.getText();
				if (word.length() <= 1) {
					message.setText("Choose at least 2 letters");
					return;
				}
				localExpectedWordScore = Long.parseLong(textField_escore.getText());


				new FindWordRequest(model, MultiGame.this.app).process();
				try {
					Thread.sleep(350);
				} catch (InterruptedException e1) {
				
					e1.printStackTrace();
				}
				clearAllChosen();
				long wordScore=model.getBoard().getWord().getScore();

				if (wordScore==0) {
					message.setText("Word Picked By Others or It is illegal");
				} else {
					String wordScoreFromServer = String.valueOf(wordScore);
					SimpleDateFormat df = new SimpleDateFormat("HH:mm:ss");
                    model.getGame().getSelectedWord().add(new Word(word,wordScore,df.format(new Date())));
                    setWordTable();
                    
					if (localExpectedWordScore != Integer.valueOf(wordScoreFromServer)) {
						message.setText("You get a bonus");
					}
				}

				

			}
		});

		btnExit = new JButton("Exit");
		btnExit.setBackground(Color.RED);
		btnExit.addActionListener(new ActionListener() {
/**Realize the exit button.*/
			public void actionPerformed(ActionEvent e) {
				new ExitGameRequest(model, MultiGame.this.app).process();
				MultiGame.this.dispose();
				Application page = Application.getInstance(model);
				page.setSize(600, 300); 
				page.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				page.setVisible(true);
			}
		});	


		Exit_practiceGame exitControl = new Exit_practiceGame(this, model);
		btnExit.addActionListener(exitControl);
		btnExit.setBounds(625, 14, 69, 38);
		getContentPane().add(btnExit);

		btnLock = new JButton("Lock");
		if (m.getGame().isLocked() || !(m.getGame().getManagingUser().equals(m.getPlayer().getName()))) {

			btnLock.setEnabled(false);
		} else {
			btnLock.setEnabled(true);
		}
/**Realize the lock button.*/
		btnLock.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				new LockGameRequest(model, MultiGame.this.app).process();
				try {
					Thread.sleep(150);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				JButton button = (JButton) e.getSource();
				button.setEnabled(false);
				message.setText("You have locked your game");

			}
		});
		btnLock.setBounds(35, 378, 89, 23);
		getContentPane().add(btnLock);

		button_reset = new JButton("Reset Board");
		button_reset.setBounds(35, 343, 111, 23);
		getContentPane().add(button_reset);
		button_reset.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {

				new ResetGameRequest(model, MultiGame.this.app).process();
				try {
					Thread.sleep(250);
				} catch (InterruptedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				message.setText("You have reset your game");

			}

		});

		JLabel lblScoreRanking = new JLabel("Score Ranking :");
		lblScoreRanking.setBounds(42, 240, 101, 16);
		getContentPane().add(lblScoreRanking);

		JLabel lblManagerPanel = new JLabel("Manager Control");
		lblManagerPanel.setBounds(42, 315, 126, 16);
		getContentPane().add(lblManagerPanel);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(158, 239, 390, 197);
		getContentPane().add(scrollPane);
		table = new JTable();

		scrollPane.setViewportView(table);
		table.setBounds(200, 238, 402, 234);

		panel = new JPanel();
		panel.setForeground(Color.WHITE);
		panel.setLayout(null);
		panel.setBounds(6, 45, 426, 179);
		getContentPane().add(panel);
		panel.setBorder(new CompoundBorder());

		panel.setBorder(BorderFactory.createLineBorder(Color.gray));

		JLabel lblNewLabel = new JLabel("      Your Game Board");
		lblNewLabel.setBounds(114, 6, 285, 22);
		panel.add(lblNewLabel);

		message = new JLabel("");
		message.setBounds(555, 281, 310, 144);
		getContentPane().add(message);
		message.setBorder(new TitledBorder(null, "", TitledBorder.LEADING, TitledBorder.TOP, null, null));
		message.setBorder(BorderFactory.createLineBorder(Color.gray));
		message.setForeground(Color.red);

		btnClear = new JButton("Clear");
		btnClear.setBackground(Color.CYAN);
		btnClear.setBounds(706, 13, 89, 38);
		getContentPane().add(btnClear);
		/**Realize the clear button*/
		btnClear.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				clearAllChosen();

			}
		});

		JLabel lblYourName = new JLabel("Your Name:");
		lblYourName.setBounds(158, 509, 83, 23);
		getContentPane().add(lblYourName);

		JLabel lblGameId = new JLabel("Game ID:");
		lblGameId.setBounds(168, 454, 89, 27);
		getContentPane().add(lblGameId);

		textField_name = new JTextField();
		textField_name.setBounds(242, 503, 240, 35);
		getContentPane().add(textField_name);
		textField_name.setColumns(10);
		textField_name.setEditable(false);

		textField_gameId = new JTextField();
		textField_gameId.setBounds(242, 451, 301, 33);
		getContentPane().add(textField_gameId);
		textField_gameId.setColumns(10);
		textField_gameId.setEditable(false);

		textField_manager = new JTextField();
		textField_manager.setBounds(242, 562, 240, 38);
		getContentPane().add(textField_manager);
		textField_manager.setColumns(10);
		textField_manager.setEditable(false);
		JLabel lblManagerName = new JLabel("Manager Name:");
		lblManagerName.setBounds(127, 568, 103, 16);
		getContentPane().add(lblManagerName);

		textField_score = new JTextField();
		textField_score.setText("0");
		textField_score.setBounds(603, 120, 262, 28);
		getContentPane().add(textField_score);
		textField_score.setColumns(10);
		textField_score.setEditable(false);
		JLabel lblYourScore_1 = new JLabel("Your Score:");
		lblYourScore_1.setBounds(516, 126, 85, 16);
		getContentPane().add(lblYourScore_1);

		JLabel lblNewLabel_1 = new JLabel("");
		lblNewLabel_1.setBounds(586, 273, 89, 16);
		getContentPane().add(lblNewLabel_1);

		JLabel lblMessage = new JLabel("Message:");
		lblMessage.setBounds(579, 286, 61, 16);
		getContentPane().add(lblMessage);
		
		btn_xmlo = new JButton("Open Xml Message With Server");
		btn_xmlo.setBounds(555, 437, 240, 38);
		getContentPane().add(btn_xmlo);
		this.btn_xmlo.addActionListener(new Action());
		
		btn_xmlc = new JButton("Close Xml Message With Server");
		btn_xmlc.setBounds(555, 475, 240, 37);
		getContentPane().add(btn_xmlc);
		
		
		this.btn_xmlc.addActionListener(new Action());
		JScrollPane scrollPane_selectedword = new JScrollPane();
		scrollPane_selectedword.setBounds(516, 562, 361, 210);
		getContentPane().add(scrollPane_selectedword);
		table2 = new JTable();

		scrollPane_selectedword.setViewportView(table2);
		
		JLabel lblWordSelectedHistory = new JLabel("      Legal Words Selected History");
		lblWordSelectedHistory.setFont(new Font("Lucida Grande", Font.PLAIN, 15));
		lblWordSelectedHistory.setBounds(517, 524, 297, 30);
		getContentPane().add(lblWordSelectedHistory);
		
		JLabel lblNewLabel_playerNum = new JLabel("Player number:");
		lblNewLabel_playerNum.setBounds(107, 650, 101, 38);
		getContentPane().add(lblNewLabel_playerNum);
		
		textField_playerNum = new JTextField();
		textField_playerNum.setEditable(false);
		textField_playerNum.setBounds(205, 655, 101, 33);
		getContentPane().add(textField_playerNum);
		textField_playerNum.setColumns(10);
		setallCellsbtns();
		refreshBoard();

		
	}
	
	/**
	 * The Class Action.
	 */
	class Action  implements ActionListener{
		
		/* (non-Javadoc)
		 * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
		 */
		@Override
	public void actionPerformed(ActionEvent e) {
			
			
		
		if(e.getSource().equals(btn_xmlo)){
		JButton btn=(JButton)e.getSource();
		
		btn.setEnabled(false);
		btn_xmlc.setEnabled(true);
	    MultiGame.this.app.getXmlb().setVisible(true);
		}
		if(e.getSource().equals(btn_xmlc)){
			JButton btn=(JButton)e.getSource();
			
			btn.setEnabled(false);
			btn_xmlo.setEnabled(true);
		    MultiGame.this.app.getXmlb().setVisible(false);
			}
	
	}

	}



	/**
	 * Sets the score table.
	 */
	public void setScoreTable() {
		table.setModel(new DefaultTableModel(convertPlayersListToArray(),
				new String[] { "Player Name", "Player Score", "Position", "Rank" }));
	}
	
	/**
	 * Sets the word table.
	 */
	public void setWordTable() {
		table2.setModel(new DefaultTableModel(convertWordListToArray(),
				new String[] { "Word", "Score","Time"}));
	}

	/**
	 * Gets the model.
	 *
	 * @return the model
	 */
	public Model getModel() {
		return model;
	}

	/**
	 * Sets the model.
	 *
	 * @param model the new model
	 */
	public void setModel(Model model) {
		this.model = model;
	}

	/**
	 * Gets the all cellsbtns.
	 *
	 * @return the all cellsbtns
	 */
	public List<JButton> getAllCellsbtns() {
		return allCellsbtns;
	}

	/**
	 * Sets the all cellsbtns.
	 *
	 * @param allCellsbtns the new all cellsbtns
	 */
	public void setAllCellsbtns(List<JButton> allCellsbtns) {
		this.allCellsbtns = allCellsbtns;
	}

	/**
	 * Convert players list to array.
	 *
	 * @return the object[][]
	 */
	public Object[][] convertPlayersListToArray() {
		List playersList = model.getGame().getPlayersListByScore();
		Object[][] objAy = new Object[playersList.size()][4];
		for (int i = 0; i < objAy.length; i++) {

			Player p = (Player) playersList.get(i);
			objAy[i][0] = p.getName();
			objAy[i][1] = p.getScore();
			objAy[i][2] = p.getPosition();
			objAy[i][3] = "No." + (i + 1);

		}
		return objAy;
	}
	
	/**
	 * Convert word list to array.
	 *
	 * @return the object[][]
	 */
	public Object[][] convertWordListToArray() {
		List<Word> WordList = model.getGame().getSelectedWord();
		Object[][] objAy = new Object[WordList.size()][3];
		for (int i = 0; i < objAy.length; i++) {

			Word w = WordList.get(i);
			objAy[i][0] = w.getContent();
			objAy[i][1] = w.getScore();
			objAy[i][2] = w.getWordSelectedTime();
			
			

		}
		return objAy;
	}

	/**
	 * Gets the chosenbtns.
	 *
	 * @return the chosenbtns
	 */
	public List<JButton> getChosenbtns() {
		return this.chosenbtns;
	}

	/**
	 * Sets the chosenbtns.
	 *
	 * @param btn the new chosenbtns
	 */
	public void setChosenbtns(JButton btn) {
		chosenbtns.add(btn);
	}

	/**
	 * Setall cellsbtns.
	 */
	public void setallCellsbtns() {
		for (int i = 0; i < 4; i++) {
			for (int j = 0; j < 4; j++) {
				int x = 25 + j * 100;
				int y = 40 + i * 35;
				JButton btn = new JButton();
				btn.setBounds(x, y, 89, 23);
				btn.setToolTipText(model.getBoard().cells[i][j].getPoints());
				allCellsbtns.add(btn);
				ClickButton_multiGame clickControl = new ClickButton_multiGame(this);
				btn.addActionListener(clickControl);
				btn.setBackground(Color.WHITE);
				panel.add(btn);

			}
		}
	}
/** The place to store the different score of each letter.
 */
	public void refreshBoard() {
		String letter = "ABCDEFGHIJKLMNOPRSTUVWXYZQ";
		String[] points = {"2","4","3","3","1","4","4","2","2","7","5","3","3","2","2","4","2","2","1","3","5","3","7","4","8","11"};
		char[] LettersToBeAdd = this.model.getBoard().getBoardInfo().toCharArray();	
		if (this.model.getBoard().getBoardInfo().length()!=0){			
		for (int i = 0; i < 16; i++) {
			String lettToBeAdd = String.valueOf(LettersToBeAdd[i]);
			this.allCellsbtns.get(i).setToolTipText(points[letter.indexOf(lettToBeAdd)]);
			if (lettToBeAdd.equals("Q")) {
				lettToBeAdd = "Qu";
			}
			this.allCellsbtns.get(i).setText(lettToBeAdd);
			
			
		}
			clearAllChosen();
			resetInfo();
			panel.repaint();
			for (int i = 0; i < 16; i++) {
				this.allCellsbtns.get(i)
						.setBackground(new Color(255 - (model.getBoard().getOverlapTimes()[i]) * 15,
								255 - (model.getBoard().getOverlapTimes()[i]) * 15,
								255 - (model.getBoard().getOverlapTimes()[i]) * 15));
			}
			this.textField_playerNum.setText(String.valueOf(model.getGame().getPlayersListByScore().size()));
		}
		
		setWordTable();
		setScoreTable();

	}
/** Initialize the color of the button.*/
	private void removeCellBtnsColors() {
		for (int i = 0; i < 16; i++) {
			JButton btn = this.allCellsbtns.get(i);
			btn.setBackground(new Color(255 - (model.getBoard().getOverlapTimes()[i] ) *15,
					255 - (model.getBoard().getOverlapTimes()[i] ) *15,
					255 - (model.getBoard().getOverlapTimes()[i] ) *15));
		}
		

		panel.repaint();
	}
/** Clear all chosen button.*/
	private void clearAllChosen() {
		textField_word.setText("");
		textField_escore.setText("0");
		message.setText("");
		this.chosenbtns.removeAll(chosenbtns);
		model.getBoard().clearChosenCells();
		removeCellBtnsColors();
		for (JButton btn : allCellsbtns) {
			btn.setEnabled(true);
		}
		this.wordScoreSum=0;
		

	}
/** Reset the information in the game board.*/
	private void resetInfo() {
		textField_name.setText(model.getPlayer().getName());
		textField_gameId.setText(model.getGame().getGameId());
		textField_score.setText(String.valueOf(model.getPlayer().getScore()));
		if (model.getGame().getManagingUser().equals(model.getPlayer().getName())) {
			textField_manager.setText("Manager is You");
		} else {
			textField_manager.setText(model.getGame().getManagingUser());
		}
		if (model.getGame().getManagingUser().equals(model.getPlayer().getName())) {
			btnLock.setEnabled(true);
			button_reset.setEnabled(true);
			if (model.getGame().isLocked()) {
				btnLock.setEnabled(false);
			}

		} else {
			btnLock.setEnabled(false);
			button_reset.setEnabled(false);
		}
		message.setText("");
	}

/**
 *  Identify if the cell is bonus or not.
 *
 * @param index the index
 * @return true, if is bonus cell
 */
	public boolean isBonusCell(int index) {
		String bonusString = model.getBoard().getBonusCell();
		String[] bonuscoordinate = bonusString.split(",");
		int x = Integer.parseInt(bonuscoordinate[0]);
		int y = Integer.parseInt(bonuscoordinate[1]);
		int globalStartingCol = model.getBoard().getGlobalStartingCol();
		int globalStartingRow = model.getBoard().getGlobalStartingRow();
		int deltCol = x - globalStartingCol;
		int deltRow = y - globalStartingRow;	
		if (deltRow >= 0 && deltRow <=3 && deltCol >= 0 && deltCol <=3) {
			System.out.println(deltRow * 4 + deltCol);
			if (index == deltRow * 4 + deltCol) {
				return true;
			}

		}
		return false;

	}
}
