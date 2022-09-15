package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JLabel;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JScrollPane;
import javax.swing.JFileChooser;
import javax.swing.ImageIcon;
import javax.swing.filechooser.FileNameExtensionFilter;
import controller.GuiController;
import controller.PhotoEditorGuiController;
import model.BasicImage;
import model.BasicPhotoEditorModel;
import model.BasicPixel;
import model.SuperPhotoEditorModel;

/**
 * This class represents a GUI view that is implemented using Java
 * Swing.
 */
public class SwingGuiView extends JFrame implements PhotoEditorGuiView {
  private JLabel messageLabel;
  private JPanel mainPanel;
  private JPanel histogramPanel;
  private Histogram redHisto;
  private Histogram greenHisto;
  private Histogram blueHisto;
  private Histogram intensityHisto;
  private JLabel fileOpenDisplay;
  private SuperPhotoEditorModel model;
  private PhotoEditorGuiController c;

  /**
   * The Gui of the photo editor. Create buttons and places for the
   * image while also allowing for user interaction by interacting with
   * the gui controller.
   */
  public SwingGuiView() {
    model = new BasicPhotoEditorModel();
    ActionListener controller = new GuiController(model, this);
    c = new GuiController(model, this);
    JFrame frame = new JFrame();
    frame.setLayout(new BorderLayout());
    frame.setResizable(false);
    mainPanel = new JPanel();
    messageLabel = new JLabel();
    ArrayList<ArrayList<BasicPixel>> pixels = new ArrayList<ArrayList<BasicPixel>>();
    BasicImage image = new BasicImage(pixels);

    mainPanel.setLayout(new BorderLayout());
    mainPanel.setPreferredSize(new Dimension(1000,900));

    JPanel dialogBoxesPanel = new JPanel();
    dialogBoxesPanel.setBorder(BorderFactory.createTitledBorder("Editing Options"));
    dialogBoxesPanel.setLayout(new BoxLayout(dialogBoxesPanel, BoxLayout.PAGE_AXIS));
    dialogBoxesPanel.setPreferredSize(new Dimension(200,500));
    mainPanel.add(dialogBoxesPanel, BorderLayout.WEST);

    //file open
    JPanel fileOpenPanel = new JPanel();
    fileOpenPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(fileOpenPanel);
    JButton fileOpenButton = new JButton("Open a file");
    fileOpenButton.setPreferredSize(new Dimension(200,30));
    fileOpenButton.setActionCommand("Open file");
    fileOpenButton.addActionListener(controller);
    fileOpenPanel.add(fileOpenButton);
    fileOpenDisplay = new JLabel();
    fileOpenPanel.add(fileOpenDisplay);

    //file save
    JPanel fileSavePanel = new JPanel();
    fileSavePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(fileSavePanel);
    JButton fileSaveButton = new JButton("Save a file");
    fileSaveButton.setPreferredSize(new Dimension(200,30));
    fileSaveButton.setActionCommand("Save file");
    fileSaveButton.addActionListener(controller);
    fileSavePanel.add(fileSaveButton);
    JLabel fileSaveDisplay = new JLabel();
    fileSavePanel.add(fileSaveDisplay);

    // brighten an image
    JPanel brightenPanel = new JPanel();
    brightenPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(brightenPanel);
    JButton brightenButton = new JButton("Brightness");
    brightenButton.setPreferredSize(new Dimension(200,30));
    brightenButton.setActionCommand("brighten");
    brightenButton.addActionListener(controller);
    brightenPanel.add(brightenButton);
    JLabel brightenDisplay = new JLabel();
    brightenPanel.add(brightenDisplay);

    // vertically flip an image
    JPanel verticalFlipPanel = new JPanel();
    verticalFlipPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(verticalFlipPanel);
    JButton verticalFlipButton = new JButton("Vertical-Flip");
    verticalFlipButton.setPreferredSize(new Dimension(200,30));
    verticalFlipButton.setActionCommand("vertical-flip");
    verticalFlipButton.addActionListener(controller);
    verticalFlipPanel.add(verticalFlipButton);
    JLabel verticalFlipDisplay = new JLabel();
    verticalFlipPanel.add(verticalFlipDisplay);

    // horizontally flip an image
    JPanel horizontalFlipPanel = new JPanel();
    horizontalFlipPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(horizontalFlipPanel);
    JButton horizontalFlipButton = new JButton("Horizontal-Flip");
    horizontalFlipButton.setPreferredSize(new Dimension(200,30));
    horizontalFlipButton.setActionCommand("horizontal-flip");
    horizontalFlipButton.addActionListener(controller);
    horizontalFlipPanel.add(horizontalFlipButton);
    JLabel horizontalFlipDisplay = new JLabel();
    horizontalFlipPanel.add(horizontalFlipDisplay);

    // grey scales an image by the value
    JPanel valuePanel = new JPanel();
    valuePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(valuePanel);
    JButton valueButton = new JButton("Value");
    valueButton.setPreferredSize(new Dimension(200,30));
    valueButton.setActionCommand("value-component");
    valueButton.addActionListener(controller);
    valuePanel.add(valueButton);
    JLabel valueDisplay = new JLabel();
    valuePanel.add(valueDisplay);

    // grey scales an image by the intensity value
    JPanel intensityPanel = new JPanel();
    intensityPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(intensityPanel);
    JButton intensityButton = new JButton("Intensity");
    intensityButton.setPreferredSize(new Dimension(200,30));
    intensityButton.setActionCommand("intensity-component");
    intensityButton.addActionListener(controller);
    intensityPanel.add(intensityButton);
    JLabel intensityDisplay = new JLabel();
    intensityPanel.add(intensityDisplay);

    // grey scales an image by the luma value
    JPanel lumaPanel = new JPanel();
    lumaPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(lumaPanel);
    JButton lumaButton = new JButton("Luma");
    lumaButton.setPreferredSize(new Dimension(200,30));
    lumaButton.setActionCommand("luma-component");
    lumaButton.addActionListener(controller);
    lumaPanel.add(lumaButton);
    JLabel lumaDisplay = new JLabel();
    lumaPanel.add(lumaDisplay);

    // grey scales an image by the red value
    JPanel redPanel = new JPanel();
    redPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(redPanel);
    JButton redButton = new JButton("Red-Component");
    redButton.setPreferredSize(new Dimension(200,30));
    redButton.setActionCommand("red-component");
    redButton.addActionListener(controller);
    redPanel.add(redButton);
    JLabel redDisplay = new JLabel();
    redPanel.add(redDisplay);

    // grey scales an image by the green value
    JPanel greenPanel = new JPanel();
    greenPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(greenPanel);
    JButton greenButton = new JButton("Green-Component");
    greenButton.setPreferredSize(new Dimension(200,30));
    greenButton.setActionCommand("green-component");
    greenButton.addActionListener(controller);
    greenPanel.add(greenButton);
    JLabel greenDisplay = new JLabel();
    greenPanel.add(greenDisplay);

    // grey scales an image by the blue value
    JPanel bluePanel = new JPanel();
    bluePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(bluePanel);
    JButton blueButton = new JButton("Blue-Component");
    blueButton.setPreferredSize(new Dimension(200,30));
    blueButton.setActionCommand("blue-component");
    blueButton.addActionListener(controller);
    bluePanel.add(blueButton);
    JLabel blueDisplay = new JLabel();
    bluePanel.add(blueDisplay);

    // blurs an image
    JPanel blurPanel = new JPanel();
    blurPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(blurPanel);
    JButton blurButton = new JButton("Blur");
    blurButton.setPreferredSize(new Dimension(200,30));
    blurButton.setActionCommand("blur");
    blurButton.addActionListener(controller);
    blurPanel.add(blurButton);
    JLabel blurDisplay = new JLabel();
    blurPanel.add(blurDisplay);

    // sepias an image
    JPanel sepiaPanel = new JPanel();
    sepiaPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(sepiaPanel);
    JButton sepiaButton = new JButton("Sepia");
    sepiaButton.setPreferredSize(new Dimension(200,30));
    sepiaButton.setActionCommand("sepia");
    sepiaButton.addActionListener(controller);
    sepiaPanel.add(sepiaButton);
    JLabel sepiaDisplay = new JLabel();
    sepiaPanel.add(sepiaDisplay);

    // sharpens an image
    JPanel sharpenPanel = new JPanel();
    sharpenPanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(sharpenPanel);
    JButton sharpenButton = new JButton("Sharpen");
    sharpenButton.setPreferredSize(new Dimension(200,30));
    sharpenButton.setActionCommand("sharpen");
    sharpenButton.addActionListener(controller);
    sharpenPanel.add(sharpenButton);
    JLabel sharpenDisplay = new JLabel();
    sharpenPanel.add(sharpenDisplay);

    // grey scales an image
    JPanel greyscalePanel = new JPanel();
    greyscalePanel.setPreferredSize(new Dimension(200,30));
    greyscalePanel.setLayout(new FlowLayout());
    dialogBoxesPanel.add(greyscalePanel);
    JButton greyscaleButton = new JButton("Greyscale");
    greyscaleButton.setPreferredSize(new Dimension(200,30));
    greyscaleButton.setActionCommand("greyscale");
    greyscaleButton.addActionListener(controller);
    greyscalePanel.add(greyscaleButton);
    JLabel greyscaleDisplay = new JLabel();
    greyscalePanel.add(greyscaleDisplay);

    JScrollPane scrollPane = new JScrollPane(fileOpenDisplay);
    scrollPane.setBorder(BorderFactory.createTitledBorder("Image"));
    scrollPane.setPreferredSize(new Dimension(500, 500));
    mainPanel.add(scrollPane);

    histogramPanel = new JPanel();
    redHisto = new Histogram(new int[]{}, Color.RED);
    blueHisto = new Histogram(new int[]{}, Color.BLUE);
    greenHisto = new Histogram(new int[]{}, Color.GREEN);
    intensityHisto = new Histogram(new int[]{}, Color.DARK_GRAY);
    histogramPanel.add(redHisto);
    histogramPanel.add(greenHisto);
    histogramPanel.add(blueHisto);
    histogramPanel.add(intensityHisto);

    JScrollPane histogramScroll = new JScrollPane(histogramPanel);
    histogramScroll.setBorder(BorderFactory.createTitledBorder("Histogram"));
    mainPanel.add(histogramScroll, BorderLayout.SOUTH);

    frame.setVisible(true);
    frame.add(mainPanel);
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    frame.setTitle("Photo Editor");
    frame.pack();
    repaint();
    revalidate();
  }

  @Override
  public String openFile() {
    final JFileChooser fileChooser = new JFileChooser(".");
    this.add(fileChooser);
    FileNameExtensionFilter filter = new FileNameExtensionFilter(
            "JPG, PPM, PNG & BMP Images", "jpg", "ppm", "png", "bmp");
    fileChooser.setFileFilter(filter);
    int retValue = fileChooser.showOpenDialog(SwingGuiView.this);
    if (retValue == JFileChooser.APPROVE_OPTION) {
      File f = fileChooser.getSelectedFile();
      refresh();
      return f.getAbsolutePath();
    }
    refresh();
    return "";
  }

  @Override
  public void updateImage(String fileName) {
    model.Image image = model.getDesiredImage(fileName);

    BufferedImage bi = new BufferedImage(image.getWidth(),
            image.getHeight(), BufferedImage.TYPE_INT_RGB);
    int a;
    for (int e = 0; e < image.getHeight(); e++) {
      for (int z = 0; z < image.getWidth(); z++) {
        BasicPixel p = image.getPixelAt(e,z);
        int redValue = p.getRed();
        int greenValue = p.getGreen();
        int blueValue = p.getBlue();
        Color color = new Color(redValue, greenValue, blueValue);
        a = color.getRGB();
        bi.setRGB(z, e, a);
      }
    }
    fileOpenDisplay.setIcon(new ImageIcon(bi));
    mainPanel.repaint();
    refresh();
  }

  @Override
  public void histogramCreator() {
    histogramPanel.remove(redHisto);
    histogramPanel.remove(greenHisto);
    histogramPanel.remove(blueHisto);
    histogramPanel.remove(intensityHisto);

    redHisto = new Histogram(c.histogramValue("red"), Color.RED);
    greenHisto = new Histogram(c.histogramValue("green"), Color.GREEN);
    blueHisto = new Histogram(c.histogramValue("blue"), Color.BLUE);
    intensityHisto = new Histogram(c.histogramValue("intensity"), Color.DARK_GRAY);
    histogramPanel.add(redHisto);
    histogramPanel.add(greenHisto);
    histogramPanel.add(blueHisto);
    histogramPanel.add(intensityHisto);
    repaint();
    revalidate();
  }

  @Override
  public void refresh() {
    histogramPanel.repaint();
    histogramPanel.revalidate();
  }

  @Override
  public void renderMessage(String message) {
    this.messageLabel.setText(message);
  }
}
