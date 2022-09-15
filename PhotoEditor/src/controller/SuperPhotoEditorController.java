package controller;

import java.io.FileNotFoundException;
import java.io.IOException;

/**
 * Interface for every type of PhotoEditor.
 */
public interface SuperPhotoEditorController {

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

  /**
   * Uses user input to run the desired aspect of the program using the controller.
   *
   * @throws IOException if none of the entered commands are valid.
   */
  void editPhoto() throws IOException;

  /**
   * This allows the user to use a component to partially greyscale an image.
   *
   * @param fileName    The name of the file that the user wants to greyscale.
   * @param fileMapName The name of the map that shows the model what pixels to greyscale.
   * @param newName     The new name of the image in the hashMap.
   * @param value       The value that is used to visualize the component.
   * @throws IOException Is thrown if the load method throws an IOException.
   */
  void greyscalePartial(String fileName, String fileMapName, String newName,
                        String value) throws IOException;

  /**
   * This allows for the user to partially manipulate an image.
   *
   * @param fileName    The name of the file that the user wants to greyscale.
   * @param fileMapName The name of the map that shows the model what pixels to greyscale.
   * @param newName     The new name of the image in the hashMap.
   * @param operation   The manipulation that will be applied to the image.
   * @throws IOException Is thrown if the load method throws an IOException.
   */
  void partialManipulator(String fileName, String fileMapName, String newName,
                          String operation) throws IOException;

}
