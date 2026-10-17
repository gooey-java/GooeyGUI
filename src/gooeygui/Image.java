package gooeygui;

import java.awt.MediaTracker;
import java.io.File;
import javax.swing.ImageIcon;
import javax.swing.JLabel;

/**
 * A picture loaded from an image file (.png, .jpg, or .gif) and stretched to
 * fit the component's width and height.
 *
 * <p>File paths are relative to the project folder (the folder you opened in
 * VS Code), not the {@code src} folder. A good habit is to keep images in a
 * folder named {@code images} inside the project folder.</p>
 *
 * <pre>{@code
 * Image logo = new Image("images/logo.png", 10, 10, 100, 100);
 * window.addComponent(logo);
 * }</pre>
 */
public class Image extends Component {
    private JLabel jLabel;
    private String currentPath;

    /**
     * Constructs an image component that displays the given image file.
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
     * Loads a new image file and stretches it to fit this component's current
     * width and height.
     *
     * @param filePath the path to the image file, such as {@code "images/dog.png"}
     * @throws GooeyGUIException if {@code filePath} is {@code null}, the file
     *                           cannot be found, or it is not a readable image
     */
    public void setImagePath(String filePath) {
        jLabel.setIcon(loadScaled(filePath));
        this.currentPath = filePath;
    }

    /**
     * Loads an image file and scales it to this component's size, with
     * friendly errors for common mistakes.
     *
     * @param filePath the path to the image file
     * @return the scaled image
     * @throws GooeyGUIException if the image cannot be loaded
     */
    private ImageIcon loadScaled(String filePath) {
        if (filePath == null) {
            throw new GooeyGUIException("The file path for an Image was null.",
                    "Give the path as a String, such as \"images/cat.png\".");
        }
        File file = new File(filePath);
        if (!file.exists()) {
            String hint = "Check the spelling (including capital letters and the extension, "
                    + "like .png or .jpg). Paths start from your project folder, not the src folder.";
            if (new File("src", filePath).exists()) {
                hint = "The file is inside your src folder. Either move it to your project "
                        + "folder or change the path to \"src/" + filePath + "\".";
            }
            throw new GooeyGUIException(
                    "Could not find the image file \"" + filePath + "\". GooeyGUI looked here: "
                    + file.getAbsolutePath(),
                    hint);
        }
        if (file.isDirectory()) {
            throw new GooeyGUIException(
                    "\"" + filePath + "\" is a folder, not an image file.",
                    "Add the image's file name to the end of the path, such as \""
                    + filePath + "/cat.png\".");
        }
        ImageIcon originalIcon = new ImageIcon(filePath);
        if (originalIcon.getImageLoadStatus() != MediaTracker.COMPLETE) {
            throw new GooeyGUIException(
                    "\"" + filePath + "\" exists, but Java could not read it as an image.",
                    "Use a .png, .jpg, or .gif file. Renaming a file's extension does not "
                    + "convert it; re-save or export it in one of those formats.");
        }
        // Fully-qualified java.awt.Image prevents a naming conflict with this Image class.
        java.awt.Image scaled = originalIcon.getImage().getScaledInstance(
                getWidth(), getHeight(), java.awt.Image.SCALE_SMOOTH);
        return new ImageIcon(scaled);
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
        setImagePath(currentPath);
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
        setImagePath(currentPath);
    }
}
