package net.coderodde.research.missionaries;

import java.util.List;
import java.util.Objects;

/**
 * This class implements a state of the <a href="https://en.wikipedia.org/wiki/Missionaries_and_cannibals_problem">Missionaries and Cannibals problem</a>.
 * As crossing a river involves two banks, {@code missionaries} 
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Nov 12, 2018)
 */
public final class StateNode implements AbstractUndirectedGraphNode<StateNode> {
    
    /**
     * The number of missionaries on the source bank. The target bank contains
     * <code>gameConfiguration.getTotalNumberOfMissionaries() - numberOfMIssionaries</code>.
     */
    private final int numberOfMissionaries;
    
    /**
     * The number of cannibals on the source bank. The target bank contains
     * <code>gameConfiguration.getTotalNumberOfCannibals() - numberOfCannibals</code>.
     */
    private final int numberOfCannibals;
    
    /**
     * The boat location. The source is either at the source bank, or at the
     * target bank.
     */
    private final BoatLocation boatLocation;
    
    /**
     * Holds all the data describing the problem parameters.
     */
    private final GameConfiguration gameConfiguration;
    
    /**
     * The number of created state nodes.
     */
    private static int counter;
    
    /**
     * Constructs a new state node representing a particular game state.
     * 
     * @param numberOfMissionaries the number of cannibals at the source node.
     * @param numberOfCannibals    the number of cannibals at the source bank.
     * @param boatLocation         the boat location.
     * @param gameConfiguration    the game configuration object.
     */
    public StateNode(final int numberOfMissionaries,
                     final int numberOfCannibals,
                     final BoatLocation boatLocation,
                     final GameConfiguration gameConfiguration) {
        checkNumberOfMissionaries(numberOfMissionaries);
        checkNumberOfCannibals(numberOfCannibals);
        Objects.requireNonNull(boatLocation, "The boat location is null.");
        Objects.requireNonNull(gameConfiguration, 
                               "The game configuration object is null.");
        this.numberOfMissionaries = numberOfMissionaries;
        this.numberOfCannibals    = numberOfCannibals;
        this.boatLocation         = boatLocation;
        this.gameConfiguration    = gameConfiguration;
    }
    
    /**
     * Checks that the number of missionaries is within bounds.
     * 
     * @param numberOfMissionaries the number of missionaries.
     * @throws IllegalArgumentException in case the number of missionaries is 
     *                                  outside the bounds.
     */
    private final void checkNumberOfMissionaries(int numberOfMissionaries) {
        if (numberOfMissionaries < 0) {
            throw new IllegalArgumentException(
                    "Negative number of missionaries (" + 
                    numberOfMissionaries + 
                    ").");
        }
        
        if (numberOfMissionaries > 
                gameConfiguration.getTotalNumberOfMissionaries()) {
            throw new IllegalArgumentException(
                    "Too much missionaries (" + 
                    numberOfMissionaries +
                    "). Must be at most " + 
                    gameConfiguration.getTotalNumberOfMissionaries() + 
                    ".");
        }
    }
    
    private final void checkNumberOfCannibals(int numberOfCannibals) {
        if (numberOfCannibals < 0) {
            throw new IllegalArgumentException(
                    "Negative number of cannibals (" + 
                    numberOfCannibals +
                    ").");
        }
        
        if (numberOfCannibals > gameConfiguration.getTotalNumberOfCannibals()) {
            throw new IllegalArgumentException(
                    "Too much cannibals (" + 
                    numberOfCannibals + 
                    "). Must be at most " + 
                    gameConfiguration.getTotalNumberOfCannibals() +
                    ").");
        }
    }
    
    @Override
    public List<StateNode> children() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}