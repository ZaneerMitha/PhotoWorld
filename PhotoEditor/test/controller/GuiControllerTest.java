package controller;

import org.junit.Test;
import java.io.IOException;
import java.util.ArrayList;
import model.BasicImage;
import model.BasicPhotoEditorModel;
import model.BasicPixel;
import model.Image;
import model.Pixel;
import model.Posn;
import model.RGBColor;
import model.SuperPhotoEditorModel;
import view.PhotoEditorGuiView;
import view.SwingGuiView;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * A JUnit test all the methods in the GuiController.
 */
public class GuiControllerTest {

  @Test
  public void invalidConstructor() {
    try {
      PhotoEditorGuiView view = null;
      SuperPhotoEditorModel model = new BasicPhotoEditorModel();
      PhotoEditorGuiController c = new GuiController(model, view);
    } catch (IllegalArgumentException ex) {
      assertEquals("View cannot be null!", ex.getMessage());
    }

    try {
      PhotoEditorGuiView view = new SwingGuiView();
      SuperPhotoEditorModel model = null;
      PhotoEditorGuiController c = new GuiController(model, view);
    } catch (IllegalArgumentException ex) {
      assertEquals("Model cannot be null!", ex.getMessage());
    }
  }

  @Test
  public void testLoad() {
    PhotoEditorGuiView view = new SwingGuiView();
    SuperPhotoEditorModel model = new BasicPhotoEditorModel();
    PhotoEditorGuiController c = new GuiController(model, view);

    try {
      c.load("res/manhattan-small.png", "manhattan-small");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    Image manhattan = model.getDesiredImage("manhattan-small");
    assertEquals(500, manhattan.getWidth());
    assertEquals(200, manhattan.getHeight());

    Pixel firstPixel2 = manhattan.getPixelAt(0, 0);
    assertEquals(212, firstPixel2.getPixelValue());
    assertEquals(182.33333333333334, firstPixel2.getPixelIntensity(), 0.0);
    assertEquals(178.5032, firstPixel2.getPixelLuma(), 0.0);

    Pixel secondPixel2 = manhattan.getPixelAt(0, 1);
    assertEquals(212, secondPixel2.getPixelValue());
    assertEquals(182.33333333333334, secondPixel2.getPixelIntensity(), 0.0);
    assertEquals(178.5032, secondPixel2.getPixelLuma(), 0.0);

    Pixel thirdPixel2 = manhattan.getPixelAt(0, 2);
    assertEquals(212, thirdPixel2.getPixelValue());
    assertEquals(182.33333333333334, thirdPixel2.getPixelIntensity(), 0.0);
    assertEquals(178.5032, thirdPixel2.getPixelLuma(), 0.0);
  }

  @Test
  public void testSave() {
    RGBColor color = new RGBColor(0, 0, 0);
    Posn zeroZero = new Posn(0, 0);
    Posn zeroOne = new Posn(0, 1);
    Posn zeroTwo = new Posn(0, 2);
    Posn oneZero = new Posn(1, 0);
    Posn oneOne = new Posn(1, 1);
    Posn oneTwo = new Posn(1, 2);
    Posn twoZero = new Posn(2, 0);
    Posn twoOne = new Posn(2, 1);
    Posn twoTwo = new Posn(2, 2);


    BasicPixel one = new BasicPixel(zeroZero, color);
    BasicPixel two = new BasicPixel(zeroOne, color);
    BasicPixel three = new BasicPixel(zeroTwo, color);
    BasicPixel four = new BasicPixel(oneZero, color);
    BasicPixel five = new BasicPixel(oneOne, color);
    BasicPixel six = new BasicPixel(oneTwo, color);
    BasicPixel seven = new BasicPixel(twoZero, color);
    BasicPixel eight = new BasicPixel(twoOne, color);
    BasicPixel nine = new BasicPixel(twoTwo, color);

    ArrayList<ArrayList<BasicPixel>> pixels = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOne = new ArrayList<BasicPixel>();
    rowOne.add(one);
    rowOne.add(two);
    rowOne.add(three);

    ArrayList<BasicPixel> rowTwo = new ArrayList<BasicPixel>();
    rowTwo.add(four);
    rowTwo.add(five);
    rowTwo.add(six);

    ArrayList<BasicPixel> rowThree = new ArrayList<BasicPixel>();
    rowThree.add(seven);
    rowThree.add(eight);
    rowThree.add(nine);

    pixels.add(rowOne);
    pixels.add(rowTwo);
    pixels.add(rowThree);

    BasicImage image = new BasicImage(pixels);

    SuperPhotoEditorModel model = new BasicPhotoEditorModel();
    model.acceptImage("image", image);

    PhotoEditorGuiView view = new SwingGuiView();
    PhotoEditorGuiController c = new GuiController(model, view);

    try {
      c.save("images/image.ppm", "image");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    try {
      c.load("images/image.ppm", "image");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    Image koala = model.getDesiredImage("image");
    assertEquals(3, koala.getWidth());
    assertEquals(3, koala.getHeight());

    Pixel firstPixel = koala.getPixelAt(0, 0);
    assertEquals(0, firstPixel.getPixelValue());
    assertEquals(0, firstPixel.getPixelIntensity(), 0.0);
    assertEquals(0, firstPixel.getPixelLuma(), 0.0);

    Pixel secondPixel = koala.getPixelAt(0, 1);
    assertEquals(0, secondPixel.getPixelValue());
    assertEquals(0, secondPixel.getPixelIntensity(), 0.0);
    assertEquals(0, secondPixel.getPixelLuma(), 0.0);

    Pixel thirdPixel = koala.getPixelAt(0, 2);
    assertEquals(0, thirdPixel.getPixelValue());
    assertEquals(0, thirdPixel.getPixelIntensity(), 0.0);
    assertEquals(0, thirdPixel.getPixelLuma(), 0.0);
  }


  @Test
  public void testActionPerformedVerticalFLip() {
    RGBColor color = new RGBColor(0, 0, 0);
    Posn zeroZero = new Posn(0, 0);
    Posn zeroOne = new Posn(0, 1);
    Posn zeroTwo = new Posn(0, 2);
    Posn oneZero = new Posn(1, 0);
    Posn oneOne = new Posn(1, 1);
    Posn oneTwo = new Posn(1, 2);
    Posn twoZero = new Posn(2, 0);
    Posn twoOne = new Posn(2, 1);
    Posn twoTwo = new Posn(2, 2);


    BasicPixel one = new BasicPixel(zeroZero, color);
    BasicPixel two = new BasicPixel(zeroOne, color);
    BasicPixel three = new BasicPixel(zeroTwo, color);
    BasicPixel four = new BasicPixel(oneZero, color);
    BasicPixel five = new BasicPixel(oneOne, color);
    BasicPixel six = new BasicPixel(oneTwo, color);
    BasicPixel seven = new BasicPixel(twoZero, color);
    BasicPixel eight = new BasicPixel(twoOne, color);
    BasicPixel nine = new BasicPixel(twoTwo, color);

    ArrayList<ArrayList<BasicPixel>> pixels = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOne = new ArrayList<BasicPixel>();
    rowOne.add(one);
    rowOne.add(two);
    rowOne.add(three);

    ArrayList<BasicPixel> rowTwo = new ArrayList<BasicPixel>();
    rowTwo.add(four);
    rowTwo.add(five);
    rowTwo.add(six);

    ArrayList<BasicPixel> rowThree = new ArrayList<BasicPixel>();
    rowThree.add(seven);
    rowThree.add(eight);
    rowThree.add(nine);

    pixels.add(rowOne);
    pixels.add(rowTwo);
    pixels.add(rowThree);

    BasicImage image = new BasicImage(pixels);

    SuperPhotoEditorModel model = new BasicPhotoEditorModel();
    model.acceptImage("image", image);

    PhotoEditorGuiView view = new SwingGuiView();
    PhotoEditorGuiController c = new GuiController(model, view);

    model.flipVertical("image", "image-vertical");

    ArrayList<ArrayList<BasicPixel>> pixels2 = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOneFlipH = new ArrayList<BasicPixel>();
    rowOneFlipH.add(three);
    rowOneFlipH.add(two);
    rowOneFlipH.add(one);

    ArrayList<BasicPixel> rowTwoFlipH = new ArrayList<BasicPixel>();
    rowTwoFlipH.add(six);
    rowTwoFlipH.add(five);
    rowTwoFlipH.add(four);

    ArrayList<BasicPixel> rowThreeFlipH = new ArrayList<BasicPixel>();
    rowThreeFlipH.add(nine);
    rowThreeFlipH.add(eight);
    rowThreeFlipH.add(seven);

    pixels2.add(rowOneFlipH);
    pixels2.add(rowTwoFlipH);
    pixels2.add(rowThreeFlipH);

    Image image2 = new BasicImage(pixels2);

    assertEquals(image2, model.getDesiredImage("image-vertical"));
  }

  @Test
  public void testActionPerformedFlipHorizontal() {
    RGBColor color = new RGBColor(0, 0, 0);
    Posn zeroZero = new Posn(0, 0);
    Posn zeroOne = new Posn(0, 1);
    Posn zeroTwo = new Posn(0, 2);
    Posn oneZero = new Posn(1, 0);
    Posn oneOne = new Posn(1, 1);
    Posn oneTwo = new Posn(1, 2);
    Posn twoZero = new Posn(2, 0);
    Posn twoOne = new Posn(2, 1);
    Posn twoTwo = new Posn(2, 2);


    BasicPixel one = new BasicPixel(zeroZero, color);
    BasicPixel two = new BasicPixel(zeroOne, color);
    BasicPixel three = new BasicPixel(zeroTwo, color);
    BasicPixel four = new BasicPixel(oneZero, color);
    BasicPixel five = new BasicPixel(oneOne, color);
    BasicPixel six = new BasicPixel(oneTwo, color);
    BasicPixel seven = new BasicPixel(twoZero, color);
    BasicPixel eight = new BasicPixel(twoOne, color);
    BasicPixel nine = new BasicPixel(twoTwo, color);

    ArrayList<ArrayList<BasicPixel>> pixels = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOne = new ArrayList<BasicPixel>();
    rowOne.add(one);
    rowOne.add(two);
    rowOne.add(three);

    ArrayList<BasicPixel> rowTwo = new ArrayList<BasicPixel>();
    rowTwo.add(four);
    rowTwo.add(five);
    rowTwo.add(six);

    ArrayList<BasicPixel> rowThree = new ArrayList<BasicPixel>();
    rowThree.add(seven);
    rowThree.add(eight);
    rowThree.add(nine);

    pixels.add(rowOne);
    pixels.add(rowTwo);
    pixels.add(rowThree);

    BasicImage image = new BasicImage(pixels);

    SuperPhotoEditorModel model = new BasicPhotoEditorModel();
    model.acceptImage("image", image);

    PhotoEditorGuiView view = new SwingGuiView();
    PhotoEditorGuiController c = new GuiController(model, view);

    model.flipHorizontal("image", "image-horizontal");

    ArrayList<ArrayList<BasicPixel>> pixelsFlipV = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOneFlipV = new ArrayList<BasicPixel>();
    rowOneFlipV.add(one);
    rowOneFlipV.add(two);
    rowOneFlipV.add(three);

    ArrayList<BasicPixel> rowTwoFlipV = new ArrayList<BasicPixel>();
    rowTwoFlipV.add(four);
    rowTwoFlipV.add(five);
    rowTwoFlipV.add(six);

    ArrayList<BasicPixel> rowThreeFlipV = new ArrayList<BasicPixel>();
    rowThreeFlipV.add(seven);
    rowThreeFlipV.add(eight);
    rowThreeFlipV.add(nine);

    pixelsFlipV.add(rowThreeFlipV);
    pixelsFlipV.add(rowTwoFlipV);
    pixelsFlipV.add(rowOneFlipV);

    Image image2 = new BasicImage(pixelsFlipV);
    assertEquals(image2, model.getDesiredImage("image-horizontal"));
  }

  @Test
  public void testActionPerformedBrighten() {
    RGBColor color = new RGBColor(0, 0, 0);
    Posn zeroZero = new Posn(0, 0);
    Posn zeroOne = new Posn(0, 1);
    Posn zeroTwo = new Posn(0, 2);
    Posn oneZero = new Posn(1, 0);
    Posn oneOne = new Posn(1, 1);
    Posn oneTwo = new Posn(1, 2);
    Posn twoZero = new Posn(2, 0);
    Posn twoOne = new Posn(2, 1);
    Posn twoTwo = new Posn(2, 2);

    BasicPixel one = new BasicPixel(zeroZero, color);
    BasicPixel two = new BasicPixel(zeroOne, color);
    BasicPixel three = new BasicPixel(zeroTwo, color);
    BasicPixel four = new BasicPixel(oneZero, color);
    BasicPixel five = new BasicPixel(oneOne, color);
    BasicPixel six = new BasicPixel(oneTwo, color);
    BasicPixel seven = new BasicPixel(twoZero, color);
    BasicPixel eight = new BasicPixel(twoOne, color);
    BasicPixel nine = new BasicPixel(twoTwo, color);

    ArrayList<ArrayList<BasicPixel>> pixels = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOne = new ArrayList<BasicPixel>();
    rowOne.add(one);
    rowOne.add(two);
    rowOne.add(three);

    ArrayList<BasicPixel> rowTwo = new ArrayList<BasicPixel>();
    rowTwo.add(four);
    rowTwo.add(five);
    rowTwo.add(six);

    ArrayList<BasicPixel> rowThree = new ArrayList<BasicPixel>();
    rowThree.add(seven);
    rowThree.add(eight);
    rowThree.add(nine);

    pixels.add(rowOne);
    pixels.add(rowTwo);
    pixels.add(rowThree);

    BasicImage image = new BasicImage(pixels);

    SuperPhotoEditorModel model = new BasicPhotoEditorModel();
    model.acceptImage("image", image);

    PhotoEditorGuiView view = new SwingGuiView();
    PhotoEditorGuiController c = new GuiController(model, view);

    model.brightenOrDarken("image", "image-brighter", 10);

    one = new BasicPixel(zeroZero, color.brightenOrDarkenColor(10));
    two = new BasicPixel(zeroOne, color.brightenOrDarkenColor(10));
    three = new BasicPixel(zeroTwo, color.brightenOrDarkenColor(10));
    four = new BasicPixel(oneZero, color.brightenOrDarkenColor(10));
    five = new BasicPixel(oneOne, color.brightenOrDarkenColor(10));
    six = new BasicPixel(oneTwo, color.brightenOrDarkenColor(10));
    seven = new BasicPixel(twoZero, color.brightenOrDarkenColor(10));
    eight = new BasicPixel(twoOne, color.brightenOrDarkenColor(10));
    nine = new BasicPixel(twoTwo, color.brightenOrDarkenColor(10));

    ArrayList<ArrayList<BasicPixel>> pixelsBright = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOneBright = new ArrayList<BasicPixel>();
    rowOneBright.add(one);
    rowOneBright.add(two);
    rowOneBright.add(three);

    ArrayList<BasicPixel> rowTwoBright = new ArrayList<BasicPixel>();
    rowTwoBright.add(four);
    rowTwoBright.add(five);
    rowTwoBright.add(six);

    ArrayList<BasicPixel> rowThreeBright = new ArrayList<BasicPixel>();
    rowThreeBright.add(seven);
    rowThreeBright.add(eight);
    rowThreeBright.add(nine);

    pixelsBright.add(rowOneBright);
    pixelsBright.add(rowTwoBright);
    pixelsBright.add(rowThreeBright);

    Image image2 = new BasicImage(pixelsBright);
    assertEquals(image2, model.getDesiredImage("image-brighter"));
  }

  @Test
  public void testGoDarken() {
    RGBColor color = new RGBColor(0, 0, 0);
    Posn zeroZero = new Posn(0, 0);
    Posn zeroOne = new Posn(0, 1);
    Posn zeroTwo = new Posn(0, 2);
    Posn oneZero = new Posn(1, 0);
    Posn oneOne = new Posn(1, 1);
    Posn oneTwo = new Posn(1, 2);
    Posn twoZero = new Posn(2, 0);
    Posn twoOne = new Posn(2, 1);
    Posn twoTwo = new Posn(2, 2);

    BasicPixel one = new BasicPixel(zeroZero, color);
    BasicPixel two = new BasicPixel(zeroOne, color);
    BasicPixel three = new BasicPixel(zeroTwo, color);
    BasicPixel four = new BasicPixel(oneZero, color);
    BasicPixel five = new BasicPixel(oneOne, color);
    BasicPixel six = new BasicPixel(oneTwo, color);
    BasicPixel seven = new BasicPixel(twoZero, color);
    BasicPixel eight = new BasicPixel(twoOne, color);
    BasicPixel nine = new BasicPixel(twoTwo, color);

    ArrayList<ArrayList<BasicPixel>> pixels = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOne = new ArrayList<BasicPixel>();
    rowOne.add(one);
    rowOne.add(two);
    rowOne.add(three);

    ArrayList<BasicPixel> rowTwo = new ArrayList<BasicPixel>();
    rowTwo.add(four);
    rowTwo.add(five);
    rowTwo.add(six);

    ArrayList<BasicPixel> rowThree = new ArrayList<BasicPixel>();
    rowThree.add(seven);
    rowThree.add(eight);
    rowThree.add(nine);

    pixels.add(rowOne);
    pixels.add(rowTwo);
    pixels.add(rowThree);

    BasicImage image = new BasicImage(pixels);

    SuperPhotoEditorModel model = new BasicPhotoEditorModel();
    model.acceptImage("image", image);

    model.brightenOrDarken("image", "image-darker", -10);

    one = new BasicPixel(zeroZero, color.brightenOrDarkenColor(-10));
    two = new BasicPixel(zeroOne, color.brightenOrDarkenColor(-10));
    three = new BasicPixel(zeroTwo, color.brightenOrDarkenColor(-10));
    four = new BasicPixel(oneZero, color.brightenOrDarkenColor(-10));
    five = new BasicPixel(oneOne, color.brightenOrDarkenColor(-10));
    six = new BasicPixel(oneTwo, color.brightenOrDarkenColor(-10));
    seven = new BasicPixel(twoZero, color.brightenOrDarkenColor(-10));
    eight = new BasicPixel(twoOne, color.brightenOrDarkenColor(-10));
    nine = new BasicPixel(twoTwo, color.brightenOrDarkenColor(-10));

    ArrayList<ArrayList<BasicPixel>> pixelsDark = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOneDark = new ArrayList<BasicPixel>();
    rowOneDark.add(one);
    rowOneDark.add(two);
    rowOneDark.add(three);

    ArrayList<BasicPixel> rowTwoDark = new ArrayList<BasicPixel>();
    rowTwoDark.add(four);
    rowTwoDark.add(five);
    rowTwoDark.add(six);

    ArrayList<BasicPixel> rowThreeDark = new ArrayList<BasicPixel>();
    rowThreeDark.add(seven);
    rowThreeDark.add(eight);
    rowThreeDark.add(nine);

    pixelsDark.add(rowOneDark);
    pixelsDark.add(rowTwoDark);
    pixelsDark.add(rowThreeDark);

    Image image3 = new BasicImage(pixelsDark);
    assertEquals(image3, model.getDesiredImage("image-darker"));
  }

  @Test
  public void testActionPerformedVisualizeRed() {
    RGBColor color = new RGBColor(3, 0, 0);

    Posn zeroZero = new Posn(0, 0);
    Posn zeroOne = new Posn(0, 1);
    Posn zeroTwo = new Posn(0, 2);
    Posn oneZero = new Posn(1, 0);
    Posn oneOne = new Posn(1, 1);
    Posn oneTwo = new Posn(1, 2);
    Posn twoZero = new Posn(2, 0);
    Posn twoOne = new Posn(2, 1);
    Posn twoTwo = new Posn(2, 2);


    BasicPixel one = new BasicPixel(zeroZero, color);
    BasicPixel two = new BasicPixel(zeroOne, color);
    BasicPixel three = new BasicPixel(zeroTwo, color);
    BasicPixel four = new BasicPixel(oneZero, color);
    BasicPixel five = new BasicPixel(oneOne, color);
    BasicPixel six = new BasicPixel(oneTwo, color);
    BasicPixel seven = new BasicPixel(twoZero, color);
    BasicPixel eight = new BasicPixel(twoOne, color);
    BasicPixel nine = new BasicPixel(twoTwo, color);

    ArrayList<ArrayList<BasicPixel>> pixels = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOne = new ArrayList<BasicPixel>();
    rowOne.add(one);
    rowOne.add(two);
    rowOne.add(three);

    ArrayList<BasicPixel> rowTwo = new ArrayList<BasicPixel>();
    rowTwo.add(four);
    rowTwo.add(five);
    rowTwo.add(six);

    ArrayList<BasicPixel> rowThree = new ArrayList<BasicPixel>();
    rowThree.add(seven);
    rowThree.add(eight);
    rowThree.add(nine);

    pixels.add(rowOne);
    pixels.add(rowTwo);
    pixels.add(rowThree);

    BasicImage image2 = new BasicImage(pixels);

    SuperPhotoEditorModel model = new BasicPhotoEditorModel();
    model.acceptImage("image2", image2);

    PhotoEditorGuiView view = new SwingGuiView();
    PhotoEditorGuiController c = new GuiController(model, view);

    model.greyscale("image2", "image2-red", "red");

    BasicPixel oneRed = new BasicPixel(zeroZero, new RGBColor(3, 3, 3));
    BasicPixel twoRed = new BasicPixel(zeroOne, new RGBColor(3, 3, 3));
    BasicPixel threeRed = new BasicPixel(zeroTwo, new RGBColor(3, 3, 3));
    BasicPixel fourRed = new BasicPixel(oneZero, new RGBColor(3, 3, 3));
    BasicPixel fiveRed = new BasicPixel(oneOne, new RGBColor(3, 3, 3));
    BasicPixel sixRed = new BasicPixel(oneTwo, new RGBColor(3, 3, 3));
    BasicPixel sevenRed = new BasicPixel(twoZero, new RGBColor(3, 3, 3));
    BasicPixel eightRed = new BasicPixel(twoOne, new RGBColor(3, 3, 3));
    BasicPixel nineRed = new BasicPixel(twoTwo, new RGBColor(3, 3, 3));

    ArrayList<ArrayList<BasicPixel>> pixelsRed = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOneRed = new ArrayList<BasicPixel>();
    rowOneRed.add(oneRed);
    rowOneRed.add(twoRed);
    rowOneRed.add(threeRed);

    ArrayList<BasicPixel> rowTwoRed = new ArrayList<BasicPixel>();
    rowTwoRed.add(fourRed);
    rowTwoRed.add(fiveRed);
    rowTwoRed.add(sixRed);

    ArrayList<BasicPixel> rowThreeRed = new ArrayList<BasicPixel>();
    rowThreeRed.add(sevenRed);
    rowThreeRed.add(eightRed);
    rowThreeRed.add(nineRed);

    pixelsRed.add(rowOneRed);
    pixelsRed.add(rowTwoRed);
    pixelsRed.add(rowThreeRed);

    Image imageRed = new BasicImage(pixelsRed);
    assertTrue(imageRed.equals(model.getDesiredImage("image2-red")));
  }

  @Test
  public void testActionPerformedVisualizeGreen() {
    RGBColor color = new RGBColor(0, 4, 0);

    Posn zeroZero = new Posn(0, 0);
    Posn zeroOne = new Posn(0, 1);
    Posn zeroTwo = new Posn(0, 2);
    Posn oneZero = new Posn(1, 0);
    Posn oneOne = new Posn(1, 1);
    Posn oneTwo = new Posn(1, 2);
    Posn twoZero = new Posn(2, 0);
    Posn twoOne = new Posn(2, 1);
    Posn twoTwo = new Posn(2, 2);


    BasicPixel one = new BasicPixel(zeroZero, color);
    BasicPixel two = new BasicPixel(zeroOne, color);
    BasicPixel three = new BasicPixel(zeroTwo, color);
    BasicPixel four = new BasicPixel(oneZero, color);
    BasicPixel five = new BasicPixel(oneOne, color);
    BasicPixel six = new BasicPixel(oneTwo, color);
    BasicPixel seven = new BasicPixel(twoZero, color);
    BasicPixel eight = new BasicPixel(twoOne, color);
    BasicPixel nine = new BasicPixel(twoTwo, color);

    ArrayList<ArrayList<BasicPixel>> pixels = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOne = new ArrayList<BasicPixel>();
    rowOne.add(one);
    rowOne.add(two);
    rowOne.add(three);

    ArrayList<BasicPixel> rowTwo = new ArrayList<BasicPixel>();
    rowTwo.add(four);
    rowTwo.add(five);
    rowTwo.add(six);

    ArrayList<BasicPixel> rowThree = new ArrayList<BasicPixel>();
    rowThree.add(seven);
    rowThree.add(eight);
    rowThree.add(nine);

    pixels.add(rowOne);
    pixels.add(rowTwo);
    pixels.add(rowThree);

    BasicImage image2 = new BasicImage(pixels);

    SuperPhotoEditorModel model = new BasicPhotoEditorModel();
    model.acceptImage("image2", image2);


    PhotoEditorGuiView view = new SwingGuiView();
    PhotoEditorGuiController c = new GuiController(model, view);

    model.greyscale("image2", "image2-greyscale", "green");

    BasicPixel oneGreen = new BasicPixel(zeroZero, new RGBColor(4, 4, 4));
    BasicPixel twoGreen = new BasicPixel(zeroOne, new RGBColor(4, 4, 4));
    BasicPixel threeGreen = new BasicPixel(zeroTwo, new RGBColor(4, 4, 4));
    BasicPixel fourGreen = new BasicPixel(oneZero, new RGBColor(4, 4, 4));
    BasicPixel fiveGreen = new BasicPixel(oneOne, new RGBColor(4, 4, 4));
    BasicPixel sixGreen = new BasicPixel(oneTwo, new RGBColor(4, 4, 4));
    BasicPixel sevenGreen = new BasicPixel(twoZero, new RGBColor(4, 4, 4));
    BasicPixel eightGreen = new BasicPixel(twoOne, new RGBColor(4, 4, 4));
    BasicPixel nineGreen = new BasicPixel(twoTwo, new RGBColor(4, 4, 4));

    ArrayList<ArrayList<BasicPixel>> pixelsGreen = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOneGreen = new ArrayList<BasicPixel>();
    rowOneGreen.add(oneGreen);
    rowOneGreen.add(twoGreen);
    rowOneGreen.add(threeGreen);

    ArrayList<BasicPixel> rowTwoGreen = new ArrayList<BasicPixel>();
    rowTwoGreen.add(fourGreen);
    rowTwoGreen.add(fiveGreen);
    rowTwoGreen.add(sixGreen);

    ArrayList<BasicPixel> rowThreeGreen = new ArrayList<BasicPixel>();
    rowThreeGreen.add(sevenGreen);
    rowThreeGreen.add(eightGreen);
    rowThreeGreen.add(nineGreen);

    pixelsGreen.add(rowOneGreen);
    pixelsGreen.add(rowTwoGreen);
    pixelsGreen.add(rowThreeGreen);

    Image imageRed = new BasicImage(pixelsGreen);
    assertTrue(imageRed.equals(model.getDesiredImage("image2-greyscale")));
  }

  @Test
  public void testActionPerformedVisualizeBlue() {
    RGBColor color = new RGBColor(0, 0, 5);

    Posn zeroZero = new Posn(0, 0);
    Posn zeroOne = new Posn(0, 1);
    Posn zeroTwo = new Posn(0, 2);
    Posn oneZero = new Posn(1, 0);
    Posn oneOne = new Posn(1, 1);
    Posn oneTwo = new Posn(1, 2);
    Posn twoZero = new Posn(2, 0);
    Posn twoOne = new Posn(2, 1);
    Posn twoTwo = new Posn(2, 2);


    BasicPixel one = new BasicPixel(zeroZero, color);
    BasicPixel two = new BasicPixel(zeroOne, color);
    BasicPixel three = new BasicPixel(zeroTwo, color);
    BasicPixel four = new BasicPixel(oneZero, color);
    BasicPixel five = new BasicPixel(oneOne, color);
    BasicPixel six = new BasicPixel(oneTwo, color);
    BasicPixel seven = new BasicPixel(twoZero, color);
    BasicPixel eight = new BasicPixel(twoOne, color);
    BasicPixel nine = new BasicPixel(twoTwo, color);

    ArrayList<ArrayList<BasicPixel>> pixels = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOne = new ArrayList<BasicPixel>();
    rowOne.add(one);
    rowOne.add(two);
    rowOne.add(three);

    ArrayList<BasicPixel> rowTwo = new ArrayList<BasicPixel>();
    rowTwo.add(four);
    rowTwo.add(five);
    rowTwo.add(six);

    ArrayList<BasicPixel> rowThree = new ArrayList<BasicPixel>();
    rowThree.add(seven);
    rowThree.add(eight);
    rowThree.add(nine);

    pixels.add(rowOne);
    pixels.add(rowTwo);
    pixels.add(rowThree);

    BasicImage image2 = new BasicImage(pixels);

    SuperPhotoEditorModel model = new BasicPhotoEditorModel();
    model.acceptImage("image2", image2);

    PhotoEditorGuiView view = new SwingGuiView();
    PhotoEditorGuiController c = new GuiController(model, view);

    model.greyscale("image2", "image2-greyscale", "blue");

    BasicPixel oneBlue = new BasicPixel(zeroZero, new RGBColor(5, 5, 5));
    BasicPixel twoBlue = new BasicPixel(zeroOne, new RGBColor(5, 5, 5));
    BasicPixel threeBlue = new BasicPixel(zeroTwo, new RGBColor(5, 5, 5));
    BasicPixel fourBlue = new BasicPixel(oneZero, new RGBColor(5, 5, 5));
    BasicPixel fiveBlue = new BasicPixel(oneOne, new RGBColor(5, 5, 5));
    BasicPixel sixBlue = new BasicPixel(oneTwo, new RGBColor(5, 5, 5));
    BasicPixel sevenBlue = new BasicPixel(twoZero, new RGBColor(5, 5, 5));
    BasicPixel eightBlue = new BasicPixel(twoOne, new RGBColor(5, 5, 5));
    BasicPixel nineBlue = new BasicPixel(twoTwo, new RGBColor(5, 5, 5));

    ArrayList<ArrayList<BasicPixel>> pixelsBlue = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOneBlue = new ArrayList<BasicPixel>();
    rowOneBlue.add(oneBlue);
    rowOneBlue.add(twoBlue);
    rowOneBlue.add(threeBlue);

    ArrayList<BasicPixel> rowTwoBlue = new ArrayList<BasicPixel>();
    rowTwoBlue.add(fourBlue);
    rowTwoBlue.add(fiveBlue);
    rowTwoBlue.add(sixBlue);

    ArrayList<BasicPixel> rowThreeBlue = new ArrayList<BasicPixel>();
    rowThreeBlue.add(sevenBlue);
    rowThreeBlue.add(eightBlue);
    rowThreeBlue.add(nineBlue);

    pixelsBlue.add(rowOneBlue);
    pixelsBlue.add(rowTwoBlue);
    pixelsBlue.add(rowThreeBlue);

    Image imageRed = new BasicImage(pixelsBlue);
    assertTrue(imageRed.equals(model.getDesiredImage("image2-greyscale")));
  }

  @Test
  public void testActionPerformedVisualizeValue() {
    RGBColor color = new RGBColor(15, 10, 5);

    Posn zeroZero = new Posn(0, 0);
    Posn zeroOne = new Posn(0, 1);
    Posn zeroTwo = new Posn(0, 2);
    Posn oneZero = new Posn(1, 0);
    Posn oneOne = new Posn(1, 1);
    Posn oneTwo = new Posn(1, 2);
    Posn twoZero = new Posn(2, 0);
    Posn twoOne = new Posn(2, 1);
    Posn twoTwo = new Posn(2, 2);


    BasicPixel one = new BasicPixel(zeroZero, color);
    BasicPixel two = new BasicPixel(zeroOne, color);
    BasicPixel three = new BasicPixel(zeroTwo, color);
    BasicPixel four = new BasicPixel(oneZero, color);
    BasicPixel five = new BasicPixel(oneOne, color);
    BasicPixel six = new BasicPixel(oneTwo, color);
    BasicPixel seven = new BasicPixel(twoZero, color);
    BasicPixel eight = new BasicPixel(twoOne, color);
    BasicPixel nine = new BasicPixel(twoTwo, color);

    ArrayList<ArrayList<BasicPixel>> pixels = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOne = new ArrayList<BasicPixel>();
    rowOne.add(one);
    rowOne.add(two);
    rowOne.add(three);

    ArrayList<BasicPixel> rowTwo = new ArrayList<BasicPixel>();
    rowTwo.add(four);
    rowTwo.add(five);
    rowTwo.add(six);

    ArrayList<BasicPixel> rowThree = new ArrayList<BasicPixel>();
    rowThree.add(seven);
    rowThree.add(eight);
    rowThree.add(nine);

    pixels.add(rowOne);
    pixels.add(rowTwo);
    pixels.add(rowThree);

    BasicImage image2 = new BasicImage(pixels);

    SuperPhotoEditorModel model = new BasicPhotoEditorModel();
    model.acceptImage("image2", image2);

    PhotoEditorGuiView view = new SwingGuiView();
    PhotoEditorGuiController c = new GuiController(model, view);

    model.greyscale("image2", "image2-greyscale", "value");

    BasicPixel oneBlue = new BasicPixel(zeroZero, new RGBColor(15, 15, 15));
    BasicPixel twoBlue = new BasicPixel(zeroOne, new RGBColor(15, 15, 15));
    BasicPixel threeBlue = new BasicPixel(zeroTwo, new RGBColor(15, 15, 15));
    BasicPixel fourBlue = new BasicPixel(oneZero, new RGBColor(15, 15, 15));
    BasicPixel fiveBlue = new BasicPixel(oneOne, new RGBColor(15, 15, 15));
    BasicPixel sixBlue = new BasicPixel(oneTwo, new RGBColor(15, 15, 15));
    BasicPixel sevenBlue = new BasicPixel(twoZero, new RGBColor(15, 15, 15));
    BasicPixel eightBlue = new BasicPixel(twoOne, new RGBColor(15, 15, 15));
    BasicPixel nineBlue = new BasicPixel(twoTwo, new RGBColor(15, 15, 15));

    ArrayList<ArrayList<BasicPixel>> pixelsValue = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOneValue = new ArrayList<BasicPixel>();
    rowOneValue.add(oneBlue);
    rowOneValue.add(twoBlue);
    rowOneValue.add(threeBlue);

    ArrayList<BasicPixel> rowTwoValue = new ArrayList<BasicPixel>();
    rowTwoValue.add(fourBlue);
    rowTwoValue.add(fiveBlue);
    rowTwoValue.add(sixBlue);

    ArrayList<BasicPixel> rowThreeValue = new ArrayList<BasicPixel>();
    rowThreeValue.add(sevenBlue);
    rowThreeValue.add(eightBlue);
    rowThreeValue.add(nineBlue);

    pixelsValue.add(rowOneValue);
    pixelsValue.add(rowTwoValue);
    pixelsValue.add(rowThreeValue);

    Image imageRed = new BasicImage(pixelsValue);
    assertTrue(imageRed.equals(model.getDesiredImage("image2-greyscale")));
  }

  @Test
  public void testActionPerformedVisualizeIntensity() {
    RGBColor color = new RGBColor(15, 10, 5);

    Posn zeroZero = new Posn(0, 0);
    Posn zeroOne = new Posn(0, 1);
    Posn zeroTwo = new Posn(0, 2);
    Posn oneZero = new Posn(1, 0);
    Posn oneOne = new Posn(1, 1);
    Posn oneTwo = new Posn(1, 2);
    Posn twoZero = new Posn(2, 0);
    Posn twoOne = new Posn(2, 1);
    Posn twoTwo = new Posn(2, 2);


    BasicPixel one = new BasicPixel(zeroZero, color);
    BasicPixel two = new BasicPixel(zeroOne, color);
    BasicPixel three = new BasicPixel(zeroTwo, color);
    BasicPixel four = new BasicPixel(oneZero, color);
    BasicPixel five = new BasicPixel(oneOne, color);
    BasicPixel six = new BasicPixel(oneTwo, color);
    BasicPixel seven = new BasicPixel(twoZero, color);
    BasicPixel eight = new BasicPixel(twoOne, color);
    BasicPixel nine = new BasicPixel(twoTwo, color);

    ArrayList<ArrayList<BasicPixel>> pixels = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOne = new ArrayList<BasicPixel>();
    rowOne.add(one);
    rowOne.add(two);
    rowOne.add(three);

    ArrayList<BasicPixel> rowTwo = new ArrayList<BasicPixel>();
    rowTwo.add(four);
    rowTwo.add(five);
    rowTwo.add(six);

    ArrayList<BasicPixel> rowThree = new ArrayList<BasicPixel>();
    rowThree.add(seven);
    rowThree.add(eight);
    rowThree.add(nine);

    pixels.add(rowOne);
    pixels.add(rowTwo);
    pixels.add(rowThree);

    BasicImage image2 = new BasicImage(pixels);

    SuperPhotoEditorModel model = new BasicPhotoEditorModel();
    model.acceptImage("image2", image2);

    PhotoEditorGuiView view = new SwingGuiView();
    PhotoEditorGuiController c = new GuiController(model, view);

    model.greyscale("image2", "image2-greyscale", "intensity");

    BasicPixel oneBlue = new BasicPixel(zeroZero, new RGBColor(10, 10, 10));
    BasicPixel twoBlue = new BasicPixel(zeroOne, new RGBColor(10, 10, 10));
    BasicPixel threeBlue = new BasicPixel(zeroTwo, new RGBColor(10, 10, 10));
    BasicPixel fourBlue = new BasicPixel(oneZero, new RGBColor(10, 10, 10));
    BasicPixel fiveBlue = new BasicPixel(oneOne, new RGBColor(10, 10, 10));
    BasicPixel sixBlue = new BasicPixel(oneTwo, new RGBColor(10, 10, 10));
    BasicPixel sevenBlue = new BasicPixel(twoZero, new RGBColor(10, 10, 10));
    BasicPixel eightBlue = new BasicPixel(twoOne, new RGBColor(10, 10, 10));
    BasicPixel nineBlue = new BasicPixel(twoTwo, new RGBColor(10, 10, 10));

    ArrayList<ArrayList<BasicPixel>> pixelsIntensity = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOneIntensity = new ArrayList<BasicPixel>();
    rowOneIntensity.add(oneBlue);
    rowOneIntensity.add(twoBlue);
    rowOneIntensity.add(threeBlue);

    ArrayList<BasicPixel> rowTwoIntensity = new ArrayList<BasicPixel>();
    rowTwoIntensity.add(fourBlue);
    rowTwoIntensity.add(fiveBlue);
    rowTwoIntensity.add(sixBlue);

    ArrayList<BasicPixel> rowThreeIntensity = new ArrayList<BasicPixel>();
    rowThreeIntensity.add(sevenBlue);
    rowThreeIntensity.add(eightBlue);
    rowThreeIntensity.add(nineBlue);

    pixelsIntensity.add(rowOneIntensity);
    pixelsIntensity.add(rowTwoIntensity);
    pixelsIntensity.add(rowThreeIntensity);

    Image imageRed = new BasicImage(pixelsIntensity);
    assertTrue(imageRed.equals(model.getDesiredImage("image2-greyscale")));
  }

  @Test
  public void testActionPerformedVisualizeLuma() {
    RGBColor color = new RGBColor(15, 10, 5);

    Posn zeroZero = new Posn(0, 0);
    Posn zeroOne = new Posn(0, 1);
    Posn zeroTwo = new Posn(0, 2);
    Posn oneZero = new Posn(1, 0);
    Posn oneOne = new Posn(1, 1);
    Posn oneTwo = new Posn(1, 2);
    Posn twoZero = new Posn(2, 0);
    Posn twoOne = new Posn(2, 1);
    Posn twoTwo = new Posn(2, 2);


    BasicPixel one = new BasicPixel(zeroZero, color);
    BasicPixel two = new BasicPixel(zeroOne, color);
    BasicPixel three = new BasicPixel(zeroTwo, color);
    BasicPixel four = new BasicPixel(oneZero, color);
    BasicPixel five = new BasicPixel(oneOne, color);
    BasicPixel six = new BasicPixel(oneTwo, color);
    BasicPixel seven = new BasicPixel(twoZero, color);
    BasicPixel eight = new BasicPixel(twoOne, color);
    BasicPixel nine = new BasicPixel(twoTwo, color);

    ArrayList<ArrayList<BasicPixel>> pixels = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOne = new ArrayList<BasicPixel>();
    rowOne.add(one);
    rowOne.add(two);
    rowOne.add(three);

    ArrayList<BasicPixel> rowTwo = new ArrayList<BasicPixel>();
    rowTwo.add(four);
    rowTwo.add(five);
    rowTwo.add(six);

    ArrayList<BasicPixel> rowThree = new ArrayList<BasicPixel>();
    rowThree.add(seven);
    rowThree.add(eight);
    rowThree.add(nine);

    pixels.add(rowOne);
    pixels.add(rowTwo);
    pixels.add(rowThree);

    BasicImage image2 = new BasicImage(pixels);

    SuperPhotoEditorModel model = new BasicPhotoEditorModel();
    model.acceptImage("image2", image2);

    PhotoEditorGuiView view = new SwingGuiView();
    PhotoEditorGuiController c = new GuiController(model, view);

    model.greyscale("image2", "image2-greyscale", "luma");

    BasicPixel oneLuma = new BasicPixel(zeroZero, new RGBColor(11, 11, 11));
    BasicPixel twoLuma = new BasicPixel(zeroOne, new RGBColor(11, 11, 11));
    BasicPixel threeLuma = new BasicPixel(zeroTwo, new RGBColor(11, 11, 11));
    BasicPixel fourLuma = new BasicPixel(oneZero, new RGBColor(11, 11, 11));
    BasicPixel fiveLuma = new BasicPixel(oneOne, new RGBColor(11, 11, 11));
    BasicPixel sixLuma = new BasicPixel(oneTwo, new RGBColor(11, 11, 11));
    BasicPixel sevenLuma = new BasicPixel(twoZero, new RGBColor(11, 11, 11));
    BasicPixel eightLuma = new BasicPixel(twoOne, new RGBColor(11, 11, 11));
    BasicPixel nineLuma = new BasicPixel(twoTwo, new RGBColor(11, 11, 11));

    ArrayList<ArrayList<BasicPixel>> pixelsLuma = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOneLuma = new ArrayList<BasicPixel>();
    rowOneLuma.add(oneLuma);
    rowOneLuma.add(twoLuma);
    rowOneLuma.add(threeLuma);

    ArrayList<BasicPixel> rowTwoLuma = new ArrayList<BasicPixel>();
    rowTwoLuma.add(fourLuma);
    rowTwoLuma.add(fiveLuma);
    rowTwoLuma.add(sixLuma);

    ArrayList<BasicPixel> rowThreeLuma = new ArrayList<BasicPixel>();
    rowThreeLuma.add(sevenLuma);
    rowThreeLuma.add(eightLuma);
    rowThreeLuma.add(nineLuma);

    pixelsLuma.add(rowOneLuma);
    pixelsLuma.add(rowTwoLuma);
    pixelsLuma.add(rowThreeLuma);

    Image imageLuma = new BasicImage(pixelsLuma);
    assertTrue(imageLuma.equals(model.getDesiredImage("image2-greyscale")));
  }

  @Test
  public void testActionPerformedBlur() {

    Posn zeroZero = new Posn(0,0);
    Posn zeroOne = new Posn(0,1);
    Posn zeroTwo = new Posn(0,2);
    Posn oneZero = new Posn(1,0);
    Posn oneOne = new Posn(1,1);
    Posn oneTwo = new Posn(1,2);
    Posn twoZero = new Posn(2,0);
    Posn twoOne = new Posn(2,1);
    Posn twoTwo = new Posn(2,2);


    BasicPixel one = new BasicPixel(zeroZero, new RGBColor(100,20,10));
    BasicPixel two = new BasicPixel(zeroOne, new RGBColor(100,20,10));
    BasicPixel three = new BasicPixel(zeroTwo, new RGBColor(100,20,10));
    BasicPixel four = new BasicPixel(oneZero, new RGBColor(40,22,76));
    BasicPixel five = new BasicPixel(oneOne, new RGBColor(40,22,76));
    BasicPixel six = new BasicPixel(oneTwo, new RGBColor(40,22,76));
    BasicPixel seven = new BasicPixel(twoZero, new RGBColor(20,220,100));
    BasicPixel eight = new BasicPixel(twoOne, new RGBColor(20,220,100));
    BasicPixel nine = new BasicPixel(twoTwo, new RGBColor(20,220,100));


    ArrayList<ArrayList<BasicPixel>> pixels = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOne = new ArrayList<BasicPixel>();
    rowOne.add(one);
    rowOne.add(two);
    rowOne.add(three);

    ArrayList<BasicPixel> rowTwo = new ArrayList<BasicPixel>();
    rowTwo.add(four);
    rowTwo.add(five);
    rowTwo.add(six);

    ArrayList<BasicPixel> rowThree = new ArrayList<BasicPixel>();
    rowThree.add(seven);
    rowThree.add(eight);
    rowThree.add(nine);

    pixels.add(rowOne);
    pixels.add(rowTwo);
    pixels.add(rowThree);


    BasicImage image2 = new BasicImage(pixels);
    SuperPhotoEditorModel model = new BasicPhotoEditorModel();
    model.acceptImage("image2", image2);

    PhotoEditorGuiView view = new SwingGuiView();
    PhotoEditorGuiController c = new GuiController(model, view);

    model.blur("image2", "image2-blurred");

    BasicPixel oneBlur = new BasicPixel(zeroZero, new RGBColor(100,20,10));
    BasicPixel twoBlur = new BasicPixel(zeroOne, new RGBColor(100,20,10));
    BasicPixel threeBlur = new BasicPixel(zeroTwo, new RGBColor(100,20,10));

    BasicPixel fourBlur = new BasicPixel(oneZero,  new RGBColor(40,22,76));
    BasicPixel fiveBlur = new BasicPixel(oneOne, new RGBColor(50, 73, 67));
    BasicPixel sixBlur = new BasicPixel(oneTwo,  new RGBColor(40,22,76));

    BasicPixel sevenBlur = new BasicPixel(twoZero, new RGBColor(20,220,100));
    BasicPixel eightBlur = new BasicPixel(twoOne, new RGBColor(20,220,100));
    BasicPixel nineBlur = new BasicPixel(twoTwo, new RGBColor(20,220,100));


    ArrayList<ArrayList<BasicPixel>> pixelsBlur = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOneBlur = new ArrayList<BasicPixel>();
    rowOneBlur.add(oneBlur);
    rowOneBlur.add(twoBlur);
    rowOneBlur.add(threeBlur);

    ArrayList<BasicPixel> rowTwoBlur = new ArrayList<BasicPixel>();
    rowTwoBlur.add(fourBlur);
    rowTwoBlur.add(fiveBlur);
    rowTwoBlur.add(sixBlur);

    ArrayList<BasicPixel> rowThreeBlur = new ArrayList<BasicPixel>();
    rowThreeBlur.add(sevenBlur);
    rowThreeBlur.add(eightBlur);
    rowThreeBlur.add(nineBlur);

    pixelsBlur.add(rowOneBlur);
    pixelsBlur.add(rowTwoBlur);
    pixelsBlur.add(rowThreeBlur);

    Image imageBlur = new BasicImage(pixelsBlur);
    assertTrue(imageBlur.equals(model.getDesiredImage("image2-blurred")));
  }

  @Test
  public void testActionPerformedSharpen() {
    Posn zeroZero = new Posn(0,0);
    Posn zeroOne = new Posn(0,1);
    Posn zeroTwo = new Posn(0,2);
    Posn zeroThree = new Posn(0,3);
    Posn zeroFour = new Posn(0,4);

    Posn oneZero = new Posn(1,0);
    Posn oneOne = new Posn(1,1);
    Posn oneTwo = new Posn(1,2);
    Posn oneThree = new Posn(1,3);
    Posn oneFour = new Posn(1,4);

    Posn twoZero = new Posn(2,0);
    Posn twoOne = new Posn(2,1);
    Posn twoTwo = new Posn(2,2);
    Posn twoThree = new Posn(2,3);
    Posn twoFour = new Posn(2,4);

    Posn threeZero = new Posn(3,0);
    Posn threeOne = new Posn(3,1);
    Posn threeTwo = new Posn(3,2);
    Posn threeThree = new Posn(3,3);
    Posn threeFour = new Posn(3,4);

    Posn fourZero = new Posn(4,0);
    Posn fourOne = new Posn(4,1);
    Posn fourTwo = new Posn(4,2);
    Posn fourThree = new Posn(4,3);
    Posn fourFour = new Posn(4,4);


    BasicPixel one = new BasicPixel(zeroZero, new RGBColor(100,20,10));
    BasicPixel two = new BasicPixel(zeroOne, new RGBColor(100,20,10));
    BasicPixel three = new BasicPixel(zeroTwo, new RGBColor(100,20,10));
    BasicPixel four = new BasicPixel(zeroThree, new RGBColor(100,20,10));
    BasicPixel five = new BasicPixel(zeroFour, new RGBColor(100,20,10));

    BasicPixel six = new BasicPixel(oneZero, new RGBColor(40,22,76));
    BasicPixel seven = new BasicPixel(oneOne, new RGBColor(40,22,76));
    BasicPixel eight = new BasicPixel(oneTwo, new RGBColor(40,22,76));
    BasicPixel nine = new BasicPixel(oneThree, new RGBColor(40,22,76));
    BasicPixel ten = new BasicPixel(oneFour, new RGBColor(40,22,76));

    BasicPixel eleven = new BasicPixel(twoZero, new RGBColor(20,220,100));
    BasicPixel twelve = new BasicPixel(twoOne, new RGBColor(20,220,100));
    BasicPixel thirteen = new BasicPixel(twoTwo, new RGBColor(20,220,100));
    BasicPixel fourteen = new BasicPixel(twoThree, new RGBColor(20,220,100));
    BasicPixel fifteen = new BasicPixel(twoFour, new RGBColor(20,220,100));

    BasicPixel sixteen = new BasicPixel(threeZero, new RGBColor(100,20,10));
    BasicPixel seventeen = new BasicPixel(threeOne, new RGBColor(100,20,10));
    BasicPixel eighteen = new BasicPixel(threeTwo, new RGBColor(100,20,10));
    BasicPixel nineteen = new BasicPixel(threeThree, new RGBColor(100,20,10));
    BasicPixel twenty = new BasicPixel(threeFour, new RGBColor(100,20,10));

    BasicPixel twentyOne = new BasicPixel(fourZero, new RGBColor(40,22,76));
    BasicPixel twentyTwo = new BasicPixel(fourOne, new RGBColor(40,22,76));
    BasicPixel twentyThree = new BasicPixel(fourTwo, new RGBColor(40,22,76));
    BasicPixel twentyFour = new BasicPixel(fourThree, new RGBColor(40,22,76));
    BasicPixel twentyFive = new BasicPixel(fourFour, new RGBColor(40,22,76));

    ArrayList<ArrayList<BasicPixel>> pixels = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOne = new ArrayList<BasicPixel>();
    rowOne.add(one);
    rowOne.add(two);
    rowOne.add(three);
    rowOne.add(four);
    rowOne.add(five);

    ArrayList<BasicPixel> rowTwo = new ArrayList<BasicPixel>();
    rowTwo.add(six);
    rowTwo.add(seven);
    rowTwo.add(eight);
    rowTwo.add(nine);
    rowTwo.add(ten);

    ArrayList<BasicPixel> rowThree = new ArrayList<BasicPixel>();
    rowThree.add(eleven);
    rowThree.add(twelve);
    rowThree.add(thirteen);
    rowThree.add(fourteen);
    rowThree.add(fifteen);

    ArrayList<BasicPixel> rowFour = new ArrayList<BasicPixel>();
    rowFour.add(sixteen);
    rowFour.add(seventeen);
    rowFour.add(eighteen);
    rowFour.add(nineteen);
    rowFour.add(twenty);

    ArrayList<BasicPixel> rowFive = new ArrayList<BasicPixel>();
    rowFive.add(twentyOne);
    rowFive.add(twentyTwo);
    rowFive.add(twentyThree);
    rowFive.add(twentyFour);
    rowFive.add(twentyFive);

    pixels.add(rowOne);
    pixels.add(rowTwo);
    pixels.add(rowThree);
    pixels.add(rowFour);
    pixels.add(rowFive);

    BasicImage image3 = new BasicImage(pixels);
    SuperPhotoEditorModel model = new BasicPhotoEditorModel();
    model.acceptImage("image3", image3);

    PhotoEditorGuiView view = new SwingGuiView();
    PhotoEditorGuiController c = new GuiController(model, view);

    model.sharpen("image3", "image3-sharpen");

    BasicPixel oneSharpen = new BasicPixel(zeroZero, new RGBColor(100,20,10));
    BasicPixel twoSharpen = new BasicPixel(zeroOne, new RGBColor(100,20,10));
    BasicPixel threeSharpen = new BasicPixel(zeroTwo, new RGBColor(100,20,10));
    BasicPixel fourSharpen = new BasicPixel(zeroThree, new RGBColor(100,20,10));
    BasicPixel fiveSharpen = new BasicPixel(zeroFour, new RGBColor(100,20,10));

    BasicPixel sixSharpen = new BasicPixel(oneZero, new RGBColor(40,22,76));
    BasicPixel sevenSharpen = new BasicPixel(oneOne, new RGBColor(40,22,76));
    BasicPixel eightSharpen = new BasicPixel(oneTwo, new RGBColor(40,22,76));
    BasicPixel nineSharpen = new BasicPixel(oneThree, new RGBColor(40,22,76));
    BasicPixel tenSharpen = new BasicPixel(oneFour, new RGBColor(40,22,76));

    BasicPixel elevenSharpen = new BasicPixel(twoZero, new RGBColor(20,220,100));
    BasicPixel twelveSharpen = new BasicPixel(twoOne, new RGBColor(20,220,100));
    BasicPixel thirteenSharpen = new BasicPixel(twoTwo, new RGBColor(12,255,122));
    BasicPixel fourteenSharpen = new BasicPixel(twoThree, new RGBColor(20,220,100));
    BasicPixel fifteenSharpen = new BasicPixel(twoFour, new RGBColor(20,220,100));

    BasicPixel sixteenSharpen = new BasicPixel(threeZero, new RGBColor(100,20,10));
    BasicPixel seventeenSharpen = new BasicPixel(threeOne, new RGBColor(100,20,10));
    BasicPixel eighteenSharpen = new BasicPixel(threeTwo, new RGBColor(100,20,10));
    BasicPixel nineteenSharpen = new BasicPixel(threeThree, new RGBColor(100,20,10));
    BasicPixel twentySharpen = new BasicPixel(threeFour, new RGBColor(100,20,10));

    BasicPixel twentyOneSharpen = new BasicPixel(fourZero, new RGBColor(40,22,76));
    BasicPixel twentyTwoSharpen = new BasicPixel(fourOne, new RGBColor(40,22,76));
    BasicPixel twentyThreeSharpen = new BasicPixel(fourTwo, new RGBColor(40,22,76));
    BasicPixel twentyFourSharpen = new BasicPixel(fourThree, new RGBColor(40,22,76));
    BasicPixel twentyFiveSharpen = new BasicPixel(fourFour, new RGBColor(40,22,76));


    ArrayList<ArrayList<BasicPixel>> pixelsSharpen = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOneSharpen = new ArrayList<BasicPixel>();
    rowOneSharpen.add(oneSharpen);
    rowOneSharpen.add(twoSharpen);
    rowOneSharpen.add(threeSharpen);
    rowOneSharpen.add(fourSharpen);
    rowOneSharpen.add(fiveSharpen);

    ArrayList<BasicPixel> rowTwoSharpen = new ArrayList<BasicPixel>();
    rowTwoSharpen.add(sixSharpen);
    rowTwoSharpen.add(sevenSharpen);
    rowTwoSharpen.add(eightSharpen);
    rowTwoSharpen.add(nineSharpen);
    rowTwoSharpen.add(tenSharpen);

    ArrayList<BasicPixel> rowThreeSharpen = new ArrayList<BasicPixel>();
    rowThreeSharpen.add(elevenSharpen);
    rowThreeSharpen.add(twelveSharpen);
    rowThreeSharpen.add(thirteenSharpen);
    rowThreeSharpen.add(fourteenSharpen);
    rowThreeSharpen.add(fifteenSharpen);

    ArrayList<BasicPixel> rowFourSharpen = new ArrayList<BasicPixel>();
    rowFourSharpen.add(sixteenSharpen);
    rowFourSharpen.add(seventeenSharpen);
    rowFourSharpen.add(eighteenSharpen);
    rowFourSharpen.add(nineteenSharpen);
    rowFourSharpen.add(twentySharpen);

    ArrayList<BasicPixel> rowFiveSharpen = new ArrayList<BasicPixel>();
    rowFiveSharpen.add(twentyOneSharpen);
    rowFiveSharpen.add(twentyTwoSharpen);
    rowFiveSharpen.add(twentyThreeSharpen);
    rowFiveSharpen.add(twentyFourSharpen);
    rowFiveSharpen.add(twentyFiveSharpen);

    pixelsSharpen.add(rowOneSharpen);
    pixelsSharpen.add(rowTwoSharpen);
    pixelsSharpen.add(rowThreeSharpen);
    pixelsSharpen.add(rowFourSharpen);
    pixelsSharpen.add(rowFiveSharpen);

    Image imageSharpen = new BasicImage(pixelsSharpen);
    assertTrue(imageSharpen.equals(model.getDesiredImage("image3-sharpen")));
  }

  @Test
  public void testActionPerformedSepiaTone() {
    Posn zeroZero = new Posn(0, 0);
    Posn zeroOne = new Posn(0, 1);
    Posn zeroTwo = new Posn(0, 2);
    Posn oneZero = new Posn(1, 0);
    Posn oneOne = new Posn(1, 1);
    Posn oneTwo = new Posn(1, 2);
    Posn twoZero = new Posn(2, 0);
    Posn twoOne = new Posn(2, 1);
    Posn twoTwo = new Posn(2, 2);

    BasicPixel one = new BasicPixel(zeroZero, new RGBColor(100, 20, 10));
    BasicPixel two = new BasicPixel(zeroOne, new RGBColor(100, 20, 10));
    BasicPixel three = new BasicPixel(zeroTwo, new RGBColor(100, 20, 10));
    BasicPixel four = new BasicPixel(oneZero, new RGBColor(40, 22, 76));
    BasicPixel five = new BasicPixel(oneOne, new RGBColor(40, 22, 76));
    BasicPixel six = new BasicPixel(oneTwo, new RGBColor(40, 22, 76));
    BasicPixel seven = new BasicPixel(twoZero, new RGBColor(20, 220, 100));
    BasicPixel eight = new BasicPixel(twoOne, new RGBColor(20, 220, 100));
    BasicPixel nine = new BasicPixel(twoTwo, new RGBColor(20, 220, 100));

    ArrayList<ArrayList<BasicPixel>> pixels = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOne = new ArrayList<BasicPixel>();
    rowOne.add(one);
    rowOne.add(two);
    rowOne.add(three);

    ArrayList<BasicPixel> rowTwo = new ArrayList<BasicPixel>();
    rowTwo.add(four);
    rowTwo.add(five);
    rowTwo.add(six);

    ArrayList<BasicPixel> rowThree = new ArrayList<BasicPixel>();
    rowThree.add(seven);
    rowThree.add(eight);
    rowThree.add(nine);

    pixels.add(rowOne);
    pixels.add(rowTwo);
    pixels.add(rowThree);

    BasicImage image2 = new BasicImage(pixels);
    SuperPhotoEditorModel model = new BasicPhotoEditorModel();
    model.acceptImage("image2", image2);

    PhotoEditorGuiView view = new SwingGuiView();
    PhotoEditorGuiController c = new GuiController(model, view);

    model.sepia("image2", "image2-sepia");

    BasicPixel oneSepia = new BasicPixel(zeroZero, new RGBColor(57, 50, 39));
    BasicPixel twoSepia = new BasicPixel(zeroOne, new RGBColor(57, 50, 39));
    BasicPixel threeSepia = new BasicPixel(zeroTwo, new RGBColor(57, 50, 39));

    BasicPixel fourSepia = new BasicPixel(oneZero, new RGBColor(47, 42, 33));
    BasicPixel fiveSepia = new BasicPixel(oneOne, new RGBColor(47, 42, 33));
    BasicPixel sixSepia = new BasicPixel(oneTwo, new RGBColor(47, 42, 33));

    BasicPixel sevenSepia = new BasicPixel(twoZero, new RGBColor(196, 175, 136));
    BasicPixel eightSepia = new BasicPixel(twoOne, new RGBColor(196, 175, 136));
    BasicPixel nineSepia = new BasicPixel(twoTwo, new RGBColor(196, 175, 136));

    ArrayList<ArrayList<BasicPixel>> pixelsSepia = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOneSepia = new ArrayList<BasicPixel>();
    rowOneSepia.add(oneSepia);
    rowOneSepia.add(twoSepia);
    rowOneSepia.add(threeSepia);

    ArrayList<BasicPixel> rowTwoSepia = new ArrayList<BasicPixel>();
    rowTwoSepia.add(fourSepia);
    rowTwoSepia.add(fiveSepia);
    rowTwoSepia.add(sixSepia);

    ArrayList<BasicPixel> rowThreeSepia = new ArrayList<BasicPixel>();
    rowThreeSepia.add(sevenSepia);
    rowThreeSepia.add(eightSepia);
    rowThreeSepia.add(nineSepia);

    pixelsSepia.add(rowOneSepia);
    pixelsSepia.add(rowTwoSepia);
    pixelsSepia.add(rowThreeSepia);

    Image imageSepia = new BasicImage(pixelsSepia);
    assertTrue(imageSepia.equals(model.getDesiredImage("image2-sepia")));
  }

  @Test
  public void testGreyscaleKernelImage() {
    Posn zeroZero = new Posn(0,0);
    Posn zeroOne = new Posn(0,1);
    Posn zeroTwo = new Posn(0,2);
    Posn oneZero = new Posn(1,0);
    Posn oneOne = new Posn(1,1);
    Posn oneTwo = new Posn(1,2);
    Posn twoZero = new Posn(2,0);
    Posn twoOne = new Posn(2,1);
    Posn twoTwo = new Posn(2,2);


    BasicPixel one = new BasicPixel(zeroZero, new RGBColor(100,20,10));
    BasicPixel two = new BasicPixel(zeroOne, new RGBColor(100,20,10));
    BasicPixel three = new BasicPixel(zeroTwo, new RGBColor(100,20,10));
    BasicPixel four = new BasicPixel(oneZero, new RGBColor(40,22,76));
    BasicPixel five = new BasicPixel(oneOne, new RGBColor(40,22,76));
    BasicPixel six = new BasicPixel(oneTwo, new RGBColor(40,22,76));
    BasicPixel seven = new BasicPixel(twoZero, new RGBColor(20,220,100));
    BasicPixel eight = new BasicPixel(twoOne, new RGBColor(20,220,100));
    BasicPixel nine = new BasicPixel(twoTwo, new RGBColor(20,220,100));


    ArrayList<ArrayList<BasicPixel>> pixels = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOne = new ArrayList<BasicPixel>();
    rowOne.add(one);
    rowOne.add(two);
    rowOne.add(three);

    ArrayList<BasicPixel> rowTwo = new ArrayList<BasicPixel>();
    rowTwo.add(four);
    rowTwo.add(five);
    rowTwo.add(six);

    ArrayList<BasicPixel> rowThree = new ArrayList<BasicPixel>();
    rowThree.add(seven);
    rowThree.add(eight);
    rowThree.add(nine);

    pixels.add(rowOne);
    pixels.add(rowTwo);
    pixels.add(rowThree);


    BasicImage image2 = new BasicImage(pixels);
    SuperPhotoEditorModel model = new BasicPhotoEditorModel();
    model.acceptImage("image2", image2);

    PhotoEditorGuiView view = new SwingGuiView();
    PhotoEditorGuiController c = new GuiController(model, view);

    model.greyscaleKernel("image2", "image2-greyscale");

    BasicPixel oneGreyscale = new BasicPixel(zeroZero, new RGBColor(36,36,36));
    BasicPixel twoGreyscale = new BasicPixel(zeroOne, new RGBColor(36,36,36));
    BasicPixel threeGreyscale = new BasicPixel(zeroTwo, new RGBColor(36,36,36));

    BasicPixel fourGreyscale = new BasicPixel(oneZero,  new RGBColor(30,30,30));
    BasicPixel fiveGreyscale = new BasicPixel(oneOne, new RGBColor(30,30,30));
    BasicPixel sixGreyscale = new BasicPixel(oneTwo,  new RGBColor(30,30,30));

    BasicPixel sevenGreyscale = new BasicPixel(twoZero, new RGBColor(169,169,169));
    BasicPixel eightGreyscale = new BasicPixel(twoOne, new RGBColor(169,169,169));
    BasicPixel nineGreyscale = new BasicPixel(twoTwo, new RGBColor(169,169,169));


    ArrayList<ArrayList<BasicPixel>> pixelsGreyscale = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOneGreyscale = new ArrayList<BasicPixel>();
    rowOneGreyscale.add(oneGreyscale);
    rowOneGreyscale.add(twoGreyscale);
    rowOneGreyscale.add(threeGreyscale);

    ArrayList<BasicPixel> rowTwoGreyscale = new ArrayList<BasicPixel>();
    rowTwoGreyscale.add(fourGreyscale);
    rowTwoGreyscale.add(fiveGreyscale);
    rowTwoGreyscale.add(sixGreyscale);

    ArrayList<BasicPixel> rowThreeGreyscale = new ArrayList<BasicPixel>();
    rowThreeGreyscale.add(sevenGreyscale);
    rowThreeGreyscale.add(eightGreyscale);
    rowThreeGreyscale.add(nineGreyscale);

    pixelsGreyscale.add(rowOneGreyscale);
    pixelsGreyscale.add(rowTwoGreyscale);
    pixelsGreyscale.add(rowThreeGreyscale);

    Image imageGreyscale = new BasicImage(pixelsGreyscale);
    assertTrue(imageGreyscale.equals(model.getDesiredImage("image2-greyscale")));
  }
}