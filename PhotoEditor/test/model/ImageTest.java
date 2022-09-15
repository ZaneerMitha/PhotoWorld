package model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * A JUnit test for the model.BasicImage class.
 */
public class ImageTest {
  BasicImage image;

  Posn zeroZero;
  Posn zeroOne;
  Posn zeroTwo;
  Posn oneZero;
  Posn oneOne;
  Posn oneTwo;
  Posn twoZero;
  Posn twoOne;
  Posn twoTwo;

  BasicPixel one;
  BasicPixel two;
  BasicPixel three;
  BasicPixel four;
  BasicPixel five;
  BasicPixel six;
  BasicPixel seven;
  BasicPixel eight;
  BasicPixel nine;

  /**
   * Initial image data.
   */
  @Before
  public void initData() {
    RGBColor color = new RGBColor(0,0,0);
    zeroZero = new Posn(0,0);
    zeroOne = new Posn(0,1);
    zeroTwo = new Posn(0,2);
    oneZero = new Posn(1,0);
    oneOne = new Posn(1,1);
    oneTwo = new Posn(1,2);
    twoZero = new Posn(2,0);
    twoOne = new Posn(2,1);
    twoTwo = new Posn(2,2);


    one = new BasicPixel(zeroZero, color);
    two = new BasicPixel(zeroOne, color);
    three = new BasicPixel(zeroTwo, color);
    four = new BasicPixel(oneZero, color);
    five = new BasicPixel(oneOne, color);
    six = new BasicPixel(oneTwo, color);
    seven = new BasicPixel(twoZero, color);
    eight = new BasicPixel(twoOne, color);
    nine = new BasicPixel(twoTwo, color);

    ArrayList<ArrayList<BasicPixel>> pixels = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOne = new ArrayList<BasicPixel>();
    rowOne.add(one);
    rowOne.add(two);
    rowOne.add(three);

    ArrayList<BasicPixel> rowTwo = new ArrayList<BasicPixel>();
    rowTwo.add(four);
    rowTwo.add(five);
    rowTwo.add(six);

    ArrayList<BasicPixel> rowThree = new ArrayList<BasicPixel>();
    rowThree.add(seven);
    rowThree.add(eight);
    rowThree.add(nine);

    pixels.add(rowOne);
    pixels.add(rowTwo);
    pixels.add(rowThree);

    image = new BasicImage(pixels);

  }

  @Test
  public void testCorrectConstructor() {
    RGBColor color = new RGBColor(0,0,0);
    zeroZero = new Posn(0,0);
    zeroOne = new Posn(0,1);
    zeroTwo = new Posn(0,2);
    oneZero = new Posn(1,0);
    oneOne = new Posn(1,1);
    oneTwo = new Posn(1,2);
    twoZero = new Posn(2,0);
    twoOne = new Posn(2,1);
    twoTwo = new Posn(2,2);


    one = new BasicPixel(zeroZero, color);
    two = new BasicPixel(zeroOne, color);
    three = new BasicPixel(zeroTwo, color);
    four = new BasicPixel(oneZero, color);
    five = new BasicPixel(oneOne, color);
    six = new BasicPixel(oneTwo, color);
    seven = new BasicPixel(twoZero, color);
    eight = new BasicPixel(twoOne, color);
    nine = new BasicPixel(twoTwo, color);

    ArrayList<ArrayList<BasicPixel>> pixels = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOne = new ArrayList<BasicPixel>();
    rowOne.add(one);
    rowOne.add(two);
    rowOne.add(three);

    ArrayList<BasicPixel> rowTwo = new ArrayList<BasicPixel>();
    rowTwo.add(four);
    rowTwo.add(five);
    rowTwo.add(six);

    ArrayList<BasicPixel> rowThree = new ArrayList<BasicPixel>();
    rowThree.add(seven);
    rowThree.add(eight);
    rowThree.add(nine);

    pixels.add(rowOne);
    pixels.add(rowTwo);
    pixels.add(rowThree);

    image = new BasicImage(pixels);

    assertEquals(one, image.getPixelAt(0,0));
    assertEquals(two, image.getPixelAt(0,1));
    assertEquals(three, image.getPixelAt(0,2));
    assertEquals(four, image.getPixelAt(1,0));
    assertEquals(five, image.getPixelAt(1,1));
    assertEquals(six, image.getPixelAt(1,2));
    assertEquals(seven, image.getPixelAt(2,0));
    assertEquals(eight, image.getPixelAt(2,1));
    assertEquals(nine, image.getPixelAt(2,2));
  }

  @Test
  public void testInvalidConstructor() {

    ArrayList<ArrayList<BasicPixel>> pixels = null;

    try {
      new BasicImage(pixels);
      fail("Constructor did not throw an Illegal Argument Exception when it should.");
    } catch (IllegalArgumentException e) {
      assertEquals("List of pixels should not be null!", e.getMessage());
    }
  }

  @Test
  public void testEquals() {
    RGBColor color = new RGBColor(1,1,1);
    one = new BasicPixel(zeroZero, color);
    two = new BasicPixel(zeroOne, color);
    three = new BasicPixel(zeroTwo, color);
    four = new BasicPixel(oneZero, color);
    five = new BasicPixel(oneOne, color);
    six = new BasicPixel(oneTwo, color);
    seven = new BasicPixel(twoZero, color);
    eight = new BasicPixel(twoOne, color);
    nine = new BasicPixel(twoTwo, color);

    ArrayList<ArrayList<BasicPixel>> pixels = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOne = new ArrayList<BasicPixel>();
    rowOne.add(one);
    rowOne.add(two);
    rowOne.add(three);

    ArrayList<BasicPixel> rowTwo = new ArrayList<BasicPixel>();
    rowTwo.add(four);
    rowTwo.add(five);
    rowTwo.add(six);

    ArrayList<BasicPixel> rowThree = new ArrayList<BasicPixel>();
    rowThree.add(seven);
    rowThree.add(eight);
    rowThree.add(nine);

    pixels.add(rowOne);
    pixels.add(rowTwo);
    pixels.add(rowThree);
    BasicImage image2 = new BasicImage(pixels);

    assertTrue(image.equals(image));
    assertFalse(image.equals(image2));
    assertTrue(image2.equals(image2));
  }

  @Test
  public void testHashCode() {
    int hash = image.hashCode();
    int hashCopy = image.hashCode();
    assertTrue(image.hashCode() == hash);
    assertTrue(hash == hashCopy);
  }

  @Test
  public void testGetPixelAt() {
    assertTrue(image.getPixelAt(0,0).equals(one));
    assertTrue(image.getPixelAt(0,1).equals(two));
    assertTrue(image.getPixelAt(0,2).equals(three));
    assertTrue(image.getPixelAt(1,0).equals(four));
    assertTrue(image.getPixelAt(1,1).equals(five));
    assertTrue(image.getPixelAt(1,2).equals(six));
    assertTrue(image.getPixelAt(2,0).equals(seven));
    assertTrue(image.getPixelAt(2,1).equals(eight));
    assertTrue(image.getPixelAt(2,2).equals(nine));
  }

  @Test
  public void testGetWidth() {
    assertEquals(3, image.getWidth());
  }

  @Test
  public void testGetHeight() {
    assertEquals(3, image.getHeight());
  }

}
