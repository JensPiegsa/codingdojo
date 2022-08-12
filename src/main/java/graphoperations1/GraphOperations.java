package graphoperations1;

import java.util.HashSet;
import java.util.Set;

public class GraphOperations {

	private GraphOperations() {
	}

	public static Set<Vertex> getNeighbours(final Graph graph, final Vertex vertex) {
		
		final var neighbours = new HashSet<Vertex>();
		for (final Edge edge : graph.getEdges()) {
			if (edge.getV1().equals(vertex)) {
				neighbours.add(edge.getV2());
			} else if (edge.getV2().equals(vertex)) {
				neighbours.add(edge.getV1());
			}
		}

		return neighbours;
	}
}
