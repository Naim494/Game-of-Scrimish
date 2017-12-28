import java.util.*;

public class Scrimish {

	public static void main(String[] args) {
		DeckOfCards player1 = new DeckOfCards("Player1");
		DeckOfCards computer = new DeckOfCards("Computer");
		Scanner in = new Scanner(System.in);
		String cards;
		System.out.printf("%65s", "***GAME OF SCRIMISH***");
		System.out.println();
		System.out.println("------------------------------------------------------------------------------------------------------------------");
		System.out.println("Enter 1 to create your deck OR 0 to generate deck: ");
		int choice = in.nextInt();
		if(choice == 1){
			player1 = new DeckOfCards();
			System.out.print("(S : Strength) \n"
					+ "S-1 Dagger Cards (x5 per player) \n"
					+ "S-2 Sword Cards (x5 per player) \n"
					+ "S-3 Morning Star Cards (x3 per player)\n"
					+ "S-4 War Axe Cards (x3 per player)\n"
					+ "S-5 Halberd Cards (x2 per player)\n"
					+ "S-6 Longsword Cards (x2 per player)\n"
					+ "S-A Archer Cards (x2 per player)\n"
					+ "S-S Shield Cards (x2 per player)\n"
					+ "S-C Crown Card (x1 per player)");
			System.out.println();
			
			System.out.println("Enter 5 piles using card strenghts(ex. A23S1): ");
			in.nextLine();
			cards = in.nextLine();
			player1.addCard2(player1.getPile1(), cards);
			cards = in.nextLine();
			player1.addCard2(player1.getPile2(), cards);
			cards = in.nextLine();
			player1.addCard2(player1.getPile3(), cards);
			cards = in.nextLine();
			player1.addCard2(player1.getPile4(), cards);
			cards = in.nextLine();
			player1.addCard2(player1.getPile5(), cards);
		}
		else{
			player1.addCard(player1.getPile1(), player1.getMasterPile());
			player1.addCard(player1.getPile2(), player1.getMasterPile());
			player1.addCard(player1.getPile3(), player1.getMasterPile());
			player1.addCard(player1.getPile4(), player1.getMasterPile());
			player1.addCard(player1.getPile5(), player1.getMasterPile());
		}
		computer.addCard(computer.getPile1(), computer.getMasterPile());
		computer.addCard(computer.getPile2(), computer.getMasterPile());
		computer.addCard(computer.getPile3(), computer.getMasterPile());
		computer.addCard(computer.getPile4(), computer.getMasterPile());
		computer.addCard(computer.getPile5(), computer.getMasterPile());
		
		displayDeck(player1);
		displayDeck(computer);

		boolean gameOn = true;
		Card attackCard = null;
		Card defCard = null;;
		int pileCard = 0;
		int pileCard2 = 0;
		Random rand = new Random(2);
		int round = 1;

		while (gameOn) {
			System.out.println("* ROUND " + round + " *");
			System.out.println("------------------------------------------------------------------------------------------------------------ ");
			System.out.println("(User turn)");
			System.out.println("Enter a pile number(1 - 5) to select a card from one pile to attack OR"
					+ " enter 0 to discard a card: ");
			pileCard = in.nextInt();
			if (pileCard == 0) {
				System.out.println("Enter pile to discard from: ");
				int x = in.nextInt();
				discardCard(player1, x);
			} 
			else {
				attackCard = getCard(player1, pileCard);
				System.out.println("Selected card: " + attackCard.toString());
				if (attackCard.getStrength() == 'S') {
					System.out.println("Shiled cannot attack; select another pile: ");
					pileCard = in.nextInt();
					attackCard = getCard(player1, pileCard);
					System.out.println("Selected card: " + attackCard.toString());
				}

				System.out.println("Select opponest's pile to attack: ");
				pileCard2 = in.nextInt();
				defCard = getCard(computer, pileCard2);
				System.out.println("Attacked card: " + defCard.toString());
				System.out.println();

				if (attackCard.getStrength() == 'C') {
					if (defCard.getStrength() == 'C') {
						// discardCard(computer, pileCard);
						System.out.println("You Win!");
						gameOn = false;
					} else {
						// discardCard(player1, pileCard);
						System.out.println("You Loose :(");
						gameOn = false;
					}
				} else {
					if (!(attackPlay(attackCard, defCard, player1, computer, pileCard, pileCard2, true)))
						gameOn = false;
				}
			}
			if(!(gameOn))
				continue;
			
			displayDeck(player1);
			displayDeck(computer);
			System.out.println("------------------------------------------------------------------------------------------------------------ ");
			System.out.println("(PC turn)");
			pileCard = rand.nextInt(6);
			
			if (pileCard == 0) {
				//System.out.println("Enter pile to discard from: ");
				int x = rand.nextInt(5) + 1;
				discardCard(computer, x);
			} 
			else {
				attackCard = selectCardPC(pileCard, computer, rand);
				System.out.println("Selected card: " + attackCard.toString());

				//System.out.println("Select opponest's pile to attack: ");
				pileCard2 = rand.nextInt(5) + 1;
				defCard = getCard(player1, pileCard2);
				System.out.println("Attacked card: " + defCard.toString());
				System.out.println();

				if (attackCard.getStrength() == 'C') {
					if (defCard.getStrength() == 'C') {
						// discardCard(computer, pileCard);
						System.out.println("You Loose :(");
						gameOn = false;
					} else {
						// discardCard(player1, pileCard);
						System.out.println("You Win!");
						gameOn = false;
					}
				} else {
					if (!(attackPlay(attackCard, defCard, computer, player1, pileCard, pileCard2, true)))
						gameOn = false;
				}
			}
			System.out.println("At the end of Round " + round + ":");
			System.out.println("------------------------------------------------------------------------------------------------------------ ");
			displayDeck(player1);
			displayDeck(computer);
			System.out.println();
			round++;
		}
		
		in.close();
	}
	
	public static Card selectCardPC(int pile, DeckOfCards PC, Random rand){
		Card attackCard = getCard(PC, pile);
		if(!(attackCard.getStrength() == 'S'))
			return attackCard;
		else{
			return selectCardPC(rand.nextInt(5) + 1, PC, rand);
		}
	}

	public static boolean attackPlay(Card attackCard, Card defCard, DeckOfCards p1, DeckOfCards p2, int p1pile,
			int p2pile, boolean flag) {
		if (Character.isDigit(attackCard.getStrength()) && Character.isDigit(defCard.getStrength())) {
			if (Character.getNumericValue(attackCard.getStrength()) > Character
					.getNumericValue(defCard.getStrength())) {
				discardCard(p2, p2pile);
				return flag;
			} else if (Character.getNumericValue(attackCard.getStrength()) < Character
					.getNumericValue(defCard.getStrength())) {
				discardCard(p1, p1pile);
				return flag;
			} else if (Character.getNumericValue(attackCard.getStrength()) == Character
					.getNumericValue(defCard.getStrength())) {
				discardCard(p1, p1pile);
				discardCard(p2, p2pile);
				return flag;
			}
		} else if (attackCard.getStrength() == 'A' || defCard.getStrength() == 'A') {
			if (!(defCard.getStrength() == 'S'))
				discardCard(p2, p2pile);
			return flag;
		} else if (defCard.getStrength() == 'S') {
			discardCard(p1, p1pile);
			discardCard(p2, p2pile);
			return flag;
		} else if (defCard.getStrength() == 'C') {
			System.out.println(p1 + " wins!");
			flag = false;
			return flag;
		}
		return flag;
	}

	public static Card getCard(DeckOfCards playerX, int pileNum) {
		int x = pileNum;
		if (x == 1)
			return playerX.getPile1().get(playerX.getPile1().size() - 1);
		else if (x == 2)
			return playerX.getPile2().get(playerX.getPile2().size() - 1);
		else if (x == 3)
			return playerX.getPile3().get(playerX.getPile3().size() - 1);
		else if (x == 4)
			return playerX.getPile4().get(playerX.getPile4().size() - 1);
		else if (x == 5)
			return playerX.getPile5().get(playerX.getPile5().size() - 1);
		return null;

	}

	public static void discardCard(DeckOfCards playerX, int pileNum) {
		int x = pileNum;
		if (x == 1) {
			playerX.getPile1().remove(playerX.getPile1().size() - 1);
			playerX.getPile1().trimToSize();
		} else if (x == 2) {
			playerX.getPile2().remove(playerX.getPile2().size() - 1);
			playerX.getPile2().trimToSize();
		} else if (x == 3) {
			playerX.getPile3().remove(playerX.getPile3().size() - 1);
			playerX.getPile3().trimToSize();
		} else if (x == 4) {
			playerX.getPile4().remove(playerX.getPile4().size() - 1);
			playerX.getPile4().trimToSize();
		} else if (x == 5) {
			playerX.getPile5().remove(playerX.getPile5().size() - 1);
			playerX.getPile5().trimToSize();
		}
	}
	
	public static void displayDeck(DeckOfCards player){
		System.out.println(player.toString() + " Deck: ");
		System.out.println("----------------------------");
		for (ArrayList<Card> pile : player.getDeck()) {
			for (Card x : pile) {
				System.out.printf("%-18s", x.toString());
			}
			System.out.println();
		}
		System.out.println();
		
	}

}
