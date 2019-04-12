package com.svi.training.warcard;
import java.util.*;

import java.io.IOException;


public class WarCard 
{
	public static void main(String [] args)
	{
		int numberOfPlayers;
		int numberOfShuffles;
		boolean wrongInput = true;
		Scanner input = new Scanner(System.in);
		
		//START
		
		System.out.println("========================================================================");
		System.out.println("W E L C O M E  T O  T H E  W A R  C A R D  G A M E !");
		System.out.println("========================================================================");

		System.out.print("\n");
		
		//for the input of number of players
		System.out.println("Please enter the the number of players.");
		do 
		{
			while(!input.hasNextInt())
			{
				input.next();
				System.out.println("Input invalid. Enter the number of players: ");
			}
			numberOfPlayers = input.nextInt();

			if (numberOfPlayers>=2) 
			{
				wrongInput = false;
			} 
			else 
			{
	            System.out.println("Invalid. Number of Players must be greater than 1");
	           input.nextLine();
			}
		}
			while (wrongInput);
		
		//for the input of number of shuffles
		System.out.print("\n");
		System.out.println("And now, please enter your desired number of shuffles.");
		while(!input.hasNextInt())
		{
			input.next();
			System.out.println("Input invalid. Enter the number of shuffles: ");
		}
		numberOfShuffles = input.nextInt();
		input.close();
	
	
		//importing initial deck
		LinkedList<com.svi.training.warcard.Card> initialDeck;
		
		try 
		{
			initialDeck = Deck.loadDeckFromFile("input.txt");
		} 
		catch(IOException e) 
		{
			
			System.out.println("Blahhh.");
			initialDeck = new LinkedList<>();
			for (Suit suit : Suit.values()) 
			{
				for (Rank rank : Rank.values())
				{
					Card card = new Card(rank.getRankText(), suit.getSuitText(), rank.getRankValue(), suit.getSuitValue());
					initialDeck.add(card);
				}
			}
		}

		//printing the initial deck
		System.out.print("\n");
		System.out.println("INITIAL DECK: ");
		System.out.print("\n");
		System.out.println(initialDeck);
		
		//printing the shuffled deck
		LinkedList<Card> shuffledDeck = Deck.faroshuffleDeck(numberOfShuffles, initialDeck);
		System.out.print("\n");
		System.out.println("DECK AFTER THE SHUFFLE: ");
		System.out.print("\n");
		System.out.println(shuffledDeck);
	
	
		//Creating players
		//Dealing cards to players
		List<Player>  playerList = new ArrayList<>();
			for (int i = 0; i < numberOfPlayers; i++)
			{ 
				playerList.add(new Player("PLAYER "+(i+1) ));
			}
			Pile.dealCards(playerList, shuffledDeck);
	
		//Displaying cards of players
		//battle
		int roundNumber = 1;
		while(playerList.size()>1) 
		{
			System.out.print("\n");
			System.out.println("LIST OF PLAYER'S CARDS: ");
			System.out.print("\n");
			playerList.forEach(player -> System.out.println(player.getNameOfPlayer() + player.getPlayerCards()));
			playerList.removeIf(player -> player.getPlayerCards().isEmpty());
		
			System.out.println("=================================================================");
			System.out.print("R O U N D  "+roundNumber);
			List<Card> topCardList = Pile.getTopCards(playerList);
			int highestCardIndex = Pile.highestCard(topCardList);
			Pile.cardsToWinner(highestCardIndex, playerList, topCardList);
			System.out.print("\n");
			playerList.removeIf(player -> player.getPlayerCards().isEmpty());
			roundNumber++;
		}
		
		//END
			System.out.println("=================================================================");
			System.out.print("\n");
			System.out.printf("It took %d round/s. The %S wins.",roundNumber-1,playerList.get(0).getNameOfPlayer());
			playerList.forEach(
			player -> System.out.print("\nWinning Deck : " + player.getPlayerCards()));
			System.out.print("\n");
			System.out.printf("C O N G R A T U L A T I O N S");
		}
	}
