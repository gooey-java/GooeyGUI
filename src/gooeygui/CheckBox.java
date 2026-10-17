package gooeygui;

import javax.swing.JCheckBox;

/**
 * A box with a text label that the user can check or uncheck. Each check box
 * works independently; to let the user pick only one option from a group, use
 * {@link RadioButton} and {@link RadioGroup} instead.
 *
 * <pre>{@code
 * CheckBox extraCheese = new CheckBox("Extra cheese", 50, 100, 150, 30);
 * extraCheese.setOnChange(this::updatePrice);
 * window.addComponent(extraCheese);
 * }</pre>
 */
public class CheckBox extends Component {
    private JCheckBox jCheckBox;
    private Runnable changeAction;

    /**
     * Constructs an unchecked check box with a text label.
     *
     * @param text the text displayed next to the box
     * @param x the x-coordinate of the top-left corner, in pixels
     * @param y the y-coordinate of the top-left corner, in pixels
     * @param width the width of the check box and its label, in pixels (at least 1)
     * @param height the height of the check box, in pixels (at least 1)
     * @throws GooeyGUIException if {@code text} is {@code null}, or if
     *                           {@code width} or {@code height} is less than 1
     */
    public CheckBox(String text, int x, int y, int width, int height) {
        super(new JCheckBox(Check.text(text, "a CheckBox")), x, y, width, height);
        this.jCheckBox = (JCheckBox) getSwingComponent();

        // Run the change action whenever the user toggles the box.
        this.jCheckBox.addActionListener(e ->
                Check.runAction(changeAction, "the change action for " + describe()));
    }

    /**
     * Returns whether the box is currently checked.
     *
     * @return {@code true} if checked, {@code false} if unchecked
     */
    public boolean isSelected() {
        return jCheckBox.isSelected();
    }

    /**
     * Checks or unchecks the box. This does not run the change action.
     *
     * @param selected {@code true} to check the box, {@code false} to uncheck it
     */
    public void setSelected(boolean selected) {
        jCheckBox.setSelected(selected);
    }

    /**
     * Returns the text displayed next to the box.
     *
     * @return the check box's text
     */
    public String getText() {
        return jCheckBox.getText();
    }

    /**
     * Sets the text displayed next to the box.
     *
     * @param text the new text to display
     * @throws GooeyGUIException if {@code text} is {@code null}
     */
    public void setText(String text) {
        jCheckBox.setText(Check.text(text, describe()));
    }

    /**
     * Sets the method to run whenever the user checks or unchecks the box.
     *
     * @param changeAction the method to run, written as {@code this::methodName},
     *                     or {@code null} to remove the action
     */
    public void setOnChange(Runnable changeAction) {
        this.changeAction = changeAction;
    }
}
