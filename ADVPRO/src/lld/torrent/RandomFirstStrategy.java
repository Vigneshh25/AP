package lld.torrent;

import lld.torrent.Chunk;
import lld.torrent.PieceSelectionStrategy;

import java.util.*;

class RandomFirstStrategy implements PieceSelectionStrategy {
    @Override
    public Chunk selectPiece(List<Chunk> availableChunks) {
        // Implement random first piece selection strategy
        // Return a random chunk from the available chunks
        if (availableChunks.isEmpty()) {
            return null;
        }
        Random random = new Random();
        return availableChunks.get(random.nextInt(availableChunks.size()));
    }
}
