package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

/**
 * A JUnit test for the model.Color class.
 */
public class ColorTest {

  RGBColor black;
  RGBColor blue;
  RGBColor white;

  /**
   * Initial color data.
   */
  @Before
  public void initData() {
    black = new RGBColor(0,0,0);
    blue = new RGBColor(0,0,1);
    white = new RGBColor(1,1,1);
  }

  @Test
  public void testCorrectConstruct() {
    RGBColor color1 = new RGBColor(9,8,2);
    RGBColor color2 = new RGBColor(10,5,4);
    RGBColor color3 = new RGBColor(4,2,4);

    assertEquals(9, color1.getRedValue());
    assertEquals(8, color1.getGreenValue());
    assertEquals(2, color1.getBlueValue());

    assertEquals(10, color2.getRedValue());
    assertEquals(5, color2.getGreenValue());
    assertEquals(4, color2.getBlueValue());

    assertEquals(4, color3.getRedValue());
    assertEquals(2, color3.getGreenValue());
    assertEquals(4, color3.getBlueValue());
  }

  @Test
  public void testEquals() {

    RGBColor black2 = new RGBColor(0,0,0);

    assertFalse(black.equals(white));
    assertTrue(black.equals(black));
    assertTrue(black.equals(black2));
  }

  @Test
  public void testGetValue() {
    assertEquals(0,black.getValue());
    assertEquals(1,blue.getValue());
    assertEquals(1, white.getValue());
  }

  @Test
  public void testGetIntensity() {
    assertEquals(0,black.getIntensity(), 0.0);
    assertEquals(0.33333333333333333,blue.getIntensity(), 0.0);
    assertEquals(1, white.getIntensity(), 0.0);
  }

  @Test
  public void getLuma() {
    assertEquals(0.0,black.getLuma(), 0.0);
    assertEquals(0.0722,blue.getLuma(), 0.0);
    assertEquals(1, white.getLuma(), 0.0);
  }

  @Test
  public void testGetRedValue() {
    assertEquals(0,  black.getRedValue());
    assertEquals(0, blue.getRedValue());
    assertEquals(1, white.getRedValue());
  }

  @Test
  public void testGetGreenValue() {
    assertEquals(0, black.getGreenValue());
    assertEquals(0, blue.getGreenValue());
    assertEquals(1, white.getGreenValue());
  }

  @Test
  public void testGetBlueValue() {
    assertEquals(0, black.getBlueValue());
    assertEquals(1, blue.getBlueValue());
    assertEquals(1, white.getBlueValue());
  }

  @Test
  public void testBrightenOrDarkenColor() {
    assertEquals(black.brightenOrDarkenColor(1), white);
    assertEquals(white.brightenOrDarkenColor(-1), black);
  }

  @Test
  public void testGreyscale() {
    assertEquals(new RGBColor(0,0,0), black.greyscale("red"));
    assertEquals(new RGBColor(0,0,0), black.greyscale("green"));
    assertEquals(new RGBColor(0,0,0), black.greyscale("blue"));
    assertEquals(new RGBColor(0,0,0), black.greyscale("value"));
    assertEquals(new RGBColor(0,0,0), black.greyscale("luma"));
    assertEquals(new RGBColor(0,0,0), black.greyscale("intensity"));

    assertEquals(new RGBColor(0,0,0), blue.greyscale("red"));
    assertEquals(new RGBColor(0,0,0), blue.greyscale("green"));
    assertEquals(new RGBColor(1,1,1), blue.greyscale("blue"));
    assertEquals(new RGBColor(1,1,1), blue.greyscale("value"));
    assertEquals(new RGBColor(0,0,0), blue.greyscale("luma"));
    assertEquals(new RGBColor(0,0,0), blue.greyscale("intensity"));

    assertEquals(new RGBColor(1,1,1), white.greyscale("red"));
    assertEquals(new RGBColor(1,1,1), white.greyscale("green"));
    assertEquals(new RGBColor(1,1,1), white.greyscale("blue"));
    assertEquals(new RGBColor(1,1,1), white.greyscale("value"));
    assertEquals(new RGBColor(1,1,1), white.greyscale("luma"));
    assertEquals(new RGBColor(1,1,1), white.greyscale("intensity"));
  }
}