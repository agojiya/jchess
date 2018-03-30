package me.agojiya.jchess.board;

/**
 * A class representing the positions of the pieces of a complete chess board.
 *
 * @author agojiya
 */
public class Pieces {

    private final long[] PIECE_BITBOARDS = new long[]{
            Bitboards.WHITE_PAWNS.getBoard(),
            Bitboards.BLACK_PAWNS.getBoard(),
            Bitboards.WHITE_ROOKS.getBoard(),
            Bitboards.BLACK_ROOKS.getBoard(),
            Bitboards.WHITE_KNIGHTS.getBoard(),
            Bitboards.BLACK_KNIGHTS.getBoard(),
            Bitboards.WHITE_BISHOPS.getBoard(),
            Bitboards.BLACK_BISHOPS.getBoard(),
            Bitboards.WHITE_QUEEN.getBoard(),
            Bitboards.BLACK_QUEEN.getBoard(),
            Bitboards.WHITE_KING.getBoard(),
            Bitboards.BLACK_KING.getBoard()
    };

    /**
     * Constructor to initialize the pieces as the starting positions.
     */
    public Pieces() {
        return;
    }

    /**
     * Constructor to initialize the positions of each piece type individually.
     *
     * @param whitePawns   a bitboard representation of the white pawns
     * @param blackPawns   a bitboard representation of the black pawns
     * @param whiteRooks   a bitboard representation of the white rooks
     * @param blackRooks   a bitboard representation of the black rooks
     * @param whiteKnights a bitboard representation of the white knights
     * @param blackKnights a bitboard representation of the black knights
     * @param whiteBishops a bitboard representation of the white bishops
     * @param blackBishops a bitboard representation of the black bishops
     * @param whiteQueen   a bitboard representation of the white queen
     * @param blackQueen   a bitboard representation of the black queen
     * @param whiteKing    a bitboard representation of the white king
     * @param blackKing    a bitboard representation of the black king
     */
    public Pieces(final long whitePawns, final long blackPawns, final long whiteRooks, final long blackRooks,
                  final long whiteKnights, final long blackKnights, final long whiteBishops, final long blackBishops,
                  final long whiteQueen, final long blackQueen, final long whiteKing, final long blackKing) {
        this.PIECE_BITBOARDS[0] = whitePawns;
        this.PIECE_BITBOARDS[1] = blackPawns;
        this.PIECE_BITBOARDS[2] = whiteRooks;
        this.PIECE_BITBOARDS[3] = blackRooks;
        this.PIECE_BITBOARDS[4] = whiteKnights;
        this.PIECE_BITBOARDS[5] = blackKnights;
        this.PIECE_BITBOARDS[6] = whiteBishops;
        this.PIECE_BITBOARDS[7] = blackBishops;
        this.PIECE_BITBOARDS[8] = whiteQueen;
        this.PIECE_BITBOARDS[9] = blackQueen;
        this.PIECE_BITBOARDS[10] = whiteKing;
        this.PIECE_BITBOARDS[11] = blackKing;
    }

    public long getPseudoLegalMoves(final Piece targetPiece, final Position posiiton) {
        // TODO: Calculate pseudo-legal moves based on the Piece type if the piece exists at the provided position
        // TODO: Check for legality
        return 0L;
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
