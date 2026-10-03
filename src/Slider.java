import javax.swing.JSlider;

/**
 * A graphical slider control wrapping Java Swing's JSlider.
 * Allows users to select a numeric value by dragging a knob within a bounded range.
 */
public class Slider extends Component {
    private JSlider jSlider;
    private Runnable changeAction;

    /**
     * Constructs a Slider with defined minimum, maximum, and starting values.
     *
     * @param min The minimum value of the slider range.
     * @param max The maximum value of the slider range.
     * @param startingValue The initial position value of the slider knob.
     * @param x The x-coordinate relative to the parent container.
     * @param y The y-coordinate relative to the parent container.
     * @param width The width of the slider component in pixels.
     * @param height The height of the slider component in pixels.
     */
    public Slider(int min, int max, int startingValue, int x, int y, int width, int height) {
        super(new JSlider(min, max, startingValue), x, y, width, height);
        this.jSlider = (JSlider)getSwingComponent();

        // Trigger callback while or after dragging the slider handle
        this.jSlider.addChangeListener(e -> {
            if (changeAction != null) {
                try {
                    changeAction.run();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    /**
     * Gets the current numeric value represented by the position of the slider knob.
     *
     * @return The current integer value of the slider.
     */
    public int getValue() {
        return jSlider.getValue();
    }

    /**
     * Programmatically sets the current numeric position of the slider knob.
     *
     * @param value The new integer position value for the slider knob.
     */
    public void setValue(int value) {
        jSlider.setValue(value);
    }

    /**
     * Sets the minimum allowable value for the slider range.
     *
     * @param min The new minimum integer value.
     */
    public void setMinimum(int min) {
        jSlider.setMinimum(min);
    }

    /**
     * Sets the maximum allowable value for the slider range.
     *
     * @param max The new maximum integer value.
     */
    public void setMaximum(int max) {
        jSlider.setMaximum(max);
    }

    /**
     * Assigns an action to execute whenever the slider's position changes.
     *
     * @param changeAction A Runnable lambda (e.g., () -> myMethod()) to execute when adjusted.
     */
    public void setOnChange(Runnable changeAction) {
        this.changeAction = changeAction;
    }
}