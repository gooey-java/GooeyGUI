import javax.swing.JFrame;

public class Window {
    private JFrame frame;
    private int width;
    private int height;

    public Window(String title, int width, int height) {
        frame = new JFrame(title);
        frame.setSize(width, height);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(null); 
        frame.setLocationRelativeTo(null);
        frame.setResizable(false); // Disable window resizing
        frame.setVisible(true);

        this.width = width;
        this.height = height;
    }

    /**
     * Adds a component to the window at the (x,y) of the component. 
     * Fun fact: This is a "polymorphic" method because it accepts ANY subclass 
     * of SimpleComponent.
     * @param comp
     */
    public void addComponent(Component comp) {
        frame.add(comp.getSwingComponent());
        frame.revalidate(); // Re-indexes layout & input maps
        frame.repaint(); // Redraws pixels
    }

    /**
     * Gets the width of the window.
     * @return
     */
    public int getWidth() {
        return width;
    }

    /**
     * Gets the height of the window.
     * @return
     */
    public int getHeight() {
        return height;
    }

}