package gooeygui;

import javax.swing.JTextField;

/**
 * A box where the user can type one line of text, such as a name or a number.
 *
 * <p>{@link #getText()} always returns a {@code String}. To use the text as a
 * number, convert it, for example with {@code Integer.parseInt(field.getText())}.</p>
 *
 * <pre>{@code
 * TextField nameField = new TextField(50, 100, 200, 30);
 * nameField.setOnSubmit(this::greetUser); // runs when the user presses Enter
 * window.addComponent(nameField);
 * }</pre>
 */
public class TextField extends Component {
    private JTextField jTextField;
    private Runnable submitAction;

    /**
     * Constructs a text field that starts with the given text.
     *
     * @param text the starting text
     * @param x the x-coordinate of the top-left corner, in pixels
     * @param y the y-coordinate of the top-left corner, in pixels
     * @param width the width of the text field, in pixels (at least 1)
     * @param height the height of the text field, in pixels (at least 1)
     * @throws GooeyGUIException if {@code text} is {@code null}, or if
     *                           {@code width} or {@code height} is less than 1
     */
    public TextField(String text, int x, int y, int width, int height) {
        super(new JTextField(Check.text(text, "a TextField")), x, y, width, height);
        this.jTextField = (JTextField) getSwingComponent();

        // Run the submit action when the user presses Enter.
        this.jTextField.addActionListener(e ->
                Check.runAction(submitAction, "the submit action for a TextField"));
    }

    /**
     * Constructs an empty text field.
     *
     * @param x the x-coordinate of the top-left corner, in pixels
     * @param y the y-coordinate of the top-left corner, in pixels
     * @param width the width of the text field, in pixels (at least 1)
     * @param height the height of the text field, in pixels (at least 1)
     * @throws GooeyGUIException if {@code width} or {@code height} is less than 1
     */
    public TextField(int x, int y, int width, int height) {
        this("", x, y, width, height);
    }

    /**
     * Returns the text currently in the text field.
     *
     * @return the text field's contents (an empty string if nothing was typed)
     */
    public String getText() {
        return jTextField.getText();
    }

    /**
     * Replaces the text in the text field.
     *
     * @param text the new text to display
     * @throws GooeyGUIException if {@code text} is {@code null}
     */
    public void setText(String text) {
        jTextField.setText(Check.text(text, "a TextField"));
    }

    /**
     * Sets the method to run when the user presses Enter while typing in this
     * text field.
     *
     * @param submitAction the method to run, written as {@code this::methodName},
     *                     or {@code null} to remove the action
     */
    public void setOnSubmit(Runnable submitAction) {
        this.submitAction = submitAction;
    }
}
