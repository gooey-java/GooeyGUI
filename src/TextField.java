import javax.swing.JTextField;

/**
 * A single-line text input field.
 * Useful for capturing brief user input such as names, numbers, or queries.
 */
public class TextField extends Component {
    private JTextField jTextField;
    private Runnable submitAction;

    /**
     * Constructs a TextField initialized with default text.
     *
     * @param text The initial text to display inside the text field.
     * @param x The x-coordinate relative to the parent container.
     * @param y The y-coordinate relative to the parent container.
     * @param width The width of the component in pixels.
     * @param height The height of the component in pixels.
     */
    public TextField(String text, int x, int y, int width, int height) {
        super(new JTextField(text), x, y, width, height);
        this.jTextField = (JTextField) getSwingComponent();

        // Triggers callback when the user presses Enter in the text field
        this.jTextField.addActionListener(e -> {
            if (submitAction != null) {
                try {
                    submitAction.run();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    /**
     * Constructs an empty TextField.
     *
     * @param x The x-coordinate relative to the parent container.
     * @param y The y-coordinate relative to the parent container.
     * @param width The width of the component in pixels.
     * @param height The height of the component in pixels.
     */
    public TextField(int x, int y, int width, int height) {
        this("", x, y, width, height);
    }

    /**
     * Gets the current text entered in the text field.
     *
     * @return The current text as a String.
     */
    public String getText() {
        return jTextField.getText();
    }

    /**
     * Sets the text displayed inside the text field.
     *
     * @param text The new text to display.
     */
    public void setText(String text) {
        jTextField.setText(text);
    }

    /**
     * Assigns an action to execute when the user presses the Enter key in this text field.
     *
     * @param submitAction A Runnable lambda (e.g., () -> handleSubmit()) to execute on Enter.
     */
    public void setOnSubmit(Runnable submitAction) {
        this.submitAction = submitAction;
    }

    @Override 
    public void setEnabled(boolean isEnabled) {
        super.setEnabled(isEnabled);
        jTextField.setEditable(isEnabled);
    }
}