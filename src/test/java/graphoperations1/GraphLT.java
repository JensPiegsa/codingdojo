package graphoperations1;

import static org.assertj.core.api.BDDAssertions.then;

import java.util.Iterator;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("A Graph (Learning Test)")
public class GraphLT {

	@Test @DisplayName("implicitly adds vertices of edge.")
	void implicitlyAddsVerticesOfEdge() {
		// given
		Graph graph = new Graph();
		Vertex v1 = new Vertex();
		Vertex v2 = new Vertex();

		// when
		graph.addEdge(v1, v2);

		// then
		then(graph.getVertices()).contains(v1, v2);
		final Iterator<Vertex> iterator = graph.getVertices().iterator();
		then(iterator.next()).isIn(v1, v2);
		then(iterator.next()).isIn(v1, v2);
	}

}
