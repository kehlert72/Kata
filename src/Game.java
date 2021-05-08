public class Game {
	private Hand playerOne;
	private Hand playerTwo;
	
	public Game() {
		playerOne = new Hand();
		playerTwo = new Hand();
	}
	
	public Game(String input) {
		playerOne = new Hand();
		playerTwo = new Hand();
		
		String input1 = input.substring(0, input.length() / 2);
		String input2 = input.substring(input.length() / 2, input.length());
		
		for(String card : input1.split("\\s+")) {
			if (card.compareTo("White:") == 0) {
				playerOne.Rename("White");
			} else if (card.compareTo("Black:") == 0) {
				playerOne.Rename("Black");
			} else if (card.compareTo("") != 0 && card != null) {
				playerOne.AddCard(card.substring(0, 1), card.substring(1));
			}
		}
		
		for(String card : input2.split("\\s+")) {
			if (card.compareTo("White:") == 0) {
				playerTwo.Rename("White");
			} else if (card.compareTo("Black:") == 0) {
				playerTwo.Rename("Black");
			} else if (card.compareTo("") != 0 && card != null) {
				playerTwo.AddCard(card.substring(0, 1), card.substring(1));
			}
		}
	}
	
	public void CompareHand() {
		if (playerOne.GetHand().size() < 1 || playerTwo.GetHand().size() < 1 ) {
			return;
		}
		
		// Evaluate each hand
		playerOne.EvaluateHand();
		playerTwo.EvaluateHand();
		
		// Compare the players hand
		if (playerOne.GetRank().GetValue() > playerTwo.GetRank().GetValue()) {
			if (playerOne.GetRank().GetName() == RankName.FullHouse) {
				Win(playerOne.GetName(), playerOne.GetRank(), playerOne.HighestCard(), playerOne.GetValue());
			} else {
				Win(playerTwo.GetName(), playerTwo.GetRank(), playerTwo.GetValue());
			}
		} else if (playerOne.GetRank().GetValue() < playerTwo.GetRank().GetValue()) {
			if (playerTwo.GetRank().GetName() == RankName.FullHouse) {
				Win(playerTwo.GetName(), playerTwo.GetRank(), playerTwo.HighestCard(), playerTwo.GetValue());
			} else {
				Win(playerTwo.GetName(), playerTwo.GetRank(), playerTwo.GetValue());
			}
		} else if (playerOne.GetRank().GetValue() == playerTwo.GetRank().GetValue()) {		
			for (int index = 1; index < 5; index++) {
				if (playerOne.NextHighestCard() > playerTwo.NextHighestCard()) {
					Win(playerOne.GetName(), playerOne.GetRank(), playerOne.GetValue());
					return;
				} else if (playerOne.NextHighestCard() < playerTwo.NextHighestCard()) {
					Win(playerTwo.GetName(), playerTwo.GetRank(), playerTwo.GetValue());
					return;
				}
			}
			
			System.out.println("Tie");
		} else {
			System.out.println("ERROR: NO WINNER!!!");
		}
	}
	
	public void Win(String name, Rank rank, int numerator, int denominator) {
		System.out.println(name + " wins. - with " + rank.GetName() + ": " + numerator + " over " + denominator);
	}
	
	public void Win(String name, Rank rank, Integer highestCard) {
		String cardValue = "";

		if (highestCard == 11) {
			cardValue = "Jack";
		} else if (highestCard == 12) {
			cardValue = "Queen";
		} else if (highestCard == 13) {
			cardValue = "King";
		} else if (highestCard == 14) {
			cardValue = "Ace";
		} else {
			cardValue = highestCard.toString();
		}
		
		System.out.println(name + " wins. - with " + rank.GetName() + ": " + cardValue);
	}
}
