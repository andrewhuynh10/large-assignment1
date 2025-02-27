import org.junit.jupiter.api.Test;

import model.LibraryModel;
import model.Song;
import store.MusicStore;

import static org.junit.jupiter.api.Assertions.*;

class LibraryModelTest {
    
	
	@Test
    void testAddSongToLibrary() {
        LibraryModel library = new LibraryModel();
        MusicStore store = new MusicStore("resources/albums/albums.txt"); // Assuming files exist
        Song song = new Song("Blinding Lights", "The Weeknd", "After Hours");
        assertFalse(library.addSong(song, store)); // Should fail if song isn't in store
    }

    @Test
    void testCreatePlaylist() {
        LibraryModel library = new LibraryModel();
        assertTrue(library.createPlaylist("Workout Mix"));
        assertFalse(library.createPlaylist("Workout Mix")); // Duplicate name
    }

    @Test
    void testAddSongToPlaylist() {
        LibraryModel library = new LibraryModel();
        library.createPlaylist("Chill Hits");
        Song song = new Song("Save Your Tears", "The Weeknd", "After Hours");
        assertFalse(library.addSongToPlaylist("Chill Hits", song)); // Song must be in library first
    }

    @Test
    void testAddFavorite(){
        LibraryModel library = new LibraryModel();
        Song song = new Song("Save Your Tears", "The Weeknd", "After Hours");
        assertTrue(library.addFavorite(song));
    }

}
