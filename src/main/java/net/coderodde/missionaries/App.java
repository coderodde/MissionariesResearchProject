package net.coderodde.missionaries;

import java.util.Arrays;
import java.util.List;

/**
 *
 * @author rodde
 */
public class App {
    
    public static void main(String[] args) {
        GameParameters gameParameters = new GameParameters(4, 4, 3);
        StateNode sourceStateNode = StateNode.getSourceState(gameParameters);
        StateNode targetStateNode = StateNode.getTargetState(gameParameters);
        
        long startTime = System.currentTimeMillis();
        List<StateNode> solutionPath = BFS.search(sourceStateNode,
                                                  targetStateNode);
        long endTime = System.currentTimeMillis();
        
        System.out.println("BFS in " + (endTime - startTime) + " ms. " +
                           "States: " + solutionPath.size());
        
        for (StateNode stateNode : solutionPath) {
            System.out.println(stateNode);
        }
    }
}
