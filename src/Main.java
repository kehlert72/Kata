import java.util.ArrayList;
import java.util.List;

public class Main {
	public static void main(String[] args) {
		List<Game> games = new ArrayList<Game>();
		
		games.add(new Game("Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C AH"));
		games.add(new Game("Black: 2H 4S 4C 2D 4H  White: 2S 8S AS QS 3S"));
		games.add(new Game("Black: 2H 3D 5S 9C KD  White: 2C 3H 4S 8C KH"));
		games.add(new Game("Black: 2H 3D 5S 9C KD  White: 2D 3H 5C 9S KH"));
		
		for(Game game : games) {
			game.CompareHand();
		}
	}
}
