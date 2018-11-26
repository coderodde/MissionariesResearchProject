package net.coderodde.missionaries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * This class implements a game state of the Missionaries and Cannibals puzzle.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Nov 26, 2018)
 */
final class StateNode {
    
    private static int counter;
    
    static int getCounter() {
        return counter;
    }
    
    /**
     * The number of missionaries on the source bank. The number of missionaries
     * on the target bank is {@code gameParameters.getTotalNumber}
     */
    private final int numberOfMissionariesOnSourceBank;
    
    /**
     * The number of cannibals on the target bank. The number of cannibals on 
     * the target bank is {@code gameParameters.}
     */
    private final int numberOfCannibalsOnSourceBank;
    
    /**
     * Specifies the number of missionaries, cannibals and boat capacity.
     */
    private final GameParameters gameParameters;
    
    /**
     * Specifies the location of the boat.
     */
    private final BoatLocation boatLocation;
    
    /**
     * The list of all neighbor states.
     */
    private List<StateNode> neighbors;
    
    /**
     * Constructs a state.
     * 
     * @param numberOfMissionariesOnSourceBank the number of missionaries on the
     *                                         source bank. It is assumed that
     *                                         the number of missionaries on the
     *                                         target bank is {@code 
     *                                         gameParameters.getTotalNumberOfMiwsionaries
     *                                         - numberOfMissionariesOnSourceBank}.
     * @param numberOfCannibalsOnSourceBank    the number of cannibals on the 
     *                                         source bank. It is assumed that
     *                                         the number of cannibals on the
     *                                         target bank is {@code 
     *                                         gameParameters.getTotalNumberOfCannibals
     *                                         - numberOfCannibalsOnSourceBank}.
     * @param configuration                    Describes the three game 
     *                                         parameters: total number of
     *                                         missionaries, total number of
     *                                         cannibals, the boat capacity.
     * @param boatLocation 
     */
    StateNode(int numberOfMissionariesOnSourceBank,
              int numberOfCannibalsOnSourceBank,
              GameParameters configuration,
              BoatLocation boatLocation) {
        counter++;
        this.numberOfMissionariesOnSourceBank = 
                numberOfMissionariesOnSourceBank;
        
        this.numberOfCannibalsOnSourceBank = 
                numberOfCannibalsOnSourceBank;
        
        this.gameParameters = configuration;
        this.boatLocation = boatLocation;
    }
    
    @Override
    public boolean equals(Object o) {
        if (o == this) {
            return true;
        }
        
        if (o == null) {
            return false;
        }
        
        StateNode otherStateNode = (StateNode) o;
        
        if (!this.getClass().equals(otherStateNode.getClass())) {
            return false;
        }
        
        if (numberOfMissionariesOnSourceBank != 
                otherStateNode.numberOfMissionariesOnSourceBank) {
            return false;
        }
        
        if (numberOfCannibalsOnSourceBank != 
                otherStateNode.numberOfCannibalsOnSourceBank) {
            return false;
        }
        
        if (!gameParameters.equals(otherStateNode.gameParameters)) {
            return false;
        }
        
        return boatLocation == otherStateNode.boatLocation;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 89 * hash + this.numberOfMissionariesOnSourceBank;
        hash = 89 * hash + this.numberOfCannibalsOnSourceBank;
        hash = 89 * hash + Objects.hashCode(this.gameParameters);
        hash = 89 * hash + Objects.hashCode(this.boatLocation);
        return hash;
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        int missionaryFieldLength = 
                ("" + gameParameters.getTotalNumberOfMissionaries()).length();

        int cannibalFieldLength = 
                ("" + gameParameters.getTotalNumberOfCannibals()).length();

        // Situation at the source bank.
        sb.append(String.format("[m: %" + missionaryFieldLength + "d",
                  numberOfMissionariesOnSourceBank));

        sb.append(String.format(", c: %" + cannibalFieldLength + "d]",
                  numberOfCannibalsOnSourceBank));

        // Draw the boat location.
        switch (boatLocation) {
            case SOURCE_RIVER_BANK: {
                sb.append("v ~~~  ");
                break;
            }

            case TARGET_RIVER_BANK: {
                sb.append("  ~~~ v");
                break;
            }
        }

        // Situation at the destination bank.
        sb.append(String.format("[m: %" + missionaryFieldLength + "d",
                  gameParameters.getTotalNumberOfMissionaries() - 
                      numberOfMissionariesOnSourceBank));

        sb.append(String.format(", c: %" + cannibalFieldLength + "d]",
                  gameParameters.getTotalNumberOfCannibals() -
                      numberOfCannibalsOnSourceBank));

        return sb.toString();
    }

    
    boolean isTerminalState() {
        if (numberOfMissionariesOnSourceBank > 0 && 
            numberOfMissionariesOnSourceBank < numberOfCannibalsOnSourceBank) {
            return true;
        }
        
        int numberOfMissionariesOnTargetBank = 
                gameParameters.getTotalNumberOfMissionaries() -
                numberOfMissionariesOnSourceBank;
        
        int numberOfCannibalsOnTargetBank =
                gameParameters.getTotalNumberOfCannibals() -
                numberOfCannibalsOnSourceBank;
        
        return numberOfMissionariesOnTargetBank > 0 &&
               numberOfMissionariesOnTargetBank < numberOfCannibalsOnTargetBank;
    }
    
    private List<StateNode> buildNeighborsList() {
        neighbors = new ArrayList<>();
        BoatLocation currentBoatLocation = this.boatLocation;
        
        switch (currentBoatLocation) {
            case SOURCE_RIVER_BANK:
                return trySendFromSource();
                
            case TARGET_RIVER_BANK:
                return trySendFromTarget();
        }
        
        throw new IllegalStateException(
                "Unknown BoatLocation enumeration: " + currentBoatLocation);
    }
    
    private List<StateNode> trySendFromSource() {
        List<StateNode> neighborStates = new ArrayList<>();
        int boatCapacity = gameParameters.getBoatCapacity();
        int availableMissionaries = Math.min(numberOfMissionariesOnSourceBank,
                                             boatCapacity);
        
        for (int m = 0; m <= availableMissionaries; m++) {
            for (int c = ((m == 0) ? 1 : 0),
                 availableCannibals = Math.min(numberOfCannibalsOnSourceBank, 
                                               boatCapacity - m);
                 c <= availableCannibals;
                 c++) {
                StateNode stateNode =
                        new StateNode(numberOfMissionariesOnSourceBank - m,
                                      numberOfCannibalsOnSourceBank - c,
                                      gameParameters,
                                      BoatLocation.TARGET_RIVER_BANK);
                
                if (!stateNode.isTerminalState()) {
                    neighborStates.add(stateNode);
                }
            }
        }
        
        return neighborStates;
    }
    
    private List<StateNode> trySendFromTarget() {
        List<StateNode> neighborStates = new ArrayList<>();
        
        int boatCapacity = gameParameters.getBoatCapacity();
        
        int availableMissionaries
                = Math.min(boatCapacity,
                           gameParameters.getTotalNumberOfMissionaries() -
                                   numberOfMissionariesOnSourceBank);
        
        int availableCannibals
                = Math.min(boatCapacity,
                           gameParameters.getTotalNumberOfCannibals() -
                                   numberOfCannibalsOnSourceBank);
        
        for (int m = 0; m <= availableMissionaries; m++) {
            for (int c = ((m == 0) ? 1 : 0), 
                     cend = Math.min(availableCannibals, boatCapacity - m);
                 c <= availableCannibals;
                 c++) {
                StateNode stateNode =
                        new StateNode(numberOfMissionariesOnSourceBank + m,
                                      numberOfCannibalsOnSourceBank + c,
                                      gameParameters,
                                      BoatLocation.SOURCE_RIVER_BANK);
                
                if (!stateNode.isTerminalState()) {
                    neighborStates.add(stateNode);
                }
            }
        }
        
        return neighborStates;
    }
          
    
    List<StateNode> getNeighbors() {
        if (neighbors == null) {
            neighbors = buildNeighborsList();
        }
        
        return Collections.unmodifiableList(neighbors);
    }
    
    static StateNode getSourceState(GameParameters gameParameters) {
        return new StateNode(gameParameters.getTotalNumberOfMissionaries(),
                             gameParameters.getTotalNumberOfCannibals(),
                             gameParameters,
                             BoatLocation.SOURCE_RIVER_BANK);
    }
    
    static StateNode getTargetState(GameParameters gameParameters) {
        return new StateNode(0, 
                             0,
                             gameParameters,
                             BoatLocation.TARGET_RIVER_BANK);
    }
}
