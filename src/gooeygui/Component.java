package gooeygui;

import javax.swing.AbstractButton;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 * The parent class of every visual GooeyGUI component, such as {@link Button},
 * {@link Label}, and {@link TextField}. The methods listed here can be called on
 * any of those components.
 *
 * <p>Every component has a position and a size, measured in pixels. The position
 * (x, y) is the top-left corner of the component, measured from the top-left
 * corner of the {@link Window} or {@link Panel} that contains it. The x-coordinate
 * increases to the right and the y-coordinate increases <em>downward</em>.</p>
 *
 * <p>{@code Component} cannot be created directly. Create one of its subclasses
 * instead:</p>
 *
 * <pre>{@code
 * Button myButton = new Button("Click me", 50, 100, 120, 30);
 * myButton.setX(200);   // works on every kind of component
 * }</pre>
 */
public abstract class Component {
    // The underlying Swing component. Package-private access keeps it hidden from students.
    private JComponent swingComponent;
    private int x;
    private int y;
    private int width;
    private int height;

    // What the student last passed to setEnabled().
    private boolean enabled;

    // False when a Panel or TabbedPane containing this component is disabled.
    private boolean parentEnabled;

    // The Window or Component this was added to, or null if not added yet.
    private Object container;

    /**
     * Wraps a Swing component and places it at the given position and size.
     * Package-private, so only GooeyGUI's own classes can call it.
     *
     * @param swingComponent the Swing component being wrapped
     * @param x the x-coordinate of the top-left corner, in pixels
     * @param y the y-coordinate of the top-left corner, in pixels
     * @param width the width of the component, in pixels
     * @param height the height of the component, in pixels
     * @throws GooeyGUIException if {@code width} or {@code height} is less than 1
     */
    Component(JComponent swingComponent, int x, int y, int width, int height) {
        this.swingComponent = swingComponent;
        Check.size(width, "width", describe());
        Check.size(height, "height", describe());
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.enabled = true;
        this.parentEnabled = true;
        this.container = null;
        updateBounds();
    }

    // ------------------------------------------------------------------
    // Package-private helpers (hidden from students)
    // ------------------------------------------------------------------

    /**
     * Returns the wrapped Swing component.
     *
     * @return the underlying Swing component
     */
    JComponent getSwingComponent() {
        return swingComponent;
    }

    /**
     * Returns a short description for error messages, such as
     * {@code Button "Submit"} or {@code Slider}.
     *
     * @return a description of this component
     */
    String describe() {
        String name = getClass().getSimpleName();
        String text = null;
        if (swingComponent instanceof AbstractButton) {
            text = ((AbstractButton) swingComponent).getText();
        } else if (swingComponent instanceof JLabel) {
            text = ((JLabel) swingComponent).getText();
        }
        if (text == null || text.isEmpty()) {
            return name;
        }
        return name + " \"" + text + "\"";
    }

    /**
     * Returns the Window or Component this component was added to.
     *
     * @return the container, or {@code null} if this has not been added anywhere
     */
    Object getContainer() {
        return container;
    }

    /**
     * Records that this component was added to a container.
     *
     * @param newContainer the Window, Panel, or TabbedPane it is being added to
     * @throws GooeyGUIException if this component was already added somewhere
     */
    void attachTo(Object newContainer) {
        if (container != null) {
            String where = (container == newContainer) ? "this " : "a ";
            throw new GooeyGUIException(
                    describe() + " has already been added to " + where
                    + container.getClass().getSimpleName() + ".",
                    "Each component can only be added once, to one Window, Panel, or "
                    + "TabbedPane. To move it, use setX() and setY(). To show it in two "
                    + "places, create a second component.");
        }
        container = newContainer;
    }

    /**
     * Throws an error if adding {@code child} to this component would place a
     * component inside itself (directly or through other panels).
     *
     * @param child the component about to be added to this one
     * @throws GooeyGUIException if {@code child} is this component or contains it
     */
    void checkNotInsideItself(Component child) {
        Object current = this;
        while (current instanceof Component) {
            if (current == child) {
                String childName = child.getClass().getSimpleName();
                String problem = (child == this)
                        ? "You tried to add a " + childName + " to itself."
                        : "You tried to add a " + childName + " into a "
                          + getClass().getSimpleName() + " that is already inside that same "
                          + childName + ".";
                throw new GooeyGUIException(problem,
                        "A container cannot be placed inside itself. Check which Panel you "
                        + "are calling addComponent() on.");
            }
            current = ((Component) current).getContainer();
        }
    }

    /**
     * Returns whether the user can currently interact with this component: it
     * must be enabled itself, and every Panel or TabbedPane containing it must
     * be enabled too.
     *
     * @return {@code true} if usable, {@code false} otherwise
     */
    boolean isUsable() {
        return enabled && parentEnabled;
    }

    /**
     * Called by a containing Panel or TabbedPane when that container is
     * enabled or disabled.
     *
     * @param isParentEnabled whether the container is usable
     */
    void setParentEnabled(boolean isParentEnabled) {
        this.parentEnabled = isParentEnabled;
        applyEnabled();
    }

    /**
     * Applies the current usable state to the Swing component. Containers
     * override this to pass the state on to their children.
     */
    void applyEnabled() {
        swingComponent.setEnabled(isUsable());
    }

    /**
     * Applies the stored x, y, width, and height to the Swing component.
     */
    private void updateBounds() {
        swingComponent.setBounds(x, y, width, height);
    }

    // ------------------------------------------------------------------
    // Public methods
    // ------------------------------------------------------------------

    /**
     * Returns the x-coordinate of the top-left corner of this component.
     *
     * @return the x-coordinate, in pixels
     */
    public int getX() {
        return x;
    }

    /**
     * Returns the y-coordinate of the top-left corner of this component.
     *
     * @return the y-coordinate, in pixels
     */
    public int getY() {
        return y;
    }

    /**
     * Returns the width of this component.
     *
     * @return the width, in pixels
     */
    public int getWidth() {
        return width;
    }

    /**
     * Returns the height of this component.
     *
     * @return the height, in pixels
     */
    public int getHeight() {
        return height;
    }

    /**
     * Moves the component horizontally by setting the x-coordinate of its
     * top-left corner. The y-coordinate and size do not change.
     *
     * @param x the new x-coordinate, in pixels
     */
    public void setX(int x) {
        this.x = x;
        updateBounds();
    }

    /**
     * Moves the component vertically by setting the y-coordinate of its
     * top-left corner. The x-coordinate and size do not change.
     *
     * @param y the new y-coordinate, in pixels
     */
    public void setY(int y) {
        this.y = y;
        updateBounds();
    }

    /**
     * Sets the width of the component. Text may be cut off if the component
     * is too narrow to display it.
     *
     * @param width the new width, in pixels (at least 1)
     * @throws GooeyGUIException if {@code width} is less than 1
     */
    public void setWidth(int width) {
        Check.size(width, "width", describe());
        this.width = width;
        updateBounds();
    }

    /**
     * Sets the height of the component. Text may be cut off if the component
     * is too short to display it.
     *
     * @param height the new height, in pixels (at least 1)
     * @throws GooeyGUIException if {@code height} is less than 1
     */
    public void setHeight(int height) {
        Check.size(height, "height", describe());
        this.height = height;
        updateBounds();
    }

    /**
     * Enables or disables the component. A disabled component is still visible
     * but appears grayed out and cannot be clicked or edited by the user.
     * Components are enabled by default.
     *
     * <p>Disabling a {@link Panel} or {@link TabbedPane} also disables
     * everything inside it. Enabling it again restores each inner component to
     * its own setting, so a button you disabled yourself stays disabled.</p>
     *
     * @param isEnabled {@code true} to let the user interact with the component,
     *                  {@code false} to prevent interaction
     */
    public void setEnabled(boolean isEnabled) {
        this.enabled = isEnabled;
        applyEnabled();
    }

    /**
     * Returns the value last given to {@link #setEnabled(boolean)} for this
     * component. This does not check whether a containing Panel is disabled.
     *
     * @return {@code true} if this component is enabled, {@code false} otherwise
     */
    public boolean isEnabled() {
        return enabled;
    }

    /**
     * Shows or hides the component. An inactive component is invisible, so the
     * user cannot see or use it. Components are active by default.
     *
     * <p>Hiding a {@link Panel} or {@link TabbedPane} also hides everything
     * inside it. Showing it again restores each inner component to its own
     * setting, so a button you hid yourself stays hidden.</p>
     *
     * @param isActive {@code true} to show the component, {@code false} to hide it
     */
    public void setActive(boolean isActive) {
        swingComponent.setVisible(isActive);
    }

    /**
     * Returns the value last given to {@link #setActive(boolean)} for this
     * component. This does not check whether a containing Panel is hidden.
     *
     * @return {@code true} if this component is active, {@code false} otherwise
     */
    public boolean isActive() {
        return swingComponent.isVisible();
    }
}
