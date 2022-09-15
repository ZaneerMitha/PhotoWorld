package view;

import java.io.IOException;

import model.SuperPhotoEditorModel;

/**
 * Implementation of the basic version of SuperPhotoEditor.
 */
public class BasicPhotoEditorView implements SuperPhotoEditorView {

  protected SuperPhotoEditorModel model;
  protected Appendable log;

  /**
   * Constructor that creates a SuperPhotoEditorModel object by taking in a model.
   * @param model SuperPhotoEditorModel with map of images.
   * @throws IllegalArgumentException thrown if any of the inputs are null.
   */
  public BasicPhotoEditorView(SuperPhotoEditorModel model)
          throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model is null");
    }

    this.model = model;
    this.log = System.out;
  }

  /**
   * Constructor that creates a SuperPhotoEditorModel object by taking in a model and.
   * Appendable.
   * @param model SuperPhotoEditorModel with map of images.
   * @param log Appendable that will house inputs.
   * @throws IllegalArgumentException thrown if any of the inputs are null.
   */
  public BasicPhotoEditorView(SuperPhotoEditorModel model, Appendable log)
          throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model is null");
    }
    if (log == null) {
      throw new IllegalArgumentException("Appendable is null");
    }

    this.model = model;
    this.log = log;
  }

  @Override
  public void renderMessage(String message) throws IOException {
    log.append(message + "\n");
  }

}
