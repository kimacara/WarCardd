package com.svi.training.warcard;
import java.util.*;

public class Player 
{

	private String nameOfPlayer;
	private LinkedList<Card> playerCards;
	
	public Player (String nameOfPlayer)
	{
		this.nameOfPlayer = nameOfPlayer;
		playerCards = new LinkedList<Card>();
	}

	public String getNameOfPlayer() 
	{
		return nameOfPlayer;
	}

	public LinkedList<Card> getPlayerCards()
	{
		return playerCards;
	}

}
