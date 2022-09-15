package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Interface for the gui controller with its operations.
 */
public interface PhotoEditorGuiController extends ActionListener {
  /**
   * When the user clicks a button, chooses a menu item, presses Enter in a text field.
   * The result is that an actionPerformed message is sent to all action listeners that
   * are registered on the relevant component.
   *
   * @param e the event to be processed
   */
  void actionPerformed(ActionEvent e);

  /**
   * Creates the values of a histogram using an image.
   *
   * @param command the different inputs: red, green, blue, intensity.
   * @return an array of values
   */
  int[] histogramValue(String command);

  /**
   * Load an image from the specified path and refer it to.
   * henceforth in the program by the given image name.
   *
   * @param path     The path in that one must take to reach the file.
   * @param fileName The name of the file in the hashMap.
   */
  void load(String path, String fileName) throws IOException;

  /**
   * Saves the file given to the hashMap in the form of an Image.
   *
   * @param path     The path that one must take to reach the file.
   * @param fileName The name of the file.
   * @throws FileNotFoundException If there is no file to be found using the path.
   */
  void save(String path, String fileName) throws IOException;
}
