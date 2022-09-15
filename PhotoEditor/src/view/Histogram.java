package view;

import javax.swing.JPanel;
import java.awt.Dimension;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;


/**
 * Represents a class of a Histogram
 * that visualizes the distribution of color or intensities in an image.
 */
public class Histogram extends JPanel {
  private int[] values;

  private Color color;

  /**
   * Constructor for the histogram that is a
   * table of (value,frequency) entries.
   *
   * @param values an array of numbers (the values in an image).
   * @param color  the colors in an image
   */
  public Histogram(int[] values, Color color) {
    if (values == null) {
      throw new IllegalArgumentException("Image cannot be null");
    }
    this.values = values;
    this.color = color;
  }

  @Override
  protected void paintComponent(Graphics g) {
    super.paintComponent(g);
    int width = getWidth();
    int height = getHeight();
    int maxH = 0;

    int[] histoValues = new int[256];

    for (int currentValue : values) {
      histoValues[currentValue] = histoValues[currentValue] + 1;
    }

    Graphics2D graphics = (Graphics2D) g;

    int width2 = (int) Math.round(width / 255);

    graphics.drawRect(0, 0, width, height);
    graphics.setColor(color);

    for (int i = 0; i < histoValues.length; i++) {
      if (histoValues[i] > maxH) {
        maxH += histoValues[i];
      }
    }
    for (int j = 0; j < histoValues.length; j++) {
      graphics.fillRect(j * width2, height - Math.min((int) Math.round((histoValues[j]) * .02),
              maxH), width2, (histoValues[j]));
    }
  }

  @Override
  public Dimension getPreferredSize() {
    return new Dimension(256, 256);
  }
}