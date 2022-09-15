package view;

import org.junit.Test;

import java.io.IOException;

import model.BasicPhotoEditorModel;
import model.SuperPhotoEditorModel;

import static org.junit.Assert.assertEquals;

/**
 * A JUnit test for the BasicPhotoEditorView class.
 */
public class BasicPhotoEditorViewTest {

  @Test
  public void testCorrectConstructor() {
    SuperPhotoEditorModel model = new BasicPhotoEditorModel();
    Appendable log = new StringBuilder();
    SuperPhotoEditorView view = new BasicPhotoEditorView(model, log);

    try {
      view.renderMessage("Constructor works!");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    assertEquals("Constructor works!", log.toString());
  }

  @Test
  public void testInvalidConstructor() {
    try {
      SuperPhotoEditorModel model = null;
      Appendable log = new StringBuilder();
      SuperPhotoEditorView view = new BasicPhotoEditorView(model, log);
    } catch (IllegalArgumentException e) {
      assertEquals("Model is null", e.getMessage());
    }

    try {
      SuperPhotoEditorModel model = new BasicPhotoEditorModel();
      Appendable log = null;
      SuperPhotoEditorView view = new BasicPhotoEditorView(model, log);
    } catch (IllegalArgumentException e) {
      assertEquals("Appendable is null", e.getMessage());
    }
  }

  @Test
  public void testRenderMessage() throws IOException {
    SuperPhotoEditorModel c = new BasicPhotoEditorModel();
    Appendable log = new StringBuilder();
    SuperPhotoEditorView view = new BasicPhotoEditorView(c, log);

    view.renderMessage("I'm so tired!!");

    assertEquals("I'm so tired!!", log.toString());
  }
}
