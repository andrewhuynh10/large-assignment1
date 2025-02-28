package view;

import model.*;
import store.MusicStore;
import java.util.*;

/**
 * MusicLibraryUI is a text-based user interface for interacting with the user's music library.
 * It communicates with the LibraryModel and MusicStore to search, add, and manage songs, albums, and playlists.
 *
 * NOTE: Portions of this code were generated using AI assistance and were reviewed and tested for correctness.
 */
public class UserInterface {
    private Scanner scanner;
    private LibraryModel library;
    private MusicStore store;

    public UserInterface(LibraryModel library, MusicStore store) {
        this.scanner = new Scanner(System.in);
        this.library = library;
        this.store = store;
    }

    public void start() {
        while (true) {
            System.out.println("\nMusic Library Menu:");
            System.out.println("1. Search for a Song");
            System.out.println("2. Search for an Album");
            System.out.println("3. Add Song to Library");
            System.out.println("4. Add Album to Library");
            System.out.println("5. Create Playlist");
            System.out.println("6. Add Song to Playlist");
            System.out.println("7. List Favorite Songs");
            System.out.println("8. Rate a Song");
            System.out.println("9. Mark Song as Favorite");
            System.out.println("10. Exit");
            System.out.print("Enter choice: ");
            
            int choice = scanner.nextInt();
            scanner.nextLine();
            
            switch (choice) {
                case 1:
                    searchSongOption();
                    break;
                case 2:
                    searchAlbumOption();
                    break;
                case 3:
                    addSongToLibrary();
                    break;
                case 4:
                    addAlbumToLibrary();
                    break;
                case 5:
                    createPlaylist();
                    break;
                case 6:
                    addSongToPlaylist();
                    break;
                case 7:
                    listFavoriteSongs();
                    break;
                case 8:
                    rateSong();
                    break;
                case 9:
                    markSongAsFavorite();
                    break;
                case 10:
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid choice. Try again.");            }
        }
    }
    
    private void searchSongOption() {
        System.out.println("Search by:");
        System.out.println("1. Song Title");
        System.out.println("2. Artist Name");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            searchSong();
        } else if (choice == 2) {
            searchSongByArtist();
        } else {
            System.out.println("Invalid choice. Returning to main menu.");
        }
    }

    private void searchSong() {
        System.out.print("Enter song title: ");
        String title = scanner.nextLine();
        List<Song> results = store.searchSong(title);
        if (results.isEmpty()) {
            System.out.println("No matching songs found.");
        } else {
            results.forEach(System.out::println);
        }
    }
    
    private void searchSongByArtist() {
        System.out.print("Enter artist name: ");
        String artist = scanner.nextLine();
        List<Song> results = store.searchArtistSongs(artist);
        if (results.isEmpty()) {
            System.out.println("No songs found for artist " + artist);
        } else {
            results.forEach(System.out::println);
        }
    }
    
    private void searchAlbumOption() {
        System.out.println("Search for an album by:");
        System.out.println("1. Album Title");
        System.out.println("2. Artist Name");
        System.out.print("Enter choice: ");
        int choice = scanner.nextInt();
        scanner.nextLine();

        if (choice == 1) {
            searchAlbumByTitle();
        } else if (choice == 2) {
            searchAlbumByArtist();
        } else {
            System.out.println("Invalid choice. Returning to main menu.");
        }
    }

    private void searchAlbumByTitle() {
        System.out.print("Enter album title: ");
        String title = scanner.nextLine();
        Album album = store.searchAlbum(title);
        if (album != null) {
            System.out.println(album);
        } else {
            System.out.println("Album not found.");
        }
    }
    
    private void searchAlbumByArtist() {
        System.out.print("Enter artist name: ");
        String artist = scanner.nextLine();
        List<Album> results = store.searchArtistAlbums(artist);
        if (results.isEmpty()) {
            System.out.println("No albums found for artist " + artist);
        } else {
            results.forEach(System.out::println);
        }
    }

    private void addSongToLibrary() {
        System.out.print("Enter song title: ");
        String title = scanner.nextLine();
        System.out.print("Enter artist: ");
        String artist = scanner.nextLine();

        Song song = store.getSong(title, artist);

        if (song == null) {
            System.out.println("Song not found in MusicStore!");
            return;
        }

        System.out.println("Found song -> " + song.getTitle() + " by " + song.getArtist());
        
        boolean added = library.addSong(song, store);

        if (added) {
            System.out.println("Song added successfully!");
        } else {
            System.out.println("Could not add song. It might already be in the library or doesn't exist in the store.");
        }
    }


    private void addAlbumToLibrary() {
        System.out.print("Enter album title: ");
        String title = scanner.nextLine();
        System.out.print("Enter artist: ");
        String artist = scanner.nextLine();
        Album album = store.getAlbum(title, artist);
        if (album != null && library.addAlbum(album, store)) {
            System.out.println("Album added successfully!");
        } else {
            System.out.println("Could not add album.");
        }
    }

    private void createPlaylist() {
        System.out.print("Enter playlist name: ");
        String name = scanner.nextLine();
        if (library.createPlaylist(name)) {
            System.out.println("Playlist created successfully!");
        } else {
            System.out.println("Playlist already exists.");
        }
    }

    private void addSongToPlaylist() {
        System.out.print("Enter playlist name: ");
        String playlist = scanner.nextLine();
        System.out.print("Enter song title: ");
        String title = scanner.nextLine();
        System.out.print("Enter artist: ");
        String artist = scanner.nextLine();
        Song song = store.getSong(title, artist);
        if (song != null && library.addSongToPlaylist(playlist, song)) {
            System.out.println("Song added to playlist.");
        } else {
            System.out.println("Could not add song.");
        }
    }

    private void listFavoriteSongs() {
        List<Song> favorites = library.listFavorite();
        if (favorites.isEmpty()) {
            System.out.println("No favorite songs found.");
        } else {
            favorites.forEach(System.out::println);
        }
    }

    private void rateSong() {
        System.out.print("Enter song title: ");
        String title = scanner.nextLine();
        System.out.print("Enter artist: ");
        String artist = scanner.nextLine();
        System.out.print("Enter rating (1-5): ");
        int rating = scanner.nextInt();
        scanner.nextLine();

        Song song = store.getSong(title, artist);
        
        if (song != null && rating >= 1 && rating <= 5) {
            song.setRating(Rating.values()[rating - 1]);
            System.out.println("Rating updated.");

            //  If rating is 5, automatically mark as favorite
            if (rating == 5) {
                boolean favoriteAdded = library.makeFavorite(song);
                if (favoriteAdded) {
                    System.out.println("This song is now marked as a favorite!");
                } else {
                    System.out.println("Song was already a favorite.");
                }
            }
        } else {
            System.out.println("Invalid rating or song not found.");
        }
    }

    private void markSongAsFavorite() {
        System.out.print("Enter song title: ");
        String title = scanner.nextLine();
        System.out.print("Enter artist: ");
        String artist = scanner.nextLine();
        Song song = store.getSong(title, artist);
        if (song != null && library.makeFavorite(song)) {
            System.out.println("Song marked as favorite.");
        } else {
            System.out.println("Could not mark song as favorite.");
        }
    }
}