package com.capricorn.listener;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;


import com.capricorn.model.Model;
import com.capricorn.view.PracticeGame;
import com.capricorn.view.Application;
// TODO: Auto-generated Javadoc
/**
 * This is the enter to the practice game.
 * If the client want to be familiar with the game before the true competition.
 * Here is a good palce for the player to have fun and practice.
 * This function do not need to connect to sever, so no request and response will generate
 * or receive.
 */
public class StartPractice implements ActionListener{
	
	/** The st */
	Application st;
	
	/** The m. */
	Model m;
	
	/**
	 *  Construct StartPractice object to use default port data.
	 *
	 * @param p the p
	 * @param m the m
	 */
	public StartPractice(Application p,Model m){
		this.st=p;
		this.m=m;
	}
	
	/**
	 * Dispose the default board at the beginning and connect to the game board.
	 *
	 * @param e the e
	 */
	@Override
	public void actionPerformed(ActionEvent e) {
		m.getBoard().setcellArrayForPractice();
		st.dispose();
	//startpage disappear
		PracticeGame practice = new PracticeGame(m);
		
		// initialized practice page
		practice.setSize(600, 300); // set practicegame size
		practice.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		practice.setVisible(true);
		}
	}