import javax.swing.JPanel;
import java.util.ArrayList;

public class Panel extends Component {
    private JPanel jPanel;
    private ArrayList<Component> children;

    public Panel(int x, int y, int width, int height) {
        super(new JPanel(), x, y, width, height);
        this.jPanel = (JPanel)getSwingComponent();
        this.jPanel.setLayout(null);
        this.children = new ArrayList<>();
    }

    /**
     * Adds a component to the panel.
     * @param component
     */
    public void addComponent(Component component) {
        children.add(component);
        jPanel.add(component.getSwingComponent());
        jPanel.revalidate();
        jPanel.repaint();
    }

    /**
     * Toggles visibility and interactivity for the panel 
     * and all the components the panel contains.
     */
    @Override
    public void setActive(boolean isActive) {
        // Sets active state on the panel itself
        super.setActive(isActive);
        
        // Propagates active state down to every child widget/sub-panel
        for (Component child : children) {
            child.setActive(isActive);
        }
    }
}