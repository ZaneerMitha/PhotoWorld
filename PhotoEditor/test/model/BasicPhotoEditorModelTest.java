package model;

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * A JUnit test for the model.BasicPhotoEditorModel class.
 */
public class BasicPhotoEditorModelTest {

  BasicImage image;

  Posn zeroZero;
  Posn zeroOne;
  Posn zeroTwo;
  Posn oneZero;
  Posn oneOne;
  Posn oneTwo;
  Posn twoZero;
  Posn twoOne;
  Posn twoTwo;

  BasicPixel one;
  BasicPixel two;
  BasicPixel three;
  BasicPixel four;
  BasicPixel five;
  BasicPixel six;
  BasicPixel seven;
  BasicPixel eight;
  BasicPixel nine;
  SuperPhotoEditorModel photoEditor;

  RGBColor color;

  ArrayList<ArrayList<BasicPixel>> pixels;
  ArrayList<BasicPixel> rowOne;
  ArrayList<BasicPixel> rowTwo;
  ArrayList<BasicPixel> rowThree;

  /**
   * Initial image data.
   */
  @Before
  public void initData() {
    color = new RGBColor(0,0,0);
    zeroZero = new Posn(0,0);
    zeroOne = new Posn(0,1);
    zeroTwo = new Posn(0,2);
    oneZero = new Posn(1,0);
    oneOne = new Posn(1,1);
    oneTwo = new Posn(1,2);
    twoZero = new Posn(2,0);
    twoOne = new Posn(2,1);
    twoTwo = new Posn(2,2);


    one = new BasicPixel(zeroZero, color);
    two = new BasicPixel(zeroOne, color);
    three = new BasicPixel(zeroTwo, color);
    four = new BasicPixel(oneZero, color);
    five = new BasicPixel(oneOne, color);
    six = new BasicPixel(oneTwo, color);
    seven = new BasicPixel(twoZero, color);
    eight = new BasicPixel(twoOne, color);
    nine = new BasicPixel(twoTwo, color);

    pixels = new ArrayList<ArrayList<BasicPixel>>();

    rowOne = new ArrayList<BasicPixel>();
    rowOne.add(one);
    rowOne.add(two);
    rowOne.add(three);

    rowTwo = new ArrayList<BasicPixel>();
    rowTwo.add(four);
    rowTwo.add(five);
    rowTwo.add(six);

    rowThree = new ArrayList<BasicPixel>();
    rowThree.add(seven);
    rowThree.add(eight);
    rowThree.add(nine);

    pixels.add(rowOne);
    pixels.add(rowTwo);
    pixels.add(rowThree);

    image = new BasicImage(pixels);

    photoEditor = new BasicPhotoEditorModel();
    photoEditor.acceptImage("image", image);

  }

  @Test
  public void flipVertical() {
    ArrayList<ArrayList<BasicPixel>> pixels2 = new ArrayList<ArrayList<BasicPixel>>();

    ArrayList<BasicPixel> rowOne = new ArrayList<BasicPixel>();
    rowOne.add(three);
    rowOne.add(two);
    rowOne.add(one);

    ArrayList<BasicPixel> rowTwo = new ArrayList<BasicPixel>();
    rowTwo.add(six);
    rowTwo.add(five);
    rowTwo.add(four);

    ArrayList<BasicPixel> rowThree = new ArrayList<BasicPixel>();
    rowThree.add(nine);
    rowThree.add(eight);
    rowThree.add(seven);

    pixels2.add(rowOne);
    pixels2.add(rowTwo);
    pixels2.add(rowThree);

    Image image2 = new BasicImage(pixels2);

    photoEditor.flipVertical("image", "image-horizontal");
    assertEquals(image2, photoEditor.getDesiredImage("image-horizontal"));
  }

  @Test
  public void flipHorizontal() {

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

    pixels.add(rowThree);
    pixels.add(rowTwo);
    pixels.add(rowOne);

    Image image2 = new BasicImage(pixels);
    photoEditor.flipHorizontal("image", "image-vertical");
    assertEquals(image2, photoEditor.getDesiredImage("image-vertical"));
  }

  @Test
  public void testBrightenOrDarkenImage() {
    RGBColor color = new RGBColor(0,0,0);

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

    pixelsBright.add(rowOne);
    pixelsBright.add(rowTwo);
    pixelsBright.add(rowThree);

    Image image2 = new BasicImage(pixelsBright);
    photoEditor.brightenOrDarken("image", "image-brighter", 10);
    assertEquals(image2, photoEditor.getDesiredImage("image-brighter"));

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
    photoEditor.brightenOrDarken("image", "image-darker", -10);
    assertEquals(image3, photoEditor.getDesiredImage("image-darker"));
  }

  @Test
  public void testVisualizeRed() {

    color = new RGBColor(3,0,0);

    zeroZero = new Posn(0,0);
    zeroOne = new Posn(0,1);
    zeroTwo = new Posn(0,2);
    oneZero = new Posn(1,0);
    oneOne = new Posn(1,1);
    oneTwo = new Posn(1,2);
    twoZero = new Posn(2,0);
    twoOne = new Posn(2,1);
    twoTwo = new Posn(2,2);


    one = new BasicPixel(zeroZero, color);
    two = new BasicPixel(zeroOne, color);
    three = new BasicPixel(zeroTwo, color);
    four = new BasicPixel(oneZero, color);
    five = new BasicPixel(oneOne, color);
    six = new BasicPixel(oneTwo, color);
    seven = new BasicPixel(twoZero, color);
    eight = new BasicPixel(twoOne, color);
    nine = new BasicPixel(twoTwo, color);

    pixels = new ArrayList<ArrayList<BasicPixel>>();

    rowOne = new ArrayList<BasicPixel>();
    rowOne.add(one);
    rowOne.add(two);
    rowOne.add(three);

    rowTwo = new ArrayList<BasicPixel>();
    rowTwo.add(four);
    rowTwo.add(five);
    rowTwo.add(six);

    rowThree = new ArrayList<BasicPixel>();
    rowThree.add(seven);
    rowThree.add(eight);
    rowThree.add(nine);

    pixels.add(rowOne);
    pixels.add(rowTwo);
    pixels.add(rowThree);

    BasicImage image2 = new BasicImage(pixels);

    photoEditor = new BasicPhotoEditorModel();
    photoEditor.acceptImage("image2", image2);

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
    photoEditor.greyscale("image2", "image2-greyscale", "red");
    assertTrue(imageRed.equals(photoEditor.getDesiredImage("image2-greyscale")));

  }

  @Test
  public void testVisualizeGreen() {

    color = new RGBColor(0,4,0);

    zeroZero = new Posn(0,0);
    zeroOne = new Posn(0,1);
    zeroTwo = new Posn(0,2);
    oneZero = new Posn(1,0);
    oneOne = new Posn(1,1);
    oneTwo = new Posn(1,2);
    twoZero = new Posn(2,0);
    twoOne = new Posn(2,1);
    twoTwo = new Posn(2,2);


    one = new BasicPixel(zeroZero, color);
    two = new BasicPixel(zeroOne, color);
    three = new BasicPixel(zeroTwo, color);
    four = new BasicPixel(oneZero, color);
    five = new BasicPixel(oneOne, color);
    six = new BasicPixel(oneTwo, color);
    seven = new BasicPixel(twoZero, color);
    eight = new BasicPixel(twoOne, color);
    nine = new BasicPixel(twoTwo, color);

    pixels = new ArrayList<ArrayList<BasicPixel>>();

    rowOne = new ArrayList<BasicPixel>();
    rowOne.add(one);
    rowOne.add(two);
    rowOne.add(three);

    rowTwo = new ArrayList<BasicPixel>();
    rowTwo.add(four);
    rowTwo.add(five);
    rowTwo.add(six);

    rowThree = new ArrayList<BasicPixel>();
    rowThree.add(seven);
    rowThree.add(eight);
    rowThree.add(nine);

    pixels.add(rowOne);
    pixels.add(rowTwo);
    pixels.add(rowThree);

    BasicImage image2 = new BasicImage(pixels);

    photoEditor = new BasicPhotoEditorModel();
    photoEditor.acceptImage("image2", image2);

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
    photoEditor.greyscale("image2", "image2-greyscale", "green");
    assertTrue(imageRed.equals(photoEditor.getDesiredImage("image2-greyscale")));

  }

  @Test
  public void testVisualizeBlue() {

    color = new RGBColor(0,0,5);

    zeroZero = new Posn(0,0);
    zeroOne = new Posn(0,1);
    zeroTwo = new Posn(0,2);
    oneZero = new Posn(1,0);
    oneOne = new Posn(1,1);
    oneTwo = new Posn(1,2);
    twoZero = new Posn(2,0);
    twoOne = new Posn(2,1);
    twoTwo = new Posn(2,2);


    one = new BasicPixel(zeroZero, color);
    two = new BasicPixel(zeroOne, color);
    three = new BasicPixel(zeroTwo, color);
    four = new BasicPixel(oneZero, color);
    five = new BasicPixel(oneOne, color);
    six = new BasicPixel(oneTwo, color);
    seven = new BasicPixel(twoZero, color);
    eight = new BasicPixel(twoOne, color);
    nine = new BasicPixel(twoTwo, color);

    pixels = new ArrayList<ArrayList<BasicPixel>>();

    rowOne = new ArrayList<BasicPixel>();
    rowOne.add(one);
    rowOne.add(two);
    rowOne.add(three);

    rowTwo = new ArrayList<BasicPixel>();
    rowTwo.add(four);
    rowTwo.add(five);
    rowTwo.add(six);

    rowThree = new ArrayList<BasicPixel>();
    rowThree.add(seven);
    rowThree.add(eight);
    rowThree.add(nine);

    pixels.add(rowOne);
    pixels.add(rowTwo);
    pixels.add(rowThree);

    BasicImage image2 = new BasicImage(pixels);

    photoEditor = new BasicPhotoEditorModel();
    photoEditor.acceptImage("image2", image2);

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
    photoEditor.greyscale("image2", "image2-greyscale", "blue");
    assertTrue(imageRed.equals(photoEditor.getDesiredImage("image2-greyscale")));

  }

  @Test
  public void testVisualizeValue() {

    color = new RGBColor(15,10,5);

    zeroZero = new Posn(0,0);
    zeroOne = new Posn(0,1);
    zeroTwo = new Posn(0,2);
    oneZero = new Posn(1,0);
    oneOne = new Posn(1,1);
    oneTwo = new Posn(1,2);
    twoZero = new Posn(2,0);
    twoOne = new Posn(2,1);
    twoTwo = new Posn(2,2);


    one = new BasicPixel(zeroZero, color);
    two = new BasicPixel(zeroOne, color);
    three = new BasicPixel(zeroTwo, color);
    four = new BasicPixel(oneZero, color);
    five = new BasicPixel(oneOne, color);
    six = new BasicPixel(oneTwo, color);
    seven = new BasicPixel(twoZero, color);
    eight = new BasicPixel(twoOne, color);
    nine = new BasicPixel(twoTwo, color);

    pixels = new ArrayList<ArrayList<BasicPixel>>();

    rowOne = new ArrayList<BasicPixel>();
    rowOne.add(one);
    rowOne.add(two);
    rowOne.add(three);

    rowTwo = new ArrayList<BasicPixel>();
    rowTwo.add(four);
    rowTwo.add(five);
    rowTwo.add(six);

    rowThree = new ArrayList<BasicPixel>();
    rowThree.add(seven);
    rowThree.add(eight);
    rowThree.add(nine);

    pixels.add(rowOne);
    pixels.add(rowTwo);
    pixels.add(rowThree);

    BasicImage image2 = new BasicImage(pixels);

    photoEditor = new BasicPhotoEditorModel();
    photoEditor.acceptImage("image2", image2);

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
    photoEditor.greyscale("image2", "image2-greyscale", "value");
    assertTrue(imageRed.equals(photoEditor.getDesiredImage("image2-greyscale")));

  }

  @Test
  public void testVisualizeIntensity() {

    color = new RGBColor(15,10,5);

    zeroZero = new Posn(0,0);
    zeroOne = new Posn(0,1);
    zeroTwo = new Posn(0,2);
    oneZero = new Posn(1,0);
    oneOne = new Posn(1,1);
    oneTwo = new Posn(1,2);
    twoZero = new Posn(2,0);
    twoOne = new Posn(2,1);
    twoTwo = new Posn(2,2);


    one = new BasicPixel(zeroZero, color);
    two = new BasicPixel(zeroOne, color);
    three = new BasicPixel(zeroTwo, color);
    four = new BasicPixel(oneZero, color);
    five = new BasicPixel(oneOne, color);
    six = new BasicPixel(oneTwo, color);
    seven = new BasicPixel(twoZero, color);
    eight = new BasicPixel(twoOne, color);
    nine = new BasicPixel(twoTwo, color);

    pixels = new ArrayList<ArrayList<BasicPixel>>();

    rowOne = new ArrayList<BasicPixel>();
    rowOne.add(one);
    rowOne.add(two);
    rowOne.add(three);

    rowTwo = new ArrayList<BasicPixel>();
    rowTwo.add(four);
    rowTwo.add(five);
    rowTwo.add(six);

    rowThree = new ArrayList<BasicPixel>();
    rowThree.add(seven);
    rowThree.add(eight);
    rowThree.add(nine);

    pixels.add(rowOne);
    pixels.add(rowTwo);
    pixels.add(rowThree);

    BasicImage image2 = new BasicImage(pixels);

    photoEditor = new BasicPhotoEditorModel();
    photoEditor.acceptImage("image2", image2);

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
    photoEditor.greyscale("image2", "image2-greyscale", "intensity");
    assertTrue(imageRed.equals(photoEditor.getDesiredImage("image2-greyscale")));

  }

  @Test
  public void testVisualizeLuma() {

    color = new RGBColor(15,10,15);

    zeroZero = new Posn(0,0);
    zeroOne = new Posn(0,1);
    zeroTwo = new Posn(0,2);
    oneZero = new Posn(1,0);
    oneOne = new Posn(1,1);
    oneTwo = new Posn(1,2);
    twoZero = new Posn(2,0);
    twoOne = new Posn(2,1);
    twoTwo = new Posn(2,2);

    one = new BasicPixel(zeroZero, color);
    two = new BasicPixel(zeroOne, color);
    three = new BasicPixel(zeroTwo, color);
    four = new BasicPixel(oneZero, color);
    five = new BasicPixel(oneOne, color);
    six = new BasicPixel(oneTwo, color);
    seven = new BasicPixel(twoZero, color);
    eight = new BasicPixel(twoOne, color);
    nine = new BasicPixel(twoTwo, color);

    pixels = new ArrayList<ArrayList<BasicPixel>>();

    rowOne = new ArrayList<BasicPixel>();
    rowOne.add(one);
    rowOne.add(two);
    rowOne.add(three);

    rowTwo = new ArrayList<BasicPixel>();
    rowTwo.add(four);
    rowTwo.add(five);
    rowTwo.add(six);

    rowThree = new ArrayList<BasicPixel>();
    rowThree.add(seven);
    rowThree.add(eight);
    rowThree.add(nine);

    pixels.add(rowOne);
    pixels.add(rowTwo);
    pixels.add(rowThree);

    BasicImage image2 = new BasicImage(pixels);

    photoEditor = new BasicPhotoEditorModel();
    photoEditor.acceptImage("image2", image2);

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
    photoEditor.greyscale("image2", "image2-greyscale", "luma");
    assertTrue(imageLuma.equals(photoEditor.getDesiredImage("image2-greyscale")));
  }

  @Test
  public void testBlurImage() {

    zeroZero = new Posn(0,0);
    zeroOne = new Posn(0,1);
    zeroTwo = new Posn(0,2);
    oneZero = new Posn(1,0);
    oneOne = new Posn(1,1);
    oneTwo = new Posn(1,2);
    twoZero = new Posn(2,0);
    twoOne = new Posn(2,1);
    twoTwo = new Posn(2,2);


    one = new BasicPixel(zeroZero, new RGBColor(100,20,10));
    two = new BasicPixel(zeroOne, new RGBColor(100,20,10));
    three = new BasicPixel(zeroTwo, new RGBColor(100,20,10));
    four = new BasicPixel(oneZero, new RGBColor(40,22,76));
    five = new BasicPixel(oneOne, new RGBColor(40,22,76));
    six = new BasicPixel(oneTwo, new RGBColor(40,22,76));
    seven = new BasicPixel(twoZero, new RGBColor(20,220,100));
    eight = new BasicPixel(twoOne, new RGBColor(20,220,100));
    nine = new BasicPixel(twoTwo, new RGBColor(20,220,100));


    pixels = new ArrayList<ArrayList<BasicPixel>>();

    rowOne = new ArrayList<BasicPixel>();
    rowOne.add(one);
    rowOne.add(two);
    rowOne.add(three);

    rowTwo = new ArrayList<BasicPixel>();
    rowTwo.add(four);
    rowTwo.add(five);
    rowTwo.add(six);

    rowThree = new ArrayList<BasicPixel>();
    rowThree.add(seven);
    rowThree.add(eight);
    rowThree.add(nine);

    pixels.add(rowOne);
    pixels.add(rowTwo);
    pixels.add(rowThree);


    BasicImage image2 = new BasicImage(pixels);

    photoEditor = new BasicPhotoEditorModel();
    photoEditor.acceptImage("image2", image2);

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
    photoEditor.blur("image2", "image2-blur");
    assertTrue(imageBlur.equals(photoEditor.getDesiredImage("image2-blur")));
    assertTrue(imageBlur.getPixelAt(1,1).equals(photoEditor.getDesiredImage("image2-blur")
            .getPixelAt(1,1)));
  }

  @Test
  public void testSharpenImage() {

    zeroZero = new Posn(0,0);
    zeroOne = new Posn(0,1);
    zeroTwo = new Posn(0,2);
    Posn zeroThree = new Posn(0,3);
    Posn zeroFour = new Posn(0,4);

    oneZero = new Posn(1,0);
    oneOne = new Posn(1,1);
    oneTwo = new Posn(1,2);
    Posn oneThree = new Posn(1,3);
    Posn oneFour = new Posn(1,4);

    twoZero = new Posn(2,0);
    twoOne = new Posn(2,1);
    twoTwo = new Posn(2,2);
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


    one = new BasicPixel(zeroZero, new RGBColor(100,20,10));
    two = new BasicPixel(zeroOne, new RGBColor(100,20,10));
    three = new BasicPixel(zeroTwo, new RGBColor(100,20,10));
    four = new BasicPixel(zeroThree, new RGBColor(100,20,10));
    five = new BasicPixel(zeroFour, new RGBColor(100,20,10));

    six = new BasicPixel(oneZero, new RGBColor(40,22,76));
    seven = new BasicPixel(oneOne, new RGBColor(40,22,76));
    eight = new BasicPixel(oneTwo, new RGBColor(40,22,76));
    nine = new BasicPixel(oneThree, new RGBColor(40,22,76));
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

    pixels = new ArrayList<ArrayList<BasicPixel>>();

    rowOne = new ArrayList<BasicPixel>();
    rowOne.add(one);
    rowOne.add(two);
    rowOne.add(three);
    rowOne.add(four);
    rowOne.add(five);

    rowTwo = new ArrayList<BasicPixel>();
    rowTwo.add(six);
    rowTwo.add(seven);
    rowTwo.add(eight);
    rowTwo.add(nine);
    rowTwo.add(ten);

    rowThree = new ArrayList<BasicPixel>();
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

    photoEditor = new BasicPhotoEditorModel();
    photoEditor.acceptImage("image3", image3);

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
    photoEditor.sharpen("image3", "image3-sharpen");
    assertTrue(imageSharpen.equals(photoEditor.getDesiredImage("image3-sharpen")));
    assertEquals(imageSharpen, photoEditor.getDesiredImage("image3-sharpen"));
  }

  @Test
  public void testSepiaToneImage() {

    zeroZero = new Posn(0,0);
    zeroOne = new Posn(0,1);
    zeroTwo = new Posn(0,2);
    oneZero = new Posn(1,0);
    oneOne = new Posn(1,1);
    oneTwo = new Posn(1,2);
    twoZero = new Posn(2,0);
    twoOne = new Posn(2,1);
    twoTwo = new Posn(2,2);


    one = new BasicPixel(zeroZero, new RGBColor(100,20,10));
    two = new BasicPixel(zeroOne, new RGBColor(100,20,10));
    three = new BasicPixel(zeroTwo, new RGBColor(100,20,10));
    four = new BasicPixel(oneZero, new RGBColor(40,22,76));
    five = new BasicPixel(oneOne, new RGBColor(40,22,76));
    six = new BasicPixel(oneTwo, new RGBColor(40,22,76));
    seven = new BasicPixel(twoZero, new RGBColor(20,220,100));
    eight = new BasicPixel(twoOne, new RGBColor(20,220,100));
    nine = new BasicPixel(twoTwo, new RGBColor(20,220,100));


    pixels = new ArrayList<ArrayList<BasicPixel>>();

    rowOne = new ArrayList<BasicPixel>();
    rowOne.add(one);
    rowOne.add(two);
    rowOne.add(three);

    rowTwo = new ArrayList<BasicPixel>();
    rowTwo.add(four);
    rowTwo.add(five);
    rowTwo.add(six);

    rowThree = new ArrayList<BasicPixel>();
    rowThree.add(seven);
    rowThree.add(eight);
    rowThree.add(nine);

    pixels.add(rowOne);
    pixels.add(rowTwo);
    pixels.add(rowThree);


    BasicImage image2 = new BasicImage(pixels);

    photoEditor = new BasicPhotoEditorModel();
    photoEditor.acceptImage("image2", image2);

    BasicPixel oneSepia = new BasicPixel(zeroZero, new RGBColor(57,50,39));
    BasicPixel twoSepia = new BasicPixel(zeroOne, new RGBColor(57,50,39));
    BasicPixel threeSepia = new BasicPixel(zeroTwo, new RGBColor(57,50,39));

    BasicPixel fourSepia = new BasicPixel(oneZero,  new RGBColor(47,42,33));
    BasicPixel fiveSepia = new BasicPixel(oneOne, new RGBColor(47,42,33));
    BasicPixel sixSepia = new BasicPixel(oneTwo,  new RGBColor(47,42,33));

    BasicPixel sevenSepia = new BasicPixel(twoZero, new RGBColor(196,175,136));
    BasicPixel eightSepia = new BasicPixel(twoOne, new RGBColor(196,175,136));
    BasicPixel nineSepia = new BasicPixel(twoTwo, new RGBColor(196,175,136));


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
    photoEditor.sepia("image2", "image2-sepia");

    assertTrue(imageSepia.equals(photoEditor.getDesiredImage("image2-sepia")));
  }

  @Test
  public void testGreyscaleKernelImage() {

    zeroZero = new Posn(0,0);
    zeroOne = new Posn(0,1);
    zeroTwo = new Posn(0,2);
    oneZero = new Posn(1,0);
    oneOne = new Posn(1,1);
    oneTwo = new Posn(1,2);
    twoZero = new Posn(2,0);
    twoOne = new Posn(2,1);
    twoTwo = new Posn(2,2);


    one = new BasicPixel(zeroZero, new RGBColor(100,20,10));
    two = new BasicPixel(zeroOne, new RGBColor(100,20,10));
    three = new BasicPixel(zeroTwo, new RGBColor(100,20,10));
    four = new BasicPixel(oneZero, new RGBColor(40,22,76));
    five = new BasicPixel(oneOne, new RGBColor(40,22,76));
    six = new BasicPixel(oneTwo, new RGBColor(40,22,76));
    seven = new BasicPixel(twoZero, new RGBColor(20,220,100));
    eight = new BasicPixel(twoOne, new RGBColor(20,220,100));
    nine = new BasicPixel(twoTwo, new RGBColor(20,220,100));


    pixels = new ArrayList<ArrayList<BasicPixel>>();

    rowOne = new ArrayList<BasicPixel>();
    rowOne.add(one);
    rowOne.add(two);
    rowOne.add(three);

    rowTwo = new ArrayList<BasicPixel>();
    rowTwo.add(four);
    rowTwo.add(five);
    rowTwo.add(six);

    rowThree = new ArrayList<BasicPixel>();
    rowThree.add(seven);
    rowThree.add(eight);
    rowThree.add(nine);

    pixels.add(rowOne);
    pixels.add(rowTwo);
    pixels.add(rowThree);


    BasicImage image2 = new BasicImage(pixels);

    photoEditor = new BasicPhotoEditorModel();
    photoEditor.acceptImage("image2", image2);

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
    photoEditor.greyscaleKernel("image2", "image2-greyscale");

    assertTrue(imageGreyscale.equals(photoEditor.getDesiredImage("image2-greyscale")));
  }

  @Test
  public void testGetDesiredImage() {
    assertEquals(image, photoEditor.getDesiredImage("image"));
  }

  @Test
  public void testAcceptImage() {
    photoEditor.acceptImage("imageAccepted", image);
    assertEquals(image, photoEditor.getDesiredImage("imageAccepted"));
  }
}