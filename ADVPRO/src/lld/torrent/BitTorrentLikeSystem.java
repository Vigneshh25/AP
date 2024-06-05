package lld.torrent;

import java.util.List;

public class BitTorrentLikeSystem {
    public static void main(String[] args) {
        // Create peers
        Peer peer1 = PeerFactory.createPeer("peer1");
        Peer peer2 = PeerFactory.createPeer("peer2");

        // Create files
        File file1 = FileFactory.createFile("file1");
        File file2 = FileFactory.createFile("file2");

        // Create chunks
        Chunk chunk1 = ChunkFactory.createChunk("chunk1", new byte[]{0, 1, 2}, "file1");
        Chunk chunk2 = ChunkFactory.createChunk("chunk2", new byte[]{3, 4, 5}, "file2");

        // Add chunks to files
        file1.addChunk(chunk1);
        file2.addChunk(chunk2);

        // Share files among peers
        peer1.shareFile(file1);
        peer2.shareFile(file2);

        // Download chunks using different piece selection strategies
        PieceSelectionStrategy rarestFirstStrategy = new RarestFirstStrategy();
        PieceSelectionStrategy randomFirstStrategy = new RandomFirstStrategy();

        List<Chunk> file2Chunks = peer2.getSharedFiles().get(0).getChunks();
        Chunk rarestChunk = rarestFirstStrategy.selectPiece(file2Chunks);
        if (rarestChunk != null) {
            peer1.downloadChunk(rarestChunk);
        }

        List<Chunk> file1Chunks = peer1.getSharedFiles().get(0).getChunks();
        Chunk randomChunk = randomFirstStrategy.selectPiece(file1Chunks);
        if (randomChunk != null) {
            peer2.downloadChunk(randomChunk);
        }
    }
}
