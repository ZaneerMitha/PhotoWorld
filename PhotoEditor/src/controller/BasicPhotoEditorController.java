package controller;

import java.awt.Color;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.imageio.ImageIO;

import model.BasicImage;
import model.BasicPixel;
import model.Image;
import model.Posn;
import model.RGBColor;
import model.SuperPhotoEditorModel;
import view.SuperPhotoEditorView;

/**
 * Represents a class of the controller of the SuperPhotoEditor that
 *   has a SuperPhotoEditor model, a SuperPhotoEditor view and a readable .
 */
public class BasicPhotoEditorController implements SuperPhotoEditorController {
  private final SuperPhotoEditorModel model;
  private final SuperPhotoEditorView view;
  private final Scanner scan;

  /**
   * Constructor for the controller that takes in model, view, and reader and checks.
   * if they are null.
   * @param model model.SuperPhotoEditorModel that houses all the image files.
   * @param reader Readable object used later to read inputs.
   * @throws IllegalArgumentException thrown if any of the inputs are null.
   */
  public BasicPhotoEditorController(SuperPhotoEditorModel model,
                                    SuperPhotoEditorView view, Readable reader)
          throws IllegalArgumentException {
    if (model == null) {
      throw new IllegalArgumentException("Model is null");
    }
    if (view == null) {
      throw new IllegalArgumentException("View is null");
    }
    if (reader == null) {
      throw new IllegalArgumentException("Reader is null");
    }

    this.model = model;
    this.view = view;
    this.scan = new Scanner(reader);
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
    if (path.contains(".ppm")) {
      savePPM(path, fileName);
    } else {
      saveConventionalFileFormats(path, fileName);
    }
  }

  @Override
  public void greyscalePartial(String fileName, String fileMapName, String newName, String value)
          throws IOException {

    model.greyscale(fileName, "value-greyscaled", value);
    this.load(fileMapName, "map");

    partialManipulatorHelper(fileName, newName, "value-greyscaled");

  }

  @Override
  public void partialManipulator(String fileName, String fileMapName, String newName,
                                 String operation) throws IOException {
    switch (operation) {
      case "blurred":
        model.blur(fileName, "blurred");
        this.load(fileMapName, "map");
        partialManipulatorHelper(fileName, newName, "blurred");
        break;
      case "sharpened":
        model.sharpen(fileName, "sharpened");
        this.load(fileMapName, "map");
        partialManipulatorHelper(fileName, newName, "sharpened");
        break;
      case "greyscaled":
        model.greyscaleKernel(fileName, "greyscaled");
        this.load(fileMapName, "map");
        partialManipulatorHelper(fileName, newName, "greyscaled");
        break;
      case "sepia":
        model.sepia(fileName, "sepia");
        this.load(fileMapName, "map");
        partialManipulatorHelper(fileName ,newName, "sepia");
        break;
      default:
        view.renderMessage("This is an invalid partial-manipulator command!");
    }
  }

  private void partialManipulatorHelper(String fileName, String newName, String operation)
          throws IOException {

    Image base = model.getDesiredImage(operation);
    Image map = model.getDesiredImage("map");
    Image original = model.getDesiredImage(fileName);
    ArrayList<ArrayList<BasicPixel>> partialBlurPix = new ArrayList<>();

    for (int e = 0; e < base.getHeight(); e++) {
      ArrayList<BasicPixel> row = new ArrayList<>();
      for (int z = 0; z < base.getWidth(); z++) {
        if (map.getPixelAt(e,z).getRed() == 0
                || map.getPixelAt(e,z).getBlue() == 0
                || map.getPixelAt(e,z).getGreen() == 0) {
          row.add(base.getPixelAt(e,z));
        }
        else {
          row.add(original.getPixelAt(e,z));
        }
      }
      partialBlurPix.add(row);
    }

    Image partialBlur = new BasicImage(partialBlurPix);
    model.acceptImage(newName, partialBlur);

  }

  @Override
  public void editPhoto() throws IOException {

    introMess();
    while (scan.hasNext()) {
      String command = scan.next();
      switch (command) {
        case "load":
          ArrayList<String> list = new ArrayList<String>(Arrays.asList(scan.next(), scan.next()));
          load(list.get(0), list.get(1));
          break;
        case "save":
          ArrayList<String> list2 = new ArrayList<String>(Arrays.asList(scan.next(), scan.next()));
          save(list2.get(0), list2.get(1));
          break;
        case "brighten":
          ArrayList<String> list3 = new ArrayList<String>(Arrays.asList(scan.next(),
                  scan.next(), scan.next()));
          model.brightenOrDarken(list3.get(1), list3.get(2), Integer.parseInt(list3.get(0)));
          view.renderMessage("Your image has been brightened!");
          break;
        case "vertical-flip":
          ArrayList<String> list4 = new ArrayList<String>(Arrays.asList(scan.next(), scan.next()));
          model.flipVertical(list4.get(0), list4.get(1));
          view.renderMessage("Your image has been flipped!");
          break;
        case "horizontal-flip":
          ArrayList<String> list5 = new ArrayList<String>(Arrays.asList(scan.next(), scan.next()));
          model.flipHorizontal(list5.get(0), list5.get(1));
          view.renderMessage("Your image has been flipped!");
          break;
        case "value-component":
          ArrayList<String> list6 = new ArrayList<String>(Arrays.asList(scan.next(), scan.next()));
          if (list6.get(1).contains(".")) {
            this.greyscalePartial(list6.get(0), list6.get(1), scan.next(), "value");
            view.renderMessage("Your image has been partially grey-scaled!");
          }
          else {
            model.greyscale(list6.get(0), list6.get(1), "value");
            view.renderMessage("Your image has been grey-scaled!");
          }
          break;
        case "intensity-component":
          ArrayList<String> list7 = new ArrayList<String>(Arrays.asList(scan.next(), scan.next()));
          if (list7.get(1).contains(".")) {
            this.greyscalePartial(list7.get(0), list7.get(1), scan.next(), "intensity");
            view.renderMessage("Your image has been partially grey-scaled!");
          }
          else {
            model.greyscale(list7.get(0), list7.get(1), "intensity");
            view.renderMessage("Your image has been grey-scaled!");
          }
          break;
        case "luma-component":
          ArrayList<String> list8 = new ArrayList<String>(Arrays.asList(scan.next(), scan.next()));
          if (list8.get(1).contains(".")) {
            this.greyscalePartial(list8.get(0), list8.get(1), scan.next(), "luma");
            view.renderMessage("Your image has been partially grey-scaled!");
          }
          else {
            model.greyscale(list8.get(0), list8.get(1), "luma");
            view.renderMessage("Your image has been grey-scaled!");
          }
          break;
        case "red-component":
          ArrayList<String> list9 = new ArrayList<String>(Arrays.asList(scan.next(), scan.next()));
          if (list9.get(1).contains(".")) {
            this.greyscalePartial(list9.get(0), list9.get(1), scan.next(), "red");
            view.renderMessage("Your image has been partially grey-scaled!");
          }
          else {
            model.greyscale(list9.get(0), list9.get(1), "red");
            view.renderMessage("Your image has been grey-scaled!");
          }
          break;
        case "green-component":
          ArrayList<String> list10 = new ArrayList<String>(Arrays.asList(scan.next(), scan.next()));
          if (list10.get(1).contains(".")) {
            this.greyscalePartial(list10.get(0), list10.get(1), scan.next(), "green");
            view.renderMessage("Your image has been partially grey-scaled!");
          }
          else {
            model.greyscale(list10.get(0), list10.get(1), "green");
            view.renderMessage("Your image has been grey-scaled!");
          }
          break;
        case "blue-component":
          ArrayList<String> list11 = new ArrayList<String>(Arrays.asList(scan.next(), scan.next()));
          if (list11.get(1).contains(".")) {
            this.greyscalePartial(list11.get(0), list11.get(1), scan.next(), "blue");
            view.renderMessage("Your image has been partially grey-scaled!");
          }
          else {
            model.greyscale(list11.get(0), list11.get(1), "blue");
            view.renderMessage("Your image has been grey-scaled!");
          }
          break;
        case "blur":
          ArrayList<String> list12 = new ArrayList<String>(Arrays.asList(scan.next(), scan.next()));
          if (list12.get(1).contains(".")) {
            this.partialManipulator(list12.get(0), list12.get(1), scan.next(), "blurred");
            view.renderMessage("Your image has been partially blurred!");
          }
          else {
            model.blur(list12.get(0), list12.get(1));
            view.renderMessage("Your image has been blurred!");
          }
          break;
        case "sharpen":
          ArrayList<String> list13 = new ArrayList<String>(Arrays.asList(scan.next(), scan.next()));
          if (list13.get(1).contains(".")) {
            this.partialManipulator(list13.get(0), list13.get(1), scan.next(), "sharpened");
            view.renderMessage("Your image has been partially sharpened!");
          }
          else {
            model.sharpen(list13.get(0), list13.get(1));
            view.renderMessage("Your image has been sharpened!");
          }
          break;
        case "greyscale":
          ArrayList<String> list14 = new ArrayList<String>(Arrays.asList(scan.next(), scan.next()));
          if (list14.get(1).contains(".")) {
            this.partialManipulator(list14.get(0), list14.get(1), scan.next(), "greyscaled");
            view.renderMessage("Your image has been partially grey-scaled!");
          }
          else {
            model.greyscaleKernel(list14.get(0), list14.get(1));
            view.renderMessage("Your image has been grey-scaled!");
          }
          break;
        case "sepia":
          ArrayList<String> list15 = new ArrayList<String>(Arrays.asList(scan.next(), scan.next()));
          if (list15.get(1).contains(".")) {
            this.partialManipulator(list15.get(0), list15.get(1), scan.next(), "sepia");
            view.renderMessage("Your image has been partially filtered using a sepia filter!");
          }
          else {
            model.sepia(list15.get(0), list15.get(1));
            view.renderMessage("Your image has been filtered using a sepia filter!");
          }
          break;
        default:
          view.renderMessage("Sorry, none of these are valid commands!");
          break;
      }
    }
  }

  // helper that loads a ppm file
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
    Image desiredImage = model.getDesiredImage(fileName);
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

    Image desiredImage = model.getDesiredImage(fileName);

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

  // intro message that tells the user what they can input.
  private void introMess() {
    try {
      view.renderMessage("Here are things you can do to your image: \n" +
                       "load (i.e. load images/selfie.ppm selfie)\n" +
                       "brighten (i.e. brighten 10 selfie brightSelfie)\n" +
                       "vertical-flip (i.e. vertical-flip selfie selfie-vertical)\n" +
                       "horizontal-flip (i.e. horizontal-flip selfie selfie-horizontal)\n" +
                       "value-component (i.e. value-component selfie selfie-greyscale)\n" +
                       "intensity-component (i.e. intensity-component selfie selfie-greyscale)\n" +
                       "luma-component (i.e. luma-component selfie selfie-greyscale)\n" +
                       "red-component (i.e. red-component selfie selfie-greyscale)\n" +
                       "green-component (i.e. green-component selfie selfie-greyscale)\n" +
                       "blue-component (i.e. blue-component selfie selfie-greyscale)\n" +
                       "blur (i.e. blur selfie selfie-blurred)\n" +
                       "sharpen (i.e. sharpen selfie selfie-sharpened)\n" +
                       "sepia (i.e. sepia selfie selfie-sepia)");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }
}

