package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Album{
    private String title;
    private String artist;
    private String genre;
    private int year;
    private List<Song> songs;


    public Album(String title, String artist, String genre, int year){
        this.title = title;
        this.artist = artist;
        this.genre = genre;
        this.year = year;
        this.songs = new ArrayList<>();
    }
    
    public void addSong(Song song) {
        if (song.getAlbum().equals(this.title) && song.getArtist().equals(this.artist)) {
            songs.add(song);
        } else {
            throw new IllegalArgumentException("Song does not belong to this album.");
        }
    }

    public String getTitle(){
        return title;
    }

    public String getArtist(){
        return artist;
    }

    public String getGenre(){
        return genre;
    }

    public int getYear(){
        return year;
    }

    public List<Song> getSongs(){
        return Collections.unmodifiableList(songs);
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(title).append(" by ").append(artist)
          .append(" (").append(year).append(") - Genre: ").append(genre)
          .append("\nSongs:\n");

        for (int i = 0; i < songs.size(); i++) {
            sb.append(i + 1).append(". ").append(songs.get(i).getTitle()).append("\n");
        }

        return sb.toString();
    }

}