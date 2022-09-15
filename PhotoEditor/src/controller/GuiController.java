package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import javax.imageio.ImageIO;
import javax.swing.JOptionPane;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import model.BasicImage;
import model.BasicPhotoEditorModel;
import model.BasicPixel;
import model.Posn;
import model.RGBColor;
import model.SuperPhotoEditorModel;
import view.PhotoEditorGuiView;
import view.SwingGuiView;

/**
 * Represents a class of the controller of the SwingGui that
 *   has a SuperPhotoEditor model and a PhotoEditorGuiView view.
 */
public class GuiController implements PhotoEditorGuiController, ActionListener {
  SuperPhotoEditorModel model;
  PhotoEditorGuiView view;

  /**
   * Constructor for the gui controller that assigns the model and view.
   */
  public GuiController() {
    this.model = new BasicPhotoEditorModel();
    this.view = new SwingGuiView();
  }

  /**
   * Constructor for the gui controller that takes in a
   * SuperPhotoEditorModel model and a PhotoEditorGuiView view
   * and makes sure they're not null.
   * @param model model.SuperPhotoEditorModel that houses all the image files.
   * @param view view.PhotoEditorGuiView that displays the gui view of the photo editor.
   */
  public GuiController(SuperPhotoEditorModel model, PhotoEditorGuiView view) {
    if (model == null) {
      throw new IllegalArgumentException("Model cannot be null!");
    } if (view == null) {
      throw new IllegalArgumentException("View cannot be null!");
    }
    this.model = model;
    this.view = view;
  }

  @Override
  public void load(String path, String fileName) throws IOException {
    if (path.contains(".ppm")) {
      loadPPM(path, fileName);
    } else {
      loadConventionalFileFormats(path,fileName);
    }
  }

  @Override
  public void save(String path, String fileName) throws IOException {
    try {
      if (path.contains(".ppm")) {
        savePPM(path, fileName);
      } else {
        saveConventionalFileFormats(path, fileName);
      }
    }  catch (NullPointerException o) {
      JOptionPane.showMessageDialog(null, "You must open a file before saving.",
              "No Image Was Loaded", JOptionPane.ERROR_MESSAGE);
    }
    if (!path.contains(".ppm") || !path.contains(".png") || !path.contains(".bmp")
            || !path.contains(".jpg")) {
      JOptionPane.showMessageDialog(null, "File must be saved as a PNG, PPM, BMP or JPG.",
              "File Was Not Saved Correctly", JOptionPane.ERROR_MESSAGE);
    }
  }


  @Override
  public void actionPerformed(ActionEvent e) {
    switch (e.getActionCommand()) {
      case "Open file": {
        try {
          load(view.openFile(), "image");
          try {
            view.updateImage("image");
          } catch (NullPointerException o) {
            view.renderMessage("Keep Going!");
          }
        } catch (IOException ex) {
          throw new RuntimeException(ex);
        }
        view.histogramCreator();
      }
      break;
      case "Save file": {
        final JFileChooser fileChooser = new JFileChooser(".");
        int retValue = fileChooser.showSaveDialog(null);
        if (retValue == JFileChooser.APPROVE_OPTION) {
          File f = fileChooser.getSelectedFile();
          String path = f.getAbsolutePath();
          try {
            save(path, "image");
          } catch (IOException o) {
            throw new RuntimeException(o);
          }
        }
      }
      break;
      case "brighten": {
        JLabel inputDisplay = new JLabel();
        inputDisplay.setText(JOptionPane.showInputDialog("Brighten or Darken:"));
        try {
          try {
            model.brightenOrDarken("image", "image", Integer.parseInt(inputDisplay.getText()));
          } catch (NumberFormatException o) {
            JOptionPane.showMessageDialog(null, "You must enter a number.",
                    "Entered Value Wasn't a Number", JOptionPane.ERROR_MESSAGE);
          }
        } catch (NullPointerException o) {
          JOptionPane.showMessageDialog(null, "You must open a file before editing.",
                  "No Image Was Loaded", JOptionPane.ERROR_MESSAGE);
        }
        updateImage();
        view.histogramCreator();
      }
      break;
      case "vertical-flip": {
        try {
          model.flipVertical("image", "image");
        } catch (NullPointerException o) {
          JOptionPane.showMessageDialog(null, "You must open a file before editing.",
                  "No Image Was Loaded", JOptionPane.ERROR_MESSAGE);
        }
        updateImage();
        view.histogramCreator();
      }
      break;
      case "horizontal-flip": {
        try {
          model.flipHorizontal("image", "image");
        } catch (NullPointerException o) {
          JOptionPane.showMessageDialog(null, "You must open a file before editing.",
                  "No Image Was Loaded", JOptionPane.ERROR_MESSAGE);
        }
        updateImage();
        view.histogramCreator();
      }
      break;
      case "value-component": {
        try {
          model.greyscale("image", "image", "value");
        } catch (NullPointerException o) {
          JOptionPane.showMessageDialog(null, "You must open a file before editing.",
                  "No Image Was Loaded", JOptionPane.ERROR_MESSAGE);
        }
        updateImage();
        view.histogramCreator();
      }
      break;
      case "intensity-component": {
        try {
          model.greyscale("image", "image", "intensity");
        } catch (NullPointerException o) {
          JOptionPane.showMessageDialog(null, "You must open a file before editing.",
                  "No Image Was Loaded", JOptionPane.ERROR_MESSAGE);
        }
        updateImage();
        view.histogramCreator();
      }
      break;
      case "luma-component": {
        try {
          model.greyscale("image", "image", "luma");
        } catch (NullPointerException o) {
          JOptionPane.showMessageDialog(null, "You must open a file before editing.",
                  "No Image Was Loaded", JOptionPane.ERROR_MESSAGE);
        }
        updateImage();
        view.histogramCreator();
      }
      break;
      case "red-component": {
        try {
          model.greyscale("image", "image", "red");
        } catch (NullPointerException o) {
          JOptionPane.showMessageDialog(null, "You must open a file before editing.",
                  "No Image Was Loaded", JOptionPane.ERROR_MESSAGE);
        }
        updateImage();
        view.histogramCreator();
      }
      break;
      case "green-component": {
        try {
          model.greyscale("image", "image", "green");
        } catch (NullPointerException o) {
          JOptionPane.showMessageDialog(null, "You must open a file before editing.",
                  "No Image Was Loaded", JOptionPane.ERROR_MESSAGE);
        }
        updateImage();
        view.histogramCreator();
      }
      break;
      case "blue-component": {
        try {
          model.greyscale("image", "image", "blue");
        } catch (NullPointerException o) {
          JOptionPane.showMessageDialog(null, "You must open a file before editing.",
                  "No Image Was Loaded", JOptionPane.ERROR_MESSAGE);
        }
        updateImage();
        view.histogramCreator();
      }
      break;
      case "blur": {
        try {
          model.blur("image", "image");
        } catch (NullPointerException o) {
          JOptionPane.showMessageDialog(null, "You must open a file before editing.",
                  "No Image Was Loaded", JOptionPane.ERROR_MESSAGE);
        }
        updateImage();
        view.histogramCreator();
      }
      break;
      case "sepia": {
        try {
          model.sepia("image", "image");
        } catch (NullPointerException o) {
          JOptionPane.showMessageDialog(null, "You must open a file before editing.",
                  "No Image Was Loaded", JOptionPane.ERROR_MESSAGE);
        }
        updateImage();
        view.histogramCreator();
      }
      break;
      case "sharpen": {
        try {
          model.sharpen("image", "image");
        } catch (NullPointerException o) {
          JOptionPane.showMessageDialog(null, "You must open a file before editing.",
                  "No Image Was Loaded", JOptionPane.ERROR_MESSAGE);
        }
        updateImage();
        view.histogramCreator();
      }
      break;
      case "greyscale": {
        try {
          model.greyscaleKernel("image", "image");
        } catch (NullPointerException o) {
          JOptionPane.showMessageDialog(null, "You must open a file before editing.",
                  "No Image Was Loaded", JOptionPane.ERROR_MESSAGE);
        }
        updateImage();
        view.histogramCreator();
      }
      break;
      default:
        view.renderMessage("Invalid!");
    }
  }

  @Override
  public int[] histogramValue(String command) {
    model.Image image = model.getDesiredImage("image");
    int[] values = new int[]{};
    try {
      values = new int[image.getHeight() * image.getWidth()];
    } catch (NullPointerException o) {
      view.renderMessage("Keep Going!");
    }
    try {
      switch (command) {
        case "red":
          for (int i = 0; i < (image.getHeight() * image.getWidth()); ) {
            for (int r = 0; r < image.getHeight(); r++) {
              for (int c = 0; c < image.getWidth(); c++) {
                BasicPixel current = image.getPixelAt(r, c);
                int redVal = current.getRed();
                values[i] = redVal;
                i++;
              }
            }
          }
          break;
        case "green":
          for (int i = 0; i < (image.getHeight() * image.getWidth()); ) {
            for (int r = 0; r < image.getHeight(); r++) {
              for (int c = 0; c < image.getWidth(); c++) {
                BasicPixel current = image.getPixelAt(r, c);
                int greenVal = current.getGreen();
                values[i] = greenVal;
                i++;
              }
            }
          }
          break;
        case "blue":
          for (int i = 0; i < (image.getHeight() * image.getWidth()); ) {
            for (int r = 0; r < image.getHeight(); r++) {
              for (int c = 0; c < image.getWidth(); c++) {
                BasicPixel current = image.getPixelAt(r, c);
                int blueVal = current.getBlue();
                values[i] = blueVal;
                i++;
              }
            }
          }
          break;
        case "intensity":
          for (int i = 0; i < (image.getHeight() * image.getWidth()); ) {
            for (int r = 0; r < image.getHeight(); r++) {
              for (int c = 0; c < image.getWidth(); c++) {
                int intensityValue = (int) image.getPixelAt(r, c).getPixelIntensity();
                values[i] = intensityValue;
                i++;
              }
            }
          }
          break;
        default:
          throw new IllegalArgumentException();
      }
    } catch (NullPointerException o) {
      view.renderMessage("Keep Going!");
    }
    return values;
  }

  private void loadPPM(String path, String fileName) throws IOException {

    Scanner sc;
    int redValue;
    int blueValue;
    int greenValue;
    RGBColor newColor;
    BasicPixel newPixel;

    try {
      sc = new Scanner(new FileInputStream(path));
    }
    catch (FileNotFoundException e) {
      System.out.println("File " + path + " not found!");
      sc = new Scanner(System.in);
    }
    StringBuilder builder = new StringBuilder();
    //read the file line by line, and populate a string. This will throw away any comment lines
    while (sc.hasNextLine()) {
      String s = sc.nextLine();
      if (s.charAt(0) != '#') {
        builder.append(s + System.lineSeparator());
      }
    }

    //now set up the scanner to read from the string we just built
    sc = new Scanner(builder.toString());

    String token;

    token = sc.next();
    if (!token.equals("P3")) {
      System.out.println("Invalid PPM file: plain RAW file should begin with P3");
    }
    int width = sc.nextInt();
    int height = sc.nextInt();
    int maxValue = sc.nextInt();

    ArrayList<ArrayList<BasicPixel>> pixelListLoaded = new ArrayList<>();
    for (int i = 0; i < height; i++) {
      ArrayList<BasicPixel> row = new ArrayList<>();
      for (int j = 0; j < width; j++) {
        redValue = sc.nextInt();
        greenValue = sc.nextInt();
        blueValue = sc.nextInt();
        newColor = new RGBColor(redValue, greenValue, blueValue);
        newPixel = new BasicPixel(new Posn(j, i), newColor);
        row.add(newPixel);
      }
      pixelListLoaded.add(row);
    }
    model.acceptImage(fileName, new BasicImage(pixelListLoaded));
    view.renderMessage("Your image has been loaded!");
  }

  // helper that loads png, jpg, bmp, etc...
  private void loadConventionalFileFormats(String path, String fileName) throws IOException {
    File file = new File(path);
    BufferedImage bi;
    try {
      bi = ImageIO.read(file);
    } catch (IOException e) {
      view.renderMessage("Can't read file! :(");
      bi = null;
      return;
    }

    ArrayList<ArrayList<BasicPixel>> img = new ArrayList<>();
    for (int i = 0; i < bi.getHeight(); i++) {
      ArrayList<BasicPixel> row = new ArrayList<>();
      for (int j = 0; j < bi.getWidth(); j++) {
        Color color = new Color(bi.getRGB(j,i));
        int redValue = color.getRed();
        int greenValue = color.getGreen();
        int blueValue = color.getBlue();
        BasicPixel newPixel = new BasicPixel(new Posn(i, j),
                new RGBColor(redValue, greenValue, blueValue));
        row.add(newPixel);
      }
      img.add(row);
    }
    model.acceptImage(fileName, new BasicImage(img));
    view.renderMessage("Your image has been loaded!");
  }

  // helper that saves a png, jpg, bmp, etc..
  private void saveConventionalFileFormats(String path, String fileName) throws IOException {
    String extension = path.substring(path.indexOf(".") + 1);
    model.Image desiredImage = model.getDesiredImage(fileName);
    int a = 0;
    BufferedImage bi = new BufferedImage(desiredImage.getWidth(),
            desiredImage.getHeight(), BufferedImage.TYPE_INT_RGB);
    for (int e = 0; e < desiredImage.getHeight(); e++) {
      for (int z = 0; z < desiredImage.getWidth(); z++) {
        BasicPixel p = desiredImage.getPixelAt(e,z);
        int redValue = p.getRed();
        int greenValue = p.getGreen();
        int blueValue = p.getBlue();
        Color color = new Color(redValue, greenValue, blueValue);
        a = color.getRGB();
        bi.setRGB(z, e, a);
      }
    }
    File out = new File(path);
    try {
      ImageIO.write(bi, extension, out);
    } catch (IOException w) {
      throw new IllegalArgumentException(w);
    }
    view.renderMessage("Your image has been saved!");
  }

  // helper that saves a ppm file
  private void savePPM(String path, String fileName) throws IOException {

    model.Image desiredImage = model.getDesiredImage(fileName);

    FileOutputStream file = new FileOutputStream(path);
    StringBuilder builder = new StringBuilder();
    builder.append("P3\n");
    builder.append(desiredImage.getWidth());
    builder.append(" ");
    builder.append(desiredImage.getHeight());
    builder.append(" ");
    builder.append("255");
    builder.append("\n");
    for (int i = 0; i < desiredImage.getHeight(); i++) {
      for (int j = 0; j < desiredImage.getWidth(); j++) {
        builder.append(desiredImage.getPixelAt(i,j).getRed()).append("\n");
        builder.append(desiredImage.getPixelAt(i,j).getGreen()).append("\n");
        builder.append(desiredImage.getPixelAt(i,j).getBlue()).append("\n");
      }
    }

    try {
      file.write(builder.toString().getBytes());
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }

    try {
      file.close();
    } catch (IOException e) {
      throw new IllegalStateException(e.getMessage());
    }
    view.renderMessage("Your image has been saved!");
  }

  private void updateImage() {
    try {
      view.updateImage("image");
    } catch (NullPointerException o) {
      view.renderMessage("Keep Going!");
    }
  }
}
