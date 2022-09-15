package model;

/**
 * This interface represents a Pixel and the operations used on a Pixel.
 * There are multiple types of pixels that can implement this interface.
 */
public interface Pixel {

  /**
   * Gets the value of the pixel.
   * @return the value of a color which is an int of the
   *         max between the red, green and blue values.
   */
  public int getPixelValue();

  /**
   * Gets the intensity of a pixel.
   * @return the intensity of a color which is a double of
   *         the average of the red, green and blue values.
   */
  public double getPixelIntensity();

  /**
   * Gets the luma of a pixel.
   * @return the luma of a color which is the double of the weighted
   *         sum of the red, green and blue values.
   */
  public double getPixelLuma();

  /**
   * Flips the pixel horizontally by flipping the position column.
   * @param value the total size.
   * @return a new pixel with a newly flipped position.
   */
  public Pixel flipHorizontal(int value);

  /**
   * Flips the pixel vertically by flipping the position row.
   * @param value the total size.
   * @return a new pixel with a newly flipped position.
   */
  public Pixel flipVertical(int value);

  /**
   * Brightens or Darkens the pixel by adding the given value to the color.
   * @param value  a positive or negative int that's used to either
   *               brighten or darken an image.
   * @return a new model.Pixel with a new brightened or darkened color.
   */
  public Pixel brightenOrDarkenPixel(int value);
}
