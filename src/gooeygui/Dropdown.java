package gooeygui;

import javax.swing.JComboBox;

/**
 * A drop-down menu that lets the user choose one text option from a list.
 *
 * <pre>{@code
 * String[] sizes = {"Small", "Medium", "Large"};
 * Dropdown sizeMenu = new Dropdown(sizes, 50, 100, 150, 30);
 * sizeMenu.setOnChange(this::updatePrice);
 * window.addComponent(sizeMenu);
 * }</pre>
 */
public class Dropdown extends Component {
    private JComboBox<String> jComboBox;
    private Runnable changeAction;

    /**
     * Constructs a drop-down menu containing the given options. The first
     * option is selected to start (if there is one).
     *
     * @param options the text options to show in the menu, in order
     * @param x the x-coordinate of the top-left corner, in pixels
     * @param y the y-coordinate of the top-left corner, in pixels
     * @param width the width of the menu, in pixels (at least 1)
     * @param height the height of the menu (when closed), in pixels (at least 1)
     * @throws GooeyGUIException if {@code options} or any option is {@code null},
     *                           or if {@code width} or {@code height} is less than 1
     */
    @SuppressWarnings("unchecked")
    public Dropdown(String[] options, int x, int y, int width, int height) {
        super(new JComboBox<>(checkOptions(options)), x, y, width, height);
        this.jComboBox = (JComboBox<String>) getSwingComponent();

        // Run the change action whenever the selected item changes.
        this.jComboBox.addActionListener(e ->
                Check.runAction(changeAction, "the change action for a Dropdown"));
    }

    /**
     * Constructs an empty drop-down menu. Use {@link #addItem(String)} to add
     * options.
     *
     * @param x the x-coordinate of the top-left corner, in pixels
     * @param y the y-coordinate of the top-left corner, in pixels
     * @param width the width of the menu, in pixels (at least 1)
     * @param height the height of the menu (when closed), in pixels (at least 1)
     * @throws GooeyGUIException if {@code width} or {@code height} is less than 1
     */
    public Dropdown(int x, int y, int width, int height) {
        this(new String[0], x, y, width, height);
    }

    /**
     * Checks that the options array and every option in it are not {@code null}.
     *
     * @param options the options to check
     * @return the same array
     * @throws GooeyGUIException if the array or an option is {@code null}
     */
    private static String[] checkOptions(String[] options) {
        if (options == null) {
            throw new GooeyGUIException("The options array for a Dropdown was null.",
                    "Create the array first, for example: String[] options = {\"A\", \"B\"};");
        }
        for (int i = 0; i < options.length; i++) {
            if (options[i] == null) {
                throw new GooeyGUIException(
                        "Option " + i + " in the Dropdown's options array was null.",
                        "Make sure every element of the array has been given a String.");
            }
        }
        return options;
    }

    /**
     * Adds an option to the end of the menu. If the menu was empty, the new
     * option becomes the selected option (and the change action runs).
     *
     * @param item the text of the option to add
     * @throws GooeyGUIException if {@code item} is {@code null}
     */
    public void addItem(String item) {
        jComboBox.addItem(Check.text(item, "a Dropdown item"));
    }

    /**
     * Returns the number of options in the menu.
     *
     * @return the number of options
     */
    public int getItemCount() {
        return jComboBox.getItemCount();
    }

    /**
     * Returns the text of the currently selected option.
     *
     * @return the selected option's text, or an empty string ({@code ""}) if
     *         nothing is selected
     */
    public String getSelectedItem() {
        Object selected = jComboBox.getSelectedItem();
        return selected == null ? "" : selected.toString();
    }

    /**
     * Returns the index of the currently selected option. The first option is
     * at index 0.
     *
     * @return the index of the selected option, or -1 if nothing is selected
     */
    public int getSelectedIndex() {
        return jComboBox.getSelectedIndex();
    }

    /**
     * Selects the option at the given index. This also runs the change action.
     *
     * @param index the index of the option to select, from 0 to {@code getItemCount() - 1}
     * @throws GooeyGUIException if {@code index} is out of bounds
     */
    public void setSelectedIndex(int index) {
        Check.index(index, jComboBox.getItemCount(), "Dropdown", "item");
        jComboBox.setSelectedIndex(index);
    }

    /**
     * Sets the method to run whenever the selected option changes.
     *
     * @param changeAction the method to run, written as {@code this::methodName},
     *                     or {@code null} to remove the action
     */
    public void setOnChange(Runnable changeAction) {
        this.changeAction = changeAction;
    }
}
