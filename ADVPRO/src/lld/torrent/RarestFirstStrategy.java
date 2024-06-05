package lld.torrent;

import java.util.List;

class RarestFirstStrategy implements PieceSelectionStrategy {
    @Override
    public Chunk selectPiece(List<Chunk> availableChunks) {
        // Implement rarest first piece selection strategy
        // For simplicity, returning the first chunk in the list
        return availableChunks.isEmpty() ? null : availableChunks.get(0);
    }
}
