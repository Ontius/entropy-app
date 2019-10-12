/**
*
*      Entropy
*
* ============================================================================
*
*      Version      1.02
*      Date         2015-10-08
*      Author       J. M. Stettbacher
*
*      System       Java (tested on version 1.7.0_55 on Linux)
*
* ============================================================================
*
*      Build class file:
*      (1) Compile using Eclipse or other.
*      (2) Compile using Makefile. Type on command line:
*          >> make
*      (3) Directly on command line:
*          >> javac Entropy.java
*
*      Execute class file (filename is a valid data file in ASCII format):
*      (1) Run from Eclipse by specifying the filename command line argument.
*      (2) Run from command line:
*          >> java Entropy filename
*
*      Description:
*      Reads symbols from the data file and determines:
*      - number of different character types in file.
*      - total number of characters in file.
*      - probability of each character type.
*      - information of each character type.
*      - entropy of entire file.
*
*/


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * The main class.
 *
 * @author Patrick Egli
 * @version 1.0.0
 */
public class Main {

    /** Global hash-map (some kind of an array):
     * Key is of type Integer (we will use character codes as key).
     * Content of hash-map is of type CharProp.
     * Name of hash-map is "chars" (characters).
     */
    static HashMap <Integer, CharacterProperty> chars = new HashMap<>();

    /** Useful hash-map methods:
     * chars.get()		Returns the contents of a key.
     * chars.put()		Add an element to the map.
     * chars.remove()	Remove an element from the map.
     * chars.size()		Returns number of keys in map.
     * chars.containsKey()	Returns true if key is in map.
     * chars.keySet()	Returns a set of all keys in this map.
     */

    /**
     * Global counter variable:
     */
    static double fileCharactersCount = 0;
    static double fileEntropy = 0;

    /**
     * Main method. The program starts here.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        // Print hello message:
        System.out.println("======================================================");
        System.out.println("Starting ComputeMain...");

        /**
         * Check if a valid filename has been supplied on command line:
         * Is there a command line argument at all?
         */
        if (args.length <= 0) {
            System.out.println("ERROR: You have to supply a filename on the command line!");
            System.out.println(" ");
            System.exit(0);
        }

        // Yes, there is one
        String s = args[0];
        File file = new File(s);
        // Check if file with that name exists
        if (!file.exists()) {
            // Quit program with an error message
            System.out.println("ERROR: Data file: " + s + " does not exist!");
            System.out.println(" ");
            System.exit(0);
        }

        // Yes, command line argument is okay.
        System.out.println( "Data file " + s + " exists.");

        // Call each method
        ReadInputTextFileCharacters(s);
        ComputeProbabilities();
        ComputeInformation();
        ComputeEntropy();
        PrintOutCharProps();

        // Print goodbye message
        System.out.println("Done.");
        System.out.println("======================================================");
    }

    /**
     * Base 2 logarithm.
     *
     * @param d The value to compute the the logarithm.
     * @return The result value.
     */
    static double log2(double d) {
        return Math.log(d)/Math.log(2.0);
    }

    /**
     * Read character from file and count them.
     *
     * @param relativeFilePath The relative file path.
     */
    static void ReadInputTextFileCharacters(String relativeFilePath) {
        System.out.println("Reading file ...");

        // Open file and read character by character:
        try (BufferedReader in = new BufferedReader(new FileReader(relativeFilePath))) {
            int c;
            // Read characters c until there are no more:
            while ((c = in.read()) != -1) {
                /*
                 * ToDo: [1.1] implement computation of each character.
                 * */
                if(!chars.containsKey(c)) {
                    chars.put(c, new CharacterProperty(c, 1, 0, 0));
                } else {
                    chars.get(c).updateOccurrenceBy(1);
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
     * Compute probability of each character in hash-map.
     */
    static void ComputeProbabilities() {

        System.out.println( "Computing probabilities...");
        /*
         * ToDo: [2] implement computation of the probability for each character.
         *
         * */

        // Parse through all hash-map keys:
        for (Map.Entry<Integer, CharacterProperty> entry : chars.entrySet()) {
            CharacterProperty property = entry.getValue();
            int occurrence = property.getOccurrence();
            double probability = (double)occurrence / fileCharactersCount;
            property.setProbability(probability);
        }
    }

    /**
     * Compute information for each character in hash-map.
     */
    static void ComputeInformation() {

        System.out.println( "Computing information...");
        /*
         * ToDo: [3] implement computation of the information for each character.
         *
         * */

        // Parse through all hash-map keys:
        for (Map.Entry<Integer, CharacterProperty> entry : chars.entrySet()) {
            CharacterProperty property = entry.getValue();
            double probability = property.getProbability();
            double information = log2(1 / probability);
            property.setInformation(information);
        }
    }

    /**
     * Compute entropy of all characters in hash-map.
     */
    static void ComputeEntropy() {
        System.out.println( "Computing entropy...");
        double sum = 0;
        /*
         * ToDo: [4] implement computation of the entropy of all characters.
         *           Send the entropy value back as a result.
         * */

        for(Map.Entry<Integer, CharacterProperty> entry : chars.entrySet()) {
            CharacterProperty property = entry.getValue();
            double probability = property.getProbability();
            sum += probability * log2(1 / probability);
        }

        fileEntropy = sum;
    }

    /**
     * Print result table with occurrence, probability and information.
     */
    static void PrintOutCharProps() {
        // Print general statistics
        System.out.println("Character types in file: " + chars.size());
        System.out.println("Number of character in file: " + fileCharactersCount);
        System.out.println("Entropy of file: " + fileEntropy);
        System.out.println(" ");

        // Print character statistics:
        for (Map.Entry<Integer, CharacterProperty> entry : chars.entrySet()) {
            CharacterProperty property = entry.getValue();
            String c = property.getCharacterAsString();
            int o = property.getOccurrence();
            double p = property.getProbability();
            double i = property.getInformation();

            System.out.format("  %5s : o=%8d  p=%1.10f  i=%-2.10f%n", c, o, p, i);
        }
    }
}
