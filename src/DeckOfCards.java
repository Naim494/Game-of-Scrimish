import java.util.*;

public class DeckOfCards {
	private String name;
	private Random rand = new Random();
	private Random rand1 = new Random();
	private int randomPile = rand.nextInt(5);
	private int randomCard;
	Scanner in = new Scanner(System.in);
	private int c1Count;
	private int c2Count;
	private int c3Count;
	private int c4Count;
	private int c5Count;
	private int c6Count;
	private int c7Count;
	private int c8Count;
	private int c9Count;

	Card c1 = new Card("Dagger", '1');
	Card c2 = new Card("Sword", '2');
	Card c3 = new Card("Morning Star", '3');
	Card c4 = new Card("War Axe", '4');
	Card c5 = new Card("Halberd", '5');
	Card c6 = new Card("Longsword", '6');
	Card c7 = new Card("Archer", 'A');
	Card c8 = new Card("Shield", 'S');
	Card c9 = new Card("Crown", 'C');

	private ArrayList<Card> pile1 = new ArrayList<Card>(5);
	private ArrayList<Card> pile2 = new ArrayList<Card>(5);
	private ArrayList<Card> pile3 = new ArrayList<Card>(5);
	private ArrayList<Card> pile4 = new ArrayList<Card>(5);
	private ArrayList<Card> pile5 = new ArrayList<Card>(5);

	private ArrayList<Card> masterPile = generateRandomPile();
	private ArrayList<ArrayList<Card>> deck = new ArrayList<ArrayList<Card>>();

	DeckOfCards(String name) {
		this.name = name;
		deck.add(pile1);
		deck.add(pile2);
		deck.add(pile3);
		deck.add(pile4);
		deck.add(pile5);
		randomCrownPile();
	}
	
	DeckOfCards() {
		this.name = "Player1";
		deck.add(pile1);
		deck.add(pile2);
		deck.add(pile3);
		deck.add(pile4);
		deck.add(pile5);
	}
	
	
	public String toString(){
		return name;
	}

	public ArrayList<ArrayList<Card>> getDeck() {
		return deck;
	}

	public ArrayList<Card> getPile1() {
		return pile1;
	}

	public ArrayList<Card> getPile2() {
		return pile2;
	}

	public ArrayList<Card> getPile3() {
		return pile3;
	}

	public ArrayList<Card> getPile4() {
		return pile4;
	}

	public ArrayList<Card> getPile5() {
		return pile5;
	}

	public ArrayList<Card> getMasterPile() {
		return masterPile;
	}

	public void addCard(ArrayList<Card> pile, ArrayList<Card> mPile) {
		int x = 5;
		if (!(pile.isEmpty())) {
			while (x > 1) {
				if (masterPile.size() >= 1) {
					pile.add(masterPile.get(masterPile.size() - 1));
					x--;
					masterPile.remove(masterPile.size() - 1);
					masterPile.trimToSize();
				}
			}
		} else {
			while (x > 0) {
				if (masterPile.size() >= 1) {
					pile.add(masterPile.get(masterPile.size() - 1));
					x--;
					masterPile.remove(masterPile.size() - 1);
					masterPile.trimToSize();
				}
			}
		}
	}
	
	
	public void addCard2(ArrayList<Card> pile,String cards) {
		char[] list = cards.toCharArray();
		for(char x : list)
			if(Character.getNumericValue(x) == 1){
				pile.add(c1);
			}
			else if(Character.getNumericValue(x) == 2){
				pile.add(c2);
			}
			else if(Character.getNumericValue(x) == 3)
				pile.add(c3);
			else if(Character.getNumericValue(x) == 4)
				pile.add(c4);
			else if(Character.getNumericValue(x) == 5)
				pile.add(c5);
			else if(Character.getNumericValue(x) == 6)
				pile.add(c6);
			else if(Character.toUpperCase(x) == 'A')
				pile.add(c7);
			else if(Character.toUpperCase(x) == 'S')
				pile.add(c8);
			else if(Character.toUpperCase(x) == 'C')
				pile.add(c9);
	}

	public void randomCrownPile() {
		if (randomPile == 0)
			pile1.add(c9);
		else if (randomPile == 1)
			pile2.add(c9);
		else if (randomPile == 2)
			pile3.add(c9);
		else if (randomPile == 3)
			pile4.add(c9);
		else if (randomPile == 4)
			pile5.add(c9);
	}

	public ArrayList<Card> generateRandomPile() {
		int daggerCnt = 5;
		int swordCnt = 5;
		int morningCnt = 3;
		int axeCount = 3;
		int halberd = 2;
		int longSwordCnt = 2;
		int archerCnt = 2;
		int shieldCnt = 2;
		int x = 0;
		ArrayList<Card> pile = new ArrayList<Card>(24);

		while (x < 24) {
			for (int i = 0; i < 8; i++) {
				randomCard = rand1.nextInt(8);
				if (randomCard == 0 && daggerCnt > 0) {
					pile.add(c1);
					daggerCnt--;
					x++;
				} else if (randomCard == 1 && swordCnt > 0) {
					pile.add(c2);
					swordCnt--;
					x++;
				} else if (randomCard == 2 && morningCnt > 0) {
					pile.add(c3);
					morningCnt--;
					x++;
				} else if (randomCard == 3 && axeCount > 0) {
					pile.add(c4);
					axeCount--;
					x++;
				} else if (randomCard == 4 && halberd > 0) {
					pile.add(c5);
					halberd--;
					x++;
				} else if (randomCard == 5 && longSwordCnt > 0) {
					pile.add(c6);
					longSwordCnt--;
					x++;
				} else if (randomCard == 6 && archerCnt > 0) {
					pile.add(c7);
					archerCnt--;
					x++;
				} else if (randomCard == 7 && shieldCnt > 0) {
					pile.add(c8);
					shieldCnt--;
					x++;
				}
			}
		}

		return pile;
	}
}
