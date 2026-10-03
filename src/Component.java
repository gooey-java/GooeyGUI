import javax.swing.JComponent;

/**
 * The parent of all components (such as Button and TextField objects). Any component 
 * must extend this class or a subclass of this class in order to be able to be added
 * to a Window object. 
 * 
 * NOTE: the Component class is "abstract" and cannot be instantiated (only its
 * non-abstract child classes can).
 */
public abstract class Component {
    // The underlying Swing component hidden from standard usage
    private JComponent swingComponent;
    private int x;
    private int y;
    private int width;
    private int height;

    public Component(JComponent swingComponent, int x, int y, int width, int height) {
        this.swingComponent = swingComponent;
        setPosition(x, y, width, height);
    }

    //Should not be accessible outside of package (like a jar?)
    // but protected would be accessible by subclasses outside
    // of package? --> TODO: Double check this!
    JComponent getSwingComponent() {
        return swingComponent;
    }

    // High-level wrapper method to set bounds
    private void setPosition(int x, int y, int width, int height) {
        swingComponent.setBounds(x, y, width, height);
    }

    /**
     * Sets the x-coordinate of the top left corner of the component.
     * @param x
     */
    public void setX(int x) {
        this.x = x;
        setPosition(x, y, width, height);
    }
    
    /**
     * Sets the y-coordinate of the top left corner of the component.
     * @param y
     */
    public void setY(int y) {
        this.y = y;
        setPosition(x, y, width, height);
    }

    /**
     * Sets the width of the component.
     * Note: text might not show if the component is too small.
     * @param width
     */
    public void setWidth(int width) {
        this.width = width;
        setPosition(x, y, width, height);
    }
    
    /**
     * Sets the height of the component.
     * Note: text might not show if the component is too small.
     * @param height
     */
    public void setHeight(int height) {
        this.height = height;
        setPosition(x, y, width, height);
    }

    /**
     * Enables or disables user editing of the component (true by default).
     *
     * @param isEnabled True to allow usage, false to make read-only.
     */
    public void setEnabled(boolean isEnabled) {
        swingComponent.setEnabled(isEnabled);
    }

    /**
     * Both enables/disables component and makes visible/invisible (true by default).
     *
     * @param isActive True to set visible and usable, false to set invisible and unusable.
     */
    void setActive(boolean isActive) {
        swingComponent.setVisible(isActive);
        swingComponent.setEnabled(isActive);
    }

}