import org.junit.jupiter.api.Test;

import model.Rating;
import model.Song;
import static org.junit.jupiter.api.Assertions.*;


public class TestSong {
    
    @Test
    void testSong(){
        Song song = new Song("Hello", "Adele", "25");
        assertEquals("Hello", song.getTitle());
        assertEquals("Adele", song.getArtist());
        assertEquals("25", song.getAlbum());
    }

    @Test
    void testRating(){
        Song song = new Song("Someone Like You", "Adele", "21");
        song.setRating(Rating.FIVE);
        assertEquals(Rating.FIVE, song.getRating());
        song.setRating(Rating.ONE);
        assertEquals(Rating.ONE, song.getRating());
    }
}
