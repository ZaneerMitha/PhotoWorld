package model;

import java.util.Objects;

/**
 * Represents a class of colors that are made up of different values
 * and operations to represent the colors.
 */
public class RGBColor implements Color {
  private final int redValue;
  private final int greenValue;
  private final int blueValue;

  /**
   * Constructs a color by taking in the rgb (red, green and blue) values that are given.
   * Rgb can be combined in various proportions to obtain any color in the visible spectrum.
   * @param redValue the red value of a system for representing the colors to be
   *                 used on a computer display.
   * @param greenValue the green value of a system for representing the colors to be
   *                   used on a computer display.
   * @param blueValue the blue value of a system for representing the colors to be
   *                  used on a computer display.
   */
  public RGBColor(int redValue, int greenValue, int blueValue) {

    if (redValue < 0) {
      this.redValue = 0;
    }
    else if (redValue > 255) {
      this.redValue = 255;
    }
    else {
      this.redValue = redValue;
    }
    if (greenValue < 0) {
      this.greenValue = 0;
    }
    else if (greenValue > 255) {
      this.greenValue = 255;
    }
    else {
      this.greenValue = greenValue;
    }
    if (blueValue < 0) {
      this.blueValue = 0;
    }
    else if (blueValue > 255) {
      this.blueValue = 255;
    }
    else {
      this.blueValue = blueValue;
    }

  }

  @Override
  public boolean equals(Object that) {
    if (that == this) {
      return true;
    }
    if (!(that instanceof RGBColor)) {
      return false;
    }
    RGBColor other = (RGBColor)that;
    return (this.redValue == other.redValue)
        && (this.greenValue == other.greenValue)
        && (this.blueValue == other.blueValue);
  }

  @Override
  public int hashCode() {
    return Objects.hash(redValue, greenValue, blueValue);
  }

  /**
   * Returns the maximum value of the three components (rgb) for each pixel.
   * @return an int of the max between the red, green and blue values.
   */
  public int getValue() {

    if (redValue > greenValue) {
      return Math.max(redValue, blueValue);
    }
    else {
      return Math.max(greenValue, blueValue);
    }
  }

  /**
   * Returns the average of the three components (rgb) for each pixel by adding them
   *         then dividing the sum by 3.0.
   * @return a double of the average of the red, green and blue values.
   */
  public double getIntensity() {
    return (redValue + greenValue + blueValue) / 3.0;
  }

  /**
   * Returns the weighted sum of the three components (rgb) by
   *     multiplying the red value by 0.2126, the green value by 0.7152
   *     and the blue value by 0.0722, then adding them together.
   * @return a double of the weighted sum of the red, green and blue values.
   */
  public double getLuma() {
    return (0.2126 * redValue) + (0.7152 * greenValue) + (0.0722 * blueValue);
  }

  /**
   * Brightens (positive value) or darkens (negative value) the image by the given
   *              value to create a new image.
   * @param value a positive or negative int that's used to either brighten or darken
   *              an image
   * @return a new color with the given value added to the rgb values.
   */
  public RGBColor brightenOrDarkenColor(int value) {
    return new RGBColor(redValue + value,
            greenValue + value,
            blueValue + value);
  }

  /**
   * Getter method that gets the red value.
   * @return the red value.
   */
  public int getRedValue() {
    return this.redValue;
  }

  /**
   * Getter method that gets the green value.
   * @return the green value.
   */
  public int getGreenValue() {
    return this.greenValue;
  }

  /**
   * Getter method that gets the blue value.
   * @return the blue value.
   */
  public int getBlueValue() {
    return this.blueValue;
  }

  /**
   * Visualizes individual R,G,B components, the value,intensity or luma of an image.
   * @param value a string that's either red, blue, green, value, intensity,
   *              or luma.
   * @return a new color of the visualization.
   */
  public RGBColor greyscale(String value) {

    RGBColor newColor;

    switch (value) {
      case "red" :
        newColor = new RGBColor(redValue, redValue, redValue);
        break;
      case "blue" :
        newColor = new RGBColor(blueValue, blueValue, blueValue);
        break;
      case "green" :
        newColor = new RGBColor(greenValue, greenValue, greenValue);
        break;
      case "value" :
        newColor = new RGBColor(this.getValue(), this.getValue(), this.getValue());
        break;
      case "intensity" :
        newColor = new RGBColor((int) Math.round(this.getIntensity()),
                (int) Math.round(this.getIntensity()),
                (int) Math.round(this.getIntensity()));
        break;
      case "luma" :
        newColor = new RGBColor((int) Math.round(this.getLuma()), (int) Math.round(this.getLuma()),
                (int) Math.round(this.getLuma()));
        break;
      default:
        newColor = this;
    }
    return newColor;
  }
}
