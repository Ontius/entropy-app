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

import java.io.File;

/**
 * The main class.
 *
 * @author Patrick Egli
 * @version 1.0.0
 */
public class Main {

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

        Entropy entropy = new Entropy();

        // Call each method
        entropy.readInputTextFileCharacters(s);
        entropy.computeProbabilities();
        entropy.computeInformation();
        entropy.computeEntropy();
        entropy.printOutCharProps();

        // Print goodbye message
        System.out.println("Done.");
        System.out.println("======================================================");
    }

}
