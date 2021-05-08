enum RankName {
	HighCard,
	Pair,
	TwoPairs,
	ThreeOfAKind,
	Straight,
	Flush,
	FullHouse,
	FourOfAkind,
	StraightFlush
}

public class Rank {
	private RankName name;
	private int value;
	
	public Rank() {
		name = RankName.HighCard;
		value = 0;
	}
	
	public RankName GetName() {
		return name;
	}
	
	public void SetName(RankName n) {
		name = n;
		
		if (name == RankName.StraightFlush) {
			value = 7;
		} else if (name == RankName.FourOfAkind) {
			value = 6;
		} else if (name == RankName.FullHouse) {
			value = 5;
		} else if (name == RankName.Flush) {
			value = 4;
		} else if (name == RankName.Straight) {
			value = 3;
		} else if (name == RankName.ThreeOfAKind) {
			value = 2;
		} else if (name == RankName.TwoPairs) {
			value = 1;
		} else if (name == RankName.HighCard) {
			value = 0;
		}
	}
	
	public int GetValue() {
		return value;
	}
}
