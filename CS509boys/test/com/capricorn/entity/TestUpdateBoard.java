package com.capricorn.entity;

import java.util.ArrayList;
/**
 *Test for all elements in entity
 */
import java.util.List;

import junit.framework.TestCase;

public class TestUpdateBoard extends TestCase {
	
	Board board = new Board();
	
	public void testupdateBoard(){

		board.setGlobalStartingCol(3);
		board.setGlobalStartingRow(4);
		
		String a = ("a,b,c,e,f,w,d,l,a,d,A,b,c,e,a,w");
		String b = "";
		board.updateBoard(board.getGlobalStartingCol(),board.getGlobalStartingRow(),a);
		
		assertTrue(board.getGlobalStartingCol()==3&&board.getGlobalStartingRow()==4);

		for(int i=0;i<board.cells.length;i++){
			for(int j=0;j<board.cells.length;j++){
				b += board.cells[i][j].getLetter();
				if(b.length() < 31){
					b += ",";
				}
			}
		}
		assertEquals(b,a);
		
	 
	}
}
