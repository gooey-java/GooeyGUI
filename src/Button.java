import javax.swing.JButton;

public class Button extends Component{
    private JButton jButton;
    private Runnable clickAction;

    //TODO: Add alternative constructor for icon instead of text?
    // (It alread exists in JButton parent!)

    /**
     * Primary Constructor
     * Note: click action must be given in the form this::MethodName
     * @param text
     * @param x
     * @param y
     * @param width
     * @param height
     * @param clickAction
     */
    public Button(String text, int x, int y, int width, int height, Runnable clickAction) {
        super(new JButton(text), x, y, width, height);
        this.jButton = (JButton)getSwingComponent();
        this.clickAction = clickAction;

        // Listens for when the button is clicked.
        // When clicked, the listener triggers the stored action below.
        this.jButton.addActionListener(e -> {
            if (this.clickAction != null) {
                this.clickAction.run();
            }
        });
    }

    /**
     * Overloaded Constructor: no click action assigned at instantiation
     * @param text
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public Button(String text, int x, int y, int width, int height) {
        this(text, x, y, width, height, null);
    }

    /**
     * Assigns a method to call when the button is clicked.
     * Note: click action must be given in the form this::MethodName
     * @param clickAction
     */
    public void setOnClick(Runnable clickAction) {
        this.clickAction = clickAction;
    }

    /**
     * Gets the Button's text.
     * @return
     */
    public String getText() {
        return jButton.getText();
    }

    /**
     * Sets the Button's text.
     * @param text
     */
    public void setText(String text) {
        jButton.setText(text);
    }

}