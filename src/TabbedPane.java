import javax.swing.JTabbedPane;
import java.util.ArrayList;

public class TabbedPane extends Component {
    private JTabbedPane jTabbedPane;
    private ArrayList<Panel> tabs; // Explicitly store Panels
    private Runnable tabChangeAction;

    public TabbedPane(int x, int y, int width, int height) {
        super(new JTabbedPane(), x, y, width, height);
        this.jTabbedPane = (JTabbedPane) getSwingComponent();
        this.tabs = new ArrayList<>();
        
        // Trigger action callback when the active tab is switched
        this.jTabbedPane.addChangeListener(e -> {
            if (tabChangeAction != null) {
                try {
                    tabChangeAction.run();
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            }
        });
    }

    /**
     * Adds a Panel as a new tab.
     */
    public void addTab(String title, Panel panel) {
        tabs.add(panel);
        jTabbedPane.addTab(title, panel.getSwingComponent());
        jTabbedPane.revalidate();
        jTabbedPane.repaint();
    }

    /**
     * Gets the zero-based index of the currently selected tab.
     */
    public int getSelectedIndex() {
        return jTabbedPane.getSelectedIndex();
    }

    /**
     * Switches to a tab by index.
     */
    public void setSelectedIndex(int index) {
        if (index >= 0 && index < jTabbedPane.getTabCount()) {
            jTabbedPane.setSelectedIndex(index);
        }
    }

    /**
     * Toggles visibility and interactivity for the 
     * tabbed pane and all its tab panels.
     */
    @Override
    public void setActive(boolean active) {
        super.setActive(active);
        for (Panel panel : tabs) {
            panel.setActive(active);
        }
    }
}