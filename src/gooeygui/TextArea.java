package gooeygui;

import javax.swing.JScrollPane;
import javax.swing.JTextArea;

/**
 * A box for displaying or typing multiple lines of text. Long lines wrap at
 * word boundaries, and a scroll bar appears when the text does not fit.
 *
 * <p>When a text area is disabled, its text can still be read and scrolled but
 * cannot be changed by the user, which makes it useful for showing output.</p>
 *
 * <pre>{@code
 * TextArea output = new TextArea(20, 60, 300, 200);
 * output.setEnabled(false); // read-only
 * window.addComponent(output);
 * output.setText("Line 1\nLine 2");
 * }</pre>
 */
public class TextArea extends Component {
    private JTextArea jTextArea;

    /**
     * Constructs a text area that starts with the given text.
     *
     * @param text the starting text
     * @param x the x-coordinate of the top-left corner, in pixels
     * @param y the y-coordinate of the top-left corner, in pixels
     * @param width the width of the text area, in pixels (at least 1)
     * @param height the height of the text area, in pixels (at least 1)
     * @throws GooeyGUIException if {@code text} is {@code null}, or if
     *                           {@code width} or {@code height} is less than 1
     */
    public TextArea(String text, int x, int y, int width, int height) {
        super(new JScrollPane(new JTextArea(Check.text(text, "a TextArea"))), x, y, width, height);

        // The wrapped Swing component is the scroll pane; get the text area inside it.
        JScrollPane scrollPane = (JScrollPane) getSwingComponent();
        this.jTextArea = (JTextArea) scrollPane.getViewport().getView();

        this.jTextArea.setLineWrap(true);
        this.jTextArea.setWrapStyleWord(true);
    }

    /**
     * Constructs an empty text area.
     *
     * @param x the x-coordinate of the top-left corner, in pixels
     * @param y the y-coordinate of the top-left corner, in pixels
     * @param width the width of the text area, in pixels (at least 1)
     * @param height the height of the text area, in pixels (at least 1)
     * @throws GooeyGUIException if {@code width} or {@code height} is less than 1
     */
    public TextArea(int x, int y, int width, int height) {
        this("", x, y, width, height);
    }

    /**
     * Returns all of the text in the text area. Lines are separated by
     * newline characters ({@code "\n"}).
     *
     * @return the text area's contents
     */
    public String getText() {
        return jTextArea.getText();
    }

    /**
     * Replaces all of the text in the text area.
     *
     * @param text the new text; use {@code "\n"} to start a new line
     * @throws GooeyGUIException if {@code text} is {@code null}
     */
    public void setText(String text) {
        jTextArea.setText(Check.text(text, "a TextArea"));
    }

    /**
     * Adds text to the end of the text area without removing what is already
     * there. Useful for showing output one line at a time.
     *
     * @param text the text to add; include {@code "\n"} to end the line
     * @throws GooeyGUIException if {@code text} is {@code null}
     */
    public void appendText(String text) {
        jTextArea.append(Check.text(text, "a TextArea"));
    }

    /**
     * Applies the enabled state. A disabled text area stays readable and
     * scrollable but cannot be edited.
     */
    @Override
    void applyEnabled() {
        super.applyEnabled();
        jTextArea.setEditable(isUsable());
    }
}
