/*
 * File: LibraryModel.java
 * 
 * Purpose: This class is used as the user's library, where the user can add albums, songs, from the MusicStore, and create their own playlists.
 * I decided on using a Set for the songs and albums, since irl you cant really have duplicate things like that in your library, so a set insures that there are no dups!
 */


package model;

import java.util.*;

import store.MusicStore;

public class LibraryModel {
    private Set<Song> songs; //User song collection
    private Set<Album> albums; // User album collection
    private Map<String, Playlist> playlists; // User playlists

    public LibraryModel(){
        this.songs = new HashSet<>();
        this.albums = new HashSet<>();
        this.playlists = new HashMap<>();
    }


    // Adds song to user library
    public boolean addSong(Song song, MusicStore store){
        if(store.containsSong(song.getTitle(), song.getArtist())){
            return songs.add(song);
        }
        return false;
    }


    // Adds album to user library
    public boolean addAlbum(Album album, MusicStore store){
        if (store.containsAlbum(album.getTitle(), album.getArtist())){
            return albums.add(album);
        }
        return false;
    }


    // Creates new Playlist
    public boolean createPlaylist(String name){
        if(!playlists.containsKey(name)){
            playlists.put(name, new Playlist(name));
            return true;
        }
        return false;
    }

    // Add song to playlist
    public boolean addSongToPlaylist(String name, Song song){
        Playlist playlist = playlists.get(name);
        if (playlist != null && songs.contains(song)){
            playlist.addSong(song);
            return true;
        }
        return false;
    }
    // Remove song from playlist
    public boolean removeSongFromPlaylist(String name, Song song){
        Playlist playlist = playlists.get(name);
        return playlist != null && playlist.removeSong(song);
    }

    // Basic getters
    public Set<Song> getSong(){
        return Collections.unmodifiableSet(songs);
    }
    
    public Set<Album> getAlbums(){
        return Collections.unmodifiableSet(albums);
    }

    public Map<String, Playlist> getPlaylist(){
        return Collections.unmodifiableMap(playlists);
    }

    @Override
    public String toString(){
        return "Library:\nSongs: " + songs + "\nAlbums: " + albums + "\nPlaylists: " + playlists.keySet();
    }
}