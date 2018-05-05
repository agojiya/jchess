package me.agojiya.jchess.board;

/**
 * A representation of locations on the chess board.
 *
 * @author agojiya
 */
public class Position {

    private static final int FILE_A_INT = (int) 'a';

    private char file;
    private int rank;

    private int file_int;

    /**
     * Constructor to initialize a position on the board.
     *
     * @param file the file of the position
     * @param rank the rank of the position
     */
    public Position(final char file, final int rank) {
        this.file = file;
        this.file_int = ((int) file) - FILE_A_INT;
        this.rank = rank;
    }

    /**
     * Provides the file of the position.
     *
     * @return an {@link Integer} representing the file
     */
    public int getFile() {
        return file_int;
    }

    /**
     * Provides the rank of the position.
     *
     * @return an {@link Integer} representing the rank
     */
    public int getRank() {
        return rank;
    }

    /**
     * Provides the file of the position.
     *
     * @return a {@link Character} representing the file
     */
    public int getFileChar() {
        return file;
    }

    /**
     * Provides a bitboard in which the only activated bit represents this position.
     *
     * @return a {@link Long} value representing the bitboard
     */
    public long toBitboard() {
        return (1L << getFile()) << (8 * (getRank() - 1));
    }

}
