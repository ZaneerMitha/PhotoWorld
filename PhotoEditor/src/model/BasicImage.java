package model;

import java.util.ArrayList;
import java.util.Objects;

/**
 * Represents a class of an image that are made up of pixels,
 * and contains operations to represent and edit the image.
 */
public class BasicImage implements Image {

  private final ArrayList<ArrayList<BasicPixel>> pixelList;

  /**
   * Constructs a basic image by taking in a 2d array of pixels.
   * @param pixelList a list of pixels.
   */
  public BasicImage(ArrayList<ArrayList<BasicPixel>> pixelList) throws IllegalArgumentException {
    if (pixelList == null) {
      throw new IllegalArgumentException("List of pixels should not be null!");
    }
    this.pixelList = pixelList;
  }

  @Override
  public boolean equals(Object that) {

    if (that == this) {
      return true;
    }
    if (!(that instanceof BasicImage)) {
      return false;
    }

    BasicImage other = (BasicImage)that;

    return (this.pixelList.equals(other.pixelList));
  }

  @Override
  public int hashCode() {
    return Objects.hash(pixelList);
  }

  @Override
  public BasicPixel getPixelAt(int row, int column) {
    return pixelList.get(row).get(column);
  }

  @Override
  public int getWidth() {
    return pixelList.get(0).size();
  }

  @Override
  public int getHeight() {
    return pixelList.size();
  }
}
