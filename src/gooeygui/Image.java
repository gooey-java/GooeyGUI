package gooeygui;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * A picture loaded from an image file (.png, .jpg, or .gif).
 *
 * <p>An {@code Image} can be shown two ways:</p>
 * <ul>
 *   <li>Added to a {@link Window} or {@link Panel} like any other component.
 *       The picture is stretched to the image's width and height.</li>
 *   <li>Given to a {@link Button} so the button shows the picture. The button
 *       resizes the picture to fit itself, so the image's position and size
 *       don't matter.</li>
 * </ul>
 *
 * <p>File paths start from the project folder (the folder you opened in
 * VS Code), not the {@code src} folder. A good habit is to keep images in a
 * folder named {@code images} inside the project folder.</p>
 *
 * <pre>{@code
 * // Shown in the window at (10, 10), stretched to 100 by 100 pixels
 * Image logo = new Image("images/logo.png", 10, 10, 100, 100);
 * window.addComponent(logo);
 *
 * // Used on a button, so no position or size is needed
 * Image playPicture = new Image("images/play.png");
 * Button play = new Button(playPicture, 20, 150, 60, 60, this::play);
 * }</pre>
 */
public class Image extends Component {
    private JLabel jLabel;
    private String currentPath;

    // The picture at its original size, kept so it can be re-scaled without reloading the file.
    private ImageIcon originalIcon;

    /**
     * Constructs an image at the given position, stretched to the given size.
     *
     * @param filePath the path to the image file, such as {@code "images/cat.png"}
     * @param x the x-coordinate of the top-left corner, in pixels
     * @param y the y-coordinate of the top-left corner, in pixels
     * @param width the width to display the image at, in pixels (at least 1)
     * @param height the height to display the image at, in pixels (at least 1)
     * @throws GooeyGUIException if the file cannot be found or is not a readable
     *                           image, or if {@code width} or {@code height} is
     *                           less than 1
     */
    public Image(String filePath, int x, int y, int width, int height) {
        super(new JLabel(), x, y, width, height);
        this.jLabel = (JLabel) getSwingComponent();
        setImagePath(filePath);
    }

    /**
     * Constructs an image at its original size, positioned at (0, 0). This is
     * the simplest way to make a picture for a {@link Button}.
     *
     * <pre>{@code
     * Button play = new Button(new Image("images/play.png"), 20, 20, 60, 60, this::play);
     * }</pre>
     *
     * @param filePath the path to the image file, such as {@code "images/play.png"}
     * @throws GooeyGUIException if the file cannot be found or is not a readable image
     */
    public Image(String filePath) {
        this(ImageFiles.load(filePath, "an Image"), filePath);
    }

    /**
     * Constructs an image at its original size from an already-loaded picture.
     *
     * @param loaded the loaded picture
     * @param filePath the path it was loaded from
     */
    private Image(ImageIcon loaded, String filePath) {
        super(new JLabel(), 0, 0, loaded.getIconWidth(), loaded.getIconHeight());
        this.jLabel = (JLabel) getSwingComponent();
        this.originalIcon = loaded;
        this.currentPath = filePath;
        refreshIcon();
    }

    /**
     * Returns the picture at its original size. Package-private; used by
     * {@link Button}.
     *
     * @return the original, unscaled picture
     */
    ImageIcon getOriginalIcon() {
        return originalIcon;
    }

    /**
     * Stretches the original picture to this component's current size.
     */
    private void refreshIcon() {
        jLabel.setIcon(ImageFiles.stretch(originalIcon, getWidth(), getHeight()));
    }

    /**
     * Loads a new image file and stretches it to fit this component's current
     * width and height. A {@link Button} already showing this image keeps its
     * old picture; call the button's {@code setImage} method to update it.
     *
     * @param filePath the path to the image file, such as {@code "images/dog.png"}
     * @throws GooeyGUIException if {@code filePath} is {@code null}, the file
     *                           cannot be found, or it is not a readable image
     */
    public void setImagePath(String filePath) {
        this.originalIcon = ImageFiles.load(filePath, "an Image");
        this.currentPath = filePath;
        refreshIcon();
    }

    /**
     * Returns the path of the image file this component is showing.
     *
     * @return the image file path
     */
    public String getImagePath() {
        return currentPath;
    }

    /**
     * Sets the width of the image and re-stretches the picture to fit.
     *
     * @param width the new width, in pixels (at least 1)
     * @throws GooeyGUIException if {@code width} is less than 1
     */
    @Override
    public void setWidth(int width) {
        super.setWidth(width);
        refreshIcon();
    }

    /**
     * Sets the height of the image and re-stretches the picture to fit.
     *
     * @param height the new height, in pixels (at least 1)
     * @throws GooeyGUIException if {@code height} is less than 1
     */
    @Override
    public void setHeight(int height) {
        super.setHeight(height);
        refreshIcon();
    }
}
