package me.agojiya.jchess.board;

/**
 * A representation of locations on the chess board.
 *
 * @author agojiya
 */
public class Position {

    private static final int FILE_A_INT = (int) 'a';

    private int rank;
    private char file;

    private int file_int;

    /**
     * Constructor to initialize a position on the board.
     *
     * @param rank the rank of the position
     * @param file the file of the position
     */
    public Position(final int rank, final char file) {
        this.rank = rank;
        this.file = file;
        this.file_int = ((int) file) - FILE_A_INT;
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
     * @return an {@link Integer} representing the file
     */
    public int getFile() {
        return file_int;
    }

    /**
     * Provides the file of the position.
     *
     * @return a {@link Character} representing the file
     */
    public int getFileChar() {
        return file;
    }

}
