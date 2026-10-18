package gooeygui;

import java.awt.Insets;
import javax.swing.ImageIcon;
import javax.swing.JButton;

/**
 * A clickable button that runs a method when clicked. A button can show text,
 * an {@link Image}, or both.
 *
 * <p>The method to run is written as {@code this::methodName}, where
 * {@code methodName} is a {@code void} method with no parameters in your class.
 * Notice there are no parentheses after the method name.</p>
 *
 * <pre>{@code
 * public class MyApp {
 *     private Label message;
 *
 *     public MyApp() {
 *         Window window = new Window("My App", 400, 300);
 *         message = new Label("", 50, 150, 200, 30);
 *         window.addComponent(new Button("Say hi", 50, 100, 120, 30, this::sayHi));
 *         window.addComponent(message);
 *     }
 *
 *     public void sayHi() {
 *         message.setText("Hi!");
 *     }
 * }
 * }</pre>
 *
 * <h2>Buttons with pictures</h2>
 *
 * <p>Give the button an {@link Image}. The picture is resized to fit inside the
 * button without being stretched out of shape, so the image's own position and
 * size don't matter. When a button has both text and a picture, the picture
 * appears to the left of the text.</p>
 *
 * <pre>{@code
 * Image playPicture = new Image("images/play.png");
 * Button play = new Button(playPicture, 20, 20, 60, 60, this::play);
 *
 * Button save = new Button("Save", new Image("images/disk.png"), 100, 20, 120, 40, this::save);
 * }</pre>
 */
public class Button extends Component {
    private JButton jButton;
    private Runnable clickAction;

    // The Image this button shows, or null for a text-only button.
    private Image image;

    /**
     * Constructs a text button with a method to run when it is clicked.
     *
     * @param text the text displayed on the button
     * @param x the x-coordinate of the top-left corner, in pixels
     * @param y the y-coordinate of the top-left corner, in pixels
     * @param width the width of the button, in pixels (at least 1)
     * @param height the height of the button, in pixels (at least 1)
     * @param clickAction the method to run when clicked, written as
     *                    {@code this::methodName}
     * @throws GooeyGUIException if {@code text} is {@code null}, or if
     *                           {@code width} or {@code height} is less than 1
     */
    public Button(String text, int x, int y, int width, int height, Runnable clickAction) {
        super(new JButton(Check.text(text, "a Button")), x, y, width, height);
        this.jButton = (JButton) getSwingComponent();
        this.clickAction = clickAction;

        // When clicked, run the stored action (if there is one).
        this.jButton.addActionListener(e ->
                Check.runAction(this.clickAction, "the click action for " + describe()));
    }

    /**
     * Constructs a text button with no click action. Use
     * {@link #setOnClick(Runnable)} to choose a method later.
     *
     * @param text the text displayed on the button
     * @param x the x-coordinate of the top-left corner, in pixels
     * @param y the y-coordinate of the top-left corner, in pixels
     * @param width the width of the button, in pixels (at least 1)
     * @param height the height of the button, in pixels (at least 1)
     * @throws GooeyGUIException if {@code text} is {@code null}, or if
     *                           {@code width} or {@code height} is less than 1
     */
    public Button(String text, int x, int y, int width, int height) {
        this(text, x, y, width, height, null);
    }

    /**
     * Constructs a button that shows only a picture, with a method to run when
     * it is clicked.
     *
     * <pre>{@code
     * Button play = new Button(new Image("images/play.png"), 20, 20, 60, 60, this::play);
     * }</pre>
     *
     * @param image the picture to show on the button
     * @param x the x-coordinate of the top-left corner, in pixels
     * @param y the y-coordinate of the top-left corner, in pixels
     * @param width the width of the button, in pixels (at least 1)
     * @param height the height of the button, in pixels (at least 1)
     * @param clickAction the method to run when clicked, written as
     *                    {@code this::methodName}
     * @throws GooeyGUIException if {@code image} is {@code null}, or if
     *                           {@code width} or {@code height} is less than 1
     */
    public Button(Image image, int x, int y, int width, int height, Runnable clickAction) {
        this("", image, x, y, width, height, clickAction);
    }

    /**
     * Constructs a button that shows only a picture, with no click action. Use
     * {@link #setOnClick(Runnable)} to choose a method later.
     *
     * @param image the picture to show on the button
     * @param x the x-coordinate of the top-left corner, in pixels
     * @param y the y-coordinate of the top-left corner, in pixels
     * @param width the width of the button, in pixels (at least 1)
     * @param height the height of the button, in pixels (at least 1)
     * @throws GooeyGUIException if {@code image} is {@code null}, or if
     *                           {@code width} or {@code height} is less than 1
     */
    public Button(Image image, int x, int y, int width, int height) {
        this("", image, x, y, width, height, null);
    }

    /**
     * Constructs a button that shows text and a picture, with a method to run
     * when it is clicked. The picture appears to the left of the text.
     *
     * <pre>{@code
     * Button save = new Button("Save", new Image("images/disk.png"), 100, 20, 120, 40, this::save);
     * }</pre>
     *
     * @param text the text displayed next to the picture
     * @param image the picture to show on the button
     * @param x the x-coordinate of the top-left corner, in pixels
     * @param y the y-coordinate of the top-left corner, in pixels
     * @param width the width of the button, in pixels (at least 1)
     * @param height the height of the button, in pixels (at least 1)
     * @param clickAction the method to run when clicked, written as
     *                    {@code this::methodName}
     * @throws GooeyGUIException if {@code text} or {@code image} is {@code null},
     *                           or if {@code width} or {@code height} is less than 1
     */
    public Button(String text, Image image, int x, int y, int width, int height,
                  Runnable clickAction) {
        this(text, x, y, width, height, clickAction);
        setImage(image);
    }

    /**
     * Constructs a button that shows text and a picture, with no click action.
     * Use {@link #setOnClick(Runnable)} to choose a method later.
     *
     * @param text the text displayed next to the picture
     * @param image the picture to show on the button
     * @param x the x-coordinate of the top-left corner, in pixels
     * @param y the y-coordinate of the top-left corner, in pixels
     * @param width the width of the button, in pixels (at least 1)
     * @param height the height of the button, in pixels (at least 1)
     * @throws GooeyGUIException if {@code text} or {@code image} is {@code null},
     *                           or if {@code width} or {@code height} is less than 1
     */
    public Button(String text, Image image, int x, int y, int width, int height) {
        this(text, image, x, y, width, height, null);
    }

    /**
     * Returns a description for error messages. Picture-only buttons are
     * described by their image file.
     *
     * @return a description of this button
     */
    @Override
    String describe() {
        if (image != null && jButton.getText().isEmpty()) {
            return "Button with image \"" + image.getImagePath() + "\"";
        }
        return super.describe();
    }

    /**
     * Re-scales the picture (if there is one) to fit the button's current size.
     * With no text, the picture can fill the button; with text, it uses at most
     * half the width so the text has room.
     */
    private void refreshIcon() {
        if (image == null) {
            jButton.setIcon(null);
            return;
        }
        Insets insets = jButton.getInsets();
        int roomWidth = getWidth() - insets.left - insets.right;
        int roomHeight = getHeight() - insets.top - insets.bottom;
        if (!jButton.getText().isEmpty()) {
            roomWidth = roomWidth / 2;
        }
        ImageIcon picture = image.getOriginalIcon();
        jButton.setIcon(ImageFiles.fit(picture, Math.max(1, roomWidth), Math.max(1, roomHeight)));
    }

    /**
     * Sets the method to run when the button is clicked, replacing any
     * previous one.
     *
     * @param clickAction the method to run, written as {@code this::methodName},
     *                    or {@code null} to remove the action
     */
    public void setOnClick(Runnable clickAction) {
        this.clickAction = clickAction;
    }

    /**
     * Returns the text displayed on the button.
     *
     * @return the button's text ({@code ""} for a picture-only button)
     */
    public String getText() {
        return jButton.getText();
    }

    /**
     * Sets the text displayed on the button. If the button shows a picture,
     * the picture is resized to make room for the text (or to fill the button
     * if the new text is {@code ""}).
     *
     * @param text the new text to display, or {@code ""} for none
     * @throws GooeyGUIException if {@code text} is {@code null}
     */
    public void setText(String text) {
        jButton.setText(Check.text(text, describe()));
        refreshIcon();
    }

    /**
     * Shows a picture on the button, replacing any previous picture. This can
     * be used to change a button's picture while the program runs, such as
     * switching between "play" and "pause" pictures.
     *
     * <pre>{@code
     * playButton.setImage(pausePicture);
     * }</pre>
     *
     * @param image the picture to show on the button
     * @throws GooeyGUIException if {@code image} is {@code null}
     */
    public void setImage(Image image) {
        if (image == null) {
            String owner = describe().equals("Button") ? "a Button" : describe();
            throw new GooeyGUIException("The Image for " + owner + " was null.",
                    "Create the Image before using it, for example: new Image(\"images/play.png\"). "
                    + "To take the picture off a button, use removeImage() instead.");
        }
        this.image = image;
        refreshIcon();
    }

    /**
     * Returns the picture shown on the button.
     *
     * @return the button's Image, or {@code null} if the button has no picture
     */
    public Image getImage() {
        return image;
    }

    /**
     * Removes the picture from the button, leaving only its text.
     */
    public void removeImage() {
        this.image = null;
        refreshIcon();
    }

    /**
     * Sets the width of the button. If the button shows a picture, the picture
     * is resized to fit.
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
     * Sets the height of the button. If the button shows a picture, the
     * picture is resized to fit.
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
