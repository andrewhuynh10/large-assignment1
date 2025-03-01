import org.junit.jupiter.api.Test;

import model.Album;
import model.LibraryModel;
import model.Rating;
import model.Song;
import store.MusicStore;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import java.util.Set;

class LibraryModelTest {
    
	
	@Test
    void testAddSongToLibrary() {
        LibraryModel library = new LibraryModel();
        MusicStore store = new MusicStore("resources/albums/albums.txt"); // Assuming files exist
        Song song = new Song("Blinding Lights", "The Weeknd", "After Hours");
        Song newSong = new Song("Daydreamer", "Adele", "19");
        assertFalse(library.addSong(song, store)); // Should fail if song isn't in store
        assertTrue(library.addSong(newSong, store));
    }
	
	@Test
    void testAddAlbumToLibrary() {
        LibraryModel library = new LibraryModel();
        MusicStore store = new MusicStore("resources/albums/albums.txt"); // Assuming files exist
        Album album = new Album("25", "Adele", "Pop", 2015);
        Album newAlbum = new Album("19", "Adele", "Pop", 2008);
        assertFalse(library.addAlbum(album, store)); // Should fail if song isn't in store
        assertTrue(library.addAlbum(newAlbum, store));        
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
    void testRemoveSongFromPlaylist() {
    	LibraryModel library = new LibraryModel();
    	MusicStore store = new MusicStore("resources/albums/albums.txt");
    	library.createPlaylist("Yo Mama");
    	Song song = new Song("Daydreamer", "Adele", "19");
    	library.addSong(song, store);
    	library.addSongToPlaylist("Yo Mama", song);
    	assertTrue(library.removeSongFromPlaylist("Yo Mama", song));
    	assertFalse(library.removeSongFromPlaylist("Yo Mama", new Song("YOOOO","ME","Swag")));
    	
    }
    
    @Test
    void testRateAndMakeFav() {
    	LibraryModel library = new LibraryModel();
    	MusicStore store = new MusicStore("resources/albums/albums.txt");
    	Song song = new Song("Daydreamer", "Adele", "19");
    	Song newSong = new Song("Save Your Tears", "The Weeknd", "After Hours");
    	library.addSong(song, store);
    	assertTrue(library.makeFavorite(song));
    	assertFalse(library.makeFavorite(newSong));
    	assertTrue(library.listFavorite().contains(song));
    	assertFalse(library.listFavorite().contains(newSong));
    	assertTrue(library.rateSong(song, Rating.THREE));
    	assertFalse(library.rateSong(newSong, Rating.THREE));
    	
    }
    
    @Test
    void testSearchSong() {
        LibraryModel library = new LibraryModel();
        MusicStore store = new MusicStore("resources/albums/albums.txt");
        Song song = new Song("Daydreamer", "Adele", "19");

        library.addSong(song, store);

        assertNotNull(library.searchSong("Daydreamer"));
        assertEquals("Daydreamer", library.searchSong("Daydreamer").getTitle());

        assertNull(library.searchSong("Nonexistent Song"));
    }

    
    @Test
    void testSearchAlbum() {
    	LibraryModel library = new LibraryModel();
    	MusicStore store = new MusicStore("resources/albums/albums.txt");
    	Album album = store.getAlbum("19", "Adele");
    	library.addAlbum(album, store);
    	assertNotNull(library.searchAlbum("19"));
        assertNull(library.searchAlbum("Nonexistent Song"));
    }
    
    @Test
    void testSearchSongArtist() {
    	LibraryModel library = new LibraryModel();
    	MusicStore store = new MusicStore("resources/albums/albums.txt");
    	String artist = "Adele";
    	Album album = store.getAlbum("19", "Adele");
    	library.addAlbum(album, store);
    	assertEquals(12,library.searchArtistSongs(artist).size());
    }
    
    @Test
    void testSearchPlaylistByName() {
        LibraryModel library = new LibraryModel();

        library.createPlaylist("Road Trip");

        assertNotNull(library.searchPlaylist("Road Trip"));
        assertNull(library.searchPlaylist("Nonexistent Playlist"));
    }
    
    @Test
    void testListThings(){
        LibraryModel library = new LibraryModel();
        Song song = new Song("Amen", "Leonard Cohen", "Old Ideas");
        MusicStore store = new MusicStore("resources/albums/albums.txt");
        Album album = store.getAlbum("Old Ideas", "Leonard Cohen");
        assertNotNull(album, "Album 'Old Ideas' not found in MusicStore!");
        library.addAlbum(album, store);
        library.addSong(song, store);
        library.createPlaylist("Test");
        library.addSongToPlaylist("Test", song);

        List<String> songTitles = library.listSongs();
        Set<String> artists = library.listArtists();
        List<String> albums = library.listAlbums();
        Set<String> playlists = library.listPlaylist();
        assertFalse(songTitles.isEmpty());
        assertFalse(artists.isEmpty());
        assertFalse(albums.isEmpty());
        assertFalse(playlists.isEmpty());
    }
    
    @Test
    void testGetters() {
        LibraryModel library = new LibraryModel();

        assertTrue(library.getSong().isEmpty());
        assertTrue(library.getAlbums().isEmpty());
        assertTrue(library.getPlaylist().isEmpty());
    }

    @Test
    void testToString() {
        LibraryModel library = new LibraryModel();
        String output = library.toString();

        assertTrue(output.contains("Library:"));
        assertTrue(output.contains("Songs:"));
        assertTrue(output.contains("Albums:"));
        assertTrue(output.contains("Playlists:"));
    }

}