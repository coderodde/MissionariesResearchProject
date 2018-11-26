package net.coderodde.missionaries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;
import java.util.Queue;

/**
 *
 * @author Rodion "rodde" Efremov
 * @version 1.6 (Nov 26, 2018)
 */
public final class KShortestPathFinder {
    
    public static List<StateNodePath> search(StateNode sourceStateNode,
                                             StateNode targetStateNode,
                                             int requestedPathLength) {
        List<StateNodePath> paths = new ArrayList<>();
        Map<StateNode, Integer> countMap = new HashMap<>();
        Queue<StateNodePath> pathHeap = new PriorityQueue<>();
        pathHeap.add(new StateNodePath(sourceStateNode));
        
        while (!pathHeap.isEmpty()) {
            StateNodePath currentPath = pathHeap.remove();
            StateNode endNode = currentPath.getEndPoint();
            
            if (currentPath.getNumberOfNodes() > requestedPathLength) {
                return paths;
            }
            
            if (endNode.equals(targetStateNode)) {
                paths.add(currentPath);
            }
            
            for (StateNode child : endNode.getNeighbors()) {
                StateNodePath path = currentPath.append(child);
                pathHeap.add(path);
            }
        }
        
        throw new IllegalStateException();
    }
    
    private KShortestPathFinder() {
        
    }
}
