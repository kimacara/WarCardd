package com.svi.training.warcard;

import java.util.*;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

public class Deck
{
	
	//initial deck
	public static Suit findSuit(String suitText) 
	{
		for (Suit suit : Suit.values()) 
		{
			if (suit.getSuitText().trim().equals(suitText)) 
			{
				return suit;
			}
		}

		return null;
	}

	public static Rank findRank(String rankText) 
	{
		for (Rank rank : Rank.values()) 
		{
			if (rank.getRankText().trim().equals(rankText))
			{
				return rank;
			}
		}

		return null;
	}
	
	public static LinkedList<Card> loadDeckFromFile(String path) throws IOException, IllegalArgumentException 
	{
		String contents = new String(Files.readAllBytes(Paths.get(path)), StandardCharsets.UTF_8);
		String[] cards = contents.split(",");

		LinkedList<Card> initialDeck = new LinkedList<>();

		for (String card : cards)
		{
			String[] pair = card.split("-");
			Suit suit = findSuit(pair[1].trim());
			Rank rank = findRank(pair[0].trim());

			if (suit != null && rank != null)
			{
				initialDeck.add(new Card(rank.getRankText(), suit.getSuitText(), rank.getRankValue(), suit.getSuitValue()));
			}
		}

		return initialDeck;
	}
	
	
	//shuffled deck
	public static LinkedList<Card> faroshuffleDeck(int numberOfShuffles, LinkedList<Card> deck) 
	{
		LinkedList<Card> shuffledDeck = new LinkedList<>();
		LinkedList<Card> actingDeck = new LinkedList<>();

		for (int i = 0; i < numberOfShuffles; i++) 
		{
			if (shuffledDeck.isEmpty()) 
			{
				actingDeck = deck;
			} 
			else
			{
				actingDeck = shuffledDeck;
			}
			shuffledDeck = new LinkedList<Card>();
			for (int j = 0; j < deck.size() / 2; j++) 
			{
				shuffledDeck.add(actingDeck.get(j));
				shuffledDeck.add(actingDeck.get(j + (deck.size()/2)));
			}
		}
		return shuffledDeck;
	
	}
}