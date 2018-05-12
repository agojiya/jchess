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
     * @param position    the position for which to find pseudo-legal moves
     * @return a {@link Long} value representing a bitboard
     * @throws IllegalArgumentException the piece at the provided position is not equal to the provided piece type
     */
    public long getPseudoLegalMoves(final Piece targetPiece, final Position position) {
        // TODO: Moves that involve taking over a piece
        final long pieceBitboard = this.PIECE_BITBOARDS[getIndex(targetPiece)] & position.toBitboard();
        if (pieceBitboard == 0L) {
            throw new IllegalArgumentException("The provided piece type was not found at the provided position");
        }
        if (targetPiece == Piece.BLACK_PAWN || targetPiece == Piece.WHITE_PAWN) {
            final long SINGLE_STEP = targetPiece == Piece.WHITE_PAWN ? pieceBitboard << 8 : pieceBitboard >>> 8,
                    DOUBLE_STEP = targetPiece == Piece.WHITE_PAWN ? pieceBitboard << 16 : pieceBitboard >>> 16;
            long result = SINGLE_STEP;
            if (position.getRank() == 2 || position.getRank() == 7) {
                result ^= DOUBLE_STEP;
            }
            final long intersections = getAll() & result;
            result ^= intersections;
            if ((result & SINGLE_STEP) == 0L) {
                result = 0L;
            }
            return result;
        } else if (targetPiece == Piece.BLACK_KNIGHT || targetPiece == Piece.WHITE_KNIGHT) {
            final long NW_SHORT = pieceBitboard << (8 - 2), NW_LONG = pieceBitboard << (8 + 7),
                    NE_SHORT = pieceBitboard << (8 + 2), NE_LONG = pieceBitboard << (8 + 9),
                    SW_SHORT = pieceBitboard >>> (8 + 2), SW_LONG = pieceBitboard >>> (8 + 9),
                    SE_SHORT = pieceBitboard >>> (8 - 2), SE_LONG = pieceBitboard >>> (8 + 7);
            final int inlineIndex = Long.numberOfTrailingZeros(pieceBitboard) % 8;
            long result = (inlineIndex >= 2 ? NW_SHORT ^ SW_SHORT : 0L) ^ (inlineIndex >= 1 ? NW_LONG ^ SW_LONG : 0L) ^
                    (inlineIndex <= 5 ? NE_SHORT ^ SE_SHORT : 0L) ^ (inlineIndex <= 6 ? NE_LONG ^ SE_LONG : 0L);
            final long team_intersections = result &
                    (targetPiece == Piece.BLACK_KNIGHT ? getAllBlack() : getAllWhite());
            result ^= team_intersections;
            // TODO: Handle opposite colour intersections. Unlike pawns, knights can take out pieces (at intersections).
            return result;
        }
        return 0L;
    }

    /**
     * Provides the index of the provided {@link Piece} type in the bitboard array representing each piece.
     *
     * @param targetPiece the piece for which the index should be calculated
     * @return an {@link Integer} value representing the index
     * @see Pieces#PIECE_BITBOARDS
     */
    private int getIndex(final Piece targetPiece) {
        return Arrays.asList(Piece.values()).indexOf(targetPiece);
    }

    /**
     * Provides the representation of all white pieces in the current setup.
     *
     * @return A {@link Long} value representing a bitboard
     */
    private long getAllWhite() {
        long all = 0L;
        for (short index = 0; index < PIECE_BITBOARDS.length / 2; index++) {
            all ^= PIECE_BITBOARDS[index * 2];
        }
        return all;
    }

    /**
     * Provides the representation of all black pieces in the current setup.
     *
     * @return A {@link Long} value representing a bitboard
     */
    private long getAllBlack() {
        long all = 0L;
        for (short index = 0; index < PIECE_BITBOARDS.length / 2; index++) {
            all ^= PIECE_BITBOARDS[(index * 2) + 1];
        }
        return all;
    }

    /**
     * Provides the representation of all pieces in the current setup.
     *
     * @return a {@link Long} value representing a bitboard
     */
    private long getAll() {
        return getAllWhite() ^ getAllBlack();
    }

}
