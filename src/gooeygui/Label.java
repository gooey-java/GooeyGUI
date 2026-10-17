package gooeygui;

import javax.swing.JLabel;
import javax.swing.SwingConstants;

/**
 * A piece of text displayed in the window. The user cannot edit a label, but
 * your program can change its text at any time.
 *
 * <pre>{@code
 * Label scoreLabel = new Label("Score: 0", 20, 20, 150, 30);
 * window.addComponent(scoreLabel);
 * scoreLabel.setText("Score: " + score);
 * }</pre>
 */
public class Label extends Component {
    private JLabel jLabel;

    /**
     * Constructs a label that displays the given text, aligned to the left.
     *
     * @param text the text to display
     * @param x the x-coordinate of the top-left corner, in pixels
     * @param y the y-coordinate of the top-left corner, in pixels
     * @param width the width of the label, in pixels (at least 1)
     * @param height the height of the label, in pixels (at least 1)
     * @throws GooeyGUIException if {@code text} is {@code null}, or if
     *                           {@code width} or {@code height} is less than 1
     */
    public Label(String text, int x, int y, int width, int height) {
        super(new JLabel(Check.text(text, "a Label")), x, y, width, height);
        this.jLabel = (JLabel) getSwingComponent();
    }

    /**
     * Returns the text displayed by the label.
     *
     * @return the label's text
     */
    public String getText() {
        return jLabel.getText();
    }

    /**
     * Sets the text displayed by the label.
     *
     * @param text the new text to display
     * @throws GooeyGUIException if {@code text} is {@code null}
     */
    public void setText(String text) {
        jLabel.setText(Check.text(text, describe()));
    }

    /**
     * Sets how the text is lined up inside the label's area. Capitalization does
     * not matter, so {@code "center"} and {@code "CENTER"} both work.
     *
     * @param alignment {@code "LEFT"}, {@code "CENTER"}, or {@code "RIGHT"}
     * @throws GooeyGUIException if {@code alignment} is not one of those values
     */
    public void setAlignment(String alignment) {
        if ("LEFT".equalsIgnoreCase(alignment)) {
            jLabel.setHorizontalAlignment(SwingConstants.LEFT);
        } else if ("CENTER".equalsIgnoreCase(alignment)) {
            jLabel.setHorizontalAlignment(SwingConstants.CENTER);
        } else if ("RIGHT".equalsIgnoreCase(alignment)) {
            jLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        } else {
            String given = (alignment == null) ? "null" : "\"" + alignment + "\"";
            throw new GooeyGUIException(
                    given + " is not a valid alignment for " + describe() + ".",
                    "Use \"LEFT\", \"CENTER\", or \"RIGHT\". (\"CENTRE\" is not accepted.)");
        }
    }
}
