package com.svi.training.warcard;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Pile 
{

	//dealing cards to players
	public static void dealCards (List<Player> playerList, LinkedList<Card> deck)
	{
		int index = 0;
		while (!deck.isEmpty())
		{
			playerList.get(index).getPlayerCards().addFirst(deck.removeFirst());
			if(index < playerList.size() - 1)
			{
				index++;
			} 
			else 
			{
				index = 0;
			}
		}
	}
	
	
	//getting top card of players
	public static List<Card> getTopCards(List<Player> playerList) 
	{
			List<Card> topCardList = new ArrayList<Card>();
		    playerList.forEach(player -> topCardList.add(player.getPlayerCards().removeFirst()));
		    System.out.print("\n");
			System.out.println("\nT O P  C A R D S : ");
			System.out.println(topCardList);
			return topCardList;
	}

	
	//comparing cards
	public static boolean compareCards(Card card1, Card card2) 
	{
		if (card1.getRankValue() == card2.getRankValue()) 
		{
			return (card1.getSuitValue()<card2.getSuitValue());
		} 
			return (card1.getRankValue() < card2.getRankValue());
	}

	
	
	//finding the highest card
	public static int highestCard(List<Card> topCardList) 
	{
		int highestCardIndex=0;
		for(int i = 1; i<topCardList.size(); i++) 
		{
				if (compareCards(topCardList.get(highestCardIndex),topCardList.get(i))) 
				{
					highestCardIndex=i;
				}
		}
			System.out.print("\n");
			System.out.println("H I G H E S T  C A R D : ");
			System.out.println(topCardList.get(highestCardIndex));
			return highestCardIndex;
	}
	
	
	
	public static void cardsToWinner(int highestCardIndex, List<Player> playerList, List<Card> topCardList)
	{
		Collections.rotate(topCardList, topCardList.size()-highestCardIndex);
		playerList.get(highestCardIndex).getPlayerCards().addAll(topCardList);
	}

}
	

