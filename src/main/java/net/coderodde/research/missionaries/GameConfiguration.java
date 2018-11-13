package net.coderodde.research.missionaries;

/**
 *
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Mar 12, 2018)
 */
public final class GameConfiguration {
    
    /**
     * Specifies how many persons (both missionaries and cannibals) can fit in 
     * the boat.
     */
    public static final int MINIMUM_BOAT_CAPACITY                = 2;
    
    /**
     * Specifies the lower limit on the amount of missionaries,
     */
    public static final int MINIMUM_TOTAL_NUMBER_OF_MISSIONARIES = 1;
    
    /**
     * Specifies the lower limit on the amount of cannibals.
     */
    public static final int MINIMUM_TOTAL_NUMBER_OF_CANNIBALS    = 1;
    public static final int MINIMUM_TOTAL_NUMBER_OF_ACTORS       = 1;
    
    private final int totalNumberOfMissionaries;
    
    private final int totalNumberOfCannibals;
    
    private final int boatCapacity;
    
    GameConfiguration(final int totalNumberOfMissionaries,
                      final int totalNumberOfCannibals,
                      final int boatCapacity) {
        checkTotalNumberOfMissionaries  (totalNumberOfMissionaries);
        checkTotalNumberOfCannibals     (totalNumberOfCannibals);
        checkBoatCapacity               (boatCapacity);
    }
    
    /**
     * Makes sure that the given total number of missionaries is not too small.
     * 
     * @param totalNumberOfMisssionaries the number of missionaries to check.
     * @throws {@link java.lang.IllegalArgumentException} in case the given
     *         number of missionaries is too small.
     */
    private final void checkTotalNumberOfMissionaries(
            final int totalNumberOfMisssionaries) {
        if (totalNumberOfMissionaries < MINIMUM_TOTAL_NUMBER_OF_MISSIONARIES) {
            throw new IllegalArgumentException(
                    "Input total number of missionaries (" +
                    totalNumberOfMisssionaries + 
                    ") is too small. Must be at least " + 
                    MINIMUM_TOTAL_NUMBER_OF_MISSIONARIES + ".");
        }
    }
    
    /**
     * Makes sure that the given total number of cannibals is not too small.
     * 
     * @param totalNumberOfCannibals the number of cannibals to check.
     * @throws {@link java.lang.IllegalArgumentException} in case the given 
     *         number of cannibals is too small.
     */
    private final void checkTotalNumberOfCannibals(
            final int totalNumberOfCannibals) {
        if (totalNumberOfCannibals < MINIMUM_TOTAL_NUMBER_OF_CANNIBALS) {
            throw new IllegalArgumentException(
                    "Input total number of cannibals (" + 
                    totalNumberOfCannibals + 
                    ") is too small. Must be at least " +
                    MINIMUM_TOTAL_NUMBER_OF_CANNIBALS + ".");
        }
    }
    
    /**
     * Makes sure that the given boat capacity is not too small.
     * 
     * @param boatCapacity the boat capacity to check.
     * @throws {@link java.lang.IllegalArgumentException} in case the given boat
     *         capacity is too small.
     */
    private final void checkBoatCapacity(final int boatCapacity) {
        if (boatCapacity < MINIMUM_BOAT_CAPACITY) {
            throw new IllegalArgumentException(
                    "Input boat capacity (" + 
                    boatCapacity + 
                    ") is too small. Must be at least " +
                    MINIMUM_BOAT_CAPACITY + ".");
        }
    }
}
