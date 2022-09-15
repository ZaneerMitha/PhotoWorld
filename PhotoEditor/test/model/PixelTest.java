package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

/**
 * A JUnit test for the model.Pixel class.
 */
public class PixelTest {

  private Posn zeroZero;
  private Posn zeroOne;
  private Posn zeroTwo;
  RGBColor black;
  RGBColor blue;
  RGBColor white;
  BasicPixel pixel1;
  BasicPixel pixel2;
  BasicPixel pixel3;

  /**
   * Initial pixel data.
   */
  @Before
  public void initData() {

    zeroZero = new Posn(0, 0);
    zeroOne = new Posn(0, 1);
    zeroTwo = new Posn(0, 2);
    black = new RGBColor(0, 0, 0);
    blue = new RGBColor(0, 0, 1);
    white = new RGBColor(1, 1, 1);
    pixel1 = new BasicPixel(zeroZero, black);
    pixel2 = new BasicPixel(zeroOne, blue);
    pixel3 = new BasicPixel(zeroTwo, white);

  }

  @Test
  public void testCorrectConstructor() {
    Posn p1 = new Posn(1,1);
    RGBColor c1 = new RGBColor(1,1,1);
    BasicPixel pix = new BasicPixel(p1, c1);

    assertEquals(1, pix.getRed());
    assertEquals(1, pix.getGreen());
    assertEquals(1, pix.getBlue());
  }

  @Test
  public void testInvalidConstructor() {
    try {
      Posn p1 = null;
      RGBColor c1 = new RGBColor(1,1,1);
      new BasicPixel(p1, c1);
      fail("Constructor did not throw an Illegal Argument Exception when it should.");
    } catch (IllegalArgumentException e) {
      assertEquals("Posn should not be null!", e.getMessage());
    }

    try {
      Posn p1 = new Posn(1,1);
      RGBColor c1 = null;
      new BasicPixel(p1, null);
      fail("Constructor did not throw an Illegal Argument Exception when it should.");
    } catch (IllegalArgumentException e) {
      assertEquals("Color should not be null!", e.getMessage());
    }
  }

  @Test
  public void testEquals() {

    BasicPixel pixel1Copy = new BasicPixel(zeroZero, black);

    assertTrue(pixel1.equals(pixel1Copy));
    assertTrue(pixel1.equals(pixel1));
    assertFalse(pixel1.equals(pixel2));

  }

  @Test
  public void testHashCode() {
    int hash1 = pixel1.hashCode();
    int hash2 = pixel2.hashCode();
    int hash3 = pixel3.hashCode();
    assertTrue(pixel1.hashCode() == hash1);
    assertFalse(hash1 == hash2);
    assertFalse(hash1 == hash3);
    assertTrue(pixel2.hashCode() == hash2);
    assertFalse(hash2 == hash3);
    assertTrue(pixel3.hashCode() == hash3);
  }

  @Test
  public void testGetRed() {
    assertEquals(0, pixel1.getRed());
    assertEquals(0, pixel2.getRed());
    assertEquals(1, pixel3.getRed());
    RGBColor color = new RGBColor(3, 5, 7);
    assertEquals(3, new BasicPixel(zeroZero, color).getRed());

  }

  @Test
  public void testGetGreen() {
    assertEquals(0, pixel1.getGreen());
    assertEquals(0, pixel2.getGreen());
    assertEquals(1, pixel3.getGreen());
    RGBColor color = new RGBColor(3, 5, 7);
    assertEquals(5, new BasicPixel(zeroZero, color).getGreen());
  }

  @Test
  public void testGetBlue() {
    assertEquals(0, pixel1.getBlue());
    assertEquals(1, pixel2.getBlue());
    assertEquals(1, pixel3.getBlue());
    RGBColor color = new RGBColor(3, 5, 7);
    assertEquals(7, new BasicPixel(zeroZero, color).getBlue());
  }

  @Test
  public void testGetPixelValue() {
    assertEquals(0, pixel1.getPixelValue());
    assertEquals(1, pixel2.getPixelValue());
    assertEquals(1, pixel3.getPixelValue());
    RGBColor color = new RGBColor(3, 5, 7);
    BasicPixel pixel = new BasicPixel(zeroZero, color);
    assertEquals(7, pixel.getPixelValue());
  }

  @Test
  public void testGetPixelIntensity() {
    assertEquals(0, pixel1.getPixelIntensity(), 0.1);
    assertEquals(0.333333, pixel2.getPixelIntensity(), 0.1);
    assertEquals(1, pixel3.getPixelIntensity(), 0.1);
    RGBColor color = new RGBColor(3, 5, 7);
    BasicPixel pixel = new BasicPixel(zeroZero, color);
    assertEquals(5, pixel.getPixelIntensity(), 0.1);
  }

  @Test
  public void testGetPixelLuma() {
    assertEquals(0, pixel1.getPixelLuma(), 0.1);
    assertEquals(0.0722, pixel2.getPixelLuma(), 0.1);
    assertEquals(1, pixel3.getPixelLuma(), 0.1);
    RGBColor color = new RGBColor(3, 5, 7);
    BasicPixel pixel = new BasicPixel(zeroZero, color);
    assertEquals(4.7192, pixel.getPixelLuma(), 0.1);
  }

  @Test
  public void testFlipHorizontal() {
    Posn zeroZeroXFlip = zeroZero.flipX(5);
    Posn zeroOneXFlip = zeroOne.flipX(4);
    Posn zeroTwoXFlip = zeroTwo.flipX(36);

    BasicPixel pixel1XFlip = new BasicPixel(zeroZeroXFlip, black);
    BasicPixel pixel2XFlip = new BasicPixel(zeroOneXFlip, blue);
    BasicPixel pixel3XFlip = new BasicPixel(zeroTwoXFlip, white);

    assertTrue(pixel1XFlip.equals(pixel1.flipHorizontal(5)));
    assertTrue(pixel2XFlip.equals(pixel2.flipHorizontal(4)));
    assertTrue(pixel3XFlip.equals(pixel3.flipHorizontal(36)));
  }

  @Test
  public void testFlipVertical() {
    Posn zeroZeroYFlip = zeroZero.flipY(5);
    Posn zeroOneYFlip = zeroOne.flipY(4);
    Posn zeroTwoYFlip = zeroTwo.flipY(34);

    BasicPixel pixel1YFlip = new BasicPixel(zeroZeroYFlip, black);
    BasicPixel pixel2YFlip = new BasicPixel(zeroOneYFlip, blue);
    BasicPixel pixel3YFlip = new BasicPixel(zeroTwoYFlip, white);

    assertTrue(pixel1YFlip.equals(pixel1.flipVertical(5)));
    assertTrue(pixel2YFlip.equals(pixel2.flipVertical(4)));
    assertTrue(pixel3YFlip.equals(pixel3.flipVertical(34)));
  }

  @Test
  public void testBrightenOrDarkenPixel() {
    assertEquals(pixel1.brightenOrDarkenPixel(1), new BasicPixel(zeroZero, white));
    assertEquals(pixel3.brightenOrDarkenPixel(-1), new BasicPixel(zeroTwo, black));
  }

  @Test
  public void testVisualize() {
    assertEquals(new BasicPixel(zeroZero, new RGBColor(0,0,0)), pixel1.greyscale("red"));
    assertEquals(new BasicPixel(zeroZero, new RGBColor(0,0,0)), pixel1.greyscale("green"));
    assertEquals(new BasicPixel(zeroZero, new RGBColor(0,0,0)), pixel1.greyscale("blue"));
    assertEquals(new BasicPixel(zeroZero, new RGBColor(0,0,0)), pixel1.greyscale("value"));
    assertEquals(new BasicPixel(zeroZero, new RGBColor(0,0,0)), pixel1.greyscale("luma"));
    assertEquals(new BasicPixel(zeroZero, new RGBColor(0,0,0)), pixel1.greyscale("intensity"));
    assertEquals(new BasicPixel(zeroOne, new RGBColor(0,0,0)), pixel2.greyscale("red"));

    assertEquals(new BasicPixel(zeroOne, new RGBColor(0,0,0)), pixel2.greyscale("green"));
    assertEquals(new BasicPixel(zeroOne, new RGBColor(1,1,1)), pixel2.greyscale("blue"));
    assertEquals(new BasicPixel(zeroOne, new RGBColor(1,1,1)), pixel2.greyscale("value"));
    assertEquals(new BasicPixel(zeroOne, new RGBColor(0,0,0)), pixel2.greyscale("luma"));
    assertEquals(new BasicPixel(zeroOne, new RGBColor(0,0,0)), pixel2.greyscale("intensity"));

    assertEquals(new BasicPixel(zeroTwo, new RGBColor(1,1,1)), pixel3.greyscale("red"));
    assertEquals(new BasicPixel(zeroTwo, new RGBColor(1,1,1)), pixel3.greyscale("green"));
    assertEquals(new BasicPixel(zeroTwo, new RGBColor(1,1,1)), pixel3.greyscale("blue"));
    assertEquals(new BasicPixel(zeroTwo, new RGBColor(1,1,1)), pixel3.greyscale("value"));
    assertEquals(new BasicPixel(zeroTwo, new RGBColor(1,1,1)), pixel3.greyscale("luma"));
    assertEquals(new BasicPixel(zeroTwo, new RGBColor(1,1,1)), pixel3.greyscale("intensity"));
  }
}