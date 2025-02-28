import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Album;
import model.Song;
import store.MusicStore;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MusicStoreTest {
    private MusicStore store;

    @BeforeEach
    void setUp() throws IOException {
        // Create a temporary test albums.txt file
        File albumsFile = new File("resources/albums/test_albums.txt");
        albumsFile.getParentFile().mkdirs();  // Ensure directories exist
        try (FileWriter writer = new FileWriter(albumsFile)) {
            writer.write("Test Album,Test Artist\n");
        }

        // Create a temporary test album file
        File albumFile = new File("resources/albums/Test Album_Test Artist.txt");
        try (FileWriter writer = new FileWriter(albumFile)) {
            writer.write("Test Album,Test Artist,Rock,2022\n");
            writer.write("Test Song\n");
        }

        // Load the test files
        store = new MusicStore("resources/albums/test_albums.txt");
    }
    
    @Test
    void testLoadMusicStore() {
        assertTrue(store.containsAlbum("Test Album", "Test Artist"));
    }

    @Test
    void testLoadAlbumDetails() {
        Album album = store.getAlbum("Test Album", "Test Artist");
        assertNotNull(album);
        assertEquals("Test Album", album.getTitle());
        assertEquals("Test Artist", album.getArtist());
        assertEquals("Rock", album.getGenre());
        assertEquals(2022, album.getYear());
        assertEquals(1, album.getSongs().size());
    }

    @Test
    void testContainsAlbum() {
        assertTrue(store.containsAlbum("Test Album", "Test Artist"));
        assertFalse(store.containsAlbum("Fake Album", "Fake Artist"));
    }

    @Test
    void testContainsSong() {
        assertTrue(store.containsSong("Test Song", "Test Artist"));
        assertFalse(store.containsSong("Nonexistent Song", "Unknown Artist"));
    }

    @Test
    void testGetSong() {
        Song song = store.getSong("Test Song", "Test Artist");
        assertNotNull(song);
        assertEquals("Test Song", song.getTitle());
        assertEquals("Test Artist", song.getArtist());
    }

    @Test
    void testGetAlbum() {
        Album album = store.getAlbum("Test Album", "Test Artist");
        assertNotNull(album);
    }

    @Test
    void testLoadMissingAlbumFile() {
        assertNull(store.getAlbum("Missing Album", "Missing Artist"));
    }
    
    @Test
    void testSearchSongsByTitle() {
    	MusicStore store = new MusicStore("resources/albums/albums.txt");
        List<Song> results = store.searchSong("Make You Feel My Love");
        assertFalse(results.isEmpty());
    }

    @Test
    void testSearchSongsByArtist() {
    	MusicStore store = new MusicStore("resources/albums/albums.txt");
        List<Song> results = store.searchArtistSongs("Adele");
        assertFalse(results.isEmpty());
    }

    @Test
    void testSearchAlbumByTitle() {
    	MusicStore store = new MusicStore("resources/albums/albums.txt");
        Album album = store.searchAlbum("21");
        assertNotNull(album);
        assertEquals("21", album.getTitle());
    }

    @Test
    void testSearchAlbumsByArtist() {
    	MusicStore store = new MusicStore("resources/albums/albums.txt");
        List<Album> results = store.searchArtistAlbums("Adele");
        assertFalse(results.isEmpty());
    }
}
