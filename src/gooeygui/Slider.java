package gooeygui;

import javax.swing.JSlider;

/**
 * A bar with a knob the user can drag to choose a whole number within a range.
 *
 * <pre>{@code
 * //                     min, max, start, x,  y,   width, height
 * Slider volume = new Slider(0, 100, 50,  50, 100, 200,   40);
 * volume.setOnChange(this::updateVolume);
 * window.addComponent(volume);
 * }</pre>
 */
public class Slider extends Component {
    private JSlider jSlider;
    private Runnable changeAction;

    /**
     * Constructs a horizontal slider with the given range and starting value.
     *
     * @param min the smallest value the slider can have
     * @param max the largest value the slider can have
     * @param startingValue the value the slider starts at, from {@code min} to {@code max}
     * @param x the x-coordinate of the top-left corner, in pixels
     * @param y the y-coordinate of the top-left corner, in pixels
     * @param width the width of the slider, in pixels (at least 1)
     * @param height the height of the slider, in pixels (at least 1)
     * @throws GooeyGUIException if {@code min} is greater than {@code max}, if
     *                           {@code startingValue} is outside the range, or if
     *                           {@code width} or {@code height} is less than 1
     */
    public Slider(int min, int max, int startingValue, int x, int y, int width, int height) {
        super(new JSlider(checkRange(min, max, startingValue), max, startingValue),
                x, y, width, height);
        this.jSlider = (JSlider) getSwingComponent();

        // Run the change action repeatedly while the knob is being dragged.
        this.jSlider.addChangeListener(e ->
                Check.runAction(changeAction, "the change action for a Slider"));
    }

    /**
     * Checks the constructor's range values.
     *
     * @param min the minimum
     * @param max the maximum
     * @param startingValue the starting value
     * @return {@code min}, so this can be used inside the {@code super(...)} call
     * @throws GooeyGUIException if the range or starting value is invalid
     */
    private static int checkRange(int min, int max, int startingValue) {
        if (min > max) {
            throw new GooeyGUIException(
                    "The Slider's minimum (" + min + ") is greater than its maximum (" + max + ").",
                    "The order is new Slider(min, max, startingValue, x, y, width, height). "
                    + "Check that the first number is the smaller one.");
        }
        checkValue(startingValue, min, max, "starting value");
        return min;
    }

    /**
     * Checks that a value is within the slider's range.
     *
     * @param value the value to check
     * @param min the minimum
     * @param max the maximum
     * @param what a description of the value, such as {@code "starting value"}
     * @throws GooeyGUIException if {@code value} is outside {@code min} to {@code max}
     */
    private static void checkValue(int value, int min, int max, String what) {
        if (value < min || value > max) {
            throw new GooeyGUIException(
                    "The Slider's " + what + " (" + value + ") is outside its range of "
                    + min + " to " + max + ".",
                    "Use a value from " + min + " to " + max + ", or change the range with "
                    + "setMinimum() or setMaximum() first.");
        }
    }

    /**
     * Returns the slider's current value.
     *
     * @return the current value, between the minimum and maximum (inclusive)
     */
    public int getValue() {
        return jSlider.getValue();
    }

    /**
     * Moves the knob to the given value. This also runs the change action.
     *
     * @param value the new value, from the minimum to the maximum
     * @throws GooeyGUIException if {@code value} is outside the slider's range
     */
    public void setValue(int value) {
        checkValue(value, jSlider.getMinimum(), jSlider.getMaximum(), "new value");
        jSlider.setValue(value);
    }

    /**
     * Returns the smallest value the slider can have.
     *
     * @return the minimum value
     */
    public int getMinimum() {
        return jSlider.getMinimum();
    }

    /**
     * Returns the largest value the slider can have.
     *
     * @return the maximum value
     */
    public int getMaximum() {
        return jSlider.getMaximum();
    }

    /**
     * Sets the smallest value the slider can have. If the current value is
     * below the new minimum, the knob moves up to the minimum.
     *
     * @param min the new minimum value
     * @throws GooeyGUIException if {@code min} is greater than the current maximum
     */
    public void setMinimum(int min) {
        if (min > jSlider.getMaximum()) {
            throw new GooeyGUIException(
                    "The new minimum (" + min + ") is greater than the Slider's maximum ("
                    + jSlider.getMaximum() + ").",
                    "Raise the maximum with setMaximum() first, or use a smaller minimum.");
        }
        jSlider.setMinimum(min);
    }

    /**
     * Sets the largest value the slider can have. If the current value is
     * above the new maximum, the knob moves down to the maximum.
     *
     * @param max the new maximum value
     * @throws GooeyGUIException if {@code max} is less than the current minimum
     */
    public void setMaximum(int max) {
        if (max < jSlider.getMinimum()) {
            throw new GooeyGUIException(
                    "The new maximum (" + max + ") is less than the Slider's minimum ("
                    + jSlider.getMinimum() + ").",
                    "Lower the minimum with setMinimum() first, or use a larger maximum.");
        }
        jSlider.setMaximum(max);
    }

    /**
     * Sets the method to run whenever the slider's value changes. The method
     * runs many times while the user is dragging the knob, not just once at
     * the end.
     *
     * @param changeAction the method to run, written as {@code this::methodName},
     *                     or {@code null} to remove the action
     */
    public void setOnChange(Runnable changeAction) {
        this.changeAction = changeAction;
    }
}
