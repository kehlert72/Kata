enum Suit {
  Club,
  Diamond,
  Heart,
  Spade
}

public class Card {
	private int value;
	private Suit suit;
	
	public Card(String v, String s) {
		switch (v) {
		  case "A":
			value = 14;
			break;
		  case "K":
			value = 13;
			break;
		  case "Q":
			value = 12;
			break;
		  case "J":
			value = 11;
			break;
		  default:
			value = Integer.parseInt(v);
			break;
		}
		
		switch (s) {
		  case "C":
		    suit = Suit.Club;
		    break;
		  case "D":
			suit = Suit.Diamond;
		    break;
		  case "H":
			suit = Suit.Heart;
			break;
		  case "S":
			suit = Suit.Spade;
			break;
		}
	}
	
	public int GetValue() {
		return value;
	}
	
	public Suit GetSuit() {
		return suit;
	}
}
