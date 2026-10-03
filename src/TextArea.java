import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * A multi-line text display and input area wrapping Java Swing's JTextArea 
 * inside a JScrollPane. Supports automatic word wrapping and scrollbars.
 */
public class TextArea extends Component{
    private JTextArea jTextArea;

    /**
     * Constructs a TextArea initialized with default text.
     *
     * @param text The initial text to display inside the text area.
     * @param x The x-coordinate relative to the parent container.
     * @param y The y-coordinate relative to the parent container.
     * @param width The width of the component in pixels.
     * @param height The height of the component in pixels.
     */
    public TextArea(String text, int x, int y, int width, int height) {
        super(new JScrollPane(new JTextArea(text)), x, y, width, height);
        
        // Extract internal JTextArea from the JScrollPane view
        JScrollPane scrollPane = (JScrollPane) getSwingComponent();
        this.jTextArea = (JTextArea) scrollPane.getViewport().getView();

        // Enable automatic word wrapping
        this.jTextArea.setLineWrap(true);
        this.jTextArea.setWrapStyleWord(true);
    }

    /**
     * Constructs an empty TextArea.
     *
     * @param x The x-coordinate relative to the parent container.
     * @param y The y-coordinate relative to the parent container.
     * @param width The width of the component in pixels.
     * @param height The height of the component in pixels.
     */
    public TextArea(int x, int y, int width, int height) {
        this("", x, y, width, height);
    }

    /**
     * Gets the full text contained in the text area.
     * 
     * @return The complete content as a String.
     */
    public String getText() {
        return jTextArea.getText();
    }

    /**
     * Replaces the entire content of the text area with new text.
     * 
     * @param text The new text to display.
     */
    public void setText(String text) {
        jTextArea.setText(text);
    }

    @Override 
    public void setEnabled(boolean isEnabled) {
        super.setEnabled(isEnabled);
        jTextArea.setEditable(isEnabled);
    }

}