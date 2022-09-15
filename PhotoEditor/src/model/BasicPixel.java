package model;

import java.util.Objects;

/**
 * Represents a class of pixels that are made up of a position
 * and colors, and also contains operations to represent and edit the image.
 */
public class BasicPixel implements Pixel {
  private Posn posn;
  private RGBColor color;

  /**
   * Constructs a pixel by taking in a position and a color.
   * @param posn The column (x) and row (y) of a pixel.
   * @param color a combination of rgb values.
   */
  public BasicPixel(Posn posn, RGBColor color) throws IllegalArgumentException {
    if (posn == null) {
      throw new IllegalArgumentException("Posn should not be null!");
    } if (color == null) {
      throw new IllegalArgumentException("Color should not be null!");
    }
    this.posn = posn;
    this.color = color;
  }

  @Override
  public boolean equals(Object that) {
    if (that == this) {
      return true;
    }
    if (!(that instanceof BasicPixel)) {
      return false;
    }
    BasicPixel other = (BasicPixel)that;
    return (this.posn.equals(other.posn))
        && (this.color.equals(other.color));
  }

  @Override
  public int hashCode() {
    return Objects.hash(posn, color);
  }

  /**
   * Getter method that gets the red value from the color class.
   * @return the red value.
   */
  public int getRed() {
    return color.getRedValue();
  }

  /**
   * Getter method that gets the green value from the color class.
   * @return the green value.
   */
  public int getGreen() {
    return color.getGreenValue();
  }

  /**
   * Getter method that gets the blue value from the color class.
   * @return the blue value.
   */
  public int getBlue() {
    return color.getBlueValue();
  }

  /**
   * Gets the value of the pixel.
   * @return the value of a color which is an int of the
   *         max between the red, green and blue values.
   */
  public int getPixelValue() {
    return color.getValue();
  }

  /**
   * Gets the intensity of a pixel.
   * @return the intensity of a color which is a double of
   *         the average of the red, green and blue values.
   */
  public double getPixelIntensity() {
    return color.getIntensity();
  }

  /**
   * Gets the luma of a pixel.
   * @return the luma of a color which is the double of the weighted
   *         sum of the red, green and blue values.
   */
  public double getPixelLuma() {
    return color.getLuma();
  }

  /**
   * Flips the pixel horizontally by flipping the position column.
   * @param value the total size.
   * @return a new pixel with a newly flipped position.
   */
  public BasicPixel flipHorizontal(int value) {
    return new BasicPixel(posn.flipX(value), color);
  }

  /**
   * Flips the pixel vertically by flipping the position row.
   * @param value the total size.
   * @return a new pixel with a newly flipped position.
   */
  public BasicPixel flipVertical(int value) {
    return new BasicPixel(posn.flipY(value), color);
  }

  /**
   * Brightens or Darkens the pixel by adding the given value to the color.
   * @param value  a positive or negative int that's used to either
   *               brighten or darken an image.
   * @return a new model.Pixel with a new brightened or darkened color.
   */
  public BasicPixel brightenOrDarkenPixel(int value) {
    return new BasicPixel(posn, color.brightenOrDarkenColor(value));
  }


  /**
   * Visualizes individual R,G,B components, the value,intensity or luma of an image.
   * @param value a string that's either red, blue, green, value, intensity,
   *              or luma.
   * @return a new Pixel with a new color.
   */
  public BasicPixel greyscale(String value) {
    return new BasicPixel(posn, color.greyscale(value));
  }
}
