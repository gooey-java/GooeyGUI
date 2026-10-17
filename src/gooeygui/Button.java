package gooeygui;

import javax.swing.JButton;

/**
 * A clickable button that displays text and runs a method when clicked.
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
 */
public class Button extends Component {
    private JButton jButton;
    private Runnable clickAction;

    //TODO: Add alternative constructor for icon instead of text?
    // (It already exists in JButton parent!)

    /**
     * Constructs a button with text and a method to run when it is clicked.
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
     * Constructs a button with text and no click action. Use
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
     * @return the button's text
     */
    public String getText() {
        return jButton.getText();
    }

    /**
     * Sets the text displayed on the button.
     *
     * @param text the new text to display
     * @throws GooeyGUIException if {@code text} is {@code null}
     */
    public void setText(String text) {
        jButton.setText(Check.text(text, describe()));
    }
}
