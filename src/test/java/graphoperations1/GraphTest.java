package graphoperations1;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Set;

public class GraphTest {
    Graph exampleGraph;
    Vertex[] V = new Vertex[]{new Vertex(), new Vertex(), new Vertex(), new Vertex(), new Vertex(), new Vertex()};

    @BeforeEach
    public void setUpSmallExampleGraph(){
        exampleGraph = new Graph();
        /*
        *      V[2] - V[0] - V[3] - V[4]    V[5]
        *              |       |
        *             V[1] - - -
        */
        exampleGraph.addEdges(V[0], V[1], V[0], V[2], V[0], V[3], V[1], V[3], V[3], V[4]);
        exampleGraph.addVertex(V[5]);
    }

    @Test
    public void testNeighboursExample(){
        Set<Vertex> actualNeighbours;

        actualNeighbours = GraphOperations.getNeighbours(exampleGraph, V[0]);
        assertEquals(3, actualNeighbours.size());
        assertTrue(actualNeighbours.contains(V[1]));
        assertTrue(actualNeighbours.contains(V[2]));
        assertTrue(actualNeighbours.contains(V[3]));

        actualNeighbours = GraphOperations.getNeighbours(exampleGraph, V[1]);
        assertEquals(2, actualNeighbours.size());
        assertTrue(actualNeighbours.contains(V[0]));
        assertTrue(actualNeighbours.contains(V[3]));

        actualNeighbours = GraphOperations.getNeighbours(exampleGraph, V[2]);
        assertEquals(1, actualNeighbours.size());
        assertTrue(actualNeighbours.contains(V[0]));

        actualNeighbours = GraphOperations.getNeighbours(exampleGraph, V[3]);
        assertEquals(3, actualNeighbours.size());
        assertTrue(actualNeighbours.contains(V[0]));
        assertTrue(actualNeighbours.contains(V[1]));
        assertTrue(actualNeighbours.contains(V[4]));

        actualNeighbours = GraphOperations.getNeighbours(exampleGraph, V[4]);
        assertEquals(1, actualNeighbours.size());
        assertTrue(actualNeighbours.contains(V[3]));

        actualNeighbours = GraphOperations.getNeighbours(exampleGraph, V[5]);
        assertEquals(0, actualNeighbours.size());
    }
}
