package Controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JFrame;

import Ui.PracticeGame;
import Ui.StartPage;

public class Exit implements ActionListener{
	PracticeGame p;
	public Exit(PracticeGame p){
		this.p=p;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		p.dispose();
		StartPage page = new StartPage();
		page.setSize(600, 300); // set StartPage size
		page.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		page.setVisible(true);
	}
}
