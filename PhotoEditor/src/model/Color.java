package model;

/**
 * This interface represents a Color and the operations used on a Color.
 * There are multiple types of colors that can implement this interface.
 */
public interface Color {

  /**
   * Returns the maximum value of the three components (rgb) for each pixel.
   * @return an int of the max between the red, green and blue values.
   */
  public int getValue();

  /**
   * Returns the average of the three components (rgb) for each pixel by adding them
   *         then dividing the sum by 3.0.
   * @return a double of the average of the red, green and blue values.
   */
  public double getIntensity();

  /**
   * Returns the weighted sum of the three components (rgb) by
   *     multiplying the red value by 0.2126, the green value by 0.7152
   *     and the blue value by 0.0722, then adding them together.
   * @return a double of the weighted sum of the red, green and blue values.
   */
  public double getLuma();

  /**
   * Brightens (positive value) or darkens (negative value) the image by the given
   *              value to create a new image.
   * @param value a positive or negative int that's used to either brighten or darken
   *              an image
   * @return a new color with the given value added to the rgb values.
   */
  public Color brightenOrDarkenColor(int value);

  /**
   * Visualizes individual R,G,B components, the value,intensity or luma of an image.
   * @param value a string that's either red, blue, green, value, intensity,
   *              or luma.
   * @return a new color of the visualization.
   */
  public Color greyscale(String value);
}
