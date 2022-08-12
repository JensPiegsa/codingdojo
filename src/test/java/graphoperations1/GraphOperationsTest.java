package graphoperations1;

import static org.assertj.core.api.BDDAssertions.*;
import static org.junit.jupiter.api.Assertions.*;

import java.util.Set;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

/**
 * <a href="https://www.codewars.com/kata/5880e6e59785e5a52b000bd7/train/java">Codewars kata description</a>
 */
@DisplayName("GraphOperations")
class GraphOperationsTest {

	@Nested @DisplayName("provide a method to retrieve neighbours that")
	class Neighbours {

		@Test @DisplayName("returns empty set for graph with single vertex.")
		void returnsEmptySetForGraphWithSingleVertex() {
			
			final Graph graph = new Graph();
			final Vertex vertex = new Vertex();
			graph.addVertex(vertex);

			final Set<Vertex> neighbours = GraphOperations.getNeighbours(graph, vertex);

			then(neighbours).isNotNull();
			then(neighbours).isEmpty();
		}
		
		@Test @DisplayName("test")
		void test() {
			final Graph graph = new Graph();
			final Vertex v1 = new Vertex();
			final Vertex v2 = new Vertex();
			graph.addEdge(v1, v2);

			final Set<Vertex> neighbours = GraphOperations.getNeighbours(graph, v1);

			then(neighbours).contains(v2);
		}
	}
}