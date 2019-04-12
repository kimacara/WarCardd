package com.svi.training.warcard;

public enum Suit 
{

	DIAMONDS(3, "D"),  
	HEARTS(2, "H"), 
	SPADES(1, "S"), 
	CLUBS(0, "C") ; 

	private int suitValue; 
	private String suitText;

	private Suit(int suitValue, String suitText) 
	{ 
		this.suitValue = suitValue; 
		this.suitText = suitText;
	} 
	
	public String getSuitText() 
	{
		return suitText;
	}
	
	public int getSuitValue() 
	{ 
		return suitValue; 
	} 
	
}
