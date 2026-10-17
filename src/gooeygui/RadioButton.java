package gooeygui;

import javax.swing.JRadioButton;

/**
 * A round option button with a text label. Add related radio buttons to the
 * same {@link RadioGroup} so the user can select only one of them at a time.
 *
 * <pre>{@code
 * RadioButton small = new RadioButton("Small", 50, 100, 100, 30);
 * RadioButton large = new RadioButton("Large", 50, 140, 100, 30);
 * RadioGroup sizes = new RadioGroup();
 * sizes.addRadioButton(small);
 * sizes.addRadioButton(large);
 * window.addComponent(small);
 * window.addComponent(large);
 * }</pre>
 */
public class RadioButton extends Component {
    private JRadioButton jRadioButton;
    private Runnable changeAction;

    // The group this button belongs to, or null if it is not in a group.
    private RadioGroup group;

    /**
     * Constructs an unselected radio button with a text label.
     *
     * @param text the text displayed next to the button
     * @param x the x-coordinate of the top-left corner, in pixels
     * @param y the y-coordinate of the top-left corner, in pixels
     * @param width the width of the radio button and its label, in pixels (at least 1)
     * @param height the height of the radio button, in pixels (at least 1)
     * @throws GooeyGUIException if {@code text} is {@code null}, or if
     *                           {@code width} or {@code height} is less than 1
     */
    public RadioButton(String text, int x, int y, int width, int height) {
        super(new JRadioButton(Check.text(text, "a RadioButton")), x, y, width, height);
        this.jRadioButton = (JRadioButton) getSwingComponent();

        // Run the change action whenever the user clicks this button.
        this.jRadioButton.addActionListener(e ->
                Check.runAction(changeAction, "the change action for " + describe()));
    }

    /**
     * Returns the group this button belongs to. Package-private.
     *
     * @return the group, or {@code null} if none
     */
    RadioGroup getGroup() {
        return group;
    }

    /**
     * Records the group this button belongs to. Package-private.
     *
     * @param group the group this button was added to
     */
    void setGroup(RadioGroup group) {
        this.group = group;
    }

    /**
     * Returns whether this radio button is currently selected.
     *
     * @return {@code true} if selected, {@code false} otherwise
     */
    public boolean isSelected() {
        return jRadioButton.isSelected();
    }

    /**
     * Selects or unselects this radio button. If it belongs to a
     * {@link RadioGroup}, selecting it unselects the others in the group.
     * This does not run the change action.
     *
     * @param selected {@code true} to select this button, {@code false} to
     *                 unselect it
     */
    public void setSelected(boolean selected) {
        jRadioButton.setSelected(selected);
    }

    /**
     * Returns the text displayed next to the button.
     *
     * @return the radio button's text
     */
    public String getText() {
        return jRadioButton.getText();
    }

    /**
     * Sets the text displayed next to the button.
     *
     * @param text the new text to display
     * @throws GooeyGUIException if {@code text} is {@code null}
     */
    public void setText(String text) {
        jRadioButton.setText(Check.text(text, describe()));
    }

    /**
     * Sets the method to run whenever the user clicks this radio button.
     *
     * @param changeAction the method to run, written as {@code this::methodName},
     *                     or {@code null} to remove the action
     */
    public void setOnChange(Runnable changeAction) {
        this.changeAction = changeAction;
    }
}
