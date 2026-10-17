package gooeygui;

import java.util.ArrayList;
import javax.swing.JTabbedPane;

/**
 * A container with a row of tabs across the top. Each tab shows a different
 * {@link Panel}, and clicking a tab switches to that panel.
 *
 * <pre>{@code
 * TabbedPane tabs = new TabbedPane(0, 0, 500, 400);
 * Panel homePage = new Panel(0, 0, 500, 400);
 * Panel settingsPage = new Panel(0, 0, 500, 400);
 * tabs.addTab("Home", homePage);
 * tabs.addTab("Settings", settingsPage);
 * tabs.setOnChange(this::handleTabChange);
 * window.addComponent(tabs);
 * }</pre>
 */
public class TabbedPane extends Component {
    private JTabbedPane jTabbedPane;
    private ArrayList<Panel> tabs;
    private Runnable tabChangeAction;

    /**
     * Constructs a tabbed pane with no tabs.
     *
     * @param x the x-coordinate of the top-left corner, in pixels
     * @param y the y-coordinate of the top-left corner, in pixels
     * @param width the width of the tabbed pane, including the tab row, in pixels (at least 1)
     * @param height the height of the tabbed pane, including the tab row, in pixels (at least 1)
     * @throws GooeyGUIException if {@code width} or {@code height} is less than 1
     */
    public TabbedPane(int x, int y, int width, int height) {
        super(new JTabbedPane(), x, y, width, height);
        this.jTabbedPane = (JTabbedPane) getSwingComponent();
        this.tabs = new ArrayList<>();

        // Run the change action whenever a different tab is selected.
        this.jTabbedPane.addChangeListener(e ->
                Check.runAction(tabChangeAction, "the tab change action for a TabbedPane"));
    }

    /**
     * Adds a new tab to the right end of the tab row. The tabbed pane sizes and
     * positions the panel automatically.
     *
     * @param title the text shown on the tab
     * @param panel the panel to show when the tab is selected
     * @throws GooeyGUIException if {@code title} or {@code panel} is {@code null},
     *                           or if {@code panel} has already been added somewhere
     */
    public void addTab(String title, Panel panel) {
        Check.text(title, "a tab");
        Check.component(panel, "add a tab to a TabbedPane");
        checkNotInsideItself(panel);
        panel.attachTo(this);
        tabs.add(panel);
        panel.setParentEnabled(isUsable());
        jTabbedPane.addTab(title, panel.getSwingComponent());
        jTabbedPane.revalidate();
        jTabbedPane.repaint();
    }

    /**
     * Returns the number of tabs.
     *
     * @return the number of tabs that have been added
     */
    public int getTabCount() {
        return tabs.size();
    }

    /**
     * Returns the index of the currently selected tab. The first tab is at
     * index 0.
     *
     * @return the index of the selected tab, or -1 if there are no tabs
     */
    public int getSelectedIndex() {
        return jTabbedPane.getSelectedIndex();
    }

    /**
     * Switches to the tab at the given index. This also runs the change action.
     *
     * @param index the index of the tab to select, from 0 to {@code getTabCount() - 1}
     * @throws GooeyGUIException if {@code index} is out of bounds
     */
    public void setSelectedIndex(int index) {
        Check.index(index, tabs.size(), "TabbedPane", "tab");
        jTabbedPane.setSelectedIndex(index);
    }

    /**
     * Sets the method to run whenever a different tab is selected, either by
     * the user or by {@link #setSelectedIndex(int)}.
     *
     * @param tabChangeAction the method to run, written as {@code this::methodName},
     *                        or {@code null} to remove the action
     */
    public void setOnChange(Runnable tabChangeAction) {
        this.tabChangeAction = tabChangeAction;
    }

    /**
     * Enables or disables the tabbed pane and every tab panel inside it. While
     * disabled, the user cannot switch tabs.
     *
     * @param isEnabled {@code true} to allow interaction, {@code false} to gray
     *                  out the tabs and their contents
     */
    @Override
    public void setEnabled(boolean isEnabled) {
        super.setEnabled(isEnabled);
    }

    /**
     * Applies the enabled state to the tabbed pane and passes it on to every tab panel.
     */
    @Override
    void applyEnabled() {
        super.applyEnabled();
        for (Panel panel : tabs) {
            panel.setParentEnabled(isUsable());
        }
    }
}
