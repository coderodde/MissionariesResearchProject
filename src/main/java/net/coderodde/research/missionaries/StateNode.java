package net.coderodde.research.missionaries;

import java.util.List;

/**
 * This class implements a state of the <a href="https://en.wikipedia.org/wiki/Missionaries_and_cannibals_problem">Missionaries and Cannibals problem</a>.
 * As crossing a river involves two banks, {@code missionaries} 
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Nov 12, 2018)
 */
public final class StateNode implements AbstractUndirectedGraphNode<StateNode> {

    /**
     * The number of missionaries on the source bank.
     */
    private final int numberOfMissionaries;
    
    /**
     * The number of cannibals on the source bank.
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
    
    public StateNode(final int missionaries,
                     final int cannibals,
                     final BoatLocation boatLocation,
                     final GameConfiguration gameConfiguration) {
        
    }
    
    @Override
    public List<StateNode> children() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}