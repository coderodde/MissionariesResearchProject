package net.coderodde.research.missionaries;

import java.util.List;

/**
 *
 * @author rodde
 */
public interface AbstractUndirectedGraphNode
      <N extends AbstractUndirectedGraphNode<N>> {
    public List<N> children();
}
