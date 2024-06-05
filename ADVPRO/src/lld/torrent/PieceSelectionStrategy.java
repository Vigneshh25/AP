package lld.torrent;

import java.util.List;

public interface PieceSelectionStrategy {
    Chunk selectPiece(List<Chunk> availableChunks);
}
