/**
 * Character property class.
 *
 * @author Patrick Egli
 * @version 1.0.0
 */
public class CharacterProperty {

    /**
     * The character.
     */
    private int character;

    /**
     * The occurrence of the character.
     */
    private int occurrence;

    /**
     * The probability of the character.
     */
    private double probability;

    /**
     * The information of the character.
     */
    private double information;

    /**
     * This constructor creates a {@link CharacterProperty} object instance with the given
     * {@code character}, {@code occurrence}, {@code probability} and {@code information}.
     *
     * @param character The character.
     * @param occurrence The occurrence that is being set.
     * @param probability The probability that is being set.
     * @param information The information that is being set.
     */
    public CharacterProperty(int character, int occurrence, double probability, double information) {
        this.character = character;
        this.occurrence = occurrence;
        this.probability = probability;
        this.information = information;
    }

    /**
     * Returns the character.
     *
     * @return The character.
     */
    public int getCharacter() {
        return character;
    }

    /**
     * Sets the character.
     *
     * @param character The character that is being set.
     */
    public void setCharacter(int character) {
        this.character = character;
    }

    /**
     * Returns the occurrence of the character.
     *
     * @return The occurrence of the character.
     */
    public int getOccurrence() {
        return occurrence;
    }

    /**
     * Sets the occurrence of the character.
     *
     * @param occurrence The occurrence of the character that is being set.
     */
    public void setOccurrence(int occurrence) {
        this.occurrence = occurrence;
    }

    /**
     * Updates the occurrence by the given {@code delta}.
     *
     * @param delta The delta value.
     */
    public void updateOccurrenceBy(int delta) {
        this.occurrence += delta;
    }

    /**
     * Returns the probability of the character.
     *
     * @return The probability of the character.
     */
    public double getProbability() {
        return probability;
    }

    /**
     * Sets the probability of the character.
     *
     * @param probability The probability of the character that is being set.
     */
    public void setProbability(double probability) {
        this.probability = probability;
    }

    /**
     * Returns the information of the character.
     *
     * @return The information of the character.
     */
    public double getInformation() {
        return information;
    }

    /**
     * Sets the information of the character.
     *
     * @param information The information of the character that is being set.
     */
    public void setInformation(double information) {
        this.information = information;
    }

    /**
     * Returns the character as a string.
     *
     * @return The character as a string.
     */
    public String getCharacterAsString() {
        return Character.isWhitespace(character) ? "(" + character + ")" : "" + (char) character;
    }

}
