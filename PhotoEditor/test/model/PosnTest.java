package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * A JUnit test for the model.Posn class.
 */
public class PosnTest {


  private Posn zeroZero;
  private Posn zeroZero2;
  private Posn zeroTwo;

  /**
   * Initial position data.
   */
  @Before
  public void initData() {

    zeroZero = new Posn(0,0);
    zeroZero2 = new Posn(0,0);
    zeroTwo = new Posn(0,2);
  }

  @Test
  public void testCorrectConstructor() {
    Posn p1 = new Posn(1,2);
    Posn p2 = new Posn(1,3);
    Posn p3 = new Posn(1,4);

    assertEquals(1, p1.getX());
    assertEquals(2, p1.getY());

    assertEquals(1, p2.getX());
    assertEquals(3, p2.getY());

    assertEquals(1, p3.getX());
    assertEquals(4, p3.getY());
  }

  @Test
  public void testInvalidConstructor() {
    try {
      new Posn(-1,2);
      fail("Constructor did not throw an Illegal Argument Exception when it should.");
    } catch (IllegalArgumentException e) {
      assertEquals("X should not be negative!", e.getMessage());
    }

    try {
      new Posn(1,-2);
      fail("Constructor did not throw an Illegal Argument Exception when it should.");
    } catch (IllegalArgumentException e) {
      assertEquals("Y should not be negative!", e.getMessage());
    }
  }

  @Test
  public void testEquals() {

    assertTrue(zeroZero.equals(zeroZero));
    assertTrue(zeroZero.equals(zeroZero2));
    assertFalse(zeroZero.equals(zeroTwo));
  }

  @Test
  public void testHashCode() {
    int hash1 = zeroZero.hashCode();
    int hash2 = zeroTwo.hashCode();

    assertTrue(zeroZero.hashCode() == hash1);
    assertTrue(zeroZero.hashCode() == zeroZero2.hashCode());
    assertTrue(zeroTwo.hashCode() == hash2);
    assertFalse(hash1 == hash2);
  }

  @Test
  public void getX() {
    assertEquals(0,zeroZero.getX());
    assertEquals(0,zeroZero2.getX());
    assertEquals(0,zeroTwo.getX());
  }

  @Test
  public void getY() {
    assertEquals(0,zeroZero.getY());
    assertEquals(0,zeroZero2.getY());
    assertEquals(2,zeroTwo.getY());
  }

  @Test
  public void flipX() {

    Posn zeroZeroXFlip = zeroZero.flipX(5);
    Posn zeroZero2XFlip = zeroZero2.flipX(4);
    Posn zeroTwoXFlip = zeroTwo.flipX(36);

    assertEquals(5, zeroZeroXFlip.getX());
    assertEquals(4, zeroZero2XFlip.getX());
    assertEquals(36, zeroTwoXFlip.getX());
  }

  @Test
  public void flipY() {

    Posn zeroZeroYFlip = zeroZero.flipY(5);
    Posn zeroZero2YFlip = zeroZero2.flipY(4);
    Posn zeroTwoYFlip = zeroTwo.flipY(36);

    assertEquals(5, zeroZeroYFlip.getY());
    assertEquals(4, zeroZero2YFlip.getY());
    assertEquals(34, zeroTwoYFlip.getY());

  }
}