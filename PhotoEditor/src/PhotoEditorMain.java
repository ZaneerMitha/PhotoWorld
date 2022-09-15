import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controller.BasicPhotoEditorController;
import controller.SuperPhotoEditorController;
import model.BasicPhotoEditorModel;
import model.SuperPhotoEditorModel;
import view.BasicPhotoEditorView;
import view.SuperPhotoEditorView;

/**
 * Main class to run our program in the console and actually.
 * save and load images to the computer.
 */
public class PhotoEditorMain {

  /**
   * Main method that runs the program in the console.
   * @param args Array of inputs.
   * @throws IOException Is thrown if the controller throws and exception.
   */
  public static void main(String[] args) throws IOException {
    SuperPhotoEditorModel model = new BasicPhotoEditorModel();
    SuperPhotoEditorView view = new BasicPhotoEditorView(model);
    SuperPhotoEditorController controller;

    if (args.length > 0) {
      String s = args[1];
      if (s.equalsIgnoreCase("-file")) {
        FileReader reader = new FileReader(args[2]);
        controller = new BasicPhotoEditorController(model, view, reader);
        controller.editPhoto();
      }
    }
    else {
      Readable reader = new InputStreamReader(System.in);
      controller = new BasicPhotoEditorController(model, view, reader);
      controller.editPhoto();
    }
  }
}
