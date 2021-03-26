package pokerflush;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

// TODO: Replace examples and use TDD development by writing your own tests

public class PokerflushTest {
  @Test
  @Ignore
  public void BasicTests() {

    assertEquals(true, Pokerflush.CheckIfFlush(new String[]{"AS", "3S", "9S", "KS", "4S"}));
    assertEquals(false, Pokerflush.CheckIfFlush(new String[]{"AD", "4S", "7H", "KC", "5S"}));
    assertEquals(false, Pokerflush.CheckIfFlush(new String[]{"AD", "4S", "10H", "KC", "5S"}));
    assertEquals(true, Pokerflush.CheckIfFlush(new String[]{"QD", "4D", "10D", "KD", "5D"}));
  }

  @Test
  public void onlySpades() {
    assertEquals(true, Pokerflush.CheckIfFlush(new String[]{"AS", "3S", "9S", "KS", "4S"}));
  }

  @Test
  public void noFlush1() {
    assertEquals(false, Pokerflush.CheckIfFlush(new String[]{"AD", "4S", "7H", "KC", "5S"}));
  }

  @Test
  public void noFlush2() {
    assertEquals(false, Pokerflush.CheckIfFlush(new String[]{"AD", "4S", "10H", "KC", "5S"}));
  }

  @Test
  public void onlyDiamonds() {
    assertEquals(true, Pokerflush.CheckIfFlush(new String[]{"QD", "4D", "10D", "KD", "5D"}));
  }
  
}