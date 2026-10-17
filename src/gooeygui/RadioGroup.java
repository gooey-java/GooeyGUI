package gooeygui;

import java.util.ArrayList;
import javax.swing.ButtonGroup;
import javax.swing.JRadioButton;

/**
 * Links {@link RadioButton} objects together so that only one of them can be
 * selected at a time. Selecting one button in the group automatically
 * unselects the others.
 *
 * <p>A {@code RadioGroup} is not visible and is not added to a {@link Window}.
 * Each radio button must still be added to the window (or a panel) separately.</p>
 *
 * <pre>{@code
 * RadioGroup sizes = new RadioGroup();
 * sizes.addRadioButton(small);
 * sizes.addRadioButton(large);
 * // later...
 * String choice = sizes.getSelectedText(); // "Small", "Large", or ""
 * }</pre>
 */
public class RadioGroup {
    private ButtonGroup buttonGroup;
    private ArrayList<RadioButton> buttons;

    /**
     * Constructs an empty radio group.
     */
    public RadioGroup() {
        this.buttonGroup = new ButtonGroup();
        this.buttons = new ArrayList<>();
    }

    /**
     * Adds a radio button to this group.
     *
     * @param radioButton the radio button to add
     * @throws GooeyGUIException if {@code radioButton} is {@code null} or already
     *                           belongs to a RadioGroup
     */
    public void addRadioButton(RadioButton radioButton) {
        Check.component(radioButton, "add a RadioButton to a RadioGroup");
        if (radioButton.getGroup() != null) {
            String which = (radioButton.getGroup() == this) ? "this" : "another";
            throw new GooeyGUIException(
                    radioButton.describe() + " is already in " + which + " RadioGroup.",
                    "Each RadioButton can belong to only one RadioGroup, and only once.");
        }
        radioButton.setGroup(this);
        buttons.add(radioButton);
        buttonGroup.add((JRadioButton) radioButton.getSwingComponent());
    }

    /**
     * Returns the text of the selected radio button in this group.
     *
     * @return the selected button's text, or an empty string ({@code ""}) if no
     *         button in the group is selected
     */
    public String getSelectedText() {
        for (RadioButton button : buttons) {
            if (button.isSelected()) {
                return button.getText();
            }
        }
        return "";
    }

    /**
     * Unselects every radio button in this group.
     */
    public void clearSelection() {
        buttonGroup.clearSelection();
    }
}
