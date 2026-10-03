import javax.swing.JComboBox;

/**
 * A dropdown selection menu component wrapping Java Swing's JComboBox.
 * Allows users to select a single text option from a list of choices.
 */
public class Dropdown extends Component {
    private JComboBox<String> jComboBox;
    private Runnable changeAction;

    /**
     * Constructs a Dropdown menu populated with an array of string options.
     *
     * @param options The array of String choices to populate the dropdown.
     * @param x The x-coordinate relative to the parent container.
     * @param y The y-coordinate relative to the parent container.
     * @param width The width of the dropdown component in pixels.
     * @param height The height of the dropdown component in pixels.
     */
    public Dropdown(String[] options, int x, int y, int width, int height) {
        super(new JComboBox<>(options), x, y, width, height);
        this.jComboBox = (JComboBox<String>)getSwingComponent();

        // Trigger callback whenever the selected item changes
        this.jComboBox.addActionListener(e -> {
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
     * Constructs an empty Dropdown menu.
     *
     * @param x The x-coordinate relative to the parent container.
     * @param y The y-coordinate relative to the parent container.
     * @param width The width of the dropdown component in pixels.
     * @param height The height of the dropdown component in pixels.
     */
    public Dropdown(int x, int y, int width, int height) {
        this(new String[0], x, y, width, height);
    }

    /**
     * Appends a new item option to the end of the dropdown menu list.
     *
     * @param item The text of the option to add.
     */
    public void addItem(String item) {
        jComboBox.addItem(item);
    }

    /**
     * Retrieves the currently selected text option.
     *
     * @return The String text of the selected option, or an empty string if nothing is selected.
     */
    public String getSelectedItem() {
        Object selected = jComboBox.getSelectedItem();
        return selected == null ? "" : selected.toString();
    }

    /**
     * Retrieves the index of the currently selected option.
     *
     * @return The zero-based index of the selection, or -1 if no item is selected.
     */
    public int getSelectedIndex() {
        return jComboBox.getSelectedIndex();
    }

    /**
     * Programmatically sets the selected option by its index.
     *
     * @param index The zero-based index of the item to select.
     */
    public void setSelectedIndex(int index) {
        if (index >= 0 && index < jComboBox.getItemCount()) {
            jComboBox.setSelectedIndex(index);
        }
    }

    /**
     * Assigns an action to execute whenever the user changes the selection.
     *
     * @param changeAction A Runnable lambda (e.g., () -> myMethod()) to execute on selection change.
     */
    public void setOnChange(Runnable changeAction) {
        this.changeAction = changeAction;
    }
}