package view;

import java.io.IOException;
import controller.GuiController;
import controller.PhotoEditorGuiController;
import model.BasicPhotoEditorModel;
import model.SuperPhotoEditorModel;

/**
 * Main class to run our program as a gui and be able to
 * edit, save and load images to the computer.
 */
public class GUIMain {

  /**
   * Main method that runs the program.
   * @param args Array of inputs.
   * @throws IOException Is thrown if the controller throws an exception.
   */
  public static void main(String[] args) throws IOException {
    PhotoEditorGuiView view = new SwingGuiView();
    SuperPhotoEditorModel model = new BasicPhotoEditorModel();
    PhotoEditorGuiController c = new GuiController(model, view);
  }
}

