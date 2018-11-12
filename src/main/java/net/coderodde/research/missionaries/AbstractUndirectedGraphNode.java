package net.coderodde.research.missionaries;

import java.util.List;

/**
 * This interface defines the API for undirected graphs.
 * 
 * @author Rodion "rodde" Efremov
 * @version 1.61
 * @param <N> the actual graph node type.
 */
public interface AbstractUndirectedGraphNode
<N extends AbstractUndirectedGraphNode<N>> {

    /**
     * Returns the children nodes of this node.
     * 
     * @return a list of child nodes.
     */
    public List<N> children();
}
