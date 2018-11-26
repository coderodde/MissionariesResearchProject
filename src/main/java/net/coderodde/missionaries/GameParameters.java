package net.coderodde.missionaries;

/**
 *
 * @author rodde
 */
final class GameParameters {
    
    private final int totalNumberOfMissionaries;
    private final int totalNumberOfCannibals;
    private final int boatCapacity;
    
    GameParameters(int totalNumberOfMissionaries,
                      int totalNumberOfCannibals,
                      int boatCapacity) {
        this.totalNumberOfMissionaries = totalNumberOfMissionaries;
        this.totalNumberOfCannibals = totalNumberOfCannibals;
        this.boatCapacity = boatCapacity;
    }
    
    int getTotalNumberOfMissionaries() {
        return totalNumberOfMissionaries;
    }
    
    int getTotalNumberOfCannibals() {
        return totalNumberOfCannibals;
    }
    
    int getBoatCapacity() {
        return boatCapacity;
    }
    
    @Override
    public int hashCode() {
        return (totalNumberOfMissionaries ^ totalNumberOfCannibals) 
                                          ^ boatCapacity;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        
        if (obj == null) {
            return false;
        }
        
        if (getClass() != obj.getClass()) {
            return false;
        }
        
        GameParameters other = (GameParameters) obj;
        
        if (this.totalNumberOfMissionaries != other.totalNumberOfMissionaries) {
            return false;
        }
        
        if (this.totalNumberOfCannibals != other.totalNumberOfCannibals) {
            return false;
        }
        
        if (this.boatCapacity != other.boatCapacity) {
            return false;
        }
        
        return true;
    }
}
