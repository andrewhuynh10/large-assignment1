import model.LibraryModel;
import store.MusicStore;
import view.UserInterface;

public class Main {
    public static void main(String[] args) {
        // Initialize Music Store with the path to albums file
        MusicStore store = new MusicStore("resources/albums/albums.txt"); // Update path if needed
        
        // Initialize User's Library
        LibraryModel library = new LibraryModel();
        
        // Start the User Interface
        UserInterface ui = new UserInterface(library, store);
        ui.start();
    }
}
