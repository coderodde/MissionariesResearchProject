package net.coderodde.missionaries;

import java.util.ArrayList;
import java.util.List;

/**
 * This class implements an unweighted path in a state node.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Nov 26, 2018)
 */
public final class StateNodePath implements Comparable {
    
    private final List<StateNode> nodeList = new ArrayList<>();
    
    public StateNodePath(StateNode sourceStateNode) {
        nodeList.add(sourceStateNode);
    }
    
    private StateNodePath(StateNodePath stateNodePath, StateNode stateNode) {
        nodeList.addAll(stateNodePath.nodeList);
        nodeList.add(stateNode);
    }
    
    public StateNodePath append(StateNode stateNode) {
        return new StateNodePath(this, stateNode);
    }
    
    public StateNode getEndPoint() {
        return nodeList.get(nodeList.size() - 1);
    }
    
    public int getNumberOfNodes() {
        return nodeList.size();
    }

    @Override
    public int compareTo(Object o) {
        StateNodePath otherStateNodePath = (StateNodePath) o;
        return Integer.compare(nodeList.size(), 
                               otherStateNodePath.nodeList.size());
    }
    
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        
        for (StateNode stateNode : this.nodeList) {
            sb.append(stateNode.toString()).append("\n");
        }
        
        return sb.toString();
    }
}
