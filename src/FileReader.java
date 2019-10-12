import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;

/**
 * The file reader class.
 *
 * @author Patrick Egli
 * @version 1.0.0
 */
public class FileReader {

    /**
     * The character count in the file
     */
    private double fileCharactersCount;

    /**
     * The character map.
     */
    private HashMap<Integer, CharacterProperty> characters = new HashMap<>();

    /**
     * The default constructor.
     */
    public FileReader() {
        fileCharactersCount = 0;
        characters = new HashMap<>();
    }

    /**
     * Read character from file and count them.
     *
     * @param relativeFilePath The relative file path.
     */
    public void readInputTextFileCharacters(String relativeFilePath) {
        System.out.println("Reading file ...");

        // Open file and read character by character:
        try (BufferedReader in = new BufferedReader(new java.io.FileReader(relativeFilePath))) {
            int c;
            // Read characters c until there are no more:
            while ((c = in.read()) != -1) {
                /*
                 * ToDo: [1.1] implement computation of each character.
                 * */
                if(!characters.containsKey(c)) {
                    characters.put(c, new CharacterProperty(c, 1, 0, 0));
                } else {
                    characters.get(c).updateOccurrenceBy(1);
                }

                /*
                 * ToDo: [1.2] count all characters in the file.
                 * */
                fileCharactersCount++;
            }
        }
        catch(IOException ioe) {}
    }

    /**
     * Returns the character count of the file.
     *
     * @return The character count.
     */
    public double getFileCharactersCount() {
        return fileCharactersCount;
    }

    /**
     * Returns the character map.
     *
     * @return The character map.
     */
    public HashMap<Integer, CharacterProperty> getCharacters() {
        return characters;
    }

}
