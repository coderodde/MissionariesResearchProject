package net.coderodde.missionaries;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author rodde
 */
public class App {
    
    public static void main(String[] args) {
        GameParameters gameParameters = new GameParameters(3, 3, 2);
        StateNode sourceStateNode = StateNode.getSourceState(gameParameters);
        StateNode targetStateNode = StateNode.getTargetState(gameParameters);
        
        long startTime = System.currentTimeMillis();
        List<StateNode> solutionPath = BFS.search(sourceStateNode,
                                                  targetStateNode);
        long endTime = System.currentTimeMillis();
        
        System.out.println("BFS in " + (endTime - startTime) + " ms. " +
                           "States: " + solutionPath.size());
        
        System.out.println("Graph size: " + StateNode.getCounter());
        
        int optimalPathLength = solutionPath.size();
        
        startTime = System.currentTimeMillis();
        List<StateNodePath> optimalPaths =
                KShortestPathFinder.search(sourceStateNode, 
                                           targetStateNode, 
                                           optimalPathLength);
        endTime = System.currentTimeMillis();
        
        System.out.println(
                "Found " + 
                        optimalPaths.size() + 
                        " shortest paths in " +
                        (endTime - startTime) + 
                        " milliseconds.");
        
//        for (StateNodePath stateNodePath : optimalPaths) {
//            System.out.println(stateNodePath);
//        }
        
//        for (StateNode stateNode : solutionPath) {
//            System.out.println(stateNode);
//        }
    }
    
    private static void produceData() {
        for (int m = 1; m <= 10; m++) {
            
        }
    }
}
