package patterngenerator;

import static org.junit.Assert.*;

import org.junit.Test;

public class PatternGeneratorTests {
  
  @Test
  public void shouldHandleParamLessThanZero() {
    String expected = "";
    String actual = PatternGenerator.patternGenerator(-1);
    System.out.println(actual);
    
  }
  @Test
  public void shouldMatchesPattern0() {
    String expected = "";
    String actual = PatternGenerator.patternGenerator(0);
    System.out.println(actual);
  }
  @Test
  public void shouldMatchesPattern1() {
    String expected = "x";
    String actual = PatternGenerator.patternGenerator(1);
    System.out.println(actual);
  }
  @Test
  public void shouldMatchesPattern2() {
    String expected = " x\nx x\n x";
    String actual = PatternGenerator.patternGenerator(2);
    System.out.println(actual);
  }
  @Test
  public void shouldMatchesPattern3() {
    String expected = "  x\n   x\nx o x\n x\n  x";
    String actual = PatternGenerator.patternGenerator(3);
    System.out.println(actual);
  }
  @Test
  public void shouldMatchesPattern4() {
    String expected = "   x\n    x\n   o x\nx o o x\n x o\n  x\n   x";
    String actual = PatternGenerator.patternGenerator(4);
    System.out.println(actual);
  }
  @Test
  public void shouldMatchesPattern5() {
    String expected = "    x\n     x\n    o x\n     o x\nx o x o x\n x o\n  x o\n   x\n    x";
    String actual = PatternGenerator.patternGenerator(5);
    System.out.println(actual);
  }
  @Test
  public void shouldMatchesPattern6() {
    String expected = "     x\n      x\n     o x\n      o x\n     x o x\nx o x x o x\n x o x\n  x o\n   x o\n    x\n     x";
    String actual = PatternGenerator.patternGenerator(6);
    System.out.println(actual);
  }
  @Test
  public void shouldMatchesPattern7() {
    String expected = "      x\n       x\n      o x\n       o x\n      x o x\n       x o x\nx o x o x o x\n x o x\n  x o x\n   x o\n    x o\n     x\n      x";
    String actual = PatternGenerator.patternGenerator(7);
    System.out.println(actual);
  }
  @Test
  public void shouldMatchesPattern8() {
    String expected = "       x\n        x\n       o x\n        o x\n       x o x\n        x o x\n       o x o x\nx o x o o x o x\n x o x o\n  x o x\n   x o x\n    x o\n     x o\n      x\n       x";
    String actual = PatternGenerator.patternGenerator(8);
    System.out.println(actual);
  }
  @Test
  public void shouldMatchesPattern9() {
    String expected = "        x\n         x\n        o x\n         o x\n        x o x\n         x o x\n        o x o x\n         o x o x\nx o x o x o x o x\n x o x o\n  x o x o\n   x o x\n    x o x\n     x o\n      x o\n       x\n        x";
    String actual = PatternGenerator.patternGenerator(9);
    System.out.println(actual);
  }
}