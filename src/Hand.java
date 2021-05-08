import java.util.ArrayList;
import java.util.List;

public class Hand {
	private String name;
	private List<Card> hand;
	private Rank rank;
	private int value;
	private int numberOfCardsWithTheSameValue;
	
	public Hand() {
		hand = new ArrayList<Card>();
		rank = new Rank();
		value = 0;
	}
	
	public List<Card> GetHand() {
		return hand;
	}
	
	public String GetName() {
		return name;
	}
	
	public Card GetCard(int index) {
		return hand.get(index);
	}
	
	public void Rename(String n) {
		name = n;
	}
	
	public void AddCard(String valueParam, String suitParam) {
		hand.add(new Card(valueParam, suitParam));
	}
	
	public Rank GetRank() {
		return rank;
	}
	
	public void SetRank(Rank rankParam) {
		rank = rankParam;
	}
	
	public int GetValue() {
		return value;
	}
	
	public void SetValue(int valueParam) {
		value = valueParam;
	}
	
	public int GetNumberOfSameValues() {
		return numberOfCardsWithTheSameValue;
	}
	
	public void EvaluateHand() {
		int count = 0;
		int highest = HighestCard();
		boolean hasPair = false;
		
		numberOfCardsWithTheSameValue = 0;
		value = 0;
		
		for (int index = 0; index < hand.size(); index++) {
			count = 1;
			for (int comparison = (index + 1); comparison < hand.size(); comparison++) {
				if (hand.get(index).GetValue() == hand.get(comparison).GetValue()) {
					count++;
					value += hand.get(index).GetValue();
					hasPair = true;
				}
			}
			
			if (numberOfCardsWithTheSameValue < count) {
				numberOfCardsWithTheSameValue = count;
			}
			
			if (!hasPair) {
				break;
			}
		}
		
		if (highest > value) {
			value = highest;
		}
		
		if (StraightFlush()) {
			rank.SetName(RankName.StraightFlush);
		} else if (numberOfCardsWithTheSameValue == 4) {
			rank.SetName(RankName.FourOfAkind);
		} else if (numberOfCardsWithTheSameValue == 3 && Pair()) {
			rank.SetName(RankName.FullHouse);
		} else if (numberOfCardsWithTheSameValue == 5) {
			rank.SetName(RankName.Flush);
			HighCard();
		} else if (Straight()) {
			rank.SetName(RankName.Straight);
		} else if (ThreeOfAKind()) {
			rank.SetName(RankName.ThreeOfAKind);
		} else if (TwoPairs()) {
			rank.SetName(RankName.TwoPairs);
		} else {
			rank.SetName(RankName.HighCard);
			HighCard();
		}
	}
	
	private boolean StraightFlush() {
		Suit suit = hand.get(0).GetSuit();
		int previousValue = hand.get(0).GetValue();
		
		// Check for all the same suit and consecutive values
		for (int index = 1; index < hand.size(); index++) {
			if (suit != hand.get(index).GetSuit()) {
				return false;
			} else if ((previousValue + 1) != hand.get(index).GetValue()) {
				return false;
			}
			
			previousValue = hand.get(index).GetValue();
		}
		
		return true;
	}
	
	private boolean Straight() {
		int previousValue = hand.get(0).GetValue();
		int highestCard = 0;
		
		// Check for all consecutive values
		for (int index = 1; index < hand.size(); index++) {
			if ((previousValue + 1) != hand.get(index).GetValue()) {
				return false;
			}
			
			if (highestCard < hand.get(index).GetValue()) {
				highestCard = hand.get(index).GetValue();
			}
			
			previousValue = hand.get(index).GetValue();
		}
		
		return false;
	}
	
	private boolean ThreeOfAKind() {
		int count = 0;
		int valueOfHand = 0;
		
		// Check for three of the same card
		for (Card card : hand) {
			for (int index = 1; index < hand.size(); index++) {
				if (hand.get(index).GetValue() == card.GetValue()) {
					count++;
					valueOfHand += card.GetValue();
				}
			}
			
			if (count == 3) {
				value = valueOfHand;
				return true;
			}
			
			count = 0;
		}
		
		return false;
	}

	private boolean TwoPairs() {
		int pairs = 0;
		
		for (int index = 0; index < hand.size(); index++) {
			for (int comparison = (index + 1); comparison < hand.size(); comparison++) {
				if (hand.get(index).GetValue() == hand.get(comparison).GetValue()) {
					pairs++;
					
					if (value < hand.get(index).GetValue()) {
						value = hand.get(index).GetValue();
					}
				}
			}
		}
		
		if (pairs == 2) {
			return true;
		}
		
		return false;	
	}
	
	private boolean Pair() {
		for (Card card : hand) {
			for (int index = 1; index < hand.size(); index++) {
				if (hand.get(index).GetValue() == card.GetValue()) {
					value = card.GetValue();
					return true;
				}
			}
		}
		
		return false;
	}
	
	private void HighCard() {
		int highestCard = 0;
		
		for (Card card : hand) {
			if (highestCard < card.GetValue()) {
				highestCard = card.GetValue();
			}
		}
		
		value = highestCard;
	}
	
	public int HighestCard() {
		int highest = 0;
		
		for (Card card : hand) {
			if (highest < card.GetValue()) {
				highest = card.GetValue();
			}
		}
		
		return highest;
	}
	
	public int NextHighestCard() {
		int highest = 0;
		
		for (Card card : hand) {
			if (highest < card.GetValue() && card.GetValue() < value) {
				highest = card.GetValue();
			}
		}
		
		value = highest;
		
		return highest;
	}
	
	public void PrintHand() {
		if (hand.isEmpty()) {
			System.out.println(name + " contains no cards");
		} else {
			for (Card card : hand) {
				System.out.println(card);
			}
		}
	}
}
