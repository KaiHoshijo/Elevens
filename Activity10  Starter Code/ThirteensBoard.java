import java.util.List;
import java.util.ArrayList;

public class ThirteensBoard extends Board{

    /**
     * The size (number of cards) on the board.
     */
    private static final int BOARD_SIZE = 10;

    /**
     * The ranks of the cards for this game to be sent to the deck.
     */
    private static final String[] RANKS =
            {"ace", "2", "3", "4", "5", "6", "7", "8", "9", "10", "jack", "queen", "king"};

    /**
     * The suits of the cards for this game to be sent to the deck.
     */
    private static final String[] SUITS =
            {"spades", "hearts", "diamonds", "clubs"};

    /**
     * The values of the cards for this game to be sent to the deck.
     */
    private static final int[] POINT_VALUES =
            {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13};

    /**
     * Flag used to control debugging print statements.
     */
    private static final boolean I_AM_DEBUGGING = false;


    /**
     * Creates a new <code>ThirteensBoard</code> instance.
     */
    public ThirteensBoard() {
        super(BOARD_SIZE, RANKS, SUITS, POINT_VALUES);
    }

    /**
     * Determines if the selected cards form a valid group for removal.
     * In Elevens, the legal groups are (1) a pair of non-face cards
     * whose values add to 11, and (2) a group of three cards consisting of
     * a jack, a queen, and a king in some order.
     * @param selectedCards the list of the indices of the selected cards.
     * @return true if the selected cards form a valid group for removal;
     *         false otherwise.
     */
    @Override
    public boolean isLegal(List<Integer> selectedCards) {
        if (selectedCards.size() == 2) {
            return containsPairSum11(selectedCards);
        } else if (selectedCards.size() == 1) {
            return containsK(selectedCards);
        } else {
            return false;
        }
    }

    /**
     * Determine if there are any legal plays left on the board.
     * In Elevens, there is a legal play if the board contains
     * (1) a pair of non-face cards whose values add to 11, or (2) a group
     * of three cards consisting of a jack, a queen, and a king in some order.
     * @return true if there is a legal play left on the board;
     *         false otherwise.
     */
    @Override
    public boolean anotherPlayIsPossible() {
        List<Integer> cIndexes = cardIndexes();
        return containsPairSum11(cIndexes) || containsK(cIndexes);
    }

    /**
     * Check for an 11-pair in the selected cards.
     * @param selectedCards selects a subset of this board.  It is list
     *                      of indexes into this board that are searched
     *                      to find an 13-pair.
     * @return true if the board entries in selectedCards
     *              contain an 13-pair; false otherwise.
     */
    private boolean containsPairSum11(List<Integer> selectedCards) {
        for (int sk1 = 0; sk1 < selectedCards.size(); sk1++) {
            int k1 = selectedCards.get(sk1).intValue();
            for (int sk2 = sk1 + 1; sk2 < selectedCards.size(); sk2++) {
                int k2 = selectedCards.get(sk2).intValue();
                if (cardAt(k1).pointValue() + cardAt(k2).pointValue() == 13) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Check for a K in the selected cards.
     * @param selectedCards selects a subset of this board.  It is list
     *                      of indexes into this board that are searched
     *                      to find a king.
     * @return true if the board entries in selectedCards
     *              include a king; false otherwise.
     */
    private boolean containsK(List<Integer> selectedCards) {
        boolean foundKing = false;
        for (Integer kObj : selectedCards) {
            int k = kObj.intValue();
            if (cardAt(k).rank().equals("king")) {
                foundKing = true;
            }
        }
        return foundKing;
    }
}
