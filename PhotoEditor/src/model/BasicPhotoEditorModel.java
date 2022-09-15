package model;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Represents a photo editor with operations that edits/filters photos.
 */
public class BasicPhotoEditorModel implements SuperPhotoEditorModel {

  private HashMap<String, Image> imageFiles = new HashMap<>();

  /**
   * Flips the image horizontally by flipping the columns values.
   */
  public void flipVertical(String fileName, String newName) {

    ArrayList<ArrayList<BasicPixel>> newPixelList = new ArrayList<ArrayList<BasicPixel>>();
    Image currentImage = imageFiles.get(fileName);

    for (int e = 0; e < currentImage.getHeight(); e++) {
      ArrayList<BasicPixel> row = new ArrayList<>();
      for (int z = currentImage.getWidth() - 1; z >= 0; z--) {
        row.add(currentImage.getPixelAt(e,z));
      }
      newPixelList.add(row);
    }
    imageFiles.put(newName, new BasicImage(newPixelList));
  }

  /**
   * Flips the image vertically by flipping the rows values.
   */
  public void flipHorizontal(String fileName, String newName) {

    ArrayList<ArrayList<BasicPixel>> newPixelList = new ArrayList<>();
    Image currentImage = imageFiles.get(fileName);

    for (int e = currentImage.getHeight() - 1; e >= 0; e--) {
      ArrayList<BasicPixel> column = new ArrayList<>();
      for (int z = 0; z < currentImage.getWidth(); z++) {
        column.add(currentImage.getPixelAt(e,z));
      }
      newPixelList.add(column);
    }
    imageFiles.put(newName, new BasicImage(newPixelList));
  }

  /**
   * Brightens or darkens an image by iterating through the
   * array and adding the given value to the pixels in the image.
   * @param value a positive or negative int that's used to either
   *              brighten or darken an image
   */
  public void brightenOrDarken(String fileName, String newName, int value) {

    ArrayList<ArrayList<BasicPixel>> newPixelList = new ArrayList<>();
    Image currentImage = imageFiles.get(fileName);

    for (int e = 0; e < currentImage.getHeight(); e++) {
      ArrayList<BasicPixel> row = new ArrayList<>();
      for (int z = 0; z < currentImage.getWidth(); z++) {
        row.add(currentImage.getPixelAt(e,z).brightenOrDarkenPixel(value));
      }
      newPixelList.add(row);
    }

    imageFiles.put(newName, new BasicImage(newPixelList));
  }

  /**
   * Create an images that visualize individual R,G,B components, the value,
   *              intensity or luma of an image by iterating through it
   *              and changing the pixels to the visualization.
   * @param value a string that's either red, blue, green, value, intensity,
   *              or luma.
   */
  @Override
  public void greyscale(String fileName, String newName, String value) {

    ArrayList<ArrayList<BasicPixel>> newPixelList = new ArrayList<>();
    Image currentImage = imageFiles.get(fileName);

    for (int e = 0; e < currentImage.getHeight(); e++) {
      ArrayList<BasicPixel> row = new ArrayList<>();
      for (int z = 0; z < currentImage.getWidth(); z++) {
        row.add(currentImage.getPixelAt(e,z).greyscale(value));
      }
      newPixelList.add(row);
    }
    imageFiles.put(newName, new BasicImage(newPixelList));
  }

  /**
   * Gets the image of the wanted file.
   * @param fileName the name of the file that is wanted.
   * @return the image of the inputted file name.
   */
  public Image getDesiredImage(String fileName) {
    return imageFiles.get(fileName);
  }

  /**
   * Accepts an image and then adds it to the hashmap.
   * @param fileName the name of the file.
   * @param image the picture made of up pixels,
   */
  public void acceptImage(String fileName, Image image) {
    imageFiles.put(fileName, image);
  }

  /**
   * Blurs an image by iterating through the array
   *      and changing the values of the pixels.
   * @param fileName the name of the file that is wanted.
   * @param newName the new changed name of the file.
   */
  public void blur(String fileName, String newName) {

    double[][] kernel = {
            {0.0625, 0.125, 0.0625},
            {0.125, 0.25, 0.125},
            {0.0625, 0.125, 0.0625}};

    blurOrSharpen(fileName, newName, kernel);
  }

  /**
   * Sharpens an image by iterating through the array
   *     and changing the values of the pixels.
   * @param fileName the name of the file that is wanted.
   * @param newName the new changed name of the file.
   */
  public void sharpen(String fileName, String newName) {
    double[][] kernel = {
            {-0.125, -0.125, -0.125, -0.125, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, 0.25, 1.0, 0.25, -0.125},
            {-0.125, 0.25, 0.25, 0.25, -0.125},
            {-0.125, -0.125, -0.125, -0.125, -0.125}};

    blurOrSharpen(fileName, newName, kernel);
  }

  /**
   * Converts a colored image to a greyscale image by multiplying
   *        the rgb values by an amount and adding them together.
   * @param fileName the name of the file that is wanted.
   * @param newName the new changed name of the file.
   */
  public void greyscaleKernel(String fileName, String newName) {

    double[][] matrix = {
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722},
            {0.2126, 0.7152, 0.0722}};

    greyscaleOrSepia(fileName, newName, matrix);
  }

  /**
   * Converts a colored image to a sepia tone image by multiplying
   *         the rgb values by an amount and adding them together.
   * @param fileName the name of the file that is wanted.
   * @param newName the new changed name of the file.
   */
  public void sepia(String fileName, String newName) {

    double[][] matrix = {
            {0.393, 0.769, 0.189},
            {0.349, 0.686, 0.168},
            {0.272, 0.534, 0.131}};

    greyscaleOrSepia(fileName, newName, matrix);
  }

  // helper that gets the kernel value in an image.
  private RGBColor getKernelValue(double[][] kernel, Image image, int row, int col) {

    int redValue = 0;
    int greenValue = 0;
    int blueValue = 0;
    int kernelSize = kernel.length / 2;

    for (int e = 0; e < kernel.length; e++) {
      for (int z = 0; z < kernel[0].length; z++) {
        try {
          redValue = redValue + (int) Math.round((image.getPixelAt(row + (e - kernelSize),
                 col + (z - kernelSize)).getRed() * kernel[e][z]));
          greenValue = greenValue + (int) Math.round((image.getPixelAt(row + (e - kernelSize),
                 col + (z - kernelSize)).getGreen() * kernel[e][z]));
          blueValue = blueValue + (int) Math.round((image.getPixelAt(row + (e - kernelSize),
                 col + (z - kernelSize)).getBlue() * kernel[e][z]));
        }
        catch (IndexOutOfBoundsException x) {
          return new RGBColor(image.getPixelAt(row,col).getRed(),
                              image.getPixelAt(row,col).getGreen(),
                              image.getPixelAt(row,col).getBlue());
        }
      }
    }
    return new RGBColor(redValue, greenValue, blueValue);
  }

  // returns a new filtered color in an image by multiplying the rgb values.
  private RGBColor filter(double[][] matrix, Image image, int row, int col) {
    int redValue = image.getPixelAt(row, col).getRed();
    int greenValue = image.getPixelAt(row, col).getGreen();
    int blueValue = image.getPixelAt(row, col).getBlue();

    int newRedValue = (int) Math.round((matrix[0][0] * redValue) + (matrix[0][1] * greenValue)
            + (matrix[0][2] * blueValue));
    int newGreenValue = (int) Math.round((matrix[1][0] * redValue) + (matrix[1][1] * greenValue)
            + (matrix[1][2] * blueValue));
    int newBlueValue = (int) Math.round((matrix[2][0] * redValue) + (matrix[2][1] * greenValue)
            + (matrix[2][2] * blueValue));

    return new RGBColor(newRedValue, newGreenValue, newBlueValue);
  }


  // helper than blurs or sharpens an image by iterating through
  // the image and changing the color values.
  private void blurOrSharpen(String fileName, String newName, double[][] kernel) {
    ArrayList<ArrayList<BasicPixel>> newPixelList = new ArrayList<>();
    Image currentImage = imageFiles.get(fileName);

    for (int e = 0; e < currentImage.getHeight(); e++) {
      ArrayList<BasicPixel> row = new ArrayList<>();
      for (int z = 0; z < currentImage.getWidth(); z++) {
        row.add(new BasicPixel(new Posn(e,z), getKernelValue(kernel, currentImage, e, z)));
      }
      newPixelList.add(row);
    }
    imageFiles.put(newName, new BasicImage(newPixelList));
  }

  // helper than blurs or sepia tones an image by iterating through
  // the image and changing the color values.
  private void greyscaleOrSepia(String fileName, String newName, double[][] matrix) {
    ArrayList<ArrayList<BasicPixel>> newPixelList = new ArrayList<>();
    Image currentImage = imageFiles.get(fileName);

    for (int e = 0; e < currentImage.getHeight(); e++) {
      ArrayList<BasicPixel> row = new ArrayList<>();
      for (int z = 0; z < currentImage.getWidth(); z++) {
        row.add(new BasicPixel(new Posn(e,z), filter(matrix, currentImage, e, z)));
      }
      newPixelList.add(row);
    }
    imageFiles.put(newName, new BasicImage(newPixelList));
  }


}
