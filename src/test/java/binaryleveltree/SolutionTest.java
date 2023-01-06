package binaryleveltree;

import org.assertj.core.api.BDDAssertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class SolutionTest {
    
    // Kata: https://www.codewars.com/kata/52bef5e3588c56132c0003bc

    @Test @DisplayName("returns empty list for null.")
    void returnsEmptyListForNull() {
        assertThat(Kata.treeByLevels(null)).isEmpty();
    }

    @Test @DisplayName("returns value of single node.")
    void returnsValueOfSingleNode() {
        assertThat(Kata.treeByLevels(new Node(null, null, 1))).containsExactly(1);
    }


    @Test @DisplayName("returns [1, 2] for two nodes.")
    void returns12ForTwoNodes() {
        assertThat(Kata.treeByLevels(new Node(new Node(null, null, 2), null, 1))).containsExactly(1, 2);
    }
    
    @Test @DisplayName("returns [1, 2, 3, 4] for four nodes.")
    void returns1234ForFourNodes() {
        //       1
        //      2 3
        //     4
        final Node n4 = new Node(null, null, 4);
        final Node n2 = new Node(n4, null, 2);
        final Node n3 = new Node(null, null, 3);
        final Node n1 = new Node(n2, n3, 1);
        assertThat(Kata.treeByLevels(n1)).containsExactly(1, 2, 3, 4);
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    @Test
    public void nullTest() {
      assertEquals(Arrays.asList(), Kata.treeByLevels(null));
    }
    
    @Test
    public void basicTest() {
      assertEquals(Arrays.asList(1,2,3,4,5,6), Kata.treeByLevels(new Node(new Node(null, new Node(null, null, 4), 2), new Node(new Node(null, null, 5), new Node(null, null, 6), 3), 1)));
    }
}