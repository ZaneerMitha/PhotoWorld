package model;

/**
 * This interface represents a Image and the operations used on a Pixel.
 * There are multiple types of images that can implement this interface.
 */
public interface Image {

  /**
   * Gets the pixel at a specific position by going through
   *    the image and finding the location of entered
   *    row and column.
   * @param row the  x value inputted
   * @param column the y value
   * @return the pixel at the inputted row and column
   */
  public BasicPixel getPixelAt(int row, int column);

  /**
   * Gets the width of the image.
   * @return the width of the image.
   */
  public int getWidth();

  /**
   * Gets the height of the image.
   * @return the height of the image.
   */
  public int getHeight();

}
