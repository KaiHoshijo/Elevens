public class ThirteensBoardGUI {
    /**
     * Plays the GUI version of Elevens.
     * @param args is not used.
     */
    public static void main(String[] args) {
        Board board = new ThirteensBoard();
        CardGameGUI gui = new CardGameGUI(board);
        gui.displayGame();
    }
}