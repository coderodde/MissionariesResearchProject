package net.coderodde.research.missionaries;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import static net.coderodde.research.missionaries.Utilities.checkIntNotLess;
import static net.coderodde.research.missionaries.Utilities.checkNotNegative;

/**
 * This class implements a state of the "Cannibals and Missionaries" puzzle. As
 * crossing a river involves two banks, {@code missionaries} denotes the amount
 * of missionaries on the source bank, and
 * {@code totalMissionaries - missionaries} is the amount of missionaries on the
 * target bank. Same arithmetics applies to cannibals.
 *
 * @author Rodion "rodde" Efremov
 * @version 1.6
 */
public class StateNode implements AbstractUndirectedGraphNode<StateNode> {

    private static int counter;

    /**
     * This enumeration enumerates all possible boat locations.
     */
    public enum BoatLocation {

        /**
         * The boat location where all figures start.
         */
        SOURCE_BANK,

        /**
         * The boat location all figures want to reach.
         */
        TARGET_BANK
    }

    /**
     * The amount of missionaries at the source bank.
     */
    private final int missionaries;

    /**
     * The amount of cannibals at the source bank.
     */
    private final int cannibals;

    /**
     * The game configuration object.
     */
    private final GameConfiguration configuration;

    /**
     * The location of the boat.
     */
    private final BoatLocation boatLocation;

    /**
     * Constructs this state.
     *
     * @param missionaries      the amount of missionaries at a bank.
     * @param cannibals         the amount of cannibals at the same ban.
     * @param totalMissionaries total amount of missionaries.
     * @param totalCannibals    total amount of cannibals.
     * @param boatCapacity      total amount of places in the boat.
     * @param boatLocation      the location of the boat.
     */
    public StateNode(int missionaries,
                     int cannibals,
                     int totalMissionaries,
                     int totalCannibals,
                     int boatCapacity,
                     BoatLocation boatLocation) {
        Objects.requireNonNull(boatLocation, "Boat location is null.");
        checkMissionaryCount(missionaries, totalMissionaries);
        checkCannibalCount(cannibals, totalCannibals);

        this.configuration = new GameConfiguration(totalMissionaries,
                                                   totalCannibals,
                                                   boatCapacity);
        this.boatLocation = boatLocation;
        this.missionaries = missionaries;
        this.cannibals = cannibals;

        StateNode.counter++;
    }

    /**
     * A constructor for child generation.
     * 
     * @param missionaries  the amount of missionaries at the source bank.
     * @param cannibals     the amount of cannibals at the source bank.
     * @param boatLocation  the boat location.
     * @param configuration the configuration object.
     */
    StateNode(int missionaries,
              int cannibals,
              BoatLocation boatLocation,
              GameConfiguration configuration) {
        this.missionaries = missionaries;
        this.cannibals = cannibals;
        this.boatLocation = boatLocation;
        this.configuration = configuration;

        StateNode.counter++;
    }

    /**
     * Creates the source state node.
     *
     * @param totalMissionaries the total amount of missionaries.
     * @param totalCannibals    the total amount of cannibals.
     * @param boatCapacity      the total amount of places in the boat.
     * @return the initial state node.
     */
    public static StateNode getInitialStateNode(int totalMissionaries,
                                                int totalCannibals,
                                                int boatCapacity) {
        return new StateNode(totalMissionaries,
                             totalCannibals,
                             totalMissionaries,
                             totalCannibals,
                             boatCapacity,
                             BoatLocation.SOURCE_BANK);
    }

    /**
     * Returns the amount of state objects constructed.
     * 
     * @return the amount of state objects.
     */
    public static int getCounter() {
        return counter;
    }

    /**
     * Checks whether this state encodes a solution state, in which all figures
     * are safely at the target bank.
     *
     * @return {@code true} if this state is a solution state.
     */
    public boolean isSolutionState() {
        return boatLocation == BoatLocation.TARGET_BANK
                && missionaries == 0
                && cannibals == 0;
    }

    /**
     * Checks whether this state is terminal, which is the case whenever at some
     * bank cannibals outnumber missionaries.
     *
     * @return {@code true} if this state is terminal.
     */
    public boolean isTerminalState() {
        if (missionaries > 0 && missionaries < cannibals) {
            // At the source bank, cannibals outnumber missionaries. Game over.
            return true;
        }

        int missionariesAtTargetBank = configuration.getTotalMissionaries() -
                                       missionaries;
        int cannibalsAtTargetBank = configuration.getTotalCannibals() -
                                    cannibals;

        if (missionariesAtTargetBank > 0
                && missionariesAtTargetBank < cannibalsAtTargetBank) {
            // At the target bank, cannibals outnumber missionaries. Game over.
            return true;
        }

        return false;
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        int missionaryFieldLength = ("" + configuration.getTotalMissionaries())
                                    .length();

        int cannibalFieldLength = ("" + configuration.getTotalCannibals())
                                  .length();

        // Situation at the source bank.
        sb.append(String.format("[m: %" + missionaryFieldLength + "d",
                missionaries));

        sb.append(String.format(", c: %" + cannibalFieldLength + "d]",
                cannibals));

        // Draw the boat location.
        switch (boatLocation) {
            case SOURCE_BANK: {
                sb.append("v ~~~  ");
                break;
            }

            case TARGET_BANK: {
                sb.append("  ~~~ v");
                break;
            }
        }

        // Situation at the destination bank.
        sb.append(String.format("[m: %" + missionaryFieldLength + "d",
                configuration.getTotalMissionaries() - missionaries));

        sb.append(String.format(", c: %" + cannibalFieldLength + "d]",
                configuration.getTotalCannibals() - cannibals));

        return sb.toString();
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public boolean equals(Object o) {
        if (!(o instanceof StateNode)) {
            return false;
        }

        StateNode other = (StateNode) o;
        return missionaries == other.missionaries
                && cannibals == other.cannibals
                && boatLocation == other.boatLocation
                && configuration.equals(other.configuration);
    }

    /**
     * {@inheritDoc }
     */
    @Override
    public int hashCode() {
        // Generated by NetBeans.
        int hash = 3;
        hash = 17 * hash + this.missionaries;
        hash = 17 * hash + this.cannibals;
        hash = 17 * hash + Objects.hashCode(this.configuration);
        hash = 17 * hash + Objects.hashCode(this.boatLocation);
        return hash;
    }

    /**
     * {@inheritDoc } 
     */
    @Override
    public List<StateNode> children() {
        if (isTerminalState()) {
            return Collections.<StateNode>emptyList();
        }

        List<StateNode> ret = new ArrayList<>();

        switch (boatLocation) {
            case SOURCE_BANK: {
                trySendFromSourceBank(ret);
                break;
            }

            case TARGET_BANK: {
                trySendFromTargetBank(ret);
                break;
            }
        }

        return ret;
    }

    // Attempts to send some figures from the source bank to the target bank.
    private void trySendFromSourceBank(List<StateNode> list) {
        int boatCapacity = configuration.getBoatCapacity();
        int availableMissionaries = Math.min(missionaries, boatCapacity);

        for (int m = 0; m <= availableMissionaries; ++m) {
            for (int c = ((m == 0) ? 1 : 0), 
                    availableCannibals = Math.min(cannibals, boatCapacity - m); 
                    c <= availableCannibals; 
                    ++c) {
                list.add(new StateNode(missionaries - m,
                                       cannibals - c,
                                       BoatLocation.TARGET_BANK,
                                       configuration));
            }
        }
    }

    // Attempts to send some figures from the target bank to the source bank.
    private void trySendFromTargetBank(List<StateNode> list) {
        int boatCapacity = configuration.getBoatCapacity();

        int availableMissionaries
                = Math.min(configuration.getTotalMissionaries() - missionaries, 
                           boatCapacity);

        int availableCannibals
                = Math.min(configuration.getTotalCannibals() - cannibals, 
                           boatCapacity);

        for (int m = 0; m <= availableMissionaries; ++m) {
            for (int c = ((m == 0) ? 1 : 0), 
                    cend = Math.min(availableCannibals, boatCapacity - m); 
                    c <= availableCannibals; 
                    ++c) {
                    list.add(new StateNode(missionaries + m,
                                           cannibals + c,
                                           BoatLocation.SOURCE_BANK,
                                           configuration));
            }
        }
    }

    /**
     * Checks that missionary count is in order.
     *
     * @param missionaries the amount of missionaries at the source bank.
     * @param totalMissionaries total amount of missionaries in the game.
     */
    private static void checkMissionaryCount(int missionaries,
                                             int totalMissionaries) {
        checkNotNegative(missionaries,
                         "Negative amount of missionaries: " + missionaries);
        checkIntNotLess(totalMissionaries,
                        missionaries,
                        "Missionaries at a bank (" + missionaries + "), " +
                        "missionaries in total (" + totalMissionaries + ").");
    }

    /**
     * Checks that cannibal count is in order.
     *
     * @param cannibals the amount of cannibals at the source bank.
     * @param totalCannibals total amount of cannibals in the game.
     */
    private static void checkCannibalCount(int cannibals,
                                           int totalCannibals) {
        checkNotNegative(cannibals,
                         "Negative amount of cannibals: " + cannibals);
        checkIntNotLess(totalCannibals,
                        cannibals,
                        "Cannibals at a bank (" + cannibals + "), " +
                        "cannibals in total (" + totalCannibals + ").");
    }

}