package net.coderodde.missionaries;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 * @author rodde
 */
final class BFS {
    
    static List<StateNode> search(StateNode source,
                                  StateNode target) {
        Map<StateNode, StateNode> parentMap = new HashMap<>();
        Deque<StateNode> queue = new ArrayDeque<>();
        
        parentMap.put(source, null);
        queue.addLast(source);
        
        while (!queue.isEmpty()) {
            StateNode current = queue.removeFirst();
            
            if (current.equals(target)) {
                return tracebackPath(target, parentMap);
            }
            
            for (StateNode child : current.getNeighbors()) {
                if (!parentMap.containsKey(child)) {
                    parentMap.put(child, current);
                    queue.addLast(child);
                }
            }
        }
        
        throw new IllegalStateException();
    }
    
    static List<StateNode> tracebackPath(StateNode targetNode,
                                         Map<StateNode, StateNode> parentMap) {
        List<StateNode> path = new ArrayList<>();
        StateNode current = targetNode;
        
        while (current != null) {
            path.add(current);
            current = parentMap.get(current);
        }
        
        Collections.<StateNode>reverse(path);
        return path;
    }
}
