package me.agojiya.jchess.board;

import java.util.Arrays;

/**
 * A class representing the positions of the pieces of a complete chess board.
 *
 * @author agojiya
 */
public class Pieces {

    private long[] PIECE_BITBOARDS = Arrays.stream(Bitboards.values()).mapToLong(Bitboards::getBoard).toArray();

    /**
     * Constructor to initialize the pieces as the starting positions.
     */
    public Pieces() {
        return;
    }

    /**
     * Constructor to initialize the positions of each piece type individually.
     *
     * @param bitboards bitboard representations of each piece
     * @throws IllegalArgumentException the amount of bitboards provided does not correspond to the types of pieces
     */
    public Pieces(final long... bitboards) throws IllegalArgumentException {
        if (bitboards.length != 12) {
            throw new IllegalArgumentException("Amount of provided bitboards is not 12 (number of chess pieces)");
        }
        this.PIECE_BITBOARDS = bitboards;
    }

    /**
     * Generates pseudo-legal moves for any provided {@link Piece} type at any provided valid {@link Position}.
     *
     * @param targetPiece the piece type for which to find pseudo-legal moves
     * @param position the position for which to find pseudo-legal moves
     * @throws IllegalArgumentException the piece at the provided position is not equal to the provided piece type
     * @return a {@link Long} value representing a bitboard
     */
    public long getPseudoLegalMoves(final Piece targetPiece, final Position position) {
        if ((this.PIECE_BITBOARDS[getIndex(targetPiece)] & position.toBitboard()) == 0L) {
            throw new IllegalArgumentException("The provided piece type was not found at the provided position");
        }
        // TODO: Calculate pseudo-legal moves based on the Piece type if the piece exists at the provided position
        // TODO: Check for legality
        return 0L;
    }

    /**
     * Provides the index of the provided {@link Piece} type in the bitboard array representing each piece.
     *
     * @see Pieces#PIECE_BITBOARDS
     * @param targetPiece the piece for which the index should be calculated
     * @return an {@link Integer} value representing the index
     */
    private int getIndex(final Piece targetPiece) {
        return Arrays.asList(Piece.values()).indexOf(targetPiece);
    }

    /**
     * Provides the representation of all pieces in the current setup.
     *
     * @return a {@link Long} value representing a bitboard
     */
    private long getAll() {
        return this.PIECE_BITBOARDS[0] & this.PIECE_BITBOARDS[1] & this.PIECE_BITBOARDS[2] & this.PIECE_BITBOARDS[3]
                & this.PIECE_BITBOARDS[4] & this.PIECE_BITBOARDS[5] & this.PIECE_BITBOARDS[6] & this.PIECE_BITBOARDS[7]
                & this.PIECE_BITBOARDS[8] & this.PIECE_BITBOARDS[9] & this.PIECE_BITBOARDS[10]
                & this.PIECE_BITBOARDS[11];
    }

}
