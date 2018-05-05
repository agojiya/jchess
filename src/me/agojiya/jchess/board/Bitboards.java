package me.agojiya.jchess.board;

/**
 * Starting bitboard representations of all of the types of chess pieces.
 *
 * @author agojiya
 */
public enum Bitboards {

    WHITE_PAWNS     (0b0000000000000000000000000000000000000000000000001111111100000000L),
    BLACK_PAWNS     (0b0000000011111111000000000000000000000000000000000000000000000000L),
    WHITE_ROOKS     (0b0000000000000000000000000000000000000000000000000000000010000001L),
    BLACK_ROOKS     (0b1000000100000000000000000000000000000000000000000000000000000000L),
    WHITE_KNIGHTS   (0b0000000000000000000000000000000000000000000000000000000001000010L),
    BLACK_KNIGHTS   (0b0100001000000000000000000000000000000000000000000000000000000000L),
    WHITE_BISHOPS   (0b0000000000000000000000000000000000000000000000000000000000100100L),
    BLACK_BISHOPS   (0b0010010000000000000000000000000000000000000000000000000000000000L),
    WHITE_QUEEN     (0b0000000000000000000000000000000000000000000000000000000000001000L),
    BLACK_QUEEN     (0b0000100000000000000000000000000000000000000000000000000000000000L),
    WHITE_KING      (0b0000000000000000000000000000000000000000000000000000000000010000L),
    BLACK_KING      (0b0001000000000000000000000000000000000000000000000000000000000000L);

    private long board;

    Bitboards(final long board) {
        this.board = board;
    }

    /**
     * Provides the starting board position.
     *
     * @return a {@link Long} value representing a bitboard
     */
    public long getBoard() {
        return this.board;
    }

    /**
     * Formats the provided long variable that represents a bitboard into a board that is easier to visualize and
     * provides the result as a string.
     *
     * @param board a representation of a bitboard
     * @return a {@link String} value representing the formatted bitboard
     */
    public static String toString(final long board) {
        final String boardStr = String.format("%64s", Long.toBinaryString(board)).replace(' ', '0')
                .replace('0', '-');
        final StringBuilder result = new StringBuilder();
        for (short i = 0; i < 8; i++) {
            final StringBuilder line = new StringBuilder(boardStr.substring(i * 8, (i + 1) * 8));
            result.append(line.reverse().toString().replace("", " "));
            result.append('\n');
        }
        return result.toString();
    }

}
