package view;

/**
 * This interface represents a GUI view for a photo editor.
 */
public interface PhotoEditorGuiView {
  /**
   * Refresh the screen. This is called when the something on the
   * screen is updated therefore it must be redrawn.
   */
  void refresh();

  /**
   * Display a message in a suitable area of the GUI.
   * @param message the message to be displayed
   */
  void renderMessage(String message);

  /**
   * Gives the controller the ability to open files found in the computer.
   * @return a string of the file that was selected.
   */
  String openFile();

  /**
   * Updates the image in the gui by creating a new image.
   * @param fileName the name of the file that is being updated.
   */
  void updateImage(String fileName);

  /**
   * Creates a histogram and adds it to the panel that's in the gui.
   */
  void histogramCreator();

}
