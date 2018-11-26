package net.coderodde.research.missionaries.old;

import java.util.List;

/**
 *
 * @author rodde
 */
public interface AbstractUndirectedGraphNode
      <N extends AbstractUndirectedGraphNode<N>> {
    public List<N> children();
}
