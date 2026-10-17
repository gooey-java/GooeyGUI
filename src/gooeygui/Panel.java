package gooeygui;

import java.util.ArrayList;
import javax.swing.JPanel;

/**
 * A rectangular container that holds other components. Panels are useful for
 * grouping components so they can be shown, hidden, enabled, or disabled
 * together, and for building the pages of a {@link TabbedPane}.
 *
 * <p>The positions of components added to a panel are measured from the
 * top-left corner of the panel, not the window.</p>
 *
 * <pre>{@code
 * Panel settings = new Panel(0, 0, 400, 300);
 * settings.addComponent(new Label("Volume", 20, 20, 100, 30));
 * window.addComponent(settings);
 * settings.setActive(false);  // hides the panel and everything in it
 * settings.setEnabled(false); // grays out everything in the panel
 * }</pre>
 */
public class Panel extends Component {
    private JPanel jPanel;
    private ArrayList<Component> children;

    /**
     * Constructs an empty panel. If the panel will be used as a tab in a
     * {@link TabbedPane}, the tabbed pane sets its position and size
     * automatically, so the values given here are ignored.
     *
     * @param x the x-coordinate of the top-left corner, in pixels
     * @param y the y-coordinate of the top-left corner, in pixels
     * @param width the width of the panel, in pixels (at least 1)
     * @param height the height of the panel, in pixels (at least 1)
     * @throws GooeyGUIException if {@code width} or {@code height} is less than 1
     */
    public Panel(int x, int y, int width, int height) {
        super(new JPanel(null), x, y, width, height);
        this.jPanel = (JPanel) getSwingComponent();
        this.children = new ArrayList<>();
    }

    /**
     * Adds a component to this panel. The component's (x, y) position is
     * measured from the top-left corner of the panel. If this panel is
     * disabled, the new component is disabled too.
     *
     * @param component the component to add
     * @throws GooeyGUIException if {@code component} is {@code null}, has
     *                           already been added somewhere, or is this panel
     *                           (or a panel containing this one)
     */
    public void addComponent(Component component) {
        Check.component(component, "add a component to a Panel");
        checkNotInsideItself(component);
        component.attachTo(this);
        children.add(component);
        component.setParentEnabled(isUsable());
        jPanel.add(component.getSwingComponent());
        jPanel.revalidate();
        jPanel.repaint();
    }

    /**
     * Enables or disables this panel and every component inside it. Enabling
     * the panel again restores each inner component to its own setting, so a
     * component you disabled yourself stays disabled.
     *
     * @param isEnabled {@code true} to let the user interact with the panel's
     *                  contents, {@code false} to gray them all out
     */
    @Override
    public void setEnabled(boolean isEnabled) {
        super.setEnabled(isEnabled);
    }

    /**
     * Applies the enabled state to the panel and passes it on to every child.
     */
    @Override
    void applyEnabled() {
        super.applyEnabled();
        for (Component child : children) {
            child.setParentEnabled(isUsable());
        }
    }
}
