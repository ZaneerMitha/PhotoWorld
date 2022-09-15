package model;

/**
 * This interface represents an editor and the operations used by the editor.
 */
public interface SuperPhotoEditorModel {

  /**
   * Flips the image horizontally by flipping the columns values.
   */
  void flipHorizontal(String fileName, String newName);

  /**
   * Flips the image vertically by flipping the rows values.
   */
  void flipVertical(String fileName, String newName);

  /**
   * Brightens or darkens an image by iterating through the
   * array and adding the given value to the pixels in the image.
   *
   * @param value a positive or negative int that's used to either
   *              brighten or darken an image.
   */
  void brightenOrDarken(String fileName, String newName, int value);

  /**
   * Create an images that visualize individual R,G,B components, the value,
   * intensity or luma of an image by iterating through it
   * and changing the pixels to the visualization.
   *
   * @param value a string that's either red, blue, green, value, intensity,
   *              or luma.
   */
  void greyscale(String fileName, String newName, String value);

  /**
   * Gets the image of the wanted file.
   *
   * @param fileName the name of the file that is wanted.
   * @return the image of the inputted file name.
   */
  Image getDesiredImage(String fileName);

  /**
   * Accepts an image and then adds it to the hashmap.
   *
   * @param fileName the name of the file.
   * @param image    the picture made of up pixels,
   */
  void acceptImage(String fileName, Image image);

  /**
   * Blurs an image by iterating through the array
   * and changing the values of the pixels.
   *
   * @param fileName the name of the file that is wanted.
   * @param newName  the new changed name of the file.
   */
  void blur(String fileName, String newName);

  /**
   * Sharpens an image by iterating through the array
   * and changing the values of the pixels.
   *
   * @param fileName the name of the file that is wanted.
   * @param newName  the new changed name of the file.
   */
  void sharpen(String fileName, String newName);

  /**
   * Converts a colored image to a greyscale image by multiplying
   * the rgb values by an amount and adding them together.
   *
   * @param fileName the name of the file that is wanted.
   * @param newName  the new changed name of the file.
   */
  void greyscaleKernel(String fileName, String newName);

  /**
   * Converts a colored image to a sepia tone image by multiplying
   * the rgb values by an amount and adding them together.
   *
   * @param fileName the name of the file that is wanted.
   * @param newName  the new changed name of the file.
   */
  void sepia(String fileName, String newName);
}
