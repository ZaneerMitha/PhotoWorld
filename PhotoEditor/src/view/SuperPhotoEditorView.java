package view;

import java.io.IOException;

/**
 * Interface for every type of PhotoEditor.
 */
public interface SuperPhotoEditorView {

  /**
   * Display a message to the user.
   * @param message the message to be displayed
   */
  void renderMessage(String message) throws IOException;
}
