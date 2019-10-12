import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The entropy class. Contains the functionality to calculate the entropy of a given file.
 *
 * @author Patrick Egli
 * @version 1.0.0
 */
public class Entropy {

    /**
     * Map for all the characters.
     */
    private HashMap<Integer, CharacterProperty> characters = new HashMap<>();

    /**
     * The number of characters in the file.
     */
    private double fileCharactersCount = 0;

    /**
     * The entropy of the file.
     */
    private double fileEntropy = 0;

    /**
     * Base 2 logarithm.
     *
     * @param d The value to compute the the logarithm.
     * @return The result value.
     */
    private double log2(double d) {
        return Math.log(d)/Math.log(2.0);
    }

    /**
     * Reads characters from file and count them.
     *
     * @param relativeFilePath The relative file path.
     */
    public void readInputTextFileCharacters(String relativeFilePath) {
        System.out.println("Reading file ...");

        // Open file and read character by character:
        try (BufferedReader in = new BufferedReader(new FileReader(relativeFilePath))) {
            int c;
            // Read characters c until there are no more:
            while ((c = in.read()) != -1) {
                // ToDo: [1.1] implement computation of each character.
                if(!characters.containsKey(c)) {
                    characters.put(c, new CharacterProperty(c, 1, 0, 0));
                } else {
                    characters.get(c).updateOccurrenceBy(1);
                }

                // ToDo: [1.2] count all characters in the file.
                fileCharactersCount++;
            }
        }
        catch(IOException ioe) {}
    }

    /**
     * Computes the probability of each character in the map.
     */
    public void computeProbabilities() {
        System.out.println( "Computing probabilities...");

        // ToDo: [2] implement computation of the probability for each character.

        // Parse through all hash-map keys:
        for (Map.Entry<Integer, CharacterProperty> entry : characters.entrySet()) {
            CharacterProperty property = entry.getValue();
            int occurrence = property.getOccurrence();
            double probability = (double)occurrence / fileCharactersCount;
            property.setProbability(probability);
        }
    }

    /**
     * Computes the information for each character in the map.
     */
    public void computeInformation() {
        System.out.println( "Computing information...");

        // ToDo: [3] implement computation of the information for each character.

        // Parse through all hash-map keys:
        for (Map.Entry<Integer, CharacterProperty> entry : characters.entrySet()) {
            CharacterProperty property = entry.getValue();
            double probability = property.getProbability();
            double information = log2(1 / probability);
            property.setInformation(information);
        }
    }

    /**
     * Computes the entropy of all characters in the map.
     */
    public void computeEntropy() {
        System.out.println( "Computing entropy...");
        double sum = 0;

        // ToDo: [4] implement computation of the entropy of all characters. Send the entropy value back as a result.

        for(Map.Entry<Integer, CharacterProperty> entry : characters.entrySet()) {
            CharacterProperty property = entry.getValue();
            double probability = property.getProbability();
            sum += probability * log2(1 / probability);
        }

        fileEntropy = sum;
    }

    /**
     * Prints the result table with occurrence, probability and information.
     */
    public void printOutCharProps() {
        // Print general statistics
        System.out.println("Character types in file: " + characters.size());
        System.out.println("Number of character in file: " + fileCharactersCount);
        System.out.println("Entropy of file: " + fileEntropy);
        System.out.println(" ");

        // Print character statistics:
        for (Map.Entry<Integer, CharacterProperty> entry : characters.entrySet()) {
            CharacterProperty property = entry.getValue();
            String c = property.getCharacterAsString();
            int o = property.getOccurrence();
            double p = property.getProbability();
            double i = property.getInformation();

            System.out.format("  %5s : o=%8d  p=%1.10f  i=%-2.10f%n", c, o, p, i);
        }
    }

}
